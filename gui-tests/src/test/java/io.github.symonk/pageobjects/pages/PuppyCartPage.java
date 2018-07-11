package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PuppyCartPage {

    private static final SelenideElement COMPLETE_ADOPTION_BUTTON = $(By.xpath("//*[@id='content']/div[2]/form[1]/div/input"));


    public PuppyOrderDetailsPage completeTheAdoption() {
        COMPLETE_ADOPTION_BUTTON.click();
        return new PuppyOrderDetailsPage();
    }




}
