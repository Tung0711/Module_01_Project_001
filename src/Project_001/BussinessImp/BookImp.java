package Project_001.BussinessImp;

import Project_001.Entity.Book;
import Project_001.Entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Project_001.Presentation.BookMenu.lsBook;
import static Project_001.Presentation.CategoryMenu.lsCatalog;


public class BookImp {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void inputDataBook(Scanner scanner) {
        System.out.println("Nhập vào số lượng sách cần nhập thông tin:");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Số lượng sách là số nguyên, vui lòng nhập lại!");
        }
        for (int i = 0; i < number; i++) {
            Book bk = new Book();
            bk.input(scanner);
            lsBook.add(bk);
        }
    }

    public void displayDataBook() {
        formatPrintBook();
        for (int i = 0; i < lsBook.size(); i++) {
            lsBook.get(i).output();
        }
    }

    public static void formatPrintBook() {
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------");
        System.out.println(ANSI_GREEN + "| Mã Sách |          Tiêu đề sách          |   Tên tác giả   |" +
                "     Nhà xuất bản     | Năm xuất bản |        Mô tả sách        |         Thể loại sách         |" + ANSI_RESET);
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------");
    }

    public void updateBookById(Scanner scanner) {
        System.out.println("Nhập mã sách cần cập nhật:");
        String bookIdUpdate = null;
        try {
            bookIdUpdate = scanner.nextLine();
        } catch (NumberFormatException ex) {
            System.err.println("Mã sách là số nguyên, vui lòng nhập lại!");
            ex.printStackTrace();
        }
        boolean find = false;
        for (int i = 0; i < lsBook.size(); i++) {
            Book bk = lsBook.get(i);
            if (bk.getId().equals(bookIdUpdate)) {
                bk.updateBook(scanner);
                System.out.println("Thông tin sách đã được cập nhật thành công!");
                find = true;
                break;
            }
        }
        if (!find) {
            System.err.println("Mã sách không tồn tại, vui lòng nhập lại!");
        }
    }

    public void deleteBook(Scanner scanner) {
        System.out.println("Nhập vào mã sách cần xóa:");
        try {
            String bookIdDelete = scanner.nextLine();
            boolean isDelete = false;
            for (Book bk : lsBook) {
                if (bk.getId().equals(bookIdDelete)) {
                    lsBook.remove(bk);
                    isDelete = true;
                    System.out.println("Xóa sách thành công!");
                    break;
                }
            }
            if (!isDelete) {
                System.err.println("Mã sách không tồn tại, vui lòng nhập lại!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void findBook(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sách cần tìm: ");
        try {
            String findBookTitle = scanner.nextLine();
            boolean isFindBook = lsBook.stream().anyMatch(book -> book.getTitle().equalsIgnoreCase(findBookTitle));
            if (!isFindBook) {
                System.err.println("Sách không tồn tại!");
            } else {
                formatPrintBook();
                lsBook.stream().filter(book -> book.getTitle().equalsIgnoreCase(findBookTitle)).forEach(System.out::println);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printBookForGroup() {
        for (Category ctl : lsCatalog) {
            System.out.println("Thể loại: " + ctl.getName());
            formatPrintBook();
            lsBook.stream().filter(book -> book.getCategoryId() == ctl.getId()).forEach(System.out::println);
        }
    }

    public static List<Book> readDataBookFromFile() {
        List<Book> listBookRead = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBookRead = (List<Book>) ois.readObject();
        } catch (Exception ex) {
            listBookRead = new ArrayList<>();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listBookRead;
    }

    public void writeDataBookToFile() {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lsBook);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
