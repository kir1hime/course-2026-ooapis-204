package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Admins;

@Repository
public interface AdminsRepositories extends JpaRepository<Admins, Long> {
}
