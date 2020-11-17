package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.OauthResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OauthResourceRepository extends JpaRepository<OauthResource, String> {

    List<OauthResource> findByResourceIdIn(List<String> resourceidList);
}
