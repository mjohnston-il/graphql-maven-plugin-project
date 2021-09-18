package org.allGraphQLCases;

import org.allGraphQLCases.client.util.MyQueryTypeExecutorAllGraphQLCases;
import org.allGraphQLCases.client2.util.MyQueryTypeExecutorAllGraphQLCases2;
import org.allGraphQLCases.demo.impl.PartialDirectQueries;
import org.forum.client.util.QueryTypeExecutorForum;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;

import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.graphqlrepository.EnableGraphQLRepositories;

@Configuration
@SpringBootApplication
@ComponentScan(basePackageClasses = { GraphQLConfiguration.class, MyQueryTypeExecutorAllGraphQLCases.class,
		MyQueryTypeExecutorAllGraphQLCases2.class, QueryTypeExecutorForum.class, PartialDirectQueries.class })
@PropertySource("classpath:/application.properties")
@EnableGraphQLRepositories({ "org.allGraphQLCases.demo.impl", "org.allGraphQLCases.subscription.graphqlrepository",
		"org.allGraphQLCases.two_graphql_servers" })
@SuppressWarnings("deprecation")
public class SpringTestConfig {

	@Bean
	ServerOAuth2AuthorizedClientExchangeFilterFunction serverOAuth2AuthorizedClientExchangeFilterFunctionAllGraphQLCases(
			ReactiveClientRegistrationRepository clientRegistrations) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrations, new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
		oauth.setDefaultClientRegistrationId("provider_test");
		return oauth;
	}

	@Bean
	ServerOAuth2AuthorizedClientExchangeFilterFunction serverOAuth2AuthorizedClientExchangeFilterFunctionForum(
			ReactiveClientRegistrationRepository clientRegistrations) {
		return null;
	}
}
