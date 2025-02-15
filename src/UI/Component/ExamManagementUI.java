package UI.Component;

import BLL.ExamsBLL;
import DTO.ExamsDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExamManagementUI extends JPanel {
    private final ExamsBLL examsBLL;
    private DefaultTableModel tableModel;
    private JTable examTable;
    private JTextField searchField;

    public ExamManagementUI() {
        examsBLL = new ExamsBLL();
        initComponents();
        loadExamData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        searchButton.addActionListener(e -> searchExam());
//        addButton.addActionListener(e -> addExam());
        editButton.addActionListener(e -> editExam());
        deleteButton.addActionListener(e -> deleteExam());

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        // Cập nhật cột để bao gồm examId
        String[] columnNames = {"Exam ID", "Test Code", "Order", "Exam Code", "Question IDs"};
        tableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(examTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadExamData() {
        tableModel.setRowCount(0);
        List<ExamsDTO> exams = examsBLL.getAllExams();
        for (ExamsDTO exam : exams) {
            tableModel.addRow(new Object[]{
                exam.getExamId(),  // Thêm examId
                exam.getTestCode(),
                exam.getExOrder(),
                exam.getExCode(),
                exam.getExQuesIDs()
            });
        }
    }

    private void searchExam() {
        String keyword = searchField.getText().trim();
        tableModel.setRowCount(0);
        List<ExamsDTO> exams = examsBLL.searchExam(keyword);
        for (ExamsDTO exam : exams) {
            tableModel.addRow(new Object[]{
                exam.getExamId(),  // Hiển thị examId
                exam.getTestCode(),
                exam.getExOrder(),
                exam.getExCode(),
                exam.getExQuesIDs()
            });
        }
    }

//    private void addExam() {
//        ExamAdd addExam = new ExamAdd((JFrame) SwingUtilities.getWindowAncestor(this), true, this, examsBLL);
//        addExam.setVisible(true);
//    }

  private void editExam() {
    int selectedRow = examTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an exam to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int examId = (int) tableModel.getValueAt(selectedRow, 0);
    ExamsDTO exam = examsBLL.getExamById(examId);

    if (exam != null) {
//        ExamFormDialog form = new ExamFormDialog(SwingUtilities.getWindowAncestor(this), "Edit Exam", exam);
//        form.setVisible(true);
        loadExamData();
    } else {
        JOptionPane.showMessageDialog(this, "Exam not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void deleteExam() {
    int selectedRow = examTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an exam to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }
    int examId = (int) tableModel.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this exam?", "Confirm", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        if (examsBLL.deleteExam(examId)) {
            JOptionPane.showMessageDialog(this, "Exam deleted successfully!");
            loadExamData();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete exam.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}
