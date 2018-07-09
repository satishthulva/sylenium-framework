package io.github.symonk.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryListener implements IRetryAnalyzer {

  private static final int MAXIMUM_ATTEMPTS = 1;
  private int attempts = 0;

  @Override
  public boolean retry(final ITestResult iTestResult) {
    boolean result = false;
    if (!iTestResult.isSuccess()) {
      if (attempts < MAXIMUM_ATTEMPTS) {
        attempts++;
        iTestResult.setStatus(ITestResult.FAILURE);
        result = true;
      } else {
        iTestResult.setStatus(ITestResult.FAILURE);
      }
    } else {
      iTestResult.setStatus(ITestResult.SUCCESS);
    }
    return result;
  }
}
