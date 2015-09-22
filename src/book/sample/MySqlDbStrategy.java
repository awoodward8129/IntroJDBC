/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class MySqlDbStrategy implements DbStrategy {
    
        private String driverClass;
	private String url;
	private String userName;
	private String password;
        private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, 
            String userName, String password) throws Exception {
            Class.forName (driverClass);
            conn = DriverManager.getConnection(url,userName, password);
    }
    
    @Override
    public void closeConnection()throws SQLException {
        conn.close();
    }
    
    @Override
    public List<Map<String,Object>> findAllRecords(String tableName) throws SQLException{
        List<Map<String,Object>> recordList = new ArrayList();
        String sql ="SELECT * FROM " + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()){
            Map<String,Object> record = new HashMap<>();
            for(int i=1; i <= columnCount; i++){
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            recordList.add(record);
        }  
        return recordList;
    }
    
    @Override
    public void deleteRecordById(String tableName, String column, Object value) throws Exception{
        value.toString();
        String sql ="DELETE FROM " + tableName + " WHERE " + column + "=" + "'" +value.toString() + "'";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
    
  @Override
    public void insertRecord(String tableName, String columns, String values) throws Exception {
         String sql ="INSERT INTO " + tableName + " (" +  columns + ")" + "VALUES (" + values + ")";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
       
    }
    
      @Override
    public void updateRecord(String tableName, String columnEqualsValue, String whereClause) throws Exception {
        String sql= "UPDATE " + tableName + "SET " + columnEqualsValue +"WHERE " + whereClause +";";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
    
    public static void main(String[] args) throws Exception {
          MySqlDbStrategy db = new MySqlDbStrategy();
          db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin");          
//          db.deleteRecordById("book", "book_id", "1");
//          db.insertRecord("book", "title,author,page_count, publish_date", "'JAVA is the BEST','Alejando Inaratu', 200, '2005-02-15'");
          db.updateRecord("book ", "title='Update Works' ","book_id=1");
           List<Map<String,Object>> records = db.findAllRecords("book");
          for (Map record :records ){
              System.out.println(record);
          }
        db.closeConnection();
    }

  

    
}
