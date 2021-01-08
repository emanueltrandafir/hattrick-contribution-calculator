package ro.etrcpo.htcalculator.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import oauth.signpost.exception.OAuthException;
import ro.etrcpo.htcalculator.auth.AuthService;
import ro.etrcpo.htcalculator.auth.OauthClient;
import ro.etrcpo.htcalculator.auth.OauthClientCredentials;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;


    @GetMapping("/authorization")
	public ResponseEntity<String> requestAuthorization() {
    	System.out.println( "requestAuthorization()" );
		try {
			return new ResponseEntity<String>(authService.getAuthorizationUrl(), HttpStatus.OK);
		} catch (OAuthException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e2) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @GetMapping("/authentification")
	public ResponseEntity<OauthClientCredentials> requestAuthentification(@RequestParam String token, @RequestParam String verifier) {
    	System.out.println( String.format( "requestAuthentification( %s, %s )", token, verifier ));
		try {
			return new ResponseEntity<>(authService.authentificate(token, verifier), HttpStatus.OK);
		} catch (OAuthException e) {
			System.err.println( "oauth exception: " + e.getMessage() );
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e2) {
			System.err.println( "not found exception: " + e2.getMessage() );
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e3) {
			System.err.println( "unexpected exception: " + e3.getMessage() );
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @GetMapping("/players")
	public ResponseEntity<String> requestPlayers(@RequestParam String token, @RequestParam String secret) {
    	System.out.println( String.format( "requestPlayers( %s, %s )", token, secret ));
		try {
			return new ResponseEntity<>( new OauthClient(new OauthClientCredentials(token, secret), authService).getPlayers() , HttpStatus.OK);
		} catch (OAuthException e) {
			System.err.println( "oauth exception: " + e.getMessage() );
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e2 ) {
			System.err.println( "unexpected exception: " + e2.getMessage() );
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
