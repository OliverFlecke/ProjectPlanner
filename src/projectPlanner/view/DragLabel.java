package projectPlanner.view;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;

import javax.swing.JLabel;

public class DragLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519584057700725537L;
	
	
	private DragSource dragSource;
	private DragGestureListener dgListener;
	private DragSourceListener dsListener;

	public DragLabel(String s) {
		this.dragSource = DragSource.getDefaultDragSource();
		this.dgListener = new DGListener();
		this.dsListener = new DSListener();
		
		
		//component, action, listener
		this.dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this.dgListener);
	}
}
