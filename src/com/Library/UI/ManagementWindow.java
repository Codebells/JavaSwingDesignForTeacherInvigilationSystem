package com.Library.UI;

import javax.swing.*;
import com.Library.DataBase.DataBase;
import com.Library.Service.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *  管理员类
 * 
 * @author Codebells
 *
 */
public class ManagementWindow extends JFrame {
	private static final long serialVersionUID = 6918457427019512011L;
	private JPanel imagePanel;
	private ImageIcon background;
	private JPanel pane = null; // 主要的JPanel
	private CardLayout card = null; // CardLayout布局管理器
	private JPanel p_1 = null, p_2 = null, p_3 = null, p_4 = null, p_5 = null, p_6 = null, p_7 = null, p_8 = null,
			p_9 = null, p_10 = null, P_main = null;
	private String CheckSex = "男";// 注册普通用户使用的性别判断的功能标识符
	private Vector<Serializable> rowData, columnNames; // rowData用来存放行数据 ,
														// columnNames存放列名
	private int Mark;// 作为表格选择功能的标记位
	private String data;// 作为表格查看的数据 传递的参数
	private ResultSet rstable = null;// 作为表格的查看数据库调用的方法
	private JPanel P_table = null;
	private Icon ico = new ImageIcon("images/button.png");// 设置按钮的背景颜色
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/*
	 * public static void main(String[] args) {//单独测试用的main new
	 * ManagementWindow(); }
	 */
	/**
	 * 构造方法
	 */
	public ManagementWindow() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();// 关闭数据库连接
				System.exit(0);
			}
		});
		setTitle("\u6559\u52A1\u5728\u7EBF");
		setBounds(100, 100, 1205, 830);
		setLocationRelativeTo(null);// 让窗口居中显示
		setResizable(false);// 禁用窗口最大化按钮
		setImg();// 加载图片
		CardPanle();// 卡片布局加载
		Label(); // 标签上方组件
		ButtonSet();// 加载功能按钮
		P_main.setBackground(Color.gray);
		setVisible(true);
	}

	/**
	 * 注册卡片布局框架
	 */
	public void CardPanle() {
		card = new CardLayout(5, 5);
		pane = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
		pane.setBounds(228, 120, 957, 662);
		getContentPane().add(pane);

		P_main = new JPanel();
		p_1 = new JPanel();
		p_2 = new JPanel();
		p_3 = new JPanel();
		p_4 = new JPanel();
		p_5 = new JPanel();
		p_6 = new JPanel();
		p_7 = new JPanel();
		p_8 = new JPanel();
		p_9 = new JPanel();
		p_10 = new JPanel();
		P_main.setLayout(null);
		p_1.setLayout(null);
		p_2.setLayout(null);
		p_3.setLayout(null);
		p_4.setLayout(null);
		p_5.setLayout(null);
		p_6.setLayout(null);
		p_7.setLayout(null);
		p_8.setLayout(null);
		p_9.setLayout(null);
		p_10.setLayout(null);

		ClickEvent_Adminadd();// 添加管理员窗体
		ClickEvent_AdminDelect();// 删除管理员窗体
		ClickEvent_UserAdd();// 添加用户窗体
		ClickEvent_UserUpdata();// 更新用户窗体
		ClickEvent_UserDelect();// 删除用户窗体
		ClickEvent_UserQuery();// 查找用户窗体
		ClickEvent_BookAddAndUpdata();// 添加或更新考试窗体
		ClickEvent_BookBorrowQueryByID();// 查找教师监考了那些考试的窗体
		ClickEvent_QueryBooks();// 查询监考考试窗体
		ClickEvent_DelectBook();// 删除监考考试窗体
		SetP_Main_Img();

		pane.add(P_main, "P_main"); // 讲窗体标签添加到界面上
		pane.add(p_1, "p1");
		pane.add(p_2, "p2");
		pane.add(p_3, "p3");
		pane.add(p_4, "p4");
		pane.add(p_5, "p5");
		pane.add(p_6, "p6");
		pane.add(p_7, "p7");

		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(191, 309, 208, 35);
		p_7.add(textField_2);

		JLabel label = new JLabel("\u6559\u5BA4:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label.setBounds(50, 309, 127, 35);
		p_7.add(label);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(690, 310, 208, 35);
		p_7.add(textField_3);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u53F7:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_1.setBounds(538, 309, 127, 35);
		p_7.add(label_1);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(191, 391, 208, 35);
		p_7.add(textField_4);

		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_2.setBounds(50, 391, 127, 35);
		p_7.add(label_2);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(690, 392, 208, 35);
		p_7.add(textField_5);

		JLabel label_3 = new JLabel("\u4E3B\u8003:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_3.setBounds(538, 391, 127, 35);
		p_7.add(label_3);

		JLabel label_4 = new JLabel("\u526F\u8003:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_4.setBounds(50, 463, 127, 35);
		p_7.add(label_4);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(191, 464, 208, 35);
		p_7.add(textField_6);
		pane.add(p_8, "p8");
		pane.add(p_9, "p9");
		pane.add(p_10, "p10");
	}

	/**
	 * 功能按钮组件
	 */
	public void ButtonSet() {
		JButton BtnAdmin_add = new JButton("\u65B0\u589E\u7BA1\u7406\u5458", ico);
		BtnAdmin_add.setForeground(Color.WHITE);
		BtnAdmin_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnAdmin_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 添加管理员的点击事件
				card.show(pane, "p1");
			}
		});
		BtnAdmin_add.setBounds(8, 164, 190, 27);
		getContentPane().add(BtnAdmin_add);

		JButton BtnAdmin_delect = new JButton("\u5220\u9664\u7BA1\u7406\u5458", ico);// 间隙+10
		BtnAdmin_delect.setForeground(Color.WHITE);
		BtnAdmin_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnAdmin_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 删除点击
				card.show(pane, "p2");
			}
		});
		BtnAdmin_delect.setBounds(8, 201, 190, 27);
		getContentPane().add(BtnAdmin_delect);

		JButton BtnUser_add = new JButton("\u6DFB\u52A0\u6559\u5E08", ico);
		BtnUser_add.setForeground(Color.WHITE);
		BtnUser_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_add.addActionListener(new ActionListener() { // 添加一个普通用户
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "p3");
			}
		});
		BtnUser_add.setBounds(8, 238, 190, 27);
		getContentPane().add(BtnUser_add);

		JButton BtnUser_updata = new JButton("\u66F4\u65B0\u6559\u5E08", ico);
		BtnUser_updata.setForeground(Color.WHITE);
		BtnUser_updata.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_updata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 更新用户信息
				card.show(pane, "p4");
			}
		});
		BtnUser_updata.setBounds(8, 275, 190, 27);
		getContentPane().add(BtnUser_updata);

		JButton BtnUser_delect = new JButton("\u5220\u9664\u6559\u5E08", ico);
		BtnUser_delect.setForeground(Color.WHITE);
		BtnUser_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 删除用户
				card.show(pane, "p5");
			}
		});
		BtnUser_delect.setBounds(8, 312, 190, 27);
		getContentPane().add(BtnUser_delect);

		JButton BtnUser_query = new JButton("\u67E5\u627E\u6559\u5E08", ico);
		BtnUser_query.setForeground(Color.WHITE);
		BtnUser_query.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 查找普通用户的信息
				card.show(pane, "p6");
			}
		});
		BtnUser_query.setBounds(8, 349, 190, 27);
		getContentPane().add(BtnUser_query);

		JButton BtnBook_add = new JButton("\u6DFB\u52A0\u6216\u66F4\u65B0\u8003\u8BD5", ico);
		BtnBook_add.setForeground(Color.WHITE);
		BtnBook_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 添加图书
				card.show(pane, "p7");
			}
		});
		BtnBook_add.setBounds(8, 406, 190, 27);
		getContentPane().add(BtnBook_add);

		JButton BtnBook_UserFindsBook = new JButton("\u67E5\u627E\u6559\u5E08\u76D1\u8003\u4FE1\u606F", ico);
		BtnBook_UserFindsBook.setForeground(Color.WHITE);
		BtnBook_UserFindsBook.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_UserFindsBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 查看用户有多少场监考
				card.show(pane, "p8");

			}
		});
		BtnBook_UserFindsBook.setBounds(8, 443, 190, 27);
		getContentPane().add(BtnBook_UserFindsBook);

		JButton BtnBook_delect = new JButton("\u5220\u9664\u8003\u8BD5", ico);
		BtnBook_delect.setForeground(Color.WHITE);
		BtnBook_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 删除考试
				card.show(pane, "p9");
			}
		});
		BtnBook_delect.setBounds(8, 480, 190, 27);
		getContentPane().add(BtnBook_delect);

		JButton BtnBook_query = new JButton("\u67E5\u8BE2\u8003\u8BD5", ico);
		BtnBook_query.setForeground(Color.WHITE);
		BtnBook_query.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 查询考试
				card.show(pane, "p10");
			}
		});
		BtnBook_query.setBounds(8, 517, 190, 27);
		getContentPane().add(BtnBook_query);

	}

	/**
	 * 标题栏处的组件
	 */
	public void Label() {
		JLabel lblNewLabel = new JLabel("角色：管理员");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(1100, 84, 100, 18);
		imagePanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("欢迎您:" + AdminServise.getID());
		lblNewLabel_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(977, 84, 100, 18);
		getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("修改密码");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 修改密码
				String pwd = JOptionPane.showInputDialog("输入新密码");
				if (pwd != null)
					if (pwd.length() != 0) {
						if (pwd.matches("[a-zA-Z0-9]{6,15}")) {
							if (AdminServise.UpdataAdmin(AdminServise.getID(), pwd))
								JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "遇到未知的异常", "提示", JOptionPane.PLAIN_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "请输入6-15位数字和字母组成的密码", "提示", JOptionPane.PLAIN_MESSAGE);
					}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(235, 42, 90, 27);
		getContentPane().add(btnNewButton);

		JButton btnNewButton1 = new JButton("注销");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击注销的响应事件
				int i = JOptionPane.showConfirmDialog(null, "确定注销吗?", "提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					DataBase.Colse(); // 关闭数据库连接
					new LoginWindow();
					dispose();
				}
			}
		});
		btnNewButton1.setForeground(Color.BLACK);
		btnNewButton1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton1.setContentAreaFilled(false);
		btnNewButton1.setBounds(218, 80, 90, 27);
		getContentPane().add(btnNewButton1);

		JButton button_Main = new JButton("主页");
		button_Main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "P_main");
			}
		});
		button_Main.setForeground(Color.BLACK);
		button_Main.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_Main.setContentAreaFilled(false);
		button_Main.setBounds(311, 80, 90, 27);
		getContentPane().add(button_Main);
	}

	/**
	 * 加载背景图片
	 */
	public void setImg() {
		background = new ImageIcon("images/management.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位
		// 置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// 内容窗格默认的布局管理器为BorderLayout new FlowLayout()
		imagePanel.setLayout(null);

		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 添加管理员
	 */
	public void ClickEvent_Adminadd() {
		JTextField text_id = new JTextField();
		text_id.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		text_id.setForeground(Color.RED);
		text_id.setBounds(410, 206, 182, 30);
		p_1.add(text_id);
		text_id.setColumns(10);

		JTextField text_pwd = new JTextField();
		text_pwd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		text_pwd.setForeground(Color.RED);
		text_pwd.setBounds(410, 303, 182, 30);
		p_1.add(text_pwd);
		text_pwd.setColumns(10);

		JLabel lbl_ID = new JLabel("账号：");
		lbl_ID.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lbl_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ID.setBounds(230, 212, 72, 18);
		p_1.add(lbl_ID);

		JLabel lbl_pwd = new JLabel("密码：");
		lbl_pwd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lbl_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pwd.setBounds(230, 309, 72, 18);
		p_1.add(lbl_pwd);

		JLabel lblNewLabel_3 = new JLabel("请输入字母和数字组成的6-15位的密码和账号");
		lblNewLabel_3.setBounds(230, 378, 332, 42);
		lblNewLabel_3.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		p_1.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 确定事件
				String id = text_id.getText();
				String pwd = text_pwd.getText();
				boolean isid = id.matches("[a-zA-Z0-9]{1,15}");
				boolean ispwd = pwd.matches("[a-zA-Z0-9]{6,15}");
				if (id.length() != 0 && pwd.length() != 0) {

					if (isid == true && ispwd == true) {
						if (AdminServise.addNewAdmin(id, pwd))
							JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "账户名已被注册", "提示", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "请输入6-15位字母和数字组成的账号和密码", "提示", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton_1.setBounds(355, 503, 162, 37);
		p_1.add(btnNewButton_1);
	}

	/**
	 * 删除管理员
	 */
	public void ClickEvent_AdminDelect() {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField.setBounds(361, 261, 200, 37);
		p_2.add(textField);
		textField.setColumns(10);

		JLabel lbl_delect = new JLabel("输入要删除的账号：");
		lbl_delect.setFont(new Font("微软雅黑", Font.BOLD, 19));
		lbl_delect.setForeground(Color.BLACK);
		lbl_delect.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_delect.setBounds(135, 261, 183, 37);
		p_2.add(lbl_delect);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(361, 541, 200, 37);
		p_2.add(lblNewLabel_2);

		JButton btnNewButton_2 = new JButton("确定");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				if (id.length() != 0) {
					if (AdminServise.deleteAdmin(id))
						lblNewLabel_2.setText("成功");
					else
						lblNewLabel_2.setText("失败");
				} else
					lblNewLabel_2.setText("请输入完整");
			}
		});
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 19));
		btnNewButton_2.setBounds(361, 435, 176, 37);
		p_2.add(btnNewButton_2);
	}

	/**
	 * 添加用户
	 */
	public void ClickEvent_UserAdd() {
		/****** 单选按钮组 ******/
		CheckSex = "man";
		JRadioButton rdbtnUser = new JRadioButton("男", true);
		rdbtnUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.BLACK);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件处理机制
				CheckSex = "man";
			}
		});

		rdbtnUser.setBounds(157, 407, 61, 27);
		p_3.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("女");
		rdbtnAdmin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.BLACK);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "woman";
			}
		});
		rdbtnAdmin.setBounds(284, 407, 73, 27);
		p_3.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// 创建一个组 把两个对象合并为组
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// 设置组件透明
		rdbtnUser.setContentAreaFilled(false);
		// *******end*******//

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_name.setBounds(149, 310, 208, 35);
		p_3.add(textField_name);
		textField_name.setColumns(10);

		JTextField textField_numer = new JTextField();
		textField_numer.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_numer.setColumns(10);
		textField_numer.setBounds(638, 196, 208, 35);
		p_3.add(textField_numer);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(638, 310, 208, 35);
		p_3.add(textField_pwd);

		JTextField textField_id = new JTextField();
		textField_id.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_id.setColumns(10);
		textField_id.setBounds(149, 196, 208, 35);
		p_3.add(textField_id);

		JLabel label_usernumer = new JLabel("账号:");
		label_usernumer.setHorizontalAlignment(SwingConstants.CENTER);
		label_usernumer.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_usernumer.setBounds(486, 198, 127, 35);
		p_3.add(label_usernumer);

		JLabel label_userpwd = new JLabel("密码:");
		label_userpwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_userpwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_userpwd.setBounds(486, 310, 127, 35);
		p_3.add(label_userpwd);

		JLabel label_userid = new JLabel("\u804C\u5DE5\u53F7:");
		label_userid.setHorizontalAlignment(SwingConstants.CENTER);
		label_userid.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_userid.setBounds(8, 196, 127, 35);
		p_3.add(label_userid);

		JLabel label_username = new JLabel("姓名:");
		label_username.setHorizontalAlignment(SwingConstants.CENTER);
		label_username.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_username.setBounds(8, 310, 127, 35);
		p_3.add(label_username);

		JLabel label_userSex = new JLabel("性别:");
		label_userSex.setHorizontalAlignment(SwingConstants.CENTER);
		label_userSex.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_userSex.setBounds(8, 407, 127, 35);
		p_3.add(label_userSex);

		JLabel lblNewLabel_4 = new JLabel("输入9位数字组成的学号,数字加字母组成的6-15位的账号密码");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(34, 503, 421, 35);
		p_3.add(lblNewLabel_4);

		JButton btn_userAddOK = new JButton("注册");
		btn_userAddOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击注册后的响应事件处理
				String id = textField_id.getText();
				String name = textField_name.getText();
				String numer = textField_numer.getText();
				String pwd = textField_pwd.getText();
				String sex = CheckSex;
				if (id.length() != 0 && name.length() != 0 && numer.length() != 0 && pwd.length() != 0) {
					if (pwd.matches("[a-zA-Z0-9]{6,15}") == true) {
						if (UserServise.addNewUser(name, id, sex, numer, pwd))
							JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "失败", "提示", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "请输入9位数字组成的职工号,数字加字母组成的6-15位的账号密码", "提示",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		btn_userAddOK.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btn_userAddOK.setBounds(655, 435, 183, 35);
		p_3.add(btn_userAddOK);
	}

	/**
	 * 更新用户
	 */
	public void ClickEvent_UserUpdata() {
		JRadioButton rdbtnUser = new JRadioButton("男", true);
		rdbtnUser.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.BLACK);
		CheckSex = "man";
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件处理机制
				CheckSex = "man";
			}
		});

		rdbtnUser.setBounds(203, 449, 61, 27);
		p_4.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("女");
		rdbtnAdmin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.BLACK);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "woman";
			}
		});
		rdbtnAdmin.setBounds(330, 449, 73, 27);
		p_4.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// 创建一个组 把两个对象合并为组
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// 设置组件透明
		rdbtnUser.setContentAreaFilled(false);
		// ******end**************//
		JTextField textField_lodID = new JTextField();
		textField_lodID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_lodID.setColumns(10);
		textField_lodID.setBounds(292, 231, 208, 35);
		p_4.add(textField_lodID);

		JLabel label_id = new JLabel("\u8981\u4FEE\u6539\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_id.setBounds(79, 229, 190, 35);
		p_4.add(label_id);

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_name.setColumns(10);
		textField_name.setBounds(195, 344, 208, 35);
		p_4.add(textField_name);

		JLabel label = new JLabel("姓名:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label.setBounds(54, 344, 127, 35);
		p_4.add(label);

		JLabel label_1 = new JLabel("性别:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_1.setBounds(54, 441, 127, 35);
		p_4.add(label_1);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(641, 344, 208, 35);
		p_4.add(textField_pwd);

		JLabel lblPwd = new JLabel("密码:");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		lblPwd.setBounds(500, 344, 127, 35);
		p_4.add(lblPwd);

		JButton button = new JButton("确认");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确认的响应事件处理
				boolean nameis = false, pwdis = false, sexis = false;
				int i = JOptionPane.showConfirmDialog(null, "确定更改吗?", "提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String id = textField_lodID.getText();
					String name = textField_name.getText();
					String pwd = textField_pwd.getText();
					String sex = CheckSex;
					if (name.length() != 0) {
						if (UserServise.updataUserName(id, name))
							nameis = true;
						else
							nameis = false;
					}
					if (pwd.length() != 0) {
						if (pwd.matches("[a-zA-Z0-9]{6,15}"))
							if (UserServise.updataUserPassword(id, pwd))
								pwdis = true;
							else
								pwdis = false;
						else
							JOptionPane.showMessageDialog(null, "请输入6-15位数字和字母组成的密码", "提示", JOptionPane.ERROR_MESSAGE);
					}
					if (UserServise.updataUserSex(id, sex))
						sexis = true;
					else
						sexis = false;

					if (nameis && pwdis && sexis)
						JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "获取ID失败", "提示", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(701, 469, 183, 35);
		p_4.add(button);
	}

	/**
	 * 删除用户
	 */
	public void ClickEvent_UserDelect() {
		JLabel label = new JLabel("\u8981\u5220\u9664\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.BOLD, 19));
		label.setBounds(209, 247, 224, 37);
		p_5.add(label);

		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(476, 247, 200, 37);
		p_5.add(textField_1);

		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确定响应事件
				String id = textField_1.getText();
				if (id.length() != 0) {
					if (UserServise.deleteUser(id))
						JOptionPane.showMessageDialog(null, "成功", "提示", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "获取ID失败", "提示", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 19));
		button.setBounds(476, 421, 176, 37);
		p_5.add(button);
	}

	/**
	 * 查找用户
	 */
	public void ClickEvent_UserQuery() {
		JLabel label = new JLabel("\u8981\u67E5\u627E\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.BOLD, 19));
		label.setBounds(62, 31, 224, 37);
		p_6.add(label);

		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(353, 32, 200, 37);
		p_6.add(textField_1);

		JTextPane textQuery = new JTextPane();
		textQuery.setForeground(Color.BLUE);
		textQuery.setOpaque(false);
		textQuery.setEditable(false);
		textQuery.setFont(new Font("微软雅黑 Light", Font.PLAIN, 47));
		textQuery.setBounds(77, 123, 842, 516);
		p_6.add(textQuery);

		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确定响应事件
				String id = textField_1.getText();
				String query = UserServise.QueryUser(id);
				if (id.length() != 0) {
					textQuery.setText(query);
				} else
					JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 19));
		button.setBounds(674, 31, 176, 37);
		p_6.add(button);
	}

	/**
	 * 更新或添加考试
	 */
	public void ClickEvent_BookAddAndUpdata() {
		JRadioButton rdbtnupdata = new JRadioButton("\u66F4\u65B0\u8003\u8BD5");
		rdbtnupdata.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnupdata.setForeground(Color.BLACK);
		rdbtnupdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件处理机制
				CheckSex = "updata";
			}
		});

		rdbtnupdata.setBounds(220, 60, 105, 27);
		p_7.add(rdbtnupdata);

		JRadioButton rdbtnadd = new JRadioButton("\u6DFB\u52A0\u8003\u8BD5",true);
		rdbtnadd.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		rdbtnadd.setForeground(Color.BLACK);
		rdbtnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "add";
			}
		});
		rdbtnadd.setBounds(370, 60, 127, 27);
		p_7.add(rdbtnadd);
		ButtonGroup grout = new ButtonGroup();// 创建一个组 把两个对象合并为组
		grout.add(rdbtnadd);
		grout.add(rdbtnupdata);
		rdbtnupdata.setContentAreaFilled(false);
		// **********end*************//
		JLabel label_select = new JLabel("选择需要的功能:");
		label_select.setHorizontalAlignment(SwingConstants.CENTER);
		label_select.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		label_select.setBounds(67, 55, 127, 35);
		p_7.add(label_select);

		JTextField text_id = new JTextField();
		text_id.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		text_id.setColumns(10);
		text_id.setBounds(191, 152, 208, 35);
		p_7.add(text_id);

		JLabel label_id = new JLabel("\u8003\u8BD5\u6279\u6B21\uFF1A");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_id.setBounds(50, 152, 127, 35);
		p_7.add(label_id);

		JLabel label_name = new JLabel("\u76D1\u8003\u65E5\u671F\uFF1A");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_name.setBounds(538, 154, 127, 35);
		p_7.add(label_name);

		JTextField text_name = new JTextField();
		text_name.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		text_name.setColumns(10);
		text_name.setBounds(690, 152, 208, 35);
		p_7.add(text_name);

		JTextField text_press = new JTextField();
		text_press.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		text_press.setColumns(10);
		text_press.setBounds(690, 228, 208, 35);
		p_7.add(text_press);

		JLabel label_press = new JLabel("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_press.setHorizontalAlignment(SwingConstants.CENTER);
		label_press.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_press.setBounds(538, 227, 127, 35);
		p_7.add(label_press);

		JTextField text_author = new JTextField();
		text_author.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		text_author.setColumns(10);
		text_author.setBounds(191, 227, 208, 35);
		p_7.add(text_author);

		JLabel label_author = new JLabel("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_author.setHorizontalAlignment(SwingConstants.CENTER);
		label_author.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label_author.setBounds(50, 227, 127, 35);
		p_7.add(label_author);

		JButton button_OK = new JButton("确定");
		button_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击确定按钮的事件
				int i = JOptionPane.showConfirmDialog(null, "确定无误吗?", "提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String id = text_id.getText();
					String name = text_name.getText();
					String author = text_author.getText();
					String press = text_press.getText();
					String classroom = textField_2.getText();
					String cno = textField_3.getText();
					String cname = textField_4.getText();
					String main = textField_5.getText();
					String fu = textField_6.getText();
					if (CheckSex == "add") {
						if (id.length() != 0 && name.length() != 0 && author.length() != 0 && press.length() != 0) {

							if (BookServise.NewBook(id, name, author, press, classroom, cno, cname, main, fu)) {
								JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.PLAIN_MESSAGE);
								UserServise.stat = 1;
								String mail=UserServise.UserMail(main);
								SentMail sentMail=new SentMail(mail);
								sentMail.Sent();
								String mail1=UserServise.UserMail(fu);
								SentMail sentMail1=new SentMail(mail1);
								sentMail1.Sent();
							} else
								JOptionPane.showMessageDialog(null, "添加失败课程号存在", "错误", JOptionPane.ERROR_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE);
					}
					if (CheckSex == "updata") {
						if (id.length() != 0) {
							if (BookServise.findBookByON(id)) { // 查找这考试的课程号是否存在
								JOptionPane.showMessageDialog(null, "更新成功", "错误", JOptionPane.ERROR_MESSAGE);
							} else
								JOptionPane.showMessageDialog(null, "不存在的课程号", "错误", JOptionPane.ERROR_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "更新需要输入课程号", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_OK.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_OK.setBounds(697, 496, 183, 35);
		p_7.add(button_OK);
	}

	/**
	 * 设置表格组件
	 */
	public void SetTable() {
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

		rowData = SetTableData(Mark, data);

		JTable jt = new JTable(rowData, columnNames);
		jt.setForeground(Color.BLUE);
		jt.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setSize(819, 337);
		jsp.setLocation(46, 261);
		P_table.add(jsp);

	}

	/**
	 * 设置表格显示的数据内容
	 */
	public Vector<Serializable> SetTableData(int Mark, String data) {

		Vector<Serializable> rowData = new Vector<Serializable>();
		if (Mark == 1) {
			String book[][] = UserServise.BookDataByUserID(UserServise.QueryUserName(data));
			System.out.println("now"+UserServise.QueryUserName(data));
			for (int i = 0; i < book.length; i++) {
				Vector<Serializable> hang = new Vector<Serializable>();
				hang.add(book[i][0]);
				System.out.println(book[i][0]);
				hang.add(book[i][1]);
				hang.add(book[i][2]);
				hang.add(book[i][3]);
				hang.add(book[i][4]);
				hang.add(book[i][5]);
				hang.add(book[i][6]);
				hang.add(book[i][7]);
				rowData.add(hang);
			}
		}
		if (Mark == 2) {
			ResultSet rs = rstable;
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
			}
		}
		return rowData;
	}

	/**
	 * 查询教师监考信息
	 */
	public void ClickEvent_BookBorrowQueryByID() {
		JLabel lblid = new JLabel(
				"\u8BF7\u8F93\u5165\u60A8\u8981\u67E5\u8BE2\u6559\u5E08\u7684\u76D1\u8003\u4FE1\u606FID\u53F7:");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setForeground(Color.BLACK);
		lblid.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblid.setBounds(27, 56, 289, 37);
		p_8.add(lblid);

		JTextField textField_FindBook = new JTextField();
		textField_FindBook.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_FindBook.setColumns(10);
		textField_FindBook.setBounds(356, 57, 200, 37);
		p_8.add(textField_FindBook);

		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 点击事件
				String id = textField_FindBook.getText();
				if (id.length() != 0) {
					Mark = 1;
					data = id;
					P_table = p_8;
					SetTable();
				} else
					JOptionPane.showMessageDialog(null, "您没有输入", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 19));
		button.setBounds(677, 56, 176, 37);
		p_8.add(button);
	}

	/**
	 * 查询考试
	 */
	public void ClickEvent_QueryBooks() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-->请选择查询类型--<", "考试批次", "监考日期", "教室", "课程号", "课程名称","主考教师","副考教师", "全部" }));
		comboBox.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		comboBox.setBounds(74, 61, 195, 32);
		p_10.add(comboBox);

		JTextField textField_QueryBook = new JTextField();
		textField_QueryBook.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		textField_QueryBook.setBounds(338, 61, 214, 32);
		p_10.add(textField_QueryBook);
		textField_QueryBook.setColumns(10);

		JButton btnNewButton_OK = new JButton("查询");
		btnNewButton_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击事件
				String query = textField_QueryBook.getText();
				String type = (String) comboBox.getSelectedItem(); // 获取书籍类型

				if ((query.length() != 0 || type == "全部") && comboBox.getSelectedIndex() != 0) {
					Mark = 2;
					switch (type) {
					case "考试批次":
						P_table = p_10;
						rstable = BookServise.FindsBookexam_pici(query);
						SetTable();
						break;
					case "监考日期":
						P_table = p_10;
						rstable = BookServise.FindsBookDate(query);
						SetTable();
						break;
					case "教室":
						P_table = p_10;
						rstable = BookServise.FindsBookclassroom(query);
						SetTable();
						break;
					case "课程号":
						P_table = p_10;
						rstable = BookServise.FindsBookID(query);
						SetTable();
						break;
					case "课程名称":
						P_table = p_10;
						rstable = BookServise.FindsBookcname(query);
						SetTable();
						break;
					case "主考教师":
						P_table = p_10;
						rstable = BookServise.FindsBookmainteacher(query);
						SetTable();
						break;
					case "副考教师":
						P_table = p_10;
						rstable = BookServise.FindsBookfu_teacher(query);
						SetTable();
						break;
					case "全部":
						P_table = p_10;
						rstable = BookServise.FindsBookAll();
						SetTable();
						break;
					}

				} else
					JOptionPane.showMessageDialog(null, "您没有输入", "提示", JOptionPane.ERROR_MESSAGE);

			}
		});
		btnNewButton_OK.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		btnNewButton_OK.setBounds(641, 61, 151, 32);
		p_10.add(btnNewButton_OK);
	}

	/**
	 * 删除考试
	 */
	public void ClickEvent_DelectBook() {
		JLabel label_tishi = new JLabel(
				"\u8BF7\u8F93\u5165\u60A8\u8981\u5220\u9664\u7684\u7684\u8003\u8BD5\u7684\u8BFE\u7A0B\u53F7:");
		label_tishi.setHorizontalAlignment(SwingConstants.CENTER);
		label_tishi.setForeground(Color.BLACK);
		label_tishi.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_tishi.setBounds(301, 134, 289, 37);
		p_9.add(label_tishi);

		JTextField textField_Bookid = new JTextField();
		textField_Bookid.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_Bookid.setColumns(10);
		textField_Bookid.setBounds(345, 286, 200, 37);
		p_9.add(textField_Bookid);

		JLabel lbl_Prompt = new JLabel();
		lbl_Prompt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Prompt.setForeground(Color.RED);
		lbl_Prompt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Prompt.setBounds(334, 417, 228, 37);
		p_9.add(lbl_Prompt);

		JButton button_Ok = new JButton("删除");
		button_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 点击删除的响应
				String id = textField_Bookid.getText();
				if (id.length() != 0) {
					if (BookServise.deleteBook(id))
						lbl_Prompt.setText("删除成功");
					else
						lbl_Prompt.setText("没找到此课程号，删除失败");
				} else
					lbl_Prompt.setText("请输入完整");
			}
		});
		button_Ok.setFont(new Font("微软雅黑", Font.BOLD, 19));
		button_Ok.setBounds(362, 523, 176, 37);
		p_9.add(button_Ok);
	}

	/**
	 * 设置主卡片的背景
	 */
	public void SetP_Main_Img() {
		ImageIcon background = new ImageIcon("images/main.png");// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		label.setBounds(0, 0, 957, 662);
		P_main.add(label, new Integer(Integer.MIN_VALUE));
	}
}
