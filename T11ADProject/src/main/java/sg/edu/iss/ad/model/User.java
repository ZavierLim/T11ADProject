package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@Email
	private String email;
	private RoleType role;
	
	@OneToMany(mappedBy="User")
	private List<UserStockComment> UserStockComment;
	
	//@OneToOne(mappedBy="User")
	@OneToMany(mappedBy="User")
	private List<UserStockWatchList> UserStockWatchList;
	
	@OneToMany(mappedBy="User")
	private List<CandleHistory> CandleHistory;
	
	public User() {};
}
