package projectPlanner.view;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class StringTransferable implements Transferable, ClipboardOwner {
	
	
	@SuppressWarnings("deprecation")
	public static final DataFlavor plainTextFlavor = DataFlavor.plainTextFlavor;
	public static final DataFlavor localStringFlavor = DataFlavor.stringFlavor;
	
	
public static final DataFlavor[] flavors = { StringTransferable.plainTextFlavor, StringTransferable.localStringFlavor };

private static final List flavorList = Arrays.asList(flavors);

public synchronized DataFlavor[] getTransferDataFlavors(){
	return flavors;
}

public boolean isDataFlavorSupported(DataFlavor flavor) {
	return (flavorList.contains(flavor));
}

}
