package tweets;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class TweetsTest {

   private TweetAPIClient tweetAPIClient;

   @BeforeMethod
    public void setUpTweetAPI(){
       this.tweetAPIClient = new TweetAPIClient();
   }

   @Test
    public void testUserCanTweetSuccessfully(){
       String tweet = "Tweet " + UUID.randomUUID().toString();
       ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
       response.statusCode(200);
       String actualTweet = response.extract().body().path("text");
       Assert.assertEquals(tweet, actualTweet);
   }
   @Test
    public void testUserCanNotCreateDuplicateTweet(){

       ValidatableResponse response = this.tweetAPIClient.createTweet("Hello World 2");
       response.statusCode(200);
       ValidatableResponse response2 = this.tweetAPIClient.createTweet("Hello World 2");
       response2.statusCode(403);

//       String msg = response.extract().body().path("message");
//       String errorMsg = "Status is a duplicate.";
//       Assert.assertEquals(msg,errorMsg);

   }

}
