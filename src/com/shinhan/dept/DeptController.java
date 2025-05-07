package com.shinhan.dept;

import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;
import com.shinhan.emp.EmpDTO;
import com.shinhan.emp.EmpService;
import com.shinhan.emp.EmpView;


//controller(사용자가 요청하면 요청을 처리하고 응답을 보낸다.(Servlet으로 변경할 예정이다.)
public class DeptController implements CommonControllerInterface{
	
	static Scanner sc1 = new Scanner(System.in);
	static DeptService deptService = new DeptService();
	
	
	static Scanner sc = new Scanner(System.in);
	
	@Override
	public void execute() {
		
		boolean isStop = false;
		while(!isStop) {	
			DeptView.menuDisplay();
			String dept = sc1.nextLine();
			switch(dept) {
			case "all"->{f_all();}
			case "detail"->{f_detail();}
			case "i"->{f_insert();}
			case "u"->{f_update();}
			case "d"->{f_delete();}
			
			case "exit"->{isStop=true;}
			
			
			}
			
		}
		DeptView.display("프로그램 종료");
		
				
		
		

	}
	private static void f_delete() {
		DeptDTO dept = makeDept("입력할 부서번호>>");
		DeptView.display(deptService.updateDept(dept)+"건 삭제");
		
		
	}
	private static void f_update() {
		DeptDTO dept = makeDept("입력할 부서번호>>");
		DeptView.display(deptService.updateDept(dept)+"건 수정");
		
	}
	private static void f_insert() {
	
		DeptDTO dept = makeDept("입력할 부서번호>>");
		DeptView.display(deptService.updateDept(dept)+"건 입력");
		
	}
	
	private static void makeDept(String title) {

		int deptid = parseInt(dataInsert("부서번호>>"));
		String deptname = dataInsert("부서이름>>");
		int mgrid = parseInt(dataInsert("매니저번호>>"));
		int locid = parseInt(dataInsert("지역번호>>"));
		DeptDTO dept = DeptDTO.builder()
				.department_id(deptid)
				.department_name(deptname)
				.location_id(locid)
				.manager_id(mgrid)
				.build();
			DeptView.display(deptService.updateDept(dept)+"건 입력");
		
	}
	private static int parseInt(String dataInsert) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static String dataInsert(String column) {
		System.out.println(column);
		return sc1.nextLine()
;	}
	
	private static void f_detail() {
		String s_deptid=sc1.nextLine();
		int deptid = Integer.parseInt(s_deptid);
		DeptView.display(deptService.selectById(deptid));
	}
	private static void f_all() {
		DeptView.display(deptService.selectAll() );
		
	}
	
	private static void menuDisplay() {
		// TODO Auto-generated method stub
		
	}

}
