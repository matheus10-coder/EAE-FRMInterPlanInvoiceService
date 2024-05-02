package net.abcbs.rpa.dto;

import java.util.List;

/***********************************************************************************************************************************************************************
 * @author mfribeiro
 * 
 * Description: InnerPlanInvoiceDTO class used to perform core task such as create and construct the object used in InterPlanInvoiceJavaBean
 * 
 * Project: FRM InterPlan Invoice
 ***********************************************************************************************************************************************************************/

public class InnerPlanInvoiceDTO {
	
	//private variables
	private String sccfId;
	private String lineNumber;
	private String spclPriceCd1;
	private String spclPriceCd2;
	private String spclPriceCd3;
	private String spclPriceCd4;
	private String spclPriceCd5;
	private String spclPriceAmt1;
	private String spclPriceAmt2;
	private String spclPriceAmt3;
	private String spclPriceAmt4;
	private String spclPriceAmt5;
	private String dispCd;
	private String totalAppPmtAmt; 
	private String busOwnerId;
	private String error;
	
	//getters and setters
	public String getSccfId() {
		return sccfId;
	}
	public void setSccfId(String sccfId) {
		this.sccfId = sccfId;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getSpclPriceCd1() {
		return spclPriceCd1;
	}
	public void setSpclPriceCd1(String spclPriceCd1) {
		this.spclPriceCd1 = spclPriceCd1;
	}
	public String getSpclPriceCd2() {
		return spclPriceCd2;
	}
	public void setSpclPriceCd2(String spclPriceCd2) {
		this.spclPriceCd2 = spclPriceCd2;
	}
	public String getSpclPriceCd3() {
		return spclPriceCd3;
	}
	public void setSpclPriceCd3(String spclPriceCd3) {
		this.spclPriceCd3 = spclPriceCd3;
	}
	public String getSpclPriceCd4() {
		return spclPriceCd4;
	}
	public void setSpclPriceCd4(String spclPriceCd4) {
		this.spclPriceCd4 = spclPriceCd4;
	}
	public String getSpclPriceCd5() {
		return spclPriceCd5;
	}
	public void setSpclPriceCd5(String spclPriceCd5) {
		this.spclPriceCd5 = spclPriceCd5;
	}
	public String getSpclPriceAmt1() {
		return spclPriceAmt1;
	}
	public void setSpclPriceAmt1(String spclPriceAmt1) {
		this.spclPriceAmt1 = spclPriceAmt1;
	}
	public String getSpclPriceAmt2() {
		return spclPriceAmt2;
	}
	public void setSpclPriceAmt2(String spclPriceAmt2) {
		this.spclPriceAmt2 = spclPriceAmt2;
	}
	public String getSpclPriceAmt3() {
		return spclPriceAmt3;
	}
	public void setSpclPriceAmt3(String spclPriceAmt3) {
		this.spclPriceAmt3 = spclPriceAmt3;
	}
	public String getSpclPriceAmt4() {
		return spclPriceAmt4;
	}
	public void setSpclPriceAmt4(String spclPriceAmt4) {
		this.spclPriceAmt4 = spclPriceAmt4;
	}
	public String getSpclPriceAmt5() {
		return spclPriceAmt5;
	}
	public void setSpclPriceAmt5(String spclPriceAmt5) {
		this.spclPriceAmt5 = spclPriceAmt5;
	}
	public String getDispCd() {
		return dispCd;
	}
	public void setDispCd(String dispCd) {
		this.dispCd = dispCd;
	}
	public String getTotalAppPmtAmt() {
		return totalAppPmtAmt;
	}
	public void setTotalAppPmtAmt(String totalAppPmtAmt) {
		this.totalAppPmtAmt = totalAppPmtAmt;
	}
	public String getBusOwnerId() {
		return busOwnerId;
	}
	public void setBusOwnerId(String busOwnerId) {
		this.busOwnerId = busOwnerId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}

