/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MySQLConnection {
    public static Connection getConnection() {
        Connection result = null;
        try {
            // Dang ky MySQL Driver voi DriverManager
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //Cac thong so
            String url = "jdbc:mySQL://localhost:3306/quanlythitracnghiem";
            String userName = "root";
            String password = "anhladuado1";
            //Tao ket noi 
            result = DriverManager.getConnection(url, userName, password);
            System.out.println("Đã kết nối đến cơ sở dữ liệu !");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thể đóng cơ sở dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
