package ua.com.kneu.services.clients;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.clients.ClientsDTO;
import ua.com.kneu.dtos.clients.ClientsMapper;
import ua.com.kneu.entities.Clients;
import ua.com.kneu.repositories.ClientsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    @Override
    public ClientsDTO save(ClientsDTO dto) {
        Clients entity = ClientsMapper.toEntity(dto);
        Clients saved = clientsRepository.save(entity);
        return ClientsMapper.toDTO(saved);
    }

    @Override
    public ClientsDTO update(ClientsDTO dto) {
        Clients entity = ClientsMapper.toEntity(dto);
        Clients updated = clientsRepository.save(entity);
        return ClientsMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        clientsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        clientsRepository.deleteAll();
    }

    @Override
    public List<ClientsDTO> findAll() {
        return clientsRepository.findAll()
                .stream().map(ClientsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClientsDTO findById(Long id) {
        return clientsRepository.findById(id).map(ClientsMapper::toDTO).orElse(null);
    }
}
