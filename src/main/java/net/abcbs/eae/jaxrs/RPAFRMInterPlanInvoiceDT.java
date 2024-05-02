package net.abcbs.eae.jaxrs;
import java.util.ArrayList;
import java.util.*;
/***********************************************************************************************************************************************************************
 * @author mfribeiro
 * 
 * Description: RPAFRMInterPlanInvoiceDT class is used to manually test the web service call to retrieve values out of a data table
 * 
 * Project: FRM InterPlan Invoice
 ***********************************************************************************************************************************************************************/
public class RPAFRMInterPlanInvoiceDT {
	
	public List<RPAFRMInterPlanInvoiceMessage> getDBServiceLines(){
		RPAFRMInterPlanInvoiceMessage servLn1 = new RPAFRMInterPlanInvoiceMessage("COVID19",1);
		RPAFRMInterPlanInvoiceMessage servLn2 = new RPAFRMInterPlanInvoiceMessage("ITS",2);
		RPAFRMInterPlanInvoiceMessage servLn3 = new RPAFRMInterPlanInvoiceMessage("N/A",3);
		
		List<RPAFRMInterPlanInvoiceMessage> serviceLinesLs = new ArrayList<>();
		serviceLinesLs.add(servLn1);
		serviceLinesLs.add(servLn2);
		serviceLinesLs.add(servLn3);
		return serviceLinesLs;
	}

}
