/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import DTO.TestDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Nguyễn Đình Thông
 */
public class ExportDocx {

    public ExportDocx() {}
    
    public boolean exportDeThi(TestDTO test) {
        XWPFDocument document = new XWPFDocument();
        String tenBaiThi = test.getTestTitle();
        String maDe = test.getTestCode();
        String thoiGian = String.valueOf(test.getTestTime());
        try {
            FileOutputStream out = new FileOutputStream("baithi.docx");

            // Tiêu đề Trường và Khoa
            addTitle(document, "TRƯỜNG ĐẠI HỌC SÀI GÒN", false);
            addTitle(document, "KHOA CÔNG NGHỆ THÔNG TIN", true);
            addTitle(document, "", false); // Dòng trống

            // Thông tin bài thi
            addContent(document, "Bài thi: ", tenBaiThi, true);
            addContent(document, "Thời gian: ", thoiGian + " phút", true);
            addContent(document, "Mã đề: ", maDe, true);
            addTitle(document, "", false); // Dòng trống

            // Danh sách câu hỏi và đáp án
            List<String> questions = new ArrayList<>(Arrays.asList(
                "Làm thế nào để kết nối Java với MySQL?",
                "Java là gì?",
                "Một `ArrayList` trong Java có thể chứa các loại đối tượng nào?"
            ));
            

            List<List<String>> tempList = Arrays.asList(
            Arrays.asList("A. Sử dụng JDBC", "B. Sử dụng ODBC", "C. Sử dụng Hibernate", "D. Sử dụng JPA"),
            Arrays.asList("A. Ngôn ngữ lập trình hướng đối tượng", "B. Một hệ điều hành", "C. Một trình duyệt web", "D. Một cơ sở dữ liệu"),
            Arrays.asList("A. Tất cả các đối tượng", "B. Chỉ các đối tượng của lớp con của `ArrayList`", "C. Chỉ các đối tượng số nguyên", "D. Chỉ các đối tượng kiểu dữ liệu nguyên thủy")
            );

            // Tạo ArrayList<ArrayList<String>> đúng kiểu
            ArrayList<ArrayList<String>> answers = new ArrayList<>();
            for (List<String> item : tempList) {
                answers.add(new ArrayList<>(item));
            }

            // Thêm câu hỏi và câu trả lời vào file
            for (int i = 0; i < questions.size(); i++) {
                addTitle(document, (i + 1) + ". " + questions.get(i), true);
                for (String answer : answers.get(i)) {
                    addTitle(document, answer, false);
                }
                addTitle(document, "", false); // Dòng trống sau mỗi câu hỏi
            }

            // Ghi file
            document.write(out);
            out.close();
            document.close();

            System.out.println("✅ File DOCX 'baithi.docx' đã được tạo thành công!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addTitle(XWPFDocument document, String text, boolean bold) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
            if (bold) run.setBold(true);
            run.setFontSize(13);
    }
    
    private void addContent(XWPFDocument document, String title, String content, boolean bold) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(title);
            if (bold) run.setBold(true);
            else {
                run.setBold(false);
            }
            run.setFontSize(13);

            run.setText(content);
            run.setBold(false);
            run.setFontSize(13);
    }
}
