/**
 * 
 */
package graphql.mavenplugin_notscannedbyspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.graphql_java_generator.plugin.PluginMode;

/**
 * The Spring configuration used for JUnit tests
 * 
 * @author etienne-sf
 */
@Configuration
@ComponentScan(basePackages = "com.graphql_java_generator", excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".*\\.GenerateRelaySchema.*"))
public class HelloWorld_Client_SpringConfiguration extends AbstractSpringConfiguration {

	public HelloWorld_Client_SpringConfiguration() {
		super("helloworld.graphqls", PluginMode.client);
	}
}
