package Project_001.Presentation;

import Project_001.BussinessImp.CategoryImp;
import Project_001.Entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryMenu {
    CategoryImp ctl = new CategoryImp();
    public static List<Category> lsCatalog = new ArrayList<>();

    public void displayCatalogMenu(Scanner scanner) {
        try {
            lsCatalog = CategoryImp.readDataCatalogFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isExit = true;
        do {
            System.out.println("************ QUẢN LÝ THỂ LOẠI ************");
            System.out.println("1. Thêm mới thể loại");
            System.out.println("2. Hiển thị danh sách theo tên A – Z");
            System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
            System.out.println("4. Cập nhật thể loại");
            System.out.println("5. Xóa thể loại");
            System.out.println("6. Quay loại");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4)
                        && (choice != 5) && (choice != 6) && (choice != 7)) {
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    ctl.inputDataCatalog(scanner);
                    ctl.writeDataCatalogToFile();
                    ctl.displayDataCatalog();
                    break;
                case 2:
                    ctl.sortCatalogbyName();
                    break;
                case 3:
                    ctl.quantityStatisticsBook();
                    break;
                case 4:
                    ctl.updateCatalogById(scanner);
                    ctl.writeDataCatalogToFile();
                    ctl.displayDataCatalog();
                    break;
                case 5:
                    ctl.deleteCatalogById(scanner);
                    ctl.writeDataCatalogToFile();
                    ctl.displayDataCatalog();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Lựa chọn không phù hợp, Vui lòng nhập lại!");
            }
        } while (isExit);
    }
}
