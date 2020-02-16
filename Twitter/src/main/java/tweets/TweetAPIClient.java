package tweets;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class TweetAPIClient {

    private final String API_KEY = "JBHJ8Smr9D90KYbC3LARtVjxI";
    private final String API_SECRET_KEY = "jshodxI79XE4Gp9TMf8ch7zoVTjCQQQlD8hyjUBpW78rpUzT4o";
    private final String ACCESS_TOKEN = "1224911129950019584-uR8ne6kmDs1hTfZTH9z3mKkJ3ofuKS";
    private final String ACCESS_TOKEN_SECRET = "Y2kGrNRPrEAyPtmUbsQYWqCZ6biCdyhBULVEQ0o7gwvIn";
    private final String CREATE_TWEET_ENDPOINT = "https://api.twitter.com/1.1/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "https://api.twitter.com/1.1/statuses/destroy/:id.json";
    private final String GET_USER_TWEETS_ENDPOINT = "https://api.twitter.com/1.1/statuses/user_timeline.json";

    //given
    //authentication, prams, headers if any
    //when
    //making the request
    //then
    //where we gwt our response and validate etc

    /**
     * Retrieves all the user's tweets from their timeline
     *
     * @return a response fof the request
     */
    public ValidatableResponse getUserTimelineTweet() {
        return given().auth().oauth(this.API_KEY, this.API_SECRET_KEY, this.ACCESS_TOKEN, this.ACCESS_TOKEN_SECRET)
                .when().get(GET_USER_TWEETS_ENDPOINT)
                .then();
    }

    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.API_KEY, this.API_SECRET_KEY, this.ACCESS_TOKEN, this.ACCESS_TOKEN_SECRET)
                .param("status", tweet)
                .when().post(CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.API_KEY, this.API_SECRET_KEY, this.ACCESS_TOKEN, this.ACCESS_TOKEN_SECRET)
                .queryParam("id", tweetId)
                .when().post(DELETE_TWEET_ENDPOINT)
                .then();
    }


}
