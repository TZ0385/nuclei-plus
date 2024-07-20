package com.g3g4x5x6.nuclei.panel.template;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.g3g4x5x6.nuclei.NucleiConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.g3g4x5x6.nuclei.ultils.TextAreaUtils.createTextArea;


@Slf4j
public class CopyToTemplatePanel extends JPanel {
    private final JToolBar toolBar;
    private final RSyntaxTextArea textArea;

    private final JButton pasteBtn = new JButton(new FlatSVGIcon("icons/menu-paste.svg"));
    private final JButton compileBtn = new JButton(new FlatSVGIcon("icons/compile.svg"));


    public CopyToTemplatePanel() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(800, 900));

        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        initToolBar();

        textArea = createTextArea();
        textArea.setSyntaxEditingStyle("text/markdown");

        String templateStr = "# 模板要求\n" +
                "\n" +
                "1. poc模板除外，禁止回答其他内容\n" +
                "2. “path” 字段必须由模板变量 “BaseURL” 拼接路径\n" +
                "\n" +
                "\n" +
                "\n" +
                "# HTTP流量\n" +
                "\n" +
                "```HTTP-FLOW\n" +
                "\n" +
                "```\n" +
                "\n" +
                "\n" +
                "请根据 “HTTP流量” 以及 “模板要求”，编写一个poc模板。";
        textArea.setText(templateStr);
        resetTextAreaPopupMenu();
        RTextScrollPane rTextScrollPane = new RTextScrollPane(textArea);
        rTextScrollPane.setBorder(null);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(rTextScrollPane, BorderLayout.CENTER);
    }

    private void initToolBar() {
        pasteBtn.setToolTipText("粘贴HTTP流");
        compileBtn.setToolTipText("生成PoC模板");

        toolBar.add(pasteBtn);
        toolBar.add(compileBtn);
    }

    @SneakyThrows
    private void resetTextAreaPopupMenu() {
        JPopupMenu popupMenu = textArea.getPopupMenu();

        JMenu insertMenu = new JMenu("匹配器");
        insertMenu.setIcon(new FlatSVGIcon("icons/regexSelected.svg"));

        JMenu extractorMenu = new JMenu("提取器");
        extractorMenu.setIcon(new FlatSVGIcon("icons/traceInto.svg"));

        JMenu helperMenu = new JMenu("辅助函数");
        helperMenu.setIcon(new FlatSVGIcon("icons/function.svg"));

        popupMenu.insert(new JSeparator(), 0);
        popupMenu.insert(helperMenu, 0);
        popupMenu.insert(extractorMenu, 0);
        popupMenu.insert(insertMenu, 0);

        configTextAreaPopupMenu(insertMenu);

        textArea.setComponentPopupMenu(popupMenu);
    }

    private void configTextAreaPopupMenu(JMenu insertMenu) {
        insertMenu.add(new AbstractAction("大于等于") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}