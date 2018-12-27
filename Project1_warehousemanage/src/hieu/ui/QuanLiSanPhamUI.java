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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import hieu.model.Category;
import hieu.model.Product;
import hieu.net.Client;
import hieu.service.CategoryService;



public class QuanLiSanPhamUI extends JFrame {

	JList<Category> listDanhMuc;
	JButton btNew,btUpdate,btRemove, btnInsert ,btnTaoMoiSp, btnThem, btnLuuSp,btnXoaSp, btnNewImport, btnSaveImport;
	DefaultTableModel tbmSanPham, tbmKho, tbmDoanhThu;
	JTextField txtMasp,txtTen,txtSoLuong,txtGia, txtHang, txtSoLuongNhap, txtGiaNhap, txtMaNhap, txtNgayNhap;
	JComboBox<Category> cbDanhMuc, cbDanhMuc2;
	ArrayList<Product> dsSP = null;
	JTable tbSanPham, tbKho, tbDoanhThu;
	JPanel pnInfo, pnSanPham, pnKho, pnDoanhThu;
	JScrollPane scpTableSanPham, scpTableKho, scpDoanhThu;
	JMenuItem itShowTonKho, itShowNhapKho, itShowDoanhThu, itFileTimkienTen, itFileTimkiemMa;
	JRadioButton raDoanhSo, raLoiNhuan;

	public QuanLiSanPhamUI(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		showWindow();
		addControls();
		addMenu();
		addEvent();


	}

	private void addMenu() {
		// TODO Auto-generated method stub
		JMenuBar mb = new JMenuBar();

		JMenu mnFile = new JMenu("File");
		JMenu mnShow = new JMenu("Show");
		JMenu mnContact = new JMenu("Contact");

		itShowTonKho = new JMenuItem("Tồn kho");
		itShowNhapKho = new JMenuItem("Nhập kho");
		itShowDoanhThu = new JMenuItem("Doanh thu");
	
		mnShow.add(itShowTonKho);
		mnShow.add(itShowNhapKho);
		mnShow.add(itShowDoanhThu);
		
		JMenu mnTimKiem = new JMenu("Tìm kiếm");
		itFileTimkiemMa = new JMenuItem("Mã");
		itFileTimkienTen = new JMenuItem("Tên");
		mnTimKiem.add(itFileTimkiemMa);
		mnTimKiem.add(itFileTimkienTen);

		mnFile.add(mnTimKiem);

		mb.add(mnFile);
		mb.add(mnShow);
		mb.add(mnContact);
		this.setJMenuBar(mb);

	}

