package com.graphql_java_generator.client.domain.starwars;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;

/**
 * @author generated by graphql-java-generator
 * @See https://github.com/graphql-java-generator/graphql-java-generator
 */
public interface Character {

	@GraphQLScalar(graphQLTypeName = "ID", javaClass = String.class)
	public void setId(String id);

	@GraphQLScalar(graphQLTypeName = "ID", javaClass = String.class)
	public String getId();

	@GraphQLScalar(graphQLTypeName = "ID", javaClass = String.class)
	public void setName(String name);

	@GraphQLScalar(graphQLTypeName = "String", javaClass = String.class)
	public String getName();

	@GraphQLNonScalar(graphQLTypeName = "Character", javaClass = Character.class)
	@JsonDeserialize(contentAs = CharacterImpl.class)
	public void setFriends(List<Character> friends);

	@GraphQLNonScalar(graphQLTypeName = "Character", javaClass = Character.class)
	@JsonDeserialize(contentAs = CharacterImpl.class)
	public List<Character> getFriends();

	@GraphQLScalar(graphQLTypeName = "Episode", javaClass = Episode.class)
	@JsonDeserialize(contentAs = Episode.class)
	public void setAppearsIn(List<Episode> appearsIn);

	@GraphQLScalar(graphQLTypeName = "Episode", javaClass = Episode.class)
	@JsonDeserialize(contentAs = Episode.class)
	public List<Episode> getAppearsIn();
}