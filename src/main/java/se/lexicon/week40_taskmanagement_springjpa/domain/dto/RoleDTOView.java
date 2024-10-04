package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTOView {
    private Long id;
    private String name;
}
