package io.github.symonk.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IExecutionListener;

@Slf4j
public class TestExecutionListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        log.info("test started!");
    }

    @Override
    public void onExecutionFinish() {
        log.info("test completed!");
    }
}
