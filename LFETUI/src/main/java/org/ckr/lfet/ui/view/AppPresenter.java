package org.ckr.lfet.ui.view;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AppPresenter implements Initializable {

	@FXML
	MenuBar menuBar;

	@FXML
	AnchorPane input;
	@FXML
	AnchorPane overview;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handleOpen(ActionEvent event) {
		System.out.println("open");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setTitle("Open LFET");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("LFET", Arrays.asList("*.lfet")));
		File openDialog = fileChooser.showOpenDialog(null);

		System.out.println(openDialog);
	}

	@FXML
	public void handleExit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void handleSave(ActionEvent event) {
	}

}
