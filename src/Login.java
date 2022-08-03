
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Login implements ActionListener{

	JFrame frame;
	protected static JTextField usuario;
	protected JPasswordField contra;
	protected JButton entrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	public String replacestring(String a){
		a = a.replace("[", "");
		a = a.replace("]", "");
		a = a.replace(",", "");
		a = a.replace(" ", "");
		return a;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
        frame.setBounds(100, 100, 500, 600);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        
        JLabel lblNewLabel = new JLabel("Biblioteca\r\n");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 38));
        lblNewLabel.setBounds(154, 120, 200, 48);
        frame.getContentPane().add(lblNewLabel);
        
        usuario = new JTextField();
        usuario.setBounds(148, 218, 200, 34);
        frame.getContentPane().add(usuario);
        usuario.setColumns(10);
		usuario.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        contra = new JPasswordField();
        contra.setColumns(10);
        contra.setBounds(148, 275, 200, 34);
        frame.getContentPane().add(contra);
		contra.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        entrar = new JButton("Entrar");
        entrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        entrar.setBounds(168, 356, 160, 33);
        frame.getContentPane().add(entrar);
		this.entrar.addActionListener(this);
	}
        
    @Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == entrar) {
			String user = usuario.getText();
			char[] pass = contra.getPassword();
			String passw = Arrays.toString(pass);
			passw = replacestring(passw);


			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net/sql5508994", "sql5508994", "7H23S1ERTm");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from user");
				while (rs.next()) {
					if(user.equals(rs.getString(2)) && passw.equals(rs.getString(3))) {
						System.out.println("Login correcto");
						frame.dispose();
						new Catalogo();
					}else{
						JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor intente de nuevo...");
						System.out.println(passw + " " + passw.getClass());
						System.out.println(rs.getString(3) + " " + rs.getString(3).getClass());

					}	
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

    	} 
	}
}
