package com.employee.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtils {

	private static final String JWT_SECRET = "abcdefghhacc3uyrabcdefghhacc3uyr82y3r894758278r394729865249yf7gf2g877462934981481y423y7fg23fg2fgijklmnopqrstuvwxyz123456789082y3r894758278r394729865249yf7gf2g877462934981481y423y7fg23fg2fgijklmnopqrstuvwxyz1234567890";
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claim = new HashMap<>();
		return createToken(claim, userDetails.getUsername());
		
	}

	public String createToken(Map<String, Object> claim, String subject) {

		return Jwts.builder()
				.setClaims(claim)
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256,JWT_SECRET)
				.compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

	public boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	public <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}
}
