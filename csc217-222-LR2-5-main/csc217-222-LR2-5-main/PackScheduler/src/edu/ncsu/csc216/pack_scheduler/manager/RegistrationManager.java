package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class used to update and complement the User interface for registration
 * 
 * @author Hayden Hunter
 *
 */
public class RegistrationManager {

	/**
	 * Instance field of ReginstrationManager
	 */
	private static RegistrationManager instance;

	/**
	 * Catalog of different courses
	 */
	private CourseCatalog courseCatalog;

	/**
	 * Directory used to store student records
	 */
	private StudentDirectory studentDirectory;

	/**
	 * Instance of the registrar class.
	 */
	private User registrar;

	/**
	 * Field storing the information of the current user
	 */
	private User currentUser;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Prop file relating to registrar */
	private static final String PROP_FILE = "registrar.properties";

	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		createRegistrar();
	}

	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Getter Method for instance field
	 * 
	 * @return instance field
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Getter Method for courseCatalog
	 * 
	 * @return course catalog field
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Getter Method for Student Directory
	 * 
	 * @return studentDirectory field
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Used to check whether a student has logged into their account
	 * 
	 * @param id       student id
	 * @param password student password
	 * @return whether student has logged in or not
	 */
	public boolean login(String id, String password) {
		User s = studentDirectory.getStudentById(id);
		String localHashPW = hashPW(password);

		if (currentUser != null) {
			return false;
		}

		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			} else {
				return false;
			}
		}

		if (s != null) {
			if (s.getPassword().equals(localHashPW) && s.getId().equals(id)) {
				currentUser = s;
				return true;
			}
		} else {
			throw new IllegalArgumentException("User doesn't exist.");
		}

		return false;
	}

	/**
	 * Method used to logout from account
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Getter Method for current user field
	 * 
	 * @return current user field
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Method used to clear records/data from the student course catalog and/or
	 * directory
	 */
	public void clearData() {
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();

		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName is the first name of the student
		 * @param lastName  is the last name of student
		 * @param id        is the id of student
		 * @param email     is the email of the student
		 * @param hashPW    is the hash code of the password for the student
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}