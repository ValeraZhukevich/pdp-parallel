package api;

import config.RestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojo.Character;
import pojo.Location;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.PropertiesReader.getProperty;

@Listeners(RestConfig.class)
@Feature("Rick & Morty. Location")
public class LocationTest {

    @DataProvider
    public Object[][] ids() {
        return new String[][]{
                {"1", "Earth (C-137)"},
                {"2", "Abadango"},
                {"3", "Citadel of Ricks"},
                {"4", "Worldender's lair"},
                {"5", "Anatomy Park"},
        };
    }

    @Test(dataProvider = "ids")
    @Story("Get location by id")
    public void getLocationById(String id, String name) {
        Location location = given()
                .param("id", id)
                .when()
                .get(getProperty("location"))
                .then().statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("schemas/get_location.json"))
                .extract().response().as(Location.class);

        Assert.assertEquals(location.getName(), name);
    }
}
