/*package Test;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;

import com.Library.Service.BorrowAndReturnService;
import com.Library.Service.UserServise;


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
		columnNames.add("书号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("类型");
		columnNames.add("时间");
		this.rs = rs;
		rowData = gethang();
		jt = new JTable(rowData, columnNames);
		MouseEventButtonListener();
		jt.setRowHeight(20);
		jt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		jt.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	 if(e.getButton()==MouseEvent.BUTTON3 ){//如果是右键点击的话             	
		                	popupMenu1.show(jt,e.getX(),e.getY());	               	                	
		                }
	            }
		});
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.setSize(600, 300);
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
				rowData.add(hang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return rowData;
		}
	}
	
	*//**
	 * 右键菜单的添加 和菜单的监听响应 方法
	 *//*
	public void MouseEventButtonListener(){
		popupMenu1 = new PopupMenu();	//创建菜单对象
		MenuItem menuItem1 = new MenuItem();	//添加子菜单
		menuItem1.setLabel("借来看看");	// 规定名称
		popupMenu1.add(menuItem1);	// 添加到菜单对象中
		getContentPane().add(popupMenu1);	//添加到窗体上
		menuItem1.addActionListener(new ActionListener() { //菜单1的事件监听
			public void actionPerformed(ActionEvent e) {
				int i=jt.getSelectedRow() ;
				String bookid=(String) jt.getValueAt(i,0);	
				String bookname=(String) jt.getValueAt(i,1);	
				int k = JOptionPane.showConfirmDialog(null, "你要借-" + bookname + "-这本书吗", "提示",
						JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					if (BorrowAndReturnService.BookBorrow(bookid, UserServise.getId()))
						JOptionPane.showMessageDialog(null, "借书成功，好好学习天天向上", "提示", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "你来晚了呦，书已经被借了", "错误", JOptionPane.ERROR_MESSAGE);	
				}
			}
			});
	}
}
*/