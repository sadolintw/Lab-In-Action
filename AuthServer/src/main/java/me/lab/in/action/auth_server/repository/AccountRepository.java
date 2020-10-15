package me.lab.in.action.auth_server.repository;


import me.lab.in.action.auth_server.model.Account;
import me.lab.in.action.auth_server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findByUsername(@Param("username") String username);

    @Query("SELECT r FROM Account a, AccountRole ar, Role r WHERE a.username = :username and a.accountid = ar.accountid and ar.roleid = r.roleid")
    List<Role> findRoleListByUsername(@Param("username") String username);
}
