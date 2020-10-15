package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.Oauthtoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthtokenRepository extends JpaRepository<Oauthtoken, String> {

    Oauthtoken findByTokenid(String tokenid);

    Oauthtoken findByRefreshid(String refreshid);
}
