package ElectronicStore.EStore.SpringSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt.secretkey}")
    private String mysecretkey;

    //retrive username from jwt token
    public String getUsernamefromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrive expiration date from jwt token

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public  <T>T getClaimFromToken(String token, Function<Claims,T>claimresolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimresolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String Token){
        return Jwts.parser().setSigningKey(mysecretkey).build().parseClaimsJws(Token).getBody();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userdetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,userdetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object>claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(
                        System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS256,mysecretkey)
                .compact();
    }

    private String signInKey(){
        byte[] decodedBytes = Base64.getDecoder().decode(mysecretkey);

        String decodedString = new String(decodedBytes);
        return decodedString;
    }
    public Boolean ValidateToken(String token,UserDetails userDetails){
        final String username = getUsernamefromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

}
