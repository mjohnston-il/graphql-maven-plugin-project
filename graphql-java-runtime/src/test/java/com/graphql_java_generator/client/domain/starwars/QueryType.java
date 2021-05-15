/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.starwars;

import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLQuery;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;
import com.graphql_java_generator.client.request.ObjectResponse;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

/**
 * This class contains the response for a full request. See the
 * <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/exec_graphql_requests.html">plugin web
 * site</A> for more information on full and partial requests.<BR/>
 * It also allows access to the _extensions_ part of the response. Take a look at the
 * <A HRE="https://spec.graphql.org/June2018/#sec-Response">GraphQL spec</A> for more information on this.
 * 
 * @author generated by graphql-java-generator
 * @see <a href=
 *      "https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLQuery(name = "QueryType", type = RequestType.query)
@GraphQLObjectType("QueryType")
public class QueryType extends QueryTypeExecutor implements com.graphql_java_generator.client.GraphQLRequestObject {

	private ObjectMapper mapper = null;
	private JsonNode extensions;
	private Map<String, JsonNode> extensionsAsMap = null;

	public QueryType() {
		// No action
	}

	/**
	 * hero is the main character of the episode
	 */
	@JsonProperty("hero")
	@GraphQLInputParameters(names = { "episode" }, types = { "Episode" }, mandatories = { false }, listDepths = {
			0 }, itemsMandatory = { false })
	@GraphQLNonScalar(fieldName = "hero", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	Character hero;

	@JsonProperty("characters")
	@JsonDeserialize(using = CustomJacksonDeserializers.ListCharacter.class)
	@GraphQLInputParameters(names = { "episode" }, types = { "Episode" }, mandatories = { false }, listDepths = {
			0 }, itemsMandatory = { false })
	@GraphQLNonScalar(fieldName = "characters", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	List<Character> characters;

	@JsonProperty("human")
	@GraphQLInputParameters(names = { "id" }, types = { "ID" }, mandatories = { false }, listDepths = {
			0 }, itemsMandatory = { false })
	@GraphQLNonScalar(fieldName = "human", graphQLTypeSimpleName = "Human", javaClass = Human.class)
	Human human;

	@JsonProperty("droid")
	@GraphQLInputParameters(names = { "id" }, types = { "ID" }, mandatories = { true }, listDepths = {
			0 }, itemsMandatory = { false })
	@GraphQLNonScalar(fieldName = "droid", graphQLTypeSimpleName = "Droid", javaClass = Droid.class)
	Droid droid;

	@JsonProperty("__schema")
	@GraphQLNonScalar(fieldName = "__schema", graphQLTypeSimpleName = "__Schema", javaClass = __Schema.class)
	__Schema __schema;

	@JsonProperty("__type")
	@GraphQLInputParameters(names = { "name" }, types = { "String" }, mandatories = { true }, listDepths = {
			0 }, itemsMandatory = { false })
	@GraphQLNonScalar(fieldName = "__type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	__Type __type;

	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	String __typename;

	/**
	 * hero is the main character of the episode
	 */
	public void setHero(Character hero) {
		this.hero = hero;
	}

	/**
	 * hero is the main character of the episode
	 */
	public Character getHero() {
		return hero;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public Human getHuman() {
		return human;
	}

	public void setDroid(Droid droid) {
		this.droid = droid;
	}

	public Droid getDroid() {
		return droid;
	}

	public void set__schema(__Schema __schema) {
		this.__schema = __schema;
	}

	public __Schema get__schema() {
		return __schema;
	}

	public void set__type(__Type __type) {
		this.__type = __type;
	}

	public __Type get__type() {
		return __type;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

	@Override
	public String toString() {
		return "QueryType {" + "hero: " + hero + ", " + "characters: " + characters + ", " + "human: " + human + ", "
				+ "droid: " + droid + ", " + "__schema: " + __schema + ", " + "__type: " + __type + ", "
				+ "__typename: " + __typename + "}";
	}

	/**
	 * Enum of field names
	 */
	public static enum Field implements GraphQLField {
		Hero("hero"), Characters("characters"), Human("human"), Droid("droid"), __schema("__schema"), __type(
				"__type"), __typename("__typename");

		private String fieldName;

		Field(String fieldName) {
			this.fieldName = fieldName;
		}

		@Override
		public String getFieldName() {
			return fieldName;
		}

		@Override
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
		private Character hero;
		private List<Character> characters;
		private Human human;
		private Droid droid;
		private __Schema __schema;
		private __Type __type;

		public Builder withHero(Character hero) {
			this.hero = hero;
			return this;
		}

		public Builder withCharacters(List<Character> characters) {
			this.characters = characters;
			return this;
		}

		public Builder withHuman(Human human) {
			this.human = human;
			return this;
		}

		public Builder withDroid(Droid droid) {
			this.droid = droid;
			return this;
		}

		public Builder with__schema(__Schema __schema) {
			this.__schema = __schema;
			return this;
		}

		public Builder with__type(__Type __type) {
			this.__type = __type;
			return this;
		}

		public QueryType build() {
			QueryType _object = new QueryType();
			_object.setHero(hero);
			_object.setCharacters(characters);
			_object.setHuman(human);
			_object.setDroid(droid);
			_object.set__schema(__schema);
			_object.set__type(__type);
			_object.set__typename("QueryType");
			return _object;
		}
	}

	/** {@inheritDoc} */
	public QueryType(String graphqlEndpoint) {
		super(graphqlEndpoint);
	}

	/** {@inheritDoc} */
	public QueryType(String graphqlEndpoint, SSLContext sslContext, HostnameVerifier hostnameVerifier) {
		super(graphqlEndpoint, sslContext, hostnameVerifier);
	}

	/** {@inheritDoc} */
	public QueryType(String graphqlEndpoint, Client client) {
		super(graphqlEndpoint, client);
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

	@Override
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
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public QueryTypeResponse execWithBindValues(String queryResponseDef, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.exec(queryResponseDef, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public QueryTypeResponse exec(String queryResponseDef, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.exec(queryResponseDef, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public QueryTypeResponse execWithBindValues(ObjectResponse objectResponse, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		return super.execWithBindValues(objectResponse, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public QueryTypeResponse exec(ObjectResponse objectResponse, Object... paramsAndValues)
			throws GraphQLRequestExecutionException {
		return super.exec(objectResponse, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.getResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest getGraphQLRequest(String fullRequest) throws GraphQLRequestPreparationException {
		return super.getGraphQLRequest(fullRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "hero", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public com.graphql_java_generator.client.domain.starwars.Character heroWithBindValues(String queryResponseDef,
			Episode episode, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.heroWithBindValues(queryResponseDef, episode, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "hero", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public com.graphql_java_generator.client.domain.starwars.Character hero(String queryResponseDef, Episode episode,
			Object... paramsAndValues) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.hero(queryResponseDef, episode, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "hero", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public com.graphql_java_generator.client.domain.starwars.Character heroWithBindValues(ObjectResponse objectResponse,
			Episode episode, Map<String, Object> parameters) throws GraphQLRequestExecutionException {
		return super.heroWithBindValues(objectResponse, episode, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "hero", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public com.graphql_java_generator.client.domain.starwars.Character hero(ObjectResponse objectResponse,
			Episode episode, Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.hero(objectResponse, episode, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getHeroResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.getHeroResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest getHeroGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.getHeroGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "characters", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public List<com.graphql_java_generator.client.domain.starwars.Character> charactersWithBindValues(
			String queryResponseDef, Episode episode, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.charactersWithBindValues(queryResponseDef, episode, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "characters", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public List<com.graphql_java_generator.client.domain.starwars.Character> characters(String queryResponseDef,
			Episode episode, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.characters(queryResponseDef, episode, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "characters", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public List<com.graphql_java_generator.client.domain.starwars.Character> charactersWithBindValues(
			ObjectResponse objectResponse, Episode episode, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		return super.charactersWithBindValues(objectResponse, episode, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "characters", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	public List<com.graphql_java_generator.client.domain.starwars.Character> characters(ObjectResponse objectResponse,
			Episode episode, Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.characters(objectResponse, episode, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getCharactersResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.getCharactersResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest getCharactersGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.getCharactersGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "human", graphQLTypeSimpleName = "Human", javaClass = Human.class)
	public com.graphql_java_generator.client.domain.starwars.Human humanWithBindValues(String queryResponseDef,
			String id, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.humanWithBindValues(queryResponseDef, id, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "human", graphQLTypeSimpleName = "Human", javaClass = Human.class)
	public com.graphql_java_generator.client.domain.starwars.Human human(String queryResponseDef, String id,
			Object... paramsAndValues) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.human(queryResponseDef, id, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "human", graphQLTypeSimpleName = "Human", javaClass = Human.class)
	public com.graphql_java_generator.client.domain.starwars.Human humanWithBindValues(ObjectResponse objectResponse,
			String id, Map<String, Object> parameters) throws GraphQLRequestExecutionException {
		return super.humanWithBindValues(objectResponse, id, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "human", graphQLTypeSimpleName = "Human", javaClass = Human.class)
	public com.graphql_java_generator.client.domain.starwars.Human human(ObjectResponse objectResponse, String id,
			Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.human(objectResponse, id, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getHumanResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.getHumanResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest getHumanGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.getHumanGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "droid", graphQLTypeSimpleName = "Droid", javaClass = Droid.class)
	public com.graphql_java_generator.client.domain.starwars.Droid droidWithBindValues(String queryResponseDef,
			String id, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.droidWithBindValues(queryResponseDef, id, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "droid", graphQLTypeSimpleName = "Droid", javaClass = Droid.class)
	public com.graphql_java_generator.client.domain.starwars.Droid droid(String queryResponseDef, String id,
			Object... paramsAndValues) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.droid(queryResponseDef, id, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "droid", graphQLTypeSimpleName = "Droid", javaClass = Droid.class)
	public com.graphql_java_generator.client.domain.starwars.Droid droidWithBindValues(ObjectResponse objectResponse,
			String id, Map<String, Object> parameters) throws GraphQLRequestExecutionException {
		return super.droidWithBindValues(objectResponse, id, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "droid", graphQLTypeSimpleName = "Droid", javaClass = Droid.class)
	public com.graphql_java_generator.client.domain.starwars.Droid droid(ObjectResponse objectResponse, String id,
			Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.droid(objectResponse, id, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder getDroidResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.getDroidResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest getDroidGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.getDroidGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__schema", graphQLTypeSimpleName = "__Schema", javaClass = __Schema.class)
	public com.graphql_java_generator.client.domain.starwars.__Schema __schemaWithBindValues(String queryResponseDef,
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__schemaWithBindValues(queryResponseDef, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__schema", graphQLTypeSimpleName = "__Schema", javaClass = __Schema.class)
	public com.graphql_java_generator.client.domain.starwars.__Schema __schema(String queryResponseDef,
			Object... paramsAndValues) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__schema(queryResponseDef, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__schema", graphQLTypeSimpleName = "__Schema", javaClass = __Schema.class)
	public com.graphql_java_generator.client.domain.starwars.__Schema __schemaWithBindValues(
			ObjectResponse objectResponse, Map<String, Object> parameters) throws GraphQLRequestExecutionException {
		return super.__schemaWithBindValues(objectResponse, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__schema", graphQLTypeSimpleName = "__Schema", javaClass = __Schema.class)
	public com.graphql_java_generator.client.domain.starwars.__Schema __schema(ObjectResponse objectResponse,
			Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.__schema(objectResponse, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder get__schemaResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.get__schemaResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest get__schemaGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.get__schemaGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	public com.graphql_java_generator.client.domain.starwars.__Type __typeWithBindValues(String queryResponseDef,
			String name, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__typeWithBindValues(queryResponseDef, name, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	public com.graphql_java_generator.client.domain.starwars.__Type __type(String queryResponseDef, String name,
			Object... paramsAndValues) throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__type(queryResponseDef, name, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	public com.graphql_java_generator.client.domain.starwars.__Type __typeWithBindValues(ObjectResponse objectResponse,
			String name, Map<String, Object> parameters) throws GraphQLRequestExecutionException {
		return super.__typeWithBindValues(objectResponse, name, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLNonScalar(fieldName = "__type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	public com.graphql_java_generator.client.domain.starwars.__Type __type(ObjectResponse objectResponse, String name,
			Object... paramsAndValues) throws GraphQLRequestExecutionException {
		return super.__type(objectResponse, name, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder get__typeResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.get__typeResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest get__typeGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.get__typeGraphQLRequest(partialRequest);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	public java.lang.String __typenameWithBindValues(String queryResponseDef, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__typenameWithBindValues(queryResponseDef, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	public java.lang.String __typename(String queryResponseDef, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		return super.__typename(queryResponseDef, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	public java.lang.String __typenameWithBindValues(ObjectResponse objectResponse, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		return super.__typenameWithBindValues(objectResponse, parameters);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	public java.lang.String __typename(ObjectResponse objectResponse, Object... paramsAndValues)
			throws GraphQLRequestExecutionException {
		return super.__typename(objectResponse, paramsAndValues);
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public com.graphql_java_generator.client.request.Builder get__typenameResponseBuilder()
			throws GraphQLRequestPreparationException {
		return super.get__typenameResponseBuilder();
	}

	/**
	 * This method is deprecated: please use {@link QueryTypeExecutor} class instead of this class, to execute this
	 * method. It is maintained to keep existing code compatible with the generated code. It will be removed in 2.0
	 * version.
	 */
	@Override
	@Deprecated
	public GraphQLRequest get__typenameGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return super.get__typenameGraphQLRequest(partialRequest);
	}

}
