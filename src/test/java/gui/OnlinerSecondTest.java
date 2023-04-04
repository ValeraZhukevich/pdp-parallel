package gui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("Onliner.by")
public class OnlinerSecondTest extends BaseTest {

    @DataProvider
    public Object[][] productsTwo() {
        return new String[][]{
                {"Зубная щётка"},
                {"Холодильник"},
                {"микроволновка"},
        };
    }
    @Test(dataProvider = "productsTwo")
    @Story("Search two bunch products: ")
    void secondSearchProduct(String product){
        onlinerPage.openOnliner();
        onlinerPage.typeInSearchField(product);
        onlinerPage.switchToResults();
        onlinerPage.allResultsShouldHaveText(product);
    }

}
