
package logic;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

public class PDF {
    
    public static void generatePiechart() throws IOException{
    
        // Create Chart
        PieChart chart = new PieChartBuilder().width(400).height(300).title("Ausgaben").build();
 
        // Customize Chart
        Color[] sliceColors = new Color[] { new Color(0, 255, 255), new Color(50, 205, 50), new Color(205, 38, 38), new Color(105, 38, 205)};
        chart.getStyler().setSeriesColors(sliceColors);
 
        // Series
        chart.addSeries("Einkaufen", 328);
        chart.addSeries("Miete", 750);
        chart.addSeries("Koks", 200);
        chart.addSeries("Nutten", 500);
        //new SwingWrapper<PieChart>(chart).displayChart();

        BitmapEncoder.saveBitmap(chart, "./Piechart", BitmapEncoder.BitmapFormat.PNG);
            
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("./Piechart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, 792-300);
        contentStream.close();
        
        document.save("Piechart.pdf");
        document.close();
        
        System.out.println("Piechart done");
    
    }
    
    
    public static void generateBarchart() throws IOException{
    
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(400).height(350).title("Monatsübersicht").xAxisTitle("").yAxisTitle("Umsätze").build();
 
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setXAxisTicksVisible(false);
 
        // Series
        chart.addSeries("Einnahme", Arrays.asList(new Integer[] { 0}), Arrays.asList(new Integer[] { 3500})).setFillColor(Color.green);
        chart.addSeries("Ausgabe", Arrays.asList(new Integer[] { 1}), Arrays.asList(new Integer[] { 2000})).setFillColor(Color.red);
            
        BitmapEncoder.saveBitmap(chart, "./Barchart", BitmapEncoder.BitmapFormat.PNG);
            
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page); 
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("./Barchart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, 792-350);
        contentStream.close();
        
        document.save("Barchart.pdf");
        document.close();
        
        System.out.println("Barchart done");   
    
    }
    
    public static void generateDonutchart() throws IOException{
    
        // Create Chart
        PieChart chart = new PieChartBuilder().width(400).height(300).title("Monatsübersicht August").build();
 
        // Customize Chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Label);
        chart.getStyler().setAnnotationDistance(.82);
        chart.getStyler().setPlotContentSize(.9);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Donut);
 
        // Series
        chart.addSeries("Ausgaben: 1500€", 1500).setFillColor(Color.red);
        chart.addSeries("Einnahmen: 2500€", 2500).setFillColor(Color.green);
        
        BitmapEncoder.saveBitmap(chart, "./Donutchart", BitmapEncoder.BitmapFormat.PNG);
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page); 
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("./Donutchart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, 792-300);
        contentStream.close();
        
        document.save("Donutchart.pdf");
        document.close();
        
        System.out.println("Donutchart done");
    
    }
   
    public static void generateGgplot2chart() throws IOException{
    
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(300).title("Jahresübersicht").xAxisTitle("").yAxisTitle("").theme(Styler.ChartTheme.GGPlot2).build();
 
        // Series
        chart.addSeries("Ausgaben", new ArrayList<String>(Arrays.asList(new String[] { "Januar", "Februar", "März", "April", "Mai" })), new ArrayList<Number>(Arrays.asList(new Number[] { 2000, 2000, 1800, 1900,
        1200 }))).setFillColor(Color.green);
        chart.addSeries("Einnahmen", new ArrayList<String>(Arrays.asList(new String[] { "Januar", "Februar", "März", "April", "Mai" })), new ArrayList<Number>(Arrays.asList(new Number[] { -580, -800, -500, -2300,
        -2400 }))).setFillColor(Color.red);
        
        BitmapEncoder.saveBitmap(chart, "./GGPlotchart", BitmapEncoder.BitmapFormat.PNG);
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        document.addPage(page);
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("./GGPlotchart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, page.getCropBox().getHeight()-300);
        contentStream.close();
        
        document.save("GGPlotChart.pdf");
        document.close();
        
        System.out.println("GGPlotchart done");
    
    }
    
    public static void generateBarchart2() throws IOException{
    
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(400).height(350).title("Jahresübersicht").xAxisTitle("").yAxisTitle("Umsätze").build();
        
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setXAxisTicksVisible(true);
 
        // Series
        chart.addSeries("Einnahme", Arrays.asList(new String[] { "Januar", "Februar", "März", "April"}), Arrays.asList(new Integer[] { 3500, 3200, 3300, 3100})).setFillColor(Color.green);
        chart.addSeries("Ausgabe", Arrays.asList(new String[] { "Januar", "Februar", "März", "April"}), Arrays.asList(new Integer[] { 2000, 2100, 1500, 900})).setFillColor(Color.red);
        
        BitmapEncoder.saveBitmap(chart, "./Barchart2", BitmapEncoder.BitmapFormat.PNG);
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page); 
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("./Barchart2.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, 792-350);
        contentStream.close();
        
        document.save("Barchart2.pdf");
        document.close();
        
        System.out.println("Barchart2 done");          
    
    }
    
}
