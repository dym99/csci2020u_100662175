package csci2020u.lab06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) {
		launch(args);
	}

	private Canvas m_canvas;
	private GraphicsContext m_gc;
	
	private static double[] avgHousingPricesByYear = 
		{247381.0,264171.4,287715.3,294736.1,308431.4,322635.9,340253.0,363153.7};
	private static double[] avgCommercialPricesByYear = 
		{1121585.3,1219479.5,1246354.2,1295364.8,1335932.6,1472362.0,1583521.9,1613246.3};
	private static Color barColours[] = { Color.RED, Color.LIME, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.CYAN };
	
	private static String[] ageGroups = 
		{"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
	private static int[] purchasesByAgeGroup = 
		{648, 1021, 2453, 3173, 1868, 2247};
	private static Color[] pieColours = 
		{Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		m_canvas = new Canvas(1024, 512);
		m_gc = m_canvas.getGraphicsContext2D();
		
		drawBarGraph(avgHousingPricesByYear, avgCommercialPricesByYear,256,128,256,256) ;
		drawPieChart(purchasesByAgeGroup, pieColours, 512, 128, 256, 256);
		
		VBox vbox = new VBox(m_canvas);
		Scene scene = new Scene(vbox);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void drawBarGraph(double[] setA, double[] setB, double x, double y, double w, double h) {
		double max = setA[0];
		int count = 0;
		for (double data : setA) {
			max = Math.max(max, data);
		}
		for (double data : setB) {
			max = Math.max(max, data);
		}

		m_gc.setFill(Color.RED);
		for (int i = 0; i < setA.length; i++) {
			m_gc.fillRect(x+25*i, y+h-(setA[i]/max)*h, 10, (setA[i]/max)*h);
		}		
		m_gc.setFill(Color.BLUE);
		for (int i = 0; i < setB.length; i++) {
			m_gc.fillRect(x+25*i+10, y+h-(setB[i]/max)*h, 10, (setB[i]/max)*h);
		}
	}
	
	public void drawPieChart(int dataSet[], Color colours[], double x, double y, double w, double h) {
		double total = 0;
		for (int i = 0; i < dataSet.length; i++) {
			total += dataSet[i];
		}
		double start = 0;
		for (int i = 0; i < dataSet.length; i++) {
			m_gc.setFill(colours[i]);
			double fill = ((double)dataSet[i])/((double)total)*360;
			m_gc.fillArc(x, y, w, h, start, fill, ArcType.ROUND);
			start+=fill;
		}
	}
	
}
