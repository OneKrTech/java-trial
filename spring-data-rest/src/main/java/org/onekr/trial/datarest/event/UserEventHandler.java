package org.onekr.trial.datarest.event;

import org.onekr.trial.datarest.entity.User;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * @author billy-work
 */
@Component
public class UserEventHandler extends AbstractRepositoryEventListener<User> {

    @Override
    public void onBeforeSave(User entity) {
        // … you can now deal with Person in a type-safe way
        var a = entity;
    }

    @Override
    public void onBeforeCreate(User entity) {
        // … you can now deal with Profile in a type-safe way
        var a = entity;
    }

}
