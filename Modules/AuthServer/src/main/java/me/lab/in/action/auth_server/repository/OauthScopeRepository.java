package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OauthScopeRepository extends JpaRepository<OauthScope, String> {

    List<OauthScope> findByResourceIdIn(List<String> resourceIdList);

    List<OauthScope> findByResourceIdInAndScopeIdIn(List<String> resourceIdList, List<String> scopeIdList);
}
