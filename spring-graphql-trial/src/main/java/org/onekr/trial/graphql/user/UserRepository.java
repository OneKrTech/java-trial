package org.onekr.trial.graphql.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.Optional;

@GraphQlRepository
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {

    @MutationMapping(name = "userSave")
    @Override
    User save(@Argument User user);

    @QueryMapping(name = "userFindById")
    @Override
    Optional<User> findById(String id);

}
