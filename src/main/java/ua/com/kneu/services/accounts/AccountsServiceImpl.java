package ua.com.kneu.services.accounts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.accounts.AccountsDTO;
import ua.com.kneu.dtos.accounts.AccountsMapper;
import ua.com.kneu.entities.Accounts;
import ua.com.kneu.repositories.AccountsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public AccountsDTO save(AccountsDTO dto) {
        Accounts entity = AccountsMapper.toEntity(dto);
        Accounts saved = accountsRepository.save(entity);
        return AccountsMapper.toDTO(saved);
    }

    @Override
    public AccountsDTO update(AccountsDTO dto) {
        Accounts entity = AccountsMapper.toEntity(dto);
        Accounts updated = accountsRepository.save(entity);
        return AccountsMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        accountsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        accountsRepository.deleteAll();
    }

    @Override
    public List<AccountsDTO> findAll() {
        return accountsRepository.findAll()
                .stream().map(AccountsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountsDTO findById(Long id) {
        return accountsRepository.findById(id).map(AccountsMapper::toDTO).orElse(null);
    }
}
