package ro.etrcpo.htcalculator.mapper;

import org.junit.Test;

import ro.etrcpo.htcalculator.auth.AuthService;

public class AuthServceTest {

	AuthService authService = new AuthService();
	
	@Test
	public void test() throws Exception {
		
		
		String oauthToken = authService.requestToken();
		
		System.out.println( oauthToken );
	}
	
}
