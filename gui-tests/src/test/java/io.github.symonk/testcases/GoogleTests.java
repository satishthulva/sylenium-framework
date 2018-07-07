package io.github.symonk.testcases;


import io.github.symonk.configurations.dependency_injection.FrameworkModule;
import io.github.symonk.pageobjects.GooglePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Epic("This is an Epic")
@Feature("This is a Feature")
@Guice(modules = FrameworkModule.class)
public class GoogleTests extends TestBaseTemplate {

    @BeforeClass(description = "Test Configuration")
    public void beforeClass() {

    }

    @Test(description = "As a user I want to do something")
    @Story("This is a story")
    @Link("www.google.co.uk")
    @Issue("123")
    @TmsLink("456")
    @Severity(SeverityLevel.CRITICAL)
    public void testOne() {
        open("/", GooglePage.class)
                .searchFor("simon")
                .someElement()
                .should(visible);
    }

    @Test(description = "Description Two")
    @Story("Another Story")
    @Link("www.bbc.co.uk")
    @Issue("999")
    @TmsLink("888")
    @Severity(SeverityLevel.CRITICAL)
    public void testTwo() {
        open("/", GooglePage.class)
                .searchFor("jenkins")
                .someElement()
                .should(visible);
    }

    @AfterClass(description = "Test Teardown")
    public void afterClass() {

    }
}
