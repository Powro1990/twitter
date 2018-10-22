package twitter.db;

import twitter.model.Tweet;
import twitter.model.TweetRepository;
import twitter.model.TweetRepositoryException;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class SqlTweetRepository implements TweetRepository {
    private DataSource dataSource;
    Collection<Tweet> tweets;

    @Override
    public void add(Tweet tweet) throws TweetRepositoryException {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement prepS = connection.prepareStatement("INSERT INTO tweet(message, author) VALUES (?, ?)");
            prepS.setString(1,tweet.getMessage());
           // prepS.setString(2,tweet.getAuthor());
            prepS.execute();

        } catch (SQLException e){
            throw new TweetRepositoryException("Zjebało sie");
        }

    }

    @Override
    public Stream<Tweet> allTweets() throws TweetRepositoryException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tweet");
            Collection<Tweet> allTweets = new ArrayList<>();
            while (resultSet.next()) {
                allTweets.add(new Tweet(resultSet.getString
                        ("message")));
            }
            return allTweets.stream();
        }catch (SQLException e){
            throw new TweetRepositoryException("Zjebało sie");
        }
    }
}
