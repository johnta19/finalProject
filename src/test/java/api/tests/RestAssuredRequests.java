package api.tests;

import api.data.provider.Builder;
import api.data.provider.DataProvider;
import base.test.BaseTestApi;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static api.constants.Endpoints.PATH_TO_BOOKING;
import static api.constants.Endpoints.POST_REQUEST;
import static api.specifications.ResponseSpec.responseSpec;
import static api.specifications.ResponseSpec.responseSpecDeleteBooking;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredRequests extends BaseTestApi {

    private DataProvider dataProvider = new DataProvider();
    private Builder builder = new Builder();


    @BeforeTest
    public void createToken() {
        Response response = given()
                .body(builder.createToken())
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(responseSpec)
                .extract().response();
        dataProvider.setToken(response.jsonPath().getString("token"));
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void createBooking() {
        Response response = given()
                .body(builder.createBooking())
                .when()
                .post(PATH_TO_BOOKING)
                .then()
                .spec(responseSpec)
                .extract().response();
        dataProvider.setId(response.jsonPath().getInt("bookingid"));
    }

    @Test
    public void getBooking() {
        given()
                .when()
                .get(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(responseSpec)
                .assertThat()
                .body("firstname", equalTo("Jim")
                        , "lastname", equalTo("Brown")
                        , "totalprice", equalTo(111)
                        , "depositpaid", equalTo(true)).extract().response();
    }

  /*  @Test
    public void checkAuthorContract() {
        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .body(builder.updateAuthor())
                .when()
                .patch(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .log()
                .body()
                .body(matchesJsonSchemaInClasspath("authorSchema.json"));
    }*/

    @Test
    public void partialUpdateBooking() {
        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .body(builder.updateAuthor())
                .when()
                .patch(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath("authorSchema.json"))
                .assertThat()
                .body("firstname", equalTo("James"), "lastname", equalTo("Bond"));
    }

    @AfterTest
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .when()
                .delete(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(responseSpecDeleteBooking);
    }
}