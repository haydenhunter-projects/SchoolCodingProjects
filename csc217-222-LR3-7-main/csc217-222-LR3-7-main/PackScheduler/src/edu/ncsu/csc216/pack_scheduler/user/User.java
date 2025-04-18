package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Parent class for users. Contains parameters for first name, last name
 * id, email, and password. 
 * @author jscleme2
 *
 */
public abstract class User {

	/** Students firstName */
	private String firstName;
	/** Students lastName */
	private String lastName;
	/** Students id */
	private String id;
	/** Students email */
	private String email;
	/** Students password */
	private String password;
	/**
	 * Creates a User object with firstname, lastname, id, email, password
	 * @param firstName firstname of User
	 * @param lastName lastname of User
	 * @param id id of User
	 * @param email email of User
	 * @param password password of User
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName); 
		setId(id); 
		setEmail(email); 
		setPassword(password); 
	}

	/**
	 * Sets the Student's password. 
	 * 
	 * @param hashPW the password to set
	 * @throws IllegalArgumentException if the hashPW parameter is empty or null
	 */
	public void setPassword(String hashPW) {
		if (hashPW == null || "".equals(hashPW)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = hashPW;
	}

	/**
	 * Sets the Student's email.
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email parameter is empty, null, 
	 * does not contain a ".", does not contain a "@", or if the last "." 
	 * comes before the last "@"
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				index1 = i;
			} else if (email.charAt(i) == '.') {
				index2 = i;
			}
		}
		if (index1 > index2) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets the Student's id.
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id parameter is empty or null
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Sets the Student's last name.
	 * 
	 * @param lastName the last name to set
	 * @throws IllegalArgumentException if the lastName parameter is empty or null
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	
	}

	/**
	 * Sets the Student's first name.
	 * 
	 * @param firstName the first name to set
	 * @throws IllegalArgumentException if the firstName parameter is empty or null
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	
	}

	/**
	 * Returns the Student's first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the Student's last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the Student's id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the Student's email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the Student's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Generates the User objects hashCode. Overrides to include the parameter of 
	 * the user object. 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	/**
	 * Compares two User objects. If the parameter is this object returns true.
	 * Overrides to check if they have the same firstName, lastName, id, email, and password.
	 * If so they are the same user. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

}