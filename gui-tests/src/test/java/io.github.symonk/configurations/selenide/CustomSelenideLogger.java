package io.github.symonk.configurations.selenide;

import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSelenideLogger extends SelenideLogger {

  public static void setListenerLogFile(final String testName) {
    CustomListener listener = (CustomListener) listeners.get().get("CustomListener");
    listener.setCurrentLog(testName);
    CustomSelenideLogger.addListener("CustomListener", listener);
  }
}
