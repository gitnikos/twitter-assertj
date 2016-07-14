package com.ten10.prometheus.assertj.pages;

import com.ten10.prometheus.assertj.util.BasicLogger;
import org.assertj.swing.core.Robot;
import org.assertj.swing.dependency.jsr305.Nonnull;
import org.assertj.swing.fixture.FrameFixture;

import java.awt.*;

public class BasePage {
  FrameFixture window;

  public BasePage(@Nonnull FrameFixture window) {
    this.window = window;
  }

  public String getTitle() {
    BasicLogger.log("Getting title.");
    return window.target().getTitle();
  }

  public void maximise() {
    BasicLogger.log("Maximising.");
    window.maximize();
  }
}
