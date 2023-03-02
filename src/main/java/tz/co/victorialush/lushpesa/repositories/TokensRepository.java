package tz.co.victorialush.lushpesa.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tz.co.victorialush.lushpesa.models.Token;

import java.util.Optional;

public interface TokensRepository extends CrudRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
