package com.graphql_java_generator.client.domain.allGraphQLCases;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author generated by graphql-java-generator
 * @see <a href=
 *      "https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public class MyQueryTypeWithOneMandatoryParam {

	@JsonProperty("withOneMandatoryParam")
	Character withOneMandatoryParam;

	public void setWithOneMandatoryParam(Character withOneMandatoryParam) {
		this.withOneMandatoryParam = withOneMandatoryParam;
	}

	public Character getWithOneMandatoryParam() {
		return withOneMandatoryParam;
	}

	@Override
	public String toString() {
		return "MyQueryTypeWithOneMandatoryParam {withOneMandatoryParam: " + withOneMandatoryParam + "}";
	}
}