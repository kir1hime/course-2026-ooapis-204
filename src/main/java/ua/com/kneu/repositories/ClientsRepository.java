package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Long id(Long id);
}
