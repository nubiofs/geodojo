package org.latinoware.geodojo.app.teste;

import org.junit.Test;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;




public class TesteTwitter4j {

	@Test
	public void send() throws TwitterException
	{
		 // The factory instance is re-useable and thread safe.
	    Twitter twitter = new TwitterFactory().getInstance("geodojo","latinoware");
	    Status status = twitter.updateStatus("Testando o  twitter4j");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
}
