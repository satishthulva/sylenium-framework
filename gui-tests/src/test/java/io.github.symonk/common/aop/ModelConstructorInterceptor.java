package io.github.symonk.common.aop;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class ModelConstructorInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
    log.debug("Attaching model data to framework report");
    methodInvocation.proceed();
    Allure.addAttachment(methodInvocation.getMethod().getClass().toString(), "application/json", methodInvocation.getThis().toString());
    return methodInvocation.proceed();
  }
}
