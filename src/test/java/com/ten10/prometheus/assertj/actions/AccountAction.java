package com.ten10.prometheus.assertj.actions;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JTextComponentFixture;

import javax.swing.*;

public class AccountAction extends BaseAction {

  public void addAccount(FrameFixture window, Robot robot, String name, String apiKey, String apiSecret, String accessToken, String accessTokenSecret) {

    DialogFixture dialogFixture = WindowFinder.findDialog(new GenericTypeMatcher<JDialog>(JDialog.class) {
      protected boolean isMatching(JDialog dialog) {
        return "Login Data".equals(dialog.getTitle());
      }
    }).withTimeout(5000).using(robot);












  }
}
