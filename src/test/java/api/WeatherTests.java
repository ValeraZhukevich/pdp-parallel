package api;

import config.RestConfig;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(RestConfig.class)
public class WeatherTests {

    @DataProvider
    public Object[][] cities() {
        return new String[][]{
                {"Ottawa"},
                {"Hrodna"},
                {"Berlin"}
        };
    }
    @Test(groups = {"API"}, dataProvider = "cities")
    public void getCity(String city) {
        System.out.println(city);
    }
}
