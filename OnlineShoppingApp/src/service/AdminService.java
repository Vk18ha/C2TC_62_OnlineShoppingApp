package service;

import model.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Admin> adminList = new ArrayList<>();

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminList;
    }
}
