package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.LoginDto;
import hu.unideb.inf.notemanager.dto.RegistrationDto;
import hu.unideb.inf.notemanager.entitiy.UserEntity;
import hu.unideb.inf.notemanager.exception.InvalidCredentialsException;
import hu.unideb.inf.notemanager.exception.UserAlreadyExistsException;
import hu.unideb.inf.notemanager.mapper.UserEntityMapper;
import hu.unideb.inf.notemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    JwtService jwtService;

    @Override
    public void registration(RegistrationDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists.");
        }
        UserEntity entity = userEntityMapper.toEntityReg(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    @Override
    public String login(LoginDto dto) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            var user = userRepository.findByEmail(dto.getEmail());
            return jwtService.generateToken(user);
        } catch (InternalAuthenticationServiceException e) {
            throw new InvalidCredentialsException("User doesn't exist.");
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Wrong password.");
        }

    }
}
