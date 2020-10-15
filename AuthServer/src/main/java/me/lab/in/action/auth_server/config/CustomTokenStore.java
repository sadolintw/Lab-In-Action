package me.lab.in.action.auth_server.config;

import lombok.extern.slf4j.Slf4j;
import me.lab.in.action.auth_server.model.OauthToken;
import me.lab.in.action.auth_server.repository.OauthTokenRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Component
public class CustomTokenStore implements TokenStore {
    @Autowired
    private OauthTokenRepository oauthtokenRepository;

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        log.debug(">> CustomTokenStore.readAuthentication token={}", token);
        return null;
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        log.debug(">> CustomTokenStore.readAuthentication token={}", token);
        return null;
    }

    /**
     * 儲存 Token ，Auth 跟 Refresh 都會使用
     *
     * @param token
     * @param authentication
     */
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        log.debug(">> CustomTokenStore.storeAccessToken token={}, authentication={}", token, authentication);
        OauthToken oauthtoken = new OauthToken();
        String serid = RandomStringUtils.randomAlphanumeric(32);
        oauthtoken.setId(serid);
        oauthtoken.setTokenId(DigestUtils.md5Hex(token.getValue()));
        oauthtoken.setRefreshId(token.getRefreshToken() == null ? null : DigestUtils.md5Hex(token.getRefreshToken().getValue()));
        oauthtoken.setClientId(authentication.getOAuth2Request().getClientId());
        oauthtoken.setGrantType(authentication.getOAuth2Request().getGrantType());
        oauthtoken.setResourceIds(authentication.getOAuth2Request().getResourceIds().toString());
        oauthtoken.setScopes(authentication.getOAuth2Request().getScope().toString());
        oauthtoken.setUsername(authentication.isClientOnly() ? null : authentication.getName());
        oauthtoken.setRedirectUri(authentication.getOAuth2Request().getRedirectUri());
        oauthtoken.setAccessToken(token.getValue());
        oauthtoken.setRefreshToken(token.getRefreshToken() == null ? null : token.getRefreshToken().getValue());
        oauthtoken.setRefreshed(Boolean.FALSE);
        oauthtoken.setLocked(Boolean.FALSE);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(authentication);
            oos.flush();
            oauthtoken.setAuthentication(baos.toByteArray());
        } catch (IOException e) {
            log.error("OAuth2Authentication serialization error", e);
        }
        oauthtokenRepository.save(oauthtoken);
        log.debug("<< CustomTokenStore.storeAccessToken");
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        log.debug(">> CustomTokenStore.readAccessToken tokenValue={}", tokenValue);
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        log.debug(">> CustomTokenStore.removeAccessToken token={}", token);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        log.debug(">> CustomTokenStore.storeRefreshToken refreshToken={}, authentication={}", refreshToken, authentication);
    }

    /**
     * 讀取 RefreshToken 這段只有在 Refresh 的時候被呼叫到，並且做限制 RefreshToken 使用過後就不能在取得新的 Token
     *
     * @param tokenValue
     * @return
     */
    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        log.debug(">> CustomTokenStore.readRefreshToken tokenValue={}", tokenValue);
        OauthToken oauthtoken = oauthtokenRepository.findByRefreshId(DigestUtils.md5Hex(tokenValue));
        if (oauthtoken.getRefreshed() == Boolean.TRUE) {
            throw new BadCredentialsException("RefreshToken Is Refreshed.");
        }
        OAuth2RefreshToken oAuth2RefreshToken = new DefaultOAuth2RefreshToken(oauthtoken.getRefreshToken());
        log.debug("<< CustomTokenStore.readRefreshToken OAuth2RefreshToken={}", oAuth2RefreshToken);
        return oAuth2RefreshToken;
    }

    /**
     * 讀取當初的授權資料才能再核發 Token
     *
     * @param token
     * @return
     */
    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        log.debug(">> CustomTokenStore.readAuthenticationForRefreshToken token={}", token);
        OAuth2Authentication oAuth2Authentication = null;
        OauthToken oauthtoken = oauthtokenRepository.findByRefreshId(DigestUtils.md5Hex(token.getValue()));
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(oauthtoken.getAuthentication()));
            oAuth2Authentication = (OAuth2Authentication) ois.readObject();
        } catch (Exception e) {
            log.error("OAuth2Authentication Deserialization error", e);
        }
        log.debug("<< CustomTokenStore.readAuthenticationForRefreshToken oAuth2Authentication={}", oAuth2Authentication);
        return oAuth2Authentication;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        log.debug(">> CustomTokenStore.removeRefreshToken token={}", token);
    }

    /**
     * 當授權成功後回頭把 refreshToken 標記為已更新核發過
     *
     * @param refreshToken
     */
    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        log.debug(">> CustomTokenStore.removeAccessTokenUsingRefreshToken refreshToken={}", refreshToken);
        OauthToken oauthtoken = oauthtokenRepository.findByRefreshId(DigestUtils.md5Hex(refreshToken.getValue()));
        oauthtoken.setRefreshed(Boolean.TRUE);
        oauthtokenRepository.save(oauthtoken);
        log.debug("<< CustomTokenStore.removeAccessTokenUsingRefreshToken");
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        log.debug(">> CustomTokenStore.getAccessToken authentication={}", authentication);
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        log.debug(">> CustomTokenStore.findTokensByClientIdAndUserName clientId={}, userName={}", clientId, userName);
        log.debug("<< CustomTokenStore.findTokensByClientIdAndUserName Collection={}", "[]");
        return Collections.emptySet();
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        log.debug(">> CustomTokenStore.findTokensByClientId clientId={}", clientId);
        return null;
    }
}