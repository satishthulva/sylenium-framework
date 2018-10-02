## HacktoberFesters! -> Hello! I will review all PR's within 30 minutes of opening them throughout the day (UK) -> PLEASE COMMENT ON THE ISSUE AND I WILL ASSIGN IT TO YOU TO AVOID SOMEONE ELSE DOING IT BEFORE YOU OPEN A PR, ASSIGNED ISSUES WILL ONLY BE MERGED TO AVOID PEOPLE WASTING THEIR TIME.

[![Build Status](https://api.travis-ci.org/symonk/sylenium-framework.svg?branch=master)](https://travis-ci.org/symonk/sylenium-framework)
[![MIT License](http://img.shields.io/badge/license-MIT-green.svg)](https://github.com/symonk/selenide-testng-allure2-test-automation-framework/blob/master/LICENSE)
![Free](https://img.shields.io/badge/free-open--source-green.svg)
[![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=io.symonk.github%3Aselenide-framework&metric=alert_status)](https://sonarcloud.io/dashboard?id=io.symonk.github%3Aselenide-framework)
[![Linked In](https://img.shields.io/badge/Add%20Me%20On-LinkedIn-green.svg)](https://www.linkedin.com/in/simonk09/)
[![Linked In](https://img.shields.io/badge/Join%20Me%20On-Slack-green.svg)](https://testersio.slack.com)

## Sylenium-framework :flags:

The aim of this project is simple, provide a powerful test automation harness for testing web applications with java.  Because test automation (especially) at the ui layer is plagued with bad practice
I would like to start by outlining a few things of what **NOT** to use this harness for.  If such a license existed that would ban you from doing the following, I would apply it to this repository...

---

### :crossed_flags: Writing pointless short tests :crossed_flags:

This framework should be used to test end to end flows of your application under no isolation of the stack.  Writing tests for simple ui
functionality such as buttons being clickable, dropdown having values etc should be done by component tests.  Modern frameworks make this a piece of cake (angular, react etc).

---

### :crossed_flags: Only automating at the ui layer :crossed_flags:

Using this test automation harness as your only automated tests.  Focus on unit and integration tests as a primary method of coverage.

---

### :crossed_flags: Writing garbage locators :crossed_flags:

Using google chrome `copy-as-xpath` and using it directly etc.  Favour adding unique identifiers to your frontend to aid with automation.  `data-` attributes etc can be extremely useful.
Using complex xpaths will end in hassle later, and please account for the page state being different later with parallel tests running.  Your useless xpath locator finding row 3 in a table won't 
work later when 10 parallel tests have flooded the table data!

---

### :crossed_flags: Overcomplicating page objects :crossed_flags:

The beauty of this framework is we have **NO** driver or page factory code in our page objects, its all handled behind the scenes using custom reflection and java dynamic proxies, coupled with smart webdriver management.
Keep your page objects simple, exposing a fluent interface for the tests to consume.  Always remember a page object is **NOT** equal to a page `!=`.  A page object can encapsulate a simple
dropdown on a page, which can be injected or reused as part of another page object.  `KISS`.

---

### :crossed_flags: Managing test data poorly :crossed_flags:

Managing test data using this harness itself through the ui for example, terrible practice.  Hopefully you have or can get access to some cool restful services to help you manage the data.  Managing test data
is most certainly not easy and becomes a behemoth over time.  The debate of randomising data is a long going one.  Please dont use your browsers for prepping and tearing down test data.  I don't even like direct database manipulation either,
from experience you will spend too long doing maintenance.

---

### :crossed_flags: Running sequential tests :crossed_flags:

Write your tests with parallelisation in mind.  Independent tests aren't enough, consider cross contamination (`system wide settings`) contaminating your tests at runtime.  For example if test A modifies
a system wide setting it can impact other tests, even though they are not remotely reliant on each other.  Multi tenancy applications can really help with this, otherwise run a `@NotThreadSafe` run at the end of your run.
If you are running one test at a time, again I will find you and I will kill you.

---

### :crossed_flags: Pointless noise in page objects :crossed_flags:

Page objects should encapsulate user actions grouped together, not individual actions that interact on a per element basis.  Writing a `Login();` method is better than writing 3 methods to do the steps of logging in.

---

### :crossed_flags: Using field injection :crossed_flags:

Using field injection with any sort of DI mechanism, I will find you and I will kill you. Yes its easier, but it sucks.  Its gimmicky magic, decreases class testability, masks design errors with large classes.  When you inject into the field often you will 
not see beefy constructors that can prompt you to do some refactoring.

---

### :crossed_flags: Not using CI :crossed_flags:

Running tests locally is easy, get your tests into a scalable distributed execution mechanism within a CI pipeline. **note:** running locally is very useful, we can guard our feature branches that way. CI can help guard master PRs and production

---

### Please contribute!

Now that we have that out of the way, I would also like that you create atleast 1 pull request to the selenide project when using this framework.  You can find the repository here:

https://github.com/codeborne/selenide

and ofcourse, open PRs here

---

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


# :triangular_flag_on_post: Getting started?

In order to get started you should clone this repository and remove all test(s) around the Puppy adoption webpage, we use this just to demonstrate the testing capabilities.
Alternatively you can fork this repo and work from there.

```
install git bash
install jdk8+
install maven 3.5.2+
type -> git clone https://github.com/symonk/selenide-testng-allure2-test-automation-framework.git
run ui_regression_parallel_chrome.bat to see the tests running in parallel! or alternatively:
import the project into intellij
install lombok intellij plugin and enable annotation-processing in intellij (important)
install the allure command line tool for reporting capabilities (important)
```

---

# :triangular_flag_on_post: Framework functionality?
This framework provides a serious amount of functionality right out of the box.

```
-> Driver and Page Factory free page objects
-> Localisation support for multi lingual applications, simply use the localisation helper to read your values from .properties files.
-> Powerful DSL powered by selenide to manage driver manipulation and powerful assertions.
-> Robust test automation properties out of the box.
-> Bespoke, beautiful reporting powered by Allure2, simply add @Step annotations in your page objects and thats it! or @Step anywhere for that matter.
-> Robust mechanisms for webdriver management, we handle all downloading, setup and execution at the switch of a property.
-> Run locally or distributed on selenium grid with the flip of a switch.
-> Customised test-ng listeners to capture and manage test flow out of the box.
-> Comes complete with jenkins example scripts.
-> Multi threaded logging per test.
-> 100% easily configurable settings for allure, properties, logging, webdriver management.
-> Dependency injection capabilities powered by google Guice.
-> Wealth of custom helpers, exceptions and annotations.
-> Jira (cloud) integration to automatically manage test awareness (@wip).
-> Slack integration to automatically manage notifications (@wip).
-> Customised test failure data (stacktrace, screenshot, pagesource, logs) automatically in the report!
-> Capturing .har performance data using a proxy (BrowserMob) available at the flip of a switch!
-> Selenide custom conditions and listeners.
-> Example tests and page objects to give you an example of how to get started
-> Easy out of the box element containers (Custom page objects like tables etc) powered by Selenide.
-> Wired together for you, using maven.
-> Supports 2 languages out of the box, with easy capabilities to add more.
-> Simple bat files to run locally straight away.
-> Maven module for performance testing powered by Maven Jmeter.
-> Maven module for api-testing, includes some example tests.
```

### :triangular_flag_on_post: Running performance tests?
To execute the example performance tests, simply launch a command prompt in the `/performance directory` where the `pom.xml` file resides.  Then execute:

`mvn verify`

all `.jmx` files will execute and the report will be available under `/target/jmeter/reports`

Complimentary `.bat` file has been added to make this even easier on a windows environment.  An example of the report can be seen below:

![Performance](https://github.com/symonk/sylenium-framework/blob/master/.resources/.images/performance-test.jpg)

### :triangular_flag_on_post: Running user interface tests?
When executing your tests it is highly recommended to encompass them as part of a CI pipeline.  If your 

- placeholder
- placeholder
- placeholder

![User Interface](https://github.com/symonk/sylenium-framework/blob/master/.resources/.images/report-dashboard.png)

### :triangular_flag_on_post: Running service or api tests?

- placeholder
- placeholder
- placeholder

### :triangular_flag_on_post: How can I configured Testrail?

- placeholder
- placeholder
- placeholder

### :triangular_flag_on_post: How can I configure slack?
As of default, slack will output total test pass percentage, and names of tests which fail in real time.  This is easily configured by doing the following:

- Go to your_team.slack.com/services/new
- Search for incoming webHook and click in Add
- Choose channel to post and press add incoming webhooks integration
- Set the webhook url in the framework property (@Default empty)
- Set the slack notifications enabled framework property to true (@Default false)
- Both of these properties are required, they can be set at runtime using standard maven -Dslack.enabled etc

Framework properties are found under `gui-tests/src/test/resources/framework.properties` or can be passed in at runtime.

### :triangular_flag_on_post: How can I configure Jira cloud?

- placeholder
- placeholder
- placeholder
