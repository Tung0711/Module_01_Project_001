package Project_001.BussinessImp;

import Project_001.Entity.Book;
import Project_001.Entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static Project_001.Presentation.BookMenu.lsBook;
import static Project_001.Presentation.CategoryMenu.lsCatalog;

public class CategoryImp {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void inputDataCatalog(Scanner scanner) {
        System.out.println("Nhập vào số lượng thể loại cần nhập thông tin:");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Số lượng thể loại là số nguyên, vui lòng nhập lại!");
        }
        for (int i = 0; i < number; i++) {
            Category ctl = new Category();
            ctl.input(scanner);
            lsCatalog.add(ctl);
        }
    }

    public void displayDataCatalog() {
        formatPrintCatalog();
        for (int i = 0; i < lsCatalog.size(); i++) {
            lsCatalog.get(i).output();
        }
    }

    public static void formatPrintCatalog() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(ANSI_YELLOW + "| Mã thể loại |          Tên thể loại         | Trạng thái thể loại |" + ANSI_RESET);
        System.out.println("---------------------------------------------------------------------");
    }

    public void sortCatalogbyName() {
        System.out.println("Sắp xếp thể loại theo tên từ A-Z");
        try {
            formatPrintCatalog();
            lsCatalog.stream().sorted(Comparator.comparing(Category::getName)).forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void quantityStatisticsBook() {
        try {
            for (Category ctl : lsCatalog) {
                long countCatalog = lsBook.stream().filter(book -> book.getCategoryId() == ctl.getId()).count();
                System.out.printf("%d - %s - Số sách: %d\n", ctl.getId(), ctl.getName(), countCatalog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCatalogById(Scanner scanner) {
        System.out.println("Nhập mã thể loại cần cập nhật:");
        int catalogIdUpdate = 0;
        try {
            catalogIdUpdate = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Mã thể loại là số nguyên, vui lòng nhập lại!");
            ex.printStackTrace();
        }
        boolean find = false;
        for (int i = 0; i < lsCatalog.size(); i++) {
            Category ctl = lsCatalog.get(i);
            if (ctl.getId() == catalogIdUpdate) {
                ctl.updateCatalog(scanner);
                System.out.println("Thông tin thể loại đã được cập nhật thành công!");
                find = true;
                break;
            }
        }
        if (!find) {
            System.err.println("Mã thể loại không tồn tại, vui lòng nhập lại!");
        }
    }

    public void deleteCatalogById(Scanner scanner) {
        System.out.println("Nhập vào mã thể loại cần xóa: ");
        int deleteCatalogId = 0;
        try {
            deleteCatalogId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Mã thể loại là số nguyên, vui lòng nhập lại!");
            ex.printStackTrace();
        }
        int indexDelete = -1;
        boolean isDelete = false;
        for (int i = 0; i < lsCatalog.size(); i++) {
            Category ctl = lsCatalog.get(i);
            if (ctl.getId() == deleteCatalogId) {
                indexDelete = i;
                isDelete = true;
                break;
            }
        }
        if (!isDelete) {
            System.err.println("Mã thể loại không tồn tại, vui lòng nhập lại!");
        } else if (CategoryImp.hasBook(deleteCatalogId)) {
            System.err.println("Thể loại đã chứa sách, không thể xóa!");
        } else {
            lsCatalog.remove(indexDelete);
            System.out.println("Thể loại đã xóa thành công");
        }
    }

    public static boolean hasBook(int catalogId) {
        for (Book bk : lsBook) {
            if (bk.getCategoryId() == catalogId) {
                return true;
            }
        }
        return false;
    }

    public static List<Category> readDataCatalogFromFile() {
        List<Category> listCatalogRead = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCatalogRead = (List<Category>) ois.readObject();
        } catch (Exception ex) {
            listCatalogRead = new ArrayList<>();
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
        return listCatalogRead;
    }

    public void writeDataCatalogToFile() {
        File file = new File("categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lsCatalog);
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
