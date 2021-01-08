package ro.etrcpo.htcalculator.auth;

import lombok.Data;

@Data
public class OauthClientCredentials {
	private final String token;
	private final String secret;
}
