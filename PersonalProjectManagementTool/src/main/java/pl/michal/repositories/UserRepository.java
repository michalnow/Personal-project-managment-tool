package pl.michal.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.michal.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long id);

}
