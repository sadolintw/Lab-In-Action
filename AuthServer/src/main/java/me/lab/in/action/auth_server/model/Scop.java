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
@Table(name = "scop")
@EntityListeners(AuditingEntityListener.class) //加這行 CreatedBy 才會生效
public class Scop {
    @Id
    private String scopid;
    private String resourceid;
    private String scopcode;
    private String label;

    @CreatedDate
    @Column(name = "createddate")
    private Date createddate;
    @CreatedBy
    @Column(name = "createdby")
    private String createdby;
    @LastModifiedDate
    @Column(name = "lastmodifieddate")
    private Date lastmodifieddate;
    @LastModifiedBy
    @Column(name = "lastmodifiedby")
    private String lastmodifiedby;
}
