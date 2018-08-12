[![Build Status](https://api.travis-ci.org/symonk/selenide-testng-allure2-test-automation-framework.svg?branch=master)](https://travis-ci.org/symonkselenide-testng-allure2-test-automation-framework)
[![MIT License](http://img.shields.io/badge/license-MIT-green.svg)](https://github.com/symonk/selenide-testng-allure2-test-automation-framework/blob/master/LICENSE)
![Free](https://img.shields.io/badge/free-open--source-green.svg)
[![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=io.symonk.github%3Aselenide-framework&metric=alert_status)](https://sonarcloud.io/dashboard?id=io.symonk.github%3Aselenide-framework)


### Selenide-TestNG-Allure2-Framework :flags:

The aim of this project is simple, provide a powerful test automation harness for testing web applications with java.  Because test automation (especially) at the ui layer is plagued with bad practice
I would like to start by outlining a few things of what **NOT** to use this harness for.  If such a license existed that would ban you from doing the following, I would apply it to this repository...

- Writing pointless short tests.  This framework should be used to test end to end flows of your application under no isolation of the stack.
- Using this test automation harness as your only automated tests.  Focus on unit and integration tests as a primary method of coverage.
- Writing trashy locators, especially long winded xpath (using chrome `find-xpath` capabilities etc.)
- Keep page objects simple, componentise other areas of page(s) into their own page objects, remember a page object != a web application page.
- Managing test data and teardown using the ui directly, I understand sometimes your legacy monolith is hard to work with, but prioritise spiking in services to help you.
- Not encapsulating user actions in page objects.  Please do not write methods for each individual action, group them into a public facing common sense api.
- Use fluency in your page objects where possible for cleaner tests.
- Using field injection with any sort of DI mechanism, I will find you and I will kill you.


Now that we have that out of the way, I would also like that you create atleast 1 pull request to the selenide project when using this framework.  You can find the repository here:

https://github.com/codeborne/selenide

### The framework stack :flags:

# :triangular_flag_on_post: Framework stack

| Technology | Description | Link
| ------------- | ------------- | -------------
| **Java**  | Programming language)  | [Java](https://java.com/en/download/)
| **TestNG**  | Test framework for test ecosystem  | [TestNG](http://testng.org/doc/)
| **Selenium WebDriver**  | Browser manipulation  | [Selenium](https://www.seleniumhq.org/)
| **Rest Assured**  | Restful API DSL  | [Rest Assured](http://rest-assured.io/)
| **AssertJ**  | Powerful assertsions library  | [AssertJ](http://joel-costigliola.github.io/assertj/)
| **Maven**  | Build compilation, Dependency mgmt & test execution  | [Maven](https://maven.apache.org/)
| **JFaker** | Data Generator (when applicable) | [JFaker](https://github.com/sgianelli/JFaker)
| **Logback** | Logging framework | [Logback](https://logback.qos.ch/)
| **Bespoke Reporting** | Dashboard of test results and debug data | [Allure Reporting](https://github.com/allure-framework/allure2)
| **Guice** | Dependency injection | [Guice](https://github.com/google/guice)
| **Automation Assistant** | Framework assistance by me | [Automation Assistant](https://github.com/symonk/Automation-Assistant)
| **Selenium Grid** | Distributed testing | [Selenium Grid](https://www.seleniumhq.org/docs/07_selenium_grid.jsp)
| **Lombok** | Removal of boilerplate code | [Lombok](https://projectlombok.org/download)
| **Owner** | Properties management | [@Owner](http://owner.aeonbits.org/)
| **Selenide** | Selenium wrapper | [@Selenide](http://http://selenide.org)



### Contact me:

[![Linked In](https://img.shields.io/badge/Add%20Me%20On-LinkedIn-orange.svg)](https://www.linkedin.com/in/simonk09/)
[![Linked In](https://img.shields.io/badge/Join%20Me%20On-Slack-orange.svg)](https://testersio.slack.com)



