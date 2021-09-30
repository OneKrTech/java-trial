package org.onekr.trial.graphql;

import graphql.GraphQLError;
import graphql.execution.AbortExecutionException;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GlobalExceptionHandler implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        log.error("全局异常", exception);

        var list = new ArrayList<GraphQLError>();

        var err = new AbortExecutionException();
        list.add(err);

        return Mono.just(list);
    }
}
