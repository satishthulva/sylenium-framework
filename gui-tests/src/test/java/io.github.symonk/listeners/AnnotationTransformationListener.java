package io.github.symonk.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformationListener implements IAnnotationTransformer {

  @Override
  public void transform(
      final ITestAnnotation iTestAnnotation,
      final Class aClass,
      final Constructor constructor,
      final Method method) {

    iTestAnnotation.setRetryAnalyzer(RetryListener.class);
  }
}
