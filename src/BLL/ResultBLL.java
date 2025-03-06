/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ResultDAL;
import DTO.ResultDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ResultBLL {
    private final ResultDAL resultDAL;
    
    public ResultBLL() {
        resultDAL = new ResultDAL();
    }
    
    public List<ResultDTO> getAllResults() {
        return resultDAL.getAllResults();
    }
}
