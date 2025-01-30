    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package DTO;

    public class UsersDTO {
        private int userID;
        private String userName;
        private String userEmail;
        private String userPassword;
        private String userFullName;
        private byte isAdmin;

        public UsersDTO() {}

        public UsersDTO(int userID, String userName, String userEmail, String userPassword, String userFullName, byte isAdmin) {
            this.userID = userID;
            this.userName = userName;
            this.userEmail = userEmail;
            this.userPassword = userPassword;
            this.userFullName = userFullName;
            this.isAdmin = isAdmin;
        }
        
        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public byte getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(byte isAdmin) {
            this.isAdmin = isAdmin;
        }

    }

