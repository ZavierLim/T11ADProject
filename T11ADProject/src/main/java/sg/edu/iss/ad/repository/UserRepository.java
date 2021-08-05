package sg.edu.iss.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ad.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
