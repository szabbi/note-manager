package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.LoginDto;
import hu.unideb.inf.notemanager.dto.RegistrationDto;
import hu.unideb.inf.notemanager.entitiy.UserEntity;
import hu.unideb.inf.notemanager.mapper.UserEntityMapper;
import hu.unideb.inf.notemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public void registration(RegistrationDto dto) {
        UserEntity entity = userEntityMapper.toEntityReg(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        userRepository.save(entity);
    }

    @Override
    public void login(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }
}
