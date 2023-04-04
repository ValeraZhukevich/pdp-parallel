package gui;

import config.GuiConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.OnlinerPage;

@Feature("Onliner.by")
@Listeners(GuiConfig.class)
public class OnlinerTest extends BaseTest {

    @DataProvider
    public Object[][] products() {
        return new String[][]{
                {"Робот-пылесос"},
                {"Телевизор"},
                {"наушники"},
                {"велосипед"},
                {"весы"}
        };
    }

    @Test(dataProvider = "products")
    @Story("Search product: ")
    void searchProduct(String product){
        onlinerPage.openOnliner();
        onlinerPage.typeInSearchField(product);
        onlinerPage.switchToResults();
        onlinerPage.allResultsShouldHaveText(product);
    }
}
