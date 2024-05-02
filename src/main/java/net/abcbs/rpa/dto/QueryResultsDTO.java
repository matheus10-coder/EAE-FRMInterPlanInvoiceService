package net.abcbs.rpa.dto;

import org.apache.commons.lang3.StringUtils;

public class QueryResultsDTO {
	
	private String otherPlanClaimId;
	private String abcbsClaimId;
	private String servProvName;
	private String fullName;
	private String contractId;
	private String grpId;
	private String grpName;
	private String subGrpIdOrig;
	private String srcCarrierCd;
	private String spanChgDT;
	private String loadDT;
	private String spanEffDT;
	private String memberGrpKey;
	private String groupGrpKey;
	private String error;

 

	
	public String getOtherPlanClaimId() {
		return otherPlanClaimId;
	}
	
	public void setOtherPlanClaimId(String otherPlanClaimId) {
		if (StringUtils.isBlank(otherPlanClaimId)) {

			this.otherPlanClaimId = "Null Plan Claim ID";
		}
		else {
			this.otherPlanClaimId = otherPlanClaimId.trim();
		}
		
	}
	
	public String getAbcbsClaimId() {
		return abcbsClaimId;
	}
	
	public void setAbcbsClaimId(String abcbsClaimId) {
		if (StringUtils.isBlank(abcbsClaimId)) {
			this.abcbsClaimId = "Null ABCBS Claim ID";
		}
		else {
			this.abcbsClaimId = abcbsClaimId.trim();
		}
		
	}
	
	public String getServProvName() {
		return servProvName;
	}
	
	public void setServProvName(String servProvName) {
		if (StringUtils.isBlank(servProvName)) {
			this.servProvName = "Null Serv Provider Name";
		}
		else {
			this.servProvName = servProvName.trim();
		}
		
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		if (StringUtils.isBlank(fullName)) {
			this.fullName = "Null Full Name";
		}
		else {
			this.fullName = fullName.trim();
		}
	}
	
	public String getContractId() {
		return contractId;
	}
	
	public void setContractId(String contractId) {
		if (StringUtils.isBlank(contractId)) {
			this.contractId = "Null Contract ID";
		}
		else {
			this.contractId = contractId.trim();
		}
	}
	
	public String getGrpId() {
		return grpId;
	}
	
	public void setGrpId(String grpId) {
		if (StringUtils.isBlank(grpId)) {
			this.grpId = "Null Group ID";
		}
		else {
			this.grpId = grpId.trim();
		}
	}
	
	public String getGrpName() {
		return grpName;
	}
	
	public void setGrpName(String grpName) {
		if (StringUtils.isBlank(grpName)) {
			this.grpName = "Null group Name";
		}
		else {
			this.grpName = grpName.trim();
		}
	}
	
	public String getSubGrpIdOrig() {
		return subGrpIdOrig;
	}
	
	public void setSubGrpIdOrig(String subGrpIdOrig) {
		if (StringUtils.isBlank(subGrpIdOrig)) {
			this.subGrpIdOrig = "Null sub Group Id Original";
		}
		else {
			this.subGrpIdOrig = subGrpIdOrig.trim();
		}
	}
	

	public String getSrcCarrierCd() {
		return srcCarrierCd;
	}
	
	public void setSrcCarrierCd(String srcCarrierCd) {	
		if (StringUtils.isBlank(srcCarrierCd)) {
			this.srcCarrierCd = "Null Src Carrier Cd";
		}
		else {
			this.srcCarrierCd = srcCarrierCd.trim();
		}
		
	}
	
	public String getSpanChgDT() {
		return spanChgDT;
	}
	
	public void setSpanChgDT(String spanChgDT) {
		if (StringUtils.isBlank(spanChgDT)) {
			this.spanChgDT = "Null Span Change DT";
		}
		else {
			this.spanChgDT = spanChgDT.trim();
		}
	}
	
	public String getLoadDT() {
		return loadDT;
	}
	
	public void setLoadDT(String loadDT) {
		if (StringUtils.isBlank(loadDT)) {
			this.loadDT = "Null Load DT";
		}
		else {
			this.loadDT = loadDT.trim();
		}
	}
	
	public String getSpanEffDT() {
		return spanEffDT;
	}
	
	public void setSpanEffDT(String spanEffDT) {
		if (StringUtils.isBlank(spanEffDT)) {
			this.spanEffDT = "Null Span Effective Date";
		}
		else {
			this.spanEffDT = spanEffDT.trim();
		}
	}
	
	public String getMemberGrpKey() {
		return memberGrpKey;
	}
	
	public void setMemberGrpKey(String memberGrpKey) {
		if (StringUtils.isBlank(memberGrpKey)) {
			this.memberGrpKey = "Null Member group key";
		}
		else {
			this.memberGrpKey = memberGrpKey.trim();
		}
	}
	
	public String getGroupGrpKey() {
		return groupGrpKey;
	}
	
	public void setGroupGrpKey(String groupGrpKey) {
		if (StringUtils.isBlank(groupGrpKey)) {
			this.groupGrpKey = "Null Group Grp key";
		}
		else {
			this.groupGrpKey = groupGrpKey.trim();
		}
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		if (StringUtils.isBlank(error)) {
			this.error = "Error msg is null";
		}
		else {
			this.error = error.trim();
		}
	}
	

}
