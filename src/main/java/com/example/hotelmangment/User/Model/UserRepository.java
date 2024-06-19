package com.example.hotelmangment.User.Model;

import com.example.hotelmangment.User.Model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);

}
