package view;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class Grafico {

	public Grafico(){

	};
	
	private static javax.swing.JPanel pizza3D(ArrayList<String> nome, ArrayList<Integer> valor,
			String tituloGrafico, float transparencia, String tipo){
			DefaultPieDataset pizza = new DefaultPieDataset();

			for(int i = 0; i < nome.toArray().length; i++){
			pizza.setValue("" + nome.get(i).toString(),
			new Double(valor.get(i).toString()));
			}

			JFreeChart chart = ChartFactory.createPieChart3D (tituloGrafico,
			pizza, true, true, false);
			

			java.awt.Color cor = new java.awt.Color(200, 200, 200);
			chart.setBackgroundPaint(cor);
			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setLabelLinksVisible(true);
			plot.setNoDataMessage("Não existem dados para serem exibidos " +
			"no gráfico");

			plot.setStartAngle(90);
			plot.setDirection(Rotation.CLOCKWISE);

			plot.setForegroundAlpha(transparencia);
			plot.setInteriorGap(0.20);

			ChartPanel chartPanel = new ChartPanel(chart);

			return chartPanel;
			}
	
	
	
	public static javax.swing.JPanel pizza3DStatic(ArrayList<String> nome, ArrayList<Integer> 
			valor, String tituloGrafico){
			return pizza3D(nome, valor, tituloGrafico, 0.5f, "Static");
			}
	

}
