package controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import dao.ISkillDao;
import daoImpl.SkillDaoImpl;
import model.Employee;
import model.Skill;

public class SkillController {

	ISkillDao skDao=null;
	public SkillController() throws ClassNotFoundException,SQLException
	{
		skDao=new SkillDaoImpl();
	}
	
	public Skill addSkill(String s1,String s2)  
	{
		Skill sk=new Skill();
		
		sk.setSkillName(s1);
		
		sk.setSkillDescription(s2);
		String skill=s1;
		if(skill.equals("Presentation"))
		{
			sk.setActive("Deactive");
		}
		else
		{
			sk.setActive("Active");
		
		}
		skDao.addSkill(sk);
		return sk;
		
}
	public List<Skill> getAllSkills()
	{
		List<Skill> allSkillList=skDao.getAllSkills();
		for(Skill sk:allSkillList) {
			System.out.println(sk);
		}
		
			return allSkillList;
	}
		public Skill getSkillById(String SkillId )
		{
			int id;
			id=Integer.parseInt(SkillId);
			Skill sk=skDao.getSkillById(id);
				return sk;
		}
		public void updateSkill() {
			try {
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				int id;
				String description, newdescription ;
				System.out.println("Enter SkillId whose record you want to update:");
				id=Integer.parseInt(reader.readLine());
				Skill emp=skDao.getSkillById(id);
				System.out.println("Enter your new Description:");
				description=reader.readLine();
				System.out.println("Re-enter same Description to confirm:");
				newdescription=reader.readLine();
				if(description.equals(newdescription)) {
					emp.setSkillDescription(description);
					skDao.updateSkill(emp);
				}
				else {
					System.out.println("Sorry! you have entered different Description!");
				}
			}
			catch(IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		public void deactivateSkill(int id) throws NumberFormatException, IOException {
			
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
				/*System.out.println("Enter the SkillId whose record you want to deactivate:");
				id=Integer.parseInt(reader.readLine());
				Skill sk=skDao.getSkillById(id);*/
				skDao.deactivateSkill(id);
			
		}
		public void deleteSkill() {
			try {
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				int id;
				System.out.println("Enter the SkillId whose record you want to delete:");
				id=Integer.parseInt(reader.readLine());
				skDao.deleteSkill(id);
			}
			catch(IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
}