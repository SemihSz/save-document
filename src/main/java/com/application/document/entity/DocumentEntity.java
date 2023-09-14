package com.application.document.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Semih, 3.07.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String username;

    private String fileName;

    private String fileType;

    @Column(name = "data", columnDefinition = "LONGVARBINARY")
    private byte[] data;

    public LocalDateTime time;

}
