package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OauthClientRepository extends JpaRepository<OauthClient, String> {
}
