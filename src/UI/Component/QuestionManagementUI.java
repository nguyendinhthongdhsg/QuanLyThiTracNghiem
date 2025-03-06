package UI.Component;

import BLL.QuestionsBLL;
import BLL.TopicsBLL;
import DTO.AnswersDTO;
import DTO.QuestionsDTO;
import DTO.TopicsDTO;
import helper.ImportExcel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class QuestionManagementUI extends JPanel {

    private QuestionsBLL questionsBLL = new QuestionsBLL();
    private TopicsBLL topicsBLL = new TopicsBLL();

    private JTextField txtQuestionContent, txtSearch;
    private JComboBox<String> cbLevel, cbTopic;
    private JTable tblQuestions;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch, btnReload, btnImport;
    private JButton btnChooseImage;
    private JLabel lblImagePath, lblImagePreview;
    private String selectedImagePath = "";

    public QuestionManagementUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 600));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtQuestionContent = new JTextField(20);
        cbLevel = new JComboBox<>(new String[]{"Dễ", "Trung bình", "Khó"});
        cbTopic = new JComboBox<>();
        loadTopics();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelTop.add(new JLabel("Nội dung:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelTop.add(txtQuestionContent, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelTop.add(new JLabel("Cấp độ:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelTop.add(cbLevel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelTop.add(new JLabel("Chủ đề:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelTop.add(cbTopic, gbc);

        btnChooseImage = new JButton("Chọn ảnh");
        lblImagePath = new JLabel("Chưa chọn ảnh");

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelTop.add(new JLabel("Hình ảnh:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelTop.add(lblImagePath, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panelTop.add(btnChooseImage, gbc);

        lblImagePreview = new JLabel();
        lblImagePreview.setPreferredSize(new Dimension(100, 100));
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelTop.add(new JLabel("Xem trước:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelTop.add(lblImagePreview, gbc);

        JPanel panelButtons = new JPanel();
        btnAdd = new JButton("Thêm");
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/icon/add.png")));
        btnEdit = new JButton("Sửa");
        btnEdit.setIcon(new ImageIcon(getClass().getResource("/icon/edit.png")));
        btnDelete = new JButton("Xóa");
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/icon/delete.png")));
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setIcon(new ImageIcon(getClass().getResource("/icon/search.png")));
        btnReload = new JButton("Tải lại");
        btnReload.setIcon(new ImageIcon(getClass().getResource("/icon/reload.png")));
        btnImport = new JButton("Nhập excel");
        btnImport.setIcon(new ImageIcon(getClass().getResource("/icon/xls.png")));

        txtSearch = new JTextField(15);

        panelButtons.add(txtSearch);
        panelButtons.add(btnSearch);
        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnReload);
        panelButtons.add(btnImport);
        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nội dung", "Chủ đề", "Mức độ", "Thêm Câu Trả Lời", "Ảnh"}, 0);

        tblQuestions = new JTable(tableModel);
        tblQuestions.getColumn("Thêm Câu Trả Lời").setCellRenderer(new ButtonRenderer());
        tblQuestions.getColumn("Thêm Câu Trả Lời").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tblQuestions.getColumn("Ảnh").setCellRenderer(new ImageRenderer());

        tblQuestions.setRowHeight(35);
        tblQuestions.setFont(new Font("Arial", Font.PLAIN, 14));
        tblQuestions.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblQuestions.getTableHeader().setBackground(new Color(52, 73, 94));
        tblQuestions.getTableHeader().setForeground(Color.WHITE);
        tblQuestions.setGridColor(new Color(189, 195, 199));

        JScrollPane scrollPane = new JScrollPane(tblQuestions);

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        loadQuestions();

        // Gắn sự kiện cho các nút
        btnAdd.addActionListener(e -> addQuestion());
        btnEdit.addActionListener(e -> editQuestion());
        btnDelete.addActionListener(e -> deleteQuestion());
        btnSearch.addActionListener(e -> searchQuestion());
        btnReload.addActionListener(e -> loadQuestions());
        btnChooseImage.addActionListener(e -> chooseImage());
        btnImport.addActionListener(e -> importExcelQuestion());
        
        // Bắt sự kiện chọn dòng trong bảng để hiển thị ảnh
        tblQuestions.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblQuestions.getSelectedRow();
            if (selectedRow != -1) {
                String imagePath = (String) tblQuestions.getValueAt(selectedRow, 5);
                displayImage(imagePath);
            }
        });
        tblQuestions.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblQuestions.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tblQuestions.getValueAt(selectedRow, 0);
                    QuestionsDTO question = questionsBLL.getQuestionById(id);

                    txtQuestionContent.setText(question.getqContent());
                    cbLevel.setSelectedItem(question.getqLevel());
                    cbTopic.setSelectedItem(getTopicTitle(question.getqTopicID()));

                    selectedImagePath = question.getqPictures();
                    lblImagePath.setText(selectedImagePath);
                    displayImage(selectedImagePath);
                }
            }
        });
    }

    // Load danh sách câu hỏi từ database
    private void loadQuestions() {
        tableModel.setRowCount(0);
        for (QuestionsDTO q : questionsBLL.getAllQuestions()) {
            tableModel.addRow(new Object[]{
                q.getqID(),
                q.getqContent(),
                getTopicTitle(q.getqTopicID()),
                q.getqLevel(),
                "➕ Thêm Trả Lời",
                q.getqPictures()
            });
        }
    }
    // render ảnh
    static class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                String imagePath = (String) value;
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(img));
            } else {
                setIcon(null);
            }
            return this;
        }
    }

    // 
    static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("➕ Thêm Trả Lời");
            setForeground(Color.WHITE);
            setBackground(new Color(41, 128, 185));
            setFont(new Font("Arial", Font.BOLD, 12));
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }
    // 

    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private int selectedQuestionID;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Thêm Câu Trả Lời");
            button.addActionListener(this::openAnswersManagement);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            selectedQuestionID = (int) table.getValueAt(row, 0);
            return button;
        }

        private void openAnswersManagement(ActionEvent e) {
            if (selectedQuestionID > 0) {
                new AnswersManagement((Frame) SwingUtilities.getWindowAncestor(button), selectedQuestionID).setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(button, "Vui lòng chọn một câu hỏi trước!");
            }
            fireEditingStopped();
        }

    }
    // Load danh sách chủ đề từ database

    private void loadTopics() {
        cbTopic.removeAllItems();
        for (TopicsDTO topic : topicsBLL.getAllTopics()) {
            cbTopic.addItem(topic.getTpTitle());
        }
    }

    // Chọn ảnh từ máy tính
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            lblImagePath.setText(selectedImagePath);
            displayImage(selectedImagePath);
        }
    }

    // Hiển thị ảnh
    private void displayImage(String path) {
        if (path == null || path.isEmpty()) {
            lblImagePreview.setIcon(null);
        } else {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblImagePreview.setIcon(new ImageIcon(img));
        }
    }

    // Lấy tên chủ đề từ ID
    private String getTopicTitle(int topicID) {
        for (TopicsDTO topic : topicsBLL.getAllTopics()) {
            if (topic.getTpID() == topicID) {
                return topic.getTpTitle();
            }
        }
        return "Không xác định";
    }

    // Thêm câu hỏi mới
    private void addQuestion() {
        String content = txtQuestionContent.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nội dung không được để trống!");
            return;
        }
        String level = (String) cbLevel.getSelectedItem();
        int topicIndex = cbTopic.getSelectedIndex();
        if (topicIndex == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chủ đề!");
            return;
        }
        int topicID = topicsBLL.getAllTopics().get(topicIndex).getTpID();
        String imagePath = (selectedImagePath == null || selectedImagePath.isEmpty()) ? null : selectedImagePath;
        QuestionsDTO question = new QuestionsDTO(0, content, imagePath, topicID, level, (byte) 1, new ArrayList<>());
        if (questionsBLL.addQuestion(question)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            reloadUI();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi!");
        }
    }

    // Sửa câu hỏi
    private void editQuestion() {
        int selectedRow = tblQuestions.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để sửa!");
            return;
        }
        int id = (int) tblQuestions.getValueAt(selectedRow, 0);
        String content = txtQuestionContent.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nội dung câu hỏi không được để trống!");
            return;
        }
        String level = (String) cbLevel.getSelectedItem();
        int topicIndex = cbTopic.getSelectedIndex();
        if (topicIndex == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chủ đề!");
            return;
        }
        // Lấy danh sách chủ đề từ BLL để lấy đúng ID
        List<TopicsDTO> topics = topicsBLL.getAllTopics();
        int topicID = topics.get(topicIndex).getTpID();
        QuestionsDTO oldQuestion = questionsBLL.getQuestionById(id);
        String imagePath = (selectedImagePath == null || selectedImagePath.isEmpty())
                ? oldQuestion.getqPictures()
                : selectedImagePath;

        QuestionsDTO question = new QuestionsDTO(id, content, imagePath, topicID, level, (byte) 1, new ArrayList<>());
        if (questionsBLL.updateQuestion(question)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            reloadUI();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật!");
        }
    }

    // Xóa câu hỏi
    private void deleteQuestion() {
        int selectedRow = tblQuestions.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một câu hỏi để xóa!");
            return;
        }
        int id = (int) tblQuestions.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION && questionsBLL.deleteQuestion(id)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadQuestions();
        }
    }

    // Tìm kiếm câu hỏi
    private void searchQuestion() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }
        List<QuestionsDTO> searchResults = questionsBLL.searchQuestion(keyword);
        tableModel.setRowCount(0);
        for (QuestionsDTO q : searchResults) {
            tableModel.addRow(new Object[]{q.getqID(), q.getqContent(), q.getqLevel(), getTopicTitle(q.getqTopicID())});
        }
        if (searchResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
        }
    }

    private void reloadUI() {
        loadQuestions();
        loadTopics();
        txtQuestionContent.setText("");
        cbLevel.setSelectedIndex(0);
        if (cbTopic.getItemCount() > 0) {
            cbTopic.setSelectedIndex(0);
        }
        selectedImagePath = "";
        lblImagePath.setText("Chưa chọn ảnh");
        lblImagePreview.setIcon(null);
    }
    
    public void importExcelQuestion() {
        // Mở hộp thoại chọn file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Lọc chỉ hiển thị file .xlsx
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            ImportExcel importExcel = new ImportExcel();
            File selectedFile = fileChooser.getSelectedFile();
            List<QuestionsDTO> questionListNew = importExcel.readFileQuestion(selectedFile);
            for(QuestionsDTO question : questionListNew) {
                questionsBLL.addQuestion(question);
            }
            JOptionPane.showMessageDialog(this, "Thêm danh sách câu hỏi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadQuestions();
            
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Không có file nào được chọn!",
                "Cảnh Báo",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản lý câu hỏi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new QuestionManagementUI());
            frame.setVisible(true);
        });
    }
}
