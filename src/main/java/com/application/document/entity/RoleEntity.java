package com.application.document.entity;

import com.application.document.model.enums.RoleTypes;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Semih, 2.07.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleTypes name;

}
