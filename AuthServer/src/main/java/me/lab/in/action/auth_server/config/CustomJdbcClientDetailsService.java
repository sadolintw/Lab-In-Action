package me.lab.in.action.auth_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.lab.in.action.auth_server.model.OauthClient;
import me.lab.in.action.auth_server.model.OauthClientGrantType;
import me.lab.in.action.auth_server.model.Resource;
import me.lab.in.action.auth_server.model.Scop;

import me.lab.in.action.auth_server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomJdbcClientDetailsService implements ClientDetailsService, ClientRegistrationService {
    @Autowired
    private OauthClientRepository oauthClientRepository;
    @Autowired
    private OauthClientResourceRepository oauthClientResourceRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ScopRepository scopRepository;
    @Autowired
    private OauthClientGrantTypeRepository oauthClientGrantTypeRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.debug(">> CustomJdbcClientDetailsService.loadClientByClientId clientId={}", clientId);
        OauthClient oauthClient = oauthClientRepository.findById(clientId).get();
        // 取出 Resource
        List<String> resourceidList = oauthClientResourceRepository.findResourceidByClientid(oauthClient.getClientid());
        // 雖然有對應，但避免對應錯還是去 Resource 表格取出來
        List<Resource> resourceList = resourceRepository.findByResourceidIn(resourceidList);
        List<String> resourceids = resourceList.stream().map(Resource::getResourceid).collect(Collectors.toList());
        // 取出 Scop
        List<Scop> scopList = scopRepository.findByResourceidIn(resourceids);
        List<String> scops = scopList.stream().map(Scop::getScopcode).collect(Collectors.toList());
        // 取出授權類型
        List<OauthClientGrantType> oauthClientGrantTypeList = oauthClientGrantTypeRepository.findByClientid(oauthClient.getClientid());
        List<String> grantTypes = oauthClientGrantTypeList.stream().map(OauthClientGrantType::getGranttype).collect(Collectors.toList());
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(oauthClient.getClientid());
        details.setClientSecret(oauthClient.getClientSecret());
        details.setResourceIds(resourceids);
        details.setScope(scops);
        details.setAuthorizedGrantTypes(grantTypes);
        details.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValidity());
        details.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValidity());

        // 這不用設定，在帳號驗證那邊會給值 CustomUserDetailsService.loadUserByUsername
        //details.setAuthorities(authorities);
        // 這邊會給這個 client 所有可申請的範圍，在Token轉換的時候把不適合該角色權限踢掉
        details.setAutoApproveScopes(scops);
        if (StringUtils.hasText(oauthClient.getWebServerRedirectUri())) {
            details.setRegisteredRedirectUri(Arrays.stream(oauthClient.getWebServerRedirectUri().split(",")).collect(Collectors.toSet()));
        }
        if (StringUtils.hasText(oauthClient.getAdditionalInformation())) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> additionalInformation = mapper.readValue(oauthClient.getAdditionalInformation(), Map.class);
                //System.out.println("has set additionalInformation="+additionalInformation);
                details.setAdditionalInformation(additionalInformation);
            } catch (Exception e) {
                log.error("AdditionalInformation to Map error", e);
            }
        }

        return details;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        log.debug(">> CustomJdbcClientDetailsService.addClientDetails clientDetails={}", clientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        log.debug(">> CustomJdbcClientDetailsService.updateClientDetails clientDetails={}", clientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        log.debug(">> CustomJdbcClientDetailsService.updateClientSecret clientId={}, secret={}", clientId, secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        log.debug(">> CustomJdbcClientDetailsService.removeClientDetails clientId={}", clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        log.debug(">> CustomJdbcClientDetailsService.listClientDetails");
        return null;
    }
}
