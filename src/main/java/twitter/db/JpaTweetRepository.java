package twitter.db;

import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.stream.Stream;

public class JpaTweetRepository implements TweetRepository {
    EntityManager entityManager;

    public JpaTweetRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(tweet);
        transaction.commit();
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        return entityManager.createNamedQuery("Tweet.findTweets", Tweet.class).getResultStream();
    }
}
