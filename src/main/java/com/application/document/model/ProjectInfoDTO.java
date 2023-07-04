package com.application.document.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Semih, 3.07.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoDTO {

    private String name;

    private Boolean isProjectAlive;
}
