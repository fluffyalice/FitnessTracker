package assignment2020.gui;

import java.awt.*;
import java.awt.Font;
import java.awt.geom.Line2D;
import java.util.List;
import assignment2020.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.gui.AbstractGUIPanel;
import assignment2020.codeprovided.gui.BasicGUIPlotPanel;


/**
 * A class to represent the GUI panel where the selected curves will be plot
 * using Java 2D. You are expected to write this class.
 *
 */

public class GUIPlotPanel extends BasicGUIPlotPanel {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -1482643158587603732L;
	private Graphics2D graphics;
	private double originPointX;
	private double originPointY;
	private double xAxisEndX;
	private double xAxisEndY;
	private double yAxisEndX;
	private double yAxisEndY;

	private double arrowHalfWidth;
	private double arrowLength;

	boolean ifPlotCurves = false;
	String trackerName;
	List<Measurement> measurementList;
	String measurementType;

	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		// TODO
		super(parentGUIPanel);
	}

	public void passPlotData(List<Measurement> measurementList, MeasurementType measurementType){
		ifPlotCurves = true;
		this.measurementList = measurementList;
		this.measurementType = measurementType.getMeasurementName();
	}

	public void plotTest(String trackerName){
		ifPlotCurves = true;
		this.trackerName = trackerName;
	}

	public int findMax(List<Measurement> measurementList){

		int tempMax = measurementList.get(0).getValue().intValue();
		for(int i = 1; i<measurementList.size(); i++){
			int tempMea = measurementList.get(i).getValue().intValue();
			if( tempMea > tempMax){
				tempMax = tempMea;
			}
		}
		return tempMax;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.graphics = g2;
		// TODO

		Dimension d = getSize();

		originPointX = d.width/12;
		originPointY = d.height/1.1;
		xAxisEndX = d.width/1.05;
		xAxisEndY = originPointY;
		yAxisEndX = originPointX;
		yAxisEndY = d.height/15;
		double lengthOfYAxis = originPointY-yAxisEndY;

		arrowHalfWidth = 10;
		arrowLength = 30;

		g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));

		//TODO plot curves here

		Line2D xAxis = new Line2D.Double(originPointX, originPointY, xAxisEndX, xAxisEndY);
		g2.draw(xAxis);
		Line2D yAxis = new Line2D.Double(originPointX, originPointY, yAxisEndX, yAxisEndY);
		g2.draw(yAxis);

		Line2D xAxisArrowLine1 = new Line2D.Double(xAxisEndX-arrowLength, xAxisEndY+arrowHalfWidth, xAxisEndX, xAxisEndY);
		g2.draw(xAxisArrowLine1);
		Line2D xAxisArrowLine2 = new Line2D.Double(xAxisEndX-arrowLength, xAxisEndY-arrowHalfWidth, xAxisEndX, xAxisEndY);
		g2.draw(xAxisArrowLine2);
		Line2D yAxisArrowLine1 = new Line2D.Double(yAxisEndX+arrowHalfWidth, yAxisEndY+arrowLength, yAxisEndX, yAxisEndY);
		g2.draw(yAxisArrowLine1);
		Line2D yAxisArrowLine2 = new Line2D.Double(yAxisEndX-arrowHalfWidth, yAxisEndY+arrowLength, yAxisEndX, yAxisEndY);
		g2.draw(yAxisArrowLine2);

		Font font =  new Font("Arial", Font.BOLD+Font.ITALIC,24);
		g2.setFont(font);

		g2.drawString("O", (float)originPointX-30, (float)originPointY+30);
		g2.drawString("x-Asis", (float)xAxisEndX-50, (float)xAxisEndY+45);
		g2.drawString("Count", ((float)xAxisEndX-50)/2, (float)xAxisEndY+30);
		g2.drawString("y-Asis", (float)yAxisEndX-35, (float)yAxisEndY-15);

		if(measurementType == "Distance"){
			g2.drawString("Distance", (float)originPointX-120, (float) ((yAxisEndY-15+originPointY)/2));
		}else if(measurementType == "Heart Rate"){
			g2.drawString("Heart Rate", (float)originPointX-120, (float) ((yAxisEndY-15+originPointY)/2));
		}else if(measurementType == "Steps"){
			g2.drawString("Steps", (float)originPointX-120, (float) ((yAxisEndY-15+originPointY)/2));
		}else if(measurementType == "Energy expenditure"){
			g2.drawString("Energy expenditure", (float)originPointX-120, (float) ((yAxisEndY-15+originPointY)/2));
		}

		if(ifPlotCurves == true){
			int maxCount = measurementList.size();
			int tempSegStartX = 0;
			int tempSegStartY = 0;
			int tempSegEndX = 0;
			int tempSegEndY = 0;
			int deltaX = 0;
			int tempY = 0;

			if(maxCount>1){
				int maxPlotValueY = findMax(measurementList);

				deltaX = (int) (xAxisEndX/maxCount);
				tempSegStartX = (int) (originPointX);
				tempY = measurementList.get(0).getValue().intValue();
				tempY = (int) (0.8*tempY*lengthOfYAxis/maxPlotValueY);
				tempSegStartY = (int) (originPointY-tempY);

				for(int i = 1; i < maxCount-1; i++){
					tempSegEndX = tempSegStartX+deltaX;
					tempY = measurementList.get(i).getValue().intValue();
					tempY = (int) (0.8*tempY*lengthOfYAxis/maxPlotValueY);
					tempSegEndY = (int) (originPointY-tempY);
					g2.setPaint(Color.red);
					Line2D segment = new Line2D.Double(tempSegStartX, tempSegStartY, tempSegEndX, tempSegEndY);
					graphics.draw(segment);
					tempSegStartX = tempSegEndX;
					tempSegStartY = tempSegEndY;
			    }

			}

		}

	}

}
