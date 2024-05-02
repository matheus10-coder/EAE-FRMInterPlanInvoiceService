package net.abcbs.eae.jaxrs;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import net.abcbs.issh.util.pub.common.IsSharedApplicationDataObject;
import net.abcbs.rpa.javabeans.InterPlanInvoiceJavaBean;
import net.abcbs.rpa.dto.*;

/***********************************************************************************************************************************************************************
 * @Author mfribeiro
 * 
 * Body for all the methods used for the REST Web service
 * 
 * Description: BluecardHostResource class is the application resource level which the main methods will be called in order to return the message to the user 
 * 
 * Project: FMR Inter Plan Invoice Service 
 ***********************************************************************************************************************************************************************/
@Path("/frm")
@OpenAPIDefinition(
		servers = {
				@Server(
					description = "localhost",
					url = "localhost:9080/RPAFRMInterPlanInvoiceService/resources"),
				@Server(
					description = "development",
					url = "https://isshareddev.abcbs.net/RPAFRMInterPlanInvoiceService/resources"),
				@Server(
						description = "test",
						url = "https://issharedtst.abcbs.net/RPAFRMInterPlanInvoiceService/resources"),
				@Server(
					description = "stage",
					url = "https://issharedstg.abcbs.net/RPAFRMInterPlanInvoiceService/resources"),
				@Server(
					description = "production",
					url = "https://isshared.abcbs.net/RPAFRMInterPlanInvoiceService/resources")
		})
@SecurityScheme(name = "basicAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "basic",
		description = "Username and Password are used for authorization")

public class RPAFRMInterPlanInvoiceResource {
	
    /**
     * Private method
     * 
     * Data object to get database information
     * 
     * Utilizing isSharedApplication class
     * 
     */
	private static Logger logger = LogManager.getLogger(RPAFRMInterPlanInvoiceResource.class);
	private static IsSharedApplicationDataObject isSharedApplicationDataObject = null;

	static {
		try {
			isSharedApplicationDataObject = new IsSharedApplicationDataObject(Constants.SYSTEM_NAME, Constants.AUTH_KEY, Constants.AUTH_PASS_PHRASE_DEV);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	/**
     * Public method
     * 
     * Method to output successful message from the server
     * 
     * Includes brief instruction on how to use this service
     * 
     * @return List value
     * 
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Service base endpoint that you can hit to get a response from the server",
			security = @SecurityRequirement(name = "basicAuth"),
			description = "A simple base endpoint that you can hit to get a response from the server",
			responses = {
					@ApiResponse(
							description = "JSON response",
							content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = JsonPayload.class))),
					@ApiResponse(responseCode = "401", description = "Credentials not authorized") }
	)	
	public String blueCardMessage(){
		return "{\"message\": \"FRM InterPlan Invoice service will accept in a request and respond with the appropriate information queried from the Blue2/ITS database. Db2jndiName: " + isSharedApplicationDataObject.getDb2JndiName() + ". Schema: " + isSharedApplicationDataObject.getDb2Schema() + ". Please refer to your specific endpoint. \"}";
	}
	
	
	/**
     * Public method
     * 
     * 
     * @return 
     */
	@POST
	@Path("/interplan-invoice/procjson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Purpose for this POST method is to consume json information from blue2ITS for each sccf#",
	security = @SecurityRequirement(name = "basicAuth"),
	description = "Returns a complete list of all the information requested from each sccf",
	responses = {
		@ApiResponse(
				description = "JSON response in a form of array",
				content = @Content(mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = InnerPlanInvoiceDTO.class)))),
		@ApiResponse(responseCode = "401", description = "Credentials not authorized") }
	)
	@JacksonFeatures(serializationEnable =  { SerializationFeature.INDENT_OUTPUT })
	
