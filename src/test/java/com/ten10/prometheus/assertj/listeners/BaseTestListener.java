package com.ten10.prometheus.assertj.listeners;

import com.ten10.prometheus.assertj.util.ScreenshotAgent;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.List;

public class BaseTestListener implements IInvokedMethodListener {
  @Override
  public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

  }

  @Override
  public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    if (!iTestResult.isSuccess()) {
      ScreenshotAgent.takeScreenshot(iInvokedMethod.getTestMethod().getMethodName());
    }
  }
}
