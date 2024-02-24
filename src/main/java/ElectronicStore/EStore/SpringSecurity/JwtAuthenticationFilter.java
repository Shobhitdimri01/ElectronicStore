package ElectronicStore.EStore.SpringSecurity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
            private UserDetailsService userDetailsService;
    Logger log = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization_token = request.getHeader("Authorization");
        log.info("Authorization : {}",authorization_token);
        String username=null,token=null;
        if (authorization_token!=null  ){
//            authorization_token = authorization_token.substring(7);
            System.out.println(authorization_token);
            token = authorization_token;
            try {
              username = this.jwtHelper.getUsernamefromToken(token);
            }
            catch (IllegalArgumentException e){
                log.info("IllegalArgument : {}",e);
            }catch (ExpiredJwtException e){
                log.info("Expired jwt : {}",e);
            }catch (MalformedJwtException e){
                log.info("MalformedJwtException: {}",e);
            }catch (Exception e){
                log.info("Exception " + e);
            }

        }else{
            log.info("invalid header");
        }

        if ((username!=null)&& SecurityContextHolder.getContext().getAuthentication() == null){
            //fetch user-details from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.ValidateToken(token, userDetails);
            if(validateToken){
                //Set authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                log.info("Validation failed");
            }
        }

        filterChain.doFilter(request,response);

    }
}
