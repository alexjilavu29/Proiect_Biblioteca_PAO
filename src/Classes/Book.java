package Classes;

public class Book {
    private int idBook;
    private String title;
    private String author;
    private int year;
    private String genre;
    private int copiesInStore;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
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

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopiesInStore() {
        return copiesInStore;
    }
    public void setCopiesInStore(int copiesInStore) {
        this.copiesInStore = copiesInStore;
    }

    public Book() {
        this.idBook= -1;
        this.title = "N/A";
        this.author = "N/A";
        this.year = 0;
        this.genre = "N/A";
        this.copiesInStore= 0;
    }
    public Book(int idBook, String title, String author, int year, String genre, int copiesInStore) {
        this.idBook= idBook;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.copiesInStore= copiesInStore;
    }

    @Override
    public String toString() {
        return "Classes.Book{" +
                "idBook='" + idBook + '\'' +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", copiesInStore='" + copiesInStore +
                '}';
    }




}
