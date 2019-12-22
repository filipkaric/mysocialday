package mysocialinfo.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {
	
	public String getPosts() {
		URL url;
		String staJeOvo = "";
		try {
			url = new URL("https://graph.facebook.com/v5.0/me?fields=posts");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			con.setRequestProperty("Content-Type", "application/json");
			String token =  GetFacebookToken();
			con.setRequestProperty("Authorization", "Bearer " + token);

			con.setUseCaches(false); 
			con.setDoOutput(true);
			// Send request
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.close();
			// Get Response
			InputStream is = con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			staJeOvo = response.toString();
			System.out.println(staJeOvo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staJeOvo;
	}
	


	public String GetFacebookToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
			String token = details.getTokenValue();
			return token;
		} else {
			return null;
		}
	}
}
