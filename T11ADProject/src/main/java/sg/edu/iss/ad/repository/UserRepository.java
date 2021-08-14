package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ad.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findUserByusernameAndPassword(String un, String pwd);
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	public User finduser(@Param("username")String username, @Param("password") String password);
	
	public User findByUsername(String username);
}
