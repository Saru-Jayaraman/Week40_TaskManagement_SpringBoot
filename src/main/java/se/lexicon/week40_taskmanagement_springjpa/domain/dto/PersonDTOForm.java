package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDTOForm {
    private Long id;
    private String name;
    private List<TaskDTOForm> taskList;
    private UserDTOForm user;
}
