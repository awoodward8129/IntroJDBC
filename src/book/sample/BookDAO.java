/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.sample;

import java.util.ArrayList;
import java.util.List;
import book.sample.MySqlDbStrategy;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */
public class BookDAO implements DAOStrategy {
   
    private DbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public BookDAO(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    BookDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Book> getAllBooks() throws Exception{
    db.openConnection(this.driverClass, this.url, this.userName, this.password);
    List<Book> records = new ArrayList<>();
    List<Map<String,Object>> rawData = db.findAllRecords("book");
    for(Map rawRec: rawData){
    Book book = new Book();
    
    Object obj = rawRec.get("book_id");
    Integer i = Integer.parseInt(obj.toString());
    book.setBookId(i);
    
    obj = rawRec.get("title");
    String t = obj.toString();
    book.setTitle(t);
//    
    obj = rawRec.get("author");
    String a = obj.toString();
    book.setAuthor(a);
    
    obj = rawRec.get("page_count");
    Integer p = Integer.parseInt(obj.toString());
    book.setPageCount(p);
    
    obj = rawRec.get("publish_date");
    Date d= (Date) obj;
    book.setPublishDate(d);
    
    records.add(book);
    }
    
    db.closeConnection();
    return records;
    }
 

    @Override
    public void deleteByBookId(String tableName, String column, String value) throws Exception {
       
            db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.deleteRecordById(tableName, column, value);
            db.closeConnection();
    }
    
       @Override
    public void insertRecord(String tableName, String columns, String values) throws Exception {
            db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.deleteRecordById(tableName, columns, values);
            db.closeConnection();
    }
    
    @Override
    public void updateRecord(String tableName, String columnEqualsValue, String whereClause) throws Exception {
           db.openConnection(this.driverClass, this.url, this.userName, this.password);
            db.deleteRecordById(tableName, columnEqualsValue, whereClause);
            db.closeConnection();
    }
    
    public static void main(String[] args) throws Exception {
        
        BookDAO dao = new BookDAO(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin");
        
   
//       dao.deleteByBookId("book", "book_id", "3");
        dao.insertRecord("book", "title,author,page_count, publish_date", "'JAVA is the BEST','Alejando Inaratu', 200, '2005-02-15'");
        dao.updateRecord("book ", "title='Update Works' ","book_id=1");
           List<Book> books = dao.getAllBooks();
       for(Book b :books){
           System.out.println(b);
       }
    }

    

 
}
