package hieu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import hieu.model.User;
import hieu.net.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;


public class UILogin extends JFrame {

	private final JPanel pnLogin = new JPanel();
	private JTextField txtUser;
	private JTextField txtPass;
	JButton btnLogin, btnCancel;
	/**
	 * Create the dialog.
	 */
	public UILogin() {
		this.setTitle("Login");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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


			txtPass = new JPasswordField();
			pnDong2.add(txtPass);
			txtPass.setColumns(20);




			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnLogin = new JButton("Login");
			buttonPane.add(btnLogin);
			getRootPane().setDefaultButton(btnLogin);
			btnLogin.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Client client;
					try {
						client = new Client("localhost", 3333);
						User user = new User();
						user.setPassword(txtPass.getText());
						user.setUsername(txtUser.getText());

						int flag = 0;
						client.sendRequest("login");
						client.sendObject(user);
						flag = client.receiveAuthor();
						client.close();
						if(flag == 1) {
							QuanLiSanPhamUI ui = new QuanLiSanPhamUI("QLSP");
						}
						else {
							JOptionPane.showMessageDialog(null, 
									"Sai thông tin rồi","Thông báo",DO_NOTHING_ON_CLOSE);
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					
				}
			});

			JButton btnRegister = new JButton("Register");
			buttonPane.add(btnRegister);


			btnCancel = new JButton("Cancel");
			btnCancel.setActionCommand("Cancel");
			buttonPane.add(btnCancel);
			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
		}
	}
}

