package UI.Component;

import UI.Edit.ExamEdit;
import BLL.ExamsBLL;
import BLL.TestBLL;
import DTO.ExamsDTO;
import DTO.TestDTO;
import UI.Add.ExamAdd;
import helper.ExportDocx;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;
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
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(220, 230, 250));
        
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createCompoundBorder(
        searchField.getBorder(), 
        BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        JButton searchButton = createButton("Search", "icon/search.png");
        JButton addButton = createButton("Add", "icon/add.png");
        JButton editButton = createButton("Edit", "icon/edit.png");
        JButton deleteButton = createButton("Delete", "icon/delete.png");
        JButton exportButton = createButton("Export", "icon/export.png");

        searchButton.addActionListener(e -> searchExam());
        addButton.addActionListener(e -> addExam());
        editButton.addActionListener(e -> editExam());
        deleteButton.addActionListener(e -> deleteExam());
        exportButton.addActionListener(e -> exportExam());

        topPanel.add(new JLabel("Tìm kiếm"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(exportButton);

        String[] columnNames = {"ID", "Mã bài thi", "Mã đề", "Danh sách câu hỏi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(tableModel);
        examTable.setRowHeight(30);
        examTable.setSelectionBackground(new Color(173, 216, 230));
        examTable.setGridColor(Color.LIGHT_GRAY);
        examTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JTableHeader tableHeader = examTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setBackground(new Color(65, 105, 225));
        tableHeader.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(examTable);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, String iconPath) {
        URL iconURL = getClass().getClassLoader().getResource(iconPath);
        if (iconURL == null) {
            System.err.println("Không tìm thấy icon: " + iconPath);
            return new JButton(text);
        }
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton button = new JButton(text, new ImageIcon(img));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        return button;
    }

    public void loadExamData() {
        tableModel.setRowCount(0);
        List<ExamsDTO> exams = examsBLL.getAllExams();
        if (exams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có đề thi nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (ExamsDTO exam : exams) {
                tableModel.addRow(new Object[]{
                    exam.getExamId(),
                    exam.getTestCode(),
                    exam.getExCode(),
                    exam.getExQuestIDs()
                });
            }
        }
    }

    private void searchExam() {
        String keyword = searchField.getText().trim();
        tableModel.setRowCount(0);
        List<ExamsDTO> exams = examsBLL.searchExam(keyword);

        if (exams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bài thi nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (ExamsDTO exam : exams) {
                tableModel.addRow(new Object[]{
                    exam.getExamId(),
                    exam.getTestCode(),
                    exam.getExCode(),
                    exam.getExQuestIDs()
                });
            }
        }
    }
    private void addExam() {
        ExamAdd form = new ExamAdd((JFrame) SwingUtilities.getWindowAncestor(this), "Thêm đề thi", this, examsBLL);
        form.setVisible(true);
    }

    private void editExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đề thi để chỉnh sửa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int examId = (int) tableModel.getValueAt(selectedRow, 0);
        ExamsDTO exam = examsBLL.getExamById(examId);

        if (exam != null) {
            ExamEdit editDialog = new ExamEdit((JFrame) SwingUtilities.getWindowAncestor(this), exam, examsBLL);
            editDialog.setVisible(true);
            loadExamData();
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy đề thi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đề thi để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int examId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa đề thi này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (examsBLL.deleteExam(examId)) {
                JOptionPane.showMessageDialog(this, "Xóa đề thi thành công!");
                loadExamData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa đề thi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void exportExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đề thi để xuất!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int examId = (int) tableModel.getValueAt(selectedRow, 0);
        ExamsDTO exam = examsBLL.getExamById(examId);
        TestBLL testBLL = new TestBLL();
        TestDTO test = testBLL.getTestByCode(exam.getTestCode());
        ExportDocx export = new ExportDocx();
        boolean checkExport = export.exportDeThi(test);
    }
}