	public List<InnerPlanInvoiceDTO> prePayandPostPay (
			@Parameter(description = "It reads a json file with the current invoice request for pre pay or post paid",
			required = true) String jsonFile) {
		
		InterPlanInvoiceJavaBean interPlanInvoiceJavaBean = new InterPlanInvoiceJavaBean();
		
		try {
				//Call JsonReader and pass in the json file to be read
				JsonNode node = jsonReader(jsonFile);
			
				//Assign the values from JSON to local variables
				String type = node.get("type").asText().toLowerCase();
				String businessOwnerId = node.get("businessownerId").asText();
				
				//Assign Json node and create a local array list
				JsonNode sccfIdNode = node.get("sccfId");
				List<String> sccfId = createUniqueIdList (sccfIdNode);
				
				if(type.contains("prepay")) {
					
					return interPlanInvoiceJavaBean.complexPrePay("db2NodeDB","DB21ITS",sccfId,businessOwnerId);
			
					
				}
				//postpaid
				else {
					
					return interPlanInvoiceJavaBean.postPaid(isSharedApplicationDataObject.getDb2JndiName(),isSharedApplicationDataObject.getDb2Schema(),sccfId);
				}
				
		}
		catch (Exception e) {
			e.printStackTrace();
			return interPlanInvoiceJavaBean.exceptionInterPlanMessage();
		}
		
		
	}
	
	
	/**
	 * @POST
	 * @Path (/hostplan)
	 * 
	 */
	@POST
	@Path("/hostplan/procjson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Purpose for this POST method is to consume json information from blue2ITS for each sccf#",
	security = @SecurityRequirement(name = "basicAuth"),
	description = "Returns a complete list of all the information requested from each sccf",
	responses = {
		@ApiResponse(
				description = "JSON response in a form of array",
				content = @Content(mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = InnerPlanInvoiceDTO.class)))),
		@ApiResponse(responseCode = "401", description = "Credentials not authorized") }
	)
	@JacksonFeatures(serializationEnable =  { SerializationFeature.INDENT_OUTPUT })
	
	public List<QueryResultsDTO> getHostPlanClaimInfo(
	@Parameter(description = "It reads a json file with the current invoice request for current host claim id",
	required = true) String hostPlanInputJson) {
        InterPlanInvoiceJavaBean interPlanInvoiceJavaBean = new InterPlanInvoiceJavaBean();
        
        try {
        	JsonNode node = jsonReader(hostPlanInputJson);
        	JsonNode hostPlanNode = node.get("hostPlanId");
        	List<String> hostPlanList = createUniqueIdList (hostPlanNode);
        	return  interPlanInvoiceJavaBean.fetchHostClaimId("Db2NodeEDWDB", isSharedApplicationDataObject.getDb2Schema(), hostPlanList);
        	
        	
        }
        catch (Exception e) {
			e.printStackTrace();
			return interPlanInvoiceJavaBean.exceptionHostClaimIdMessage();
        }
        


    }
	
	
	
	/**
     * Public method
     * 
     * Performs credential validation 
     * 
     * If user gets a 401 they are unauthorized
     * 
     * @return JsonPayLoad type message 
     * 
     */
	@GET
	@Path("/test/authorization") 
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Simple test authorization method",
		security = @SecurityRequirement(name = "basicAuth"),
		description = "Test your credentials",
		responses = {
			@ApiResponse(
					description = "A method to simply test your credentials against the web service to see if you are authorized",
					content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Credentials not authorized")}
	)
	public JsonPayload testAuthorization() {
		JsonPayload payload = new JsonPayload();
		payload.setMessage("authorization valid");
		return payload;
	}
	
	
	public JsonNode jsonReader(String jsonFile) throws JsonMappingException, JsonProcessingException {
		
		//Create ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		//Parse JSON file into a JsonNode
		JsonNode jsonNode = objectMapper.readTree(jsonFile);
		
		return jsonNode;
	}
	
	
	
	public List<String> createUniqueIdList (JsonNode uniqueIdNode){
		List<String> uniqueIdList = new ArrayList<String>();
		
		//Assign Unique List (example: sccfId(s) or hostClaimId(s))
		if (uniqueIdNode.isArray()) {
			
			
			for (int i = 0; i < uniqueIdNode.size(); i++) {
				uniqueIdList.add(uniqueIdNode.get(i).asText());
			}
			
		}
		return uniqueIdList;
	}
	
	
	
	
}
