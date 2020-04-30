/**
 * 
 */
package com.graphql_java_generator.client.request;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;
import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.SubscriptionCallback;
import com.graphql_java_generator.client.SubscriptionClient;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

/**
 * This class contains the description for a GraphQL request that will be sent to the server. It's an abstract class,
 * and can not be use directly: a concrete class is generated by the plugin, when in client mode. This concrete class
 * provides all the necessary context to this abstract class for it to work properly.<BR/>
 * This class stores:
 * <UL>
 * <LI>The query part, if any</LI>
 * <LI>The mutation part, if any</LI>
 * <LI>The subscription part, if any</LI>
 * <LI>The fragments, if any</LI>
 * </UL>
 * 
 * @author etienne-sf
 */
public abstract class AbstractGraphQLRequest {

	/**
	 * This contains the default configuration, that will apply if no local configuration has been defined for this
	 * instance
	 */
	static GraphQLConfiguration staticConfiguration = null;

	/**
	 * This contains the configuration for this instance. This configuration overrides the {@link #staticConfiguration},
	 * if defined.
	 */
	GraphQLConfiguration instanceConfiguration = null;

	/** The query, if any */
	QueryField query = null;

	/** The mutation, if any */
	QueryField mutation = null;

	/** The mutation, if any */
	QueryField subscription = null;

	/** All the fragments defined for this query */
	List<Fragment> fragments = new ArrayList<>();

	/** The string that has been used to create this GraphQL request */
	final String graphQLRequest;

	/**
	 * Null if the request is a full request. Mandatory if the request is a partial request. When this GraphQLRequest is
	 * built for a partial query, that is for a particular query/mutation/subscription, then fieldName states whether
	 * this queryName is actually a query, a mutation or a subscription.
	 */
	RequestType requestType;
	/**
	 * Null if the request is a full request. Mandatory if the request is a partial request.<BR/>
	 * When this GraphQLRequest is built for a partial query, that is for a particular query/mutation/subscription, then
	 * queryName is the name of the query, mutation or subscription. This allow to check that the GraphQLRequest is the
	 * good to be executed for this partial query.
	 */
	final String queryName;
	/**
	 * The package name, where the generated classes are. It's used to load the class definition, and get the GraphQL
	 * metadata coming from the GraphQL schema
	 */
	protected final String packageName;

	/**
	 * Create the instance, from the GraphQL request, for a partial request.<BR/>
	 * 
	 * <B><U>Important note:</U></B> this constructor <B>SHOULD NOT</B> be called by external application. Its signature
	 * may change in the future. To prepare Partial Requests, application code <B>SHOULD</B> call the
	 * getXxxxGraphQLRequests methods, that are generated in the query/mutation/subscription java classes.
	 * 
	 * @param graphQLRequest
	 *            The <B>partial</B> GraphQL request, in text format. Writing partial request allows use to execute a
	 *            query/mutation/subscription, and only define what's expected as a response for this
	 *            query/mutation/subscription. You can send the parameters for this query/mutation/subscription as
	 *            parameter of the java method, without dealing with bind variable in the GraphQL query. Please read the
	 *            <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">client doc
	 *            page</A> for more information, including hints and limitations.
	 * @param requestType
	 *            The information whether this queryName is actually a query, a mutation or a subscription
	 * @param queryName
	 *            The name of the query, mutation or subscription
	 * @param inputParams
	 *            The list of input parameters for this query/mutation/subscription
	 * @throws GraphQLRequestPreparationException
	 */
	public AbstractGraphQLRequest(String graphQLRequest, RequestType requestType, String queryName,
			InputParameter... inputParams) throws GraphQLRequestPreparationException {
		if (requestType == null) {
			throw new NullPointerException("requestType is mandatory, but a null value has been provided");
		}
		if (queryName == null) {
			throw new NullPointerException("queryName is mandatory, but a null value has been provided");
		}
		this.requestType = requestType;
		this.queryName = queryName;
		this.graphQLRequest = graphQLRequest;
		this.packageName = this.getClass().getPackage().getName();

		QueryField field;
		switch (requestType) {
		case query:
			query = getQueryContext();// Get the query field from the concrete class
			field = new QueryField(query.clazz, queryName);
			query.fields.add(field);
			break;
		case mutation:
			mutation = getMutationContext();// Get the mutation field from the concrete class
			field = new QueryField(mutation.clazz, queryName);
			mutation.fields.add(field);
			break;
		case subscription:
			subscription = getSubscriptionContext();// Get the subscription field from the concrete class
			field = new QueryField(subscription.clazz, queryName);
			subscription.fields.add(field);
			break;
		default:
			throw new GraphQLRequestPreparationException("Non managed request type '" + requestType
					+ " while reading the GraphQL request: " + graphQLRequest);
		}

		// Let's add the input parameters to this new field
		field.inputParameters = Arrays.asList(inputParams);

		// Ok, we have to parse a string which looks like that: "query {human(id: &humanId) { id name friends{name}}}"
		// We tokenize the string, by using the space as a delimiter, and all other special GraphQL characters
		QueryTokenizer qt = new QueryTokenizer(this.graphQLRequest);

		// The graphQLRequest may be null (for instance for a scalar, or if we want the plugin to automatically add all
		// scalar fields for this query/mutation/subscription)
		if (!qt.hasMoreTokens()) {
			// Ok, we're done
		} else {
			// The first token must be a {
			// And we must read it first, before parsing the request content
			String token = qt.nextToken();
			if (!"{".equals(token)) {
				throw new GraphQLRequestPreparationException(
						"The Partial GraphQL Request should start by a '{', but it doesn't: " + graphQLRequest);
			}
			field.readTokenizerForResponseDefinition(qt);
		}

		// Let's finish the job
		finishRequestPreparation();
	}

