package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
}
