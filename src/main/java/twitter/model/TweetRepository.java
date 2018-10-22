package twitter.model;

import java.util.stream.Stream;

public interface TweetRepository {
    void add(Tweet tweet) throws TweetRepositoryException;
    Stream<Tweet>allTweets() throws TweetRepositoryException;
}
