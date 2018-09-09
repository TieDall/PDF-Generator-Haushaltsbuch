
package logic;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeMath.round;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import ws_server.Data;
import ws_server.DataService;
import ws_server.GetBarDataResponse;
import ws_server.GetPieDataResponse;

public class PDF {
    
    public static void generatePiechart() throws IOException{
    
        // Create Chart
        PieChart chart = new PieChartBuilder().width(400).height(300).title("Einnahmen").build();
 
        // Customize Chart
        Color[] sliceColors = new Color[] { new Color(0, 255, 255), new Color(50, 205, 50), new Color(205, 38, 38), new Color(105, 38, 205)};
        chart.getStyler().setSeriesColors(sliceColors);
 
        // Series        
        Data data = new DataService().getDataPort();
        GetPieDataResponse.DataPie pieData = data.getPieData();
        List<GetPieDataResponse.DataPie.Entry> entry = pieData.getEntry();
        
        for (Iterator<GetPieDataResponse.DataPie.Entry> iterator = entry.iterator(); iterator.hasNext();) {
            GetPieDataResponse.DataPie.Entry next = iterator.next();
            chart.addSeries(next.getKey(), next.getValue());
        }
        
        //new SwingWrapper<PieChart>(chart).displayChart();

        BitmapEncoder.saveBitmapWithDPI(chart, "C:/xampp/htdocs/Piechart", BitmapEncoder.BitmapFormat.PNG, 300);
            
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/xampp/htdocs/Piechart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.drawImage(pdImage, 0, 792-300, 400, 300);
        contentStream.close();
        
        document.save("C:/xampp/htdocs/Piechart.pdf");
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
        Data data = new DataService().getDataPort();
        GetBarDataResponse.DataBar barData = data.getBarData();
        List<GetBarDataResponse.DataBar.Entry> entry = barData.getEntry();
        DecimalFormat df = new DecimalFormat("####0.00");
        
        for (Iterator<GetBarDataResponse.DataBar.Entry> iterator = entry.iterator(); iterator.hasNext();) {
            GetBarDataResponse.DataBar.Entry next = iterator.next();
            if (next.getKey().equals("Einnahmen")) {
                chart.addSeries("Einnahme", Arrays.asList(new Integer[] { 0}), Arrays.asList(new BigDecimal[] { (BigDecimal.valueOf(next.getValue().doubleValue()).setScale(2, RoundingMode.HALF_UP))})).setFillColor(Color.green);
            } else if (next.getKey().equals("Ausgaben")) {
                chart.addSeries("Ausgabe", Arrays.asList(new Integer[] { 1}), Arrays.asList(new BigDecimal[] { (BigDecimal.valueOf(next.getValue().doubleValue()).setScale(2, RoundingMode.HALF_UP))})).setFillColor(Color.red);
            }
        }        
            
        BitmapEncoder.saveBitmapWithDPI(chart, "C:/xampp/htdocs/Barchart", BitmapEncoder.BitmapFormat.PNG, 300);
            
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page); 
        
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/xampp/htdocs/Barchart.png", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        contentStream.drawImage(pdImage, 0, 792-350, 400, 350);
        contentStream.close();
        
        document.save("C:/xampp/htdocs/Barchart.pdf");
        document.close();
        
        System.out.println("Barchart done");   
    
    }
    
}
