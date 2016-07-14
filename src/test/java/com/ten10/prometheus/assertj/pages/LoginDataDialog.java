package com.ten10.prometheus.assertj.pages;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JTextComponentMatcher;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.driver.FrameDriver;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.AbstractWindowFixture;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import static org.assertj.swing.core.matcher.JTextComponentMatcher.withName;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class LoginDataDialog extends BaseDialog {

  private static GenericTypeMatcher<JTextComponent> ACCESS_TOKEN_SECRET = withName("AccessTokenSecret");
  private static GenericTypeMatcher<JTextComponent> APIKEYSECRET = withName("APISecret");
  private static GenericTypeMatcher<JTextComponent> LOGINNAME = withName("Login Data Name");
  private static GenericTypeMatcher<JTextComponent> ACCESS_TOKEN = withName("AccessToken");
  private static GenericTypeMatcher<JTextComponent> APIKEY = withName("APIKey");
  private static GenericTypeMatcher<JButton> ADD_ACCOUNT_BUTTON = withText("Add Account");

  public LoginDataDialog(@Nonnull FrameFixture parentWindow, @Nonnull DialogFixture dialog) {
    super(parentWindow, dialog);
  }

  public void setName(String name) {
    dialog.textBox(LOGINNAME).setText(name);
  }

  public void setAPIKey(String apiKey) {
    dialog.textBox(APIKEY).setText(apiKey);
  }

  public void setAPIKeySecret(String apiKeySecret) {
    dialog.textBox(APIKEYSECRET).setText(apiKeySecret);
  }

  public void setAccessToken(String accessToken) {
    dialog.textBox(ACCESS_TOKEN).setText(accessToken);
  }

  public void setAccessTokenSecret(String accessTokenSecret) {
    dialog.textBox(ACCESS_TOKEN_SECRET).setText(accessTokenSecret);
  }

  public HomePage clickAddAccountButton() {
    dialog.button(ADD_ACCOUNT_BUTTON).click();
    return new HomePage(parentWindow);
  }
}
