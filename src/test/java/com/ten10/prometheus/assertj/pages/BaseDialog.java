package com.ten10.prometheus.assertj.pages;

import com.ten10.prometheus.assertj.util.BasicLogger;
import org.assertj.swing.core.matcher.FrameMatcher;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;

public class BaseDialog {
  FrameFixture parentWindow;
  DialogFixture dialog;

  public BaseDialog(@Nonnull FrameFixture parentWindow, @Nonnull DialogFixture dialog) {
    this.parentWindow = parentWindow;
    this.dialog = dialog;
  }

  public String getTitle() {
    BasicLogger.log("Getting title.");
    return dialog.target().getTitle();
  }

}
