package org.onekr.trial.datarest.repository;

import org.onekr.trial.datarest.entity.User;
import org.onekr.trial.datarest.entity.projection.UserAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author billy-work
 */

@CrossOrigin
//@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(path = "users", excerptProjection = UserAll.class)
public
interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.CrudRepository
     */
    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends User> S save(S s);

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
     */
    @Override
    @RestResource(exported = false)
    void deleteById(String aLong);

}
