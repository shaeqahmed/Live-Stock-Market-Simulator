package stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class StartScreen{
    private JFrame start = new JFrame("Stock Market Game");
    final JTextField userbox = new JTextField("Username", SwingConstants.CENTER);
    final JTextField passbox = new JTextField("Password");
    final JLabel error = new JLabel("<html><center>For new users: username already taken.<br> Previous users: Wrong password</center></html>", SwingConstants.CENTER);
    
    public StartScreen(){
	
	JButton login = new JButton("Login");
	
	
	// Initial formatting
	start.setTitle("Stock Market Game");
	start.setSize(590,332);
	start.setLocationRelativeTo(null);
	start.setResizable(false);
	start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	start.setLayout(null);
	start.setVisible(true);
	start.setContentPane(new JLabel(new ImageIcon("background1.jpg")));
	////////////////////////////////////////////////
	
	// title formatting
	JLabel title = new JLabel("Stock Market Simulator", SwingConstants.CENTER);
	title.setForeground(Color.WHITE);
	start.add(title);
	title.setLocation(0, 20);
	title.setSize(start.getWidth(), 48);
	title.setFont(new Font("SansSerif", Font.BOLD , 36));
	//////////////////////////////////////////////////////
	
	// caption formatting
	JLabel caption = new JLabel("$$ Start making money now $$", SwingConstants.CENTER);
	start.add(caption);
	caption.setFont(new Font("SandSerif", Font.BOLD ,24));
	caption.setLocation(0, 40);
	caption.setSize(start.getWidth(),100);
	caption.setForeground(Color.GREEN);
	//////////////////////////////////////////////////
	
	// caption 2 formatting
	JLabel caption2 = new JLabel("<html><center>Enter name and password if you have already made an account.<br>For new users, enter desired name and password.</center></html>", SwingConstants.CENTER);
	start.add(caption2);
	caption2.setFont(new Font("SandSerif", Font.ITALIC ,14));
	caption2.setLocation(0, 80);
	caption2.setSize(start.getWidth(),100);
	caption2.setForeground(Color.WHITE);
	/////////////////////////////////////////////////////////////
	
	// adding username box

	start.add(userbox);
	userbox.setBounds(start.getWidth()/2 - 75, 160, 150, 25);

	/////////////////////////////////////////
	
	// adding password box
	start.add(passbox);
	passbox.setBounds(start.getWidth()/2 - 75 ,190, 150, 25);

	// error message formatting
	start.add(error);
	error.setFont(new Font("SandSerif", Font.BOLD ,13));
	error.setLocation(0, 250);
	error.setSize(start.getWidth(),40);
	error.setForeground(Color.RED);
	error.setVisible(false);
	////////////////////////////////////////////////
	
	
	// formatting login button + adding action listener
	start.add(login);
	login.setBounds(start.getWidth()/2 - 50 ,225, 100, 25);
	login.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent ae)
		{
		    String username = userbox.getText();
		    String password = passbox.getText();
		    // does nothing if user doesn't provide name and password
		    if(username != null && password != null){
			File newUserFile = new File( username);
			if(!newUserFile.exists()){
			    newUserFile.mkdirs();
			    File passfile = new File(username+"\\password");
			    try{
				BufferedWriter writer = new BufferedWriter(new FileWriter(passfile));
				writer.write(password);
				writer.close();
			    } catch(Exception e){
				System.out.println("Cannot write to file");
			    }
			    StartScreen.this.start.dispose();
                            MainScreen.main(new String[] {username});

			}
			else{
			    try{
				BufferedReader getPass = new BufferedReader(new FileReader(username+"\\password"));
				if(getPass.readLine().equals(password)) {
				    StartScreen.this.start.dispose();
                                    MainScreen.main(new String[] {username});

                                }
				else
				    error.setVisible(true);
			    }
			    catch(FileNotFoundException ex) {
				System.out.println("Could not open file");              
			    }
			    catch(IOException ex) {
				ex.printStackTrace();
			    }
			}

		    }   
		    // create instance of Stivens thing using name;
		}
	    });
	////////////////////////////////////////////////////////////////
	

    }


    public static void main(String args[]){
	new StartScreen();
    }
} 
