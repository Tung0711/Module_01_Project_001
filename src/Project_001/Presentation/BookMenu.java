package Project_001.Presentation;

import Project_001.BussinessImp.BookImp;
import Project_001.Entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookMenu {
    BookImp bk = new BookImp();
    public static List<Book> lsBook = new ArrayList<>();

    public void displayBookMenu(Scanner scanner) {
        try {
            lsBook = BookImp.readDataBookFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isExit = true;
        do {
            System.out.println("************ QUẢN LÝ SÁCH ************");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị danh sách sách theo nhóm thể loại");
            System.out.println("6. Quay lại");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4) && (choice != 5) && (choice != 6)) {
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    bk.inputDataBook(scanner);
                    bk.writeDataBookToFile();
                    bk.displayDataBook();
                    break;
                case 2:
                    bk.updateBookById(scanner);
                    bk.writeDataBookToFile();
                    break;
                case 3:
                    bk.deleteBook(scanner);
                    bk.writeDataBookToFile();
                    break;
                case 4:
                    bk.findBook(scanner);
                    break;
                case 5:
                    bk.printBookForGroup();
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
