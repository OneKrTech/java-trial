package org.onekr.trial.graphql;

import org.onekr.trial.graphql.user.User;
import org.onekr.trial.graphql.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RepositoriesInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> userList = Arrays.asList(
                new User("1", "zhangbin1"),
                new User("2", "zhangbin2"),
                new User("3", "zhangbin3"));
        userRepository.saveAll(userList);

    }

}
