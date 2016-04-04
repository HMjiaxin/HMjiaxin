package cn.hmjiaxin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="qa_business_post_tag")
public class PostTag {
	@Id
	private  int businessId;
	@OneToOne
	@JoinColumn(name="post_id")
	private PostLibrary post;
	@OneToOne
	@JoinColumn(name="tag_id")
	private ContentTag contentTag;
	
	
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public PostLibrary getPost() {
		return post;
	}
	public void setPost(PostLibrary post) {
		this.post = post;
	}
	public ContentTag getContentTag() {
		return contentTag;
	}
	public void setContentTag(ContentTag contentTag) {
		this.contentTag = contentTag;
	}
	
	
}
