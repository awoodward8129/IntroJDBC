/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.sample;

import java.util.List;

/**
 *
 * @author Alex
 */
public class BookService {
    
    DAOStrategy dao;

    public BookService(DAOStrategy dao) {
        this.dao = dao;
    }
    
    public List<Book> getAllBooks() throws Exception{
    return dao.getAllBooks();
    }
    
    
    public void deleteByBookId(String tableName, String columnName, String value) throws Exception{
    dao.deleteByBookId(tableName, columnName, value);
    }
    
     public void insertRecord(String tableName, String columns, String values) throws Exception{
    dao.insertRecord(tableName, columns, values);
    }
    
     public void updateRecord(String tableName, String columnEqualsValue, String whereClause) throws Exception{
     dao.updateRecord(tableName, columnEqualsValue, whereClause);
     }
    public static void main(String[] args) throws Exception { 
        BookService service = new BookService(new BookDAO(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin"));
//       service.deleteByBookId("book", "book_id", "3");
//       service.insertRecord("book", "title,author,page_count, publish_date", "'JAVA is the BEST','Alejando Inaratu', 200, '2005-02-15'");
       service.updateRecord("book ", "title='Update Works' ","book_id=1");
        for(Book b: service.getAllBooks()){
            System.out.println(b);
        }
    }
}