	/**
	 * Creates the GraphQL request, for a full request. It will:
	 * <UL>
	 * <LI>Read the query and/or the mutation</LI>
	 * <LI>Read all fragment definitions</LI>
	 * <LI>For all non scalar field, subfields (and so on recursively), if they are empty (that is the query doesn't
	 * define the requested fields of a non scalar field, then all its scalar fields are added)</LI>
	 * <LI>Add the introspection __typename field to all scalar field list, if it doesnt't already exist. This is
	 * necessary to allow proper deserialization of interfaces and unions.</LI>
	 * </UL>
	 * 
	 * @param graphQLRequest
	 *            The GraphQL request, in text format, as defined in the GraphQL specifications, and as it can be used
	 *            in GraphiQL. Please read the
	 *            <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">client doc
	 *            page</A> for more information, including hints and limitations.
	 * 
	 * @throws GraphQLRequestPreparationException
	 */
	public AbstractGraphQLRequest(String graphQLRequest) throws GraphQLRequestPreparationException {
		this.queryName = null;
		this.graphQLRequest = graphQLRequest;
		this.packageName = this.getClass().getPackage().getName();
		this.requestType = RequestType.query; // query is the default value, as if there is no query, mutation or
												// subscription keyword, then it must be a query.

		// Ok, we have to parse a string which looks like that: "query {human(id: &humanId) { id name friends{name}}}"
		// We tokenize the string, by using the space as a delimiter, and all other special GraphQL characters
		QueryTokenizer qt = new QueryTokenizer(this.graphQLRequest);

		// We scan the input string. It may contain fragment definition and query/mutation/subscription
		while (qt.hasMoreTokens()) {
			String token = qt.nextToken();

			switch (token) {
			case "fragment":
				fragments.add(new Fragment(qt, packageName, false, null));
				break;
			case "query":
			case "mutation":
			case "subscription":
				requestType = RequestType.valueOf(token);
				break;
			case "{":
				// We read the query/mutation/subscription like any field.
				switch (requestType) {
				case query:
					query = getQueryContext();// Get the query field from the concrete class
					query.readTokenizerForResponseDefinition(qt);
					break;
				case mutation:
					mutation = getMutationContext();// Get the mutation field from the concrete class
					mutation.readTokenizerForResponseDefinition(qt);
					break;
				case subscription:
					subscription = getSubscriptionContext();// Get the subscription field from the concrete class
					subscription.readTokenizerForResponseDefinition(qt);
					break;
				default:
					throw new GraphQLRequestPreparationException("Non managed request type '" + requestType
							+ " while reading the GraphQL request: " + graphQLRequest);
				}
				break;
			default:
				throw new GraphQLRequestPreparationException(
						"Unknown token '" + token + " while reading the GraphQL request: " + graphQLRequest);
			}
		}

		if (query == null && mutation == null) {
			throw new GraphQLRequestPreparationException("No response definition found");
		}

		// Let's finish the job
		finishRequestPreparation();
	}

	/**
	 * This method executes the current GraphQL as a <B>query</B> or <B>mutation</B> GraphQL request, and return its
	 * response mapped in the relevant POJO. This method executes a partial GraphQL query, or a full GraphQL
	 * request.<BR/>
	 * <B>Note:</B> Don't forget to free the server's resources by calling the {@link WebSocketClient#stop()} method of
	 * the returned object.
	 * 
	 * @param <T>
	 * @param t
	 *            The type of the POJO which should be returned. It must be the query or the mutation class, generated
	 *            by the plugin
	 * @param params
	 * @return
	 * @throws GraphQLRequestExecutionException
	 */
	public <T> T exec(Class<T> t, Map<String, Object> params) throws GraphQLRequestExecutionException {
		if (instanceConfiguration != null) {
			return instanceConfiguration.getQueryExecutor().execute(this, params, t);
		} else if (staticConfiguration != null) {
			return staticConfiguration.getQueryExecutor().execute(this, params, t);
		} else {
			throw new GraphQLRequestExecutionException(
					"The GraphQLRequestConfiguration has not been set in the GraphQLRequest. "
							+ "Please set either the GraphQL instance configuration "
							+ "or the GraphQL static configuration before executing a GraphQL request");
		}
	}