	private void addEvent() {
		// TODO Auto-generated method stub
		itShowTonKho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnInfo.removeAll();

				pnInfo.add(scpTableSanPham, BorderLayout.CENTER);
				pnInfo.add(pnSanPham, BorderLayout.SOUTH);
			}
		});
		
		itShowNhapKho.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnInfo.removeAll();
				pnInfo.add(scpTableKho, BorderLayout.CENTER);
				pnInfo.add(pnKho, BorderLayout.SOUTH);
			}
		});
		
		itShowDoanhThu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnInfo.removeAll();
				pnInfo.add(scpDoanhThu,BorderLayout.CENTER);
				pnInfo.add(pnDoanhThu,BorderLayout.SOUTH);
			}
		});
		
		showCategory();
		listDanhMuc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Category cate = listDanhMuc.getSelectedValue();
				if(cate == null) return;
				else {
					Client client = new Client("localhost", 3333);
					Vector<Product> list = null;
					
					client.sendRequest("listProduct");
					client.sendRequest(cate.getIdCategory()+"");
					list = (Vector<Product>) client.receiveAnswer();
					for(Product pro : list) {
						Vector<Object> vec = new Vector<>();
						vec.add(pro.getID());
						vec.add(pro.getProductName());
						vec.add(pro.getAmount());
						vec.add(pro.getCost());
						vec.add(pro.getManufacturerName());
						tbmSanPham.addRow(vec);
					}
					
					cbDanhMuc2.setSelectedIndex(listDanhMuc.getSelectedIndex());
				}
			}
		});
		
	}
	
	private void showCategory() {
		// TODO Auto-generated method stub
		Vector<Category> list = null;
		Client client = new Client("localhost", 3333);
		
		client.sendRequest("listCate");
		list = (Vector<Category>) client.receiveAnswer();
		
		listDanhMuc.setListData(list);
		cbDanhMuc.removeAllItems();
		cbDanhMuc2.removeAllItems();
		 for (Category cate : list) {
			 cbDanhMuc.addItem(cate);
			 cbDanhMuc2.addItem(cate);
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
		btnInsert = new JButton("Insert");
		pnButtonleft.add(btRemove);
		pnButtonleft.add(btUpdate);
		pnButtonleft .add(btNew);
		pnButtonleft.add(btnInsert);
		pnLeft.add(pnButtonleft,BorderLayout.SOUTH);

		JLabel lbChiTiet = new JLabel("Thông tin chi tiết", JLabel.CENTER);
		pnRight.add(lbChiTiet,BorderLayout.NORTH);

		pnInfo = new JPanel();
		pnInfo.setLayout(new BorderLayout());
		pnRight.add(pnInfo);
		
		cbDanhMuc = new JComboBox<>();
		cbDanhMuc2 = new JComboBox<>();
		
		
		// Tồn kho
		{
			tbmSanPham = new DefaultTableModel();
			tbmSanPham.addColumn("Mã");
			tbmSanPham.addColumn("Tên");
			tbmSanPham.addColumn("Số lượng");
			tbmSanPham.addColumn("Đơn giá");
			tbmSanPham.addColumn("Hãng");
			tbSanPham= new JTable(tbmSanPham);
			scpTableSanPham=new JScrollPane(tbSanPham,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		}

		{
			pnSanPham = new JPanel();
			pnSanPham.setLayout(new BoxLayout(pnSanPham, BoxLayout.Y_AXIS));

			JPanel pnDanhMuc = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel lbDanhMuc = new JLabel("Danh mục:");
			pnDanhMuc.add(lbDanhMuc);
			pnDanhMuc.add(cbDanhMuc2);
			pnSanPham.add(pnDanhMuc);

			JPanel pnMaSp=new JPanel();
			pnMaSp.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel lblMaSp=new JLabel("Mã SP:");
			txtMasp=new JTextField(30);
			pnMaSp.add(lblMaSp);
			pnMaSp.add(txtMasp);
			pnSanPham.add(pnMaSp);

			JPanel pnTen=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblTen=new JLabel("Tên SP:");
			txtTen=new JTextField(30);
			pnTen.add(lblTen);
			pnTen.add(txtTen);
			pnSanPham.add(pnTen);

			JPanel pnSoLuong=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblSoLuong=new JLabel("Số lượng SP:");
			txtSoLuong=new JTextField(30);
			pnSoLuong.add(lblSoLuong);
			pnSoLuong.add(txtSoLuong);
			pnSanPham.add(pnSoLuong);

			JPanel pnGia=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblGia=new JLabel("Giá SP:");
			txtGia=new JTextField(30);
			pnGia.add(lblGia);
			pnGia.add(txtGia);
			pnSanPham.add(pnGia);

			JPanel pnHang=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblHang=new JLabel("Hãng");
			txtHang=new JTextField(30);
			pnHang.add(lblHang);
			pnHang.add(txtHang);
			pnSanPham.add(pnHang);

			lblGia.setPreferredSize(lblSoLuong.getPreferredSize());
			lblTen.setPreferredSize(lblSoLuong.getPreferredSize());
			lblMaSp.setPreferredSize(lblSoLuong.getPreferredSize());
			lblHang.setPreferredSize(lblSoLuong.getPreferredSize());
			cbDanhMuc2.setPreferredSize(new Dimension(300, 20));

			JPanel pnButtonSanPham=new JPanel();
			pnButtonSanPham.setLayout(new FlowLayout());
			btnTaoMoiSp=new JButton("New");
			btnThem = new JButton("Insert");
			btnLuuSp=new JButton("Save");
			btnXoaSp=new JButton("Remove");
			pnButtonSanPham.add(btnTaoMoiSp);
			pnButtonSanPham.add(btnThem);
			pnButtonSanPham.add(btnLuuSp);
			pnButtonSanPham.add(btnXoaSp);
			pnSanPham.add(pnButtonSanPham);

		}
		
		// Nhập kho
		{
			tbmKho = new DefaultTableModel();
			tbmKho.addColumn("Mã sản phẩm");
			tbmKho.addColumn("Số lượng nhập");
			tbmKho.addColumn("Giá nhập");
			tbmKho.addColumn("Ngày nhập");
			tbKho = new JTable(tbmKho);
			scpTableKho = new JScrollPane(tbKho, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}

		{
			pnKho = new JPanel();
			pnKho.setLayout(new BoxLayout(pnKho, BoxLayout.Y_AXIS));
			
			JPanel pnDanhMuc = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel lbDanhMuc = new JLabel("Danh mục:");
			pnDanhMuc.add(lbDanhMuc);
			pnDanhMuc.add(cbDanhMuc);
			pnKho.add(pnDanhMuc);
			
			JPanel pnMaNhap=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblMaNhap=new JLabel("Mã nhập");
			txtMaNhap=new JTextField(30);
			pnMaNhap.add(lblMaNhap);
			pnMaNhap.add(txtMaNhap);
			pnKho.add(pnMaNhap);
			
			JPanel pnSoLuongNhap = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblSoLuongNhap = new JLabel("Số lượng nhập");
			txtSoLuongNhap = new JTextField(30);
			pnSoLuongNhap.add(lblSoLuongNhap);
			pnSoLuongNhap.add(txtSoLuongNhap);
			pnKho.add(pnSoLuongNhap);
			
			JPanel pnGiaNhap = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblGiaNhap = new JLabel("Giá nhập");
			txtGiaNhap = new JTextField(30);
			pnGiaNhap.add(lblGiaNhap);
			pnGiaNhap.add(txtGiaNhap);
			pnKho.add(pnGiaNhap);
			
			JPanel pnNgayNhap = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel lblNgayNhap = new JLabel("Ngày nhập");
			txtNgayNhap = new JTextField(30);
			pnNgayNhap.add(lblNgayNhap);
			pnNgayNhap.add(txtNgayNhap);
			pnKho.add(pnNgayNhap);
			
			cbDanhMuc.setPreferredSize(new Dimension(300, 20));
			lblGiaNhap.setPreferredSize(lblSoLuongNhap.getPreferredSize());
			lblMaNhap.setPreferredSize(lblSoLuongNhap.getPreferredSize());
			lblNgayNhap.setPreferredSize(lblSoLuongNhap.getPreferredSize());
			
			JPanel pnButtonSanPham=new JPanel();
			pnButtonSanPham.setLayout(new FlowLayout());
			btnNewImport =new JButton("New");
			btnSaveImport =new JButton("Save");
			pnButtonSanPham.add(btnNewImport);
			pnButtonSanPham.add(btnSaveImport);
			pnKho.add(pnButtonSanPham);
		}
		// Doanh thu
		{
			tbmDoanhThu = new DefaultTableModel();
			tbmDoanhThu.addColumn("Mã");
			tbmDoanhThu.addColumn("Tên sản phẩm");
			tbmDoanhThu.addColumn("Doanh số");
			tbmDoanhThu .addColumn("Lợi nhuận");
			tbDoanhThu = new JTable(tbmDoanhThu);
			scpDoanhThu = new JScrollPane(tbDoanhThu, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			pnDoanhThu = new JPanel();
			JLabel lblSapSep = new JLabel("Sắp xếp theo: ");
			raDoanhSo = new JRadioButton("Doanh số");
			raLoiNhuan = new JRadioButton("Lợi nhuận");
			ButtonGroup grSapSep = new ButtonGroup();
			grSapSep.add(raDoanhSo);
			grSapSep.add(raLoiNhuan);
			pnDoanhThu.add(lblSapSep);
			pnDoanhThu.add(raDoanhSo);
			pnDoanhThu.add(raLoiNhuan);
		}
	}

	private void showWindow() {
		// TODO Auto-generated method stub
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
