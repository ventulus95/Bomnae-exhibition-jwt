package com.ventulus95.ouathjwt.security;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    private Map<String,Object> response;
    private Map<String, Object> profile;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        response = (Map<String, Object>)attributes.get("kakao_account");
        profile = (Map<String, Object>) response.get("profile");

    }

    public String getId() {
        return  attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) profile.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) response.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) profile.get("profile_image_url");
    }
}