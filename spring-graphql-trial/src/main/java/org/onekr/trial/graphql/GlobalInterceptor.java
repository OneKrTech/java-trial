package org.onekr.trial.graphql;

import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.web.WebGraphQlHandler;
import org.springframework.graphql.web.WebInput;
import org.springframework.graphql.web.WebInterceptor;
import org.springframework.graphql.web.WebOutput;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GlobalInterceptor implements WebInterceptor {

    @Override
    public Mono<WebOutput> intercept(WebInput webInput, WebGraphQlHandler next) {
        webInput.configureExecutionInput((executionInput, builder) -> {
            Map<String, Object> map = new HashMap<>();
            return builder.extensions(map).build();
        });
        return next.handle(webInput);
    }

}