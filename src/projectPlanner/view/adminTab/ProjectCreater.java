package projectPlanner.view.adminTab;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projectPlanner.view.StdListPanel;
import projectPlanner.view.personalInfo.NameObject;
import projectPlanner.view.personalInfo.PersonalInfoPane;

public class ProjectCreater extends JPanel {
	
	private NameObject title;
	private TextNField projectName;
	private TextNDate deadLineFld;
	private TextNDate startDateFld;
	private StdListPanel lisOfemployees;
	private TextNField projectLeader;
	private JButton createProject;
	
	
	public ProjectCreater() {
		title = new NameObject("Create Project");
		projectName = new TextNField("Project Name: ");
		deadLineFld = new TextNDate("DeadLine: ");
		startDateFld = new TextNDate("Username: ");
		lisOfemployees = new StdListPanel(getEmps(), "Available Employees");
		projectLeader = new TextNField("Project Leader");
		createProject = new JButton("Create Project");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(title);
		this.add(projectName);
		this.add(startDateFld);
		this.add(deadLineFld);
		this.add(lisOfemployees);
		this.add(projectLeader);
		this.add(createProject);
		this.add(Box.createVerticalGlue());
		
		this.setPreferredSize(new Dimension(300,400));
		
	}


	private List<String> getEmps() {
		List<String> emps = Arrays.asList("Lars","Emil","Mads","Tissemand","lol");
		return emps;
	}

}
