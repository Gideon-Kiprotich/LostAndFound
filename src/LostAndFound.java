import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LostAndFound extends JFrame implements ActionListener {
    private JPanel panel1;
    private JPanel login;
    private JPanel registration;
    private JPanel admin;
    private JTextField txtLoginUsername;
    private JTextField txtLoginPassword;
    private JLabel username;
    private JLabel password;
    private JButton btnLoginSubmit;
    private JTextField txtRegistrationName;
    private JTextField txtRegistrationSchoolID;
    private JTextField txtRegistrationEmail;
    private JTextField txtRegistrationPassword;
    private JTextField txtRegistrationFaculty;
    private JTextField txtRegistrationNationalID;
    private JTextField txtRegistrationAddress;
    private JTextField txtRegistrationGender;
    private JTextField txtRegistrationPhone;
    private JTextField txtRegistrationRole;
    private JButton btnRegistrationSubmit;
    private JLabel lblRegName;
    private JLabel lblRegSchoolID;
    private JLabel lblRegEmail;
    private JLabel lblRegPassword;
    private JLabel lblRegFaculty;
    private JLabel lblRegNationalID;
    private JLabel lblRegAddress;
    private JLabel lblRegGender;
    private JLabel lblRegPhone;
    private JLabel lblRegRole;
    private JPanel student;
    private JButton btnAdminView;
    private JButton btnAdminUpdate;
    private JButton btnAdminDelete;
    private JButton btnAdminAdd;
    private JTextField txtStudItem;
    private JTextField txtAminLocation;
    private JTextField txtAdminDate;
    private JTextField txtAdminColor;
    private JTextField txtAdminType;
    private JTextField txtAdminInfo;
    private JTextField txtAdmintemID;
    private JLabel lblStudItemID;
    private JLabel lblStudItem;
    private JLabel lblStudLoca;
    private JLabel lblStudDate;
    private JLabel lblStudColor;
    private JLabel lblStudType;
    private JLabel lblStudInfo;
    private JButton btnRegLogin;
    private JPanel user;
    private String role;

    public void setItem_id(int int1) {
        this.txtAdmintemID.setText(String.valueOf(int1));
    }

    public void setItem(String string) {
        this.txtStudItem.setText(string);
    }

    public void setLocation(String string) {
        this.txtAminLocation.setText(string);
    }

    public void setDate(String string) {
        this.txtAdminDate.setText(string);
    }

    public void setColor(String string) {
        this.txtAdminColor.setText(string);
    }

    public void setType(String string) {
        this.txtAdminType.setText(string);
    }

    public void setAdditional_info(String string) {
        this.txtAdminInfo.setText(string);
    }
    public void setRole(String role) {
        this.role = role;
    }


    database db = new database();


    public LostAndFound(){
        this.setTitle("Lost and Found");
        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel to the frame
        this.add(panel1);

        // Add action listener to the buttons
        btnLoginSubmit.addActionListener(this);
        btnRegistrationSubmit.addActionListener(this);
        btnAdminAdd.addActionListener(this);
        btnAdminDelete.addActionListener(this);
        btnAdminUpdate.addActionListener(this);
        btnAdminView.addActionListener(this);
        btnRegLogin.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLoginSubmit){
            try{
                String Role = txtLoginUsername.getText();
                String Password = txtLoginPassword.getText();
                if(db.login(Role, Password)){
                    JOptionPane.showMessageDialog(null,"You have logged in successfully.\n Welcome");
                    login.setVisible(false);
                    admin.setVisible(true);
                   
                } else{
                    JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(e.getSource() == btnRegistrationSubmit) {
            try{
                String name = txtRegistrationName.getText();
                int school_id = Integer.parseInt(txtRegistrationSchoolID.getText());
                String email = txtRegistrationEmail.getText();
                String password = txtRegistrationPassword.getText();
                String faculty = txtRegistrationFaculty.getText();
                int national_id = Integer.parseInt(txtRegistrationNationalID.getText());
                String address = txtRegistrationAddress.getText();
                String gender = txtRegistrationGender.getText();
                int phone = Integer.parseInt(txtRegistrationPhone.getText());
                String role = txtRegistrationRole.getText();

                if(db.register(name, school_id, email, password, faculty, national_id, address, gender, phone, role)){
                    JOptionPane.showMessageDialog(null,"You have registered successfully.\n Welcome");
                    registration.setVisible(false);
                    login.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(e.getSource() == btnRegLogin){
            registration.setVisible(false);
            login.setVisible(true);
        }

        if(e.getSource() == btnAdminAdd){
            try{
                int item_id = Integer.parseInt(txtAdmintemID.getText());
                String item = txtStudItem.getText();
                String location = txtAminLocation.getText();
                String date = txtAdminDate.getText();
                String color = txtAdminColor.getText();
                String type = txtAdminType.getText();
                String info = txtAdminInfo.getText();

                if(db.add(item_id, item, location, date, color, type, info)){
                    JOptionPane.showMessageDialog(null,"You have added an item successfully.\n Welcome");
                    admin.setVisible(false);
                    student.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(e.getSource() == btnAdminDelete){
            try{
                int item_id = Integer.parseInt(txtAdmintemID.getText());

                if(db.delete(item_id)){
                    JOptionPane.showMessageDialog(null,"You have deleted an item successfully.\n Welcome");
                    admin.setVisible(false);
                    student.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(e.getSource() == btnAdminUpdate){
            try{
                int item_id = Integer.parseInt(txtAdmintemID.getText());
                String item = txtStudItem.getText();
                String location = txtAminLocation.getText();
                String date = txtAdminDate.getText();
                String color = txtAdminColor.getText();
                String type = txtAdminType.getText();
                String info = txtAdminInfo.getText();

                if(db.update(item_id, item, location, date, color, type, info)){
                    JOptionPane.showMessageDialog(null,"You have updated an item successfully.\n Welcome");
                    admin.setVisible(false);
                    student.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(e.getSource() == btnAdminView){
        try{
            db.getLostAndFound();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

}


