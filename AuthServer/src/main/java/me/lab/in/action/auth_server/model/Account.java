package me.lab.in.action.auth_server.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by samchu on 2017/2/14.
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class Account {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @NotNull
    @Size(min = 8, max = 255, message = "Username have to be grater than 8 characters")
    @Column(unique = true)
    private String username;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, max = 255, message = "Password have to be grater than 8 characters")
    private String password;

    @NotNull
    private boolean enabled = true;

    @NotNull
    @Column(name = "credentials_expired")
    private boolean credentialsExpired = false;

    @NotNull
    private boolean expired = false;

    @NotNull
    private boolean locked = false;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "AccountRole", joinColumns = @JoinColumn(name = "accountid", referencedColumnName = "accountid"),
//            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
//    private List<Role> roles;

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
