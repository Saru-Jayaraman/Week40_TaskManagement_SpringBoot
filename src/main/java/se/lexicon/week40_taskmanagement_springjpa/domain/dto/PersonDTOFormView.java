package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDTOFormView {
    private Long id;
    private String name;
    private List<Task> taskList;
    private UserDTOView user;
}
