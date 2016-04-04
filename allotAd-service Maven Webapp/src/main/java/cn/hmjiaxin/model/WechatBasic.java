package cn.hmjiaxin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="qa_business_wechat_basic")
public class WechatBasic {
	@Id
	private int id;
	@Column(name = "socialid")
	private int socialid;

	@Column(name = "token")
	private String token;

	@Column(name = "name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@Column(name = "keyword_length")
	private int keywordLength;

	@Column(name = "result_count")
	private int resultCount;

	@Column(name = "welcome")
	private String welcome;

	@Column(name = "keyword_too_long")
	private String keywordTooLong;

	@Column(name = "not_found")
	private String notFound;

	@Column(name = "app_id")
	private String appId;

	@Column(name = "app_secret")
	private String appSecret;

	@Column(name = "plat_type")
	private int platType;

	@Column(name = "verify_type_info")
	private int verifyTypeInfo;

	@Column(name = "func_info")
	private String funcInfo;

	@Column(name = "info_type")
	private String infoType;

	@Column(name = "account_image_url")
	private String accountImageUrl;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_background_img_url")
	private String accountBackgroundImgUrl;

	@Column(name = "account_description")
	private String accountDescription;

	@Column(name = "account_type")
	private int accountType;

	@Column(name = "account_follower_count")
	private int accountFollowerCount;

	@Column(name = "account_qrcode")
	private String accountQrcode;

	@Column(name = "location")
	private String location;

	@Column(name = "is_delete")
	private int isDelete;

	@Column(name = "is_open")
	private int isOpen;

	@Column(name = "open_api_url")
	private String openApiUrl;

	@Column(name = "is_open_scan")
	private int isOpenScan;

	@Column(name = "max_lbs_return_num")
	private int maxLbsReturnNum;

	@Column(name = "open_api_scan_url")
	private String openApiScanUrl;

	@Column(name = "is_open_keyword")
	private int isOpenKeyword;

	@Column(name = "open_api_keyword_url")
	private String openApiKeywordUrl;

	@Column(name = "is_open_allmessage")
	private int isOpenAllmessage;

	@Column(name = "is_open_card")
	private int isOpenCard;

	@Column(name = "open_api_all_message_url")
	private String openApiAllMessageUrl;

	@Column(name = "open_api_card_url")
	private String openApiCardUrl;

	@Column(name = "open_api_writelist")
	private String openApiWritelist;

	@Column(name = "is_open_customer")
	private int isOpenCustomer;

	@Column(name = "update_datetime")
	private Date updateDatetime;

	@Column(name = "created")
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSocialid() {
		return socialid;
	}

	public void setSocialid(int socialid) {
		this.socialid = socialid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public int getKeywordLength() {
		return keywordLength;
	}

	public void setKeywordLength(int keywordLength) {
		this.keywordLength = keywordLength;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getKeywordTooLong() {
		return keywordTooLong;
	}

	public void setKeywordTooLong(String keywordTooLong) {
		this.keywordTooLong = keywordTooLong;
	}

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public int getPlatType() {
		return platType;
	}

	public void setPlatType(int platType) {
		this.platType = platType;
	}

	public int getVerifyTypeInfo() {
		return verifyTypeInfo;
	}

	public void setVerifyTypeInfo(int verifyTypeInfo) {
		this.verifyTypeInfo = verifyTypeInfo;
	}

	public String getFuncInfo() {
		return funcInfo;
	}

	public void setFuncInfo(String funcInfo) {
		this.funcInfo = funcInfo;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getAccountImageUrl() {
		return accountImageUrl;
	}

	public void setAccountImageUrl(String accountImageUrl) {
		this.accountImageUrl = accountImageUrl;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBackgroundImgUrl() {
		return accountBackgroundImgUrl;
	}

	public void setAccountBackgroundImgUrl(String accountBackgroundImgUrl) {
		this.accountBackgroundImgUrl = accountBackgroundImgUrl;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getAccountFollowerCount() {
		return accountFollowerCount;
	}

	public void setAccountFollowerCount(int accountFollowerCount) {
		this.accountFollowerCount = accountFollowerCount;
	}

	public String getAccountQrcode() {
		return accountQrcode;
	}

	public void setAccountQrcode(String accountQrcode) {
		this.accountQrcode = accountQrcode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getOpenApiUrl() {
		return openApiUrl;
	}

	public void setOpenApiUrl(String openApiUrl) {
		this.openApiUrl = openApiUrl;
	}

	public int getIsOpenScan() {
		return isOpenScan;
	}

	public void setIsOpenScan(int isOpenScan) {
		this.isOpenScan = isOpenScan;
	}

	public int getMaxLbsReturnNum() {
		return maxLbsReturnNum;
	}

	public void setMaxLbsReturnNum(int maxLbsReturnNum) {
		this.maxLbsReturnNum = maxLbsReturnNum;
	}

	public String getOpenApiScanUrl() {
		return openApiScanUrl;
	}

	public void setOpenApiScanUrl(String openApiScanUrl) {
		this.openApiScanUrl = openApiScanUrl;
	}

	public int getIsOpenKeyword() {
		return isOpenKeyword;
	}

	public void setIsOpenKeyword(int isOpenKeyword) {
		this.isOpenKeyword = isOpenKeyword;
	}

	public String getOpenApiKeywordUrl() {
		return openApiKeywordUrl;
	}

	public void setOpenApiKeywordUrl(String openApiKeywordUrl) {
		this.openApiKeywordUrl = openApiKeywordUrl;
	}

	public int getIsOpenAllmessage() {
		return isOpenAllmessage;
	}

	public void setIsOpenAllmessage(int isOpenAllmessage) {
		this.isOpenAllmessage = isOpenAllmessage;
	}

	public int getIsOpenCard() {
		return isOpenCard;
	}

	public void setIsOpenCard(int isOpenCard) {
		this.isOpenCard = isOpenCard;
	}

	public String getOpenApiAllMessageUrl() {
		return openApiAllMessageUrl;
	}

	public void setOpenApiAllMessageUrl(String openApiAllMessageUrl) {
		this.openApiAllMessageUrl = openApiAllMessageUrl;
	}

	public String getOpenApiCardUrl() {
		return openApiCardUrl;
	}

	public void setOpenApiCardUrl(String openApiCardUrl) {
		this.openApiCardUrl = openApiCardUrl;
	}

	public String getOpenApiWritelist() {
		return openApiWritelist;
	}

	public void setOpenApiWritelist(String openApiWritelist) {
		this.openApiWritelist = openApiWritelist;
	}

	public int getIsOpenCustomer() {
		return isOpenCustomer;
	}

	public void setIsOpenCustomer(int isOpenCustomer) {
		this.isOpenCustomer = isOpenCustomer;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
