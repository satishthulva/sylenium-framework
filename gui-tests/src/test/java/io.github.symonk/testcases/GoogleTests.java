package io.github.symonk.testcases;


import com.codeborne.selenide.Condition;
import io.github.symonk.pageobjects.GooglePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Epic("This is an Epic")
@Feature("This is a Feature")
public class GoogleTests extends TestBaseTemplate {

    @BeforeClass(description = "Test Configuration")
    public void beforeClass() {

    }

    @Test(description = "As a user I want to do something")
    @Link("www.google.co.uk")
    @Issue("123")
    @TmsLink("456")
    @Severity(SeverityLevel.CRITICAL)
    @Story("This is a story")
    public void testOne() {
        GooglePage googlePage = open("https://www.google.co.uk", GooglePage.class);
        googlePage.searchFor("simonk");
        $("fail").shouldNot(Condition.visible);
        Allure.addAttachment("My attachment", "datString");
    }

    @Test
    public void testTwo() {
        GooglePage googlePage = open("https://www.thesun.co.uk", GooglePage.class);
    }

    @Test
    public void testThree() {
        GooglePage googlePage = open("https://www.bbc.co.uk", GooglePage.class);
    }

    @AfterClass(description = "Test Teardown")
    public void afterClass() {

    }
}
