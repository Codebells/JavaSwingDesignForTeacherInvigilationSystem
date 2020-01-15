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
 *  ����Ա��
 * 
 * @author Codebells
 *
 */
public class ManagementWindow extends JFrame {
	private static final long serialVersionUID = 6918457427019512011L;
	private JPanel imagePanel;
	private ImageIcon background;
	private JPanel pane = null; // ��Ҫ��JPanel
	private CardLayout card = null; // CardLayout���ֹ�����
	private JPanel p_1 = null, p_2 = null, p_3 = null, p_4 = null, p_5 = null, p_6 = null, p_7 = null, p_8 = null,
			p_9 = null, p_10 = null, P_main = null;
	private String CheckSex = "��";// ע����ͨ�û�ʹ�õ��Ա��жϵĹ��ܱ�ʶ��
	private Vector<Serializable> rowData, columnNames; // rowData������������� ,
														// columnNames�������
	private int Mark;// ��Ϊ���ѡ���ܵı��λ
	private String data;// ��Ϊ���鿴������ ���ݵĲ���
	private ResultSet rstable = null;// ��Ϊ���Ĳ鿴���ݿ���õķ���
	private JPanel P_table = null;
	private Icon ico = new ImageIcon("images/button.png");// ���ð�ť�ı�����ɫ
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/*
	 * public static void main(String[] args) {//���������õ�main new
	 * ManagementWindow(); }
	 */
	/**
	 * ���췽��
	 */
	public ManagementWindow() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				DataBase.Colse();// �ر����ݿ�����
				System.exit(0);
			}
		});
		setTitle("\u6559\u52A1\u5728\u7EBF");
		setBounds(100, 100, 1205, 830);
		setLocationRelativeTo(null);// �ô��ھ�����ʾ
		setResizable(false);// ���ô�����󻯰�ť
		setImg();// ����ͼƬ
		CardPanle();// ��Ƭ���ּ���
		Label(); // ��ǩ�Ϸ����
		ButtonSet();// ���ع��ܰ�ť
		P_main.setBackground(Color.gray);
		setVisible(true);
	}

	/**
	 * ע�ῨƬ���ֿ��
	 */
	public void CardPanle() {
		card = new CardLayout(5, 5);
		pane = new JPanel(card); // JPanel�Ĳ��ֹ��������ó�CardLayout
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

		ClickEvent_Adminadd();// ��ӹ���Ա����
		ClickEvent_AdminDelect();// ɾ������Ա����
		ClickEvent_UserAdd();// ����û�����
		ClickEvent_UserUpdata();// �����û�����
		ClickEvent_UserDelect();// ɾ���û�����
		ClickEvent_UserQuery();// �����û�����
		ClickEvent_BookAddAndUpdata();// ��ӻ���¿��Դ���
		ClickEvent_BookBorrowQueryByID();// ���ҽ�ʦ�࿼����Щ���ԵĴ���
		ClickEvent_QueryBooks();// ��ѯ�࿼���Դ���
		ClickEvent_DelectBook();// ɾ���࿼���Դ���
		SetP_Main_Img();

		pane.add(P_main, "P_main"); // �������ǩ��ӵ�������
		pane.add(p_1, "p1");
		pane.add(p_2, "p2");
		pane.add(p_3, "p3");
		pane.add(p_4, "p4");
		pane.add(p_5, "p5");
		pane.add(p_6, "p6");
		pane.add(p_7, "p7");

		textField_2 = new JTextField();
		textField_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(191, 309, 208, 35);
		p_7.add(textField_2);

		JLabel label = new JLabel("\u6559\u5BA4:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label.setBounds(50, 309, 127, 35);
		p_7.add(label);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(690, 310, 208, 35);
		p_7.add(textField_3);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u53F7:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_1.setBounds(538, 309, 127, 35);
		p_7.add(label_1);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(191, 391, 208, 35);
		p_7.add(textField_4);

		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_2.setBounds(50, 391, 127, 35);
		p_7.add(label_2);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(690, 392, 208, 35);
		p_7.add(textField_5);

		JLabel label_3 = new JLabel("\u4E3B\u8003:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_3.setBounds(538, 391, 127, 35);
		p_7.add(label_3);

		JLabel label_4 = new JLabel("\u526F\u8003:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_4.setBounds(50, 463, 127, 35);
		p_7.add(label_4);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(191, 464, 208, 35);
		p_7.add(textField_6);
		pane.add(p_8, "p8");
		pane.add(p_9, "p9");
		pane.add(p_10, "p10");
	}

	/**
	 * ���ܰ�ť���
	 */
	public void ButtonSet() {
		JButton BtnAdmin_add = new JButton("\u65B0\u589E\u7BA1\u7406\u5458", ico);
		BtnAdmin_add.setForeground(Color.WHITE);
		BtnAdmin_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnAdmin_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ��ӹ���Ա�ĵ���¼�
				card.show(pane, "p1");
			}
		});
		BtnAdmin_add.setBounds(8, 164, 190, 27);
		getContentPane().add(BtnAdmin_add);

		JButton BtnAdmin_delect = new JButton("\u5220\u9664\u7BA1\u7406\u5458", ico);// ��϶+10
		BtnAdmin_delect.setForeground(Color.WHITE);
		BtnAdmin_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnAdmin_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ɾ�����
				card.show(pane, "p2");
			}
		});
		BtnAdmin_delect.setBounds(8, 201, 190, 27);
		getContentPane().add(BtnAdmin_delect);

		JButton BtnUser_add = new JButton("\u6DFB\u52A0\u6559\u5E08", ico);
		BtnUser_add.setForeground(Color.WHITE);
		BtnUser_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_add.addActionListener(new ActionListener() { // ���һ����ͨ�û�
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
			public void actionPerformed(ActionEvent e) { // �����û���Ϣ
				card.show(pane, "p4");
			}
		});
		BtnUser_updata.setBounds(8, 275, 190, 27);
		getContentPane().add(BtnUser_updata);

		JButton BtnUser_delect = new JButton("\u5220\u9664\u6559\u5E08", ico);
		BtnUser_delect.setForeground(Color.WHITE);
		BtnUser_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ɾ���û�
				card.show(pane, "p5");
			}
		});
		BtnUser_delect.setBounds(8, 312, 190, 27);
		getContentPane().add(BtnUser_delect);

		JButton BtnUser_query = new JButton("\u67E5\u627E\u6559\u5E08", ico);
		BtnUser_query.setForeground(Color.WHITE);
		BtnUser_query.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnUser_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ������ͨ�û�����Ϣ
				card.show(pane, "p6");
			}
		});
		BtnUser_query.setBounds(8, 349, 190, 27);
		getContentPane().add(BtnUser_query);

		JButton BtnBook_add = new JButton("\u6DFB\u52A0\u6216\u66F4\u65B0\u8003\u8BD5", ico);
		BtnBook_add.setForeground(Color.WHITE);
		BtnBook_add.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ͼ��
				card.show(pane, "p7");
			}
		});
		BtnBook_add.setBounds(8, 406, 190, 27);
		getContentPane().add(BtnBook_add);

		JButton BtnBook_UserFindsBook = new JButton("\u67E5\u627E\u6559\u5E08\u76D1\u8003\u4FE1\u606F", ico);
		BtnBook_UserFindsBook.setForeground(Color.WHITE);
		BtnBook_UserFindsBook.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_UserFindsBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // �鿴�û��ж��ٳ��࿼
				card.show(pane, "p8");

			}
		});
		BtnBook_UserFindsBook.setBounds(8, 443, 190, 27);
		getContentPane().add(BtnBook_UserFindsBook);

		JButton BtnBook_delect = new JButton("\u5220\u9664\u8003\u8BD5", ico);
		BtnBook_delect.setForeground(Color.WHITE);
		BtnBook_delect.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ɾ������
				card.show(pane, "p9");
			}
		});
		BtnBook_delect.setBounds(8, 480, 190, 27);
		getContentPane().add(BtnBook_delect);

		JButton BtnBook_query = new JButton("\u67E5\u8BE2\u8003\u8BD5", ico);
		BtnBook_query.setForeground(Color.WHITE);
		BtnBook_query.setHorizontalTextPosition(SwingConstants.CENTER);
		BtnBook_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ��ѯ����
				card.show(pane, "p10");
			}
		});
		BtnBook_query.setBounds(8, 517, 190, 27);
		getContentPane().add(BtnBook_query);

	}

	/**
	 * �������������
	 */
	public void Label() {
		JLabel lblNewLabel = new JLabel("��ɫ������Ա");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(1100, 84, 100, 18);
		imagePanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("��ӭ��:" + AdminServise.getID());
		lblNewLabel_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(977, 84, 100, 18);
		getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("�޸�����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // �޸�����
				String pwd = JOptionPane.showInputDialog("����������");
				if (pwd != null)
					if (pwd.length() != 0) {
						if (pwd.matches("[a-zA-Z0-9]{6,15}")) {
							if (AdminServise.UpdataAdmin(AdminServise.getID(), pwd))
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "����δ֪���쳣", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "������6-15λ���ֺ���ĸ��ɵ�����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(235, 42, 90, 27);
		getContentPane().add(btnNewButton);

		JButton btnNewButton1 = new JButton("ע��");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ע������Ӧ�¼�
				int i = JOptionPane.showConfirmDialog(null, "ȷ��ע����?", "��ʾ", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					DataBase.Colse(); // �ر����ݿ�����
					new LoginWindow();
					dispose();
				}
			}
		});
		btnNewButton1.setForeground(Color.BLACK);
		btnNewButton1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		btnNewButton1.setContentAreaFilled(false);
		btnNewButton1.setBounds(218, 80, 90, 27);
		getContentPane().add(btnNewButton1);

		JButton button_Main = new JButton("��ҳ");
		button_Main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pane, "P_main");
			}
		});
		button_Main.setForeground(Color.BLACK);
		button_Main.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		button_Main.setContentAreaFilled(false);
		button_Main.setBounds(311, 80, 90, 27);
		getContentPane().add(button_Main);
	}

	/**
	 * ���ر���ͼƬ
	 */
	public void setImg() {
		background = new ImageIcon("images/management.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ
		// ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout new FlowLayout()
		imagePanel.setLayout(null);

		this.getLayeredPane().setLayout(null);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ��ӹ���Ա
	 */
	public void ClickEvent_Adminadd() {
		JTextField text_id = new JTextField();
		text_id.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		text_id.setForeground(Color.RED);
		text_id.setBounds(410, 206, 182, 30);
		p_1.add(text_id);
		text_id.setColumns(10);

		JTextField text_pwd = new JTextField();
		text_pwd.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		text_pwd.setForeground(Color.RED);
		text_pwd.setBounds(410, 303, 182, 30);
		p_1.add(text_pwd);
		text_pwd.setColumns(10);

		JLabel lbl_ID = new JLabel("�˺ţ�");
		lbl_ID.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lbl_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ID.setBounds(230, 212, 72, 18);
		p_1.add(lbl_ID);

		JLabel lbl_pwd = new JLabel("���룺");
		lbl_pwd.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lbl_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pwd.setBounds(230, 309, 72, 18);
		p_1.add(lbl_pwd);

		JLabel lblNewLabel_3 = new JLabel("��������ĸ��������ɵ�6-15λ��������˺�");
		lblNewLabel_3.setBounds(230, 378, 332, 42);
		lblNewLabel_3.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		p_1.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("ȷ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ȷ���¼�
				String id = text_id.getText();
				String pwd = text_pwd.getText();
				boolean isid = id.matches("[a-zA-Z0-9]{1,15}");
				boolean ispwd = pwd.matches("[a-zA-Z0-9]{6,15}");
				if (id.length() != 0 && pwd.length() != 0) {

					if (isid == true && ispwd == true) {
						if (AdminServise.addNewAdmin(id, pwd))
							JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "�˻����ѱ�ע��", "��ʾ", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "������6-15λ��ĸ��������ɵ��˺ź�����", "��ʾ", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		btnNewButton_1.setBounds(355, 503, 162, 37);
		p_1.add(btnNewButton_1);
	}

	/**
	 * ɾ������Ա
	 */
	public void ClickEvent_AdminDelect() {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField.setBounds(361, 261, 200, 37);
		p_2.add(textField);
		textField.setColumns(10);

		JLabel lbl_delect = new JLabel("����Ҫɾ�����˺ţ�");
		lbl_delect.setFont(new Font("΢���ź�", Font.BOLD, 19));
		lbl_delect.setForeground(Color.BLACK);
		lbl_delect.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_delect.setBounds(135, 261, 183, 37);
		p_2.add(lbl_delect);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setFont(new Font("΢���ź� Light", Font.PLAIN, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(361, 541, 200, 37);
		p_2.add(lblNewLabel_2);

		JButton btnNewButton_2 = new JButton("ȷ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				if (id.length() != 0) {
					if (AdminServise.deleteAdmin(id))
						lblNewLabel_2.setText("�ɹ�");
					else
						lblNewLabel_2.setText("ʧ��");
				} else
					lblNewLabel_2.setText("����������");
			}
		});
		btnNewButton_2.setFont(new Font("΢���ź�", Font.BOLD, 19));
		btnNewButton_2.setBounds(361, 435, 176, 37);
		p_2.add(btnNewButton_2);
	}

	/**
	 * ����û�
	 */
	public void ClickEvent_UserAdd() {
		/****** ��ѡ��ť�� ******/
		CheckSex = "man";
		JRadioButton rdbtnUser = new JRadioButton("��", true);
		rdbtnUser.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.BLACK);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ����¼��������
				CheckSex = "man";
			}
		});

		rdbtnUser.setBounds(157, 407, 61, 27);
		p_3.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("Ů");
		rdbtnAdmin.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.BLACK);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "woman";
			}
		});
		rdbtnAdmin.setBounds(284, 407, 73, 27);
		p_3.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// ����һ���� ����������ϲ�Ϊ��
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// �������͸��
		rdbtnUser.setContentAreaFilled(false);
		// *******end*******//

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
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

		JLabel label_usernumer = new JLabel("�˺�:");
		label_usernumer.setHorizontalAlignment(SwingConstants.CENTER);
		label_usernumer.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_usernumer.setBounds(486, 198, 127, 35);
		p_3.add(label_usernumer);

		JLabel label_userpwd = new JLabel("����:");
		label_userpwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_userpwd.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_userpwd.setBounds(486, 310, 127, 35);
		p_3.add(label_userpwd);

		JLabel label_userid = new JLabel("\u804C\u5DE5\u53F7:");
		label_userid.setHorizontalAlignment(SwingConstants.CENTER);
		label_userid.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_userid.setBounds(8, 196, 127, 35);
		p_3.add(label_userid);

		JLabel label_username = new JLabel("����:");
		label_username.setHorizontalAlignment(SwingConstants.CENTER);
		label_username.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_username.setBounds(8, 310, 127, 35);
		p_3.add(label_username);

		JLabel label_userSex = new JLabel("�Ա�:");
		label_userSex.setHorizontalAlignment(SwingConstants.CENTER);
		label_userSex.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_userSex.setBounds(8, 407, 127, 35);
		p_3.add(label_userSex);

		JLabel lblNewLabel_4 = new JLabel("����9λ������ɵ�ѧ��,���ּ���ĸ��ɵ�6-15λ���˺�����");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(34, 503, 421, 35);
		p_3.add(lblNewLabel_4);

		JButton btn_userAddOK = new JButton("ע��");
		btn_userAddOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ע������Ӧ�¼�����
				String id = textField_id.getText();
				String name = textField_name.getText();
				String numer = textField_numer.getText();
				String pwd = textField_pwd.getText();
				String sex = CheckSex;
				if (id.length() != 0 && name.length() != 0 && numer.length() != 0 && pwd.length() != 0) {
					if (pwd.matches("[a-zA-Z0-9]{6,15}") == true) {
						if (UserServise.addNewUser(name, id, sex, numer, pwd))
							JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "������9λ������ɵ�ְ����,���ּ���ĸ��ɵ�6-15λ���˺�����", "��ʾ",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btn_userAddOK.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		btn_userAddOK.setBounds(655, 435, 183, 35);
		p_3.add(btn_userAddOK);
	}

	/**
	 * �����û�
	 */
	public void ClickEvent_UserUpdata() {
		JRadioButton rdbtnUser = new JRadioButton("��", true);
		rdbtnUser.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnUser.setForeground(Color.BLACK);
		CheckSex = "man";
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ����¼��������
				CheckSex = "man";
			}
		});

		rdbtnUser.setBounds(203, 449, 61, 27);
		p_4.add(rdbtnUser);

		JRadioButton rdbtnAdmin = new JRadioButton("Ů");
		rdbtnAdmin.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnAdmin.setForeground(Color.BLACK);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "woman";
			}
		});
		rdbtnAdmin.setBounds(330, 449, 73, 27);
		p_4.add(rdbtnAdmin);
		ButtonGroup grout = new ButtonGroup();// ����һ���� ����������ϲ�Ϊ��
		grout.add(rdbtnAdmin);
		grout.add(rdbtnUser);
		rdbtnAdmin.setContentAreaFilled(false);// �������͸��
		rdbtnUser.setContentAreaFilled(false);
		// ******end**************//
		JTextField textField_lodID = new JTextField();
		textField_lodID.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_lodID.setColumns(10);
		textField_lodID.setBounds(292, 231, 208, 35);
		p_4.add(textField_lodID);

		JLabel label_id = new JLabel("\u8981\u4FEE\u6539\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_id.setBounds(79, 229, 190, 35);
		p_4.add(label_id);

		JTextField textField_name = new JTextField();
		textField_name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_name.setColumns(10);
		textField_name.setBounds(195, 344, 208, 35);
		p_4.add(textField_name);

		JLabel label = new JLabel("����:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label.setBounds(54, 344, 127, 35);
		p_4.add(label);

		JLabel label_1 = new JLabel("�Ա�:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_1.setBounds(54, 441, 127, 35);
		p_4.add(label_1);

		JTextField textField_pwd = new JTextField();
		textField_pwd.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textField_pwd.setColumns(10);
		textField_pwd.setBounds(641, 344, 208, 35);
		p_4.add(textField_pwd);

		JLabel lblPwd = new JLabel("����:");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		lblPwd.setBounds(500, 344, 127, 35);
		p_4.add(lblPwd);

		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ȷ�ϵ���Ӧ�¼�����
				boolean nameis = false, pwdis = false, sexis = false;
				int i = JOptionPane.showConfirmDialog(null, "ȷ��������?", "��ʾ", JOptionPane.YES_NO_OPTION);
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
							JOptionPane.showMessageDialog(null, "������6-15λ���ֺ���ĸ��ɵ�����", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
					if (UserServise.updataUserSex(id, sex))
						sexis = true;
					else
						sexis = false;

					if (nameis && pwdis && sexis)
						JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "��ȡIDʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		button.setBounds(701, 469, 183, 35);
		p_4.add(button);
	}

	/**
	 * ɾ���û�
	 */
	public void ClickEvent_UserDelect() {
		JLabel label = new JLabel("\u8981\u5220\u9664\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("΢���ź�", Font.BOLD, 19));
		label.setBounds(209, 247, 224, 37);
		p_5.add(label);

		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(476, 247, 200, 37);
		p_5.add(textField_1);

		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ȷ����Ӧ�¼�
				String id = textField_1.getText();
				if (id.length() != 0) {
					if (UserServise.deleteUser(id))
						JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "��ȡIDʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("΢���ź�", Font.BOLD, 19));
		button.setBounds(476, 421, 176, 37);
		p_5.add(button);
	}

	/**
	 * �����û�
	 */
	public void ClickEvent_UserQuery() {
		JLabel label = new JLabel("\u8981\u67E5\u627E\u7684\u6559\u5E08\u804C\u5DE5\u53F7:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("΢���ź�", Font.BOLD, 19));
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
		textQuery.setFont(new Font("΢���ź� Light", Font.PLAIN, 47));
		textQuery.setBounds(77, 123, 842, 516);
		p_6.add(textQuery);

		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ȷ����Ӧ�¼�
				String id = textField_1.getText();
				String query = UserServise.QueryUser(id);
				if (id.length() != 0) {
					textQuery.setText(query);
				} else
					JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("΢���ź�", Font.BOLD, 19));
		button.setBounds(674, 31, 176, 37);
		p_6.add(button);
	}

	/**
	 * ���»���ӿ���
	 */
	public void ClickEvent_BookAddAndUpdata() {
		JRadioButton rdbtnupdata = new JRadioButton("\u66F4\u65B0\u8003\u8BD5");
		rdbtnupdata.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnupdata.setForeground(Color.BLACK);
		rdbtnupdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ����¼��������
				CheckSex = "updata";
			}
		});

		rdbtnupdata.setBounds(220, 60, 105, 27);
		p_7.add(rdbtnupdata);

		JRadioButton rdbtnadd = new JRadioButton("\u6DFB\u52A0\u8003\u8BD5",true);
		rdbtnadd.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		rdbtnadd.setForeground(Color.BLACK);
		rdbtnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckSex = "add";
			}
		});
		rdbtnadd.setBounds(370, 60, 127, 27);
		p_7.add(rdbtnadd);
		ButtonGroup grout = new ButtonGroup();// ����һ���� ����������ϲ�Ϊ��
		grout.add(rdbtnadd);
		grout.add(rdbtnupdata);
		rdbtnupdata.setContentAreaFilled(false);
		// **********end*************//
		JLabel label_select = new JLabel("ѡ����Ҫ�Ĺ���:");
		label_select.setHorizontalAlignment(SwingConstants.CENTER);
		label_select.setFont(new Font("΢���ź� Light", Font.PLAIN, 15));
		label_select.setBounds(67, 55, 127, 35);
		p_7.add(label_select);

		JTextField text_id = new JTextField();
		text_id.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		text_id.setColumns(10);
		text_id.setBounds(191, 152, 208, 35);
		p_7.add(text_id);

		JLabel label_id = new JLabel("\u8003\u8BD5\u6279\u6B21\uFF1A");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_id.setBounds(50, 152, 127, 35);
		p_7.add(label_id);

		JLabel label_name = new JLabel("\u76D1\u8003\u65E5\u671F\uFF1A");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_name.setBounds(538, 154, 127, 35);
		p_7.add(label_name);

		JTextField text_name = new JTextField();
		text_name.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		text_name.setColumns(10);
		text_name.setBounds(690, 152, 208, 35);
		p_7.add(text_name);

		JTextField text_press = new JTextField();
		text_press.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		text_press.setColumns(10);
		text_press.setBounds(690, 228, 208, 35);
		p_7.add(text_press);

		JLabel label_press = new JLabel("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_press.setHorizontalAlignment(SwingConstants.CENTER);
		label_press.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_press.setBounds(538, 227, 127, 35);
		p_7.add(label_press);

		JTextField text_author = new JTextField();
		text_author.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		text_author.setColumns(10);
		text_author.setBounds(191, 227, 208, 35);
		p_7.add(text_author);

		JLabel label_author = new JLabel("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_author.setHorizontalAlignment(SwingConstants.CENTER);
		label_author.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		label_author.setBounds(50, 227, 127, 35);
		p_7.add(label_author);

		JButton button_OK = new JButton("ȷ��");
		button_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ȷ����ť���¼�
				int i = JOptionPane.showConfirmDialog(null, "ȷ��������?", "��ʾ", JOptionPane.YES_NO_OPTION);
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
								JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
								UserServise.stat = 1;
								String mail=UserServise.UserMail(main);
								SentMail sentMail=new SentMail(mail);
								sentMail.Sent();
								String mail1=UserServise.UserMail(fu);
								SentMail sentMail1=new SentMail(mail1);
								sentMail1.Sent();
							} else
								JOptionPane.showMessageDialog(null, "���ʧ�ܿγ̺Ŵ���", "����", JOptionPane.ERROR_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
					if (CheckSex == "updata") {
						if (id.length() != 0) {
							if (BookServise.findBookByON(id)) { // �����⿼�ԵĿγ̺��Ƿ����
								JOptionPane.showMessageDialog(null, "���³ɹ�", "����", JOptionPane.ERROR_MESSAGE);
							} else
								JOptionPane.showMessageDialog(null, "�����ڵĿγ̺�", "����", JOptionPane.ERROR_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null, "������Ҫ����γ̺�", "����", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_OK.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		button_OK.setBounds(697, 496, 183, 35);
		p_7.add(button_OK);
	}

	/**
	 * ���ñ�����
	 */
	public void SetTable() {
		columnNames = new Vector<Serializable>();
		// ��������
		columnNames.add("��������");
		columnNames.add("�࿼����");
		columnNames.add("��ʼʱ��");
		columnNames.add("����ʱ��");
		columnNames.add("����");
		columnNames.add("�γ̺�");
		columnNames.add("�γ�����");
		columnNames.add("����");

		rowData = SetTableData(Mark, data);

		JTable jt = new JTable(rowData, columnNames);
		jt.setForeground(Color.BLUE);
		jt.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setSize(819, 337);
		jsp.setLocation(46, 261);
		P_table.add(jsp);

	}

	/**
	 * ���ñ����ʾ����������
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
	 * ��ѯ��ʦ�࿼��Ϣ
	 */
	public void ClickEvent_BookBorrowQueryByID() {
		JLabel lblid = new JLabel(
				"\u8BF7\u8F93\u5165\u60A8\u8981\u67E5\u8BE2\u6559\u5E08\u7684\u76D1\u8003\u4FE1\u606FID\u53F7:");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setForeground(Color.BLACK);
		lblid.setFont(new Font("΢���ź�", Font.PLAIN, 17));
		lblid.setBounds(27, 56, 289, 37);
		p_8.add(lblid);

		JTextField textField_FindBook = new JTextField();
		textField_FindBook.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_FindBook.setColumns(10);
		textField_FindBook.setBounds(356, 57, 200, 37);
		p_8.add(textField_FindBook);

		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ����¼�
				String id = textField_FindBook.getText();
				if (id.length() != 0) {
					Mark = 1;
					data = id;
					P_table = p_8;
					SetTable();
				} else
					JOptionPane.showMessageDialog(null, "��û������", "��ʾ", JOptionPane.ERROR_MESSAGE);
			}
		});
		button.setFont(new Font("΢���ź�", Font.BOLD, 19));
		button.setBounds(677, 56, 176, 37);
		p_8.add(button);
	}

	/**
	 * ��ѯ����
	 */
	public void ClickEvent_QueryBooks() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-->��ѡ���ѯ����--<", "��������", "�࿼����", "����", "�γ̺�", "�γ�����","������ʦ","������ʦ", "ȫ��" }));
		comboBox.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		comboBox.setBounds(74, 61, 195, 32);
		p_10.add(comboBox);

		JTextField textField_QueryBook = new JTextField();
		textField_QueryBook.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		textField_QueryBook.setBounds(338, 61, 214, 32);
		p_10.add(textField_QueryBook);
		textField_QueryBook.setColumns(10);

		JButton btnNewButton_OK = new JButton("��ѯ");
		btnNewButton_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ����¼�
				String query = textField_QueryBook.getText();
				String type = (String) comboBox.getSelectedItem(); // ��ȡ�鼮����

				if ((query.length() != 0 || type == "ȫ��") && comboBox.getSelectedIndex() != 0) {
					Mark = 2;
					switch (type) {
					case "��������":
						P_table = p_10;
						rstable = BookServise.FindsBookexam_pici(query);
						SetTable();
						break;
					case "�࿼����":
						P_table = p_10;
						rstable = BookServise.FindsBookDate(query);
						SetTable();
						break;
					case "����":
						P_table = p_10;
						rstable = BookServise.FindsBookclassroom(query);
						SetTable();
						break;
					case "�γ̺�":
						P_table = p_10;
						rstable = BookServise.FindsBookID(query);
						SetTable();
						break;
					case "�γ�����":
						P_table = p_10;
						rstable = BookServise.FindsBookcname(query);
						SetTable();
						break;
					case "������ʦ":
						P_table = p_10;
						rstable = BookServise.FindsBookmainteacher(query);
						SetTable();
						break;
					case "������ʦ":
						P_table = p_10;
						rstable = BookServise.FindsBookfu_teacher(query);
						SetTable();
						break;
					case "ȫ��":
						P_table = p_10;
						rstable = BookServise.FindsBookAll();
						SetTable();
						break;
					}

				} else
					JOptionPane.showMessageDialog(null, "��û������", "��ʾ", JOptionPane.ERROR_MESSAGE);

			}
		});
		btnNewButton_OK.setFont(new Font("΢���ź�", Font.PLAIN, 17));
		btnNewButton_OK.setBounds(641, 61, 151, 32);
		p_10.add(btnNewButton_OK);
	}

	/**
	 * ɾ������
	 */
	public void ClickEvent_DelectBook() {
		JLabel label_tishi = new JLabel(
				"\u8BF7\u8F93\u5165\u60A8\u8981\u5220\u9664\u7684\u7684\u8003\u8BD5\u7684\u8BFE\u7A0B\u53F7:");
		label_tishi.setHorizontalAlignment(SwingConstants.CENTER);
		label_tishi.setForeground(Color.BLACK);
		label_tishi.setFont(new Font("΢���ź�", Font.PLAIN, 17));
		label_tishi.setBounds(301, 134, 289, 37);
		p_9.add(label_tishi);

		JTextField textField_Bookid = new JTextField();
		textField_Bookid.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		textField_Bookid.setColumns(10);
		textField_Bookid.setBounds(345, 286, 200, 37);
		p_9.add(textField_Bookid);

		JLabel lbl_Prompt = new JLabel();
		lbl_Prompt.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lbl_Prompt.setForeground(Color.RED);
		lbl_Prompt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Prompt.setBounds(334, 417, 228, 37);
		p_9.add(lbl_Prompt);

		JButton button_Ok = new JButton("ɾ��");
		button_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // ���ɾ������Ӧ
				String id = textField_Bookid.getText();
				if (id.length() != 0) {
					if (BookServise.deleteBook(id))
						lbl_Prompt.setText("ɾ���ɹ�");
					else
						lbl_Prompt.setText("û�ҵ��˿γ̺ţ�ɾ��ʧ��");
				} else
					lbl_Prompt.setText("����������");
			}
		});
		button_Ok.setFont(new Font("΢���ź�", Font.BOLD, 19));
		button_Ok.setBounds(362, 523, 176, 37);
		p_9.add(button_Ok);
	}

	/**
	 * ��������Ƭ�ı���
	 */
	public void SetP_Main_Img() {
		ImageIcon background = new ImageIcon("images/main.png");// ����ͼƬ
		JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		label.setBounds(0, 0, 957, 662);
		P_main.add(label, new Integer(Integer.MIN_VALUE));
	}
}
