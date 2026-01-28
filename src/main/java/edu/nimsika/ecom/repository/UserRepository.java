package edu.nimsika.ecom.repository;

import edu.nimsika.ecom.model.UserRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserRegisterEntity,Long>{

    UserRegisterEntity findByUsername(String username);
}
