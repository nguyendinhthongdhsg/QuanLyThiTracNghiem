package BLL;

import DAL.UsersDAL;
import DTO.UsersDTO;
import java.util.ArrayList;

public class UsersBLL {
    private final UsersDAL usersDAL = new UsersDAL();

    public String register(UsersDTO user) {
        if (user.getUserName().isEmpty() || user.getUserEmail().isEmpty() ||
            user.getUserPassword().isEmpty() || user.getUserFullName().isEmpty()) {
            return "Vui lòng điền đầy đủ thông tin!";
        }
        if (usersDAL.checkUserExists(user.getUserName())) {
            return "Tên đăng nhập đã tồn tại!";
        }

        // Mã hóa mật khẩu trước khi lưu 
        String hashedPassword = hashPassword(user.getUserPassword());
        user.setUserPassword(hashedPassword);

        // Lưu vào CSDL
        if (usersDAL.registerUser(user)) {
            return "Đăng ký thành công!";
        } else {
            return "Đăng ký thất bại. Vui lòng thử lại!";
        }
    }
    
    public String add(UsersDTO user) {
        if (user.getUserName().isEmpty() || user.getUserEmail().isEmpty() ||
            user.getUserPassword().isEmpty() || user.getUserFullName().isEmpty()) {
            return "Vui lòng điền đầy đủ thông tin!";
        }
        if (usersDAL.checkUserExists(user.getUserName())) {
            return "Tên đăng nhập đã tồn tại!";
        }

        // Mã hóa mật khẩu trước khi lưu 
        String hashedPassword = hashPassword(user.getUserPassword());
        user.setUserPassword(hashedPassword);

        // Lưu vào CSDL
        if (usersDAL.registerUser(user)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại. Vui lòng thử lại!";
        }
    }
    
    public String update(UsersDTO user) {
        if (user.getUserName().isEmpty() || user.getUserEmail().isEmpty() ||
            user.getUserPassword().isEmpty() || user.getUserFullName().isEmpty()) {
            return "Vui lòng điền đầy đủ thông tin!";
        }

        // Mã hóa mật khẩu trước khi lưu 
        String hashedPassword = hashPassword(user.getUserPassword());
        user.setUserPassword(hashedPassword);

        // Lưu vào CSDL
        if (usersDAL.update(user)) {
            return "Sửa thành công!";
        } else {
            return "Sửa thất bại. Vui lòng thử lại!";
        }
    }
    
    public String delete(int userID) {
        if (userID == -1) {
            return "Vui lòng chọn người dùng muốn xóa!";
        }

        // Lưu vào CSDL
        if (usersDAL.delete(userID)) {
            return "Xóa thành công!";
        } else {
            return "Xóa thất bại. Vui lòng thử lại!";
        }
    }
    
    public ArrayList<UsersDTO> getUserList() {
        return usersDAL.getUserList();
    }

    private String hashPassword(String password) {
        return password; 
    }
    
   public static UsersDTO authenticateUser(String username, String password) {
    return UsersDAL.selectByAccount(username, password);
}

}
