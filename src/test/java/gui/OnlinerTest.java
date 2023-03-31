package gui;

import config.GuiConfig;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static utils.PropertiesReader.getProperty;

@Listeners(GuiConfig.class)
public class OnlinerTest {

    @DataProvider
    public Object[][] products() {
        return new String[][]{
                {"Робот пылесос"},
                {"Телевизор"},
                {"наушники"},
                {"велосипед"},
                {"весы"}
        };
    }

    @Test(dataProvider = "products")
    @Story("Search product: {product}")
    void searchProduct(String product){
        open(getProperty("gui.host"));
        $(".fast-search__input").val(product).pressEnter();
        sleep(2000);
    }
}
