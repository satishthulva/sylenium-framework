package io.github.symonk.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryListener implements IRetryAnalyzer {

  private int attempts = 0;
  private static final int maximumAttempts = 1;

  @Override
  public boolean retry(final ITestResult iTestResult) {
    boolean result = false;
    if (!iTestResult.isSuccess()) {
      if (attempts < maximumAttempts) {
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
