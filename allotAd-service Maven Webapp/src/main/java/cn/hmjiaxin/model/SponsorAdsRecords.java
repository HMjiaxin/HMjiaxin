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
@Table(name = "qa_business_sponsor_ads_records")
public class SponsorAdsRecords {
	@Id
	private int id;
	@OneToOne
	@JoinColumn(name = "ad_id")
	private SponsorAds ad;// 广告
	@OneToOne
	@JoinColumn(name = "sponsor_id")
	private Business sponsor;// 广告主
	@OneToOne
	@JoinColumn(name = "post_id")
	private PostLibrary post;// 图文
	@OneToOne
	@JoinColumn(name = "media_id")
	private Business media;// 媒体主
	@OneToOne
	@JoinColumn(name = "plat_id")
	private SocialConnection plat;// 微信公众号
	@Column(name = "post_count")
	private int postCount;// 广告投放人数
	@Column(name = "post_price")
	private BigDecimal postPrice;// 广告下发价格
	@Column(name = "post_cost")
	private BigDecimal postCost;// 广告下发支出
	@Column(name = "msg_id")
	private String msgId;// 图文追踪id
	@Column(name = "post_time")
	private Date postTime;// 创建时间
	@Column(name = "int_page_read_user")
	private int intPageReadUser;// 图文页阅读人数
	@Column(name = "int_page_read_count")
	private int intPageReadCount;// 图文页阅读次数
	@Column(name = "ori_page_read_user")
	private int oriPageReadUser;// 原文页的阅读人数
	@Column(name = "ori_page_read_count")
	private int oriPageReadCount;// 原文页的阅读次数
	@Column(name = "share_user")
	private int shareUser;// 分享人数
	@Column(name = "share_count")
	private int shareCount;// 分享次数
	@Column(name = "add_to_fav_user")
	private int addToFavUser;// 收藏人数
	@Column(name = "add_to_fav_count")
	private int addToFavCount;// 收藏次数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SponsorAds getAd() {
		return ad;
	}

	public void setAd(SponsorAds ad) {
		this.ad = ad;
	}

	public Business getSponsor() {
		return sponsor;
	}

	public void setSponsor(Business sponsor) {
		this.sponsor = sponsor;
	}

	public PostLibrary getPost() {
		return post;
	}

	public void setPost(PostLibrary post) {
		this.post = post;
	}

	public Business getMedia() {
		return media;
	}

	public void setMedia(Business media) {
		this.media = media;
	}

	public SocialConnection getPlat() {
		return plat;
	}

	public void setPlat(SocialConnection plat) {
		this.plat = plat;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public BigDecimal getPostPrice() {
		return postPrice;
	}

	public void setPostPrice(BigDecimal postPrice) {
		this.postPrice = postPrice;
	}

	public BigDecimal getPostCost() {
		return postCost;
	}

	public void setPostCost(BigDecimal postCost) {
		this.postCost = postCost;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getIntPageReadUser() {
		return intPageReadUser;
	}

	public void setIntPageReadUser(int intPageReadUser) {
		this.intPageReadUser = intPageReadUser;
	}

	public int getIntPageReadCount() {
		return intPageReadCount;
	}

	public void setIntPageReadCount(int intPageReadCount) {
		this.intPageReadCount = intPageReadCount;
	}

	public int getOriPageReadUser() {
		return oriPageReadUser;
	}

	public void setOriPageReadUser(int oriPageReadUser) {
		this.oriPageReadUser = oriPageReadUser;
	}

	public int getOriPageReadCount() {
		return oriPageReadCount;
	}

	public void setOriPageReadCount(int oriPageReadCount) {
		this.oriPageReadCount = oriPageReadCount;
	}

	public int getShareUser() {
		return shareUser;
	}

	public void setShareUser(int shareUser) {
		this.shareUser = shareUser;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getAddToFavUser() {
		return addToFavUser;
	}

	public void setAddToFavUser(int addToFavUser) {
		this.addToFavUser = addToFavUser;
	}

	public int getAddToFavCount() {
		return addToFavCount;
	}

	public void setAddToFavCount(int addToFavCount) {
		this.addToFavCount = addToFavCount;
	}

}
