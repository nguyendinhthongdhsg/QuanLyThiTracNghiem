package UI.Component;

import BLL.TopicsBLL;
import DTO.TopicsDTO;
import UI.Add.TopicsAdd;
import UI.Edit.TopicsEdit;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TopicsManagement extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private ArrayList<TopicsDTO> topicsList = new ArrayList<>();
    
    TopicsBLL topicsBLL = new TopicsBLL();

    public TopicsManagement() {
        initComponents();
        initComponentCustom();
        initTable();
        topicsList = topicsBLL.getAllTopics();
        loadDataToTable(topicsList);
    }

    private void initComponentCustom() {
        jToolBarMenu.setFloatable(false);
    }

private void initTable() {
    tblModel = new DefaultTableModel();
    String[] headerTbl = new String[]{"Số thứ tự", "Tiêu Đề", "Trạng Thái", "Chủ đề cha"};  
    tblModel.setColumnIdentifiers(headerTbl);

    JTableHeader header = jTableUser.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 12));

    jTableUser.setModel(tblModel);

    jTableUser.getColumnModel().getColumn(0).setPreferredWidth(10);
    jTableUser.getColumnModel().getColumn(1).setPreferredWidth(50);
    jTableUser.getColumnModel().getColumn(2).setPreferredWidth(50);
    jTableUser.getColumnModel().getColumn(3).setPreferredWidth(50);  
}


    private void loadDataToTable(ArrayList<TopicsDTO> topicsList) {
    try {
        tblModel.setRowCount(0);
        for (TopicsDTO i : topicsList) {
            String status = (i.getTpStatus() == 1) ? "Hoạt động" : "Không hoạt động"; 
            tblModel.addRow(new Object[]{
                i.getTpID(),
                i.getTpTitle(),
                status, 
                i.getTpParent()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBarMenu = new javax.swing.JToolBar();
        jButtonAdd = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jToolBarMenu.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarMenu.setBorder(null);
        jToolBarMenu.setRollover(true);

        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        jButtonAdd.setText("Thêm");
        jButtonAdd.setFocusable(false);
        jButtonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButtonAddMouseReleased(evt);
            }
        });
        jToolBarMenu.add(jButtonAdd);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton3.setText("Sửa");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarMenu.add(jButton3);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSelectedTopic();
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jButton4.setText("Xóa");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarMenu.add(jButton4);
        
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedTopic();
            }
        });
        
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detail.png"))); // NOI18N
        jButton5.setText("Chi tiết");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarMenu.add(jButton5);

        jTextFieldSearch.setText("Nhập nội dung tìm kiếm...");
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID", "Title", "Status" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Title", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUser.setRowHeight(30);
        jTableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableUser.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {
        if (jTextFieldSearch.getText().equals("Nhập nội dung tìm kiếm...")) {
            jTextFieldSearch.setText("");
        }
    }

    // thêm
    private void jButtonAddMouseReleased(java.awt.event.MouseEvent evt) {
        TopicsAdd addTopic = new TopicsAdd((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addTopic.setVisible(true);
    }
    
    // load data sau events
    public void reloadTopicsData() {
    topicsList = topicsBLL.getAllTopics();
    loadDataToTable(topicsList);
    }
    
   
    // xóa
    private void deleteSelectedTopic() {
    int selectedRow = jTableUser.getSelectedRow();  
    if (selectedRow != -1) {  
        int topicID = (int) jTableUser.getValueAt(selectedRow, 0);  
        System.out.println("topicID: " + topicID); 

        int confirm = JOptionPane.showConfirmDialog(
            null, "Bạn có chắc chắn muốn xóa chủ đề này?", 
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = topicsBLL.deleteTopic(topicID);  
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa chủ đề thành công!");  
                reloadTopicsData();  
            } else {
                JOptionPane.showMessageDialog(null, "Xóa chủ đề thất bại! Vui lòng kiểm tra lại.");  
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn chủ đề để xóa!");
    }
    }

    // updateSelectedTopic
    private void updateSelectedTopic() {
    int selectedRow = jTableUser.getSelectedRow();
    if (selectedRow != -1) {
        int topicID = (int) jTableUser.getValueAt(selectedRow, 0); 
        System.out.println("topic_id" + topicID);
        TopicsDTO selectedTopic = topicsList.stream()
                .filter(topic -> topic.getTpID() == topicID)
                .findFirst()
                .orElse(null);
        if (selectedTopic != null) {
            TopicsEdit editTopic = new TopicsEdit((JFrame) SwingUtilities.getWindowAncestor(this), true, selectedTopic, topicsBLL);
            editTopic.setVisible(true);
            
            topicsList = topicsBLL.getAllTopics();
            loadDataToTable(topicsList);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ đề để sửa.");
    }
}

    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUser;
    private javax.swing.JToolBar jToolBarMenu;
    private javax.swing.JTextField jTextFieldSearch;
}
