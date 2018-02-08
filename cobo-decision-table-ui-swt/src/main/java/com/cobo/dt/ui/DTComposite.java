package com.cobo.dt.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gorob.client.view.SWTResourceManager;

public class DTComposite extends Composite {
	private TableViewer conditionsTableViewer;
	private TableViewer actionsTableViewer;
	private TableViewer rulesConditionsTableViewer;
	private TableViewer rulesActionsTableViewer;
	private TableViewer rulesHeaderTableViewer;
	private SashForm sashFormVertical;
	private SashForm sashFormHorizontalDefinition;
	private SashForm sashFormHorizontalRules;

	public DTComposite(Composite parent, int style) {
		super(parent, style);
		
        GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).applyTo(this);
	    GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(this);

	    createMainComposite(this);
	}
	
	private void createMainComposite(Composite parentComposite) {
		sashFormVertical = new SashForm(parentComposite, SWT.HORIZONTAL);
		
        GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).spacing(0, 0).applyTo(sashFormVertical);
	    GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(sashFormVertical);

	    createDefinitionSection(sashFormVertical);
	    createRulesSection(sashFormVertical);
	}
	
	private void createDefinitionSection(Composite parentComposite) {
    	sashFormHorizontalDefinition = new SashForm(parentComposite, SWT.VERTICAL);
    	
    	GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).applyTo(sashFormHorizontalDefinition);
	    GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sashFormHorizontalDefinition);
    	
	    createDefinitionConditionsSection(sashFormHorizontalDefinition);
	    createDefinitionsActionsSection(sashFormHorizontalDefinition);
    }
	
    private void createDefinitionConditionsSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);

        GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(5, 0, 5, 0).applyTo(sektion);
	    GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sektion);

	    Label lblConditions = new Label(sektion, SWT.NONE);
	    lblConditions.setFont(getFont(SWT.BOLD));
	    lblConditions.setText("Bedingungen:");
	    GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).grab(true, false).indent(5, 36).applyTo(lblConditions);
	    
	    conditionsTableViewer = new TableViewer(sektion, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
	    conditionsTableViewer.getTable().setHeaderVisible(false);
	    conditionsTableViewer.getTable().setLinesVisible(true);
	    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(conditionsTableViewer.getTable());
	}

    private void createDefinitionsActionsSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);

        GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(5, 0, 0, 10).applyTo(sektion);
	    GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sektion);

	    Label lblActions = new Label(sektion, SWT.NONE);
	    lblActions.setFont(getFont(SWT.BOLD));
	    lblActions.setText("Aktionen:");
	    GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).grab(true, false).indent(5, 5).applyTo(lblActions);

	    actionsTableViewer = new TableViewer(sektion, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
	    actionsTableViewer.getTable().setHeaderVisible(false);
	    actionsTableViewer.getTable().setLinesVisible(true);
	    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(actionsTableViewer.getTable());
	}

    private void createRulesSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);

        GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).applyTo(sektion);
	    GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(sektion);
	    
    	createRulesHeaderSection(sektion);
    	createRulesValuesSection(sektion);
    }
    
    private void createRulesHeaderSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);
    	
    	GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 5, 5, 0).applyTo(sektion);
    	GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(sektion);
    	
    	rulesHeaderTableViewer = new TableViewer(sektion, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
    	rulesHeaderTableViewer.getTable().setHeaderVisible(false);
    	rulesHeaderTableViewer.getTable().setLinesVisible(true);
    	GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).hint(SWT.DEFAULT, 2).applyTo(rulesHeaderTableViewer.getTable());
    }
    
    private void createRulesValuesSection(Composite parentComposite) {
    	sashFormHorizontalRules = new SashForm(parentComposite, SWT.VERTICAL);

    	GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).applyTo(sashFormHorizontalRules);
    	GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sashFormHorizontalRules);
    	
    	createRulesConditionsSection(sashFormHorizontalRules);
    	createRulesActionsSection(sashFormHorizontalRules);    	
    }
    
    private void createRulesConditionsSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);

        GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 5, 0, 0).applyTo(sektion);
	    GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sektion);

	    rulesConditionsTableViewer = new TableViewer(sektion, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
	    rulesConditionsTableViewer.getTable().setHeaderVisible(false);
	    rulesConditionsTableViewer.getTable().setLinesVisible(true);
	    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).indent(0, 28).applyTo(rulesConditionsTableViewer.getTable());
	}
    
    private void createRulesActionsSection(Composite parentComposite) {
    	Composite sektion = new Composite(parentComposite, SWT.NONE);

        GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 5, 0, 10).applyTo(sektion);
	    GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.FILL).applyTo(sektion);

	    rulesActionsTableViewer = new TableViewer(sektion, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
	    rulesActionsTableViewer.getTable().setHeaderVisible(false);
	    rulesActionsTableViewer.getTable().setLinesVisible(true);
	    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).indent(0, 28).applyTo(rulesActionsTableViewer.getTable());
	}
    
	private Font getFont(int style) {
		return SWTResourceManager.getFont(getFont().getFontData()[0].getName(), (int)getFont().getFontData()[0].height, style);
	}

    public TableViewer getConditionsTableViewer() {
		return conditionsTableViewer;
	}
    
    public TableViewer getActionsTableViewer() {
		return actionsTableViewer;
	}
    
    public TableViewer getRulesHeaderTableViewer() {
		return rulesHeaderTableViewer;
	}
    
    public TableViewer getRulesConditionsTableViewer() {
		return rulesConditionsTableViewer;
	}
    
    public TableViewer getRulesActionsTableViewer() {
		return rulesActionsTableViewer;
	}
    
    public SashForm getSashFormVertical() {
		return sashFormVertical;
	}
    
    public SashForm getSashFormHorizontalDefinition() {
		return sashFormHorizontalDefinition;
	}
    
    public SashForm getSashFormHorizontalRules() {
		return sashFormHorizontalRules;
	}
}
