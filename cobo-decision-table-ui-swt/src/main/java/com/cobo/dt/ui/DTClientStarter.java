package com.cobo.dt.ui;

import org.eclipse.swt.widgets.Display;

import com.cobo.dt.ui.DTDialog;
import com.cobo.dt.ui.DTDialogParameter;
import com.gorob.client.logging.MemoryLogging;

public class DTClientStarter {
	public static void main(String[] args) {
		MemoryLogging log = new MemoryLogging();
    	DTDialogParameter parameter = new DTDialogParameter("CoBo ET", Display.getDefault().getClientArea().width, Display.getDefault().getClientArea().height, 0, 0, true);
    	DTDialog etDialog = new DTDialog(parameter, log);
    	etDialog.open();
	}
}
