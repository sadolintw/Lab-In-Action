package me.lab.in.action.auth_server.service;


import me.lab.in.action.auth_server.model.OauthRoleScope;
import me.lab.in.action.auth_server.model.OauthScope;
import me.lab.in.action.auth_server.model.Role;
import me.lab.in.action.auth_server.repository.OauthRoleScopeRepository;
import me.lab.in.action.auth_server.repository.OauthScopeRepository;
import me.lab.in.action.auth_server.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ScopeService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OauthRoleScopeRepository roleScopeRepository;
    @Autowired
    private OauthScopeRepository scopeRepository;

    public Set<String> generationByRole(List<String> resourceIdList, List<String> roleCodeList) {
        // 先用角色代碼取出角色物件
        List<Role> roleList = roleRepository.findByCodeIn(roleCodeList);
        // 轉換成角色ID
        List<String> roleIdList = roleList.stream().map(Role::getRoleId).collect(Collectors.toList());
        // 用角色ID 找出對應的 Scope 對應表
        List<OauthRoleScope> roleScopeList = roleScopeRepository.findByRidIn(roleIdList);
        // 取出 Scope ID
        List<String> scopidList = roleScopeList.stream().map(OauthRoleScope::getScopeId).collect(Collectors.toList());
        // 去 Scope 表格找出可用 resourceId 跟 scopeId
        List<OauthScope> scopeList = scopeRepository.findByResourceIdInAndScopeIdIn(resourceIdList, scopidList);
        // 轉成 scopeCode
        Set<String> scopeSet = scopeList.stream().map(OauthScope::getScopeCode).collect(Collectors.toSet());
        return scopeSet;
    }
}
