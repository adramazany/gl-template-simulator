package dp.gl.gltemplatesimulator.swing;

import javax.swing.*;
import java.awt.*;

public class ProfileMappingForm {
    private JPanel rootPanel;
    private JTable table1;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootPanel.add(panel1, BorderLayout.NORTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, BorderLayout.CENTER);
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
