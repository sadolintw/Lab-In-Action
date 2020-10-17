package me.lab.in.action.auth_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "oauth_role_scope", schema = "dbo")
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class OauthRoleScope {
    @Id
    @Column(name = "id")
    private String id;

    private String rid;
    
    @Column(name = "scope_id")
    private String scopeId;

    @CreatedDate
    @Column(name = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    
    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;
    
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
