package dp.gl.gltemplatesimulator.swing;

import dp.gl.gltemplatesimulator.dto.ArticleDTO;
import dp.gl.gltemplatesimulator.service.TemplateService;
import dp.gl.gltemplatesimulator.swing.panel.ArticlesTablePanel;
import dp.gl.gltemplatesimulator.util.NVL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class VoucherSimulatorForm extends JFrame {
    private JPanel rootPanel;
    private JTextArea txtJSON;
    private JTextField txtEventId;
    private JButton btnCalculate;
    private ArticlesTablePanel articlesPanel;

    @Autowired
    TemplateService templateService;


    public VoucherSimulatorForm() {
        super(" شبیه ساز الگوی سند");
        if (rootPanel != null) setContentPane(rootPanel);
        pack();


        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    articlesPanel.clearArticles();
                    List<ArticleDTO> articles = templateService.calculateAllTemplates(NVL.getInt(txtEventId.getText()), txtJSON.getText());
                    articlesPanel.addArticles(articles);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }

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
        panel1.setLayout(new GridBagLayout());
        rootPanel.add(panel1, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setText("ورودی JSON");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label1, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(32);
        scrollPane1.setVerticalScrollBarPolicy(22);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        txtJSON = new JTextArea();
        txtJSON.setText("{\"sellerid\" : 1 , \"buyerid\" : 1 , \"loantypeid\" : 1 ,\"amount\":1000}");
        scrollPane1.setViewportView(txtJSON);
        final JLabel label2 = new JLabel();
        label2.setText("eventId");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label2, gbc);
        txtEventId = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtEventId, gbc);
        btnCalculate = new JButton();
        btnCalculate.setText("Calculate");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(btnCalculate, gbc);
        articlesPanel = new ArticlesTablePanel();
        rootPanel.add(articlesPanel.$$$getRootComponent$$$(), BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}