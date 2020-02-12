package csci2020u.lab05;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TableView<StudentRecord> table = new TableView<StudentRecord>();
		
		TableColumn<StudentRecord, String> idColumn = new TableColumn<StudentRecord, String>("SID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		
		table.getColumns().add(idColumn);
		
		TableColumn<StudentRecord, String> assignmentsColumn = new TableColumn<StudentRecord, String>("Assignments");
		assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));
		
		table.getColumns().add(assignmentsColumn);
		
		TableColumn<StudentRecord, String> midtermColumn = new TableColumn<StudentRecord, String>("Midterm");
		midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));
		table.getColumns().add(midtermColumn);
		
		TableColumn<StudentRecord, String> finalExamColumn = new TableColumn<StudentRecord, String>("Final Exam");
		finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
		
		table.getColumns().add(finalExamColumn);
		
		TableColumn<StudentRecord, String> finalMarkColumn = new TableColumn<StudentRecord, String>("Final Mark");
		finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
		
		table.getColumns().add(finalMarkColumn);
		
		TableColumn<StudentRecord, String> letterGradeColumn = new TableColumn<StudentRecord, String>("Letter Grade");
		letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
		
		table.getColumns().add(letterGradeColumn);
		
		ObservableList<StudentRecord> dataList = DataSource.getAllMarks();
		for (StudentRecord data : dataList) {
			table.getItems().add(data);
		}
		
		GridPane grid = new GridPane();
		
		Label sidFieldLabel = new Label("SID:");
		TextField sidField = new TextField();
		
		grid.add(sidFieldLabel, 0, 0);
		grid.add(sidField, 1, 0);

		Label assignmentsFieldLabel = new Label("Assignments:");
		TextField assignmentsField = new TextField();
		
		grid.add(assignmentsFieldLabel, 2, 0);
		grid.add(assignmentsField, 3, 0);

		Label midtermFieldLabel = new Label("Midterm/100:");
		TextField midtermField = new TextField();
		
		grid.add(midtermFieldLabel, 0, 1);
		grid.add(midtermField, 1, 1);

		Label finalExamFieldLabel = new Label("Final Exam:");
		TextField finalExamField = new TextField();
		
		grid.add(finalExamFieldLabel, 2, 1);
		grid.add(finalExamField, 3, 1);
		
		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				String sid = sidField.getText();
				float assignments = 0;
				float midterm = 0;
				float finalExam = 0;
				try {
					assignments = Float.parseFloat(assignmentsField.getText());
					midterm = Float.parseFloat(midtermField.getText());
					finalExam = Float.parseFloat(finalExamField.getText());
				} catch (NumberFormatException e) {
					return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				table.getItems().add(new StudentRecord(sid,assignments,midterm,finalExam));
			}
			
		});
		
		grid.add(addButton, 1, 3);
		grid.setStyle("-fx-padding:10px;");
		
		VBox vbox = new VBox(table, grid);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
