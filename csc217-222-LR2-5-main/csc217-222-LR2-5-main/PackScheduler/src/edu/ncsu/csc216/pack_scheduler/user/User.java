package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * The User abstract class is a superclass of Student and is intended to provide
 * functionality with Registrar users.
 * 
 * @author hchunter,
 *
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * The User constructor creates a user for the registrar
	 * 
	 * @param firstName - the first name of a user in the registrar
	 * @param lastName  - the last name of a user in the registrar
	 * @param id        - the id of a user in the registrar
	 * @param email     - the email of a user in the registrar
	 * @param password  - the password of a user in the registrar
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Returns the student's first name
	 * 
	 * @return The student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the student's last name
	 * 
	 * @return The student's lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the student's id
	 * 
	 * @return The student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's first name
	 * 
	 * @param firstName The first name of the student
	 * @throws IllegalArgumentException if first name is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * 
	 * @param lastName The last name of the student
	 * @throws IllegalArgumentException if last name is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the id for a Student
	 * 
	 * @param id - id for the Student
	 * @throws IllegalArgumentException if the inputed id is invalid
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returning the student's email
	 * 
	 * @return The student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setting up the student's email
	 * 
	 * @param email The email of the student
	 * @throws IllegalArgumentException with the message Invalid Email if the email
	 *                                  is null, an empty string, the string does
	 *                                  not contain '@' or '.', and finally if '.'
	 *                                  comes before '@' in the string.
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email) || !email.contains("@") || !email.contains(".")
				|| email.lastIndexOf('.') < email.indexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * returning the student's password
	 * 
	 * @return The student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setting the student's password
	 * 
	 * @param password The password of the student
	 * @throws IllegalArgumentException with the message Invalid password if
	 *                                  password is null or an empty string
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Provides hashcode for objects and fields in User.java for equals method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	/**
	 * Checks the hashcode value of fields and objects in User.java to check for
	 * duplicates.
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