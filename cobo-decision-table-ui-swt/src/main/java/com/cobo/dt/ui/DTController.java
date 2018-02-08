package com.cobo.dt.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.gorob.client.logging.AbstractLogging;
import com.gorob.gui.binding.IDoubleClickAction;
import com.gorob.gui.binding.ISelectionAction;
import com.gorob.gui.controller.AbstractController;

public class DTController extends AbstractController<DTComposite, DTFacade> {
	public DTController(DTComposite view, DTFacade facade, AbstractLogging log) {
		super(view, facade, log);
	}

	@Override
	protected void initController() {
		getView().getSashFormVertical().setWeights(new int[] { 315, 1051 });

		System.out.println(getView().getDisplay().getPrimaryMonitor().getClientArea().height);
		
		// getView().getSashFormHorizontalDefinition().setWeights(new int[] { 738/2, 738/2 });
		// getView().getSashFormHorizontalRules().setWeights(new int[] { 738/2-20, 738/2+20 });
	}
	
	
	private void synchSashForms() {
		int height1 = getView().getRulesConditionsTableViewer().getTable().getBounds().height;
		int height2 = getView().getRulesActionsTableViewer().getTable().getBounds().height;
		
//		System.out.println("a:" + height);
		
		// getView().getSashFormHorizontalDefinition().setWeights(new int[] { 738/height1, 738/height2 });
//		getView().getSashFormHorizontalRules().setWeights(new int[] { 738-height1-20, 738-height2+20 });
		
	}
	
	
	@Override
	protected void bindControls(DTComposite view) {
		getView().getRulesConditionsTableViewer().getTable().addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event event) {
				synchSashForms();
			}
		});
		
		
		bindTableViewer(getView().getConditionsTableViewer(), 
		        "conditionDefinitions", "selectedConditionDefinition", 
		        new String[] { "Bedingung" },
				new String[] { "text" },
				new int[] { 300 },
				new String[][] { null },
				new String[][] { null },
				new String[][] { null },
				new ISelectionAction() { public void execute() { } }, 
		        new IDoubleClickAction() { public void execute() { } }
				);
		
		bindTableViewer(getView().getActionsTableViewer(), 
		        "actionDefinitions", "selectedActionDefinition", 
		        new String[] { "Action" },
				new String[] { "text" },
				new int[] { 300 },
				new String[][] { null },
				new String[][] { null },
				new String[][] { null },
				new ISelectionAction() { public void execute() { } }, 
		        new IDoubleClickAction() { public void execute() { } }
				);
		
		bindRulesHeaderTableViewer();
		bindRulesConditionsTableViewer();
		bindRulesActionsTableViewer();
	}
	
	private void bindRulesHeaderTableViewer() {
		int columnCount = getFacade().getDecisionTable().getRules().size();
		
		bindTableViewer(getView().getRulesHeaderTableViewer(), 
		        "ruleNameHeaderRows", "selectedRuleNameHeaderRow", 
		        createColumnHeaders(columnCount),
		        createColumnValues("ruleName", columnCount),
				createInitialColumnWidths(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				new ISelectionAction() { public void execute() { } }, 
		        new IDoubleClickAction() { public void execute() { } }
				);
	}
	
	private void bindRulesConditionsTableViewer() {
		int columnCount = getFacade().getDecisionTable().getRules().size();
		
		bindTableViewer(getView().getRulesConditionsTableViewer(), 
		        "conditionValueRows", "selectedConditionValueRow", 
		        createColumnHeaders(columnCount),
		        createColumnValues("value", columnCount),
				createInitialColumnWidths(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				new ISelectionAction() { public void execute() { } }, 
		        new IDoubleClickAction() { public void execute() { } }
				);
	}

	private void bindRulesActionsTableViewer() {
		int columnCount = getFacade().getDecisionTable().getRules().size();
		
		bindTableViewer(getView().getRulesActionsTableViewer(), 
		        "actionValueRows", "selectedActionValueRow", 
		        createColumnHeaders(columnCount),
		        createColumnValues("value", columnCount),
				createInitialColumnWidths(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				createNullStringModelProperties(columnCount),
				new ISelectionAction() { public void execute() { } }, 
		        new IDoubleClickAction() { public void execute() { } }
				);
	}

	private String[] createColumnHeaders(int columnCount) {
		String[] columnValues = new String[columnCount];

		for (int i = 0; i < columnCount; i++) {
			columnValues[i] = "";
		}
		
		return columnValues;
	}
	
	private String[] createColumnValues(String property, int columnCount) {
		String[] columnValues = new String[columnCount];
		
		for (int i = 0; i < columnCount; i++) {
			columnValues[i] = property + "(" + i + ")";
		}
		
		return columnValues;
	}

	
	private int[] createInitialColumnWidths(int columnCount) {
		int[] columnWidths = new int[columnCount];
		
		for (int i = 0; i < columnCount; i++) {
			columnWidths[i]=70;
		}
		
		return columnWidths;
	}
	
	private String[][] createNullStringModelProperties(int columnCount) {
		List<String[][]> list = new ArrayList<String[][]>();
		
		for (int i = 0; i < columnCount; i++) {
			list.add(null);
		}
		
		return list.toArray(new String[][] {});
	}

}
