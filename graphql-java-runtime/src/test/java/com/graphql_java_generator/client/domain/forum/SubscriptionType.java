/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import com.graphql_java_generator.client.request.InputParameter;
import com.graphql_java_generator.client.request.ObjectResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLQuery;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.RequestType;

import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.GraphqlClientUtils;
import com.graphql_java_generator.client.SubscriptionCallback;
import com.graphql_java_generator.client.SubscriptionClient;

/**
 * This class contains the response for a full request. See the 
 * <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client_subscription.html">plugin web site</A> 
 * for more information on full and partial requests.<BR/>
 * It also allows access to the _extensions_ part of the response. Take a look at the 
 * <A HRE="https://spec.graphql.org/June2018/#sec-Response">GraphQL spec</A> for more information on this.
 * 
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLQuery(name = "SubscriptionType", type = RequestType.subscription)
@GraphQLObjectType("SubscriptionType")
public class SubscriptionType extends SubscriptionTypeExecutor implements com.graphql_java_generator.client.GraphQLRequestObject {

	private ObjectMapper mapper = null;
	private JsonNode extensions;
	private Map<String, JsonNode> extensionsAsMap = null;


	public SubscriptionType(){
		// No action
	}

	@JsonProperty("subscribeToNewPost")
	@GraphQLInputParameters(names = {"boardName"}, types = {"String"}, mandatories = {true}, listDepths = {0}, itemsMandatory = {false})
	@GraphQLNonScalar(fieldName = "subscribeToNewPost", graphQLTypeSimpleName = "Post", javaClass = Post.class)
	Post subscribeToNewPost;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	String __typename;



	public void setSubscribeToNewPost(Post subscribeToNewPost) {
		this.subscribeToNewPost = subscribeToNewPost;
	}

	public Post getSubscribeToNewPost() {
		return subscribeToNewPost;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "SubscriptionType {"
				+ "subscribeToNewPost: " + subscribeToNewPost
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		SubscribeToNewPost("subscribeToNewPost"),
		__typename("__typename");

		private String fieldName;

		Field(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Class<?> getGraphQLType() {
			return this.getClass().getDeclaringClass();
		}

	}

	public static Builder builder() {
			return new Builder();
		}



	/**
	 * Builder
	 */
	public static class Builder {
		private Post subscribeToNewPost;


		public Builder withSubscribeToNewPost(Post subscribeToNewPost) {
			this.subscribeToNewPost = subscribeToNewPost;
			return this;
		}

		public SubscriptionType build() {
			SubscriptionType _object = new SubscriptionType();
			_object.setSubscribeToNewPost(subscribeToNewPost);
			_object.set__typename("SubscriptionType");
			return _object;
		}
	}

	/** {@inheritDoc} */
	public SubscriptionType(String graphqlEndpoint) {
		super(graphqlEndpoint);
	}

	/** {@inheritDoc} */
	public SubscriptionType(String graphqlEndpoint, SSLContext sslContext, HostnameVerifier hostnameVerifier) {
		super(graphqlEndpoint, sslContext, hostnameVerifier);
	}

	/** {@inheritDoc} */
	public SubscriptionType(String graphqlEndpoint, Client client, ObjectMapper objectMapper) {
		super(graphqlEndpoint, client, objectMapper);
	}
	
	private ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public JsonNode getExtensions() {
		return extensions;
	}
	
	public void setExtensions(JsonNode extensions) {
		this.extensions = extensions;
	}
	
	/**
	 * Returns the extensions as a map. The values can't be deserialized, as their type is unknown.
	 * 
	 * @return
	 */
	public Map<String, JsonNode> getExtensionsAsMap() {
		if (extensionsAsMap == null) {
			ObjectMapper mapper = new ObjectMapper();
			extensionsAsMap = mapper.convertValue(extensions, new TypeReference<Map<String, JsonNode>>() {
			});
		}
		return extensionsAsMap;
	}
	
	/**
	 * Parse the value for the given _key_, as found in the <I>extensions</I> field of the GraphQL server's response,
	 * into the given _t_ class.
	 * 
	 * @param <T>
	 * @param key
	 * @param t
	 * @return null if the key is not in the <I>extensions</I> map. Otherwise: the value for this _key_, as a _t_
	 *         instance
	 * @throws JsonProcessingException
	 *             When there is an error when converting the key's value into the _t_ class
	 */
	public <T> T getExtensionsField(String key, Class<T> t) throws JsonProcessingException {
		JsonNode node = getExtensionsAsMap().get(key);
		return (node == null) ? null : getMapper().treeToValue(node, t);
	}

		/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient subscribeToNewPostWithBindValues(String queryResponseDef, 
			SubscriptionCallback<com.graphql_java_generator.client.domain.forum.Post> subscriptionCallback
			, String boardName, 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.subscribeToNewPostWithBindValues(queryResponseDef, subscriptionCallback , boardName, parameters);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient subscribeToNewPost(String queryResponseDef, 
			SubscriptionCallback<com.graphql_java_generator.client.domain.forum.Post> subscriptionCallback
			, String boardName, 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.subscribeToNewPost(queryResponseDef, subscriptionCallback , boardName, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient subscribeToNewPostWithBindValues(ObjectResponse objectResponse,
			SubscriptionCallback<com.graphql_java_generator.client.domain.forum.Post> subscriptionCallback
			, String boardName, 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		return super.subscribeToNewPostWithBindValues(objectResponse, subscriptionCallback , boardName, parameters);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient subscribeToNewPost(ObjectResponse objectResponse,
			SubscriptionCallback<com.graphql_java_generator.client.domain.forum.Post> subscriptionCallback
			, String boardName, 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		return super.subscribeToNewPost(objectResponse, subscriptionCallback , boardName, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getSubscribeToNewPostResponseBuilder() throws GraphQLRequestPreparationException {
		return super.getSubscribeToNewPostResponseBuilder();
	}


	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public GraphQLRequest getSubscribeToNewPostGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.getSubscribeToNewPostGraphQLRequest(partialRequest);
	}
	
	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient __typenameWithBindValues(String queryResponseDef, 
			SubscriptionCallback<java.lang.String> subscriptionCallback
			, 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__typenameWithBindValues(queryResponseDef, subscriptionCallback , parameters);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient __typename(String queryResponseDef, 
			SubscriptionCallback<java.lang.String> subscriptionCallback
			, 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__typename(queryResponseDef, subscriptionCallback , paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient __typenameWithBindValues(ObjectResponse objectResponse,
			SubscriptionCallback<java.lang.String> subscriptionCallback
			, 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		return super.__typenameWithBindValues(objectResponse, subscriptionCallback , parameters);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public SubscriptionClient __typename(ObjectResponse objectResponse,
			SubscriptionCallback<java.lang.String> subscriptionCallback
			, 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		return super.__typename(objectResponse, subscriptionCallback , paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public com.graphql_java_generator.client.request.Builder get__typenameResponseBuilder() throws GraphQLRequestPreparationException {
		return super.get__typenameResponseBuilder();
	}


	/**
	 * This method is deprecated: please use {@link SubscriptionTypeExecutor} class instead of this class, to execute this method. 
	 * It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0 version.
	 */
	@Deprecated
	public GraphQLRequest get__typenameGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.get__typenameGraphQLRequest(partialRequest);
	}
	
}
