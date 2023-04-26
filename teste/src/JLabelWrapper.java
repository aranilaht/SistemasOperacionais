package src;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelWrapper {

  JLabel lb_base2;
  JPanel myPanel;


  public JLabelWrapper(JLabel childLabel, JPanel applicationPanel) {
    this.lb_base2 = childLabel;
    this.myPanel = applicationPanel;
  }
}


