package me.lab.in.action.auth_server.repository;

import me.lab.in.action.auth_server.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, String> {

    List<AccountRole> findByAccountId(String accountId);
}
