package com.application.document.service;

import com.application.document.model.IsAliveConfigurationProperties;
import com.application.document.model.ProjectInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class IsProjectAliveService {

    private final IsAliveConfigurationProperties isAliveConfigurationProperties;

    public ProjectInfoDTO getProjectIsAlive(String project) {

        Optional<ProjectInfoDTO> packages = isAliveConfigurationProperties.getPackages().stream().filter(t -> t.getName().equals(project)).findFirst();
        return packages.orElse(null);
    }
}
