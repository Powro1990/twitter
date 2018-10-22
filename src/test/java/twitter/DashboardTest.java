package twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import twitter.model.Author;
import twitter.model.Dashboard;
import twitter.model.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest {

	private Dashboard dashboard;

	@BeforeEach
	void beforeEach() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		EntityManager entityManager = factory.createEntityManager();
		dashboard = new Dashboard(entityManager);
	}

	@DisplayName("author should be able to create a new twit")
	@Test
	void twitAuthor() throws Exception {
		// given
		String msg = "content";
		Author author1 = new Author();

		// when
		Tweet tweet = dashboard.create(msg, author1.getName());

		// then
		assertThat(tweet.getMessage()).isEqualTo(msg);
	}

	@DisplayName("should load created twit from the dashboard")
	@Test
	void db() throws Exception {
		// given
		String msg = "content";
		Author author1 = new Author();
		Tweet tweet = dashboard.create(msg, author1.getName());

		// when
		Stream<Tweet> allTwits = dashboard.load();


		// then
		assertThat(allTwits).containsExactlyInAnyOrder(tweet);
	}
}
