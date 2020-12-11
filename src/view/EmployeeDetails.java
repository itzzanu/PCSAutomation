package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import model.Employee;


import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class EmployeeDetails extends JFrame
{
	private EmployeeController empController;
	private JFrame frame;
	private JTable table;

	public EmployeeDetails()throws ClassNotFoundException, SQLException {
		String[] columnNames = {"Employee ID","Name","Gender","Designation","Status"};
		empController=new EmployeeController();
		frame = new JFrame("Employee Details");
		frame.setResizable(true);
		frame.setSize(1000, 800);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		DefaultTableModel model = new DefaultTableModel();
		
        model.setColumnIdentifiers(columnNames);
        
		table = new JTable();
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setLocation(12, 13);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(model);
		table.setSize(970, 727);
		frame.getContentPane().add(table,BorderLayout.CENTER);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        
       
		ArrayList<Employee> data= empController.getAllEmployees();
		int s1;
		String s2,s3,s4,s5,s6,s7;
		model.addRow(new Object[] {"Employee ID","Name","Gender","Designation","Status"});
		for(Employee emp:data)
		{
			s1=emp.getEmployeeID();
			s2=emp.getFirstName();
			s3=emp.getLastName();
			s4=emp.getGender();
			s5=emp.getRole();
			s6=emp.getActive();
			s7=s2+" "+s3;
			model.addRow(new Object[]{s1,s7,s4,s5,s6});
		}
	}
}