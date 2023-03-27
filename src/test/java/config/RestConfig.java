package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.*;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

public class RestConfig implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        RestAssured.requestSpecification = given()
                .baseUri(getProperty("host"))
                .header("Content-Type", ContentType.JSON);
        RestAssured.filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter());

    }

    @Override
    public void onFinish(ISuite suite) {
        RestAssured.reset();
    }
}