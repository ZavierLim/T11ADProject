package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
//	@OneToMany(fetch=FetchType.LAZY,mappedBy="User")
//	private List<UserStockComment> UserStockComment;
//	
//	//@OneToOne(mappedBy="User")
//	@OneToMany(fetch=FetchType.LAZY,mappedBy="User")
//	private List<UserStockWatchList> UserStockWatchList;
//	
//	@OneToMany(fetch=FetchType.LAZY,mappedBy="User")
//	private List<CandleHistory> CandleHistory;
	
	public User() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

//	public List<UserStockComment> getUserStockComment() {
//		return UserStockComment;
//	}
//
//	public void setUserStockComment(List<UserStockComment> userStockComment) {
//		UserStockComment = userStockComment;
//	}
//
//	public List<UserStockWatchList> getUserStockWatchList() {
//		return UserStockWatchList;
//	}
//
//	public void setUserStockWatchList(List<UserStockWatchList> userStockWatchList) {
//		UserStockWatchList = userStockWatchList;
//	}
//
//	public List<CandleHistory> getCandleHistory() {
//		return CandleHistory;
//	}
//
//	public void setCandleHistory(List<CandleHistory> candleHistory) {
//		CandleHistory = candleHistory;
//	};
	
	
}
