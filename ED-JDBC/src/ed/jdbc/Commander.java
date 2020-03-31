///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///*Reference:
//    Code used from Standard Java Command pattern
//    One version found here: 
//    https://en.wikipedia.org/wiki/Command_pattern#Java
// */
//package ed.jdbc;
//import static ed.jdbc.MyDB.getConnection;
//import java.util.HashMap;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
///**
// *
// * @author luke
// */
//
//
//public class Commander {
//    
//    /** The Command interface */
//
//    
//}
//
//
//interface Command {
//
//    
//    void execute();
////    void execute(Myuser pMyuser);
////    void execute(String input);
//}
//
//
///** DIRECTOR (or INVOKER) class **/
//class SqlManager {
//    private final HashMap<String, Command> commandMap = new HashMap<>();
//    
//    public void register(String commandName, Command command) {
//        commandMap.put(commandName, command);
//    }
//    
//    public void execute(String commandName) {
//        Command command = commandMap.get(commandName);
//        if (command == null) {
//            throw new IllegalStateException("no command registered for " + commandName);
//        }
//        command.execute();
//    }
//}
//
///** RECEIVER class **/
//class Table extends tryQuery{
//    
//    Connection _Cnnct = null;
//    Statement _Stmnt = null;
//    PreparedStatement _PreStmnt = null;
//    String _Query;
//
//    
//    public void addRecords() {
//        System.out.println("The light is on");
//    }
//    
//    public boolean createRecord(Myuser pMyuser) {
//        
//        
//        tryQuery();
//        return false;
//    }
//    
//    public Myuser getRecord(String pUserId) {
//        
//    }
//    
//    public boolean updateRecord(Myuser pMyuser) {
//        return false;
//    }
//        
//
//
//    public boolean deleteRecord(Myuser pMyuser) {
//        return false;
//    }
//
//
//    public void turnOff() {
//        System.out.println("The light is off");
//    }
//    
//        
//    private void closConnection(){
//        if(_Stmnt != null){
//                try{
//                    _Stmnt.close();
//                }
//                catch(SQLException ex){
//                }
//            }
//        if(_Cnnct != null){
//            try{
//                _Cnnct.close();
//            }
//            catch(SQLException ex){
//            }
//        }
//    }
////    private void printStackTrace(){
////        catch(SQLException ex){
////        while(ex != null){
////                ex.printStackTrace();
////                ex = ex.getNextException();
////            }    
////        }
////    }
//    
//    
//
//}
//
//class createRecord extends tryQuery{
//    
//    boolean _RecordAlreadyExists = false;
//    
//    @Override       //Command
//    public void execute(){
//        
//        try{
//            _Cnnct = getConnection();
//            String lPreQueryStatement = "INSERT INTO MYUSER VALUES(?, ?, ?, ?, ?, ?, ?, ? )";
//            _PreStmnt = _Cnnct.prepareStatement(lPreQueryStatement);
//
//            _PreStmnt.setString(1, _myUser.getUserid());
//            _PreStmnt.setString(2, _myUser.getName());
//            _PreStmnt.setString(3, _myUser.getPassword());
//            _PreStmnt.setString(4, _myUser.getEmail());
//            _PreStmnt.setString(5, _myUser.getPhone());
//            _PreStmnt.setString(6, _myUser.getAddress());
//            _PreStmnt.setString(7, _myUser.getSecQn());
//            _PreStmnt.setString(8, _myUser.getSecAns());
//
//            int lRowCount = _PreStmnt.executeUpdate();
//            if (lRowCount == 0) {
//                throw new SQLException("Cannot insert records!");
//            }
//
//            _RecordAlreadyExists = true;
//        }
//        catch(SQLException ex){
//            while(ex != null){
//                ex.printStackTrace();
//                ex = ex.getNextException();
//            }
//        }
//        catch(IOException ex){
//            ex.printStackTrace();
//        }
//        finally{
//            if(_PreStmnt != null){
//                try{
//                    _PreStmnt.close();
//                }
//                catch(SQLException e){
//                }
//            }
//        }
//    }
//    
//}
//
//
//abstract class tryQuery implements Command{ 
//    Connection _Cnnct = null;
//    PreparedStatement  _PreStmnt = null;
//    
//    String _PreQueryStatement;
////    = "INSERT INTO MYUSER VALUES(?, ?, ?, ?, ?, ?, ?, ? )";
//    Myuser _myUser;
//            
//    public tryQuery(Myuser pMyuser, String pQueryString){
//        this._myUser = pMyuser;
//        this._PreQueryStatement = pQueryString;
//        System.out.println("test tryQuery Constructor");
//    }
//    
//    public int countStatementQuestionMarks(){
//        int lQuestionMarkCount = 0;
//        
//        for(int i=0;i<_PreQueryStatement.length();i++){
//            switch(_PreQueryStatement.charAt(i)){
//                case '?':
//                    lQuestionMarkCount++;
//                    break;
//            }
//        }
//        return lQuestionMarkCount;
//    }
//    
//    @Override       //Command
//    public void execute(){
//        System.out.println("printing execute from abstract class");
//    }
//    
//    public void implementQuery() {
//
//        try{
//            _Cnnct = getConnection();
//            _PreStmnt = _Cnnct.prepareStatement(_PreQueryStatement);
//            
//            execute();
//        }
//        catch(SQLException ex){
//            while(ex != null){
//                ex.printStackTrace();
//                ex = ex.getNextException();
//            }
//        }
//        catch(IOException ex){
//            ex.printStackTrace();
//        } 
//       finally{
//            if(_PreStmnt != null){
//                try{
//                    _PreStmnt.close();
//                }
//                catch(SQLException e){
//                }
//            }
//        }
//    }
//}
//
//public static Myuser prepareSingleMyuserData() {
//Myuser lMyuser = new Myuser("000007", "Testie McTester", "987654",
//        "test@swin.edu.au", "0418926318", "Swinburne Test",
//            "What is my Test?", "To work in database");
//
//    return lMyuser;
//}