package ua.com.kneu.services.admins;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.admins.AdminsDTO;
import ua.com.kneu.dtos.admins.AdminsMapper;
import ua.com.kneu.entities.Admins;
import ua.com.kneu.repositories.AdminsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminsServiceImpl implements AdminsService {

    private final AdminsRepository adminsRepository;

    @Override
    public AdminsDTO save(AdminsDTO dto) {
        Admins entity = AdminsMapper.toEntity(dto);
        Admins saved = adminsRepository.save(entity);
        return AdminsMapper.toDTO(saved);
    }

    @Override
    public AdminsDTO update(AdminsDTO dto) {
        Admins entity = AdminsMapper.toEntity(dto);
        Admins updated = adminsRepository.save(entity);
        return AdminsMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        adminsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        adminsRepository.deleteAll();
    }

    @Override
    public List<AdminsDTO> findAll() {
        return adminsRepository.findAll()
                .stream().map(AdminsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AdminsDTO findById(Long id) {
        return adminsRepository.findById(id).map(AdminsMapper::toDTO).orElse(null);
    }
}