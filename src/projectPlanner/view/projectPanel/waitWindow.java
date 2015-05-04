package projectPlanner.view.projectPanel;

import java.io.IOException;

public class waitWindow {
	//create barChart
			new Thread() {
	            @Override
	            public void run() {
	            	
	            	try {
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                
	                latch.countDown();
	            }
	        }.start();
	        try {
	            latch.await();
	        } catch (InterruptedException ex) {
	           ex.printStackTrace();
	        }

}
