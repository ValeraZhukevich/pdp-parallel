package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Selenide.*;
import static utils.PropertiesReader.getProperty;

@Feature("Search on onliner.by")
public class OnlinerPage {
    private final SelenideElement searchInput = $(".fast-search__input");
    private final SelenideElement category = $("a.category__title");
    private final ElementsCollection productTitles = $$(".schema-product__title span");
    private final SelenideElement productName = $("h1.catalog-masthead__title");

    @Step("Open onliner.by")
    public void openOnliner(){
        open(getProperty("gui.host"));
    }

    @Step("Type '{searchValue}' in search")
    public void typeInSearchField(String searchValue){
        searchInput.setValue(searchValue).pressEnter();
    }

    @Step("Switch to result iFrame")
    public void switchToResults(){
        switchTo().frame($(".modal-iframe"));
        category.click();
    }

    @Step("All results should have text '{productName}'")
    public void allResultsShouldHaveText(String productName) {
        productTitles.should(allMatch("Verify that all results have appropriate values",
                element -> element.getText().toLowerCase().contains(productName.toLowerCase())));
    }
}
