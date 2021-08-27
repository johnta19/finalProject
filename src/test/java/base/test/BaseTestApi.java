package base.test;

import api.specifications.RequestSpec;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTestApi {


    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = RequestSpec.requestSpec;
        RestAssured.filters(new AllureRestAssured());
    }

}
