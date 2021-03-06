package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.JobController;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeactivateJob {
	private JFrame frame;
	private JTextField textField;
	private JobController jobController;
	
	public DeactivateJob()throws ClassNotFoundException, SQLException {
		jobController=new JobController();
		frame = new JFrame("DEACTIVATE JOB");
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblJobId = new JLabel("JOB ID");
		lblJobId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblJobId.setBounds(64, 54, 107, 35);
		frame.getContentPane().add(lblJobId);
		
		textField = new JTextField();
		textField.setBounds(210, 60, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSubmit.setBounds(150, 120, 100, 25);
		frame.getContentPane().add(btnSubmit);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		btnSubmit.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				DeactivateJob obj = null;
				try {
					obj = new DeactivateJob();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int id=0;
				id=Integer.parseInt(textField.getText());
				JOptionPane.showMessageDialog(frame,"Job Deactivated");
				obj.setDeactivate(id);
			}
			});
	}
	public void setDeactivate(int id)
	{
		jobController.deactivateJob();
	}
}