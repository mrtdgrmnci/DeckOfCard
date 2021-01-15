package TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utility.ConfigReader;

import static io.restassured.RestAssured.*;

public class deckOfCard {

    @BeforeAll
    public static void setUp(){
        baseURI  = ConfigReader.getProperty("baseURL");
        basePath = ConfigReader.getProperty("basePath");


    }

    @AfterAll
    public static void tearDown(){
        reset();

    }



}
