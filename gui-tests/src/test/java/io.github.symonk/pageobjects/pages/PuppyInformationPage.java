package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PuppyInformationPage {

    private static final SelenideElement ADOPT_ME_BUTTON = $(By.xpath("//*[@id='content']/div[2]/div/form/div/input[1]"));

    public PuppyCartPage adoptPuppy() {
        ADOPT_ME_BUTTON.click();
        return new PuppyCartPage();
    }

}
