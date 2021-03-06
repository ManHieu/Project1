package hieu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import hieu.model.DanhMuc;
import hieu.model.SanPham;
import hieu.service.DanhMucService;
import hieu.service.SanPhamService;


public class QuanLiSanPhamUI extends JFrame {
	
	JList<DanhMuc> listDanhMuc;
	JButton btNew,btUpdate,btRemove,btnTaoMoiSp,btnLuuSp,btnXoaSp;
	DefaultTableModel tbmSanPham;
	JTextField txtMasp,txtTen,txtSoLuong,txtGia;
	JComboBox<DanhMuc> cbDanhMuc;
	ArrayList<SanPham> dsSP = null;
	JTable tbSanPham;
	
	public QuanLiSanPhamUI(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		showWindow();
		addControls();
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		hienThiDanhMuc();
		listDanhMuc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(listDanhMuc.getSelectedValue() == null) return;
				SanPhamService sps = new SanPhamService();
				String maDM = listDanhMuc.getSelectedValue().getMaDM();
				dsSP = sps.getDsSp(maDM);
				tbmSanPham.setRowCount(0);
				for(SanPham sp : dsSP) {
					Vector<Object> vec = new Vector<>();
					vec.add(sp.getMaSP());
					vec.add(sp.getTenSP());
					vec.add(sp.getSoLuong());
					vec.add(sp.getDonGia());
					tbmSanPham.addRow(vec);
				}
				cbDanhMuc.setSelectedIndex(listDanhMuc.getSelectedIndex());
			}
		});
		tbSanPham.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = tbSanPham.getSelectedRow();
				if(row == -1) return;
				SanPham sp = dsSP.get(row);
				txtMasp.setText(sp.getMaSP());
				txtTen.setText(sp.getTenSP());
				txtSoLuong.setText(sp.getSoLuong()+"");
				txtGia.setText(sp.getDonGia()+"");
			}
		});
		btnTaoMoiSp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtGia.setText("");
				txtMasp.setText("");
				txtTen.setText("");
				txtSoLuong.setText("");
				txtMasp.requestFocus();
			}
		});
		
		btnLuuSp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPham sp = new SanPham();
				String maDM = listDanhMuc.getSelectedValue().getMaDM();
				sp.setMaDM(maDM);
				sp.setDonGia(Integer.parseInt(txtGia.getText()));
				sp.setMaSP(txtMasp.getText());
				sp.setTenSP(txtTen.getText());
				sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
				sp.setIsDeleted(0);
				SanPhamService sps = new SanPhamService();
				Boolean t = sps.themSanPham(sp);
				if(t == true) JOptionPane.showMessageDialog(null, "Lưu SP thành công");
			}
		});
	}

	private void hienThiDanhMuc() {
		// TODO Auto-generated method stub
		 DanhMucService dms = new DanhMucService();
		 Vector<DanhMuc> vec = dms.hienThiDanhMuc();
		 listDanhMuc.setListData(vec);
		 cbDanhMuc.removeAllItems();
		 for (DanhMuc dm : vec) {
			 cbDanhMuc.addItem(dm);
		 }
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		con.add(pnMain);

		JLabel lbTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		lbTitle.setHorizontalAlignment(JLabel.CENTER);
		pnMain.add(lbTitle, BorderLayout.NORTH);

		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		JPanel pnRight = new JPanel(new BorderLayout());

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
		pnMain.add(sp, BorderLayout.CENTER);

		pnLeft.setLayout(new BorderLayout());
		listDanhMuc = new JList<>();
		JScrollPane scpListDanhMuc=new JScrollPane(listDanhMuc,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TitledBorder borderList = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Danh mục sản phẩm");
		listDanhMuc.setBorder(borderList);
		pnLeft.add(scpListDanhMuc, BorderLayout.CENTER);
		
		JPanel pnButtonleft = new JPanel();
		btNew = new JButton("New");
		btUpdate = new JButton("Update");
		btRemove = new JButton("Remove");
		pnButtonleft.add(btRemove);
		pnButtonleft.add(btUpdate);
		pnButtonleft .add(btNew);
		pnLeft.add(pnButtonleft,BorderLayout.SOUTH);
		
		JLabel lbChiTiet = new JLabel("Thông tin chi tiết", JLabel.CENTER);
		pnRight.add(lbChiTiet,BorderLayout.NORTH);
		
		tbmSanPham = new DefaultTableModel();
		tbmSanPham.addColumn("Mã");
		tbmSanPham.addColumn("Tên");
		tbmSanPham.addColumn("Số lượng");
		tbmSanPham.addColumn("Đơn giá");
		tbSanPham= new JTable(tbmSanPham);
		JScrollPane scpTableSanPham=new JScrollPane(tbSanPham,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnRight.add(scpTableSanPham, BorderLayout.CENTER);
	
		JPanel pnLuaChon = new JPanel();
		pnLuaChon.setLayout(new BoxLayout(pnLuaChon, BoxLayout.Y_AXIS));
		
		JPanel pnDanhMuc = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cbDanhMuc = new JComboBox<>();
		JLabel lbDanhMuc = new JLabel("Danh mục:");
		pnDanhMuc.add(lbDanhMuc);
		pnDanhMuc.add(cbDanhMuc);
		pnLuaChon.add(pnDanhMuc);
		
		JPanel pnMaSp=new JPanel();
		pnMaSp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaSp=new JLabel("Mã SP:");
		txtMasp=new JTextField(30);
		pnMaSp.add(lblMaSp);
		pnMaSp.add(txtMasp);
		pnLuaChon.add(pnMaSp);
		
		JPanel pnTen=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen=new JLabel("Tên SP:");
		txtTen=new JTextField(30);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnLuaChon.add(pnTen);
		
		JPanel pnSoLuong=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuong=new JLabel("Số lượng SP:");
		txtSoLuong=new JTextField(30);
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnLuaChon.add(pnSoLuong);
		
		JPanel pnGia=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGia=new JLabel("Giá SP:");
		txtGia=new JTextField(30);
		pnGia.add(lblGia);
		pnGia.add(txtGia);
		pnLuaChon.add(pnGia);
		
		lblGia.setPreferredSize(lblSoLuong.getPreferredSize());
		lblTen.setPreferredSize(lblSoLuong.getPreferredSize());
		lblMaSp.setPreferredSize(lblSoLuong.getPreferredSize());
		cbDanhMuc.setPreferredSize(new Dimension(300, 20));
		
		JPanel pnButtonSanPham=new JPanel();
		pnButtonSanPham.setLayout(new FlowLayout());
		btnTaoMoiSp=new JButton("New");
		btnLuuSp=new JButton("Save");
		btnXoaSp=new JButton("Remove");
		pnButtonSanPham.add(btnTaoMoiSp);
		pnButtonSanPham.add(btnLuuSp);
		pnButtonSanPham.add(btnXoaSp);
		pnLuaChon.add(pnButtonSanPham);
		
		pnRight.add(pnLuaChon,BorderLayout.SOUTH);
		
	}

	private void showWindow() {
		// TODO Auto-generated method stub
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
