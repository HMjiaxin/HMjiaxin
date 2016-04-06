package cn.hmjiaxin.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qa_business_sponsor_ads")
public class SponsorAds {
	@Id
	private int id;
	@OneToOne
	@JoinColumn(name = "business_id")
	private Business business;
	@OneToOne
	@JoinColumn(name = "post_id")// '图文id',
	private PostLibrary post;
	@Column(name = "title")
	private String title;// 广告标题'
	@Column(name = "media_type")
	private String mediaType;// '媒体类型',
	@Column(name = "start_date")
	private Date startDate;// '广告生效时间'
	@Column(name = "end_date")
	private Date endDate;// '广告失效时间',
	@Column(name = "price")
	private BigDecimal price; // '广告价格，元/人',
	@Column(name = "price_type")
	private int priceType;// '0/默认 1/其他 2/其他',
	@Column(name = "ads_type")
	private int adsType;// '0/cpm 1/cpc 2/cpa 广告类型',
	@Column(name = "business_weight")
	private BigDecimal businessWeight;// '企业权重',
	@Column(name = "budget")
	private BigDecimal budget;// '广告预算',
	@Column(name = "cost")
	private BigDecimal cost;// '广告支出',
	@Column(name = "ref_count")
	private int refCount;// '参考下发次数',
	@Column(name = "post_total_count")
	private int postTotalCount;// '下发次数',
	@Column(name = "status")
	private int status;// '广告状态',
	@Column(name = "is_lock")
	private int isLock;// '广告锁定状态',
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "last_update_date")
	private Date lastUpdateDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public PostLibrary getPost() {
		return post;
	}
	public void setPost(PostLibrary post) {
		this.post = post;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getPriceType() {
		return priceType;
	}
	public void setPriceType(int priceType) {
		this.priceType = priceType;
	}
	public int getAdsType() {
		return adsType;
	}
	public void setAdsType(int adsType) {
		this.adsType = adsType;
	}
	public BigDecimal getBusinessWeight() {
		return businessWeight;
	}
	public void setBusinessWeight(BigDecimal businessWeight) {
		this.businessWeight = businessWeight;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public int getRefCount() {
		return refCount;
	}
	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}
	public int getPostTotalCount() {
		return postTotalCount;
	}
	public void setPostTotalCount(int postTotalCount) {
		this.postTotalCount = postTotalCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsLock() {
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	

}
