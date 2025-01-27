package com.inspire12.practice.api.module.user.infrastructure.adapter;

import com.inspire12.practice.api.module.user.domain.SessionUser;
import com.inspire12.practice.api.module.user.infrastructure.OAuthAttributes;
import com.inspire12.practice.api.module.user.infrastructure.entity.UsersEntity;
import com.inspire12.practice.api.module.user.infrastructure.jparepository.UserJpaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserJpaRepository userJpaRepository;
    private final HttpSession httpSession;

    public CustomOAuth2UserService(UserJpaRepository userJpaRepository, HttpSession httpSession) {
        this.userJpaRepository = userJpaRepository;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        UsersEntity users = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(users));
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(users.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private UsersEntity saveOrUpdate(OAuthAttributes attributes) {
        UsersEntity users = userJpaRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userJpaRepository.save(users);
    }
}
