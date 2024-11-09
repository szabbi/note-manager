package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.UserDto;
import hu.unideb.inf.notemanager.entitiy.UserEntity;
import hu.unideb.inf.notemanager.mapper.UserEntityMapper;
import hu.unideb.inf.notemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEntityMapper userEntityMapper;

    public UserDto addUser(UserDto user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity = userRepository.save(userEntity);

        UserDto dto = new UserDto();
        dto = userEntityMapper.toDto(userEntity);

        return dto;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity entity = userRepository.getReferenceById(id);
        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserEntity> entites = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        dtos = userEntityMapper.toDtoList(entites);
        return dtos;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
