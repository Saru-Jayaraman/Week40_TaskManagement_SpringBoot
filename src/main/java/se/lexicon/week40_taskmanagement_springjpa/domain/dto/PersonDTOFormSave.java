package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDTOFormSave {
    private String name;
    private UserDTOForm user;
}
