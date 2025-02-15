package UI.Edit;

import BLL.TopicsBLL;
import DTO.TopicsDTO;
import javax.swing.*;

public class TopicsEdit extends javax.swing.JDialog {
    private TopicsDTO selectedTopic;
    private TopicsBLL topicsBLL;
    private JTextField jTextFieldTitle;
    private JTextField jTextFieldParent;
    private JComboBox<String> jComboBoxStatus;
    private JButton jButtonSave;
    private JButton jButtonCancel;
    public TopicsEdit(JFrame parent, boolean modal, TopicsDTO topic, TopicsBLL topicsBLL) {
    super(parent, modal);
    initComponents();
    this.selectedTopic = topic;
    this.topicsBLL = topicsBLL;  
    jTextFieldTitle.setText(topic.getTpTitle());
    jTextFieldParent.setText(String.valueOf(topic.getTpParent()));
    jComboBoxStatus.setSelectedIndex(topic.getTpStatus() == 1 ? 0 : 1);
    
    setSize(300,200);
    setLocationRelativeTo(null);
    }


    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String title = jTextFieldTitle.getText();
        int parent = Integer.parseInt(jTextFieldParent.getText());
        byte status = (byte) (jComboBoxStatus.getSelectedIndex() == 0 ? 1 : 0);  
        
        TopicsDTO updatedTopic = new TopicsDTO(selectedTopic.getTpID(), title, parent, status);
        
        if (topicsBLL.updateTopic(updatedTopic)) {
            JOptionPane.showMessageDialog(this, "Cập nhật chủ đề thành công!");
            dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose(); 
    }
    
    
    
    private void initComponents() {
        jTextFieldTitle = new JTextField();
        jTextFieldParent = new JTextField();
        jComboBoxStatus = new JComboBox<>(new String[]{"Hoạt động", "Không hoạt động"});
        jButtonSave = new JButton("Lưu");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jButtonCancel = new JButton("Hủy");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        setLayout(new java.awt.GridLayout(4, 2));
        add(new JLabel("Tiêu đề:"));
        add(jTextFieldTitle);
        add(new JLabel("Chủ đề cha:"));
        add(jTextFieldParent);
        add(new JLabel("Trạng thái:"));
        add(jComboBoxStatus);
        add(jButtonSave);
        add(jButtonCancel);
        pack();  
    }
}
