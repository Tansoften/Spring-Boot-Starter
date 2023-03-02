package tz.co.victorialush.lushpesa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.co.victorialush.lushpesa.models.Token;
import tz.co.victorialush.lushpesa.models.User;
import tz.co.victorialush.lushpesa.plugins.Cryptography;
import tz.co.victorialush.lushpesa.repositories.TokensRepository;
import tz.co.victorialush.lushpesa.repositories.UsersRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
public class Authenticate {
    @Autowired
    TokensRepository tokensRepository;
    @Autowired
    UsersRepository usersRepository;
    private Token tokenInstance;
    private User user;

    public User user(){
        return this.user;
    }

    public boolean verifyToken(String token){
        try {
            Optional<Token> tokenEnclosure = tokensRepository.findByToken(Cryptography.hashValue(token));

            if(tokenEnclosure.isPresent()){
                tokenInstance = tokenEnclosure.get();
                Optional<User> userEnclosure = usersRepository.findById(tokenInstance.getUserId());
                if(userEnclosure.isPresent()){
                    this.user = userEnclosure.get();
                    this.user.setPassword(null);
                }

                return true;
            }
        }catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }
    public String createToken(Integer userId) throws Exception {
        String token = UUID.randomUUID().toString();
        tokenInstance = new Token();
        tokenInstance.setToken(Cryptography.hashValue(token));
        tokenInstance.setUserId(userId);
        tokensRepository.save(tokenInstance);

        return token;
    }
}
