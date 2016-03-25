package cn.hmjiaxin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "qa_shared_business")
public class Business {
	@Id
	private int id;
	@NotNull
	@Column(name = "business_logo_id")
	private int businessLogoId;
	@Column(name = "name")
	private String name;
	@Column(name = "web_site")
	private String webSite;
	@NotNull
	@Column(name = "industry_id")
	private int industryId;
	@Column(name = "phone")
	private String phone;
	@Column(name = "postal_code")
	private String postalCode;
	@Column(name = "address")
	private String address;
	@Column(name = "address2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country_code")
	private String country_code;
	@Column(name = "time_zone")
	private String timeZone;
	@Column(name = "contact_person")
	private String contactPerson;
	@Column(name = "contact_email")
	private String contactEmail;
	
	@Column(name = "terms_of_service")
	private String termsOfService;
	
	@Column(name = "privacy_policy")
	private String privacyPolicy;
	@NotNull
	@Column(name = "source_domain")
	private String sourceDomain;
	
	@Column(name = "business_type")
	private int businessType;
	
	@Column(name = "monthly_mentions_ceiling")
	private int monthlyMentionsCeiling;
	@NotNull
	@Column(name = "parent_id")
	private int parentId;
	@NotNull
	@Column(name = "billing_to")
	private String billingTo;
	@NotNull
	@Column(name = "status")
	private int status;
	
	@Column(name = "keywords")
	private String keywords;
	
	@Column(name = "negative_keywords")
	private String negativeKeywords;
	
	@Column(name = "user_twitter_sources")
	private String userTwitterSources;
	
	@Column(name = "generated_keywords")
	private String generatedKeywords;
	
	@Column(name = "popular_keywords")
	private String popularKeywords;
	
	@Column(name = "collection_entities")
	private String collectionEntities;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "size")
	private String size;
	@NotNull
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "is_ads")
	private int isAds;
	
	@Column(name = "created_date")
	private Date createdSate;
	@NotNull
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusinessLogoId() {
		return businessLogoId;
	}

	public void setBusinessLogoId(int businessLogoId) {
		this.businessLogoId = businessLogoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getTermsOfService() {
		return termsOfService;
	}

	public void setTermsOfService(String termsOfService) {
		this.termsOfService = termsOfService;
	}

	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}

	public String getSourceDomain() {
		return sourceDomain;
	}

	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public int getMonthlyMentionsCeiling() {
		return monthlyMentionsCeiling;
	}

	public void setMonthlyMentionsCeiling(int monthlyMentionsCeiling) {
		this.monthlyMentionsCeiling = monthlyMentionsCeiling;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getBillingTo() {
		return billingTo;
	}

	public void setBillingTo(String billingTo) {
		this.billingTo = billingTo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNegativeKeywords() {
		return negativeKeywords;
	}

	public void setNegativeKeywords(String negativeKeywords) {
		this.negativeKeywords = negativeKeywords;
	}

	public String getUserTwitterSources() {
		return userTwitterSources;
	}

	public void setUserTwitterSources(String userTwitterSources) {
		this.userTwitterSources = userTwitterSources;
	}

	public String getGeneratedKeywords() {
		return generatedKeywords;
	}

	public void setGeneratedKeywords(String generatedKeywords) {
		this.generatedKeywords = generatedKeywords;
	}

	public String getPopularKeywords() {
		return popularKeywords;
	}

	public void setPopularKeywords(String popularKeywords) {
		this.popularKeywords = popularKeywords;
	}

	public String getCollectionEntities() {
		return collectionEntities;
	}

	public void setCollectionEntities(String collectionEntities) {
		this.collectionEntities = collectionEntities;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getIsAds() {
		return isAds;
	}

	public void setIsAds(int isAds) {
		this.isAds = isAds;
	}

	public Date getCreatedSate() {
		return createdSate;
	}

	public void setCreatedSate(Date createdSate) {
		this.createdSate = createdSate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
