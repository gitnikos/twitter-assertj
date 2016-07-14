package com.ten10.prometheus.assertj.matchers;

import org.assertj.swing.core.GenericTypeMatcher;

import java.awt.*;

/**
 * Created by Ten10 on 13/07/2016.
 */
public class VisibleFrameByTitle extends GenericTypeMatcher<Frame> {
  String title;

  public VisibleFrameByTitle(String title) {
    super(Frame.class);
    this.title = title;
  }

  @Override
  protected boolean isMatching(Frame frame) {
    return title.equals(frame.getTitle()) && frame.isShowing();
  }

}
