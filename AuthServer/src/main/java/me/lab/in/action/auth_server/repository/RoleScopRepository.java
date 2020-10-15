package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.RoleScop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleScopRepository extends JpaRepository<RoleScop, String> {

    List<RoleScop> findByRoleidIn(List<String> roleidList);
}
