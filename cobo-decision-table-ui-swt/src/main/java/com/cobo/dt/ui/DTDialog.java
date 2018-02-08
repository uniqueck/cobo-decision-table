package com.cobo.dt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.gorob.client.logging.AbstractLogging;
import com.gorob.gui.dialog.AbstractDialog;

public class DTDialog extends AbstractDialog<DTController, DTComposite, DTFacade, DTDialogParameter> {
	public DTDialog(DTDialogParameter dialogParameter, AbstractLogging log) {
		super(dialogParameter, log);
	}

	@Override
	protected DTController createDialogController(DTComposite dialogView, DTFacade dialogFacade, AbstractLogging log) {
		return new DTController(dialogView, dialogFacade, log);
	}

	@Override
	protected DTComposite createDialogView(Composite dialogAreaComposite) {
		return new DTComposite(dialogAreaComposite, SWT.NONE);
	}

	@Override
	protected DTFacade createDialogFacade(DTDialogParameter dialogParameter, AbstractLogging log) {
		return new DTFacade(dialogParameter, log);
	}
	
	@Override
    protected Control createButtonBar(Composite parent) {
    	return null;  // keine Standard-Button-Leiste
    }   

}
