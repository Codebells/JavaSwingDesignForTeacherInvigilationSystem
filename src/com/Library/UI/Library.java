package com.Library.UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import com.Library.DataBase.DataBase;
import com.Library.Service.*;
import java.awt.event.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 教务在线系统
 * 用户类
 * @author Codebells
 */
public class Library extends JFrame {
	private static final long serialVersionUID = -1169720619929459438L;
	private JPanel imagePanel;
	private ImageIcon background;
	private JPanel pane = null; // 主要的JPanel
	private CardLayout card = null; // CardLayout布局管理器
	private JPanel p_1 = null, p_2 = null, p_3 = null, p_4 = null;
	private JPanel p_3_1;
	private String CheckSex;	//判断更新用户选择的性别
	private int bookListWidth;	
	private JScrollPane bookList;
	private JPanel bookListPanel;
	private ImageIcon bookimg = new ImageIcon("userimg/BookDefault.png");// 背景图片
	private ArrayList<String> findsList = new ArrayList<String>();
	private PopupMenu popupMenu1;
	private JTable myBookTabel;
	private Vector<Serializable> rowData,columnNames;
	private DefaultTableModel tabModel;
	private int count = 0;
	private JLabel hasbook,maxbook,credit;
	private int myBookSize=Integer.parseInt(UserServise.getMaxBook());	//获取用户还能借多少书
	private JTable table;
	public Object[][] tabledate;
	String[] columnTitleObjects;
	public int cnt =1;

