package api.tests;

import api.data.provider.DataProvider;
import api.pojo.Bookingdates;
import api.pojo.CreateBookingPojo;
import api.pojo.CreateTokenPojo;
import api.pojo.PartialUpdateBookingPojo;
import base.test.BaseTestApi;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static api.constants.Credentials.LOGIN;
import static api.constants.Credentials.PASSWORD;
import static api.constants.Endpoints.PATH_TO_BOOKING;
import static api.constants.Endpoints.POST_REQUEST;
import static api.specifications.ResponseSpec.responseSpec;
import static api.specifications.ResponseSpec.responseSpecDeleteBooking;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CrudRequestTest extends BaseTestApi {

    private DataProvider dataProvider = new DataProvider();
    private Faker faker = new Faker();
    private String firstname = faker.name().firstName();
    private String updatedFirstname = faker.name().firstName();
    private String updatedLastname = faker.name().lastName();
    private String lastname = faker.name().lastName();
    private String additionalNeeds = faker.commerce().productName();
    private int totalPrice = faker.number().randomDigit();
    private String chekIn = "2018-01-01";
    private String chekOut = "2019-01-01";

    @Description("User should be able to log in")
    @BeforeTest
    public void createToken() {
        CreateTokenPojo createToken =  CreateTokenPojo.builder()
                .username(LOGIN)
                .password(PASSWORD)
                .build();

        Response response = given()
                .body(createToken)
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(responseSpec)
                .extract().response();
        dataProvider.setToken(response.jsonPath().getString("token"));
    }

    @Description("User should be able to create booking")
    @Test
    public void createBooking() {
        Bookingdates bookingdates = Bookingdates.builder()
                .checkin(chekIn)
                .checkout(chekOut)
                .build();

        CreateBookingPojo createBooking = CreateBookingPojo.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(totalPrice)
                .depositpaid(true)
                .bookingdates(bookingdates)
                .additionalneeds(additionalNeeds)
                .build();

        Response response = given()
                .body(createBooking)
                .when()
                .post(PATH_TO_BOOKING)
                .then()
                .spec(responseSpec)
                .extract().response();
        dataProvider.setId(response.jsonPath().getInt("bookingid"));
    }

    @Description("User should be able to get booking")
    @Test
    public void getBooking() {
        given()
                .when()
                .get(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(responseSpec)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("bookingSchema.json"))
                .body("firstname", equalTo(firstname)
                        , "lastname", equalTo(lastname)
                        , "totalprice", equalTo(totalPrice)
                        , "depositpaid", equalTo(true));
    }

    @Description("User should be able to partial update firstname and lastname")
    @Test
    public void partialUpdateBooking() {
        PartialUpdateBookingPojo updateBooking = PartialUpdateBookingPojo.builder()
                .firstname(updatedFirstname)
                .lastname(updatedLastname)
                .build();

        given()
                .header("Cookie", "token=" + dataProvider.getToken())
                .body(updateBooking)
                .when()
                .patch(PATH_TO_BOOKING + dataProvider.getId())
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath("bookingSchema.json"))
                .assertThat()
                .body("firstname", equalTo(updatedFirstname), "lastname", equalTo(updatedLastname));
    }

    @Description("User should be able to partial delete booking")
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