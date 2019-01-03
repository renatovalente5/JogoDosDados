import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.awt.event.ActionEvent;

public class Login extends JogoDosDados3 {
  private final JPanel contentPanel = new JPanel();
  private JTextField username;
  private JPasswordField password;
  protected static String _user="", _pass="", _score="", _achievements="", _achievements2="";
  protected static int _numPlay;
  private JLabel lblValidation, serverDownLabel;
  protected ArrayList<User> usersList;
  protected static JLabel records;
  protected static JLabel top;
  protected static JLabel top1, top2, top3, date1, date2, date3;
  protected static String first = "";
  protected static char[] arrayAchiev, arrayAchiev2 = new char[12];
  protected static int bestRecord = 0;


  public static void main(String[] args) {
    try {
      Login dialog = new Login();
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Login() {
	//readUsers();
	//setBounds(100, 100, 450, 300);
	//setMinimumSize(new Dimension(450, 300)););
	usersList = new ArrayList<User>();
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    
      JLabel lblUsername = new JLabel("UserName");
      lblUsername.setBounds(89, 76, 63, 20);
      contentPanel.add(lblUsername);

      JLabel lblPassword = new JLabel("Password");
      lblPassword.setBounds(89, 119, 63, 20);
      contentPanel.add(lblPassword);
    
      
    username = new JTextField();
    username.setBounds(173, 76, 152, 20);
    contentPanel.add(username);
    username.setColumns(10);
    
    password = new JPasswordField();
    password.setBounds(173, 119, 152, 20);
    contentPanel.add(password);
    
    JButton btnRegister= new JButton("Register");
    btnRegister.setBounds(205, 205, 89, 23);
    contentPanel.add(btnRegister);
    
    lblValidation = new JLabel("Username or Password invalid");
    lblValidation.setBounds(173, 140, 200, 20);
    contentPanel.add(lblValidation);
    lblValidation.setVisible(false);
    
    top = new JLabel("Top 3");
    contentPanel.add(top);
    top.setFont(new Font("Tahoma", Font.PLAIN, 20));
    top.setBounds(500, 20, 89, 23);
    top1 = new JLabel("");
    contentPanel.add(top1);
    top1.setFont(new Font("Tahoma" , 2, 17));
    top1.setBounds(500, 50, 200, 23);
    top1.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            top1.setVisible(false);
            date1.setVisible(true);
        }
    });
    date1 = new JLabel("");
    contentPanel.add(date1);
    date1.setFont(new Font("Tahoma" , 2, 15));
    date1.setBounds(500, 50, 200, 23);
    date1.setVisible(false);
    date1.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent evt) {
            top1.setVisible(true);
            date1.setVisible(false);
        }
    });
    
    top2 = new JLabel("");
    contentPanel.add(top2);
    top2.setFont(new Font("Tahoma" , 2, 17));
    top2.setBounds(500, 75, 200, 23);
    top2.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            top2.setVisible(false);
            date2.setVisible(true);
        }
    });
    date2 = new JLabel("");
    contentPanel.add(date2);
    date2.setFont(new Font("Tahoma" , 2, 15));
    date2.setBounds(500, 75, 200, 23);
    date2.setVisible(false);
    date2.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent evt) {
            top2.setVisible(true);
            date2.setVisible(false);
        }
    });
    
    top3 = new JLabel("");
    contentPanel.add(top3);
    top3.setFont(new Font("Tahoma" , 2, 17));
    top3.setBounds(500, 100, 200, 23);
    top3.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            top3.setVisible(false);
            date3.setVisible(true);
        }
    });
    date3 = new JLabel("");
    contentPanel.add(date3);
    date3.setFont(new Font("Tahoma" , 2, 15));
    date3.setBounds(500, 100, 200, 23);
    date3.setVisible(false);
    date3.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent evt) {
            top3.setVisible(true);
            date3.setVisible(false);
        }
    });
    
    JLabel lblLogin = new JLabel("Login/Register");
    lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
    lblLogin.setVisible(true);
    lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblLogin.setBounds(130, 23, 230, 23);
    contentPanel.add(lblLogin);
    
    JButton btnLogin = new JButton("Login");
    btnLogin.setBounds(205, 165, 89, 23);
    btnLogin.setVisible(true);
    contentPanel.add(btnLogin);
    
    btnLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
    	  //correrServer();
    	  try {
    		  Class.forName("com.mysql.jdbc.Driver");
    		  Connection con = DriverManager.getConnection("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99"); //("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99");
    		  Statement stmt = con.createStatement();
    		  String sql = "Select * from testusers where user='"+username.getText()+"' and pass='"+password.getText().toString()+"'";
    		  ResultSet rs = stmt.executeQuery(sql);
    		  if(rs.next()) {
    			//JOptionPane.showMessageDialog(null, "Login Sucessful ");
    	          //YOUR CODE GOES HERE
    	          lblValidation.setText("Login Sucessful");
    	          lblValidation.setVisible(true);
    	          contentPanel.setVisible(false);
    	          
    	          _user = username.getText();
       	          _pass = password.getText().toString();
       	          _score = rs.getString("score");
       	          System.out.println("_score: " + _score);
       	          
       	          _achievements = rs.getString("achievements");
       	          _achievements2 = rs.getString("achievements");
       	          arrayAchiev = _achievements.toCharArray();
       	          arrayAchiev2 = _achievements.toCharArray();
       	          System.out.println("_achievements: " + _achievements);
       	          
       	          _numPlay = rs.getInt("numPlay");
       	          System.out.println("_numPlay: " + _numPlay);
       	          
       	          creatView();
       	       
    		  }else {
    			  lblValidation.setText("Username or Password invalid (2)");
    			  if(!serverDownLabel.isVisible())
    				  lblValidation.setVisible(true);
    		  con.close();
    		  }
    	  }catch(Exception e){ 
    		  //lblValidation.setText("Maybe Server is down");
    		  serverDownLabel.setVisible(true);
  			  //lblValidation.setVisible(false);
    		  System.out.println(e);
    		  
    	  }
      }
    });
    

    btnRegister.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
        	 try {
       		  Class.forName("com.mysql.jdbc.Driver");
       		  Connection con = DriverManager.getConnection("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99");
       		  Statement stmt = con.createStatement();
       		  String query = "SELECT * FROM testusers where user='"+username.getText()+"'";
       		  ResultSet rs = stmt.executeQuery(query);
       		  Data dateFormat = new Data(); //HH:mm:ss
       		  System.out.println(dateFormat);
       		  if(!rs.next()) {
	       		  String sql = "INSERT INTO `testusers` (`id`, `user`, `pass`, `score`, `achievements`, `date`, `numPlay`) VALUES (NULL, '"+username.getText()+"','"+password.getText().toString()+"','0', '000000000000', '"+dateFormat+"','0')";
	       		  PreparedStatement ptmt = con.prepareStatement(sql);
	       		  ptmt.executeUpdate();
       	          lblValidation.setText("Register Sucessful");
       	          lblValidation.setVisible(true);
       	          contentPanel.setVisible(false);
       	          
       	          _user = username.getText();
       	          _pass = password.getText().toString();
       	          _score = "0";
       	          System.out.println("criou creatView()!");
       	          creatView();
       		  }else {
       			  if(!serverDownLabel.isVisible())
       				  lblValidation.setVisible(true);
       			  lblValidation.setText("Sorry, but this User already existe");
       			  System.out.println("Já existe este User");
       		  }
       		  //if(_user.toCharArray().length > 20) serverDownLabel.setText("User so long!!");
       		  con.close();
       		  
       	  }catch(Exception e){
       		  serverDownLabel.setVisible(true);
       		  lblValidation.setVisible(false);
       		  System.out.println("erro: " +e);
       		System.out.println("NAO criou creatView()!");  
       	  }
        }
    });


    serverDownLabel = new JLabel("Server Down");
    contentPanel.add(serverDownLabel);
    serverDownLabel.setBounds(300, 165, 89, 23);
    serverDownLabel.setVisible(false);
    
    showUser();
    
  }
  
  
  public void userList(){
	
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection con = DriverManager.getConnection("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99");
		  Statement stmt = con.createStatement();
		  String sql = "Select * from testusers";
		  ResultSet rs = stmt.executeQuery(sql);
		  User user;
		  while(rs.next()) {
			  user = new User(rs.getString("user"), rs.getString("pass"), rs.getInt("score"), rs.getDate("date"), rs.getString("achievements"), rs.getInt("numPlay"));
			  usersList.add(user); 
			  System.out.println("DATE: " + rs.getDate("date"));
		  }
		  Collections.sort(usersList, new CustomComparator()); 
		  		  
	} catch (Exception e) {
		System.out.println(e);
		JOptionPane.showMessageDialog(null, e);
	} 
  }
  
  public void showUser() {
	  userList();
	  Object[] row = new Object[4];
	  for (int i = 0; i < 3; i++) { //Top 3
		row[0] = usersList.get(i).getUser();
		row[1] = usersList.get(i).getPass();
		row[2] = usersList.get(i).getScore();
		row[3] = usersList.get(i).getDate();
		if(i==0) {
			top1.setText(row[0] + " -> " + row[2]);
			date1.setText(row[3] + "");
			first = row[0] + " -> " + row[2];
			bestRecord = (int) row[2];
		}	 
		if(i==1) {
			top2.setText(row[0] + " -> " + row[2]);
			date2.setText(row[3] + "");
		}
		if(i==2) {
			top3.setText(row[0] + " -> " + row[2]);
			date3.setText(row[3] + "");
		}
	}
  }
 
  
  public class CustomComparator implements Comparator<User> {
		@Override
		public int compare(User o1, User o2) {
			Integer score1 = o1.getScore();
			Integer score2 = o2.getScore();
			return score2.compareTo(score1);
		}
	}  
}