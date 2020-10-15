package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OauthTokenRepository extends JpaRepository<OauthToken, String> {

    OauthToken findByTokenId(String tokenId);

    OauthToken findByRefreshId(String refreshId);
}
