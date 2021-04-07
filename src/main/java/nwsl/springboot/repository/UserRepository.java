package nwsl.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nwsl.springboot.todo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
}
