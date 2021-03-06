package dp.gl.gltemplatesimulator;

import dp.gl.gltemplatesimulator.swing.ScriptSimulatorForm;
import dp.gl.gltemplatesimulator.swing.VoucherSimulatorForm;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

@SpringBootApplication
public class GlTemplateSimulatorApp extends JFrame {
    private JButton btnSimScript;
    private JButton btnSimArticle;
    private JButton btnSimVoucher;
    private JButton btnExit;
    private JPanel rootPanel;

    static ConfigurableApplicationContext ctx;


    public static void main(String[] args) {
        ctx = new SpringApplicationBuilder(GlTemplateSimulatorApp.class)
                .headless(false)
                .run(args);
        EventQueue.invokeLater(() -> {
            GlTemplateSimulatorApp ex = (GlTemplateSimulatorApp) ctx.getBean(GlTemplateSimulatorApp.class);
            ex.setVisible(true);
        });
    }

    public GlTemplateSimulatorApp() {
        super("سیستم شبیه ساز الگوهای اسناد حسابداری دیجی پی ...");
        if (rootPanel != null) setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnSimScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnSimScript.actionPerformed!!!!!");
//                Test test = testService.findOne();
//                button1.setText(test.getName());
                ScriptSimulatorForm form = (ScriptSimulatorForm) ctx.getBean(ScriptSimulatorForm.class);
                form.setVisible(true);
            }
        });
        btnSimArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnSimArticle.actionPerformed!!!!!");
                TemplateSimulatorForm form = (TemplateSimulatorForm) ctx.getBean(TemplateSimulatorForm.class);
                form.setVisible(true);
            }
        });
        btnSimVoucher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnSimVoucher.actionPerformed!!!!!");
                VoucherSimulatorForm form = (VoucherSimulatorForm) ctx.getBean(VoucherSimulatorForm.class);
                form.setVisible(true);

            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnExit.actionPerformed!!!!!");
                System.exit(1);
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
        rootPanel.setLayout(new GridBagLayout());
        Font rootPanelFont = this.$$$getFont$$$(null, -1, 22, rootPanel.getFont());
        if (rootPanelFont != null) rootPanel.setFont(rootPanelFont);
        btnSimArticle = new JButton();
        btnSimArticle.setEnabled(true);
        Font btnSimArticleFont = this.$$$getFont$$$(null, -1, 20, btnSimArticle.getFont());
        if (btnSimArticleFont != null) btnSimArticle.setFont(btnSimArticleFont);
        btnSimArticle.setText("شبیه ساز یک آرتیکل سند");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(btnSimArticle, gbc);
        btnSimVoucher = new JButton();
        Font btnSimVoucherFont = this.$$$getFont$$$(null, -1, 20, btnSimVoucher.getFont());
        if (btnSimVoucherFont != null) btnSimVoucher.setFont(btnSimVoucherFont);
        btnSimVoucher.setText("شبیه ساز یک سند");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(btnSimVoucher, gbc);
        btnExit = new JButton();
        Font btnExitFont = this.$$$getFont$$$(null, -1, 20, btnExit.getFont());
        if (btnExitFont != null) btnExit.setFont(btnExitFont);
        btnExit.setText("خروج");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(btnExit, gbc);
        btnSimScript = new JButton();
        Font btnSimScriptFont = this.$$$getFont$$$(null, -1, 20, btnSimScript.getFont());
        if (btnSimScriptFont != null) btnSimScript.setFont(btnSimScriptFont);
        btnSimScript.setText("شبیه ساز یک اسکریپت");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(btnSimScript, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}



