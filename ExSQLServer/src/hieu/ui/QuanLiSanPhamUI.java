package hieu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class QuanLiSanPhamUI extends JFrame {
	public QuanLiSanPhamUI(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		showWindow();
		addControls();
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
		JList<String> listDanhMuc = new JList<>();
		JScrollPane scpListDanhMuc=new JScrollPane(listDanhMuc,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TitledBorder borderList = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Danh mục sản phẩm");
		listDanhMuc.setBorder(borderList);
		pnLeft.add(scpListDanhMuc, BorderLayout.CENTER);
		
		JPanel pnButtonleft = new JPanel();
		JButton btNew = new JButton("New");
		JButton btUpdate = new JButton("Update");
		JButton btRemove = new JButton("Remove");
		pnButtonleft.add(btRemove);
		pnButtonleft.add(btUpdate);
		pnButtonleft .add(btNew);
		pnLeft.add(pnButtonleft,BorderLayout.SOUTH);
		
		JLabel lbChiTiet = new JLabel("Thông tin chi tiết", JLabel.CENTER);
		pnRight.add(lbChiTiet,BorderLayout.NORTH);
		
		DefaultTableModel tbmSanPham = new DefaultTableModel();
		tbmSanPham.addColumn("Mã");
		tbmSanPham.addColumn("Tên");
		tbmSanPham.addColumn("Số lượng");
		tbmSanPham.addColumn("Đơn giá");
		JTable tbSanPham= new JTable(tbmSanPham);
		JScrollPane scpTableSanPham=new JScrollPane(tbSanPham,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnRight.add(scpTableSanPham, BorderLayout.CENTER);
	
		JPanel pnLuaChon = new JPanel();
		pnLuaChon.setLayout(new BoxLayout(pnLuaChon, BoxLayout.Y_AXIS));
		
		JPanel pnMaSp=new JPanel();
		pnMaSp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaSp=new JLabel("Mã SP:");
		JTextField txtMasp=new JTextField(30);
		pnMaSp.add(lblMaSp);
		pnMaSp.add(txtMasp);
		pnLuaChon.add(pnMaSp);
		
		JPanel pnTen=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen=new JLabel("Tên SP:");
		JTextField txtTen=new JTextField(30);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnLuaChon.add(pnTen);
		
		JPanel pnSoLuong=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuong=new JLabel("Số lượng SP:");
		JTextField txtSoLuong=new JTextField(30);
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnLuaChon.add(pnSoLuong);
		
		JPanel pnGia=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGia=new JLabel("Giá SP:");
		JTextField txtGia=new JTextField(30);
		pnGia.add(lblGia);
		pnGia.add(txtGia);
		pnLuaChon.add(pnGia);
		
		lblGia.setPreferredSize(lblSoLuong.getPreferredSize());
		lblTen.setPreferredSize(lblSoLuong.getPreferredSize());
		lblMaSp.setPreferredSize(lblSoLuong.getPreferredSize());
		
		JPanel pnButtonSanPham=new JPanel();
		pnButtonSanPham.setLayout(new FlowLayout());
		JButton btnTaoMoiSp=new JButton("Tạo mới");
		JButton btnLuuSp=new JButton("Lưu sản phẩm");
		JButton btnXoaSp=new JButton("Xóa sản phẩm");
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
