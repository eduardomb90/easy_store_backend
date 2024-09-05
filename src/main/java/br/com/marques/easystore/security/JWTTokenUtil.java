package br.com.marques.easystore.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.marques.easystore.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class JWTTokenUtil {

    private static final String SECRET_KEY  = "3@$ySt0rEW3bSEcuR!tyTok3n$EcR3t!";
    private static final int    EXPIRATION  = 120*60*1000; // 2h em milisegundos
    private static final String TK_PREFIX   = "Bearer ";
    private static final String HEADER_AUTH = "Authorization";

    public static String generateToken(Usuario usuario){
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String jwt = Jwts.builder()
                            .setSubject(usuario.getUsername())
                            .setIssuer("*AlunoEduardo")
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                            .signWith(secretKey, SignatureAlgorithm.HS256)
                            .compact();

        return TK_PREFIX + jwt;
    }

    public static boolean isIssuerValid(String issuer){
        return issuer.equals("*AlunoEduardo");
    }

    public static boolean isSubjectValid(String subject){
        return subject != null && subject.length() > 0;
    }

    public static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    public static Authentication decodeToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTH);
        token = token.replace(TK_PREFIX, ""); // removi o Bearer

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                                    .setSigningKey(SECRET_KEY.getBytes())
                                    .build()
                                    .parseClaimsJws(token);

        String  username = jwsClaims.getBody().getSubject();
        String  emissor = jwsClaims.getBody().getIssuer();
        Date    expira = jwsClaims.getBody().getExpiration();

        if(isSubjectValid(username) && isIssuerValid(emissor) && isExpirationValid(expira)){
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }
        return null;
    }
}
