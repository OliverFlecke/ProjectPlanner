package projectPlanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import projectPlanner.users.Employee;
import projectPlanner.users.User;

public class Project {
	
	private Double allottedTime;
	private String name;
	private List<Employee> projectLeaders;
	private Calendar startDate;
	private Calendar endDate;
	private boolean isActive;
	private static int idCount=0;
	private int id;
	
	public Project(String name, Double allottedTime) {
		this.name=name;
		this.allottedTime=allottedTime;
		this.projectLeaders = new ArrayList<Employee>();
		this.id=idCount++;
	}
	
	public void addProjectLeader(User admin, Employee employee){
		if(admin.isAdmin){
			projectLeaders.add(employee);			
		}
		else{
			throw new ActionNotAllowedExcepetion("You don't have permission to add a leader", employee);
		}
		
	}

}
