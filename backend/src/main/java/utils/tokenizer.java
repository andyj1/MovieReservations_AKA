package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Map;


public class tokenizer {
    private final Config config;
    private final String secret;

    public tokenizer() {
        this.config = ConfigFactory.load("AKA.conf");
        this.secret = this.config.getString("secrets.jwt-key");
    }

    public String verifyToken(String token) {
        String username = null;
        try {
            Algorithm algorithm = Algorithm.HMAC512(this.secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            username = claims.get("username").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
        return username;
    }

    public String makeToken(String username) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC512(this.secret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }
}
