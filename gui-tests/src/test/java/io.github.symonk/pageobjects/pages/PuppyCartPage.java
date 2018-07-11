package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.domain.PuppyOrder;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PuppyCartPage {

    private static final SelenideElement COMPLETE_ADOPTION_BUTTON = $(By.xpath("//*[@id='content']/div[2]/form[1]/div/input"));
    private static final SelenideElement COLLAR_CHECKBOX = $(By.id("collar"));
    private static final SelenideElement TOY_CHECKBOX = $(By.id("toy"));
    private static final SelenideElement CARRIER_CHECKBOX = $(By.id("carrier"));
    private static final SelenideElement VET_CHECKBOX = $(By.id("vet"));
    private static final SelenideElement TOTAL_PRICE = $(By.xpath("//td[@class='total_cell']/h2"));

    public PuppyOrderDetailsPage completeTheAdoption() {
        return completeAdoption();
    }

    public PuppyCartPage orderPriceForAllItemsIsCorrect(final String cost, final PuppyOrder order) {
        selectAppropriateOptions(order);
        TOTAL_PRICE.shouldHave(text(cost));
        return this;
    }

    public PuppyOrderDetailsPage completeTheAdoption(final PuppyOrder puppyOrder) {
        selectAppropriateOptions(puppyOrder);
        return completeTheAdoption();
    }

    private void selectAppropriateOptions(final PuppyOrder puppyOrder) {
        for(OrderOptions option : puppyOrder.getListOfOrderItems()) {
            if(option == OrderOptions.COLLAR) COLLAR_CHECKBOX.setSelected(true);
            if(option == OrderOptions.CHEW_TOY) TOY_CHECKBOX.setSelected(true);
            if(option == OrderOptions.TRAVEL_CARRIER) CARRIER_CHECKBOX.setSelected(true);
            if(option == OrderOptions.FIRST_VET_VISIT) VET_CHECKBOX.setSelected(true);
        }
    }

    private PuppyOrderDetailsPage completeAdoption() {
        COMPLETE_ADOPTION_BUTTON.click();
        return new PuppyOrderDetailsPage();
    }




}
