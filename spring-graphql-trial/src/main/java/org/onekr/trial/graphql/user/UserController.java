package org.onekr.trial.graphql.user;

import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class UserController {

    public UserController(BatchLoaderRegistry registry) {

        registry.forTypePair(String.class, AuthModel.class).registerBatchLoader((authorIds, env) -> {
            // load authors
            var ass = new AuthModel[authorIds.size()];

            for (int i = 0; i < authorIds.size(); i++) {
                var id = authorIds.get(i);
                var auth = new AuthModel();
                auth.setOpenId(id + "eee");
                ass[i] = auth;
            }

            return Flux.just(ass);
        });
    }

    @QueryMapping
    public List<UserModel> users() throws Exception {

        var result = new ArrayList<UserModel>();
        for (int i = 0; i < 5; i++) {
            var model = new UserModel();
            model.setId("asdf" + i);
            result.add(model);
        }

        return result;

    }

    @QueryMapping
    public UserModel user(@Argument String id, DataFetchingEnvironment environment, GraphQLContext context) throws Exception {
        var model = new UserModel();

        if (Strings.isNotBlank(id)) {
            if ("error".equals(id)) {
                throw new Exception("abc");
            }
            model.setId(id);
        } else {
            model.setId("kong");
        }

        return model;
    }

//    @SchemaMapping(typeName = "UserModel", field = "auth")
//    public AuthModel auth(UserModel userModel, DataFetchingEnvironment environment, GraphQLContext context) {
//
//        var auth = new AuthModel();
//        auth.setOpenId(userModel.getId() + "eee");
//
//        return auth;
//
//    }

    @SchemaMapping(typeName = "UserModel", field = "auth")
    public CompletableFuture<AuthModel> auth(UserModel userModel, DataLoader<String, AuthModel> loader, DataFetchingEnvironment environment, GraphQLContext context) {

        return loader.load(userModel.getId());

    }

    @MutationMapping
    public UserModel update(@Argument UpdateUserModel model) {

        var userModel = new UserModel();
        userModel.setId(model.getId());
        userModel.setName(model.getName());

        return userModel;
    }

}
