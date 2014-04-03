package dataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// For the blob to-> DB method
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.Arrays;

public class PicturesMapper {

    public PicturesMapper() {

    }

    /**
     *
     * @param currentFileLocation
     * @return file as byte[]
     * @throws IOException
     */
    public byte[] read(String currentFileLocation) throws IOException {
        byte[] file;

        Path path = Paths.get(currentFileLocation);
        file = Files.readAllBytes(path);

        return file;
    }

    public int writeToDB(Connection con, int id, byte[] file) {
        String sql = "INSERT INTO PICTURES VALUES(?,?)";
        int rowsAffected = 0;

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setBinaryStream(2, new ByteArrayInputStream(file), file.length);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in readToDB");
        }

        return rowsAffected;
    }

    public byte[] getSpecificFile(Connection con, int id) throws SQLException {
        byte[] file;
        
        String sql = "SELECT BLOB_PICTURES "
                + "FROM PICTURES "
                + "WHERE ID = " + id;
        
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            Blob blob = rs.getBlob("BLOB_PICTURES");

            file = blob.getBytes(1L, (int) blob.length());
            return file;
        }
        return null;
    }

    public boolean write(byte[] file, String fileDest) {

        Path path = Paths.get(fileDest);

        try {
            Files.write(path, file);
            return true;
        } catch (IOException ex) {
            System.out.println("Problem occured when trying to write file!");
            return false;
        }
        
    }

}
