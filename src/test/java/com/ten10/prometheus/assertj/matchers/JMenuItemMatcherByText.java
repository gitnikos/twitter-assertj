package com.ten10.prometheus.assertj.matchers;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.dependency.jsr305.Nonnull;

import javax.swing.*;

/**
 * Created by Ten10 on 13/07/2016.
 */
public class JMenuItemMatcherByText extends GenericTypeMatcher<JMenuItem> {
    String title;

    public JMenuItemMatcherByText(String title) {
      super(JMenuItem.class);
      this.title = title;
    }

    @Override
    protected boolean isMatching(@Nonnull JMenuItem jMenuItem) {
      return title.equals(jMenuItem.getText());
    }

}
