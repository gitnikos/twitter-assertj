package com.ten10.prometheus.assertj.util;

import org.assertj.swing.image.ScreenshotTaker;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.io.File.separator;
import static org.assertj.core.util.Files.currentFolder;

public class ScreenshotAgent {

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy-HHmmss");
  private static String imageFolderPath;
  private static ScreenshotTaker screenshotTaker = null;

  private static ScreenshotTaker getScreenshotTaker() {
    if (screenshotTaker == null) {
      screenshotTaker = new ScreenshotTaker();

      try {
        String currentFolderPath = currentFolder().getCanonicalPath();

        File imageFolder = new File(currentFolderPath + separator + "failed-gui-tests");
        imageFolderPath = imageFolder.getCanonicalPath() + separator;
      } catch (IOException e) {
        BasicLogger.log(e.getMessage());
      }
    }

    return screenshotTaker;
  }

  public static void takeScreenshot(String testName) {
    getScreenshotTaker().saveDesktopAsPng(imageFolderPath + dateFormat.format(new Date()) + testName + Constants.SCREENSHOT_FILE_EXTENSION);
  }

  public static void takeScreenshot(Component c, String testName) {
    getScreenshotTaker().saveComponentAsPng(c, imageFolderPath + dateFormat.format(new Date()) + testName + Constants.SCREENSHOT_FILE_EXTENSION);
  }
}