	public Library() {	
		
		cnt=1;
		tabledate=new Object[100][8];
		columnTitleObjects=new String[] {"考试批次","监考日期","开始时间","结束时间","教室","课程号","课程名称","主考"};
		setBounds(100, 100, 1208, 840);
		setTitle("\u6559\u52A1\u5728\u7EBF");
		setLocationRelativeTo(null);// 让窗口居中显示
		setResizable(false); // 禁用窗口最大化按钮
		setImg();
		CardPanle();
		SetLabelBtn();
		if(UserServise.getState()==1)
		{
//			String mail=UserServise.UserMail(UserServise.getName());
//			SentMail sentMail=new SentMail(mail);
//			sentMail.Sent();
			JOptionPane.showMessageDialog(null, "您有新的监考任务", "提示", JOptionPane.PLAIN_MESSAGE);
//			SentMessage();
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();//关闭数据库连接
				System.exit(0);
			}
		});
		setVisible(true);
	}

	/**
	 * 加载背景图片
	 */
	public void setImg() {
		background = new ImageIcon("userimg/UserUi.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 注册卡片布局框架
	 */
	public void CardPanle() {
		card = new CardLayout(5, 5);
		pane = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
		pane.setBounds(242, 81, 960, 724);
		getContentPane().add(pane);

		p_1 = new JPanel();
		p_2 = new JPanel();
		p_3_1 = new JPanel();
		p_4 = new JPanel();

		p_1.setLayout(null);
		p_2.setLayout(null);
		p_3_1.setLayout(null);
		p_4.setLayout(null);

		ClickEvent_Userpersonal();
		ClickEvent_UserUpdata();
		FindsBook();
		BookList();
		getBook();
		SetMyBookList();
		MouseEventButtonListener();

		pane.add(p_1, "p1");

		pane.add(p_2, "p2");
		pane.add(p_3_1, "p3");

		pane.add(p_4, "p4");

	}

	public void SetLabelBtn() {
		JLabel myuser = new JLabel("我的信息");
		myuser.setHorizontalAlignment(SwingConstants.CENTER);
		myuser.setForeground(SystemColor.textHighlight);
		myuser.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myuser.setBounds(92, 105, 95, 28);
		getContentPane().add(myuser);

		JLabel updata = new JLabel("更新我的信息");
		updata.setHorizontalAlignment(SwingConstants.CENTER);
		updata.setForeground(SystemColor.textHighlight);
		updata.setFont(new Font("微软雅黑", Font.BOLD, 20));
		updata.setBounds(92, 177, 125, 28);
		getContentPane().add(updata);

		JLabel library = new JLabel("\u6240\u6709\u8003\u8BD5");
		library.setHorizontalAlignment(SwingConstants.CENTER);
		library.setForeground(SystemColor.textHighlight);
		library.setFont(new Font("微软雅黑", Font.BOLD, 20));
		library.setBounds(92, 251, 95, 28);
		getContentPane().add(library);

		JLabel book = new JLabel("\u6211\u7684\u76D1\u8003");
		book.setHorizontalAlignment(SwingConstants.CENTER);
		book.setForeground(SystemColor.textHighlight);
		book.setFont(new Font("微软雅黑", Font.BOLD, 20));
		book.setBounds(92, 320, 95, 28);
		getContentPane().add(book);

		JLabel myuserimg = new JLabel();
		myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
		myuserimg.setBounds(0, 81, 240, 70);
		getContentPane().add(myuserimg);

		JLabel updataimg = new JLabel();
		updataimg
				.setIcon(new ImageIcon("userimg\\updata1.png"));
		updataimg.setBounds(0, 153, 240, 70);
		getContentPane().add(updataimg);

		JLabel lbiraryimg = new JLabel();
		lbiraryimg
				.setIcon(new ImageIcon("userimg\\bookall.png"));
		lbiraryimg.setBounds(0, 225, 240, 70);
		getContentPane().add(lbiraryimg);

		JLabel bookimg = new JLabel();
		bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
		bookimg.setBounds(0, 297, 240, 70);
		getContentPane().add(bookimg);

		JLabel name = new JLabel("欢迎您:" + UserServise.getName());
		name.setForeground(Color.WHITE);
		name.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		name.setBounds(956, 26, 202, 38);
		getContentPane().add(name);
		
		JButton button = new JButton("\u6CE8\u9500");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击注销的响应事件
				int i = JOptionPane.showConfirmDialog(null, "确定注销吗?", "提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					DataBase.Colse(); // 关闭数据库连接
					new LoginWindow();
					dispose();
				}
			}
		});
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setContentAreaFilled(false);
		button.setBounds(1065, 24, 137, 45);
		getContentPane().add(button);

		/************** 按键监听事件 ***************/
		myuser.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p1");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				myuser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				myuser.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				myuser.setForeground(SystemColor.textHighlight);
			}
		});

		updata.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p2");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				updata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				updata.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				updata.setForeground(SystemColor.textHighlight);
			}
		});

		library.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall1.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook.png"));
				card.show(pane, "p3");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				library.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																					// 变成小手
				library.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				library.setForeground(SystemColor.textHighlight);
			}
		});

		book.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { // 鼠标点击事件
				myuserimg.setIcon(new ImageIcon("userimg\\user1.png"));
				updataimg.setIcon(new ImageIcon("userimg\\updata1.png"));
				lbiraryimg.setIcon(new ImageIcon("userimg\\bookall.png"));
				bookimg.setIcon(new ImageIcon("userimg\\mybook1.png"));
				card.show(pane, "p4");
			}

			public void mouseEntered(MouseEvent e) { // 鼠标移动到这里的事件
				book.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 让鼠标移动到
																				// 变成小手
				book.setForeground(Color.orange); // 改变颜色
			}

			public void mouseExited(MouseEvent e) { // 鼠标离开的事件
				book.setForeground(SystemColor.textHighlight);
			}
		});
	}

	/**
	 * 我的个人信息面板
	 */
	public void ClickEvent_Userpersonal() {
		JLabel name = new JLabel("我的姓名：");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(14, 117, 218, 53);
		p_1.add(name);
		name.setFont(new Font("微软雅黑", Font.BOLD, 20));
		JLabel name1 = new JLabel(UserServise.getName());
		name1.setHorizontalAlignment(SwingConstants.LEFT);
		name1.setForeground(Color.WHITE);
		name1.setBounds(14, 170, 218, 53);
		p_1.add(name1);
		name1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		JLabel id = new JLabel("\u6211\u7684\u804C\u5DE5\u53F7:");
		id.setHorizontalAlignment(SwingConstants.LEFT);
		id.setForeground(new Color(0, 0, 0));
		id.setFont(new Font("微软雅黑", Font.BOLD, 20));
		id.setBounds(268, 117, 320, 53);
		p_1.add(id);
		JLabel id1 = new JLabel(UserServise.getId());
		id1.setHorizontalAlignment(SwingConstants.LEFT);
		id1.setForeground(Color.RED);
		id1.setBounds(268, 170, 218, 53);
		p_1.add(id1);
		id1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		JLabel sex = new JLabel("null");
		sex.setHorizontalAlignment(SwingConstants.LEFT);
		sex.setForeground(new Color(0, 0, 0));
		sex.setFont(new Font("微软雅黑", Font.BOLD, 20));
		sex.setBounds(770, 117, 218, 53);
		if (UserServise.getSex().equals("man"))
			sex.setText("我是男生");
		else
			sex.setText("我是女生");
		p_1.add(sex);

		JLabel mynumer = new JLabel("我的用户名:");
		mynumer.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer.setForeground(new Color(0, 0, 0));
		mynumer.setFont(new Font("微软雅黑", Font.BOLD, 20));
		mynumer.setBounds(513, 117, 320, 53);
		p_1.add(mynumer);
		JLabel mynumer1 = new JLabel(UserServise.getNumer());
		mynumer1.setHorizontalAlignment(SwingConstants.LEFT);
		mynumer1.setForeground(Color.BLUE);
		mynumer1.setBounds(513, 170, 218, 53);
		p_1.add(mynumer1);
		mynumer1.setFont(new Font("微软雅黑", Font.BOLD, 25));
		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/my.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_1.add(label, new Integer(Integer.MIN_VALUE));
	}

	public void ClickEvent_UserUpdata() {
		JRadioButton rdbtnUser = new JRadioButton("男", true);
		rdbtnUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.WHITE);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件处理机制
				CheckSex = "man";
			}
		});

		rdbtnUser.setBounds(184, 475, 61, 27);
		p_2.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("女");
		rdbtnAdmin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.WHITE);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "woman";
			}
		});
		rdbtnAdmin.setBounds(324, 475, 73, 27);
		p_2.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// 创建一个组 把两个对象合并为组
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// 设置组件透明
		rdbtnUser.setContentAreaFilled(false);

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_name.setColumns(10);
		textField_name.setBounds(269, 149, 208, 35);
		p_2.add(textField_name);

		JLabel label_name = new JLabel("姓名:");
		label_name.setForeground(Color.WHITE);
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_name.setBounds(128, 147, 127, 35);
		p_2.add(label_name);

		JLabel label_1 = new JLabel("性别:");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_1.setBounds(128, 405, 127, 35);
		p_2.add(label_1);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(269, 303, 208, 35);
		p_2.add(textField_pwd);

		JLabel lblPwd = new JLabel("密码:");
		lblPwd.setForeground(Color.WHITE);
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		lblPwd.setBounds(128, 303, 127, 35);
		p_2.add(lblPwd);

		JButton button = new JButton("确认");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确认的响应事件处理
				boolean nameis = false, pwdis = false, sexis = false;
				int i = JOptionPane.showConfirmDialog(null, "确定更改吗?", "提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String name = textField_name.getText();
					String pwd = textField_pwd.getText();
					String sex = CheckSex;
					if (name.length() != 0) {
						if (UserServise.updataUserName(UserServise.getId(), name))
							nameis = true;
						else
							nameis = false;
					}
					if (pwd.length() != 0) {
						if (pwd.matches("[a-zA-Z0-9]{6,15}"))
							if (UserServise.updataUserPassword(UserServise.getId(), pwd))
								pwdis = true;
							else
								pwdis = false;
						else
							JOptionPane.showMessageDialog(null, "请输入6-15位数字和字母组成的密码", "提示", JOptionPane.ERROR_MESSAGE);
					}
					if (UserServise.updataUserSex(UserServise.getId(), sex))
						sexis = true;
					else
						sexis = false;

					if (nameis && pwdis && sexis)
						JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "失败", "提示", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(616, 607, 183, 35);
		p_2.add(button);

		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/Updataimg.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_2.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 初始化考试列表的组件
	 */
	public void BookList() {
		bookListPanel = new JPanel();
		bookListPanel.setLayout(null);

		bookList = new JScrollPane(bookListPanel);
		bookList.setBounds(101, 90, 755, 464);
		bookList.setOpaque(false); // 设置透明
		bookList.getViewport().setOpaque(false); // 设置透明
		bookListWidth = bookList.getWidth();
		// 为这个容器指定 宽和 高 注：高需要动态变化的,根据添加的组件变化
		bookListPanel.setPreferredSize(new Dimension(bookListWidth, bookListPanel.getWidth()));
		tabledate[0]=new Object[]{"2019Autumn","2019/11/27","18:00","19:40","D407","1","Designmode","LiuYanjun"};
		table = new JTable();
		table.setModel(new DefaultTableModel(
				tabledate,
				columnTitleObjects
		));
		
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(0, 29, 755, 412);
		bookListPanel.add(table);
		
		JLabel label_8 = new JLabel("\u76D1\u8003\u65E5\u671F");
		label_8.setBounds(86, 5, 108, 20);
		bookListPanel.add(label_8);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_7 = new JLabel("\u8003\u8BD5\u6279\u6B21");
		label_7.setBounds(-12, 5, 97, 20);
		bookListPanel.add(label_7);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_6 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		label_6.setBounds(180, 5, 97, 20);
		bookListPanel.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_5 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		label_5.setBounds(280, 5, 97, 20);
		bookListPanel.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_4 = new JLabel("\u6559\u5BA4");
		label_4.setBounds(369, 5, 97, 20);
		bookListPanel.add(label_4);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_3 = new JLabel("\u8BFE\u7A0B\u53F7");
		label_3.setBounds(474, 5, 97, 20);
		bookListPanel.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		label_2.setBounds(565, 5, 97, 20);
		bookListPanel.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_1 = new JLabel("\u4E3B\u8003");
		label_1.setBounds(658, 5, 97, 20);
		bookListPanel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		p_3_1.add(bookList);

		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/bookimg.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_3_1.add(label, new Integer(Integer.MIN_VALUE));
	}

	
	/**
	 * 从数据库添加图书信息到窗口上显示
	 */
	public void getBook() {// x+424
		int x = 14;
		try {
			ResultSet rs = BookServise.FindsBookAll();
			cnt=0;
			while (rs.next()) {
				String exam_pici = rs.getString(1);
				String exam_date = rs.getString(2);
				String begin_time = rs.getString(3);
				String end_time = rs.getString(4);
				String classroom = rs.getString(5);
				String cno = rs.getString(6);
				String cname = rs.getString(7);
				String mainteacher = rs.getString(8);
				String fu_teacher = rs.getString(9);
				tabledate[cnt++]=new Object[]{exam_pici,exam_date,begin_time,end_time,classroom,cno,cname,mainteacher};
				
				System.out.println(fu_teacher+mainteacher);
//				LoadBookList(cno,"cm",exam_pici+exam_date+ "书名: " + begin_time + "\n" + "书号:" + end_time + "\n" + "作者: " + classroom
//						+ "\n" + "出版社: " + cno + "\n" + "类型: " + cname+mainteacher+"teacher\n"+fu_teacher, x);
				
				bookListPanel.setPreferredSize(new Dimension(bookListWidth += 380, 342));
				x += 424;
			}
			table.setModel(new DefaultTableModel(
					tabledate,
					columnTitleObjects
			));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 搜索考试的元素组件
	 */
	public void FindsBook() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(SystemColor.inactiveCaption);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "查询类型", "考试号", "课程名", "监考老师", "考试批次", "教室" }));
		comboBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		comboBox.setBounds(183, 566, 119, 32);
		p_3_1.add(comboBox);

		JTextField findsbook = new JTextField("-搜索-");
		findsbook.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本段落格式 居中
		findsbook.setBackground(SystemColor.inactiveCaption);
		findsbook.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		findsbook.setBounds(360, 567, 199, 30);
		findsbook.addMouseListener(new MouseAdapter() { // 鼠标点击文本框的响应
			@Override
			public void mouseReleased(MouseEvent e) {
				if (findsbook.getText().equals("-搜索-")) {
					findsbook.setText("");
					findsbook.setForeground(Color.black);
					findsbook.setHorizontalAlignment(SwingConstants.LEFT); // 设置文本段落格式
																			// 左对齐
					findsbook.setEditable(true);
				}
			}
		});

		p_3_1.addMouseListener(new MouseAdapter() { // 窗体获得焦点的响应
			@Override
			public void mousePressed(MouseEvent e) {
				findsbook.setText("-搜索-");
				findsbook.setForeground(Color.black);
				findsbook.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本段落格式
																			// 居中
				findsbook.setEditable(false);
			}
		});

		findsbook.addKeyListener(new KeyAdapter() {
			private ResultSet rs = null;
			private String finds;

			@Override
			public void keyReleased(KeyEvent e) { // 在文本框焦点上 输入完毕点击回车的响应
				if (e.getKeyChar() == '\n') {
					finds = findsbook.getText().toLowerCase();// 转化为小写 来比较
					if (finds.equals("-搜索-") == false && finds.length() != 0 && finds.equals("") == false) {
						String type = (String) comboBox.getSelectedItem(); // 获取考试类型

						if (comboBox.getSelectedIndex() != 0) {
							switch (type) {
							case "课程号":
								break;
							case "考试批次":
								break;
							case "教室":
								break;
							case "课程名称":
								break;
							case "监考教师":
								break;
							}
							if (rs != null)
								new QueryBookTable(rs);
						}

					}

				}
			}
		});

		p_3_1.add(findsbook);

	}
	
	/**
	 * 添加查看我的监考信息表格元素的组件
	 */
	public void SetMyBookList() {
		tabModel = new DefaultTableModel(){
			private static final long serialVersionUID = 3283588614879561211L;
			public boolean isCellEditable(int row, int column)	//设置为不可编辑状态的table ，但是能够选中
	            {
	                return false;
	            }
		};

		MyBookTabelData();
		/***************** 设置背景图片 ******************/
		ImageIcon background = new ImageIcon("userimg/MyBookList.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		p_4.add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 设置表格元素的值
	 */
	public void MyBookTabelData() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 274, 805, 403);
		scrollPane.setOpaque(false); // 设置透明
		scrollPane.getViewport().setOpaque(false); // 设置透明
		p_4.add(scrollPane);

		rowData = new Vector<Serializable>();
		columnNames = new Vector<Serializable>();
		// 设置列名
		columnNames.add("考试批次");
		columnNames.add("监考日期");
		columnNames.add("开始时间");
		columnNames.add("结束时间");
		columnNames.add("教室");
		columnNames.add("课程号");
		columnNames.add("课程名称");
		columnNames.add("主考");

		String book[][] = UserServise.BookDataByUserID(UserServise.getName());
		for (int i = 0; i < book.length; i++) {
//			System.out.println(book[i][0]);
			Vector<Serializable> hang = new Vector<Serializable>();
//			System.out.println(book[i][0]);
//			System.out.println(book[i][1]);
//			System.out.println(book[i][2]);
//			System.out.println(book[i][3]);
//			System.out.println(book[i][4]);
//			System.out.println(book[i][5]);
//			System.out.println(book[i][6]);
//			System.out.println(book[i][7]);
			hang.add(book[i][0]);
			hang.add(book[i][1]);
			hang.add(book[i][2]);
			hang.add(book[i][3]);
			hang.add(book[i][4]);
			hang.add(book[i][5]);
			hang.add(book[i][6]);
			hang.add(book[i][7]);
			rowData.add(hang);
			count++;			
		}
				
		tabModel.setDataVector(rowData, columnNames);// 这一行是关键
		tabModel.isCellEditable(0, 0);
		
		myBookTabel = new JTable(tabModel);	
		scrollPane.setViewportView(myBookTabel);
		myBookTabel.setRowHeight(25);
		myBookTabel.setBackground(new Color(39, 46, 66));
		myBookTabel.setForeground(Color.WHITE);
		myBookTabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		myBookTabel.setGridColor(Color.black);

		JTableHeader tableH = myBookTabel.getTableHeader();
		tableH.setBackground(new Color(47, 213, 249));
		tableH.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));

		hasbook = new JLabel("0\u573A");
		hasbook.setForeground(Color.WHITE);
		hasbook.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		hasbook.setBounds(125, 105, 60, 34);
		p_4.add(hasbook);

		
		maxbook = new JLabel("0\u573A");
		maxbook.setForeground(Color.WHITE);
		maxbook.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		maxbook.setBounds(423, 105, 70, 34);
		p_4.add(maxbook);
		
		credit = new JLabel("100");
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		credit.setBounds(742, 105, 80, 34);
		p_4.add(credit);

		myBookTabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {// 如果是右键点击的话
					popupMenu1.show(myBookTabel, e.getX(), e.getY());
				}
			}
		});
	}
	
	/**
	 * 使用监考操作的功能 进行更新列表的方法
	 */
	public void BorrowBookUpdataList(){
		count=0;
		rowData.clear();
		myBookTabel.removeAll();	

				
		String book[][] = UserServise.BookDataByUserID(UserServise.getId());
		for (int k = 0; k < book.length; k++) {
			Vector<Serializable> hang = new Vector<Serializable>();
			hang.add(book[k][0]);
			hang.add(book[k][1]);
			hang.add(book[k][2]);
			hang.add(book[k][3]);
			hang.add(book[k][4]);
			hang.add(book[k][5]);
			hang.add(book[k][6]);
			hang.add(book[k][7]);
			rowData.add(hang);
			count++;			
		}						
		tabModel.setDataVector(rowData, columnNames);// 这一行是关键
		hasbook.setText(count + "本");
		maxbook.setText(--myBookSize + "本");
	}
	
	/**
	 * 右键菜单的组件组件
	 */
	public void MouseEventButtonListener() {
		popupMenu1 = new PopupMenu(); // 创建菜单对象
		MenuItem menuItem1 = new MenuItem(); // 添加子菜单
		menuItem1.setLabel("我要还书"); // 规定名称
		popupMenu1.add(menuItem1); // 添加到菜单对象中
		getContentPane().add(popupMenu1); // 添加到窗体上
		menuItem1.addActionListener(new ActionListener() { // 菜单1的事件监听
			public void actionPerformed(ActionEvent e) {
				int setSelect[] = myBookTabel.getSelectedRows(); // 获取选中的列表项
																	// 升序排列
				String bookid = null;
				for (int i : setSelect) {
					bookid = (String) myBookTabel.getValueAt(i, 0);
					BorrowAndReturnService.Bookreturn(bookid, UserServise.getId());
				}
				maxbook.setText(UserServise.getMaxBook() + "本");
				
				int rowcount = tabModel.getRowCount() - 1;
				if (rowcount >= 0) {
					for (int k = setSelect.length - 1; k >= 0; k--) {
						tabModel.removeRow(setSelect[k]);
						hasbook.setText(--count + "本");			//刷新本书统计
						tabModel.setRowCount(rowcount);
					}			
				}
				maxbook.setText(++myBookSize+ "本");
				myBookTabel.revalidate();//刷新表格

			}
		});
	}


