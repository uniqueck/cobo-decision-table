package org.cobo.decisiontable.tool.presentation.app;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.simpleframework.xml.core.Persister;

import com.cobo.dt.mapper.impl.LFDecisionTableMapper;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.LFDecisionTable;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AppPresenter implements Initializable {

	@Inject
	LFDecisionTableMapper lfDecisionTableMapper;
	
	public LFDecisionTableMapper getLfDecisionTableMapper() {
		return lfDecisionTableMapper;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handleOpen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setTitle("Open LFET");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("LFET", Arrays.asList("*.lfet")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("DT", Arrays.asList("*.dt")));
		File file2Open = fileChooser.showOpenDialog(null);
		if (file2Open != null) {
			DecisionTable loadDecisionTable = loadDecisionTable(file2Open);
			System.out.println(loadDecisionTable.toString());
		}
	}

	@FXML
	public void handleExit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void handleSave(ActionEvent event) {
	}
	
	
	protected DecisionTable loadDecisionTable(File dt2Open) {
		DecisionTable decisionTable = null;
		if (dt2Open.getName().toLowerCase().endsWith(".lfet")) {
			try {
				LFDecisionTable lfdt = new Persister().read(LFDecisionTable.class, dt2Open);
				decisionTable = getLfDecisionTableMapper().map(lfdt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				decisionTable = new Persister().read(DecisionTable.class, dt2Open);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return decisionTable;
	}

}
