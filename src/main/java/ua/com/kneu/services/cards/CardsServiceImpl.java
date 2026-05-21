package ua.com.kneu.services.cards;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.cards.CardsDTO;
import ua.com.kneu.dtos.cards.CardsMapper;
import ua.com.kneu.entities.Cards;
import ua.com.kneu.repositories.CardsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;

    @Override
    public CardsDTO save(CardsDTO dto) {
        Cards entity = CardsMapper.toEntity(dto);
        Cards saved = cardsRepository.save(entity);
        return CardsMapper.toDTO(saved);
    }

    @Override
    public CardsDTO update(CardsDTO dto) {
        Cards entity = CardsMapper.toEntity(dto);
        Cards updated = cardsRepository.save(entity);
        return CardsMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        cardsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cardsRepository.deleteAll();
    }

    @Override
    public List<CardsDTO> findAll() {
        return cardsRepository.findAll()
                .stream()
                .map(CardsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CardsDTO findById(Long id) {
        return cardsRepository.findById(id)
                .map(CardsMapper::toDTO)
                .orElse(null);
    }
}