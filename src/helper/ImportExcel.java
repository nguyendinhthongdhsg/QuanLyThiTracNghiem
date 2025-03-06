/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import DTO.AnswersDTO;
import DTO.QuestionsDTO;
import DTO.UsersDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ImportExcel {
    public ImportExcel(){}
    
    public List<UsersDTO> readFileUser(File file) {
        List<UsersDTO> userList = new ArrayList<>();
        
         try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            System.out.println("Đọc dữ liệu từ: " + file.getName());


            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) { // Lấy tiêu đề cột (hàng đầu tiên)
                    isFirstRow = false;
                    continue;
                }

                UsersDTO user = new UsersDTO();

                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    switch (i) { // Xử lý từng cột theo thứ tự
                        case 0: // Cột 1: UserName
                            user.setUserName(cell.getStringCellValue());
                            break;
                        case 1: // Cột 2: Email
                            user.setUserEmail(cell.getStringCellValue());
                            break;
                        case 2: // Cột 3: Fullname
                            user.setUserFullName(cell.getStringCellValue());
                            break;
                        default:
                            System.out.print("Cột không xác định: " + cell + "\t");
                    }
                }
                
                user.setUserPassword("123456");
                user.setIsAdmin((byte) 0);
                userList.add(user);
            }
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            return userList;
        }
    }
    
    public List<QuestionsDTO> readFileQuestion(File file) {
        List<QuestionsDTO> questionList = new ArrayList<>();
        
         try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            System.out.println("Đọc dữ liệu từ: " + file.getName());


            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) { // Lấy tiêu đề cột (hàng đầu tiên)
                    isFirstRow = false;
                    continue;
                }

                QuestionsDTO question = new QuestionsDTO();
                List<AnswersDTO> answerList = new ArrayList<>();
                int indexColRight = 0;
                
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    AnswersDTO answer = new AnswersDTO();
                    boolean isRight = false;
                    switch (i) { 
                        case 0: // Cột 1: Content
                            question.setqContent(cell.getStringCellValue());
                            break;
                        case 1: // Cột 2: ID Chủ đề
                            question.setqTopicID((int)cell.getNumericCellValue());
                            break;
                        case 2: // Cột 3: Cấp độ
                            question.setqLevel(cell.getStringCellValue());
                            break;
                        case 3: // Cột 3: Thứ tự đáp án đúng
                            indexColRight = (int)cell.getNumericCellValue();
                            break;
                        case 4: // Cột 4: Đáp án 1
                            if (cell.getCellType() == CellType.NUMERIC) {
                                answer.setAwContent(String.valueOf(cell.getNumericCellValue()));
                            } else {
                                answer.setAwContent(cell.getStringCellValue());
                            }
                            if(indexColRight == 4) {
                                isRight = true;
                            }
                            break;
                        case 5: // Cột 5: Đáp án 2
                            if (cell.getCellType() == CellType.NUMERIC) {
                                answer.setAwContent(String.valueOf(cell.getNumericCellValue()));
                            } else {
                                answer.setAwContent(cell.getStringCellValue());
                            }
                            if(indexColRight == 5) {
                                isRight = true;
                            }
                            break;
                        case 6: // Cột 6: Đáp án 3
                            if (cell.getCellType() == CellType.NUMERIC) {
                                answer.setAwContent(String.valueOf(cell.getNumericCellValue()));
                            } else {
                                answer.setAwContent(cell.getStringCellValue());
                            }
                            if(indexColRight == 6) {
                                isRight = true;
                            }
                            break;
                        case 7: // Cột 7: Đáp án 4
                            if (cell.getCellType() == CellType.NUMERIC) {
                                answer.setAwContent(String.valueOf(cell.getNumericCellValue()));
                            } else {
                                answer.setAwContent(cell.getStringCellValue());
                            }
                            if(indexColRight == 7) {
                                isRight = true;
                            }
                            break;
                        default:
                            break;
                    }
                    answer.setAwStatus((byte) 1);
                    answer.setIsRight(isRight);
                    answerList.add(answer);
                }
                
               question.setqStatus((byte) 1);
               question.setAnswers(answerList);
               questionList.add(question);
            }
            return questionList;
        } catch (IOException e) {
            e.printStackTrace();
            return questionList;
        }
    }
}