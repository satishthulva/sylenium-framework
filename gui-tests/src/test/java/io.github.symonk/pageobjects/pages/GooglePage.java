package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class GooglePage {

    @FindBy(name = "q")
    private SelenideElement searchBox;

    @Step("Searching for a string {0}")
    public GooglePage searchFor(final String term) {
        log.info("Searching for a term");
        searchBox.val(term).pressEnter();
        return this;
    }

    @Step("Reloading the page")
    public void reloadThePage() {
        Selenide.refresh();
    }

    @Step("Returning some random element")
    public SelenideElement someElement() {
        return searchBox;
    }
}
