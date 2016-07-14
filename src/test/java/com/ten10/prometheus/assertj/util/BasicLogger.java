package com.ten10.prometheus.assertj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicLogger {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

  public static void log(String message) {
    System.out.println(dateFormat.format(new Date()) + ": " + message);
  }
}