	/**
	 * Execution of the given <B>subscription</B> GraphQL request, and return its response mapped in the relevant POJO.
	 * This method executes a partial GraphQL query, or a full GraphQL request.<BR/>
	 * <B>Note:</B> Don't forget to free the server's resources by calling the {@link WebSocketClient#stop()} method of
	 * the returned object.
	 * 
	 * @param <R>
	 *            The class that is generated from the subscription definition in the GraphQL schema. It contains one
	 *            attribute, for each available subscription. The data tag of the GraphQL server response will be mapped
	 *            into an instance of this class.
	 * @param <T>
	 *            The type that must is returned by the subscription in the GraphQL schema, which is actually the type
	 *            that will be sent in each notification received from this subscription.
	 * @param t
	 *            The type of the POJO which should be returned. It must be the query or the mutation class, generated
	 *            by the plugin
	 * @param params
	 *            the input parameters for this query. If the query has no parameters, it may be null or an empty list.
	 * @param subscriptionCallback
	 *            The object that will be called each time a message is received, or an error on the subscription
	 *            occurs. This object is provided by the application.
	 * @param subscriptionName
	 *            The name of the subscription that should be subscribed by this method call. It will be used to check
	 *            that the correct GraphQLRequest has been provided by the caller.
	 * @param subscriptionType
	 *            The R class
	 * @param messageType
	 *            The T class
	 * @return The Subscription client. It allows to stop the subscription, by executing its
	 *         {@link SubscriptionClient#unsubscribe()} method. This will stop the incoming notification flow, and will
	 *         free resources on both the client and the server.
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 * @throws IOException
	 */
	public <R, T> SubscriptionClient exec(Map<String, Object> params, SubscriptionCallback<T> subscriptionCallback,
			String subscriptionName, Class<R> subscriptionType, Class<T> messageType)
			throws GraphQLRequestExecutionException {
		if (instanceConfiguration != null) {
			return instanceConfiguration.getQueryExecutor().execute(this, params, subscriptionCallback,
					subscriptionName, subscriptionType, messageType);
		} else if (staticConfiguration != null) {
			return staticConfiguration.getQueryExecutor().execute(this, params, subscriptionCallback, subscriptionName,
					subscriptionType, messageType);
		} else {
			throw new GraphQLRequestExecutionException(
					"The GraphQLRequestConfiguration has not been set in the GraphQLRequest. "
							+ "Please set either the GraphQL instance configuration "
							+ "or the GraphQL static configuration before executing a GraphQL request");
		}
	}

	/**
	 * Adds the <I>__typename</I> fields to all non scalar types
	 * 
	 * @param graphQLRequest
	 * @throws GraphQLRequestPreparationException
	 */
	private void addTypenameFields() throws GraphQLRequestPreparationException {

		// We need the __typename fields, to properly parse the JSON response for interfaces and unions.
		// So we add it for every returned object.
		if (query != null) {
			query.addTypenameFields();
		}
		if (mutation != null) {
			mutation.addTypenameFields();
		}
		if (subscription != null) {
			subscription.addTypenameFields();
		}

		for (Fragment f : fragments) {
			f.addTypenameFields();
		}
	}

	/**
	 * Finish the preparation of the GraphQL request, once everything has been read:
	 * <UL>
	 * <LI>add the scalar fields, for all empty non scalar fields.</LI>
	 * <LI>Add the __typename field in fragments and field lists, to be sure to get it in return. This is necessary to
	 * properly deserialize the GRaphQL interfaces and unions
	 * </UL>
	 * 
	 * @throws GraphQLRequestPreparationException
	 */
	private void finishRequestPreparation() throws GraphQLRequestPreparationException {
		// For each non scalar field, we add its non scalar fields, if none was defined
		AddScalarFieldToEmptyNonScalarField(query);
		AddScalarFieldToEmptyNonScalarField(mutation);
		AddScalarFieldToEmptyNonScalarField(subscription);

		// Let's add the <I>__typename</I> fields to all non scalar types
		addTypenameFields();

	}

