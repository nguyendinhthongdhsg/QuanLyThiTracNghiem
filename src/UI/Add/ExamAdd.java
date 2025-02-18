/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Add;

/**
 *
 * @author Huynh Thu
 */

import BLL.ExamsBLL;
import DTO.ExamsDTO;
import UI.Component.ExamManagementUI;
import javax.swing.*;
import java.awt.*;

public class ExamAdd extends JDialog {
    private final ExamsBLL examsBLL;
    private final ExamManagementUI parentUI;
    private JCheckBox autoGenerateCheckBox;
    private JTextField txtTestCode, txtExOrder, txtExCode, txtExQuestIDs;
    private JButton btnSave, btnCancel;

    public ExamAdd(JFrame parent, String title, ExamManagementUI parentUI, ExamsBLL examsBLL) {
        super(parent, "THÊM ĐỀ THI", true);
        this.examsBLL = examsBLL;
        this.parentUI = parentUI;

        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setSize(450, 400);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("THÊM ĐỀ THI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(0, 51, 102));
        lblTitle.setPreferredSize(new Dimension(450, 40));

        // Panel chứa form nhập
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Mã bài thi
        panel.add(createLabel("Mã bài thi"), gbc);
        gbc.gridy++;
        txtTestCode = new JTextField(20);
        panel.add(txtTestCode, gbc);

        // Thứ tự mã đề
        gbc.gridy++;
        panel.add(createLabel("Thứ tự mã đề"), gbc);
        gbc.gridy++;
        txtExOrder = new JTextField(20);
        panel.add(txtExOrder, gbc);

        // Mã đề thi
        gbc.gridy++;
        panel.add(createLabel("Mã đề thi"), gbc);
        gbc.gridy++;
        txtExCode = new JTextField(20);
        panel.add(txtExCode, gbc);

        // Danh sách câu hỏi
        gbc.gridy++;
        panel.add(createLabel("Danh sách câu hỏi"), gbc);
        gbc.gridy++;
        txtExQuestIDs = new JTextField(20);
        panel.add(txtExQuestIDs, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        btnSave.setBackground(new Color(0, 51, 102));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(lblTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveExam());
        btnCancel.addActionListener(e -> dispose());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }
    
    private String generateExamCode() {
    String[] codes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    int randomIndex = (int) (Math.random() * codes.length);
    return codes[randomIndex] + (1000 + (int) (Math.random() * 9000));
}


private void saveExam() {
    String testCode = txtTestCode.getText().trim();
    String exOrder = txtExOrder.getText().trim();
    String exQuesIDs = txtExQuestIDs.getText().trim();
    String exCode = txtExCode.getText().trim();

    if (testCode.isEmpty() || exOrder.isEmpty() || exQuesIDs.isEmpty() || exCode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    ExamsDTO newExam = new ExamsDTO(0, testCode, exOrder, exCode, exQuesIDs);
    if (examsBLL.addExam(newExam)) {
        JOptionPane.showMessageDialog(this, "Thêm đề thi thành công!");
        parentUI.loadExamData();
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Thêm đề thi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

}

