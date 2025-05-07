package com.shinhan.common;

import java.util.Scanner;

import com.shinhan.dept.DeptController;
import com.shinhan.emp.EmpController;



//FrontContrller 패턴 : Controller가 여러개인 경우 사용자의 요청과 응답은 출구가 여러개 
//바람직하지 않음
//하나로 통합 (Front는 1개)
//Servlet : DispatcherServlet 있다. (Spring은 FrontController가 이미 있다.)
public class FrontController {

	public static void main(String[] args) {
		//사용자가 emp, dept작업할것인지 결정
		Scanner sc = new Scanner(System.in);
		System.out.println("emp,dept>>");
		String job = sc.next();
		boolean isStop = false;
		CommonControllerInterface controller = null;
		while(isStop) {
			if(job.equals("emp")) {
				controller = ControllerFactory.make("emp");
			}else if(job.equals("dept")) {
				controller = ControllerFactory.make("dept");
			}else if(job.equals("end")){
				isStop=true; continue;
			}
			switch(job) {
			case "emp"->{controller = ControllerFactory.make("emp");}
			case "dept"->{controller = ControllerFactory.make("dept");}
			case "job"->{controller = ControllerFactory.make("job");}
			case "end"->{isStop=true;continue;}
			default ->{continue;}
			
			}
			//전략패턴
			controller.execute();
			
			
		}
		sc.close();
		System.out.println("=====MAIN END=====");
		
		
	}

}
