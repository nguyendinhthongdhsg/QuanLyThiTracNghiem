package UI.Component;

import BLL.TestBLL;
import BLL.TopicsBLL;
import DTO.TestDTO;
import DTO.TopicsDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TestManagementUI extends JPanel {
    private TestBLL testBLL;
    private TopicsBLL topicsBLL;
    private JTextField txtSearch;
    private JTable tblTests;
    private DefaultTableModel tableModel;
    
    private JComboBox<String> cbTopics, cbNumQuestions, cbTestTime, cbNumEasy, cbNumMedium, cbNumHard;
    private JTextField txtTestCode, txtTestTitle, txtTestLimit, txtTestDate;
    private JButton btnAdd, btnSearch, btnUpdate, btnDelete, btnReload;

    public TestManagementUI() {
        testBLL = new TestBLL();
        topicsBLL = new TopicsBLL();
        initComponents();
        loadTopicsToComboBox();
        loadTestTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(5, 4, 10, 10));
    
        panelInput.add(new JLabel("Mã bài thi:"));
        txtTestCode = new JTextField();
        panelInput.add(txtTestCode);

        panelInput.add(new JLabel("Tên bài thi:"));
        txtTestTitle = new JTextField();
        panelInput.add(txtTestTitle);

        panelInput.add(new JLabel("Chủ đề:"));
        cbTopics = new JComboBox<>();
        panelInput.add(cbTopics);

        panelInput.add(new JLabel("Số câu hỏi:"));
        cbNumQuestions = new JComboBox<>(new String[]{"20", "30", "40"});
        panelInput.add(cbNumQuestions);

        panelInput.add(new JLabel("Thời gian thi (phút):"));
        cbTestTime = new JComboBox<>(new String[]{"20", "30", "40"});
        panelInput.add(cbTestTime);

        panelInput.add(new JLabel("Số câu dễ:"));
        cbNumEasy = new JComboBox<>(new String[]{"5", "10", "15"});
        panelInput.add(cbNumEasy);

        panelInput.add(new JLabel("Số câu trung bình:"));
        cbNumMedium = new JComboBox<>(new String[]{"5", "10", "15"});
        panelInput.add(cbNumMedium);

        panelInput.add(new JLabel("Số câu khó:"));
        cbNumHard = new JComboBox<>(new String[]{"5", "10", "15"});
        panelInput.add(cbNumHard);

        panelInput.add(new JLabel("Lượt làm bài:"));
        txtTestLimit = new JTextField();
        panelInput.add(txtTestLimit);

        JPanel panelButtons = new JPanel();
        
        btnAdd = new JButton("Thêm bài thi");
        btnDelete = new JButton("Xóa bài thi");
        btnUpdate = new JButton("Cập nhật");
        btnSearch = new JButton("Tìm kiếm");
        btnReload = new JButton("Tải lại");

        panelButtons.add(btnAdd);
        panelButtons.add(btnDelete);
        panelButtons.add(btnSearch);
        panelButtons.add(btnReload);
        panelButtons.add(btnUpdate);
        
        // Cập nhật bảng hiển thị bài thi, thêm cột "Số câu hỏi"
        String[] columnNames = {"ID", "Mã bài thi", "Tên bài thi", "Chủ đề", "Thời gian", "Số câu hỏi", "Số câu dễ", "Số câu TB", "Số câu khó", "Lượt làm bài", "Ngày thi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblTests = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblTests);

        add(panelInput, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addTest());
        btnDelete.addActionListener(e -> deleteTest());
        btnSearch.addActionListener(e -> searchTest());
        btnReload.addActionListener(e -> reloadTestTable()); 
        btnUpdate.addActionListener(e -> updateTest());
    }

    private void loadTopicsToComboBox() {
        List<TopicsDTO> topics = topicsBLL.getAllTopics();
        cbTopics.removeAllItems();
        for (TopicsDTO topic : topics) {
            cbTopics.addItem(topic.getTpTitle());
        }
    }

    private void loadTestTable() {
        tableModel.setRowCount(0);
        List<TestDTO> tests = testBLL.getAllTests();
        for (TestDTO test : tests) {
            int totalQuestions = test.getNumEasy() + test.getNumMedium() + test.getNumDiff();
            tableModel.addRow(new Object[]{
                test.getTestID(),
                test.getTestCode(),
                test.getTestTitle(),
                test.getTpID(),
                test.getTestTime(),
                totalQuestions, // Thêm số câu hỏi vào bảng
                test.getNumEasy(),
                test.getNumMedium(),
                test.getNumDiff(),
                test.getTestLimit(),
                test.getTestDate()
            });
        }
    }
    
    private void reloadTestTable() {
        loadTestTable();
        JOptionPane.showMessageDialog(this, "Dữ liệu đã được tải lại!");
    }

   private void addTest() {
    if (txtTestCode.getText().isEmpty() || txtTestTitle.getText().isEmpty() || txtTestLimit.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    int numEasy = Integer.parseInt((String) cbNumEasy.getSelectedItem());
    int numMedium = Integer.parseInt((String) cbNumMedium.getSelectedItem());
    int numDiff = Integer.parseInt((String) cbNumHard.getSelectedItem());

    int totalQuestions = numEasy + numMedium + numDiff;
    int selectedNumQuestions = Integer.parseInt((String) cbNumQuestions.getSelectedItem());

    if (totalQuestions != selectedNumQuestions) {
        JOptionPane.showMessageDialog(this, "Tổng số câu hỏi không khớp với số lượng đã chọn!");
        return;
    }
    TestDTO test = new TestDTO();
    test.setTestCode(txtTestCode.getText());
    test.setTestTitle(txtTestTitle.getText());
    test.setTpID(cbTopics.getSelectedIndex() + 1);
    test.setTestTime(Integer.parseInt((String) cbTestTime.getSelectedItem()));
    test.setNumEasy(numEasy);
    test.setNumMedium(numMedium);
    test.setNumDiff(numDiff);
    test.setTestLimit(Integer.parseInt(txtTestLimit.getText()));
    test.setTestDate(java.time.LocalDate.now().toString());
    test.setTestStatus(1);

    if (testBLL.addTest(test)) {
        JOptionPane.showMessageDialog(this, "Thêm bài thi thành công!");
        loadTestTable();
    } else {
        JOptionPane.showMessageDialog(this, "Thêm bài thi thất bại!");
    }
    }

 
   private String[] loadTopicsArray() {
    List<TopicsDTO> topics = topicsBLL.getAllTopics();
    String[] topicsArray = new String[topics.size()];
    for (int i = 0; i < topics.size(); i++) {
        topicsArray[i] = topics.get(i).getTpTitle();
    }
    return topicsArray;
    }
    private void searchTest() {
        String keyword = JOptionPane.showInputDialog("Nhập từ khóa tìm kiếm:");
        if (keyword != null && !keyword.trim().isEmpty()) {
            tableModel.setRowCount(0);
            List<TestDTO> tests = testBLL.searchTests(keyword);
            for (TestDTO test : tests) {
                tableModel.addRow(new Object[]{
                    test.getTestID(),
                    test.getTestCode(),
                    test.getTestTitle(),
                    test.getTpID(),
                    test.getTestTime(),
                    test.getNumEasy(),
                    test.getNumMedium(),
                    test.getNumDiff(),
                    test.getTestLimit(),
                    test.getTestDate()
                });
            }
        }
    }

    private void deleteTest() {
        int selectedRow = tblTests.getSelectedRow();
        if (selectedRow >= 0) {
            int testID = (int) tblTests.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bài thi này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (testBLL.deleteTest(testID)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadTestTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một bài thi để xóa!");
        }
    }
    
//    private void updateTest() {
//    int selectedRow = tblTests.getSelectedRow();
//    if (selectedRow >= 0) {
//        int testID = (int) tblTests.getValueAt(selectedRow, 0);
//        String testCode = (String) tblTests.getValueAt(selectedRow, 1);
//        String testTitle = (String) tblTests.getValueAt(selectedRow, 2);
//        int topicID = (int) tblTests.getValueAt(selectedRow, 3);
//        int testTime = (int) tblTests.getValueAt(selectedRow, 4);
//        int numEasy = Integer.parseInt(tblTests.getValueAt(selectedRow, 6).toString());
//        int numMedium = Integer.parseInt(tblTests.getValueAt(selectedRow, 7).toString());
//        int numDiff = Integer.parseInt(tblTests.getValueAt(selectedRow, 8).toString());
//        int testLimit = Integer.parseInt(tblTests.getValueAt(selectedRow, 9).toString());
//        String testDate = (String) tblTests.getValueAt(selectedRow, 10);
//
//        JTextField txtTestCode = new JTextField(testCode);
//        JTextField txtTestTitle = new JTextField(testTitle);
//        JComboBox<String> cbTopics = new JComboBox<>(loadTopicsArray());
//        cbTopics.setSelectedIndex(topicID - 1);
//        JComboBox<String> cbTestTime = new JComboBox<>(new String[]{"20", "30", "40"});
//        cbTestTime.setSelectedItem(String.valueOf(testTime));
//        JComboBox<String> cbNumEasy = new JComboBox<>(new String[]{"5", "10", "15"});
//        cbNumEasy.setSelectedItem(String.valueOf(numEasy));
//        JComboBox<String> cbNumMedium = new JComboBox<>(new String[]{"5", "10", "15"});
//        cbNumMedium.setSelectedItem(String.valueOf(numMedium));
//        JComboBox<String> cbNumHard = new JComboBox<>(new String[]{"5", "10", "15"});
//        cbNumHard.setSelectedItem(String.valueOf(numDiff));
//        JTextField txtTestLimit = new JTextField(String.valueOf(testLimit));
//
//        // Tạo một JPanel để chứa các trường thông tin
//        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
//        panel.add(new JLabel("Mã bài thi:"));
//        panel.add(txtTestCode);
//        panel.add(new JLabel("Tên bài thi:"));
//        panel.add(txtTestTitle);
//        panel.add(new JLabel("Chủ đề:"));
//        panel.add(cbTopics);
//        panel.add(new JLabel("Thời gian thi (phút):"));
//        panel.add(cbTestTime);
//        panel.add(new JLabel("Số câu dễ:"));
//        panel.add(cbNumEasy);
//        panel.add(new JLabel("Số câu trung bình:"));
//        panel.add(cbNumMedium);
//        panel.add(new JLabel("Số câu khó:"));
//        panel.add(cbNumHard);
//        panel.add(new JLabel("Lượt làm bài:"));
//        panel.add(txtTestLimit);
//
//        int option = JOptionPane.showConfirmDialog(this, panel, "Chỉnh sửa bài thi", JOptionPane.OK_CANCEL_OPTION);
//        if (option == JOptionPane.OK_OPTION) {
//            TestDTO test = new TestDTO();
//            test.setTestID(testID);
//            test.setTestCode(txtTestCode.getText());
//            test.setTestTitle(txtTestTitle.getText());
//            test.setTpID(cbTopics.getSelectedIndex() + 1);
//            test.setTestTime(Integer.parseInt(cbTestTime.getSelectedItem().toString()));
//            test.setNumEasy(Integer.parseInt(cbNumEasy.getSelectedItem().toString()));
//            test.setNumMedium(Integer.parseInt(cbNumMedium.getSelectedItem().toString()));
//            test.setNumDiff(Integer.parseInt(cbNumHard.getSelectedItem().toString()));
//            test.setTestLimit(Integer.parseInt(txtTestLimit.getText()));
//            test.setTestDate(testDate);  
//            if (testBLL.updateTest(test)) {
//                JOptionPane.showMessageDialog(this, "Cập nhật bài thi thành công!");
//                loadTestTable();
//            } else {
//                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
//            }
//        }
//    } else {
//        JOptionPane.showMessageDialog(this, "Chọn một bài thi để cập nhật!");
//    }
//    }
    private void updateTest() {
    int selectedRow = tblTests.getSelectedRow();
    if (selectedRow >= 0) {
        int testID = (int) tblTests.getValueAt(selectedRow, 0);
        String testCode = (String) tblTests.getValueAt(selectedRow, 1);
        String testTitle = (String) tblTests.getValueAt(selectedRow, 2);
        int topicID = (int) tblTests.getValueAt(selectedRow, 3);
        int testTime = (int) tblTests.getValueAt(selectedRow, 4);
        int numEasy = Integer.parseInt(tblTests.getValueAt(selectedRow, 6).toString());
        int numMedium = Integer.parseInt(tblTests.getValueAt(selectedRow, 7).toString());
        int numDiff = Integer.parseInt(tblTests.getValueAt(selectedRow, 8).toString());
        int testLimit = Integer.parseInt(tblTests.getValueAt(selectedRow, 9).toString());
        String testDate = (String) tblTests.getValueAt(selectedRow, 10);

        JTextField txtTestCode = new JTextField(testCode, 20);
        JTextField txtTestTitle = new JTextField(testTitle, 20);
        JComboBox<String> cbTopics = new JComboBox<>(loadTopicsArray());
        cbTopics.setSelectedIndex(topicID - 1);
        JComboBox<String> cbTestTime = new JComboBox<>(new String[]{"20", "30", "40"});
        cbTestTime.setSelectedItem(String.valueOf(testTime));
        JComboBox<String> cbNumEasy = new JComboBox<>(new String[]{"5", "10", "15"});
        cbNumEasy.setSelectedItem(String.valueOf(numEasy));
        JComboBox<String> cbNumMedium = new JComboBox<>(new String[]{"5", "10", "15"});
        cbNumMedium.setSelectedItem(String.valueOf(numMedium));
        JComboBox<String> cbNumHard = new JComboBox<>(new String[]{"5", "10", "15"});
        cbNumHard.setSelectedItem(String.valueOf(numDiff));
        JTextField txtTestLimit = new JTextField(String.valueOf(testLimit), 20);

        // Tạo một JPanel để chứa các trường thông tin
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Thêm các thành phần vào panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Mã bài thi:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTestCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Tên bài thi:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTestTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Chủ đề:"), gbc);
        gbc.gridx = 1;
        panel.add(cbTopics, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Thời gian thi (phút):"), gbc);
        gbc.gridx = 1;
        panel.add(cbTestTime, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Số câu dễ:"), gbc);
        gbc.gridx = 1;
        panel.add(cbNumEasy, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Số câu trung bình:"), gbc);
        gbc.gridx = 1;
        panel.add(cbNumMedium, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Số câu khó:"), gbc);
        gbc.gridx = 1;
        panel.add(cbNumHard, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Lượt làm bài:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTestLimit, gbc);

        int option = JOptionPane.showConfirmDialog(this, panel, "Chỉnh sửa bài thi", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            TestDTO test = new TestDTO();
            test.setTestID(testID);
            test.setTestCode(txtTestCode.getText());
            test.setTestTitle(txtTestTitle.getText());
            test.setTpID(cbTopics.getSelectedIndex() + 1);
            test.setTestTime(Integer.parseInt(cbTestTime.getSelectedItem().toString()));
            test.setNumEasy(Integer.parseInt(cbNumEasy.getSelectedItem().toString()));
            test.setNumMedium(Integer.parseInt(cbNumMedium.getSelectedItem().toString()));
            test.setNumDiff(Integer.parseInt(cbNumHard.getSelectedItem().toString()));
            test.setTestLimit(Integer.parseInt(txtTestLimit.getText()));
            test.setTestDate(testDate);  
            if (testBLL.updateTest(test)) {
                JOptionPane.showMessageDialog(this, "Cập nhật bài thi thành công!");
                loadTestTable();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Chọn một bài thi để cập nhật!");
    }
}
}
