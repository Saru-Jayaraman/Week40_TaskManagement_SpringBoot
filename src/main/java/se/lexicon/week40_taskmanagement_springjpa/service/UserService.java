package se.lexicon.week40_taskmanagement_springjpa.service;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOView;

public interface UserService {
    UserDTOView register(UserDTOForm userDTOForm);

    void updatePassword(String email, String password);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);
}
