package clientmtt.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import DNSClient.net.DnsClient;
import DNSClient.net.DnsMessage;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DNSClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtIPS;
	private JTextField txtHost;
	private JTextArea areaKQ;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DNSClientUI frame = new DNSClientUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DNSClientUI() {
		super("DNS Cliet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblDnsClient = new JLabel("DNS CLIENT");
		lblDnsClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDnsClient.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDnsClient, BorderLayout.NORTH);

		JPanel PnMain = new JPanel();
		contentPane.add(PnMain, BorderLayout.CENTER);
		PnMain.setLayout(new BoxLayout(PnMain, BoxLayout.Y_AXIS));

		JPanel pnDong1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnDong1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		PnMain.add(pnDong1);

		JLabel lblIPServer = new JLabel("IP Server");
		lblIPServer.setVerticalAlignment(SwingConstants.TOP);
		lblIPServer.setHorizontalAlignment(SwingConstants.CENTER);
		pnDong1.add(lblIPServer);

		txtIPS = new JTextField();
		txtIPS.setHorizontalAlignment(SwingConstants.LEFT);
		pnDong1.add(txtIPS);
		txtIPS.setColumns(20);

		JPanel pnDong2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnDong2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		PnMain.add(pnDong2);

		JLabel lblHost = new JLabel("IP / Hostname");
		lblHost.setHorizontalAlignment(SwingConstants.LEFT);
		pnDong2.add(lblHost);
		lblIPServer.setPreferredSize(lblHost.getPreferredSize());

		txtHost = new JTextField();
		pnDong2.add(txtHost);
		txtHost.setColumns(20);

		JPanel pnDong3 = new JPanel();
		pnDong3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "K\u1EBFt qu\u1EA3", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE));
		PnMain.add(pnDong3);
		pnDong3.setLayout(new BorderLayout(0, 0));

		JScrollPane scpKetQua = new JScrollPane();
		pnDong3.add(scpKetQua, BorderLayout.CENTER);
		
		areaKQ = new JTextArea();
		scpKetQua.setViewportView(areaKQ);

		JPanel pnDong4 = new JPanel();
		PnMain.add(pnDong4);
		pnDong4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnPhanGiai = new JButton("Ph\u00E2n gi\u1EA3i");
		btnPhanGiai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String serverHost = txtIPS.getText();
				String message = txtHost.getText();
				
				DnsClient client = new DnsClient(serverHost);
				
				client.sendMessage(message);
				
				byte[] receiveByte = client.receiveMessage();
				String KQ = DnsMessage.decodeDNSMessage(receiveByte);
				areaKQ.setText(KQ);
			}
		});
		btnPhanGiai.setHorizontalAlignment(SwingConstants.LEFT);
		pnDong4.add(btnPhanGiai);

		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnThoat.setHorizontalAlignment(SwingConstants.TRAILING);
		pnDong4.add(btnThoat);
	}

}
