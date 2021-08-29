package api.tests;

import api.data.provider.BookingData;
import api.pojo.Bookingdates;
import api.pojo.CreateBookingPojo;
import api.pojo.CreateTokenPojo;
import api.pojo.PartialUpdateBookingPojo;
import base.test.BaseTestApi;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static api.constants.Endpoints.PATH_TO_BOOKING;
import static api.constants.Endpoints.POST_REQUEST;
import static api.specifications.ResponseSpec.responseSpec;
import static api.specifications.ResponseSpec.responseSpecDeleteBooking;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static properties.ConfigProperties.getApiCredentialsProperties;

public class CrudRequestTest extends BaseTestApi {

    private BookingData bookingData;
    private Faker faker;
    public static String loginApi;
    public static String passwordApi;
    private String firstname;
    private String updatedFirstname;
    private String updatedLastname;
    private String lastname;
    private String additionalNeeds;
    private int totalPrice;
    private String chekIn;
    private String chekOut;

    @Description("User should be able to log in")
    @BeforeTest
    public void createToken() {
        getApiCredentialsProperties();
        bookingData = new BookingData();
        faker = new Faker();
        firstname = faker.name().firstName();
        updatedFirstname = faker.name().firstName();
        updatedLastname = faker.name().lastName();
        lastname = faker.name().lastName();
        additionalNeeds = faker.commerce().productName();
        totalPrice = faker.number().randomDigit();
        chekIn = "2018-01-01";
        chekOut = "2019-01-01";

        CreateTokenPojo createToken =  CreateTokenPojo.builder()
                .username(loginApi)
                .password(passwordApi)
                .build();

        Response response = given()
                .body(createToken)
                .when()
                .post(POST_REQUEST)
                .then()
                .spec(responseSpec)
                .extract().response();
        bookingData.setToken(response.jsonPath().getString("token"));
    }

    @Description("User should be able to create booking")
    @BeforeClass
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
        bookingData.setId(response.jsonPath().getInt("bookingid"));
    }

    @Description("User should be able to get booking")
    @Test
    public void getBooking() {
        given()
                .when()
                .get(PATH_TO_BOOKING + bookingData.getId())
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
                .header("Cookie", "token=" + bookingData.getToken())
                .body(updateBooking)
                .when()
                .patch(PATH_TO_BOOKING + bookingData.getId())
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
                .header("Cookie", "token=" + bookingData.getToken())
                .when()
                .delete(PATH_TO_BOOKING + bookingData.getId())
                .then()
                .spec(responseSpecDeleteBooking);
    }
}