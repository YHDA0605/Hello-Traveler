package com.teamHT.helloTraveler.Svc;

import java.util.HashMap;

public interface IKakaoService {

	String getAccessToken(String authorize_code);

	HashMap<String, Object> getUserInfo(String access_Token);

}