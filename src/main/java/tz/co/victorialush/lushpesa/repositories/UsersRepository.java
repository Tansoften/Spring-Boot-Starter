package tz.co.victorialush.lushpesa.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import tz.co.victorialush.lushpesa.models.User;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmailOrPhone(@NotNull String email, @NotNull String phone);
}
