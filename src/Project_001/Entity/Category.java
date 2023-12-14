package Project_001.Entity;

import Project_001.Bussiness.IEntity;

import java.io.Serializable;
import java.util.Scanner;

import static Project_001.Presentation.CategoryMenu.lsCatalog;

public class Category implements IEntity, Serializable {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void input(Scanner scanner) {
        this.id = catalogIdAuto();
        this.name = inputName(scanner);
        this.status = inputStatus(scanner);
    }

    @Override
    public void output() {
        System.out.printf("| %11d | %29s | %19s |\n", this.id, this.name, this.status ? "Hoạt động" : "Không hoạt động");
        System.out.println("---------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("| %11d | %29s | %19s |\n" +
                        "---------------------------------------------------------------------",
                this.id, this.name, this.status ? "Hoạt động" : "Không hoạt động");
    }

    public int catalogIdAuto() {
        //Ma thể loại tu dong tang
        if (lsCatalog.size() == 0) {
            return 1;
        } else {
            int max = lsCatalog.get(0).getId();
            for (int i = 0; i < lsCatalog.size(); i++) {
                if (max < lsCatalog.get(i).getId()) {
                    max = lsCatalog.get(i).getId();
                }
            }
            return max + 1;
        }
    }

    public String inputName(Scanner scanner) {
        System.out.println("Nhập vào tên thể loại: ");
        do {
            try {
                String name = scanner.nextLine();
                if ((name.length() >= 6) && (name.length() < 30)) {
                    boolean isExist = false;
                    for (int i = 0; i < lsCatalog.size(); i++) {
                        if (lsCatalog.get(i).getName().equalsIgnoreCase(name)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Tên thể loại đã tồn tại, vui lòng nhập lại!");
                    } else {
                        return name;
                    }
                } else {
                    System.err.println("Tên thể loại có từ 6-30 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái thể loại: ");
        do {
            try {
                String status = scanner.nextLine();
                if (status.equals("true") || status.equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Trạng thái thể loại chỉ nhận giá trị true hoặc false, Vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public void updateCatalog(Scanner scanner) {
        //Cập nhật thể loại
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên thể loại: ");
            System.out.println("2. Cập nhật trạng thái thể loại: ");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3)) {
                }
            } catch (NumberFormatException ex) {
                System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    this.name = inputName(scanner);
                    break;
                case 2:
                    this.status = inputStatus(scanner);
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }
}
