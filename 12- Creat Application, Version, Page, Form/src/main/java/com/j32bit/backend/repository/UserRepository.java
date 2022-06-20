/**
 * 
 */
package com.j32bit.backend.repository;


import com.j32bit.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

/**

 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {


	public User findByUserName(String userName);


	public ArrayList<User> findAll();


}
