package twitter.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class Dashboard {

	private EntityManager entityManager;

	;

	public Dashboard(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Tweet create(String msg, String author) throws TweetRepositoryException {
		Tweet newTweet = new Tweet(msg);

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
		query.setParameter("name", author);
		try {
			Author foundAuthor = query.getSingleResult();
			foundAuthor.addTweets(newTweet);

		} catch (NoResultException e) {
			Author newAuthor = new Author();
			newAuthor.setTweets(Collections.singleton(newTweet));
			entityManager.persist(newAuthor);
		}
		tx.commit();
		return newTweet;
	}

	public Stream<Tweet> load() throws TweetRepositoryException{
        Collection<Tweet> tweets= new ArrayList<>();
        Stream<Author> authors = entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultStream();


        authors.forEach(a ->tweets.addAll(a.getTweets()));

        return tweets.stream();
    }
}
