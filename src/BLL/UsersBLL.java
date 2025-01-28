package BLL;

import DAL.UsersDAL;
import DTO.UsersDTO;

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

    private String hashPassword(String password) {
        return password; 
    }
    
   public static boolean authenticateUser(String username, String password) {
        UsersDTO user = UsersDAL.selectByAccount(username, password);
        return user != null;
    }
}
