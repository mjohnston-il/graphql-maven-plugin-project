/** This template is custom **/
package ${pluginConfiguration.packageName};

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.graphql_java_generator.GraphqlUtils;

import graphql.schema.DataFetcher;

#foreach($import in $imports)
import $import;
#end

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Component
public class GraphQLDataFetchers {

	/** The logger for this instance */
	protected Logger logger = LoggerFactory.getLogger(GraphQLDataFetchers.class);

#foreach ($dataFetchersDelegate in $dataFetchersDelegates)
	@Resource
	${dataFetchersDelegate.pascalCaseName} ${dataFetchersDelegate.camelCaseName};

#end
	@Resource
	GraphqlUtils graphqlUtils;

#foreach ($dataFetchersDelegate in $dataFetchersDelegates)
	////////////////////////////////////////////////////////////////////////////////////////////////
	// Data fetchers for ${dataFetchersDelegate.name}
	////////////////////////////////////////////////////////////////////////////////////////////////
#foreach ($dataFetcher in $dataFetchersDelegate.dataFetchers)

	public DataFetcher<#if(${dataFetcher.completableFuture})CompletableFuture<#end#if(${dataFetcher.field.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${dataFetcher.field.list})>#end#if(${dataFetcher.completableFuture})>#end> ${dataFetchersDelegate.camelCaseName}${dataFetcher.pascalCaseName}() {
		return dataFetchingEnvironment -> {
#foreach ($argument in $dataFetcher.field.inputParameters)          
## $argument is an instance of Field
#if ($argument.type.class.simpleName == "EnumType")
#if ($argument.mandatory)
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.camelCaseName} = ${argument.type.classSimpleName}.valueOf(dataFetchingEnvironment.getArgument("${argument.javaName}"));
#else
			${argument.type.classSimpleName} ${argument.camelCaseName} = null;
			if (dataFetchingEnvironment.getArgument("${argument.name}") != null)
				${argument.camelCaseName} = ${argument.type.classSimpleName}.valueOf(dataFetchingEnvironment.getArgument("${argument.name}"));
#end
#elseif (${argument.type.inputType})
#if(${argument.list})
			List<${argument.type.classSimpleName}> ${argument.camelCaseName} = graphqlUtils.getListInputObjects((List<Map<String, Object>>) dataFetchingEnvironment.getArgument("${argument.name}"), ${argument.type.classSimpleName}.class);
#else
			${argument.type.classSimpleName} ${argument.camelCaseName} = graphqlUtils.getInputObject((Map<String, Object>) dataFetchingEnvironment.getArgument("${argument.name}"), ${argument.type.classSimpleName}.class);
#end
#elseif (${argument.type.classSimpleName} == "UUID")
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.camelCaseName} = (dataFetchingEnvironment.getArgument("${argument.name}") == null) ? null : UUID.fromString(dataFetchingEnvironment.getArgument("${argument.name}"));
#else
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.camelCaseName} = dataFetchingEnvironment.getArgument("${argument.name}");
#end
#end  ##Foreach
#if($dataFetcher.sourceName)
			${dataFetcher.sourceName} source = dataFetchingEnvironment.getSource();
#end

#if (${dataFetcher.completableFuture})
			DataLoader<${dataFetcher.field.type.identifier.type.classSimpleName}, #if(${argument.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${argument.list})>#end> dataLoader = dataFetchingEnvironment.getDataLoader("${dataFetcher.field.type.classSimpleName}"); 
			
			return ${dataFetchersDelegate.camelCaseName}.${dataFetcher.camelCaseName}(dataFetchingEnvironment, dataLoader#if($dataFetcher.sourceName), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.camelCaseName}#end);
#elseif (${dataFetcher.field.list})
			List<${dataFetcher.field.type.classSimpleName}> ret = ${dataFetchersDelegate.camelCaseName}.${dataFetcher.camelCaseName}(dataFetchingEnvironment#if($dataFetcher.sourceName), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.camelCaseName}#end);
			logger.debug("${dataFetcher.name}: {} found rows", (ret==null) ? 0 : ret.size());

			return ret;
#else
			${dataFetcher.field.type.classSimpleName} ret = null;
			try {
				ret = ${dataFetchersDelegate.camelCaseName}.${dataFetcher.camelCaseName}(dataFetchingEnvironment#if($dataFetcher.sourceName), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.camelCaseName}#end);
			} catch (NoSuchElementException e) {
				// There was no items in the Optional
			}

			if (ret != null)
				logger.debug("human: 1 result found");
			else
				logger.debug("human: no result found");

			return ret;
#end
		};
	}

#end
#end
}
