package UI.Component;

import BLL.ExamsBLL;
import DTO.ExamsDTO;
import DTO.TestDTO;
import BLL.TestBLL;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExamSelectionUI extends JPanel {
    private final ExamsBLL examsBLL;
    private final TestBLL testBLL;

    public ExamSelectionUI() {
        examsBLL = new ExamsBLL();
        testBLL = new TestBLL();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 245));
        
        List<ExamsDTO> exams = examsBLL.getAllExams();

        for (ExamsDTO exam : exams) {
            TestDTO test = testBLL.getTestByCode(exam.getTestCode());

            JPanel examPanel = new JPanel(new BorderLayout());
            examPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            examPanel.setBackground(Color.WHITE);
            examPanel.setPreferredSize(new Dimension(500, 120));
            examPanel.setMaximumSize(new Dimension(500, 120));
            examPanel.setLayout(new BorderLayout());
            
            JPanel textPanel = new JPanel(new GridLayout(4, 1));
            textPanel.setBackground(Color.WHITE);
            textPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
            
            JLabel titleLabel = new JLabel("<html><b>" + test.getTestTitle() + "</b></html>");
            JLabel timeLabel = new JLabel(" Thời gian: " + test.getTestTime() + " phút");
            JLabel codeLabel = new JLabel(" Mã đề: " + exam.getExCode());
            JLabel attemptLabel = new JLabel(" Số lượt làm bài: " + test.getTestLimit());
            
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            Font smallFont = new Font("Arial", Font.PLAIN, 12);
            timeLabel.setFont(smallFont);
            codeLabel.setFont(smallFont);
            attemptLabel.setFont(smallFont);
            
            textPanel.add(titleLabel);
            textPanel.add(timeLabel);
            textPanel.add(codeLabel);
            textPanel.add(attemptLabel);
            
            JButton startButton = new JButton("▶");
            startButton.setPreferredSize(new Dimension(40, 40));
            startButton.setBackground(new Color(0, 120, 215));
            startButton.setForeground(Color.WHITE);
            startButton.setBorder(BorderFactory.createEmptyBorder());
            startButton.addActionListener(e -> startExam(exam));
            
            examPanel.add(textPanel, BorderLayout.CENTER);
            examPanel.add(startButton, BorderLayout.EAST);
            add(Box.createVerticalStrut(10)); // Khoảng cách giữa các panel
            add(examPanel);
        }
    }

    private void startExam(ExamsDTO exam) {
        TestDTO test = testBLL.getTestByCode(exam.getTestCode()); // Lấy thông tin bài thi từ testCode
        if (test == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bài thi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mở cửa sổ mới với giao diện ExamUI
        JFrame examFrame = new JFrame("Làm bài thi");
        examFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examFrame.setSize(800, 600);
        examFrame.setLocationRelativeTo(null);
        examFrame.add(new ExamUI(test.getTestID())); // Truyền testID vào ExamUI để lấy danh sách câu hỏi
        examFrame.setVisible(true);
    }

}
