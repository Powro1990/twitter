package twitter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@NamedQuery(name = "Tweet.findTweets", query = "SELECT t FROM Tweet t")
@Entity
public class Tweet {


	@Id
	@GeneratedValue
	private int id;
	private String message;


	public Tweet() {
	}

	public Tweet(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Tweet{" +
				"message='" + message + '\'' +
				'}';
	}
}
