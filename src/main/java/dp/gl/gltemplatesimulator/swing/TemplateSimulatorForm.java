package dp.gl.gltemplatesimulator.swing;

import com.google.gson.Gson;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import dp.gl.gltemplatesimulator.dto.ArticleDTO;
import dp.gl.gltemplatesimulator.dto.TemplateDTO;
import dp.gl.gltemplatesimulator.service.TemplateService;
import dp.gl.gltemplatesimulator.swing.panel.ArticlesTablePanel;
import dp.gl.gltemplatesimulator.swing.panel.TemplateEditPanel;
import dp.gl.gltemplatesimulator.swing.panel.TemplateListPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Component
public class TemplateSimulatorForm extends JFrame {
    private JPanel rootPanel;
    private JTextArea txtJSON;
    private JButton btnCalculate;
    private JButton btnSave;
    private TemplateListPanel templatesPanel;
    private ArticlesTablePanel articlesPanel;
    private TemplateEditPanel templateEditPanel;
    private JButton btnNew;
    private JButton btnCopy;
    private JButton btnDel;

    @Autowired
    TemplateService templateService;

    TemplateDTO templateDTO = new TemplateDTO();

    public TemplateDTO getTemplateDTO() {
        templateDTO = (TemplateDTO) templateEditPanel.getTemplate();
        templateDTO.setJson_sample_business_agreed(txtJSON.getText());
        return templateDTO;
    }

    public void setTemplateDTO(TemplateDTO templateDTO) {
        this.templateDTO = templateDTO;
        templateEditPanel.setTemplate(templateDTO);
        txtJSON.setText(templateDTO.getJson_sample_business_agreed());
    }

    public TemplateSimulatorForm() {
        super(" شبیه ساز الگوی آرتیکل سند");
        if (rootPanel != null) setContentPane(rootPanel);
        pack();


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                List<TemplateDTO> templates = templateService.findAll();
                templatesPanel.setTemplates(templates);
            }
        });

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    articlesPanel.clearArticles();
                    ArticleDTO articleDTO = templateService.calculateTemplate(getTemplateDTO());
                    if (articleDTO != null) {
                        articlesPanel.addArticle(articleDTO);
                    } else {
                        JOptionPane.showMessageDialog(null, "status_formula template result is false");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateDTO templateDTO = getTemplateDTO();
                boolean isNew = templateDTO.getId() == null ? true : false;
                templateService.saveOrUpdate(getTemplateDTO());
                if (isNew) {
                    templatesPanel.addTemplateAndSelect(templateDTO);
                } else {
                    templatesPanel.changeSelectedTemplate(templateDTO);
                }
                System.out.println("save succeed.");
            }
        });

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTemplateDTO(new TemplateDTO());
            }
        });

        templatesPanel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                TemplateDTO templateDTO = templatesPanel.getSelectedTemplate();
                if (templateDTO != null) {
                    setTemplateDTO(templateDTO);
                }
                Gson gson = new Gson();
                System.out.println("TemplateSimulatorForm.valueChanged:" + gson.toJson(templateDTO));
            }
        });

        btnCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateDTO dto = getTemplateDTO();
                dto.setId(null);
                dto.setName(dto.getName() + "-2");
                setTemplateDTO(dto);
            }
        });
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer templateId = getTemplateDTO().getId();
                templateService.deleteTemplate(templateId);
                templatesPanel.removeSelectedTemplate();
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
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        templateEditPanel = new TemplateEditPanel();
        panel1.add(templateEditPanel.$$$getRootComponent$$$(), new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("ورودی JSON");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 80), null, 0, false));
        txtJSON = new JTextArea();
        scrollPane1.setViewportView(txtJSON);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnNew = new JButton();
        btnNew.setText("New");
        panel2.add(btnNew);
        btnSave = new JButton();
        btnSave.setText("Save");
        panel2.add(btnSave);
        btnCalculate = new JButton();
        btnCalculate.setText("Calculate");
        panel2.add(btnCalculate);
        btnCopy = new JButton();
        btnCopy.setText("Copy");
        panel2.add(btnCopy);
        btnDel = new JButton();
        btnDel.setText("Del");
        panel2.add(btnDel);
        templatesPanel = new TemplateListPanel();
        rootPanel.add(templatesPanel.$$$getRootComponent$$$(), new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        articlesPanel = new ArticlesTablePanel();
        rootPanel.add(articlesPanel.$$$getRootComponent$$$(), new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), null, 1, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
