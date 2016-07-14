package com.ten10.prometheus.assertj.pages;

import com.ten10.prometheus.assertj.actions.AccountAction;
import com.ten10.prometheus.assertj.matchers.JMenuItemMatcherByText;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.core.matcher.DialogMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JTextComponentMatcher;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.driver.DialogDriver;
import org.assertj.swing.driver.FrameDriver;
import org.assertj.swing.driver.JMenuItemMatcher;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.AbstractWindowFixture;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JMenuItemFixture;

import javax.swing.*;
import java.awt.*;


public class HomePage extends BasePage {

  private static DialogMatcher LOGIN_DIALOG_TILE = DialogMatcher.withTitle("Login Data");
  private static JMenuItemMatcherByText MENU_ACCOUNTS_ADD = new JMenuItemMatcherByText("Add account ...");

  public HomePage(@Nonnull FrameFixture window) {
    super(window);
  }

  public LoginDataDialog clickMenuAccountsAdd() {
    JMenuItemFixture menuItemFixture = window.menuItem(MENU_ACCOUNTS_ADD);

    menuItemFixture.click();
    return new LoginDataDialog(window, window.dialog(LOGIN_DIALOG_TILE));

  }
}
