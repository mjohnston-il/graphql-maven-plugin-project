package com.graphql_java_generator.client.domain.allGraphQLCases;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInputType;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLQuery;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;

import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("__EnumValue")
public class __EnumValue  {

	@JsonProperty("name")
	@GraphQLScalar(fieldName = "name", graphQLTypeName = "String", javaClass = String.class)
	String name;


	@JsonProperty("description")
	@GraphQLScalar(fieldName = "description", graphQLTypeName = "String", javaClass = String.class)
	String description;


	@JsonProperty("isDeprecated")
	@GraphQLScalar(fieldName = "isDeprecated", graphQLTypeName = "Boolean", javaClass = Boolean.class)
	Boolean isDeprecated;


	@JsonProperty("deprecationReason")
	@GraphQLScalar(fieldName = "deprecationReason", graphQLTypeName = "String", javaClass = String.class)
	String deprecationReason;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	String __typename;



	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setIsDeprecated(Boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public Boolean getIsDeprecated() {
		return isDeprecated;
	}

	public void setDeprecationReason(String deprecationReason) {
		this.deprecationReason = deprecationReason;
	}

	public String getDeprecationReason() {
		return deprecationReason;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "__EnumValue {"
				+ "name: " + name
				+ ", "
				+ "description: " + description
				+ ", "
				+ "isDeprecated: " + isDeprecated
				+ ", "
				+ "deprecationReason: " + deprecationReason
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Name("name"),
		Description("description"),
		IsDeprecated("isDeprecated"),
		DeprecationReason("deprecationReason"),
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
		private String name;
		private String description;
		private Boolean isDeprecated;
		private String deprecationReason;


		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder withIsDeprecated(Boolean isDeprecated) {
			this.isDeprecated = isDeprecated;
			return this;
		}
		public Builder withDeprecationReason(String deprecationReason) {
			this.deprecationReason = deprecationReason;
			return this;
		}

		public __EnumValue build() {
			__EnumValue object = new __EnumValue();
			object.setName(name);
			object.setDescription(description);
			object.setIsDeprecated(isDeprecated);
			object.setDeprecationReason(deprecationReason);
			object.set__typename("__EnumValue");
			return object;
		}
	}
}
