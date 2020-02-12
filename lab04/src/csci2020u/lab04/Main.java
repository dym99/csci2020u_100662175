package csci2020u.lab04;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Set window title
		primaryStage.setTitle("Lab 04");
		
		//VBox layout
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		//Force column 1 to be 150px wide (minimum)
	    grid.getColumnConstraints().add(new ColumnConstraints(150));
	    
		//Username Field
		Label uNameLabel = new Label("Username:");
		TextField uNameField = new TextField();
		
		grid.add(uNameLabel, 0, 0);
		grid.add(uNameField, 1, 0);
		
		//Password Field
		Label pWordLabel = new Label("Password:");
		PasswordField pWordField = new PasswordField();
		
		grid.add(pWordLabel, 0, 1);
		grid.add(pWordField, 1, 1);
		
		//Full Name Field
		Label fNameLabel = new Label("Full Name");
		TextField fNameField = new TextField();
		
		grid.add(fNameLabel, 0, 2);
		grid.add(fNameField, 1, 2);
		
		//Email field
		Label eMailLabel = new Label("E-Mail Address");
		TextField eMailField = new TextField();
		
		grid.add(eMailLabel, 0, 3);
		grid.add(eMailField, 1, 3);
		
		//Phone Field
		Label phoneLabel = new Label("Phone #:");
		TextField phoneField = new TextField();
		
		UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
			
			@Override
			public Change apply(Change change) {
				
				if (change.isReplaced()||change.isAdded()) {
					//If the the string isn't all numbers
					if (!change.getControlNewText().matches("^\\d{1,3}[-]{0,1}\\d{0,3}[-]{0,1}\\d{0,4}$")) {
						//Ignore the change.
                        change.setText(change.getControlText().substring(change.getRangeStart(), change.getRangeEnd()));
                        change.setCaretPosition(change.getControlCaretPosition());
					}
				}
				
				return change;
			}
		};
		
		phoneField.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
		
		grid.add(phoneLabel, 0, 4);
		grid.add(phoneField, 1, 4);
		
		//DOB Field
		Label dobLabel = new Label("Date of Birth");
		DatePicker dobField = new DatePicker();
		
		grid.add(dobLabel, 0, 5);
		grid.add(dobField, 1, 5);
		
		//Submit Button
		Button submitBtn = new Button("Register");
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
		    		System.out.print(uNameField.getText()+"\n"+pWordField.getText()+"\n"+fNameField.getText()+"\n"+eMailField.getText()+"\n"+phoneField.getText()+"\n"+dobField.getValue().toString());
		    	} catch(Exception ex) {}
		    }
		});
		
		grid.add(submitBtn, 1, 6);
		
		//Set scene to a new scene with vbox as root node.
		primaryStage.setScene(new Scene(grid,800,600));
		
		//Show the stage.
		primaryStage.show();
	}
}
