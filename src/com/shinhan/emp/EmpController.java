package com.shinhan.emp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;
import com.shinhan.movie.DateUtil;

//MVC2모델
//FrontController ->Controller 선택->Service->DAO->DB
//
public class EmpController implements CommonControllerInterface {
	
	static Scanner sc = new Scanner(System.in);
	static EmpService empService = new EmpService();
	
	@Override
	public void execute() {
		
		boolean isStop = false;
		while(!isStop) {
			menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
			case 1->{f_selectAll();}
			case 2->{f_selectById();}
			case 3->{f_selectByDept();}
			case 4->{f_selectByJob();}
			case 5->{f_selectByJobAndDept();}
			case 6->{f_selectByCondition();}
			case 7->{f_deleteByEmpId();}
			case 8->{f_insertEmp();}
			case 9->{f_updateEmp();}
			case 10->{f_sp_call();}
			case 99->{isStop=true;}
			
			}
			
		}
		System.out.println("===Good bye===");
		

	}
	private static void f_sp_call() {
		System.out.println("조회할 직원ID>>");
		int employee_id = sc.nextInt();
		EmpDTO emp = empService.execute_sp(employee_id);
		String message = "해당직원은 존재하지 않습니다.";
		
		if(emp!=null) {
			message = emp.getEmail()+"---"+emp.getSalary()		;
		}
		
		
	}
	private static void f_updateEmp() {
		
		System.out.printf("수정할 ID >>");
	    int employee_id  = sc.nextInt();   
	    EmpDTO exist_emp = empService.selectById(employee_id);
	    if(exist_emp==null) {
	    	EmpView.display("존재하지 않는 직원입니다.");
	    	return;
	    }
	    EmpView.display("====존재하는 직원정보입니다.====");
	    EmpView.display(exist_emp);
		int result =  empService.empUpdate(makeEmp(employee_id));
		EmpView.display(result+"건 수정:");

	}
	private static void f_insertEmp() {
		System.out.println("신규직원 ID >>");
	    int employee_id  = sc.nextInt();   
		
		int result =  empService.empInsert(makeEmp(employee_id));
		EmpView.display(result+"건 입력:");

	}
	static EmpDTO makeEmp(int employee_id) {
		
	    System.out.printf("first_name  >>");
		 String first_name  =sc.next()      ;  
		 
		 System.out.printf("last_name  >>");
		 String last_name    =sc.next()     ; 
		 
		 System.out.printf("email  >>");
		 String email       =sc.next()     ; 
		 
		 System.out.printf("phone_number  >>");
		 String phone_number  =sc.next()    ;  
		 
		 System.out.printf("hdate(yyyy-MM-dd)  >>");
		 String hdate    = sc.next()   ; 
		 Date hire_date=null;
		 
		 if(!hdate.equals("0"))
			 hire_date=DateUtil.convertToSQLDate(DateUtil.convertToDate(hdate));  
		 
	 
		 System.out.printf("job_id(IT_PROG)  >>");
		 String job_id   =sc.next()       ;
		 
	 	 System.out.printf("salary  >>");            
	 	 Double salary    =sc.nextDouble()         ;   
		 System.out.printf("commission_pct (0.2) >>");
		 Double commission_pct  =  sc.nextDouble()   ;    
		 System.out.printf("manager_id  >>");
		 Integer manager_id      = sc.nextInt()  ;        
		 System.out.printf("department_id (100) >>");
		 Integer department_id    = sc.nextInt()   ;   
		 
		 if(first_name.equals("0")) first_name = null;
		 if(last_name.equals("0")) last_name = null;
		 if(email.equals("0")) email = null;
		 if(phone_number.equals("0")) phone_number = null;
		 if(job_id.equals("0")) job_id = null;
		 if(salary==0) salary = null;
		 if(commission_pct==0) commission_pct = null;
		 if(manager_id==0) manager_id = null;
		 if(department_id ==0) department_id  = null;
		 
		 
		
		 
		 EmpDTO emp = EmpDTO.builder()
				 .commission_pct(commission_pct) 
				 .department_id(department_id)
				 .email(email)
				 .employee_id(employee_id)
				 .manager_id(manager_id)
				 .salary(salary)
				 .hire_date(hire_date)
				 .first_name(first_name)
				 .last_name(last_name)
				 .phone_number(phone_number)
				 .job_id(job_id)
				 
				 
				 .build();
		 return emp;
	}
	static EmpDTO makeEmp2(int employee_id) {

		System.out.print("first_name>>");
		String first_name = sc.next();
		System.out.print("last_name>>");
		String last_name = sc.next();
		System.out.print("email>>");
		String email = sc.next();
		System.out.print("phone_number>>");
		String phone_number = sc.next();
		System.out.print("hdate(yyyy-MM-dd)>>");
		String hdate = sc.next();
		Date hire_date = DateUtil.convertToSQLDate(DateUtil.convertToDate(hdate));
		System.out.print("job_id(FK:IT_PROG)>>");
		String job_id = sc.next();
		System.out.print("salary>>");
		double salary = sc.nextDouble();
		System.out.print("commission_pct(0.2)>>");
		double commission_pct = sc.nextDouble();
		System.out.print("manager_id(FK:100)>>");
		int manager_id = sc.nextInt();
		System.out.print("department_id(FK:60,90)>>");
		int department_id = sc.nextInt();
		EmpDTO emp = EmpDTO.builder().commission_pct(commission_pct).department_id(department_id).email(email)
				.employee_id(employee_id).first_name(first_name).hire_date(hire_date).job_id(job_id)
				.last_name(last_name).manager_id(manager_id).phone_number(phone_number).salary(salary).build();
		return emp;
	}
	

	private static void f_deleteByEmpId() {
		System.out.printf("삭제할 직원 ID >>");
		int empid = sc.nextInt();
		int result = empService.empDeleteById(empid);
		EmpView.display(result+"건 삭제");
		
	}
	private static void f_selectByCondition() {
		//in 부서, like 직책, >=급여, >=입사일
		System.out.printf("조회할 부서(10,20,30) >");
		String[] str_deptid = sc.next().split(",");
		
		Integer [] deptArr = Arrays.stream(str_deptid)
				.map(Integer::parseInt).toArray(Integer[]::new);
				
		System.out.printf("조회할 직책 ID>");
		String jobid = sc.next();
		System.out.printf("조회할 salary(이상) >");
		int salary = sc.nextInt();
		System.out.printf("조회할 입사일(yyyy-MM-dd이상) >");
		String hdate = sc.next();
		
		List<EmpDTO> emplist = empService.selectByCondition(deptArr, jobid,salary, hdate);
		EmpView.display(emplist);
		
	}
	
	private static void f_selectByJobAndDept() {
		System.out.println("조회할 직책ID, 부서ID>>");
		String data = sc.next();
		String [] arr = data.split(",");
		String jobid = sc.next();
		int deptid = Integer.parseInt(arr[1]);
		List<EmpDTO> emplist = empService.selectByJobAndDept(jobid, deptid);
		EmpView.display(emplist);
	}
	
	private static void f_selectByJob() {
		System.out.printf("조회할 직책ID > ");
		String job = sc.next();
		List<EmpDTO> emplist = empService.selectByJob(job);
		EmpView.display(emplist);
	}
	
	private static void f_selectByDept() {
		System.out.printf("조회할 부서ID > ");
		int deptid = sc.nextInt();
		List<EmpDTO> emplist = empService.selectByDept(deptid);
		EmpView.display(emplist);
	}
	
	private static void f_selectById() {
		System.out.printf("조회할 ID > ");
		int empid = sc.nextInt();
		EmpDTO emp = empService.selectById(empid);
		EmpView.display(emp);
	}
	private static void f_selectAll() {
		List<EmpDTO> emplist = empService.selectAll();
		EmpView.display(emplist);
	}
		
	private static void menuDisplay() {
		System.out.printf("---------------");
		System.out.println("1.모두조회 2.조회(직원번호) 3.조회(부서) 4.조회(직책)  ");
		System.out.println("5.조건조회(부서,직원)  6.조건조화 7.delete 8.insert");
		System.out.println("---------------");
		System.out.print("작업선택 > ");
	}

}
