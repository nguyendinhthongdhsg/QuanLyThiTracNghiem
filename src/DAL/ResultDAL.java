/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ResultDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ResultDAL {
    // Lấy tất cả bài thi
    public List<ResultDTO> getAllResults() {
        List<ResultDTO> resultList = new ArrayList<>();
        String sql = "SELECT * FROM result";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ResultDTO result = new ResultDTO();
                result.setUserID(rs.getInt("userID"));
                result.setExCode(rs.getString("exCode"));
                result.setRsAnswers(rs.getString("rs_anwsers"));
                result.setRsNum(rs.getInt("rs_num"));
                result.setRsMark(rs.getInt("rs_mark"));
                result.setRsDate(rs.getString("rs_date"));
                resultList.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
