package com.pos2.pos2.DataAccess.Repository;

import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.DataAccess.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.pass = ?2")
    User loginCheck (String username, String password);

}
