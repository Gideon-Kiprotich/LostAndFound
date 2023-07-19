import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class database {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lost_and_found";
    static final String USER = "root";
    static final String PASSWORD = "";
    private Statement statement;
    private Connection connection;


    // The login function
public boolean login(String Role, String Password) {
    boolean success = false;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "SELECT * FROM registration WHERE Role = ? AND Password = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, Role);
        statement.setString(2, Password);

        System.out.println(statement); // Print the executed statement (optional)

        rs = statement.executeQuery();
        success = rs.next();
        rs.close();

       
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return success;
}



    // The register function to the registration table
    public boolean register(String name, int school_id, String email, String password, String faculty, int national_id, String address, String gender, int phone, String role) {
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "INSERT INTO registration (name, school_id, email, password, faculty, national_id, address, gender, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, school_id);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, faculty);
            preparedStatement.setInt(6, national_id);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, gender);
            preparedStatement.setInt(9, phone);
            preparedStatement.setString(10, role);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            
            preparedStatement.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return success;
    }

    // Use username to get the user role in registration table and store the role in a variable
    public String getRole(String Role) {
        String role = "";
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT role FROM registration WHERE Role = '" + Role + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                role = rs.getString("role");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    // The add function to the lost and found table
    public boolean add(String item, String Location, String Date, String Color, String Type, String Additional_info){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "INSERT INTO lost_and_found (item, Location, Date, Color, Type, Additional_info) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, Location);
            preparedStatement.setString(3, Date);
            preparedStatement.setString(4, Color);
            preparedStatement.setString(5, Type);
            preparedStatement.setString(6, Additional_info);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            
            preparedStatement.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return success;
    }

    // delete function to the lost and found table using the id
    public boolean delete(int Item_id){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "DELETE FROM lost_and_found WHERE Item_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Item_id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            
            preparedStatement.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return success;
    }

    // Update function with id included
    public boolean update(int Item_id, String item, String Location, String Date, String Color, String Type, String Additional_info){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "UPDATE lost_and_found SET item = ?, Location = ?, Date = ?, Color = ?, Type = ?, Additional_info = ? WHERE Item_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, Location);
            preparedStatement.setString(3, Date);
            preparedStatement.setString(4, Color);
            preparedStatement.setString(5, Type);
            preparedStatement.setString(6, Additional_info);
            preparedStatement.setInt(7, Item_id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            
            preparedStatement.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return success;
    }

    // Fetch data from the lost and found table and store it in a list
    public List<LostAndFound> getLostAndFound() {
        List<LostAndFound> lostAndFound = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT * FROM lost_and_found";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                LostAndFound lost = new LostAndFound();
                lost.setItem_id(rs.getInt("Item_id"));
                lost.setItem(rs.getString("item"));
                lost.setLocation(rs.getString("Location"));
                lost.setDate(rs.getString("Date"));
                lost.setColor(rs.getString("Color"));
                lost.setType(rs.getString("Type"));
                lost.setAdditional_info(rs.getString("Additional_info"));
                lostAndFound.add(lost);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lostAndFound;
    }


}

    



