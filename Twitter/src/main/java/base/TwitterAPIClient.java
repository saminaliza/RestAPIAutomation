package base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TwitterAPIClient {
    protected String apiKey;
    protected String apiSecretKey;
    protected String accessToken;
    protected String accessTokenSecret;
    protected Properties properties;
    protected InputStream inputStream;

    public TwitterAPIClient(){
        this.properties = new Properties();
        this.inputStream = null;
        try {
            //path of properties file
            this.inputStream = new FileInputStream("src/main/secret.properties");
            //load the properties file


        }catch (Exception ex){
            System.out.println("File not found");
            ex.printStackTrace();

        }
    }


}
