package webservice.restapi.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    @NotBlank
    private String title;
    @Column
    @NotBlank
    private String content;
    @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp created;
    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Timestamp modified;
    @Column(name = "isArchived", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isArchived=false;
    public Note(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Integer getId() {
        return id;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }
}
