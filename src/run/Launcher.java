package run;


import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;
import ws.PDFGenerator;

public class Launcher{
    
    public static void main(String[] args) {
        Endpoint endpointPDF = Endpoint.publish( "http://localhost:1234/services", new PDFGenerator());
        
        JOptionPane.showMessageDialog( null, "Server beenden" );
        endpointPDF.stop();
    }
    
}
