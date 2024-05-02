package net.abcbs.rpa.javabeans;
/***********************************************************************************************************************************************************************
 * @author ABCBS resource
 * 
 * Description: InterPlanInvoiceJavaBean class will be used to perform the proper connection with Oracle database and query the correct value required by the user 
 * 
 * Project: FRM InterPlan Invoice 
 ***********************************************************************************************************************************************************************/

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.*;

import net.abcbs.eae.jdbc.PropertiesLoader;
import net.abcbs.issh.util.pub.javabeans.IsSharedJavaBean;
import net.abcbs.rpa.dto.InnerPlanInvoiceDTO;
import net.abcbs.rpa.dto.QueryResultsDTO;



public class InterPlanInvoiceJavaBean extends IsSharedJavaBean {
	private static final String FROM = "FROM";
	private static Logger logger = LogManager.getLogger(InterPlanInvoiceJavaBean.class);
	
	PropertiesLoader jdbcprop = new PropertiesLoader();
	
	/**
	 * Name: PREPAY (Originals)
	 * Description: prePay original method created to perform only Claim Line search on 
	 * the SPEC PRICING 
	 * 
	 ***/
	public List<InnerPlanInvoiceDTO> prePay(String dataSource, String scheme, List<String> sccfId, String busOwnerId) {
		this.setDbFunctionDelete(dbFunctionDelete);
		ArrayList<InnerPlanInvoiceDTO> arrayList = new ArrayList<>();
		String sqlPrePaidManipulationStatement = "SCCF_ID IN";
		
		try {
			this.initializeConnection(dataSource, "");
			 
			sqlStatement.append("SELECT SCCF_ID");//1
			sqlStatement.append(", LN_NUM");//2
			sqlStatement.append(", SPCL_PRC_CD_1");//3
			sqlStatement.append(", SPCL_PRC_CD_2");//4
			sqlStatement.append(", SPCL_PRC_CD_3");//5
			sqlStatement.append(", SPCL_PRC_CD_4");//6
			sqlStatement.append(", SPCL_PRC_CD_5");//7
			sqlStatement.append(", SPCL_PRC_AMT_1");//8
			sqlStatement.append(", SPCL_PRC_AMT_2");//9
			sqlStatement.append(", SPCL_PRC_AMT_3");//10
			sqlStatement.append(", SPCL_PRC_AMT_4");//11
			sqlStatement.append(", SPCL_PRC_AMT_5");//12
			
			
			sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_LN");
			sqlStatement.append(" WHERE " + createStatement(sccfId,sqlPrePaidManipulationStatement) + " AND BUS_OWNER_ID = ? AND (SPCL_PRC_CD_1 <> '')");
			sqlStatement.append(" WITH UR");
			
			
			
			preparedStatement = connection.prepareStatement(sqlStatement.toString());

			 
			//Loop through the values to add sccf id in the IN key word
			for (int i = 0; i < sccfId.size(); i++) {
				preparedStatement.setString(i+1,sccfId.get(i)); //Parameter indices start at 1
			
			}
			preparedStatement.setString(sccfId.size() + 1, busOwnerId);
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			while (resultSet.next()) {
				InnerPlanInvoiceDTO interPlanInvoiceOutput = new InnerPlanInvoiceDTO();

				interPlanInvoiceOutput.setSccfId(resultSet.getString(1));
				interPlanInvoiceOutput.setLineNumber(resultSet.getString(2));
				interPlanInvoiceOutput.setSpclPriceCd1(resultSet.getString(3));
				interPlanInvoiceOutput.setSpclPriceCd2(resultSet.getString(4));
				interPlanInvoiceOutput.setSpclPriceCd3(resultSet.getString(5));
				interPlanInvoiceOutput.setSpclPriceCd4(resultSet.getString(6));
				interPlanInvoiceOutput.setSpclPriceCd5(resultSet.getString(7));
				interPlanInvoiceOutput.setSpclPriceAmt1(resultSet.getString(8));
				interPlanInvoiceOutput.setSpclPriceAmt2(resultSet.getString(9));
				interPlanInvoiceOutput.setSpclPriceAmt3(resultSet.getString(10));
				interPlanInvoiceOutput.setSpclPriceAmt4(resultSet.getString(11));
				interPlanInvoiceOutput.setSpclPriceAmt5(resultSet.getString(12));
				
				
				
				arrayList.add(interPlanInvoiceOutput);
			}
		}
		catch (SQLException se) {
			this.processException(se);
		}
		catch (Exception e) {
			this.processException(e);
		}
		finally {
			sqlStatement.delete(0, sqlStatement.length());
			displayResults();
			this.closeConnections();
			
		}

		return arrayList;
	}
	
