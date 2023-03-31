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

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.PropertiesReader.getProperty;

@Listeners(RestConfig.class)
@Feature("Rick & Morty. Character")
public class CharacterTest {

    @DataProvider
    public Object[][] names() {
        return new String[][]{
                {"Rick Sanchez"},
                {"Morty Smith"},
                {"Beth Smith"},
                {"Jerry Smith"},
                {"Summer Smith"}
        };
    }

    @Test(dataProvider = "names")
    @Story("Get character by name")
    public void getCharacterByName(String name) {
        List<Character> characters = given()
                .queryParam("name", name)
                .when()
                .get(getProperty("character"))
                .then().statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("schemas/get_characters.json"))
                .extract().response().jsonPath().getList("results", Character.class);

        characters.forEach(character -> {
            try {
                Assert.assertEquals(character.getName(), "name");
            } catch (AssertionError e) {
                Allure.addAttachment("The name is different: ", character.toString());
                throw e;
            }
        });
    }

}
