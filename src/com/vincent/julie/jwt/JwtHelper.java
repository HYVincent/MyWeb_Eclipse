package com.vincent.julie.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**  
 * @Title:  JwtHelper.java   
 * @Package com.vincent.julie.jwt   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��3��15�� ����11:37:45   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class JwtHelper {

	/**
	 * ����token
	 * @param jsonWebToken
	 * @param base64Security
	 * @return
	 */
	public static Claims parseJWT(String jsonWebToken, String base64Security){  
        try  
        {  
           /* Claims claims = Jwts.parser()  
                       .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))  
                       .parseClaimsJws(jsonWebToken).getBody(); */ 
        	Claims claims = Jwts.parser()  
                    .setSigningKey(Base64.getDecoder().decode(base64Security))  
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;  
        }  
        catch(Exception ex)  
        {  
            return null;  
        }  
    }  
      
	/**
	 * ����token
	 * @param name
	 * @param userId
	 * @param role
	 * @param audience
	 * @param issuer
	 * @param TTLMillis ����ʱ��
	 * @param base64Security
	 * @return
	 */
    public static String createJWT(String name, String userId, String role,   
            String audience, String issuer, long TTLMillis, String base64Security)   
    {  
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;  
           
        long nowMillis = System.currentTimeMillis();  
        Date now = new Date(nowMillis);  
           
        //����ǩ����Կ  
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());  
           
          //��ӹ���JWT�Ĳ���  
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")  
                                        .claim("role", role)  
                                        .claim("unique_name", name)  
                                        .claim("userid", userId)  
                                        .setIssuer(issuer)  
                                        .setAudience(audience)  
                                        .signWith(signatureAlgorithm, signingKey);  
         //���Token����ʱ��  
        if (TTLMillis >= 0) {  
            long expMillis = nowMillis + TTLMillis;  
            Date exp = new Date(expMillis);  
            builder.setExpiration(exp).setNotBefore(now);  
        }  
           
         //����JWT  
        return builder.compact();  
    }   
	
	
}
