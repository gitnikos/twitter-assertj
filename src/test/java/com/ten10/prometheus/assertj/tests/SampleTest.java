package com.ten10.prometheus.assertj.tests;

import com.ten10.prometheus.assertj.pages.HomePage;
import com.ten10.prometheus.assertj.pages.LoginDataDialog;
import org.assertj.swing.core.BasicComponentPrinter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

  @Test
  public void sampleTest1() {
    HomePage homePage = new HomePage(window);
    homePage.maximise();

    BasicComponentPrinter.printerWithCurrentAwtHierarchy().printComponents(System.out, window.target());

    LoginDataDialog loginDataDialog = homePage.clickMenuAccountsAdd();
    loginDataDialog.setName(data.get("Name"));
    loginDataDialog.setAPIKey(data.get("APIKey"));
    loginDataDialog.setAPIKeySecret(data.get("APIKeySecret"));
    loginDataDialog.setAccessToken( data.get("AccessToken"));
    loginDataDialog.setAccessTokenSecret(data.get("AccessTokenSecret"));

    homePage = loginDataDialog.clickAddAccountButton();
    Assert.assertEquals(homePage.getTitle(), data.get("Expected_HomePageTitle"), "Title of homepage is not correct");
  }

  @Test(enabled = false)
  public void shouldCopyTextInLabelWhenClickingButton() {
    window.textBox("textToCopy").enterText("Some random text");
    window.button("copyButton").click();
    window.label("copiedText").requireText("Some random text");
  }
}
