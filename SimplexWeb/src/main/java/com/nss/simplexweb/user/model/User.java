package com.nss.simplexweb.user.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.nss.simplexweb.paymentterm.model.PaymentTermPartnerRel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="user_dtl_tbl")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="emp_id", unique=true)
	private Long empId;
	
	@Column(name="doj")
	private String doj;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="primary_contact_number", unique=true)
	private String primaryContactNumber;
	
	@Column(name="secondary_contact_number", unique=true)
	private String secondaryContactNumber;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name = "email", unique=true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="managerId")
	private Long managerId;
	
	@Column(name="esic_no")
	private String esicNum;
	
	@Column(name="pf_no")
	private String pfNum;
	
	@Column(name="aadhaar_no", unique=true)
	private String aadhaarNum;
	
	@Column(name="pan_no", unique=true)
	private String panNum;
	
	@Column(name = "created_timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdTimestamp;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	/*@OneToMany(mappedBy = "partner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<PaymentTermPartnerRel> paymentTermPartnerRel;*/
	
	@Column(name="profile_pic_folderpath")
	private String profilePicFolderpath;
	
	@Column(name="profile_pic_filename", unique=true)
	private String profilePicFilename;
}
