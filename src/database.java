import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

       return true;
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
    public void getRole(String Role) {
        String role = "";
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT role FROM registration WHERE Role = '" + Role + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                role = rs.getString("Role");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LostAndFound lost = new LostAndFound();
        lost.setRole(role);
    }

    // The add function to the lost and found table
    public boolean add(int Item_id, String Item, String Location, String Date, String Color, String Type, String Additional_info){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "INSERT INTO items (Item_id, Item, Location, Date, Color, Type, Additional_info) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Item_id);
            preparedStatement.setString(2, Item);
            preparedStatement.setString(3, Location);
            preparedStatement.setString(4, Date);
            preparedStatement.setString(5, Color);
            preparedStatement.setString(6, Type);
            preparedStatement.setString(7, Additional_info);
            
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

    //View columns in combo box from the com.munyao.duka.database
    public void viewColumn(JComboBox comboBox) throws SQLException {
        boolean success=false;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT Type FROM items;";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);

            comboBox.removeAllItems();
            while(rs.next())
            {
                comboBox.addItem(rs.getString(1));
            }
            success = rs.next();
            rs.close();

            statement.close();
            connection.close();
        } catch (SQLException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void search(JTable table1, String item, String location, String date, String color, String type) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM items WHERE Item LIKE ? OR Location = ? OR Date LIKE ? OR Color LIKE ? OR Type = ?");
            insert.setString(1, "%" + item + "%");
            insert.setString(2, "%" + location + "%");
            insert.setString(3, "%" + date + "%");
            insert.setString(4, "%" + color + "%");
            insert.setString(5, "%" + type + "%");
            ResultSet resultSet = insert.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
    
            // Clear existing rows from the table
            model.setRowCount(0);
    
            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
            }
    
            String a, b, c, d, e, f, g;
            while (resultSet.next()) {
                a = resultSet.getString(1);
                b = resultSet.getString(2);
                c = resultSet.getString(3);
                d = resultSet.getString(4);
                e = resultSet.getString(5);
                f = resultSet.getString(6);
                g = resultSet.getString(7);
                String[] row = {a, b, c, d, e, f, g};
                model.addRow(row);
            }
    
            // Close the ResultSet and PreparedStatement
            resultSet.close();
            insert.close();
    
            // Close the database connection
            connection.close();
    
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    


    // delete function to the lost and found table using the id
    public boolean delete(int Item_id){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "DELETE FROM items WHERE Item_ID = ?";
            
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

    public void table_update(JTable table1) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM items");
            ResultSet resultSet = insert.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
    
            // Clear existing rows from the table
            model.setRowCount(0);
    
            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
            }
            
            String a, b, c, d, e;
            while (resultSet.next()) {
                a = resultSet.getString(1);
                b = resultSet.getString(2);
                c = resultSet.getString(3);
                d = resultSet.getString(4);
    
                String[] row = {a, b, c, d};
                model.addRow(row);
            }
            
            // Close the ResultSet and PreparedStatement
            resultSet.close();
            insert.close();
            
            // Close the database connection
            connection.close();
    
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    


    // Update function with id included
    public boolean update(int Item_id, String item, String Location, String Date, String Color, String Type, String Additional_info){
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String query = "UPDATE items SET item = ?, Location = ?, Date = ?, Color = ?, Type = ?, Additional_info = ? WHERE Item_ID = ?";
            
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

    // Fetch data from the lost and found table and store each column in its variable in LostAndFound class
    public void fetch(int Item_id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement insert = connection.prepareStatement("SELECT * FROM items WHERE Item_ID = ?");
            insert.setInt(1, Item_id);
            ResultSet resultSet = insert.executeQuery();
            while (resultSet.next()) {
                LostAndFound lost = new LostAndFound();
                lost.setItem_id(resultSet.getInt("Item_ID"));
                lost.setItem(resultSet.getString("Item"));
                lost.setLocation(resultSet.getString("Location"));
                lost.setDate(resultSet.getString("Date"));
                lost.setColor(resultSet.getString("Color"));
                lost.setType(resultSet.getString("Type"));
                lost.setAdditional_info(resultSet.getString("Additional_info"));
            }
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}

    



