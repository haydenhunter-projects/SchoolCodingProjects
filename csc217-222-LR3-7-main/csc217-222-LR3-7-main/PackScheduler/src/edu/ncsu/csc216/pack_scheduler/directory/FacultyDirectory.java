package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Faculty directory class
 * 
 * @author smithej
 *
 */
public class FacultyDirectory {
	/** list of Faculty objects stored in the directory */
	private LinkedList<Faculty> facultyDirectory;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Faculty Directory Constructor. Creates a new faculty directory object.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * Creates a new list of Faculty objects. The list is called facultyDirectory.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads Faculty objects from file. Calls FacultyRecordIO to read the 
	 * file and create Faculty objects from the information on the file. The list
	 * of Faculty that is created is set to the facultyDirectory. 
	 * @param filename the name of the file with faculty information
	 */
	public void loadFacultyFromFile(String filename) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	}
	
	/**
	 * Adds Faculty object to facultyDirectory. Receives all the information required
	 * to make a Faculty object and calls the Faculty constructor to create the object.
	 * The newly created object is added to the facultyDirectory. 
	 * @param firstName the Faculty's first name
	 * @param lastName the Faculty's last name
	 * @param id the Faculty's id
	 * @param email the Faculty's email
	 * @param password the Faculty's password
	 * @param repeatPassword the Faculty's password repeated
	 * @param maxCourses the Faculty's maxCourses
	 * @return true if added false if not
	 * @throws IllegalArgumentException if there are issues with the fields needed to create the Faculty
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) throws IllegalArgumentException {
		
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(id)) {
				return false;
			}
		}
		
		
		return facultyDirectory.add(new Faculty(firstName, lastName, id, email, hashPW, maxCourses));
		
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if password cannot be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	
	/**
	 * Removes the entered Faculty. Removes the Faculty with the matching
	 * id from the facultyDirectory. If no matching id is found, nothing
	 * happens. 
	 * @param id id of the Faculty member to be removed
	 * @return true if successful in removing Faculty
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
		
	}
	
	
	/**
	 * Returns all students in the directory with a column for first name, last name, and id.
	 * The columns hold the information in that order.
	 * @return String array containing students first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}
	
	/**
	 * Saves facultyDirectory to file. Calls FacultyRecordIO to 
	 * write the facultyDirectory to the entered file.
	 * 
	 * @param filename the name of the file to write the Faculty information to
	 */
	public void saveFacultyDirectory(String filename) {
		try {
			FacultyRecordIO.writeFacultyRecords(filename, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets the Faculty with the entered id. If to faculty
	 * with a matching id is found, null is returned. 
	 * @param id the id of the Faculty to get
	 * @return faculty the Faculty that was retrieved or null if none was found
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}
}
