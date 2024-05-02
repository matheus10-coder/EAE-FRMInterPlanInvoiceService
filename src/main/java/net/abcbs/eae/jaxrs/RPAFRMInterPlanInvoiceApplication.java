package net.abcbs.eae.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/***********************************************************************************************************************************************************************
 * @Author ABCBS Resource
 * 
 * Description: RPAFRMInterPlanInvoiceApplication class will be used as the application driver
 * 
 * Project: FRM InterPlan Invoice
 ***********************************************************************************************************************************************************************/
@ApplicationPath("resources")
public class RPAFRMInterPlanInvoiceApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(RPAFRMInterPlanInvoiceResource.class);
		return classes;	
	}
}