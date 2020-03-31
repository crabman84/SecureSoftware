/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author luke
 */
public class MyDB {

    public static Connection getConnection() throws SQLException,
            IOException {
        System.setProperty("jdc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE MYUSER ( "
                    + " UserId CHAR(6) CONSTRAINT PK_CUSTOMER PRIMARY KEY, "
                    + " Name CHAR(30), Password CHAR(6), Email CHAR(30), "
                    + " Phone CHAR(10), Address CHAR(60), "
                    + " SecQn CHAR(60), SecAns CHAR(60))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void dropMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE MYUSER");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void addRecords(ArrayList<Myuser> myUsers) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            //HOw do these question marks work?
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (Myuser myuser : myUsers) {
                pStmnt.setString(1, myuser.getUserid());
                pStmnt.setString(2, myuser.getName());
                pStmnt.setString(3, myuser.getPassword());
                pStmnt.setString(4, myuser.getEmail());
                pStmnt.setString(5, myuser.getPhone());
                pStmnt.setString(6, myuser.getAddress());
                pStmnt.setString(7, myuser.getSecQn());
                pStmnt.setString(8, myuser.getSecAns());
                
                
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
    
    //TODO: P2.1 task 2
    public boolean createRecord(Myuser pMyuser) {
        Connection lCnnct = null;
        PreparedStatement  lPreStmnt = null;
        boolean lRecordAlreadyExists = false;
        try{
            lCnnct = getConnection();
            String lPreQueryStatement = "INSERT INTO MYUSER VALUES(?, ?, ?, ?, ?, ?, ?, ? )";
            lPreStmnt = lCnnct.prepareStatement(lPreQueryStatement);
            
            lPreStmnt.setString(1, pMyuser.getUserid());
            lPreStmnt.setString(2, pMyuser.getName());
            lPreStmnt.setString(3, pMyuser.getPassword());
            lPreStmnt.setString(4, pMyuser.getEmail());
            lPreStmnt.setString(5, pMyuser.getPhone());
            lPreStmnt.setString(6, pMyuser.getAddress());
            lPreStmnt.setString(7, pMyuser.getSecQn());
            lPreStmnt.setString(8, pMyuser.getSecAns());
            
            int lRowCount = lPreStmnt.executeUpdate();
            if (lRowCount == 0) {
                throw new SQLException("Cannot insert records!");
            }
                
            lRecordAlreadyExists = true;
        }
        catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            if(lPreStmnt != null){
                try{
                    lPreStmnt.close();
                    return lRecordAlreadyExists;
                }
                catch(SQLException e){
                }
            }
        }
////        Should the below line exist??? I don't think so
        return lRecordAlreadyExists;
    }
    
    
    
    public Myuser getRecord(String pUserId) {
        Myuser lMyuser = null;
        Connection lCnnct = null;
        Statement lStmnt = null;
        //OR
        String lQuery = "SELECT * FROM MYUSER WHERE USERID = '000003'";
        try{
            lCnnct = getConnection();            
            //Normal Statement
            lStmnt = lCnnct.createStatement();
            ResultSet rs = lStmnt.executeQuery(lQuery);
            
            if(rs.next()){
                String lUserId = rs.getString("USERID");
                String lName = rs.getString("NAME");
                String lPassword = rs.getString("PASSWORD");
                String lEmail = rs.getString("EMAIL");
                String lPhone = rs.getString("PHONE");
                String lAddress = rs.getString("ADDRESS");
                String lQN = rs.getString("SECQN");
                String lAns = rs.getString("SECANS");
                System.out.println(lUserId + "\t" + lName +
                               "\t" + lPassword + "\t" + lEmail +
                               "\t" + lPhone + "\t" + lAddress +
                               "\t" + lQN + "\t" + lAns);
            }
        }
        catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            if(lStmnt != null){
                try{
                    lStmnt.close();
                }
                catch(SQLException ex){
                }
            }
            if(lCnnct != null){
                try{
                    lCnnct.close();
                }
                catch(SQLException ex){
                }
            }
        }
        return lMyuser;
    }
    
    public boolean updateRecord(Myuser pMyuser){
        boolean lRecordExists = false;
        Connection lCnnct = null;
        PreparedStatement lPreStmnt = null;
        String lPreQueryStatement = "UPDATE MYUSER "
                + "SET NAME = ?, PASSWORD = ?, EMAIL = ?, "
                + "PHONE = ?, ADDRESS = ?, SECQN = ?, SECANS = ?"
                + "  WHERE USERID = ? ";
        
        // 8 variablaes
    
        try{
            lCnnct = getConnection();            
            //Normal Statement
            lPreStmnt = lCnnct.prepareStatement(lPreQueryStatement);
            
            lPreStmnt.setString(1, pMyuser.getName());
            lPreStmnt.setString(2, pMyuser.getPassword());
            lPreStmnt.setString(3, pMyuser.getEmail());
            lPreStmnt.setString(4, pMyuser.getPhone());
            lPreStmnt.setString(5, pMyuser.getAddress());
            lPreStmnt.setString(6, pMyuser.getSecQn());
            lPreStmnt.setString(7, pMyuser.getSecAns());
            lPreStmnt.setString(8, pMyuser.getUserid());
            
            
            //What does this part do???
            int lRowCount = lPreStmnt.executeUpdate();
            if (lRowCount == 0) {
                throw new SQLException("Cannot update record!");
            }
                
            lRecordExists = true;
                    }
        catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            if(lPreStmnt != null){
                try{
                    lPreStmnt.close();
                }
                catch(SQLException ex){
                }
            }
            if(lCnnct != null){
                try{
                    lCnnct.close();
                    return lRecordExists;
                }
                catch(SQLException ex){
                }
            }
        }
        return lRecordExists;
    }
    
    public boolean deleteRecord(String pUserId){
        boolean lRecordExisted = false;
        Connection lCnnct = null;
        PreparedStatement lPreStmnt = null;
        String lPreQueryStmnt = "DELETE FROM MYUSER WHERE USERID = ?";  

        try{
            lCnnct = getConnection();
            //Prepared Statement
            lPreStmnt = lCnnct.prepareStatement(lPreQueryStmnt);            
            lPreStmnt.setString(1, pUserId);
//                         
            int lRowCount = lPreStmnt.executeUpdate();
            if (lRowCount == 0) {
                throw new SQLException("Cannot delete record!");
            }
            lRecordExisted = true;
        }
        catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex.getNextException();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            
            if(lPreStmnt != null){
                try{
                    lPreStmnt.close();
                    return lRecordExisted;
                }
                catch(SQLException ex){
                    //Shouldn't there be some content here????
                    while(ex != null){
                        ex.printStackTrace();
                        ex.getNextException();
                    }
                }
            }
        }
        
        return lRecordExisted;
    }
    
}

