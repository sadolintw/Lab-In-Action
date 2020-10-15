package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthRoleScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OauthRoleScopeRepository extends JpaRepository<OauthRoleScope, String> {

    List<OauthRoleScope> findByRidIn(List<String> ridList);
}
