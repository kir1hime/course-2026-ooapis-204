package ua.com.kneu.services.admin_types;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.admin_types.AdminTypesDTO;
import ua.com.kneu.dtos.admin_types.AdminTypesMapper;
import ua.com.kneu.entities.AdminTypes;
import ua.com.kneu.repositories.AdminTypesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminTypesServiceImpl implements AdminTypesService {

    private final AdminTypesRepository adminTypesRepository;

    @Override
    public AdminTypesDTO save(AdminTypesDTO dto) {
        AdminTypes entity = AdminTypesMapper.toEntity(dto);
        AdminTypes saved = adminTypesRepository.save(entity);
        return AdminTypesMapper.toDTO(saved);
    }

    @Override
    public AdminTypesDTO update(AdminTypesDTO dto) {
        AdminTypes entity = AdminTypesMapper.toEntity(dto);
        AdminTypes updated = adminTypesRepository.save(entity);
        return AdminTypesMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        adminTypesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        adminTypesRepository.deleteAll();
    }

    @Override
    public List<AdminTypesDTO> findAll() {
        return adminTypesRepository.findAll()
                .stream().map(AdminTypesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AdminTypesDTO findById(Long id) {
        return adminTypesRepository.findById(id)
                .map(AdminTypesMapper::toDTO).orElse(null);
    }
}