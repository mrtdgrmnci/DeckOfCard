package deckOfCardTasks;

import TestBase.deckOfCard;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class Tasks extends deckOfCard {

    static String deck_id;

    @DisplayName("1.Shuffle the Cards://https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1")
    @Test
    public void shuffleTheCards(){

        JsonPath jp = given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("deck_count", 1).
                        when()
                .get("/new/shuffle/").
                        then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON).extract().jsonPath();

        String succes = jp.getString("success");
        System.out.println(succes);
        assertThat(succes,equalTo("true"));

        deck_id=jp.getString("deck_id");

        System.out.println(deck_id);

    }

    @DisplayName("2.Draw a Card https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2 ")
    @Test
    public void drawACard(){

        JsonPath jp = given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("count", 2).
                        when()
                .get("/new/draw").prettyPeek().
                        then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON).extract().jsonPath();

    }


    @DisplayName("3. Reshuffle the Cards:   https://deckofcardsapi.com/api/deck/<<deck_id>>/shuffle/")
    @Test
    public void reshuffleTheCard(){

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("deck_id",deck_id).
        when()
                .get("/{deck_id}/shuffle/").
        then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON).extract().jsonPath();


    }

    @DisplayName("4. A Brand New Deck https://deckofcardsapi.com/api/deck/new/ WITH jokers_enabled=true ")
    @Test
    public void aBrandNewDeck(){

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("jokers_enabled",true).
        when()
                .get("/new/").
        then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON).extract().jsonPath();


    }


    @DisplayName("5. https://deckofcardsapi.com/api/deck/hqc585b8405k/shuffle/?cards=AS,2S,KS,AD,2D,KD,AC,2C,KC,AH,2H,KH ")
    @Test
    public void aPartialDeck(){
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("cards","AS","2S","KS","AD","2D","KD","AC","2C","KC","AH","2H","KH").
                when()
                .get("/new/shuffle/").
                then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON).extract().jsonPath();

    }

}
