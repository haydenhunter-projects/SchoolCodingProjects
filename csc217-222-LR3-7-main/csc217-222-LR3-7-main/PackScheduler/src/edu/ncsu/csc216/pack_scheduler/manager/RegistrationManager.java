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
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This is the RegistrationManager class that is used to manage the registrar.
 * It allows the user to create and change the registrar
 * @author Travis Koekemoer
 * @author Hayden Hunter
 */
public class RegistrationManager {
	
	/** Instance of RegistrationManager*/
	private static RegistrationManager instance;
	
	/** The list of available courses to register for */
	private CourseCatalog courseCatalog;
	
	/** The list of students*/
	private StudentDirectory studentDirectory;
	
	/** University official that maintains the directory of students*/
	private User registrar;
	
	/** The current user of registration manager*/
	private User currentUser = null;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** The file that contains the registrar's information*/
	private static final String PROP_FILE = "registrar.properties";
	
	/** RegistrationManager for faculty */
	private FacultyDirectory facultyDirectory;

	/**
	 * Constructor used to call the createRegistrar() method and create studentDirectory and courseCatalog 
	 * @throws IllegalArgumentException if registrar is cannot be created
	 */
	private RegistrationManager() throws IllegalArgumentException {
		createRegistrar();
		studentDirectory = new StudentDirectory();
		courseCatalog = new CourseCatalog();
		facultyDirectory =  new FacultyDirectory();
	}
	
	/**
	 * Creates a registrar
	 * @throws IllegalArgumentException if a IOException is caught
	 */
	private void createRegistrar() throws IllegalArgumentException{
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 *Gets the faculty directory
	 * @return faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	
	
	/**
	 * Hashes the password
	 * @param pw the user's password
	 * @return hashed password
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) throws IllegalArgumentException {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Gets the instance of RegistrationManager
	 * @return the instance
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the course catalog
	 * @return courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the student directory
	 * @return studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Method to allow the user to login
	 * @param id the id of the user
	 * @param password the password of the user
	 * @return true if the password is correct
	 * @throws IllegalArgumentException if the id does not match one for a student in the directory 
	 * or the id for the registrar
	 */
	public boolean login(String id, String password) throws IllegalArgumentException {
		
		if (currentUser != null) {
			return false;
		}
		
		String localHashPW = hashPW(password);
		
		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
			else {
				return false;
			}
		}
		
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		
		if (s == null && f == null) {
				throw new IllegalArgumentException("User doesn't exist.");
		}
		if (s != null && s.getPassword().equals(localHashPW)) {
			currentUser = s;
			return true;
		} else if (f != null && f.getPassword().equals(localHashPW)) {
			currentUser = f;
			return true;
		}
		
		
		return false;
	}

	/**
	 * Method to allow the user to logout
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the current user
	 * @return currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Allows the user to clear the data in the course catalog and directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
		currentUser = null;
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
		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
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
	
	/**
	 * Updates the entered faculty's schedule with the entered course. If the course
	 * is not added because the faculty's schedule or it is a conflict or is a duplicate, 
	 * false is returned and the faculty's schedule is not changed. Otherwise, the course
	 * is added to the faculty's schedule and true is returned.
	 * @param c the course to add to the faculty's schedule
	 * @param f the faculty whose schedule should be updated
	 * @return true if the course was added successfully to the faculty's schedule
	 * @throws IllegalArgumentException if the user is not the registrar
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) throws IllegalArgumentException {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		
		return f.getSchedule().addCourseToSchedule(c);
		
	}
	
	/**
	 * Removes the entered course from the entered faculty's schedule. If the 
	 * course is not in the schedule, nothing happens and false is returned. If the 
	 * course is successfully removed the course has its instructorId set to null.
	 * @param c the course to remove from the faculty's schedule
	 * @param f the faculty whose schedule should be updated
	 * @return true if the course was removed successfully to the faculty's schedule
	 * @throws IllegalArgumentException if the user is not the registrar
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) throws IllegalArgumentException {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		return f.getSchedule().removeCourseFromSchedule(c);
	}
	
	/**
	 * Clears the faculty's schedule. After this the faculty's schedule will
	 * be empty. Any courses that were on the faculty's schedule will have their
	 * instructorId set to null.
	 * @param f the faculty whose schedule should be reset
	 * @throws IllegalArgumentException if the user is not the registrar
	 */
	public void resetFacultySchedule(Faculty f) throws IllegalArgumentException {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		f.getSchedule().resetSchedule();
		
	}
	
//	public void loadFacultyFromFile(String fileName) throws IllegalArgumentException {
//		try {
//			LinkedList<Faculty> facultyRecords = FacultyRecordIO.readFacultyRecords(fileName);
//			for (int i = 0; i < facultyRecords.size(); i++) {
//				facultyDirectory.addFaculty(facultyRecords.get(i).getFirstName(), facultyRecords.get(i).getLastName(), facultyRecords.get(i).getId(), facultyRecords.get(i).getEmail(), facultyRecords.get(i).getPassword(), facultyRecords.get(i).getPassword(), facultyRecords.get(i).getMaxCourses());
//			}
//		} catch (FileNotFoundException e) {
//			throw new IllegalArgumentException("Wrong file");
//		}
//		
//	}
	
	private static class Registrar extends User {
		/**
		 * Creates a new registrar object. Registrars have all the fields that Users do, 
		 * so the super constructor can be called within this constructor
		 * @param firstName first name of the registrar
		 * @param lastName last name of the registrar
		 * @param id of the registrar 
		 * @param email of the registrar
		 * @param hashPW the registrar's password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}