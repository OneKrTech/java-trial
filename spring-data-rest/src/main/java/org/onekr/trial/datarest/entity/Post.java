package org.onekr.trial.datarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author billy-work
 */
@Entity
@Table(name = "Post")
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Post implements Serializable {

    private static final long serialVersionUID = 640288536866304318L;

    public Post(String id, Date createdat, Date updatedat, String title, String content, Boolean published, String authorid, Boolean deleted) {
        this.id = id;
        this.createdat = createdat;
        this.updatedat = updatedat;
        this.title = title;
        this.content = content;
        this.published = published;
        this.authorid = authorid;
        this.deleted = deleted;
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
    @CreationTimestamp
    @Column(name = "createdAt",  insertable = true, updatable = true)
    private Date createdat;
    
    /**
     * 
     */
    @UpdateTimestamp
    @Column(name = "updatedAt", insertable = true, updatable = true)
    private Date updatedat;
    
    /**
     * 
     */
    @Column(name = "title", insertable = true, updatable = true)
    private String title;
    
    /**
     * 
     */
    @Column(name = "content", insertable = true, updatable = true)
    private String content;
    
    /**
     * 
     */
    @Column(name = "published", insertable = true, updatable = true)
    private Boolean published;
    
    /**
     * 
     */
    @Column(name = "authorId", insertable = true, updatable = true)
    private String authorid;

    @Column
    private Boolean deleted;
}
