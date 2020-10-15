package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.Scop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScopRepository extends JpaRepository<Scop, String> {

    List<Scop> findByResourceidIn(List<String> resourceidList);

    List<Scop> findByResourceidInAndScopidIn(List<String> resourceidList, List<String> scopidList);
}
