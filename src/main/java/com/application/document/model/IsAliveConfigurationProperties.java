package com.application.document.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@ConfigurationProperties("project-info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class IsAliveConfigurationProperties {

    private List<ProjectInfoDTO> packages;
}
