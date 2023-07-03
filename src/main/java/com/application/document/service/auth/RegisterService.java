package com.application.document.service.auth;

import com.application.document.Constant;
import com.application.document.entity.RoleEntity;
import com.application.document.entity.UserEntity;
import com.application.document.exception.AuthException;
import com.application.document.model.Role;
import com.application.document.model.request.auth.UserRegisterRequestDTO;
import com.application.document.repository.RoleRepository;
import com.application.document.repository.UserRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Semih, 2.07.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService implements SimpleTask<UserRegisterRequestDTO, Boolean> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MessageSource messageSource;

    /**
     *  This executable task manage the all register operation. If the username or email has been existed, service will return ${@link AuthException}
     * @param registerRequest the function argument
     * @return
     */
    @Override
    public Boolean apply(UserRegisterRequestDTO registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new AuthException(messageSource.getMessage(Constant.Exception.AUTH_USER_EXIST, null, Locale.ENGLISH));
        } else if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AuthException(messageSource.getMessage(Constant.Exception.AUTH_EMAIL_EXIST, null, Locale.ENGLISH));
        }
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        registerRequest.setPassword(bcryptEncoder.encode(registerRequest.getPassword()));
        try {
            final String strRoles = registerRequest.getRole();
            Set<RoleEntity> roles = new HashSet<>();
            // TODO Upgrade here!
            if (strRoles == null) {
                final RoleEntity userRole = roleRepository.findByName(Role.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                switch (strRoles) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(Role.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(Role.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(Role.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            }

            final UserEntity user = UserEntity.builder()
                    .username(registerRequest.getUsername())
                    .password(registerRequest.getPassword())
                    .roles(roles)
                    .build();
            userRepository.save(user);

            return Boolean.TRUE;

        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