	/**
	 * Name: PREPAY CLAIM SUBMISSION
	 * Description: prePayClaimSubm method created to perform only Claim Submission search
	 * 
	 ***/
	public List<InnerPlanInvoiceDTO> prePayClaimSubm(String dataSource, String scheme, List<String> sccfId, String busOwnerId) {
		this.setDbFunctionDelete(dbFunctionDelete);
		ArrayList<InnerPlanInvoiceDTO> arrayList = new ArrayList<>();
		String sqlPrePaidManipulationStatement = "SCCF_ID IN";
		
		try {
			this.initializeConnection(dataSource, "");
			 
			sqlStatement.append("SELECT SCCF_ID");//1
			sqlStatement.append(", 0 as LN_NUM");//2
			sqlStatement.append(", SPCL_PRC_CD_1");//3
			sqlStatement.append(", SPCL_PRC_CD_2");//4
			sqlStatement.append(", SPCL_PRC_CD_3");//5
			sqlStatement.append(", SPCL_PRC_CD_4");//6
			sqlStatement.append(", SPCL_PRC_CD_5");//7
			sqlStatement.append(", SPCL_PRC_AMT_1");//8
			sqlStatement.append(", SPCL_PRC_AMT_2");//9
			sqlStatement.append(", SPCL_PRC_AMT_3");//10
			sqlStatement.append(", SPCL_PRC_AMT_4");//11
			sqlStatement.append(", SPCL_PRC_AMT_5");//12
			
			
			sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_SUBM");
			sqlStatement.append(" WHERE " + createStatement(sccfId,sqlPrePaidManipulationStatement) + " AND BUS_OWNER_ID = ? AND (SPCL_PRC_CD_1 <> '')");
			sqlStatement.append(" WITH UR");
			
			
			
			preparedStatement = connection.prepareStatement(sqlStatement.toString());

			 
			//Loop through the values to add sccf id in the IN key word
			for (int i = 0; i < sccfId.size(); i++) {
				preparedStatement.setString(i+1,sccfId.get(i)); //Parameter indices start at 1
			
			}
			preparedStatement.setString(sccfId.size() + 1, busOwnerId);
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			while (resultSet.next()) {
				InnerPlanInvoiceDTO interPlanInvoiceOutput = new InnerPlanInvoiceDTO();

				interPlanInvoiceOutput.setSccfId(resultSet.getString(1));
				interPlanInvoiceOutput.setLineNumber("n/a");
				interPlanInvoiceOutput.setSpclPriceCd1(resultSet.getString(3));
				interPlanInvoiceOutput.setSpclPriceCd2(resultSet.getString(4));
				interPlanInvoiceOutput.setSpclPriceCd3(resultSet.getString(5));
				interPlanInvoiceOutput.setSpclPriceCd4(resultSet.getString(6));
				interPlanInvoiceOutput.setSpclPriceCd5(resultSet.getString(7));
				interPlanInvoiceOutput.setSpclPriceAmt1(resultSet.getString(8));
				interPlanInvoiceOutput.setSpclPriceAmt2(resultSet.getString(9));
				interPlanInvoiceOutput.setSpclPriceAmt3(resultSet.getString(10));
				interPlanInvoiceOutput.setSpclPriceAmt4(resultSet.getString(11));
				interPlanInvoiceOutput.setSpclPriceAmt5(resultSet.getString(12));
				
				
				
				arrayList.add(interPlanInvoiceOutput);
			}
		}
		catch (SQLException se) {
			this.processException(se);
		}
		catch (Exception e) {
			this.processException(e);
		}
		finally {
			displayResults();
			this.closeConnections();
		}

		return arrayList;
	}
	
	
	/**
	 * Name: COMPLEX PREPAY
	 * Description: complex prePay has been design to handle all the complexity of search the spec pricing of a claim at first
	 * LINE level or then SUBMISSION level. Several scenarions have been taken into considerations such as when null has been 
	 * encountered we will have search on both tables to finally identify that claim as null. This is a complex method that 
	 * Unit Testing has been applied manually
	 * 
	 ***/
	public List<InnerPlanInvoiceDTO> complexPrePay(String dataSource, String scheme, List<String> origSccfList, String busOwnerId) {
		this.setDbFunctionDelete(dbFunctionDelete);
		ArrayList<InnerPlanInvoiceDTO> prePayResults = new ArrayList<>();
		ArrayList<String> newSccfList = new ArrayList<>();
		ArrayList<String> comparedSccfList = new ArrayList<>();
		
		
		String sqlPrePaidManipulationStatement = "SCCF_ID IN";
		
		
		try {
			this.initializeConnection(dataSource, "");
			
			//CLAIM LINE LEVEL RUN
			sqlStatement.append("SELECT SCCF_ID");//1
			sqlStatement.append(", LN_NUM");//2
			sqlStatement.append(", SPCL_PRC_CD_1");//3
			sqlStatement.append(", SPCL_PRC_CD_2");//4
			sqlStatement.append(", SPCL_PRC_CD_3");//5
			sqlStatement.append(", SPCL_PRC_CD_4");//6
			sqlStatement.append(", SPCL_PRC_CD_5");//7
			sqlStatement.append(", SPCL_PRC_AMT_1");//8
			sqlStatement.append(", SPCL_PRC_AMT_2");//9
			sqlStatement.append(", SPCL_PRC_AMT_3");//10
			sqlStatement.append(", SPCL_PRC_AMT_4");//11
			sqlStatement.append(", SPCL_PRC_AMT_5");//12
			
			sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_LN");
			sqlStatement.append(" WHERE " + createStatement(origSccfList,sqlPrePaidManipulationStatement) + " AND BUS_OWNER_ID = ? AND (SPCL_PRC_CD_1 <> '')");
			sqlStatement.append(" WITH UR");
			
			preparedStatement = connection.prepareStatement(sqlStatement.toString());

			 
			//Loop through the values to add sccf id in the IN key word
			for (int i = 0; i < origSccfList.size(); i++) {
				preparedStatement.setString(i+1,origSccfList.get(i)); //Parameter indices start at 1
			
			}
			preparedStatement.setString(origSccfList.size() + 1, busOwnerId);
			
			//VERY FIRST RUN
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				InnerPlanInvoiceDTO prePayOutput = new InnerPlanInvoiceDTO();

				prePayOutput.setSccfId(resultSet.getString(1));
				prePayOutput.setLineNumber(resultSet.getString(2));
				prePayOutput.setSpclPriceCd1(resultSet.getString(3));
				prePayOutput.setSpclPriceCd2(resultSet.getString(4));
				prePayOutput.setSpclPriceCd3(resultSet.getString(5));
				prePayOutput.setSpclPriceCd4(resultSet.getString(6));
				prePayOutput.setSpclPriceCd5(resultSet.getString(7));
				prePayOutput.setSpclPriceAmt1(resultSet.getString(8));
				prePayOutput.setSpclPriceAmt2(resultSet.getString(9));
				prePayOutput.setSpclPriceAmt3(resultSet.getString(10));
				prePayOutput.setSpclPriceAmt4(resultSet.getString(11));
				prePayOutput.setSpclPriceAmt5(resultSet.getString(12));
				
				
				newSccfList.add(prePayOutput.getSccfId());
				prePayResults.add(prePayOutput);
			}
			
			//CLAIM SUBMISSION LEVEL RUN
			if(prePayResults.size() == 0) {
				//Cleaning the sql statement
				sqlStatement.delete(0, sqlStatement.length());
				
				sqlStatement.append("SELECT SCCF_ID");//1
				sqlStatement.append(", 0 as LN_NUM");//2
				sqlStatement.append(", SPCL_PRC_CD_1");//3
				sqlStatement.append(", SPCL_PRC_CD_2");//4
				sqlStatement.append(", SPCL_PRC_CD_3");//5
				sqlStatement.append(", SPCL_PRC_CD_4");//6
				sqlStatement.append(", SPCL_PRC_CD_5");//7
				sqlStatement.append(", SPCL_PRC_AMT_1");//8
				sqlStatement.append(", SPCL_PRC_AMT_2");//9
				sqlStatement.append(", SPCL_PRC_AMT_3");//10
				sqlStatement.append(", SPCL_PRC_AMT_4");//11
				sqlStatement.append(", SPCL_PRC_AMT_5");//12
				
				
				sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_SUBM");
				sqlStatement.append(" WHERE " + createStatement(origSccfList,sqlPrePaidManipulationStatement) + " AND BUS_OWNER_ID = ? AND (SPCL_PRC_CD_1 <> '')");
				sqlStatement.append(" WITH UR");
				
				preparedStatement = connection.prepareStatement(sqlStatement.toString());

				 
				//Loop through the values to add sccf id in the IN key word
				for (int i = 0; i < origSccfList.size(); i++) {
					preparedStatement.setString(i+1,origSccfList.get(i)); //Parameter indices start at 1
				
				}
				
				preparedStatement.setString(origSccfList.size() + 1, busOwnerId);
				//SECOND RUN
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					InnerPlanInvoiceDTO prePayOutputII = new InnerPlanInvoiceDTO();
					
					prePayOutputII.setSccfId(resultSet.getString(1));
					prePayOutputII.setLineNumber("n/a");
					prePayOutputII.setSpclPriceCd1(resultSet.getString(3));
					prePayOutputII.setSpclPriceCd2(resultSet.getString(4));
					prePayOutputII.setSpclPriceCd3(resultSet.getString(5));
					prePayOutputII.setSpclPriceCd4(resultSet.getString(6));
					prePayOutputII.setSpclPriceCd5(resultSet.getString(7));
					prePayOutputII.setSpclPriceAmt1(resultSet.getString(8));
					prePayOutputII.setSpclPriceAmt2(resultSet.getString(9));
					prePayOutputII.setSpclPriceAmt3(resultSet.getString(10));
					prePayOutputII.setSpclPriceAmt4(resultSet.getString(11));
					prePayOutputII.setSpclPriceAmt5(resultSet.getString(12));
					
					//Creating the list with the available sccfs that returned from resultSet
					newSccfList.add(prePayOutputII.getSccfId());
					prePayResults.add(prePayOutputII);
					
				}
				
				
				/**
				 * add the NULL check point here; This means the DB has returned no results for the original list of SCCFs or if there was any result that was not found in the second run
				 * */
				comparedSccfList = (ArrayList<String>) listOfStringCompare(origSccfList,newSccfList);
				//Check for null at the claim submission level
				if(prePayResults.size() == 0 || comparedSccfList.size() != 0) {
					
					if (comparedSccfList.size()!= 0 && comparedSccfList.size() != origSccfList.size()) {
						System.out.println("Query ran successfully on the database for both tables (Claim Line and Submission level) following the total row(s) returned on Claim Submission level: " + prePayResults.size() + ". Here's the number of null row(s): " + comparedSccfList.size());
						for (int i = 0; i < comparedSccfList.size(); i++) {
							InnerPlanInvoiceDTO nullOutput = new InnerPlanInvoiceDTO();
							//Null only for the submission claims 
							nullOutput.setSccfId(comparedSccfList.get(i));
							nullOutput.setLineNumber("n/a");
							nullOutput.setSpclPriceCd1("n/a");
							nullOutput.setSpclPriceCd2("n/a");
							nullOutput.setSpclPriceCd3("n/a");
							nullOutput.setSpclPriceCd4("n/a");
							nullOutput.setSpclPriceCd5("n/a");
							nullOutput.setSpclPriceAmt1("n/a");
							nullOutput.setSpclPriceAmt2("n/a");
							nullOutput.setSpclPriceAmt3("n/a");
							nullOutput.setSpclPriceAmt4("n/a");
							nullOutput.setSpclPriceAmt5("n/a");
							
							prePayResults.add(nullOutput);
						}
					}
					//All null values
					else{
						System.out.println("Query ran successfully on the database for both tables (Claim Line and Submission) but no values returned of those sccf IDs passed by the user for any tables. Total numbers of rows: " + prePayResults.size());
						
						for (int i = 0; i < origSccfList.size(); i++) {
							InnerPlanInvoiceDTO nullOutput = new InnerPlanInvoiceDTO();
							//NULL for all the cases
							nullOutput.setSccfId(origSccfList.get(i));
							nullOutput.setLineNumber("n/a");
							nullOutput.setSpclPriceCd1("n/a");
							nullOutput.setSpclPriceCd2("n/a");
							nullOutput.setSpclPriceCd3("n/a");
							nullOutput.setSpclPriceCd4("n/a");
							nullOutput.setSpclPriceCd5("n/a");
							nullOutput.setSpclPriceAmt1("n/a");
							nullOutput.setSpclPriceAmt2("n/a");
							nullOutput.setSpclPriceAmt3("n/a");
							nullOutput.setSpclPriceAmt4("n/a");
							nullOutput.setSpclPriceAmt5("n/a");
							
							prePayResults.add(nullOutput);
						}
					}
					
					
					
					
				}
				else {
					System.out.println(" All values have returned successfully from the database only retriving information from Claim Submission table. Total numbers of rows: " + prePayResults.size());
				}
				
				
			}
			else {
				/**
				 * This will execute only if the first try was success which means we had some values found in the DB, if so the method below needs to check 
				 * if the resultSet in the array list match with all the sccf in the original list if not we need to create a new list only with the values left
				 * SCCF LIST CHECK - This will try to map the original sccf list with the list we have on the arrayList to make sure we have the same
				 * THEN We will retrieve that list and run 
				 */
				comparedSccfList = (ArrayList<String>) listOfStringCompare(origSccfList,newSccfList);
				//Check for the remaining values to on the submission level
				if(comparedSccfList.size() != 0) {
					
					//Cleaning the sql statement
					sqlStatement.delete(0, sqlStatement.length());
					
					sqlStatement.append("SELECT SCCF_ID");//1
					sqlStatement.append(", 0 as LN_NUM");//2
					sqlStatement.append(", SPCL_PRC_CD_1");//3
					sqlStatement.append(", SPCL_PRC_CD_2");//4
					sqlStatement.append(", SPCL_PRC_CD_3");//5
					sqlStatement.append(", SPCL_PRC_CD_4");//6
					sqlStatement.append(", SPCL_PRC_CD_5");//7
					sqlStatement.append(", SPCL_PRC_AMT_1");//8
					sqlStatement.append(", SPCL_PRC_AMT_2");//9
					sqlStatement.append(", SPCL_PRC_AMT_3");//10
					sqlStatement.append(", SPCL_PRC_AMT_4");//11
					sqlStatement.append(", SPCL_PRC_AMT_5");//12
					
					
					sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_SUBM");
					sqlStatement.append(" WHERE " + createStatement(comparedSccfList,sqlPrePaidManipulationStatement) + " AND BUS_OWNER_ID = ? AND (SPCL_PRC_CD_1 <> '')");
					sqlStatement.append(" WITH UR");
					
					preparedStatement = connection.prepareStatement(sqlStatement.toString());

					 
					//Loop through the values to add sccf id in the IN key word
					for (int i = 0; i < comparedSccfList.size(); i++) {
						preparedStatement.setString(i+1,comparedSccfList.get(i)); //Parameter indices start at 1
					
					}
					
					preparedStatement.setString(comparedSccfList.size() + 1, busOwnerId);
					//VERY SECOND RUN FOR THE REMAING VALUES
					resultSet = preparedStatement.executeQuery();
					
					while (resultSet.next()) {
					
						InnerPlanInvoiceDTO prePayOutputIII = new InnerPlanInvoiceDTO();
						prePayOutputIII.setSccfId(resultSet.getString(1));
						prePayOutputIII.setLineNumber("n/a");
						prePayOutputIII.setSpclPriceCd1(resultSet.getString(3));
						prePayOutputIII.setSpclPriceCd2(resultSet.getString(4));
						prePayOutputIII.setSpclPriceCd3(resultSet.getString(5));
						prePayOutputIII.setSpclPriceCd4(resultSet.getString(6));
						prePayOutputIII.setSpclPriceCd5(resultSet.getString(7));
						prePayOutputIII.setSpclPriceAmt1(resultSet.getString(8));
						prePayOutputIII.setSpclPriceAmt2(resultSet.getString(9));
						prePayOutputIII.setSpclPriceAmt3(resultSet.getString(10));
						prePayOutputIII.setSpclPriceAmt4(resultSet.getString(11));
						prePayOutputIII.setSpclPriceAmt5(resultSet.getString(12));
						
						//Creating the list with the available sccfs that returned from resultSet
						newSccfList.add(prePayOutputIII.getSccfId());
						prePayResults.add(prePayOutputIII);
					}
					//check null for the remaining values from the submission level - reassign it
					comparedSccfList = (ArrayList<String>) listOfStringCompare(comparedSccfList,newSccfList);
					if (comparedSccfList.size()!= 0) {
						
						
						for (int i = 0; i < comparedSccfList.size(); i++) {
							InnerPlanInvoiceDTO nullOutput = new InnerPlanInvoiceDTO();
							
							nullOutput.setSccfId(comparedSccfList.get(i));
							nullOutput.setLineNumber("n/a");
							nullOutput.setSpclPriceCd1("n/a");
							nullOutput.setSpclPriceCd2("n/a");
							nullOutput.setSpclPriceCd3("n/a");
							nullOutput.setSpclPriceCd4("n/a");
							nullOutput.setSpclPriceCd5("n/a");
							nullOutput.setSpclPriceAmt1("n/a");
							nullOutput.setSpclPriceAmt2("n/a");
							nullOutput.setSpclPriceAmt3("n/a");
							nullOutput.setSpclPriceAmt4("n/a");
							nullOutput.setSpclPriceAmt5("n/a");
							
							prePayResults.add(nullOutput);
						}
						
						System.out.println("Query ran successfully on the database for both tables (Claim Line and Submission). Total rows that returned valid values: " + newSccfList.size() + ". Total rows that returned null: " + comparedSccfList.size());
					}
					else {
						
						System.out.println(" All values have returned successfully from the database retriving information from Claim Line and Claim Submission tables. Total numbers of rows: " + prePayResults.size());
						
					}
					
					
					
			
				}
				else {
					System.out.println(" All values have returned successfully from the database only retriving information from Claim Line table. Total numbers of rows: " + prePayResults.size());
				}
				
			}
				
		}
		
		
		catch (SQLException se) {
			this.processException(se);
		}
		catch (Exception e) {
			this.processException(e);
		}
		finally {
			sqlStatement.delete(0, sqlStatement.length());
			displayResults();
			this.closeConnections();
			
		}

		return prePayResults;
	}
	
	
	
	/**
	 * 
	 * Method Name: postPaid
	 * Method purpose: Retrieve the post paid claim information to the user
	 * 
	 * */
	
	public List<InnerPlanInvoiceDTO> postPaid(String dataSource, String scheme, List<String> sccfId) {
		this.setDbFunctionDelete(dbFunctionDelete);
		ArrayList<InnerPlanInvoiceDTO> arrayList = new ArrayList<>();
		String sqlPostPaidManipulationStatement = "SUBSTR(SCCF_ID,1,15) IN";
		
		try {
			this.initializeConnection(dataSource, "");
			 
			sqlStatement.append("SELECT SCCF_ID");//1
			sqlStatement.append(",  DISP_CD");//2
			sqlStatement.append(",  TOT_APPV_PMT_AMT");//3
			sqlStatement.append(",  BUS_OWNER_ID");//4
			
			
			
			sqlStatement.append(" "+ FROM +" " + scheme + ".CLM_DISP");
			
			sqlStatement.append(" WHERE " + createStatement(sccfId,sqlPostPaidManipulationStatement) + " AND DISP_CD = '1' WITH UR");
			logger.info("SQL: {}",sqlStatement);		
			
			
			preparedStatement = connection.prepareStatement(sqlStatement.toString());
			
			//Loop to setString from each value of sccfId
			for (int i = 0; i < sccfId.size(); i++) {
				preparedStatement.setString(i+1,sccfId.get(i)); //Parameter indices start at 1
			
			}
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			
			while (resultSet.next()) {
				InnerPlanInvoiceDTO interPlanInvoiceOutput = new InnerPlanInvoiceDTO();

				interPlanInvoiceOutput.setSccfId(resultSet.getString(1));
				interPlanInvoiceOutput.setDispCd(resultSet.getString(2));
				interPlanInvoiceOutput.setTotalAppPmtAmt(resultSet.getString(3));
				interPlanInvoiceOutput.setBusOwnerId(resultSet.getString(4));
				
				
				arrayList.add(interPlanInvoiceOutput);
			}
		}
		catch (SQLException se) {
			this.processException(se);
		}
		catch (Exception e) {
			this.processException(e);
		}
		finally {
			displayResults();
			this.closeConnections();
		}

		return arrayList;
	}
	
	
	
	/** 
	 * 
	 * Method Name: fetchHostClaimId
	 * Method purpose: Run query against edwprd(*) db in order to get result set for RPA bot's 
	 *   
	 * */
	public ArrayList<QueryResultsDTO> fetchHostClaimId(String dataSource, String scheme, List<String> hostClaim) {

		this.setDbFunctionSelect(true);
		ArrayList<QueryResultsDTO> arrayList = new ArrayList<>();
		String sqlManipulation = "ITS.OTHER_PLAN_CLAIM_ID IN";
		
		
		/*String jdbcClassName=jdbcprop.loadJDBCProperties("db.classname");
        String url=jdbcprop.loadJDBCProperties("db.url.tst");
        String user=jdbcprop.loadJDBCProperties("db.username");
        String password=jdbcprop.loadJDBCProperties("db.password");

 

		
          System.out.println("before try-catch");
		    try {
		        System.out.println("try");

		        //Load class into memory
		        Class.forName(jdbcClassName);
		        //Establish connection
		        System.out.println("before conn");
		        connection = DriverManager.getConnection(url, user, password);
		        System.out.println("after conn");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		        if(connection!=null){
		            System.out.println("Connected successfully.");*/


		        try {
		        	
					this.initializeConnection(dataSource, "JNDI/");
					System.out.println("Connection opened - ClientUser: " + connection.getClientInfo().toString());
					sqlStatement.append(" SELECT DISTINCT ITS.OTHER_PLAN_CLAIM_ID");
					sqlStatement.append(" , ITS.ARBCBS_CLAIM_ID");
					sqlStatement.append(" , ITS.SERV_PROV_NAME");
					sqlStatement.append(" , PAR.FULL_NAME");
					sqlStatement.append(" , MBR.CONTRACT_ID");
					sqlStatement.append(" , GRP.GRP_ID");
					sqlStatement.append(" , GRP.GRP_NAME");
					sqlStatement.append(" , SVC.SUB_GRP_ID_ORIG");
					sqlStatement.append(" , SVC.SRC_CARRIER_CD");
					sqlStatement.append(" , MBR.SPAN_CHG_DT");
					sqlStatement.append(" , ITS.LOAD_DT");
					sqlStatement.append(" ,  MBR.SPAN_EFF_DT");
					sqlStatement.append(" , MBR.GRP_KEY");
					sqlStatement.append(" , GRP.GRP_KEY");
					sqlStatement.append(" "+ FROM +" " + "TRFM.ITS_SF_CLM_PROV ITS");
				    sqlStatement.append(" , IDS.SVC_LN_TRAN SVC");
				    sqlStatement.append(" , IDS.MBR MBR");
				    sqlStatement.append(" , IDS.GRP GRP");
				    sqlStatement.append(" , IDS.PARTY PAR");
					sqlStatement.append(" WHERE  " + "ITS.ARBCBS_CLAIM_ID=SVC.CLAIM_ID");
					sqlStatement.append(" AND  " + "MBR.GRP_KEY=GRP.GRP_KEY");
					sqlStatement.append(" AND  " + "SVC.PAT_PARTY_KEY=PAR.PARTY_KEY");
					sqlStatement.append(" AND  " + "SVC.PAT_PARTY_KEY=MBR.PARTY_KEY");
					sqlStatement.append(" AND  " + "SVC.PAT_MBR_KEY=MBR.MBR_KEY");
					sqlStatement.append(" AND  " + createStatement(hostClaim, sqlManipulation));
					preparedStatement = connection.prepareStatement(sqlStatement.toString());
					
					//Loop to setString from each value of sccfId
					for (int i = 0; i < hostClaim.size(); i++) {
						preparedStatement.setString(i+1,hostClaim.get(i)); //Parameter indices start at 1
					
					}
					
					
					resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						QueryResultsDTO dbRecord = new QueryResultsDTO();
						
						dbRecord.setOtherPlanClaimId(resultSet.getString(1));
						dbRecord.setAbcbsClaimId(resultSet.getString(2));
						dbRecord.setServProvName(resultSet.getString(3));
						dbRecord.setFullName(resultSet.getString(4));
						dbRecord.setContractId(resultSet.getString(5));
						dbRecord.setGrpId(resultSet.getString(6));
						dbRecord.setGrpName(resultSet.getString(7));
						dbRecord.setSubGrpIdOrig(resultSet.getString(8));
						dbRecord.setSrcCarrierCd(resultSet.getString(9));
						dbRecord.setSpanChgDT(resultSet.getDate(10).toString().toString());
						dbRecord.setLoadDT(resultSet.getDate(11).toString());
						dbRecord.setSpanEffDT(resultSet.getDate(12).toString());
						dbRecord.setMemberGrpKey(String.valueOf(resultSet.getInt(13)));
						dbRecord.setGroupGrpKey(String.valueOf(resultSet.getInt(14)));
						
					 
						arrayList.add(dbRecord);
					}
					//connection.close();
					 
				}
				catch (SQLException se) {
					this.processException(se);
				}
				catch (Exception e) {
					this.processException(e);
				}
				finally {
					displayResults();
					System.out.println("Connection successfully closed");
					this.closeConnections();
				}
				

		    return arrayList;
	}
	
	
	/**
	 * Methods created to support JAVABEAN class
	 * Name: createStatement
	 * Description: Create the SQL statement according to the sccf list entered by the user
	 * **/
	private String createStatement(List<String> sccfIdList, String sqlStatement) {
		sqlStatement += " ( ";
		
		for (int i=0; i < sccfIdList.size(); i++) {
			sqlStatement += "?"; //Add a placeholder for each value
			if (i < sccfIdList.size() - 1) {
				sqlStatement += ", "; //Add a comma if it's not the last value
			}
		}
		
		sqlStatement += " )";
		return sqlStatement;
	}
	/**
	 * Methods created to support JAVABEAN class
	 * Name: exceptionMessage
	 * Description: Create a exception message for the different scenarios
	 * 
	 * **/
	public List<InnerPlanInvoiceDTO> exceptionInterPlanMessage (){
		ArrayList<InnerPlanInvoiceDTO> arrayList = new ArrayList<>();
	
		InnerPlanInvoiceDTO errorMessage = new InnerPlanInvoiceDTO();
		
		errorMessage.setError("\"Error processing current request\"");
		
		arrayList.add(errorMessage);
		return arrayList;
	}
	/**
	 * Methods created to support JAVABEAN class
	 * Name: exceptionMessage
	 * Description: Create a exception message for the different scenarios
	 * 
	 * **/
	public List<InnerPlanInvoiceDTO> exceptionLoadConfigFileMessage (){
		ArrayList<InnerPlanInvoiceDTO> arrayList = new ArrayList<>();
	
		InnerPlanInvoiceDTO errorMessage = new InnerPlanInvoiceDTO();
		
		errorMessage.setError("\"Error during loading jdbc object\"");
		
		arrayList.add(errorMessage);
		return arrayList;
	}
	/**
	 * Methods created to support JAVABEAN class
	 * Name: exceptionMessage
	 * Description: Create a exception message for the different scenarios
	 * 
	 * **/
	public List<QueryResultsDTO> exceptionHostClaimIdMessage (){
		ArrayList<QueryResultsDTO> arrayList = new ArrayList<>();
		
		QueryResultsDTO errorMessage = new QueryResultsDTO();
		errorMessage.setError("\"Error processing host claim id info request\"");
		arrayList.add(errorMessage);
		return arrayList;
	}
	
	/**
	 * Methods created to support JAVABEAN class
	 * Name: List of Compare
	 * Description: Create a list that compare two other lists and 
	 * return the values that are different from the orig list from the result set list
	 * 
	 * **/
	public List<String> listOfStringCompare (List<String> sccfIdOrig, List<String> sccfIdResultSet) {
		//Compare SCCFS
		List<String> newSccfList = new ArrayList<>(sccfIdOrig);
		//Add the values not present in sccfList but present in original list
		newSccfList.removeAll(sccfIdResultSet);
		
		return newSccfList;
	
	}
	/**
	 * Methods created to support JAVABEAN class
	 * Name: Add Unique Element
	 * Description: It will add unique elements 
	 * and not allowing duplicated elements to be added to the list
	 * **/
	public static void addUniqueElements (ArrayList<String> sccfList, String element) {
		if (!sccfList.contains(element)) {
			sccfList.add(element);
		}
	}
	
	
	

}


