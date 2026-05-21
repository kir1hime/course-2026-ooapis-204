package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.CardTypes;

@Repository
public interface CardTypesRepository extends JpaRepository<CardTypes, Long> {
}
