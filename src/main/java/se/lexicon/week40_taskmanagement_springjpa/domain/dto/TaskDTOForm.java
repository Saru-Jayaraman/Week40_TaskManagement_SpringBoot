package se.lexicon.week40_taskmanagement_springjpa.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TaskDTOForm {
    private Long id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private PersonDTOForm person;
}
