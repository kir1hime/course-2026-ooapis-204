package ua.com.kneu.services.card_types;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.card_type.CardTypesDTO;
import ua.com.kneu.dtos.card_type.CardTypesMapper;
import ua.com.kneu.entities.CardTypes;
import ua.com.kneu.repositories.CardTypesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardTypesServiceImpl implements CardTypesService {

    private final CardTypesRepository cardTypesRepository;

    @Override
    public CardTypesDTO save(CardTypesDTO dto) {
        CardTypes entity = CardTypesMapper.toEntity(dto);
        CardTypes saved = cardTypesRepository.save(entity);
        return CardTypesMapper.toDTO(saved);
    }

    @Override
    public CardTypesDTO update(CardTypesDTO dto) {
        CardTypes entity = CardTypesMapper.toEntity(dto);
        CardTypes updated = cardTypesRepository.save(entity);
        return CardTypesMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        cardTypesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cardTypesRepository.deleteAll();
    }

    @Override
    public List<CardTypesDTO> findAll() {
        return cardTypesRepository.findAll()
                .stream().map(CardTypesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CardTypesDTO findById(Long id) {
        return cardTypesRepository.findById(id).map(CardTypesMapper::toDTO).orElse(null);
    }
}
