package ua.com.kneu.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kneu.entities.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
