package ro.etrcpo.htcalculator.auth;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import lombok.Data;
import oauth.signpost.exception.OAuthException;



@Data
public class OauthClient {

	public final static String PLAYERS_URL = "https://chpp.hattrick.org/chppxml.ashx?file=players&version=2.4";
	
	private final OauthClientCredentials credentials;
	private final AuthService authService;
	
	@Autowired
	public OauthClient(OauthClientCredentials credentials, AuthService authService) {
		this.credentials = credentials;
		this.authService = authService;
	}
	
	public String getPlayers() throws OAuthException {
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			String xml = authService.fetchData(credentials, PLAYERS_URL);
			Document doc = dBuilder.parse( new InputSource(new StringReader( xml )) );
			
			doc.getDocumentElement().normalize();
			NodeList playerNodes = doc.getElementsByTagName("Player");
			List<Player> players = new ArrayList<>();

			for (int i=0; i<playerNodes.getLength(); i++) {
				Node playerNode = playerNodes.item(i);
				Player p = new Player();
				p.name = "";
				
				for( int j = 0; j < playerNode.getChildNodes().getLength(); j++ ) {
					Node skill = playerNode.getChildNodes().item(j);
					switch( skill.getNodeName() ){
						case "FirstName" : p.name += skill.getFirstChild().getNodeValue(); break;
						case "LastName" : p.name += " " + skill.getFirstChild().getNodeValue(); break;
						case "KeeperSkill" : p.goalkeeper = skill.getFirstChild().getNodeValue(); break;
						case "PlaymakerSkill" : p.playmaking = skill.getFirstChild().getNodeValue(); break;
						case "ScorerSkill" : p.scoring = skill.getFirstChild().getNodeValue(); break;
						case "PassingSkill" : p.passing = skill.getFirstChild().getNodeValue(); break;
						case "WingerSkill" : p.winger = skill.getFirstChild().getNodeValue(); break;
						case "DefenderSkill" : p.defending = skill.getFirstChild().getNodeValue(); break;
						case "SetPiecesSkill" : p.setpieces = skill.getFirstChild().getNodeValue(); break;
					}
				}
				players.add(p);
			}
			
			return new Gson().toJson(players);
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			System.err.println("error parsing the xml: " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	class Player {
		public String name; 
		public String goalkeeper;
		public String playmaking;
		public String scoring;
		public String defending;
		public String passing;
		public String winger;
		public String setpieces;
	}
}
