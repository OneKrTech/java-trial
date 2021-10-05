package org.onekr.trial.datarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author billy-work
 */
@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class User implements Serializable {

    private static final long serialVersionUID = -55031807955201326L;

    public User(String id, String email, String name, String phoneno, Boolean deleted,Date createdat) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneno = phoneno;
        this.deleted = deleted;
        this.createdat = createdat;
    }

    /**
     *
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", insertable = true, updatable = false)
    private String id;

    /**
     *
     */
    @Column(name = "email", insertable = true, updatable = true)
    private String email;

    /**
     *
     */
    @Column(name = "name", insertable = true, updatable = true)
    private String name;

    /**
     *
     */
//    @JsonIgnore
    @Column(name = "phoneNo", insertable = true, updatable = true)
    private String phoneno;

    @Column
    private Boolean deleted;

    @Column
    @CreationTimestamp
    private Date createdat;
}
