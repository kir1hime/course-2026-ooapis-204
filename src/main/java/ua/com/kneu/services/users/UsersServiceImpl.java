package ua.com.kneu.services.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kneu.dtos.users.UsersDTO;
import ua.com.kneu.dtos.users.UsersMapper;
import ua.com.kneu.entities.Users;
import ua.com.kneu.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public UsersDTO save(UsersDTO dto) {
        Users entity = UsersMapper.toEntity(dto);
        Users saved = usersRepository.save(entity);
        return UsersMapper.toDTO(saved);
    }

    @Override
    public UsersDTO update(UsersDTO dto) {
        Users entity = UsersMapper.toEntity(dto);
        Users updated = usersRepository.save(entity);
        return UsersMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        usersRepository.deleteAll();
    }

    @Override
    public List<UsersDTO> findAll() {
        return usersRepository.findAll()
                .stream().map(UsersMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsersDTO findById(Long id) {
        return usersRepository.findById(id).map(UsersMapper::toDTO).orElse(null);
    }
}