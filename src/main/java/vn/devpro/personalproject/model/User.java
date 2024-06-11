package vn.devpro.personalproject.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "tbl_user")
public class User extends BaseModel implements UserDetails{
	
	@Column(name = "username", length = 120, nullable= false)
	private String username;
	
	@Column(name = "password", length = 120, nullable = false)
	private String password;
	
	@Column(name = "name", length = 120, nullable = true)
	private String name;
	
	@Column(name = "mobile", length = 60, nullable = true)
	private String mobile;
	
	@Column(name = "email", length = 200, nullable = true)
	private String email;
	
	@Column(name = "address", length = 300, nullable = true)
	private String address;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	
	//----Mapping many -to- many : tbl_user-to-tbl_role
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private List<Role> roles = new ArrayList<Role>();
	
	public void addRelationalUserRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}
	
	public void removeRelationalUserRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}

	
// ------Mapping one-to-many : user to sale-order-----------------------------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SaleOrder> saleOrders = new HashSet<SaleOrder>();

	// method add and remove elements in relational product list
	public void addRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.add(saleOrder);
		saleOrder.setUser(this);
	}

	public void removeRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.remove(saleOrder);
		saleOrder.setUser(null);
	}
	
	//-----------Mapping many-to-one: user-to-user (user create user)------------------
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "create_by")
		private User userCreateUser;

	//-----------Mapping many-to-one: user-to-user (user update user)------------------
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "update_by")
		private User userUpdateUser;
		
	//---------Mapping one-to-many: user-to-user (for user create user)-----------	
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateUser")
		private Set<User> userCreateUsers = new HashSet<User>();

	//---------Mapping one-to-many: user-to-user (for user update user)-----------	
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateUser")
		private Set<User> userUpdateUsers = new HashSet<User>();
	
		
	//---------Mapping one-to-many: user-to-category (for user create category)-----------	
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateCategory")
			private Set<Category> userCreateCategories = new HashSet<Category>();

	//---------Mapping one-to-many: user-to-category (for user update category)-----------	
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateCategory")
			private Set<Category> userUpdateCategories = new HashSet<Category>();
			
			//---------Mapping one-to-many: user-to-product (for user create product)-----------	
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProduct")
			private Set<Product> userCreateProducts = new HashSet<Product>();

	//---------Mapping one-to-many: user-to-product (for user update product)-----------	
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProduct")
			private Set<Product> userUpdateProducts = new HashSet<Product>();

	//------Mapping one-to-many: tbl_user-to-tbl_role (for user create role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateRole")
	private Set<Role> userCreateRoles = new HashSet<Role>();

	//------Mapping one-to-many: tbl_user-to-tbl_role (for user update role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateRole")
	private Set<Role> userUpdateRoles = new HashSet<Role>();
	
	//------Mapping one-to-many: tbl_user-to-tbl_role (for user create role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProductImage")
	private Set<ProductImage> userCreateProductImages = new HashSet<ProductImage>();

	//------Mapping one-to-many: tbl_user-to-tbl_role (for user update role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProductImage")
	private Set<ProductImage> userUpdateProductImages = new HashSet<ProductImage>();
	
	//------Mapping one-to-many: tbl_user-to-tbl_role (for user create role)
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrder")
		private Set<SaleOrder> userCreateSaleOrders = new HashSet<SaleOrder>();

	//------Mapping one-to-many: tbl_user-to-tbl_role (for user update role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrder")
	private Set<SaleOrder> userUpdateSaleOrders = new HashSet<SaleOrder>();
	
	//------Mapping one-to-many: tbl_user-to-tbl_role (for user create role)
			@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrderProduct")
			private Set<SaleOrderProduct> userCreateSaleOrderProducts = new HashSet<SaleOrderProduct>();

	//------Mapping one-to-many: tbl_user-to-tbl_role (for user update role)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrderProduct")
	private Set<SaleOrderProduct> userUpdateSaleOrderProducts = new HashSet<SaleOrderProduct>();
	
	public User() {
			super();
		}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUserCreateUser() {
		return userCreateUser;
	}

	public void setUserCreateUser(User userCreateUser) {
		this.userCreateUser = userCreateUser;
	}

	public User getUserUpdateUser() {
		return userUpdateUser;
	}

	public void setUserUpdateUser(User userUpdateUser) {
		this.userUpdateUser = userUpdateUser;
	}

	public Set<User> getUserCreateUsers() {
		return userCreateUsers;
	}

	public void setUserCreateUsers(Set<User> userCreateUsers) {
		this.userCreateUsers = userCreateUsers;
	}

	public Set<User> getUserUpdateUsers() {
		return userUpdateUsers;
	}

	public void setUserUpdateUsers(Set<User> userUpdateUsers) {
		this.userUpdateUsers = userUpdateUsers;
	}

	public Set<Category> getUserCreateCategories() {
		return userCreateCategories;
	}

	public void setUserCreateCategories(Set<Category> userCreateCategories) {
		this.userCreateCategories = userCreateCategories;
	}

	public Set<Category> getUserUpdateCategories() {
		return userUpdateCategories;
	}

	public void setUserUpdateCategories(Set<Category> userUpdateCategories) {
		this.userUpdateCategories = userUpdateCategories;
	}

	public Set<Product> getUserCreateProducts() {
		return userCreateProducts;
	}

	public void setUserCreateProducts(Set<Product> userCreateProducts) {
		this.userCreateProducts = userCreateProducts;
	}

	public Set<Product> getUserUpdateProducts() {
		return userUpdateProducts;
	}

	public void setUserUpdateProducts(Set<Product> userUpdateProducts) {
		this.userUpdateProducts = userUpdateProducts;
	}

	public Set<Role> getUserCreateRoles() {
		return userCreateRoles;
	}

	public void setUserCreateRoles(Set<Role> userCreateRoles) {
		this.userCreateRoles = userCreateRoles;
	}

	public Set<Role> getUserUpdateRoles() {
		return userUpdateRoles;
	}

	public void setUserUpdateRoles(Set<Role> userUpdateRoles) {
		this.userUpdateRoles = userUpdateRoles;
	}

	public Set<ProductImage> getUserCreateProductImages() {
		return userCreateProductImages;
	}

	public void setUserCreateProductImages(Set<ProductImage> userCreateProductImages) {
		this.userCreateProductImages = userCreateProductImages;
	}

	public Set<ProductImage> getUserUpdateProductImages() {
		return userUpdateProductImages;
	}

	public void setUserUpdateProductImages(Set<ProductImage> userUpdateProductImages) {
		this.userUpdateProductImages = userUpdateProductImages;
	}

	public Set<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(Set<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public Set<SaleOrder> getUserCreateSaleOrders() {
		return userCreateSaleOrders;
	}

	public void setUserCreateSaleOrders(Set<SaleOrder> userCreateSaleOrders) {
		this.userCreateSaleOrders = userCreateSaleOrders;
	}

	public Set<SaleOrder> getUserUpdateSaleOrders() {
		return userUpdateSaleOrders;
	}

	public void setUserUpdateSaleOrders(Set<SaleOrder> userUpdateSaleOrders) {
		this.userUpdateSaleOrders = userUpdateSaleOrders;
	}

	public Set<SaleOrderProduct> getUserCreateSaleOrderProducts() {
		return userCreateSaleOrderProducts;
	}

	public void setUserCreateSaleOrderProducts(Set<SaleOrderProduct> userCreateSaleOrderProducts) {
		this.userCreateSaleOrderProducts = userCreateSaleOrderProducts;
	}

	public Set<SaleOrderProduct> getUserUpdateSaleOrderProducts() {
		return userUpdateSaleOrderProducts;
	}

	public void setUserUpdateSaleOrderProducts(Set<SaleOrderProduct> userUpdateSaleOrderProducts) {
		this.userUpdateSaleOrderProducts = userUpdateSaleOrderProducts;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User(String username, String password, String name, String mobile, String email, String address,
			String description, List<Role> roles, Set<SaleOrder> saleOrders, User userCreateUser, User userUpdateUser,
			Set<User> userCreateUsers, Set<User> userUpdateUsers, Set<Category> userCreateCategories,
			Set<Category> userUpdateCategories, Set<Product> userCreateProducts, Set<Product> userUpdateProducts,
			Set<Role> userCreateRoles, Set<Role> userUpdateRoles, Set<ProductImage> userCreateProductImages,
			Set<ProductImage> userUpdateProductImages, Set<SaleOrder> userCreateSaleOrders,
			Set<SaleOrder> userUpdateSaleOrders, Set<SaleOrderProduct> userCreateSaleOrderProducts,
			Set<SaleOrderProduct> userUpdateSaleOrderProducts) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.description = description;
		this.roles = roles;
		this.saleOrders = saleOrders;
		this.userCreateUser = userCreateUser;
		this.userUpdateUser = userUpdateUser;
		this.userCreateUsers = userCreateUsers;
		this.userUpdateUsers = userUpdateUsers;
		this.userCreateCategories = userCreateCategories;
		this.userUpdateCategories = userUpdateCategories;
		this.userCreateProducts = userCreateProducts;
		this.userUpdateProducts = userUpdateProducts;
		this.userCreateRoles = userCreateRoles;
		this.userUpdateRoles = userUpdateRoles;
		this.userCreateProductImages = userCreateProductImages;
		this.userUpdateProductImages = userUpdateProductImages;
		this.userCreateSaleOrders = userCreateSaleOrders;
		this.userUpdateSaleOrders = userUpdateSaleOrders;
		this.userCreateSaleOrderProducts = userCreateSaleOrderProducts;
		this.userUpdateSaleOrderProducts = userUpdateSaleOrderProducts;
	}
	
	
	
}
