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
@Table(name = "oauth_token", schema = "web")
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class OauthToken {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "token_id")
    private String tokenId;
    
    @Column(name = "refresh_id")
    private String refreshId;
    
    @Column(name = "client_id")
    private String clientId;
    
    @Column(name = "grant_type")
    private String grantType;
    
    @Column(name = "resource_ids")
    private String resourceIds;
    
    @Column(name = "scopes")
    private String scopes;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "redirect_uri")
    private String redirectUri;
    
    @Lob
    @Column(name = "access_token")
    private String accessToken;
    
    @Lob
    @Column(name = "refreshToken")
    private String refreshToken;
    
    @Column(name = "refreshed")
    private Boolean refreshed;
    
    @Column(name = "locked")
    private Boolean locked;
    
    @Lob
    @Column(name = "authentication")
    private byte[] authentication;
    
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
