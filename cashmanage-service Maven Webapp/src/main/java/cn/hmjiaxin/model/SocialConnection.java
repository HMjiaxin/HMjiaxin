package cn.hmjiaxin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 微信连接
 * @author rabbit
 *
 */
@Entity
@Table(name="qa_business_social_connection")
public class SocialConnection {
	@Id
	private int id;
	@OneToOne
	@JoinColumn(name="business_id")
	private Business business;
	@Column(name="access_token")
	private String accessToken;
	@Column(name="access_token_expires_at")
	private int accessTokenExpiresAt;
	@Column(name="user_access_token")
	private String userAccessToken;
	@Column(name="user_access_token_expires_at")
	private int userAccessTokenExpiresAt;
	@Column(name="social_channel_type")
	private int socialChannelType;
	@Column(name="social_profile_id")
	private String socialProfileId;
	@Column(name="social_profile_name")
	private String socialProfileName;
	@Column(name="social_profile_photo")
	private String socialProfilePhoto;
	@Column(name="is_fetch")
	private int isFetch;
	@Column(name="is_expired")
	private int isExpired;
	@Column(name="is_delete")
	private int isDelete;
	@Column(name="weight")
	private String weight;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="last_updated_date")
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
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getAccessTokenExpiresAt() {
		return accessTokenExpiresAt;
	}
	public void setAccessTokenExpiresAt(int accessTokenExpiresAt) {
		this.accessTokenExpiresAt = accessTokenExpiresAt;
	}
	public String getUserAccessToken() {
		return userAccessToken;
	}
	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}
	public int getUserAccessTokenExpiresAt() {
		return userAccessTokenExpiresAt;
	}
	public void setUserAccessTokenExpiresAt(int userAccessTokenExpiresAt) {
		this.userAccessTokenExpiresAt = userAccessTokenExpiresAt;
	}
	public int getSocialChannelType() {
		return socialChannelType;
	}
	public void setSocialChannelType(int socialChannelType) {
		this.socialChannelType = socialChannelType;
	}
	public String getSocialProfileId() {
		return socialProfileId;
	}
	public void setSocialProfileId(String socialProfileId) {
		this.socialProfileId = socialProfileId;
	}
	public String getSocialProfileName() {
		return socialProfileName;
	}
	public void setSocialProfileName(String socialProfileName) {
		this.socialProfileName = socialProfileName;
	}
	public String getSocialProfilePhoto() {
		return socialProfilePhoto;
	}
	public void setSocialProfilePhoto(String socialProfilePhoto) {
		this.socialProfilePhoto = socialProfilePhoto;
	}
	public int getIsFetch() {
		return isFetch;
	}
	public void setIsFetch(int isFetch) {
		this.isFetch = isFetch;
	}
	public int getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(int isExpired) {
		this.isExpired = isExpired;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
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
