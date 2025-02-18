package BLL;

import DAL.TestDAL;
import DTO.TestDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestBLL {
    private final TestDAL testDAL;

    public TestBLL() {
        this.testDAL = new TestDAL();
    }
    // lấy all bài thi
     public List<TestDTO> getAllTests() {
        return testDAL.getAllTests();
    }
    // Lấy bài thi theo ID
    public TestDTO getTestByID(int testID) {
        return testDAL.getTestByID(testID);
    }
    
    // Lấy danh sách bài thi theo chủ đề
    public List<TestDTO> getTestsByTopicID(int topicID) {
        return testDAL.getTestsByTopicID(topicID);
    }

    public TestDTO getTestByCode(String testCode) {
    return testDAL.getTestByCode(testCode);
}

    // Kiểm tra xem chủ đề có tồn tại không
    public boolean isTopicExist(int topicID) {
        return testDAL.isTopicExist(topicID);
    }

    // Thêm bài thi mới
    public boolean addTest(TestDTO test) {
        return testDAL.addTest(test);
    }

    // Cập nhật bài thi
    public boolean updateTest(TestDTO test) {
        return testDAL.updateTest(test);
    }

    // Xóa bài thi theo ID
    public boolean deleteTest(int testID) {
        return testDAL.deleteTest(testID);
    }
    // search
    public List<TestDTO> searchTests(String keyword) {
    return testDAL.searchTests(keyword);
    }

    // Đếm số lượng bài thi theo chủ đề
    public int countTestsByTopicID(int topicID) {
        return testDAL.countTestsByTopicID(topicID);
    }
}
