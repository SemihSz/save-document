package com.application.document;

import com.application.document.entity.RoleEntity;
import com.application.document.model.enums.RoleTypes;
import com.application.document.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Semih, 5.07.2023
 */
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        RoleEntity user = new RoleEntity();
        user.setName(RoleTypes.ROLE_USER);
        RoleEntity moderator = new RoleEntity();
        moderator.setName(RoleTypes.ROLE_MODERATOR);
        RoleEntity admin =  new RoleEntity();
        admin.setName(RoleTypes.ROLE_ADMIN);
        roleRepository.save(user);
        roleRepository.save(moderator);
        roleRepository.save(admin);
    }
}
