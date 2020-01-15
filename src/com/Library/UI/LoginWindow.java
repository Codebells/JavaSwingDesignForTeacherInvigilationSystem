package com.Library.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import com.Library.DataBase.DataBase;
import com.Library.Service.AdminServise;
import com.Library.Service.UserServise;

/**
 * 系统用户登录界面
 * @author Codebells
 */
public class LoginWindow extends JFrame {
private static final long serialVersionUID = -3331349633487502332L;
	 private JPanel imagePanel;
	 private ImageIcon background;
	 private JTextField text_numer;
	 private JPasswordField text_password;
	 private int Checkuser=0; //用来判断 选择的用户类别    0代表user  1代表admin;
	
	


	public LoginWindow() {
		setTitle("教务在线系统");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();//关闭数据库连接
				System.exit(0);
			}
			
		});
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);//让窗口居中显示
		setResizable(false);//禁用窗口最大化按钮
		setImg();  //加载图片
		addRdbtn();  //加载单选按钮组
		ButtonAndLabelSet(); //加载按钮和标签
		//Icon i=new ImageIcon("images/01.png");//设置按钮的背景颜色	
		setVisible(true);
	}
	
	public void ButtonAndLabelSet(){
		JButton btnOK = new JButton("登录");
		btnOK.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnOK.setForeground(Color.WHITE);
		btnOK.setContentAreaFilled(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //点击事件
				String numer=text_numer.getText();
				String pwd=String.valueOf(text_password.getPassword());
				if(numer.length()!=0 && pwd.length()!=0){
					
				if(Checkuser==0){//USER
					if(UserServise.UserCheck(numer, pwd)){
						new Library();
						dispose();
				}else
					JOptionPane.showMessageDialog(null, "密码或账户名错误", "提示", JOptionPane.ERROR_MESSAGE); 

					}
				else if(Checkuser==1){//ADMIN
					if(AdminServise.AdminCheck(numer, pwd)){
						new ManagementWindow();
						dispose();
					}else
						JOptionPane.showMessageDialog(null, "密码或账户名错误", "提示", JOptionPane.ERROR_MESSAGE); 

				}
			}else
				JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE); 

			}
		});
		btnOK.setBounds(360, 455, 100, 25);
		getContentPane().add(btnOK);
		
		text_numer = new JTextField();
		text_numer.setForeground(Color.WHITE);
		text_numer.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_numer.setBounds(339, 352, 153, 24);
		getContentPane().add(text_numer);
		text_numer.setColumns(10);
		text_numer.setOpaque(false);  //设置输入框透明
		
		text_password = new JPasswordField();
		text_password.setForeground(Color.WHITE);
		text_password.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		text_password.setBounds(339, 391, 153, 24);
		getContentPane().add(text_password);
		text_password.setColumns(10);
		text_password.setOpaque(false);
		text_password.addKeyListener(new KeyAdapter() {
		     	@Override
		     	public void keyReleased(KeyEvent e) {   //在文本框焦点上 输入完毕点击回车的响应		 
		     		if(e.getKeyChar()=='\n'){
		     			String numer=text_numer.getText();
						String pwd=String.valueOf(text_password.getPassword());
						if(numer.length()!=0 && pwd.length()!=0){
							
						if(Checkuser==0){//USER
							if(UserServise.UserCheck(numer, pwd)){
								new Library();
								dispose();
						}else
							JOptionPane.showMessageDialog(null, "密码或账户名错误", "提示", JOptionPane.ERROR_MESSAGE); 

							}
						else if(Checkuser==1){//ADMIN
							if(AdminServise.AdminCheck(numer, pwd)){
								new ManagementWindow();
								dispose();
							}else
								JOptionPane.showMessageDialog(null, "密码或账户名错误", "提示", JOptionPane.ERROR_MESSAGE); 

						}
					}else
						JOptionPane.showMessageDialog(null, "请输入完整", "提示", JOptionPane.ERROR_MESSAGE); 
		     		}
		     	}
		     });	     	 
		
		JLabel label = new JLabel("账号:");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(235, 358, 72, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密码:");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 15));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(235, 397, 72, 18);
		getContentPane().add(label_1);
	}
	public void setImg(){
		background = new ImageIcon("images/login.png");// 背景图片
		  JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		  // 把标签的大小位
		  // 置设置为图片刚好填充整个面板
		  label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		  // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		  imagePanel = (JPanel) this.getContentPane();
		  imagePanel.setOpaque(false);
		  // 内容窗格默认的布局管理器为BorderLayout  new FlowLayout()
		  imagePanel.setLayout(null);
		  this.getLayeredPane().setLayout(null);
		  // 把背景图片添加到分层窗格的最底层作为背景
		  this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}
	
	public void addRdbtn(){
		/******单选按钮组******/
		 JRadioButton rdbtnUser = new JRadioButton("User",true);
		 rdbtnUser.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		 rdbtnUser.setForeground(Color.WHITE);
			rdbtnUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//点击事件处理机制
					Checkuser=0;
				}
			});
			
			rdbtnUser.setBounds(63, 480, 61, 27);	
			imagePanel.add(rdbtnUser);
			
			JRadioButton rdbtnAdmin = new JRadioButton("Admin");
			rdbtnAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			rdbtnAdmin.setForeground(Color.WHITE);
			rdbtnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Checkuser=1;
					
				}
			});
			rdbtnAdmin.setBounds(190, 480, 73, 27);
			imagePanel.add(rdbtnAdmin);
			ButtonGroup grout=new ButtonGroup();//创建一个组 把两个对象合并为组
			grout.add(rdbtnAdmin);
			grout.add(rdbtnUser);
			rdbtnAdmin.setContentAreaFilled(false);//设置组件透明
			rdbtnUser.setContentAreaFilled(false);			
	}	
}
