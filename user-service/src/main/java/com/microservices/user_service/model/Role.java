package com.microservices.user_service.model;

import com.microservices.user_service.enums.AppRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @ManyToMany
    @NotNull
    @Enumerated(EnumType.STRING)
    private AppRole role_name;
}