	private void AddScalarFieldToEmptyNonScalarField(QueryField field) throws GraphQLRequestPreparationException {
		// If this field contains no subfield, and is not a scalar, we add all its scalar fields, as requested fields.
		if (field == null || field.isScalar()) {
			// No action
		} else if (field.fields.size() == 0 && field.fragments.size() == 0 && field.inlineFragments.size() == 0) {
			// This non scalar field has no subfields in the GraphQL request. It also have no fragment
			// We'll request all it scalar fields.

			if (field.clazz.isInterface()) {
				// For interfaces, we look for getters
				for (Method m : field.clazz.getDeclaredMethods()) {
					if (m.getName().startsWith("get")) {
						GraphQLScalar graphQLScalar = m.getAnnotation(GraphQLScalar.class);
						if (graphQLScalar != null) {
							// We've found a subfield that is a scalar. Let's add it.
							field.fields.add(new QueryField(field.clazz, graphQLScalar.fieldName()));
						}
					}
				}
			} else {
				// For objects, we look for class's attributes
				for (Field f : field.clazz.getDeclaredFields()) {
					GraphQLScalar graphQLScalar = f.getAnnotation(GraphQLScalar.class);
					if (graphQLScalar != null) {
						// We've found a subfield that is a scalar. Let's add it.
						field.fields.add(new QueryField(field.clazz, graphQLScalar.fieldName()));
					}
				}
			}
		} else {
			// This non scalar fields contains requested subfield. We recurse into each of its fields.
			for (QueryField f : field.fields)
				AddScalarFieldToEmptyNonScalarField(f);
		} // for
	}

	/**
	 * 
	 * @param params
	 * @return
	 * @throws GraphQLRequestExecutionException
	 */
	public String buildRequest(Map<String, Object> params) throws GraphQLRequestExecutionException {
		StringBuilder sb = new StringBuilder("{\"query\":\"");

		// Let's start by the fragments
		for (Fragment fragment : fragments) {
			fragment.appendToGraphQLRequests(sb, params);
		}

		// Then the other parts of the request
		if (query != null) {
			query.appendToGraphQLRequests(sb, params, true);
		}
		if (mutation != null) {
			mutation.appendToGraphQLRequests(sb, params, true);
		}
		if (subscription != null) {
			subscription.appendToGraphQLRequests(sb, params, true);
		}

		sb.append("\",\"variables\":null,\"operationName\":null}");

		return sb.toString();
	}

	/**
	 * Retrieved the {@link QueryField} for the query (that is the query type coming from the GraphQL schema) from the
	 * concrete class.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	protected abstract QueryField getQueryContext() throws GraphQLRequestPreparationException;

	/**
	 * Retrieved the {@link QueryField} for the mutation (that is the mutation type coming from the GraphQL schema) from
	 * the concrete class.
	 * 
	 * @return
	 */
	protected abstract QueryField getMutationContext() throws GraphQLRequestPreparationException;

	/**
	 * Retrieved the {@link QueryField} for the subscription (that is the subscription type coming from the GraphQL
	 * schema) from the concrete class.
	 * 
	 * @return
	 */
	protected abstract QueryField getSubscriptionContext() throws GraphQLRequestPreparationException;

	public QueryField getQuery() {
		return query;
	}

	public QueryField getMutation() {
		return mutation;
	}

	public QueryField getSubscription() {
		return subscription;
	}

	public List<Fragment> getFragments() {
		return fragments;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public String getQueryName() {
		return queryName;
	}

	/**
	 * This gets the default configuration, that will apply if no local configuration has been defined for this
	 * instance.
	 * 
	 * @return the staticConfiguration
	 */
	public static GraphQLConfiguration getStaticConfiguration() {
		return staticConfiguration;
	}

	/**
	 * This sets the default configuration, that will apply if no local configuration has been defined for this
	 * instance.
	 * 
	 * @param staticConfiguration
	 *            the staticConfiguration to set
	 */
	public static void setStaticConfiguration(GraphQLConfiguration staticConfiguration) {
		AbstractGraphQLRequest.staticConfiguration = staticConfiguration;
	}

	/**
	 * This gets the configuration for this instance. This configuration overrides the
	 * {@link #getStaticConfiguration()}, if defined.
	 * 
	 * @return the instanceConfiguration
	 */
	public GraphQLConfiguration getInstanceConfiguration() {
		return instanceConfiguration;
	}

	/**
	 * This sets the configuration for this instance. This configuration overrides the
	 * {@link #getStaticConfiguration()}, if defined.
	 * 
	 * @param instanceConfiguration
	 *            the instanceConfiguration to set
	 */
	public void setInstanceConfiguration(GraphQLConfiguration instanceConfiguration) {
		this.instanceConfiguration = instanceConfiguration;
	}

}
