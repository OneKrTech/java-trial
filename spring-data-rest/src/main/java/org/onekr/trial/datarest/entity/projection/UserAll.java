package org.onekr.trial.datarest.entity.projection;

import org.onekr.trial.datarest.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "all", types = {User.class})
public interface UserAll {

    String getId();

    /**
     *
     */
    String getEmail();

    /**
     *
     */
    String getName();

    String getPhoneno();

    Date getCreatedat();

}
