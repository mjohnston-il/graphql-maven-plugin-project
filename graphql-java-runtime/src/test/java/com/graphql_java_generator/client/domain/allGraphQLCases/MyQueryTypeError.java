package com.graphql_java_generator.client.domain.allGraphQLCases;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author generated by graphql-java-generator
 * @see <a href=
 *      "https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public class MyQueryTypeError {

	@JsonProperty("error")
	Character error;

	public void setError(Character error) {
		this.error = error;
	}

	public Character getError() {
		return error;
	}

	@Override
	public String toString() {
		return "MyQueryTypeError {error: " + error + "}";
	}
}