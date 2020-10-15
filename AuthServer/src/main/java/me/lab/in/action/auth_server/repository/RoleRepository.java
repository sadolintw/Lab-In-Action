package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByCode(String code);

    List<Role> findByRoleidIn(List<String> roleidList);

    List<Role> findByCodeIn(List<String> rolecodeList);
}
