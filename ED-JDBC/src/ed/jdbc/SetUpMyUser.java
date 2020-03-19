/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jdbc;

import java.util.ArrayList;

/**
 *
 * @author luke
 */
public class SetUpMyUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB mydb = new MyDB();
        /*
            * drop table first for a clean start
            * may cause error if table does not exist
         */
        mydb.dropMyuserTable();
        mydb.createMyuserTable();
        
//        TODO: Complete Lab02
        //Add array of records
        ArrayList<Myuser> aList = prepareMyuserData();
        mydb.addRecords(aList);
        
//        TODO: P2.1 Task 2.1 
//      Add a single record
        Myuser aMyuser = prepareSingleMyuserData();
        mydb.createRecord(aMyuser);
        
//        TODO: P2.1 Task 2.2
//      Get a single record with userId
        String aUserId = "00003";
        mydb.getRecord(aUserId);
        
//        TODO: P2.1 Task 2.3
//      Update a single record with userId
        Myuser aUpdateMyuser = prepareUpdateForSingleMyuserData();
        boolean checkUpdate = mydb.updateRecord(aUpdateMyuser);
        System.out.print("\n Updated?:  " + checkUpdate);
        
        //        TODO: P2.1 Task 2.4
//      Delete a single record with userId
        aUserId = "000001";
        boolean checkDelete = mydb.deleteRecord(aUserId);
        System.out.print("\n Deleted?:  " + checkDelete + "\n");
        
        //TODO: P2.1 Task 3 
        //Make client program
        
        //Code here...
        
        //END: P2.1 Task 3
        //
    }
//        TODO: P2.1 Task 2.1
    public static Myuser prepareSingleMyuserData() {
    Myuser lMyuser = new Myuser("000007", "Testie McTester", "987654",
            "test@swin.edu.au", "0418926318", "Swinburne Test",
                "What is my Test?", "To work in database");
    
        return lMyuser;
    }
    
    //        TODO: P2.1 Task 2.3
    public static Myuser prepareUpdateForSingleMyuserData() {
    Myuser lMyuser = new Myuser("000007", "Testie McUpdater", "456789",
            "updated@swin.edu.au", "0418926318", "Swinburne Update",
                "What is my Test?", "To update record database");
    
        return lMyuser;
    }
    
//        TODO: P2.1 Task 1
    public static ArrayList<Myuser> prepareMyuserData() {
        ArrayList<Myuser> myList = new ArrayList<Myuser>();
        
        Myuser myuser1 = new Myuser("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        Myuser myuser2 = new Myuser("000002", "James T. Kirk", "234567",
                "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a",
                "What is my name?", "James");
        Myuser myuser3 = new Myuser("000003", "Sheldon Cooper", "345678",
                "scooper@swin.edu.au", "7654321098", "Swinburne EN512a",
                "What is my last name?", "Cooper");
        Myuser myuser4 = new Myuser("000004", "Clark Kent", "456789",
                "ckent@swin.edu.au", "6543210987", "Swinburne EN513a",
                "What is my last name?", "Kent");
        Myuser myuser5 = new Myuser("000005", "Harry Potter", "567890",
                "hpotter@swin.edu.au", "6543210987", "Swinburne EN514a",
                "What is my last name?", "Potter");
        
        myList.add(myuser1);
        myList.add(myuser2);
        myList.add(myuser3);
        myList.add(myuser4);
        myList.add(myuser5);
        return myList;
    }

}
