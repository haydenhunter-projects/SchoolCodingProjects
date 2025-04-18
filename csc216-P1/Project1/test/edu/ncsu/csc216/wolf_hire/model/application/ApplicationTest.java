package edu.ncsu.csc216.wolf_hire.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;

/**
 * Tests Application.java
 * 
 * @author hchunter
 *
 */
public class ApplicationTest {

	/**
	 * A constant string for the rejection reason of Qualifications.
	 */
	public static final String QUALIFICATIONS_REJECTION = "Qualifications";

	/**
	 * A constant string for the rejection reason of Incomplete
	 */
	public static final String INCOMPLETE_REJECTION = "Incomplete";

	/**
	 * A constant string for the rejection reason of Positions.
	 */
	public static final String POSITIONS_REJECTION = "Positions";

	/**
	 * A constant string for the rejection reason of Duplicate.
	 */
	public static final String DUPLICATE_REJECTION = "Duplicate";

	/**
	 * A constant string for the priority of Completed.
	 */
	public static final String COMPLETED_TERMINATION = "Completed";

	/**
	 * A constant string for the priority of Resigned.
	 */
	public static final String RESIGNED_TERMINATION = "Resigned";

	/**
	 * A constant string for the priority of Fired.
	 */
	public static final String FIRED_TERMINATION = "Fired";

	/**
	 * resets application set counter to 0 before every test
	 * 
	 * @throws Exception if it cannot reset counter
	 */
	@Before
	public void setUp() throws Exception {
		// Reset the counter at the beginning of every test.
		Application.setCounter(0);
	}

	/**
	 * tests the application constructor for -new- applications in the submitted
	 * state
	 */
	@Test
	public void testApplicationConstructor1() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Application b1 = new Application("hayden", "hunter", "hchunter");
		assertEquals("hayden", b1.getFirstName());
		assertEquals("hunter", b1.getSurname());
		assertEquals("hchunter", b1.getUnityId());

		Exception c1e1 = assertThrows(IllegalArgumentException.class, () -> new Application("", "hunter", "hchunter"));
		assertEquals("Application cannot be created.", c1e1.getMessage());

		Exception c1e2 = assertThrows(IllegalArgumentException.class, () -> new Application("hayden", "", "hchunter"));
		assertEquals("Application cannot be created.", c1e2.getMessage());

		Exception c1e3 = assertThrows(IllegalArgumentException.class, () -> new Application("hayden", "hunter", ""));
		assertEquals("Application cannot be created.", c1e3.getMessage());

		Exception c1e4 = assertThrows(IllegalArgumentException.class,
				() -> new Application(null, "hunter", "hchunter"));
		assertEquals("Application cannot be created.", c1e4.getMessage());

		Exception c1e5 = assertThrows(IllegalArgumentException.class,
				() -> new Application("hayden", null, "hchunter"));
		assertEquals("Application cannot be created.", c1e5.getMessage());

