package twitter.db;

import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class inMemoryTweetRepository implements TweetRepository {
    List<Tweet> tweets = new ArrayList<>();

    public inMemoryTweetRepository() {
    }

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {
        tweets.add(tweet);
    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        return tweets.stream();
    }
}
