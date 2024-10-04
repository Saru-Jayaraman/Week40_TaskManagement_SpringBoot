package se.lexicon.week40_taskmanagement_springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    //Update Password by Email ID
    @Modifying
    @Query("update User u set u.password = :password where u.email = :email")
    int updatePasswordByEmail(@Param("email") String email,@Param("password") String password);

    //Update Expired by Email ID
    @Modifying
    @Query("update User u set u.expired = :status where u.email = :email")
    int updateExpiredByEmail(@Param("email") String email,@Param("status") boolean status);
}
