package ua.com.kneu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Currencies;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {
}
