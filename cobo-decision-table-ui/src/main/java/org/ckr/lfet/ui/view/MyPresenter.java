package org.ckr.lfet.ui.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MyPresenter implements Initializable {

	@FXML
	Label message;
	
	@FXML
	Pane lightsBox;
	
	@Inject
    private LocalDate date;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void launch() {
		message.setText("Date: " + date + " -> ");
	}
	
	public void createLights() {
		
	}
	
}
