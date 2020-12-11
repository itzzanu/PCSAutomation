package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.JDBCConnection;
import dao.IEmployeeDao;
import model.Employee;
import view.RegistrationFrame;

public class EmployeeDaoImpl implements IEmployeeDao {

	Connection conn=null;
	public EmployeeDaoImpl() throws ClassNotFoundException, SQLException{
		//Opened connection
		conn=JDBCConnection.getDBConnection();
	}
	public Employee checkLogin(String userId, String password ){
		Employee emp=new Employee();
		try{
			PreparedStatement pst=conn.prepareStatement("select * from Employee where userId=? and password=?");
			pst.setString(1, userId);
			pst.setString(2, password);
			ResultSet rst=pst.executeQuery();
			if(rst!=null) {
				if(rst.next()) {
					emp.setEmployeeID(rst.getInt(1));
					emp.setFirstName(rst.getString(2));
					emp.setLastName(rst.getString(3));
					emp.setUserID(rst.getString(4));
					emp.setPassword(rst.getString(5));
					emp.setGender(rst.getString(6));
					emp.setRole(rst.getString(7));
					emp.setActive(rst.getString(8));
				}
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		if(userId==emp.getUserID()&&password==emp.getPassword())
		{
			return emp;
		}
		return emp;
	}
	public Employee checkUser(String userId){
		Employee emp=new Employee();
		try{
			PreparedStatement pst=conn.prepareStatement("select * from Employee where userId=?");
			pst.setString(1, userId);
			ResultSet rst=pst.executeQuery();
			if(rst!=null) {
				if(rst.next()) {
					emp.setEmployeeID(rst.getInt(1));
					emp.setFirstName(rst.getString(2));
					emp.setLastName(rst.getString(3));
					emp.setUserID(rst.getString(4));
					emp.setPassword(rst.getString(5));
					emp.setGender(rst.getString(6));
					emp.setRole(rst.getString(7));
					emp.setActive(rst.getString(8));
				}
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		if(userId==emp.getUserID())
		{
			return emp;
		}
		return emp;
	}
	@Override
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> allEmpList=new ArrayList<Employee>(); //1
		try{
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery("select * from Employee");
			if(rst!=null) {
				Employee emp=null;
				while(rst.next()) {
					emp=new Employee();
					emp.setEmployeeID(rst.getInt(1));
					emp.setFirstName(rst.getString(2));
					emp.setLastName(rst.getString(3));
					emp.setUserID(rst.getString(4));
					emp.setPassword(rst.getString(5));
					emp.setGender(rst.getString(6));
					emp.setRole(rst.getString(7));
					emp.setActive(rst.getString(8));
					allEmpList.add(emp); //2
				}
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return allEmpList;
	}

	@Override
	public void addEmployee(Employee emp){
		try {
			//creating PreparedStatement object by passing query string
			PreparedStatement pst=conn.prepareStatement("insert into Employee(FirstName, LastName,UserId,Password,Gender,Role,Active) values(?,?,?,?,?,?,?)");
			pst.setString(1, emp.getFirstName());
			pst.setString(2, emp.getLastName());
			pst.setString(3, emp.getUserID());
			pst.setString(4, emp.getPassword());
			pst.setString(5, emp.getGender());
			pst.setString(6, emp.getRole());
			pst.setString(7, emp.getActive());
			int i=pst.executeUpdate();
			
			if(i==1){
				System.out.println("1 record inserted...");
			}
			else {
				System.out.println("insertion failed...");
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp=null;
		try{
			PreparedStatement pst=conn.prepareStatement("select * from Employee where EmployeeId=?");
			pst.setInt(1, id);
			ResultSet rst=pst.executeQuery();
			if(rst!=null) {
				if(rst.next()) {
					emp=new Employee();
					emp.setEmployeeID(rst.getInt(1));
					emp.setFirstName(rst.getString(2));
					emp.setLastName(rst.getString(3));
					emp.setUserID(rst.getString(4));
					emp.setPassword(rst.getString(5));
					emp.setGender(rst.getString(6));
					emp.setRole(rst.getString(7));
					emp.setActive(rst.getString(8));
				}
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return emp;
	}

	@Override
	public void updateEmployee(Employee emp) {
		try {
			//creating PreparedStatement object by passing query string
			PreparedStatement pst=conn.prepareStatement("update Employee set Password=? where empId=?");
			pst.setString(1, emp.getPassword());
			pst.setInt(2, emp.getEmployeeID());
			int i=pst.executeUpdate();
			if(i==1){
				System.out.println("1 record updated...");
			}
			else {
				System.out.println("updation failed...");
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
	public void activateEmployee(int id) {
		try {
			//creating PreparedStatement object by passing query string
			PreparedStatement pst=conn.prepareStatement("update Employee set Active=? where empId=?");
			pst.setString(1, "Active");
			pst.setInt(2, id);
			int i=pst.executeUpdate();
			/*if(i==1){
				System.out.println("Employee deactivated...");
			}
			else {
				System.out.println("updation failed...");
			}*/
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
		
	}
	@Override
	public void deactivateEmployee(int id) {
		try {
			//creating PreparedStatement object by passing query string
			PreparedStatement pst=conn.prepareStatement("update Employee set Active=? where empId=?");
			pst.setString(1, "Deactive");
			pst.setInt(2, id);
			int i=pst.executeUpdate();
			/*if(i==1){
				System.out.println("Employee deactivated...");
			}
			else {
				System.out.println("updation failed...");
			}*/
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
		
	}

	@Override
	public void deleteEmployee(int id) {
		try {
			//creating PreparedStatement object by passing query string
			PreparedStatement pst=conn.prepareStatement("delete from Employee where empId=?");
			pst.setInt(1, id);
			int i=pst.executeUpdate();
			if(i==1){
				System.out.println("Employee deleted...");
			}
			else {
				System.out.println("deletion failed...");
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
		
	}
	
}