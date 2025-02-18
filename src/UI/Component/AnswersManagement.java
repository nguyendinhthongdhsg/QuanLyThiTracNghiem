
package UI.Component;

import BLL.AnswersBLL;
import DTO.AnswersDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AnswersManagement extends JDialog {
    private JTable tblAnswers;
    private DefaultTableModel tableModel;
    private JButton btnSave, btnAdd, btnDelete;
    private JTextField txtAnswerContent;
    private JCheckBox chkCorrect;
    private AnswersBLL answersBLL;
    private int questionID;

    private JButton btnChooseImage;
    private JLabel lblImagePreview;
    private String selectedImagePath = null;

    public AnswersManagement(Frame parent, int questionID) {
        super(parent, "Quản lý Câu Trả Lời", true);
        this.questionID = questionID;
        answersBLL = new AnswersBLL();

        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nội dung", "Hình ảnh", "Đúng/Sai"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        tblAnswers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblAnswers);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 3 && e.getType() == TableModelEvent.UPDATE) {
                    int selectedRow = e.getFirstRow();
                    Boolean isChecked = (Boolean) tableModel.getValueAt(selectedRow, 3);
                    if (isChecked != null && isChecked) {
                        for (int i = 0; i < tblAnswers.getRowCount(); i++) {
                            if (i != selectedRow) {
                                tableModel.setValueAt(false, i, 3);
                            }
                        }
                    }
                }
            }
        });

        JPanel panelForm = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Nội dung câu trả lời:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtAnswerContent = new JTextField(20);
        inputPanel.add(txtAnswerContent, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        chkCorrect = new JCheckBox("Đáp án đúng");
        inputPanel.add(chkCorrect, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        btnChooseImage = new JButton("Chọn ảnh");
        inputPanel.add(btnChooseImage, gbc);

        JPanel imagePanel = new JPanel(new BorderLayout());
        lblImagePreview = new JLabel();
        lblImagePreview.setPreferredSize(new Dimension(120, 120));
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanel.add(lblImagePreview, BorderLayout.CENTER);

        btnAdd = new JButton("Thêm câu trả lời");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        inputPanel.add(btnAdd, gbc);

        btnSave = new JButton("Lưu thay đổi");
        btnDelete = new JButton("Xóa câu trả lời");
        btnDelete.setEnabled(false);

        panelForm.add(inputPanel, BorderLayout.WEST);
        panelForm.add(imagePanel, BorderLayout.EAST);

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnSave);
        panelButtons.add(btnDelete);

        add(scrollPane, BorderLayout.CENTER);
        add(panelForm, BorderLayout.SOUTH);
        add(panelButtons, BorderLayout.NORTH);

        loadAnswers();

        tblAnswers.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = tblAnswers.getSelectedRow();
                if (selectedRow != -1) {
                    String imagePath = (String) tableModel.getValueAt(selectedRow, 2);
                    displayImage(imagePath);
                    btnDelete.setEnabled(true);
                } else {
                    btnDelete.setEnabled(false);
                }
            }
        });

        btnAdd.addActionListener(this::addAnswer);
        btnSave.addActionListener(this::saveAnswers);
        btnChooseImage.addActionListener(e -> chooseImage());
        btnDelete.addActionListener(this::deleteAnswer);
    }

    private void loadAnswers() {
        tableModel.setRowCount(0);
        List<AnswersDTO> answers = answersBLL.getAnswersByQuestionID(questionID);
        for (AnswersDTO answer : answers) {
            tableModel.addRow(new Object[]{answer.getAwID(), answer.getAwContent(), answer.getAwPictures(), answer.isRight()});
        }
        displayImage(tblAnswers.getRowCount() > 0 ? (String) tableModel.getValueAt(0, 2) : null);
    }
        // sửa
    private void saveAnswers(ActionEvent e) {
        for (int i = 0; i < tblAnswers.getRowCount(); i++) {
            int answerId = (Integer) tblAnswers.getValueAt(i, 0);
            String content = (String) tblAnswers.getValueAt(i, 1);
            String image = (String) tblAnswers.getValueAt(i, 2);
            Boolean isCorrect = (Boolean) tblAnswers.getValueAt(i, 3);

            if (isCorrect == null) isCorrect = false;

            AnswersDTO answer = new AnswersDTO();
            answer.setAwID(answerId);
            answer.setqID(questionID);
            answer.setAwContent(content);
            answer.setAwPictures(image);
            answer.setIsRight(isCorrect);

            answersBLL.updateAnswer(answer, questionID);
        }
        JOptionPane.showMessageDialog(this, "Câu trả lời đã được cập nhật thành công.");
    }
    // thêm
    private void addAnswer(ActionEvent e) {
    String content = txtAnswerContent.getText().trim();
    boolean isCorrect = chkCorrect.isSelected();

    if (content.isEmpty() && selectedImagePath == null) {
        JOptionPane.showMessageDialog(this, "Phải nhập nội dung hoặc chọn hình ảnh!");
        return;
    }

    AnswersDTO answer = new AnswersDTO();
    answer.setqID(questionID);
    
        answer.setAwContent(content.isEmpty() ? "" : content);  
    answer.setAwPictures(selectedImagePath);
    answer.setIsRight(isCorrect);
    answer.setAwStatus((byte) 1);

    if (answersBLL.addAnswer(answer, questionID)) {
        JOptionPane.showMessageDialog(this, "Thêm câu trả lời thành công!");
        loadAnswers();
        txtAnswerContent.setText("");
        chkCorrect.setSelected(false);
        lblImagePreview.setIcon(null);
        selectedImagePath = null;
    } else {
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm câu trả lời!");
    }
}
    private void deleteAnswer(ActionEvent e) {
        int selectedRow = tblAnswers.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu trả lời để xóa!");
            return;
        }

        int answerID = (Integer) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa câu trả lời này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (answersBLL.deleteAnswer(answerID, questionID)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadAnswers();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa câu trả lời!");
            }
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            lblImagePreview.setIcon(new ImageIcon(new ImageIcon(selectedImagePath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        }
    }

    private void displayImage(String path) {
        lblImagePreview.setIcon(path == null || path.isEmpty() ? null : new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    }
}

