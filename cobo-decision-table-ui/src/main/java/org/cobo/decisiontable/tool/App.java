package org.cobo.decisiontable.tool;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.cobo.decisiontable.tool.presentation.app.AppView;

import com.airhacks.afterburner.injection.Injector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		LocalDate date = LocalDate.of(4242, Month.JULY, 21);
        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("date", date);
        
        Injector.setConfigurationSource(customProperties::get);
		
		Scene scene = new Scene(new AppView().getView());
		primaryStage.setTitle("LFET UI");
		final String uri = getClass().getClassLoader().getResource("app.css").toExternalForm();
		scene.getStylesheets().add(uri);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		Injector.forgetAll();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