		Exception c1e6 = assertThrows(IllegalArgumentException.class, () -> new Application("hayden", "hunter", null));
		assertEquals("Application cannot be created.", c1e6.getMessage());

	}

	/**
	 * tests the application constructor used for -read in from file- applications
	 */
	@Test
	public void testApplicationConstructor2() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Application b2 = new Application(1, "Submitted", "hayden", "hunter", "hchunter", null, null);

		assertEquals(1, b2.getId());
		assertEquals("Submitted", b2.getState());
		assertEquals("hayden", b2.getFirstName());
		assertEquals("hunter", b2.getSurname());
		assertEquals("", b2.getReviewer());
		assertEquals("", b2.getNote());

		Application b3 = new Application(5, "Submitted", "hayden", "hunter", "hchunter", null, null);
		assertEquals(5, b3.getId());

		Exception c2e1 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", "", "hunter", "hchunter", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e1.getMessage());

		Exception c2e2 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", "hayden", "", "hchunter", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e2.getMessage());

		Exception c2e3 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", "hayden", "hunter", "", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e3.getMessage());

		Exception c2e4 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", null, "hunter", "hchunter", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e4.getMessage());

		Exception c2e5 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", "hayden", null, "hchunter", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e5.getMessage());

		Exception c2e6 = assertThrows(IllegalArgumentException.class,
				() -> new Application(5, "Submitted", "hayden", "hunter", null, "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", c2e6.getMessage());

		// inactive state, duplicate message
		Exception ci2 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "Carol", "Schmidt", "cschmid", "sesmith5", "Duplicate"));
		assertEquals("Application cannot be created.", ci2.getMessage());

	}
	
	/**
	 * tests all the invalid cases of application constructor
	 * 
	 */
	@Test
	public void testApplicationConstructor3() {
		
		//Submitted state exceptions
		Exception a = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Submitted", "hayden", "hunter", "hchunter", "jgoldberg", null));
		assertEquals("Application cannot be created.", a.getMessage());
		
		Exception a1 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Submitted", "hayden", "hunter", "hchunter", null, "Qualifications"));
		assertEquals("Application cannot be created.", a1.getMessage());
		
		//rejected state exceptions
		Exception a2 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Rejected", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a2.getMessage());
		
		Exception a3 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Rejected", "hayden", "hunter", "hchunter", "jgoldberg", "Duplicate"));
		assertEquals("Application cannot be created.", a3.getMessage());
		
		Exception a4 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Rejected", "hayden", "hunter", "hchunter", "jgoldberg", "Completed"));
		assertEquals("Application cannot be created.", a4.getMessage());
		
		Exception a5 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Rejected", "hayden", "hunter", "hchunter", "jgoldberg", "Resigned"));
		assertEquals("Application cannot be created.", a5.getMessage());
		
		Exception a6 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Rejected", "hayden", "hunter", "hchunter", "jgoldberg", "Fired"));
		assertEquals("Application cannot be created.", a6.getMessage());
		
		//Reviewing State exceptions
		Exception a7 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Reviewing", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a7.getMessage());
		
		Exception a8 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Reviewing", "hayden", "hunter", "hchunter", "", null));
		assertEquals("Application cannot be created.", a8.getMessage());
		
		Exception a9 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Reviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Completed"));
		assertEquals("Application cannot be created.", a9.getMessage());
		
		Exception a10 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Reviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Resigned"));
		assertEquals("Application cannot be created.", a10.getMessage());
		
		Exception a11 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Reviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Fired"));
		assertEquals("Application cannot be created.", a11.getMessage());
		
		//Interviewing state exceptions
		Exception a12 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Interviewing", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a12.getMessage());
		
		Exception a13 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Interviewing", "hayden", "hunter", "hchunter", "", null));
		assertEquals("Application cannot be created.", a13.getMessage());
		
		Exception a14 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Completed"));
		assertEquals("Application cannot be created.", a14.getMessage());
		
		Exception a15 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Resigned"));
		assertEquals("Application cannot be created.", a15.getMessage());
		
		Exception a16 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg", "Fired"));
		assertEquals("Application cannot be created.", a16.getMessage());
		
		//Processing state exceptions
		Exception a17 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Processing", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a17.getMessage());
		
		Exception a18 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Processing", "hayden", "hunter", "hchunter", "", null));
		assertEquals("Application cannot be created.", a18.getMessage());
		
		Exception a19 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Processing", "hayden", "hunter", "hchunter", "jgoldberg", "Completed"));
		assertEquals("Application cannot be created.", a19.getMessage());
		
		Exception a20 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Processing", "hayden", "hunter", "hchunter", "jgoldberg", "Resigned"));
		assertEquals("Application cannot be created.", a20.getMessage());
		
		Exception a21 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Processing", "hayden", "hunter", "hchunter", "jgoldberg", "Fired"));
		assertEquals("Application cannot be created.", a21.getMessage());
		
		//Hired exceptions
		Exception a22 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Hired", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a22.getMessage());
		
		Exception a23 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Hired", "hayden", "hunter", "hchunter", "", null));
		assertEquals("Application cannot be created.", a23.getMessage());
		
		Exception a24 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Hired", "hayden", "hunter", "hchunter", "jgoldberg", "Completed"));
		assertEquals("Application cannot be created.", a24.getMessage());
		
		Exception a25 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Hired", "hayden", "hunter", "hchunter", "jgoldberg", "Resigned"));
		assertEquals("Application cannot be created.", a25.getMessage());
		
		Exception a26 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Hired", "hayden", "hunter", "hchunter", "jgoldberg", "Fired"));
		assertEquals("Application cannot be created.", a26.getMessage());
		
		//Inactive exceptions
		
		Exception a27 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "hayden", "hunter", "hchunter", null, null));
		assertEquals("Application cannot be created.", a27.getMessage());
		
		Exception a28 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "hayden", "hunter", "hchunter", "", null));
		assertEquals("Application cannot be created.", a28.getMessage());
		
		Exception a29 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "hayden", "hunter", "hchunter", "jgoldberg", "Qualifications"));
		assertEquals("Application cannot be created.", a29.getMessage());
		
		Exception a30 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "hayden", "hunter", "hchunter", "jgoldberg", "Incomplete"));
		assertEquals("Application cannot be created.", a30.getMessage());
		
		Exception a31 = assertThrows(IllegalArgumentException.class,
				() -> new Application(1, "Inactive", "hayden", "hunter", "hchunter", "jgoldberg", "Positions"));
		assertEquals("Application cannot be created.", a31.getMessage());
	}
	

	/**
	 * tests to string for application
	 * 
	 */
	@Test
	public void testToString() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Application b3 = new Application(1, "Submitted", "hayden", "hunter", "hchunter", null, null);
		assertEquals("* 1,Submitted,hayden,hunter,hchunter,,", b3.toString());
	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateSubmittedA() {
		Command c = new Command(CommandValue.ASSIGN, "Default Message");
		Application a = new Application("hayden", "hunter", "hchunter");
		assertEquals("Submitted", a.getState());
		a.update(c);
		assertEquals("Reviewing", a.getState());

	}

	/**
	 * tests changing a submitted state to a rejected state
	 */
	@Test
	public void testUpdateSubmittedB() {
		Command c1 = new Command(CommandValue.REJECT, QUALIFICATIONS_REJECTION);
		Application a1 = new Application("joe", "goldberg", "jgoldberg");
		a1.update(c1);
		assertEquals("Rejected", a1.getState());
		assertEquals("Qualifications", a1.getNote());
	}

	/**
	 * tests moving a rejected state to a reviewing state
	 */
	@Test
	public void testUpdateRejectedA() {
		Command c = new Command(CommandValue.RESUBMIT, null);
		Application b = new Application(5, "Rejected", "hayden", "hunter", "hchunter", null, "Qualifications");
		b.update(c);
		assertEquals("Submitted", b.getState());
		assertEquals("", b.getNote());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateReviewingB() {
		Command c = new Command(CommandValue.RETURN, null);
		Application a = new Application(5, "Reviewing", "hayden", "hunter", "hchunter", "literally anything",
				"literally anything");
		a.update(c);
		assertEquals("Submitted", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateReviewingA() {
		Command c = new Command(CommandValue.SCHEDULE, null);
		Application b = new Application(5, "Reviewing", "hayden", "hunter", "hchunter", "literally anything",
				"literally anything");
		b.update(c);
		assertEquals("Interviewing", b.getState());
		assertEquals(null, c.getCommandInformation());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateReviewingC() {
		Command c = new Command(CommandValue.REJECT, DUPLICATE_REJECTION);
		Application c1 = new Application(5, "Reviewing", "hayden", "hunter", "hchunter", "literally anything",
				"literally anything");
		c1.update(c);
		assertEquals("Rejected", c1.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateInterviewingA() {
		Command c = new Command(CommandValue.PROCESS, null);
		Application a = new Application(5, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg",
				"Qualifications");
		a.update(c);
		assertEquals("Processing", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateInterviewingB() {
		Command c = new Command(CommandValue.ASSIGN, "reviewer");
		Application a = new Application(5, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg",
				"Qualifications");
		a.update(c);
		assertEquals("Reviewing", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateInterviewingC() {
		Command c = new Command(CommandValue.SCHEDULE, null);
		Application a = new Application(5, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg",
				"Qualifications");
		a.update(c);
		assertEquals("Interviewing", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateInterviewingD() {
		Command c = new Command(CommandValue.REJECT, "Qualifications");
		Application a = new Application(5, "Interviewing", "hayden", "hunter", "hchunter", "jgoldberg",
				"Qualifications");
		a.update(c);
		assertEquals("Rejected", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateProcessingA() {
		Command c = new Command(CommandValue.HIRE, null);
		Application a = new Application(5, "Processing", "hayden", "hunter", "hchunter", "literally anything",
				"literally anything");
		a.update(c);
		assertEquals("Hired", a.getState());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateProcessingB() {
		Command c = new Command(CommandValue.REJECT, "Qualifications");
		Application a = new Application(5, "Processing", "hayden", "hunter", "hchunter", "literally anything",
				"literally anything");
		a.update(c);
		assertEquals("Rejected", a.getState());

	}

	/**
	 * tests invalid transitions of processing
	 */
	@Test
	public void testUpdateProcessingInvalid() {
		Command ci = new Command(CommandValue.REJECT, "testing purposes");
		Command c1 = new Command(CommandValue.REJECT, QUALIFICATIONS_REJECTION);
		Command c2 = new Command(CommandValue.REJECT, INCOMPLETE_REJECTION);
		Command c3 = new Command(CommandValue.REJECT, POSITIONS_REJECTION);
		Command c4 = new Command(CommandValue.REJECT, DUPLICATE_REJECTION);

		Application b = new Application(6, "Processing", "joe", "goldberg", "jgoldb", "literally anything",
				"Qualifications");
		Application b1 = new Application(6, "Processing", "joe", "goldberg", "jgoldb", "literally anything",
				"Qualifications");
		Application b2 = new Application(6, "Processing", "joe", "goldberg", "jgoldb", "literally anything",
				"Qualifications");
		Application b3 = new Application(6, "Processing", "joe", "goldberg", "jgoldb", "literally anything",
				"Qualifications");
		Application b4 = new Application(6, "Processing", "joe", "goldberg", "jgoldb", "literally anything",
				"Qualifications");

		// valid rejection reason
		b1.update(c1);
		b2.update(c2);
		b3.update(c3);
		b4.update(c4);

		Exception ci1 = assertThrows(UnsupportedOperationException.class, () -> b.update(ci));
		assertEquals("Invalid command", ci1.getMessage());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateHiredA() {
		Command c = new Command(CommandValue.TERMINATE, RESIGNED_TERMINATION);
		Application a = new Application(5, "Hired", "hayden", "hunter", "hchunter", "jgoldberg", "literally anything");
		a.update(c);
		assertEquals("Inactive", a.getState());
		assertEquals("Resigned", a.getNote());

	}

	/**
	 * tests moving a submitted state to a reviewing state
	 */
	@Test
	public void testUpdateInactiveA() {

		// invalid
		Command ci = new Command(CommandValue.ASSIGN, "testing purposes");
		Application b = new Application(6, "Inactive", "joe", "goldberg", "jgoldb", "literally anything", "Completed");

		Exception ci1 = assertThrows(UnsupportedOperationException.class, () -> b.update(ci));
		assertEquals("Invalid command", ci1.getMessage());

	}
	
	
}
