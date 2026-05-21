package ua.com.kneu.services.currencies;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.currencies.CurrenciesDTO;
import ua.com.kneu.dtos.currencies.CurrenciesMapper;
import ua.com.kneu.entities.Currencies;
import ua.com.kneu.repositories.CurrenciesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    @Override
    public CurrenciesDTO save(CurrenciesDTO dto) {
        Currencies entity = CurrenciesMapper.toEntity(dto);
        Currencies saved = currenciesRepository.save(entity);
        return CurrenciesMapper.toDTO(saved);
    }

    @Override
    public CurrenciesDTO update(CurrenciesDTO dto) {
        Currencies entity = CurrenciesMapper.toEntity(dto);
        Currencies updated = currenciesRepository.save(entity);
        return CurrenciesMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        currenciesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        currenciesRepository.deleteAll();
    }

    @Override
    public List<CurrenciesDTO> findAll() {
        return currenciesRepository.findAll()
                .stream().map(CurrenciesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CurrenciesDTO findById(Long id) {
        return currenciesRepository.findById(id).map(CurrenciesMapper::toDTO).orElse(null);
    }
}