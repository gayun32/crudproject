package com.shinhan.day15;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.movie.DBUtil;

//CRUD(Create Read UPdate Delete)
//Read(Select)
public class CRUDTest {
	
	public static void main (String[]args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql ="""
				select department_id, max(salary), min(salary) 
				from employees
				group by department_id
				having max(salary) <> min(salary)
				""";
		
		conn = DBUtil.getConnection();
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				int a = rs.getInt(1);
				int b = rs.getInt(2);
				int c = rs.getInt(3);
				System.out.println(a+"\t"+b+"\t"+c);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn,st,rs);
		}
	}
}

	
//	public static void f_2 () {
//		String url ="jdbc:oracle:thin:@localhost:1521:xe";
//		String userid="hr", userpass="hr";
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String sql="""
//				select department_id, count(*)
//				from employees
//				group by department_id
//				having count(*)>=5
//				order by 2 desc
//								""";
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(url, userid, userpass);
//			st = conn.createStatement();
//			rs=st.executeQuery(sql);
//			while(rs.next()) {
//				int deptis = rs.getInt(1);
//				int cnt = rs.getInt(2);
//				System.out.println("부서코드: " + deptid +"\t인원수" + cnt);
//			}
//
//			
//		} catch (ClassNotFoundException e) {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(url, userid, userpass);
//
//			e.printStackTrace();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs!=null) rs.close();
//				if(rs!=null) rs.close();
//				if(rs!=null) rs.close();
//			}catch (SQLException e) {
//				e.printStackTrace();
//		
//		
//	}
//	
//	
//	
//
//	public static void f_1() throws ClassNotFoundException, SQLException {
//		//1.JDBC Driver 준비 (class path 추가)
//		//2.JDBC Driver load
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		System.out.println("2.JDBC Driver load 성공");
//		//3.Connection
//		String url ="jdbc:oracle:thin:@localhost:1521:xe";
//		String userid="hr", userpass="hr";
//		Connection conn = DriverManager.getConnection(url, userid, userpass);
//		System.out.println("3. Connection 성공");
//		//4.sql문 보낼 통로 얻기 
//		Statement st = conn.createStatement();
//		System.out.println("4.SQL문 보낼 통로 얻기 성공");
//		//5.SQL문 생성, 실행 
//		String sql = """
//					select * 
//					from employees
//					where department_id = 80
//					""";
//		ResultSet rs = st.executeQuery(sql);
//		while(rs.next()) {
//			int empid = rs.getInt("employee_id");
//			String fname = rs.getString("first_name");
//			Date hdate = rs.getDate("hire_date");
//			double comm = rs.getDouble("COMMISSION_PCT");
//			System.out.printf("직원번호 :%d 이름 :%s hdate:%s comm:%3.1f\n", empid, fname, hdate, comm);
//			
//			
//		}
//		rs.close();
//		st.close();
//		conn.close();
//		
//
//	}
//
//}
