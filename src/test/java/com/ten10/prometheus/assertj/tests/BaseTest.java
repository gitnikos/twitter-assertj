package com.ten10.prometheus.assertj.tests;

import com.github.twitterswingsample.view.frames.MainFrame;
import com.ten10.prometheus.assertj.listeners.BaseTestListener;
import com.ten10.prometheus.assertj.matchers.VisibleFrameByTitle;
import com.ten10.prometheus.assertj.util.BasicLogger;
import com.ten10.prometheus.assertj.util.Constants;
import com.ten10.prometheus.assertj.util.TestData;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

@Listeners({BaseTestListener.class})
public class BaseTest extends AssertJSwingTestngTestCase {

  private static final long WAIT_BEFORE_CLOSE = 1;//000l;

  protected TestData data;

  protected FrameFixture window;

  @BeforeMethod
  public void beforeMethod(Method method) {
    try {
      data.loadDataFromFile(getClassDataFilename() + ".csv", method.getName() + ".csv");
    } catch (IOException e) {
      BasicLogger.log(e.getMessage());
    }
  }

  @Override
  protected void onSetUp() {
    try {
      data = new TestData(Constants.APPLICATION_NAME + ".csv");
    } catch (IOException e) {
      BasicLogger.log(e.getMessage());
    }

    FailOnThreadViolationRepaintManager.install();

    application(MainFrame.class).start();
    window = findFrame(new VisibleFrameByTitle(data.get("Title"))).withTimeout(Constants.DEFAULT_TIMEOUT).using(this.robot());
  }

  private String getClassDataFilename() {
    String className = getClass().getName();
    return className.substring(className.lastIndexOf('.') + 1);
  }

  @Override
  public void onTearDown() {
    try {
      Thread.sleep(WAIT_BEFORE_CLOSE);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    window.close();
  }
}
