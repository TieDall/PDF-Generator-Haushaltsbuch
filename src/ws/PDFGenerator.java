
package ws;

import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import logic.PDF;

@WebService(name = "PDFGenerator")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PDFGenerator {
        
    @WebMethod(operationName = "createPieChart")
    public void createPiePDF() throws IOException {
        PDF.generatePiechart();
    }
    
    @WebMethod(operationName = "createBarChart")
    public void createBarPDF() throws IOException {
        PDF.generateBarchart();
    }
    
}
