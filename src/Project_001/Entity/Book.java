package Project_001.Entity;

import Project_001.Bussiness.IEntity;

import java.io.Serializable;
import java.time.Year;
import java.util.Scanner;

import static Project_001.Presentation.BookMenu.lsBook;
import static Project_001.Presentation.CategoryMenu.lsCatalog;

public class Book implements IEntity, Serializable {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void input(Scanner scanner) {
        this.id = inputBookId(scanner);
        this.title = inputBookTitle(scanner);
        this.author = inputAuthor(scanner);
        this.publisher = inputPublisher(scanner);
        this.year = inputYear(scanner);
        this.description = inputDescription(scanner);
        this.categoryId = inputCatalogId(scanner);
    }

    @Override
    public void output() {
        System.out.printf("| %7s | %-30s | %15s | %20s | %12d | %24s | %29s |\n", this.id, this.title, this.author, this.publisher,
                this.year, this.description, displayCatalogNameById(categoryId));
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("| %7s | %30s | %15s | %20s | %12d | %24s | %29s |\n" +
                        "----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------", this.id, this.title, this.author, this.publisher,
                this.year, this.description, displayCatalogNameById(categoryId));
    }

    public String inputBookId(Scanner scanner) {
        System.out.println("Nhập vào mã sách: ");
        do {
            try {
                String bookId = scanner.nextLine();
                if (bookId.length() == 4) {
                    if (bookId.charAt(0) == 'B') {
                        boolean isExit = false;
                        for (int i = 0; i < lsBook.size(); i++) {
                            if (lsBook.get(i).getId().equals(bookId)) {
                                isExit = true;
                                break;
                            }
                        }
                        if (isExit) {
                            System.err.println("Mã sách đã tồn tại, vui lòng nhập lại!");
                        } else {
                            return bookId;
                        }
                    } else {
                        System.err.println("Mã sách bắt đầu là k tự B, Vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Mã sách gồm 4 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputBookTitle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sách: ");
        do {
            try {
                String title = scanner.nextLine();
                if ((title.length() >= 6) && (title.length() < 50)) {
                    boolean isExist = false;
                    for (int i = 0; i < lsBook.size(); i++) {
                        if (lsBook.get(i).getTitle().equalsIgnoreCase(title)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Tiêu đề sách đã tồn tại, vui lòng nhập lại!");
                    } else {
                        return title;
                    }
                } else {
                    System.err.println("Tiêu đề sách có từ 6-50 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập vào tên tác giả: ");
        do {
            try {
                String author = scanner.nextLine();
                if (author != null) {
                    return author;
                } else {
                    System.err.println("Tên tác giả không được bỏ trống, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputPublisher(Scanner scanner) {
        System.out.println("Nhập vào nhà xuất bản: ");
        do {
            try {
                String publisher = scanner.nextLine();
                if (publisher != null) {
                    return publisher;
                } else {
                    System.err.println("Nhà xuất bản không được bỏ trống, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        System.out.println("Nhập vào năm xuất bản: ");
        do {
            try {
                int year = Integer.parseInt(scanner.nextLine());
                int checkYear = Year.now().getValue();
                if ((year >= 1970) && (year <= checkYear)) {
                    return year;
                } else {
                    System.err.println("Năm xuất bản tối thiểu từ 1970-Nay, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sách: ");
        do {
            try {
                String description = scanner.nextLine();
                if (description != null) {
                    return description;
                } else {
                    System.err.println("Mô tả sách không được bỏ trống, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Chọn thể loại của sách: ");
        do {
            for (int i = 0; i < lsCatalog.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, lsCatalog.get(i).getName());
            }
            System.out.println("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                return lsCatalog.get(choice - 1).getId();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String displayCatalogNameById(int categoryId) {
        for (Category ctl : lsCatalog) {
            if (ctl.getId() == categoryId) {
                return ctl.getName();
            }
        }
        return " ";
    }

    public void updateBook(Scanner scanner) {
        //Cập nhật sách
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tiêu đề sách: ");
            System.out.println("2. Cập nhật tên tác giả: ");
            System.out.println("3. Cập nhật nhà xuất bản: ");
            System.out.println("4. Cập nhật năm xuất bản: ");
            System.out.println("5. Cập nhật mô tả sách: ");
            System.out.println("6. Cập nhật mã thể loại sách: ");
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4) && (choice != 5) && (choice != 6) && (choice != 7)) {
                }
            } catch (NumberFormatException ex) {
                System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    this.title = inputBookTitle(scanner);
                    break;
                case 2:
                    this.author = inputAuthor(scanner);
                    break;
                case 3:
                    this.publisher = inputPublisher(scanner);
                    break;
                case 4:
                    this.year = inputYear(scanner);
                    break;
                case 5:
                    this.description = inputDescription(scanner);
                    break;
                case 6:
                    this.categoryId = inputCatalogId(scanner);
                default:
                    isExit = false;
            }
        } while (isExit);
    }
}
