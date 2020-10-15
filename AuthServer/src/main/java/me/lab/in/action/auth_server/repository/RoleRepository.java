package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by samchu on 2017/2/9.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByCode(String code);

    List<Role> findByRoleIdIn(List<String> roleIdList);

    List<Role> findByCodeIn(List<String> roleCodeList);
}
