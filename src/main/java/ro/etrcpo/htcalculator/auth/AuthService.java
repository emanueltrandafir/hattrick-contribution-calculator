package ro.etrcpo.htcalculator.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.Data;
import oauth.signpost.AbstractOAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthException;

@Service
public class AuthService {

	private static final String CALLBACK_URL = "http://localhost:8084";
	private static final String CONSUMER_KEY = "DvLDLfaJgm85dSjrcLPiB4";
	private static final String CONSUMER_SECRET = "fUProVQ7wtBuyclozgxjF9EEAJ2IVihmeIpGOpI86PJ";

	Map<String, OauthConsumer> waitingClients= new HashMap<>();
	
	public String fetchData( String token, String tokenSecret, String urlPath ) throws OAuthException, IOException {

		DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		consumer.setTokenWithSecret(token, tokenSecret);
		
		URL url = new URL( urlPath );
		HttpURLConnection request = (HttpURLConnection) url.openConnection();

		consumer.sign(request);
		request.connect();

		System.out.println("Response: " + request.getResponseCode() + " " + request.getResponseMessage());
		BufferedReader br2 = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br2.readLine()) != null) {
			sb.append(output);
		}
		return sb.toString();
	}
	
	private OAuthProvider newProvider() {
		return new DefaultOAuthProvider(
				"https://chpp.hattrick.org/oauth/request_token.ashx",
				"https://chpp.hattrick.org/oauth/access_token.ashx", 
				"https://chpp.hattrick.org/oauth/authorize.aspx");
	}

	public String getAuthorizationUrl() throws OAuthException {
		DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		OAuthProvider provider = newProvider();
		
		String url = provider.retrieveRequestToken(consumer, CALLBACK_URL);
		waitingClients.put(consumer.getToken(), new OauthConsumer(consumer, provider));
		return url;
	}

	public String fetchData(OauthClientCredentials client, String url) throws OAuthException, IOException {
		return fetchData(client.getToken(), client.getSecret(), url);
	}
	
	public OauthClientCredentials authentificate(String token, String verifier) throws NotFoundException, OAuthException {

		if( !waitingClients.containsKey(token) ) {
			throw new NotFoundException("consumer not found");
		}
		OauthConsumer oauthClient = waitingClients.get(token);
		AbstractOAuthConsumer consumer = oauthClient.getConsumer();
		oauthClient.getProvider().retrieveAccessToken(consumer, verifier);
		
		waitingClients.remove(token);
		return new OauthClientCredentials(consumer.getToken(), consumer.getTokenSecret());
	}

	@Data 
	class OauthConsumer{
		private final AbstractOAuthConsumer consumer;
		private final OAuthProvider provider;
	}

	
}
