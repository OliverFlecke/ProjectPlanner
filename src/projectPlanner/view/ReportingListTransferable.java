package projectPlanner.view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class ReportingListTransferable implements Transferable {
	ArrayList data;
	DataFlavor localArrayListFlavor;
	DataFlavor serialArrayListFlavor;
	
	public ReportingListTransferable(ArrayList alist, DataFlavor localArrayListFlavor, DataFlavor serialArrayListFlavor) {
		data = alist;
		this.localArrayListFlavor = localArrayListFlavor;
		this.serialArrayListFlavor = serialArrayListFlavor;
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (!isDataFlavorSupported(flavor)) {
			throw new UnsupportedFlavorException(flavor);
		}
		return data;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { localArrayListFlavor, serialArrayListFlavor };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (localArrayListFlavor.equals(flavor)) {
			return true;
		}
		if (serialArrayListFlavor.equals(flavor)) {
			return true;
		}
		return false;
	}

}
