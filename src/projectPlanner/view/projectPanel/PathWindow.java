package projectPlanner.view.projectPanel;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PathWindow extends JFileChooser{
	private String path;
	public PathWindow() throws UserClosedWindowException{
		super();
		JFrame jframe = new JFrame();
		int returnVal = this.showSaveDialog(jframe);
		
		try{
		path = this.getSelectedFile().getAbsolutePath();
		
		//forces pdf extension
		if(!path.endsWith(".pdf") ) {
			path = path + ".pdf";
		}
		
		}catch(NullPointerException e){
			throw new UserClosedWindowException("The user closed the save window");
		}
		confirmSelection();
	}
	
	//sets the path to null if user decides not to overwrite
	private void confirmSelection() {
		File f = getSelectedFile();
		if(f.exists()){
			int option = JOptionPane.showConfirmDialog(this,"Do you really want to overwrite the existing file?","Overwrite the existing file?",JOptionPane.YES_NO_OPTION);
            switch(option){
                case JOptionPane.YES_OPTION:
                    return;
                case JOptionPane.NO_OPTION:
                	path = null;
                    return;
            }
		}
		
		
	}
	
	//if path is null, as when user decides not to overwrite, it throws user cancelled type exception 
	public String getPath() throws UserClosedWindowException{
		if(path==null){
			throw new UserClosedWindowException("user choose not to overwrite file");
		}
		else return path;
	}
}