/******************************************************************************************/
class QueryBookTable extends JFrame {

	private static final long serialVersionUID = 8456094523646445002L;
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	private JScrollPane jsp;
	private ResultSet rs;
	private Vector<Serializable> rowData, columnNames;
	private PopupMenu popupMenu1;
	private JTable jt;

	// 构造函数
	public QueryBookTable(ResultSet rs) {
		columnNames = new Vector<Serializable>();
		columnNames.add("考试批次");
		columnNames.add("监考日期");
		columnNames.add("开始时间");
		columnNames.add("结束时间");
		columnNames.add("教室");
		columnNames.add("课程号");
		columnNames.add("课程名称");
		columnNames.add("主考");
		this.rs = rs;
		rowData = gethang();
		jt = new JTable(rowData, columnNames);
		MouseEventButtonListener();
		jt.setRowHeight(20);
		jt.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {// 如果是右键点击的话
					popupMenu1.show(jt, e.getX(), e.getY());
				}
			}
		});
		jsp = new JScrollPane(jt);
		getContentPane().add(jsp);
		this.setSize(600, 300);
		setLocationRelativeTo(null);// 让窗口居中显示
		setResizable(false);// 禁用窗口最大化按钮
		setLocationRelativeTo(null);
		setTitle("查询结果");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}

		});
		this.setVisible(true);
	}

	@SuppressWarnings("finally")
	public Vector<Serializable> gethang() {
		Vector<Serializable> rowData = new Vector<Serializable>();
		try {

			while (rs.next()) {
				Vector<Serializable> hang = new Vector<Serializable>();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));				
				rowData.add(hang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return rowData;
		}
	}


}
}