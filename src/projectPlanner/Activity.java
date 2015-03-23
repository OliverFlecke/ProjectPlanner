package projectPlanner;

import java.util.ArrayList;
import java.util.List;

import projectPlanner.users.Employee;

public class Activity {


	private List<Employee> employeesAttached;
	private String name;
	private static int idCount=0;
	private int id;
	private final Project project;

	
	public Activity(String name, Project project) {
		this.name=name;
		this.project = project;
		this.id=idCount++;
	}
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	private double timeAccumulated;
	public double getTimeAccumulated() {
		return timeAccumulated;
	}


	public void setTimeAccumulated(double timeAccumulated) {
		this.timeAccumulated = timeAccumulated + timeAccumulated;
	}


	public List<Employee> getEmployeesAttached() {
		return employeesAttached;
	}


	public void setEmployeesAttached(Employee employee) {
		this.employeesAttached.add(employee);
	}	
	
	
	
}
