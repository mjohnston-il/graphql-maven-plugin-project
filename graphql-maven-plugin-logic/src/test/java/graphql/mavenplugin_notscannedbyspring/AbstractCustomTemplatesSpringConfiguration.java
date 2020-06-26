package graphql.mavenplugin_notscannedbyspring;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;
import org.springframework.context.annotation.Bean;

import com.graphql_java_generator.plugin.CodeTemplate;
import com.graphql_java_generator.plugin.CodeTemplateScope;
import com.graphql_java_generator.plugin.CustomScalarDefinition;
import com.graphql_java_generator.plugin.GraphQLConfiguration;
import com.graphql_java_generator.plugin.PluginMode;
import com.graphql_java_generator.plugin.test.helper.MavenTestHelper;
import com.graphql_java_generator.plugin.test.helper.GraphQLConfigurationTestHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * Base Spring configuration for Custom template test Extends {@link AbstractSpringConfiguration} to configure
 * {@link GraphQLConfigurationTestHelper} with the customized templates
 * Customized templates are generated previous to test execution by copying default templates at src/main/resources/templates to  target/test-classes/templates_personalization
 * 
 * @author ggomez
 *
 */
@Slf4j
public abstract class AbstractCustomTemplatesSpringConfiguration extends AbstractSpringConfiguration {
	
	

	/**
	 * Pattern for default templates
	 */

	protected static final Pattern templatePattern = Pattern.compile("templates\\/([a-zA-Z_]*)\\.vm\\.java");
	protected static List<CustomScalarDefinition> customScalars;

	static {
		customScalars = new ArrayList<>();
		customScalars.add(new CustomScalarDefinition("Date", "java.util.Date", null,
				"com.graphql_java_generator.customscalars.GraphQLScalarTypeDate.Date", null));
		customScalars
				.add(new CustomScalarDefinition("Long", "java.lang.Long", null, "graphql.Scalars.GraphQLLong", null));
		customScalars.add(new CustomScalarDefinition("else", "java.lang.String", null,
				"com.graphql_java_generator.customscalars.GraphQLScalarTypeString.String", null));
		customScalars.add(new CustomScalarDefinition("NonNegativeInt", "java.lang.Integer", null,
				"graphql.scalars.ExtendedScalars.NonNegativeInt", null));
	}

	protected CodeTemplateScope codeTemplateScope;

	protected AbstractCustomTemplatesSpringConfiguration(String schemaFilePattern, PluginMode mode,
			List<CustomScalarDefinition> customScalars) {
		super(schemaFilePattern, mode, customScalars);
		this.codeTemplateScope = mode == PluginMode.client ? CodeTemplateScope.CLIENT : CodeTemplateScope.SERVER;
		copyAndCustomizeTemplates();
	}

	/**
	 * Helper method to build customize templates map for given scope The customized templates are located at
	 * src/test/resources/templates_personalization Also {@link CodeTemplateScope#COMMON} templates are added
	 * 
	 * @param scope
	 * @return
	 */
	protected Map<String, String> buildTempaltes(CodeTemplateScope scope) {
		// Setup custom templates for client and common scope
		return Arrays.stream(CodeTemplate.values()).filter(
				codeTempalte -> codeTempalte.getScope() == scope || codeTempalte.getScope() == CodeTemplateScope.COMMON)
				.map(codeTemplate -> {
					Matcher matcher = templatePattern.matcher(codeTemplate.getDefaultValue());
					if (matcher.matches()) {
						return new Pair<CodeTemplate, String>(codeTemplate,
								String.format("templates_personalization/%s.vm.java", matcher.group(1)));
					} else {
						throw new RuntimeException(String.format("Template does not match expected pattenr: %s - %s",
								codeTemplate, codeTemplate.getDefaultValue()));
					}

				}).collect(Collectors.toMap(pair -> pair.getValue0().name(), pair -> pair.getValue1()));
	}

	/**
	 * Overrirdes {@link AbstractSpringConfiguratio#pluginConfigurationTestHelper} by configured custom templates
	 * localted in src/test/resources/templates_personalization
	 */
	@Override
	@Bean
	GraphQLConfiguration graphQLConfigurationTestHelper(MavenTestHelper mavenTestHelper) {
		GraphQLConfigurationTestHelper pluginConfiguration = (GraphQLConfigurationTestHelper) super.graphQLConfigurationTestHelper(
				mavenTestHelper);
		pluginConfiguration.templates = buildTempaltes(this.codeTemplateScope);
		return pluginConfiguration;
	}
	
	/**
	 * Generates custom templates from origin templates for the test
	 * Copies from src/main/resources/templates to target/test-classes/templates_personalization and changes "Generated by the default template from graphql-java-generator", to be changed by "This template is custom /" 
	 */
	protected void copyAndCustomizeTemplates() {
		List<String> templatesInScope = getTemplesInScope();

		Path targetPathDirectory = Paths.get("target", "test-classes", "templates_personalization");
		try  {
			if(Files.exists(targetPathDirectory)) {
				Files.walk(targetPathDirectory)
			      .sorted(Comparator.reverseOrder())
			      .filter(path -> !path.equals(targetPathDirectory))
			      .forEach(path -> {
			    	  try {
				    	  Files.delete(path); 			    		  
			    	  } catch(IOException e) {
			    		  throw new RuntimeException("Unexpected error deleting previous customized template " + path.getFileName().toString(), e);
			    	  }
			      });
			} else {
				Files.createDirectories(targetPathDirectory);
			}
		} catch(IOException e) {
			throw new RuntimeException("Unexpected error preparing custom templates directory", e);
		}
		
		try (Stream<Path> stream = Files.walk(Paths.get("src", "main", "resources", "templates"), 1)) {
			stream
				.filter(path -> !Files.isDirectory(path))
				.filter(path -> templatesInScope.contains(path.getFileName().toString()))
				.forEach(sourcePath -> {
					try {
						Path targetPath = targetPathDirectory.resolve(sourcePath.getFileName());
						
						log.info("Custom templates test: Copying {} into {} with contenet replacement", 
								sourcePath.toString(), targetPath.toString());
						
						String content = new String(Files.readAllBytes(sourcePath), StandardCharsets.UTF_8);
						content = content.replace("/** Generated by the default template from graphql-java-generator */", 
								"/** This template is custom **/");
						Files.write(targetPath, content.getBytes(StandardCharsets.UTF_8));						
					} catch( IOException e) {
						throw new RuntimeException(e);
					}
				});
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Resolves the name the filename of the tempaltes for the test scope
	 * @return
	 */
	protected List<String> getTemplesInScope() {
		return Arrays.stream(CodeTemplate.values())
			.filter(template -> template.getScope() == CodeTemplateScope.COMMON || template.getScope() == this.codeTemplateScope)
			.map(template -> Paths.get(template.getDefaultValue()))
			.map(templatePath -> templatePath.getFileName().toString())
			.collect(Collectors.toList());
	}
}
