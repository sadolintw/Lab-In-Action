package me.lab.in.action.auth_server.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "oauth_resource", schema = "web")
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class OauthResource {
    @Id
    @Column(name = "resource_id")
    private String resourceId;
    
    private String label;
    
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
    
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
