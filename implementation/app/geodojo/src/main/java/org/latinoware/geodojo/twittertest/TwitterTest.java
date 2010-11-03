package org.latinoware.geodojo.twittertest;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 *
 * @author ranophoenix
 */
public class TwitterTest {

    public static void main(String... args) throws TwitterException{
        // The factory instance is re-useable and thread safe.
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query("#latino");
        query.setRpp(100);
        QueryResult result = twitter.search(query);
        System.out.println("hits:" + result.getResultsPerPage());
        System.out.println(result.getMaxId() + " :" + result.getPage());
        for (Tweet tweet : result.getTweets()) {            
            User user = twitter.showUser(tweet.getFromUser());
            System.out.println(user.getLocation());
            if (user.isGeoEnabled()) {
                GeoLocation geo = user.getStatus() != null ? user.getStatus().getGeoLocation() : null;
                System.out.println(geo + ":" + tweet.getLocation() + ":" + tweet.getFromUser() + ":" + tweet.getText());
            }
        }

    }
}
