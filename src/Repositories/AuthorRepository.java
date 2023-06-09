package Repositories;

import Classes.Author;
import Support.AuditLogs;
import Support.DatabaseHandler;

import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public final class AuthorRepository {
    public List<Author> authorList;

    public AuthorRepository(){
        authorList = new ArrayList<>();
    }

    public void insertAuthor(Author author) throws SQLException {
        String sql2 = "SELECT * FROM authors WHERE name = ?";
        String sql = "INSERT INTO authors (name, birthYear, numberOfPublications,titlesInStore) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseHandler.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
            pstmt2.setString(1, author.getName());
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                author.setId(rs.getInt("idAuthor"));
                System.out.println("Autorul exista deja.");
            } else {
                pstmt.setString(1, author.getName());
                pstmt.setInt(2, author.getBirthYear());
                pstmt.setInt(3, author.getNumberOfPublications());
                pstmt.setInt(4, author.getTitlesInStore());
                pstmt.executeUpdate();

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    author.setId(generatedId);
                }
                Calendar calendarAudit = Calendar.getInstance();
                java.util.Date  dateUtilAudit = calendarAudit.getTime();
                AuditLogs auditLogs = new AuditLogs();
                auditLogs.insertAudit("Inserare autor efectuată",dateUtilAudit);
            }
        }
    }

    public void deleteAuthor(Author author) throws SQLException
    {

        if(author.getId() == -1)
            return;

        String sql = "DELETE FROM authors WHERE idAuthor = ?";
        try (Connection con = DatabaseHandler.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,author.getId());
            int result = pstmt.executeUpdate();
        }
        authorList.remove(author);
        Calendar calendarAudit = Calendar.getInstance();
        java.util.Date  dateUtilAudit = calendarAudit.getTime();
        AuditLogs auditLogs = new AuditLogs();
        auditLogs.insertAudit("Ștergere autor efectuată.",dateUtilAudit);
    }

    public void getAuthors() throws SQLException {
        String sql = "SELECT * FROM authors";
        Connection con = DatabaseHandler.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        authorList.clear();
        while(rs.next())
        {
            int idAuthor = rs.getInt("idAuthor");
            String name = rs.getString("name");
            int birthYear = rs.getInt("birthYear");
            int numberOfPublications = rs.getInt("numberOfPublications");
            int titlesInStore = rs.getInt("titlesInStore");

            Author crAuthor = new Author(idAuthor,name,birthYear,numberOfPublications,titlesInStore);
            authorList.add(crAuthor);
        }
    }

    public Author inputAuthorDetails(){
        Scanner in = new Scanner(System.in);

        System.out.println("Introduceți numele autorului:");
        String name = in.nextLine();

        System.out.println("Introduceți anul nașterii autorului:");
        int birthYear = in.nextInt();
        in.nextLine();

        System.out.println("Introduceți numărul de publicații (aproximativ) al autorului:");
        int numberOfPublications = in.nextInt();
        in.nextLine();

        Author author = new Author(-1,name,birthYear,numberOfPublications,0);

        return author;
    }
}
