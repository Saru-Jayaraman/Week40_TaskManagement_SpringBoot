package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "taskList")
@Builder
public class PersonDTOFormView {
    private Long id;
    private String name;
    private List<TaskDTOFormView> taskList;
    private UserDTOView user;
}
