package ua.com.kneu.dtos.clients;

import ua.com.kneu.entities.Accounts;
import ua.com.kneu.entities.Clients;

import java.util.stream.Collectors;

public class ClientsMapper {

    public static ClientsDTO toDTO(Clients client) {
        ClientsDTO dto = new ClientsDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setPatronymic(client.getPatronymic());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAddress(client.getAddress());
        dto.setAge(client.getAge());

        if (client.getUser() != null) {
            dto.setUserId(client.getUser().getId());
            dto.setUsername(client.getUser().getUsername());
        }

        if (client.getAccounts() != null) {
            dto.setAccountIds(client.getAccounts().stream()
                    .map(Accounts::getId)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Clients toEntity(ClientsDTO dto) {
        Clients client = new Clients();
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPatronymic(dto.getPatronymic());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());
        client.setAge(dto.getAge());
        return client;
    }
}
