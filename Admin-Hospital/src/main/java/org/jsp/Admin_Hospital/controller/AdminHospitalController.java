package org.jsp.Admin_Hospital.controller;
import java.util.List;
import java.util.Scanner;

import org.jsp.Admin_Hospital.AdminHospitalConfig;
import org.jsp.Admin_Hospital.dao.AdminDao;
import org.jsp.Admin_Hospital.dao.HospitalDao;
import org.jsp.Admin_Hospital.dto.Admin;
import org.jsp.Admin_Hospital.dto.Hospital;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class AdminHospitalController {

	private static AdminDao adao;
	private static HospitalDao hdao;
	private static Scanner sc = new Scanner(System.in);
	
	static
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(AdminHospitalConfig.class);
		adao = context.getBean(AdminDao.class);
		hdao = context.getBean(HospitalDao.class);
	}
	public static void main(String[] args) {
        boolean flag = true;
        while(flag)
        {
        	display();
        	switch(sc.nextInt())
        	{
        	    case 1:
        	    	saveAdmin();
        	    	break;
        	    case 2:
        	    	updateAdmin();
        	    	break;
        	    case 3:
        	    	verifyByPhonePassword();

        	    	break;
        	    case 4:
        	    	verifyByEmailPassword();

        	    	break;
        	    case 5:
        	    	addHospital();
        	    	break;
        	    case 6:
        	    	updateHospital();
        	    	break;
        	    case 7:
        	    	findHospitalByAdminID();
        	    	break;
        	    case 8:
        	    	findHospitalByname();
        	    	break;
        	    default:
        	    	flag=false;
        	    	break;
        	}
        }
	} // main-method
	public static void saveAdmin() // 1
	{
		Admin a = new Admin();
		System.out.println("Enter Admin Name, Phone, Email, Password");
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setPassword(sc.next());
		a=adao.save(a);
		System.out.println("Admin saved with ID: "+a.getId());
	}
	public static void updateAdmin() //2
	{
		Admin a = new Admin();
		System.out.println("Enter Admin Id to Update Details...");
		a.setId(sc.nextInt());
		System.out.println("Enter Admin Name, Phone, Email, Password");
		a.setName(sc.next());
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a.setPassword(sc.next());
		a=adao.update(a);
		System.out.println("Admin updated with ID: "+a.getId());
	}
	public static void addHospital() // 3
	{
		System.out.println("First Enter Admin ID");
		int admin_id = sc.nextInt();
		Hospital h = new Hospital();
		System.out.println("Enter Hospital Name, GST, Founder, Year_of_establish to add a Hospital");
		h.setName(sc.next());
		h.setGst(sc.nextDouble());
		h.setFounder(sc.next());
		h.setYear_Of_Establishment(sc.nextInt());
		h=hdao.addHospital(h, admin_id);
		System.out.println("Hospital Added with ID: "+h.getId());
	}
	public static void updateHospital() //4
	{
		System.out.println("First Enter Admin ID");
		int admin_id = sc.nextInt();
		Hospital h = new Hospital();
		System.out.println("Enter Hospital ID to update the Details");
		h.setId(sc.nextInt());
		System.out.println("Enter Hospital Name, GST, Founder, Year_of_establish to add a Hospital");
		h.setName(sc.next());
		h.setGst(sc.nextDouble());
		h.setFounder(sc.next());
		h.setYear_Of_Establishment(sc.nextInt());
		h=hdao.updateHospital(h, admin_id);
		System.out.println("Hospital Added with ID: "+h.getId());
	}
	
	
	
	public static void findHospitalByname() { //8
		System.out.println("enter Hospital name");
		String name = sc.next();
		List<Hospital> list = hdao.findByName(name);
		if(list != null) {
			for(Hospital h : list) {
				System.out.println("Hospital name: " + h.getName());
				System.out.println("Hospital GST: " + h.getGst());
				System.out.println("Hospital Founder: " + h.getFounder());
				System.out.println("Product description" + h.getYear_Of_Establishment());
				System.out.println();
			}
		} else {
			System.out.println("Name Not Found");
		}
	}
	
	
	
	public static void verifyByPhonePassword() //5
	{
		System.out.println("Enter Admin Mobile");
		long phone = sc.nextLong();
		System.out.println("Enter Password!!");
		String password = sc.next();
		Admin a = adao.verify(phone, password);
		if(a!=null)
		{
			System.out.println("Admin id: "+a.getId());
			System.out.println("Admin Name: "+a.getName());
			System.out.println("Admin id: "+a.getEmail());
            System.out.println("Admin Phone: "+a.getPhone());
		}
		else
		{
			System.out.println("Invalid Phone / Password...");
		}
	}
	public static void verifyByEmailPassword() //6
	{
		System.out.println("Enter Admin eMAIL id");
		String email = sc.next();
		System.out.println("Enter Password!!");
		String password = sc.next();
		Admin a = adao.verify(email, password);
		if(a!=null)
		{
			System.out.println("Admin id: "+a.getId());
			System.out.println("Admin Name: "+a.getName());
			System.out.println("Admin id: "+a.getEmail());
            System.out.println("Admin Phone: "+a.getPhone());
		}
		else
		{
			System.out.println("Invalid eMAIL id / Password...");
		}
	}
	
	public static void findHospitalByAdminID()
	{
		System.out.println("enter Admin id");
		int id = sc.nextInt();
		List<Hospital> list = adao.findHospitalByAdminId(id);
		if(list != null) {
			for(Hospital h : list) {
				System.out.println("Hospital name: " + h.getName());
				System.out.println("Hospital GST: " + h.getGst());
				System.out.println("Hospital Founder: " + h.getFounder());
				System.out.println("Product description" + h.getYear_Of_Establishment());
				System.out.println();
			}
		} else {
			System.out.println("Invalid Admin id");
		}
	}

	
	public static void display()
	{
		System.out.println("Enter Your Choice...");
		System.out.println("1. Save Admin");
		System.out.println("2. Update Admin");
		System.out.println("3. Verify Admin By Phone & Password");
		System.out.println("4. Verify Admin By Email & Password");
        System.out.println("5. Add Hospital");
        System.out.println("6. Update Hospital");
        System.out.println("7. Find Hospital By Admin ID");
        System.out.println("8. Find Hospital By Name");
	}

}
