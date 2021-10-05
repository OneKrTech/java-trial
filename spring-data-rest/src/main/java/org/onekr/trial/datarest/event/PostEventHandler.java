package org.onekr.trial.datarest.event;

import org.onekr.trial.datarest.entity.Post;
import org.onekr.trial.datarest.entity.User;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * @author billy-work
 */
@Component
public class PostEventHandler extends AbstractRepositoryEventListener<Post> {

    @Override
    public void onBeforeSave(Post entity) {
        // … you can now deal with Person in a type-safe way
        var a = entity;
    }

    @Override
    public void onBeforeCreate(Post entity) {
        // … you can now deal with Profile in a type-safe way
        var a = entity;
    }
}
