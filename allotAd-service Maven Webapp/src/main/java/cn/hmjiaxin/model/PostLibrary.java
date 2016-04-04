package cn.hmjiaxin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qa_business_post_library")
public class PostLibrary {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@Column(name = "platId")
	private String platId;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "source_url")
	private String sourceUrl;

	@Column(name = "is_tracking")
	private int isTracking;

	@Column(name = "is_append_openid")
	private int isAppendOpenid;

	@Column(name = "is_wx_tracking")
	private int isWxTracking;

	@Column(name = "digest")
	private String digest;

	@Column(name = "author")
	private String author;

	@Column(name = "is_cover")
	private int isCover;

	@Column(name = "preview_url")
	private String previewUrl;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "setting")
	private String setting;

	@Column(name = "meta")
	private String meta;

	@Column(name = "is_delete")
	private int isDelete;

	@Column(name = "post_type")
	private int postType;

	@Column(name = "size")
	private String size;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

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

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public int getIsTracking() {
		return isTracking;
	}

	public void setIsTracking(int isTracking) {
		this.isTracking = isTracking;
	}

	public int getIsAppendOpenid() {
		return isAppendOpenid;
	}

	public void setIsAppendOpenid(int isAppendOpenid) {
		this.isAppendOpenid = isAppendOpenid;
	}

	public int getIsWxTracking() {
		return isWxTracking;
	}

	public void setIsWxTracking(int isWxTracking) {
		this.isWxTracking = isWxTracking;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getIsCover() {
		return isCover;
	}

	public void setIsCover(int isCover) {
		this.isCover = isCover;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
