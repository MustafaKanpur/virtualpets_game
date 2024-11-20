import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;

public class passcodeInput extends JTextField{

    public passcodeInput() {
        this.setPreferredSize(new Dimension(50,50));
        this.setMaximumSize(new Dimension(50,50));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
