package api;

import config.RestConfig;
import exception.SortedByIdException;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojo.Episode;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static utils.PropertiesReader.getProperty;

@Listeners(RestConfig.class)
@Feature("Rick & Morty. Episode")
public class EpisodeTest {

    @Test
    @Story("All episodes sorted by id")
    public void allEpisodesSortedById() {
        List<Episode> episodes = new ArrayList<>();
        String page = "1";
        String nextUrl;
        do {
            Response episodeResponse = given()
                    .when()
                    .param("page", page)
                    .get(getProperty("episode"))
                    .then().statusCode(200)
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath("schemas/get_all_episodes.json"))
                    .extract().response();
            nextUrl = episodeResponse.jsonPath().getString("info.next");
            episodes.addAll(episodeResponse.jsonPath().getList("results", Episode.class));
            if (nextUrl != null) page = nextUrl.replaceAll("\\D", "");
            else break;
        } while (true);
        List<Episode> sortedEpisodes = episodes.stream()
                .sorted(comparing(Episode::getName))
                .collect(toList());
        try {
            Assert.assertEquals(sortedEpisodes, episodes);
        } catch (AssertionError error){
            throw new SortedByIdException(error.getMessage());
        }
    }
}
