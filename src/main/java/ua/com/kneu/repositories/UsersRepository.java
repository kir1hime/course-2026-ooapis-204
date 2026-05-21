package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
