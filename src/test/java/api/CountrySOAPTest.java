package api;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

@Feature("Country SOAP")
public class CountrySOAPTest {

    @Test
    @Story("Get full country's info by ISO code")
    public void getFullCountryInfoByISO() throws IOException {
        RestAssured.filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter());
        FileInputStream fis = new FileInputStream("src/test/resources/soap/country.xml");
        Response response = given()
                .baseUri(getProperty("soap.host"))
                .header("Content-Type", "text/xml")
                .body(IOUtils.toString(fis, "UTF-8"))
                .when()
                .post(getProperty("country.info"))
                .then().statusCode(200)
                .extract().response();

        String capital = response.xmlPath()
                .getString("Envelope.Body.FullCountryInfoResponse.FullCountryInfoResult.sCapitalCity");
        Assert.assertEquals(capital, "Berlin");
    }

}
