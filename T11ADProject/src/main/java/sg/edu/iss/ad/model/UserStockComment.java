package sg.edu.iss.ad.model;

import java.util.List;

import javax.persistence.Entity;
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
	
	@ManyToOne
	private User User;	
	
	@ManyToOne
	private Stock Stock;

	public UserStockComment() {};
}
