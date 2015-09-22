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
public interface DAOStrategy {

    List<Book> getAllBooks() throws Exception;
    
    void deleteByBookId(String tableName, String column, String value) throws Exception;
    void insertRecord(String tableName, String columns, String values)throws Exception;
    void updateRecord(String tableName, String columnEqualsValue, String whereClause)throws Exception;
    
}
