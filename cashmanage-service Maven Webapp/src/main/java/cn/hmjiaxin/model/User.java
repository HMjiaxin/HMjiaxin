package cn.hmjiaxin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "qa_shared_user")
public class User {

	@Id
	@NotNull
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone")
	private String phone;
	@NotNull
	@Column(name = "password_hash")
	private String passwordHash;
	@NotNull
	@Column(name = "password_secret")
	private String passwordSecret;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "is_verified")
	private String isVerified;
	@Column(name = "channel")
	private String channel;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_date")
	private String createdDate;
	@Column(name = "last_updated_date")
	@NotNull
	private String lastUpdatedDate;

}
