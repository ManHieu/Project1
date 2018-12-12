package hieu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;


public class UILogin extends JFrame {

	private final JPanel pnLogin = new JPanel();
	private JTextField txtUser;
	private JTextField txtPass;
	/**
	 * Create the dialog.
	 */
	public UILogin() {
		this.setTitle("Login");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 324, 184);
		getContentPane().setLayout(new BorderLayout());
		pnLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnLogin, BorderLayout.CENTER);
		pnLogin.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnCenter = new JPanel();
			pnLogin.add(pnCenter, BorderLayout.CENTER);
			pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
			
				JPanel pnDong1 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnDong1.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnCenter.add(pnDong1);
				
					JLabel lblUser = new JLabel("User name");
					pnDong1.add(lblUser);
				
				
					txtUser = new JTextField();
					pnDong1.add(txtUser);
					txtUser.setColumns(20);
				
			
			
				JPanel pnDong2 = new JPanel();
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnCenter.add(pnDong2);
				
					JLabel lblPass = new JLabel("Password");
					lblPass.setPreferredSize(lblUser.getPreferredSize());
					pnDong2.add(lblPass);
				
				
					txtPass = new JTextField();
					pnDong2.add(txtPass);
					txtPass.setColumns(20);
				
			
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton btnLogin = new JButton("Login");
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			
			
				JButton btnRegister = new JButton("Register");
				buttonPane.add(btnRegister);
			
			
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			
				addEvent();
		}
	}
	private void addEvent() {
		// TODO Auto-generated method stub
		
	}
}

