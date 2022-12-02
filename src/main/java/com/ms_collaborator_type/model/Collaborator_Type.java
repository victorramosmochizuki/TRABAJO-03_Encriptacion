package com.ms_collaborator_type.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collaborator_type")
public class Collaborator_Type {

    @Id private Long id;
    @Column private String position;
    @Column private String description;
    @Column private String area_type;
    @Column private boolean active;
    @Column private Integer stage;

}
