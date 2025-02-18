package UI.Component;

import BLL.ExamsBLL;
import BLL.QuestionsBLL;
import BLL.AnswersBLL;
import DTO.ExamsDTO;
import DTO.QuestionsDTO;
import DTO.AnswersDTO;
import DTO.TestDTO;
import BLL.TestBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ExamUI extends JPanel {
    private final ExamsBLL examsBLL;
    private final QuestionsBLL questionsBLL;
    private final AnswersBLL answersBLL;
    private final TestBLL testBLL;
    private int remainingTime;
    private int testID;
    private Timer timer;
    private JButton[] questionButtons;
    private JLabel timerLabel;
    private JPanel questionPanel;
    private List<QuestionsDTO> questions;
    private List<List<AnswersDTO>> allAnswers;
    private final java.util.Map<Integer, AnswersDTO> selectedAnswers = new java.util.HashMap<>();

public ExamUI(int testID) {
        examsBLL = new ExamsBLL();
        questionsBLL = new QuestionsBLL();
        answersBLL = new AnswersBLL();
        testBLL = new TestBLL();
        TestDTO test = testBLL.getTestByID(testID);
        this.remainingTime = test.getTestTime() * 60;
        List<ExamsDTO> exams = examsBLL.getExamsByTestCode(test.getTestCode());
        questions = new ArrayList<>();
        allAnswers = new ArrayList<>();

        for (ExamsDTO exam : exams) {
            String[] questionIDs = exam.getExQuestIDs().split(",");
            for (String qID : questionIDs) {
                int questionID = Integer.parseInt(qID.trim());
                QuestionsDTO question = questionsBLL.getQuestionById(questionID);
                if (question != null) {
                    questions.add(question);
                    allAnswers.add(answersBLL.getAnswersByQuestionID(questionID));
                }
            }
        }
        initComponents(test);
        startTimer();
    }

private void initComponents(TestDTO test) {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    topPanel.setBackground(new Color(30, 144, 255));
    
    JButton exitButton = new JButton("❌ Thoát");
    JButton submitButton = new JButton("📄 Nộp bài");
    exitButton.setBackground(Color.RED);
    exitButton.setForeground(Color.WHITE);
    submitButton.setBackground(new Color(34, 139, 34));
    submitButton.setForeground(Color.WHITE);
    
    timerLabel = new JLabel("Thời gian: " + formatTime(remainingTime), SwingConstants.CENTER);
    timerLabel.setFont(new Font("Sogoe UI", Font.BOLD, 16));
    timerLabel.setForeground(Color.WHITE);
    
    exitButton.addActionListener(e -> exitExam());
    submitButton.addActionListener(e -> {
        System.out.println("Nút Nộp bài đã được nhấn!");
        submitExam();
    });

    topPanel.add(exitButton, BorderLayout.WEST);
    topPanel.add(timerLabel, BorderLayout.CENTER);
    topPanel.add(submitButton, BorderLayout.EAST);
    
    add(topPanel, BorderLayout.NORTH);

    JPanel mainPanel = new JPanel(new BorderLayout());
    add(mainPanel, BorderLayout.CENTER);
    
    questionPanel = new JPanel(new BorderLayout());
    questionPanel.setMinimumSize(new Dimension(100, 100));  
    questionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    questionPanel.setBackground(Color.WHITE);
    mainPanel.add(questionPanel, BorderLayout.CENTER);
    
    // Danh sách câu hỏi 
    JPanel questionListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    questionListPanel.setBorder(BorderFactory.createTitledBorder("Danh sách câu hỏi"));
    questionListPanel.setBackground(Color.WHITE);
    questionButtons = new JButton[questions.size()];
    
    for (int i = 0; i < questions.size(); i++) {
        int questionIndex = i;
        questionButtons[i] = new JButton(String.valueOf(i + 1));
        questionButtons[i].setPreferredSize(new Dimension(50, 50));
        questionButtons[i].setBackground(Color.WHITE);
        questionButtons[i].setForeground(Color.BLACK);
        questionButtons[i].setFocusPainted(false);
        questionButtons[i].addActionListener(e -> showQuestion(questionIndex));
        questionListPanel.add(questionButtons[i]);
    }
    
    JScrollPane scrollPane = new JScrollPane(questionListPanel,
            JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    mainPanel.add(scrollPane, BorderLayout.SOUTH);

    showQuestion(0);
}

    private void showQuestion(int index) {
        questionPanel.removeAll();
        QuestionsDTO question = questions.get(index);
        List<AnswersDTO> answerList = allAnswers.get(index);
        String questionText = question.getqContent();
        String questionImage = question.getqPictures();

        JLabel questionLabel = new JLabel("<html><b>" + (index + 1) + ". " + questionText + "</b></html>");
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); 
        questionLabel.setBackground(new Color(230, 230, 255)); 
        questionLabel.setOpaque(true); 
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.WHITE);
        // Nếu có ảnh câu hỏi
        if (questionImage != null && !questionImage.isEmpty()) {

            try {
                ImageIcon originalIcon = new ImageIcon(questionImage);
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(scaledIcon);
                JPanel questionContainer = new JPanel();
                questionContainer.setLayout(new BorderLayout());
                questionContainer.setBackground(Color.WHITE); 
                questionContainer.add(questionLabel, BorderLayout.NORTH);
                questionContainer.add(imageLabel, BorderLayout.CENTER);
                questionPanel.add(questionContainer);
            } catch (Exception e) {
                System.out.println("Lỗi khi tải hình ảnh: " + e.getMessage());
            }
        } else {
            // Không có ảnh, căn trái và giảm khoảng cách
            JPanel questionContainer = new JPanel();
            questionContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));  
            questionContainer.setBackground(Color.WHITE); 
            questionContainer.add(questionLabel);  
            questionPanel.add(questionContainer);  
        }
        JPanel answerPanel = new JPanel(new GridLayout(2, 2));
        answerPanel.setBackground(Color.WHITE); 
        answerPanel.setPreferredSize(new Dimension(400, 250));  
        JRadioButton[] answerButtons = new JRadioButton[answerList.size()];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < answerList.size(); i++) {
            final int answerIndex = i;  
            String answerContent = answerList.get(answerIndex).getAwContent();
            String answerImage = answerList.get(answerIndex).getAwPictures();

            JPanel answerPanelWithImage = new JPanel();
            answerPanelWithImage.setLayout(new BoxLayout(answerPanelWithImage, BoxLayout.X_AXIS));
            JRadioButton answerButton = new JRadioButton("<html>" + answerContent + "</html>");  // Sử dụng HTML để xuống dòng khi văn bản dài
            answerButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));  

            // Set actionCommand để sử dụng khi kiểm tra
            answerButton.setActionCommand(answerContent);

            if (answerImage != null && !answerImage.isEmpty()) {
                try {
                    ImageIcon answerIcon = new ImageIcon(answerImage);
                    Image answerScaledImage = answerIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon scaledAnswerIcon = new ImageIcon(answerScaledImage);

                    JLabel imageLabel = new JLabel(scaledAnswerIcon);
                    answerPanelWithImage.add(answerButton);
                    answerPanelWithImage.add(imageLabel);
                } catch (Exception e) {
                    System.out.println("Lỗi khi tải hình ảnh đáp án: " + e.getMessage());
                }
            } else {
                answerPanelWithImage.add(answerButton);
            }

            group.add(answerButton);

            // Lưu đáp án đã chọn khi nhấn vào
            answerButton.addActionListener(e -> {
                questionButtons[index].setBackground(new Color(173, 216, 230));
                // Lưu đáp án vào selectedAnswers
                selectedAnswers.put(question.getqID(), answerList.get(answerIndex)); 
            });

            answerPanel.add(answerPanelWithImage);
        }

        questionPanel.add(answerPanel);
        revalidate();
        repaint();
        }

    private void submitExam() {
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            QuestionsDTO question = questions.get(i);
            List<AnswersDTO> answerList = allAnswers.get(i);

            // Chuẩn hóa độ khó
            String difficultyLevel = (question.getqLevel() != null) ? question.getqLevel().trim().toLowerCase() : "";

            String correctTextAnswer = "";
            String correctImageAnswer = "";

            // Tìm đáp án đúng
            for (AnswersDTO answer : answerList) {
                if (answer.isRight()) {
                    correctTextAnswer = (answer.getAwContent() != null) ? answer.getAwContent().trim() : "";
                    correctImageAnswer = (answer.getAwPictures() != null) ? answer.getAwPictures().trim() : "";
                    break;
                }
            }

            // Lấy đáp án đã chọn
            AnswersDTO selectedAnswer = selectedAnswers.get(question.getqID());

            if (selectedAnswer != null) {
                String selectedText = (selectedAnswer.getAwContent() != null) ? selectedAnswer.getAwContent().trim() : "";
                String selectedImage = (selectedAnswer.getAwPictures() != null) ? selectedAnswer.getAwPictures().trim() : "";

                // Kiểm tra đáp án đúng
                boolean textCorrect = selectedText.equals(correctTextAnswer);
                boolean imageCorrect = (correctImageAnswer.isEmpty() && selectedImage.isEmpty()) || selectedImage.equals(correctImageAnswer);

                if (textCorrect || imageCorrect) {

                    // Cộng điểm dựa trên độ khó
                    if ("dễ".equals(difficultyLevel)) {
                        score += 1;
                    } else if ("trung bình".equals(difficultyLevel)) {
                        score += 2;
                    } else if ("khó".equals(difficultyLevel)) {
                        score += 3;
                    } 
                } 
            } 
        }

        JOptionPane.showMessageDialog(this, "Bài thi đã được nộp!\nTổng điểm: " + score, "Kết quả thi", JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.getWindowAncestor(this).dispose();
        new ExamSelectionUI().setVisible(true);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timerLabel.setText("Thời gian: " + formatTime(remainingTime));
                if (remainingTime <= 0) {
                    timer.stop();
                    submitExam();
                }
            }
        });
        timer.start();
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    private void exitExam() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            SwingUtilities.getWindowAncestor(this).dispose(); 
            new ExamSelectionUI().setVisible(true); 
        }
    }
}
