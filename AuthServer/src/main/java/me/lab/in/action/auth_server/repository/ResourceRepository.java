package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {

    List<Resource> findByResourceidIn(List<String> resourceidList);
}
