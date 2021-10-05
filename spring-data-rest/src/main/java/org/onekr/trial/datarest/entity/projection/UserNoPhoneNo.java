package org.onekr.trial.datarest.entity.projection;

import org.onekr.trial.datarest.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "noPhoneNo", types = {User.class })
public interface UserNoPhoneNo {

    String getId();

    /**
     *
     */
    String getEmail();

    /**
     *
     */
    String getName();

    Date getCreatedat();

}
