/** Generated by the default template from graphql-java-generator */
package ${configuration.packageName};

#foreach($import in ${object.imports})
import $import;
#end

/**
#foreach ($comment in $object.comments)
 * $comment
#end
#if ($object.comments.size() > 0)
 * <BR/>
 * 
#end
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
${object.annotation}
public interface ${object.javaName} #if($object.implementz.size()>0)extends #foreach($impl in $object.implementz)$impl#if($foreach.hasNext), #end#end#end {
#foreach ($field in $object.fields)

#if ($field.comments.size() > 0)
	/**
#end	
#foreach ($comment in $field.comments)
	 * $comment
#end
#if ($field.comments.size() > 0)
	 */
#end
	${field.annotation}
	public void set${field.pascalCaseName}(${field.javaType} ${field.javaName});

#if ($field.comments.size() > 0)
	/**
#end	
#foreach ($comment in $field.comments)
	 * $comment
#end
#if ($field.comments.size() > 0)
	 */
#end
	${field.annotation}
	public ${field.javaType} get${field.pascalCaseName}();
#end
##
## When in client mode, we add the capability to receive unknown JSON attributes, which includes returned values for GraphQL aliases
##
#if(${configuration.mode}=="client")

	/**
	 * This method is called during the json deserialization process, by the {@link GraphQLObjectMapper}, each time an
	 * alias value is read from the json.
	 * 
	 * @param aliasName
	 * @param aliasDeserializedValue
	 */
	public void setAliasValue(String aliasName, Object aliasDeserializedValue);

	/**
	 * Retrieves the value for the given alias, as it has been received for this object in the GraphQL response. <BR/>
	 * This method <B>should not be used for Custom Scalars</B>, as the parser doesn't know if this alias is a custom
	 * scalar, and which custom scalar to use at deserialization time. In most case, a value will then be provided by
	 * this method with a basis json deserialization, but this value won't be the proper custom scalar value.
	 * 
	 * @param alias
	 * @return
	 */
	public Object getAliasValue(String alias);

#end
}
