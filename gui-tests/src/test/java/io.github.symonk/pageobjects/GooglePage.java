package io.github.symonk.pageobjects;

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
    public void searchFor(final String term) {
        searchBox.val(term).pressEnter();
    }

    public void reloadThePage() {
        Selenide.refresh();
    }
}
