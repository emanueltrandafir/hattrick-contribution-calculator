package ro.etrcpo.htcalculator.auth;

import org.springframework.stereotype.Service;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

@Service
public class AuthService {

	private static final String CONSUMER_KEY = "";
	private static final String CONSUMER_SECRET = "";

	public String requestToken() throws Exception {

		OAuthConsumer consumer = new DefaultOAuthConsumer( CONSUMER_KEY, CONSUMER_SECRET );

		OAuthProvider provider = new DefaultOAuthProvider(
				"https://chpp.hattrick.org/oauth/request_token.ashx",
				"https://chpp.hattrick.org/oauth/access_token.ashx", 
				"https://chpp.hattrick.org/oauth/authorize.aspx");

		System.out.println("Fetching request token...");

		String authUrl = provider.retrieveRequestToken(consumer, "http://www.google.com");

		System.out.println("Request token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		return authUrl;

	}

}
