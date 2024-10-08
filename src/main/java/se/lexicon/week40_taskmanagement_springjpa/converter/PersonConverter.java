package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.PersonDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.PersonDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.PersonDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Person;

public interface PersonConverter {
    Person toPersonEntitySave(PersonDTOFormSave dtoFormSave);

    Person toPersonEntityForm(PersonDTOForm dtoForm);

    PersonDTOFormView toPersonDTOView(Person entity);

    PersonDTOFormView toPersonDTOView(PersonDTOForm dto);

    PersonDTOForm toPersonDTOForm(PersonDTOFormView entity);

    PersonDTOForm toPersonDTOFormEntity(Person entity);
}
