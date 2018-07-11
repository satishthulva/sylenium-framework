package io.github.symonk.common.helpers.reporting;

import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class ReportHelper implements ReportInteractable {

  private static final String FILE_EXISTS =
      "environment.properties file already exists, deleting it";
  private static final String DUPLICATE_FILE_ERROR =
      "io error occurred when checking for duplicate files";
  private static final String DEFAULT_DIRECTORY = "target\\allure-results\\environment.properties";
  private static final String PROPERTIES_HEADER = "Generated runtime properties";
  private static final String STREAM_CLOSE_ERROR = "Failed to close output stream";
  private static final String INVALID_ARGS =
      "provided arguments do not meet a valid test run, aborting the run";
  private static final String IO_EXCEPTION =
      "IO error occurred when generating or pushing the environment file";

  private final ManagesFrameworkProperties properties;

  public ReportHelper(final ManagesFrameworkProperties properties) {
    this.properties = properties;
  }

  @Override
  public void pushDynamicTestRunPropertiesToReport() {
    generateEnvironmentPropertiesFile();
  }

  private void generateEnvironmentPropertiesFile() {

    final Properties environmentProperties = new Properties();
    properties.getPropertiesAsMap().forEach(environmentProperties::setProperty);

    final FileOutputStream fos;
    try {
      final Path pathToFile = Paths.get(DEFAULT_DIRECTORY);
      if (!removeFileIfExists(pathToFile)) {
        Files.createDirectories(pathToFile.getParent());
      }
      Files.createFile(pathToFile);
      fos = new FileOutputStream(pathToFile.toString());
      environmentProperties.store(fos, PROPERTIES_HEADER);
      fos.close();
    } catch (final IOException exception) {
      abortTheTestRun(IO_EXCEPTION);
    }
  }

  private boolean removeFileIfExists(final Path filePath) {
    try {
      if (Files.exists(filePath)) {
        log.info(FILE_EXISTS);
        Files.delete(filePath);
        return true;
      }
    } catch (final IOException exception) {
      log.error(DUPLICATE_FILE_ERROR, exception);
    }
    return false;
  }

  private void abortTheTestRun(final String message) {
    log.info(INVALID_ARGS);
    throw new IllegalArgumentException(message);
  }
}
