package org.onekr.trial.datarest.repository;

import com.github.javafaker.Faker;
import org.onekr.trial.datarest.entity.Post;
import org.onekr.trial.datarest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author billy-work
 */
@Component
@Order(999)
public class RepositoriesInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> userList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var faker = new Faker(Locale.CHINESE);

            userList.add(new User(String.valueOf(i), faker.internet().emailAddress(), faker.name().fullName(), faker.phoneNumber().cellPhone(), false, new Date()));

            for (int j = 0; j < 4; j++) {
                postList.add(new Post(String.valueOf(j), new Date(), null, faker.book().title(), faker.space().company(), true, String.valueOf(i), false));
            }

        }

        userRepository.saveAll(userList);
        postRepository.saveAll(postList);

    }

}
