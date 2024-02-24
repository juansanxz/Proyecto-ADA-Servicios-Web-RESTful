package edu.escuelaing.ieti.controller.auth;

import edu.escuelaing.ieti.exception.InvalidCredentialsException;
import edu.escuelaing.ieti.repository.user.RoleEnum;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.service.user.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;



@RestController
@RequestMapping( "/v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    private final UsersService userService;

    public AuthController( @Autowired UsersService userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login( @RequestBody LoginDto loginDto )
    {
        User user = userService.findByEmail( loginDto.email );
        if ( BCrypt.checkpw( loginDto.password, user.getPasswordHash() ) )
        {
            return generateTokenDto( user );
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( User user, Date expirationDate )
    {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( RoleEnum.USER.name(), user.getRoles() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, 10);
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}