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
@Table(name = "oauth_client_details", schema = "dbo")
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class OauthClient {
    @Id
    @Column(name = "client_id")
    private String clientId;
    
    @Column(name = "client_secret")
    private String clientSecret;
    
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;
    
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;
    
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    
    @Column(name = "additional_information")
    private String additionalInformation;

    private String autoapprove;

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
