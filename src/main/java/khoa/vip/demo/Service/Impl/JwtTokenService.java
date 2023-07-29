package khoa.vip.demo.Service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	
	@Value("${khoa.secretkey}")
	private String secretkey;
	
	@Value("${khoa.validy}")
	private long validy;
	
	public String creatToken(String Username) {
		Claims claims = Jwts.claims();
		claims.put("userName", Username);
		Date now = new Date();
		Date exp = new Date(now.getTime()+validy*60*1000);
		
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secretkey)
				.compact();
	}
	
	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	public String getUserName(String token) {
		try {
			return Jwts.parser().setSigningKey(secretkey)
					.parseClaimsJws(token).getBody().get("userName").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
