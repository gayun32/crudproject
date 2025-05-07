package com.shinhan.dept;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeptDTO {
	
	
	 private int department_id;  
	 private String department_name;
	 private int manager_id;     
	 private int location_id;    

}
