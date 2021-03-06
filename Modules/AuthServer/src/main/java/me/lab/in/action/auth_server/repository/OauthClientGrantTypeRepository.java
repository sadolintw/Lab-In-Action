package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthClientGrantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OauthClientGrantTypeRepository extends JpaRepository<OauthClientGrantType, String> {
    List<OauthClientGrantType> findByClientId(String clientid);
}
