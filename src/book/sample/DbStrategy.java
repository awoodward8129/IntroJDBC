/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.sample;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public interface DbStrategy {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws Exception;
    
   void deleteRecordById(String tableName, String column, Object value) throws Exception;
   
   void insertRecord(String tableName, String columns, String values)throws Exception;
   
   void updateRecord(String tableName, String columnEqualsValue, String whereClause)throws Exception;
    
}
