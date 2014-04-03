package dataSource;

import java.io.IOException;
import java.sql.SQLException;

public class DbFacade {
    
    public static void main(String[] args) {
        DbConnector db = new DbConnector();
        PicturesMapper blob = new PicturesMapper();
        byte[] file;
        String fileLocation = "/Users/ThomasHedegaard/Desktop/testImage.JPG";
        String fileDest = "/Users/ThomasHedegaard/Desktop/ModtagneFilerFraDB/secondTestImageFromDB.JPG";
        
        db.loadDBdriver();
        db.establishConnection();
        
//        try{
//        file = blob.read(fileLocation);
//        int rowsAffected = blob.writeToDB(db.getConnection(), 5, file);
//            System.out.println("Rows affected: " + rowsAffected);
//        }catch(IOException e){
//            System.out.println("Error when reading file");
//        }
    
        
        try{
        file = blob.getSpecificFile(db.getConnection(), 3);
            System.out.println(blob.write(file, fileDest));
        }
        catch(SQLException e){
            System.out.println("Error when trying to recieve file from DB");
        }
        
        
        
        
        
        
    }
    
    
    
}
