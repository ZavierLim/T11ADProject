package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Comments")
public class UserStockComment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String Comment;
	
	private long CommentDateTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User User;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Stock Stock;

	public UserStockComment() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public long getCommentDateTime() {
		return CommentDateTime;
	}

	public void setCommentDateTime(long commentDateTime) {
		CommentDateTime = commentDateTime;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Stock getStock() {
		return Stock;
	}

	public void setStock(Stock stock) {
		Stock = stock;
	};
	
	
}
