package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthClientResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OauthClientResourceRepository extends JpaRepository<OauthClientResource, String> {

    // 取出有有對應的 resourceid
    @Query("select cr.resourceId from OauthClientResource cr where cr.clientId = :clientId")
    List<String> findResourceIdByClientId(@Param("clientId") String clientId);
}
