package se.lexicon.week40_taskmanagement_springjpa.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    @Setter private String name;

    public Role(String name) {
        this.name = name;
    }
}