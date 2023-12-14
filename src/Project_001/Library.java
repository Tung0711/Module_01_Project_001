package Project_001;

import Project_001.Presentation.BookMenu;
import Project_001.Presentation.CategoryMenu;

import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        CategoryMenu ctl = new CategoryMenu();
        BookMenu bmn = new BookMenu();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************ QUẢN LÝ THƯ VIỆN ************");
            System.out.println("1. Quản lý thể loại");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3)) {
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    ctl.displayCatalogMenu(scanner);
                    break;
                case 2:
                    bmn.displayBookMenu(scanner);
                    break;
                case 3:
                    System.out.println("Chương trình kết thúc, xin tạm biệt!");
                    System.exit(0);
                default:
                    System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
            }
        } while (true);
    }
}
