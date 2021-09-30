package org.onekr.trial.datarest.repository;

import org.onekr.trial.datarest.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * @author billy-work
 */
@CrossOrigin
@RepositoryRestResource()
public interface PostRepository extends JpaRepository<Post, String>, JpaSpecificationExecutor<Post> {

//    @RestResource(path = "by-user-id")
//    Page<Post> findByauthorid(String authorid, Pageable pageable);

    @RestResource(path = "by-user-id")
    List<Post> findByauthorid(String authorid);

}
