package posts;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class postsTest {

    private PostsAPIClients postsAPIClients;

    @BeforeClass
    public void setUpPostsAPI() {
        this.postsAPIClients = new PostsAPIClients();
    }

    @Test
    public void testUserCanGetAllPosts() {
        ValidatableResponse response = this.postsAPIClients.getAllPosts();
        //response.statusCode(200);
        response.statusCode(HttpStatus.SC_OK);
    }

    @Test //not recommended
    public void testUserCanCreatePosts() {
        ValidatableResponse response = this.postsAPIClients.createPost("{\n" +
                "    \"userId\": 12,\n" +
                "    \"id\": 101,\n" +
                "    \"title\": \"test\",\n" +
                "    \"body\": \"test body\"\n" +
                "  }");
        // response.statusCode(201);
        response.statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testUserCanCreatePostsSuccessfully() {
        //PostModel obj = new PostModel(12,101,"test", "test body"); //not recommended
        int userId = 12;
        int id = 101;
        String title = "test title";
        String body = "test body";
        JSONObject json = new JSONObject();
        json.put("userId", 12);
        json.put("id", 101);
        json.put("title", "test title");
        json.put("body", "test body");
        ValidatableResponse response = this.postsAPIClients.createPost(json);
        response.statusCode(HttpStatus.SC_CREATED);
        int actualUserId = response.extract().body().path("userId");
        int actualId = response.extract().body().path("id");
        String actualTitle = response.extract().body().path("title");
        String actualBody = response.extract().body().path("body");
        Assert.assertEquals(actualId, id);
        Assert.assertEquals(actualUserId, userId);
        Assert.assertEquals(actualTitle, title);
        Assert.assertEquals(actualBody, body);
    }


}
