package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;

/**
 * Tests PositionTest.java
 * 
 * @author hchunter
 *
 */
public class PositionTest {

	/**
	 * run set up before each test
	 * 
	 * @throws Exception if it cannot reset counter
	 */
	@Before
	public void setUp() throws Exception {
		// Reset the counter at the beginning of every test.
		Application.setCounter(0);
	}

	/**
	 * tests the position constructor
	 */
	@Test
	public void testPositionConstructor() {
		Position p1 = new Position("CSC 216 PTF", 12, 12);
		assertEquals("CSC 216 PTF", p1.getPositionName());
		assertEquals(12, p1.getHoursPerWeek());
		assertEquals(12, p1.getPayRate());
		assertEquals(0, p1.getApplications().size());

		Exception c1e1 = assertThrows(IllegalArgumentException.class, () -> new Position("", 12, 12));
		assertEquals("Position cannot be created.", c1e1.getMessage());

		Exception c1e2 = assertThrows(IllegalArgumentException.class, () -> new Position(null, 12, 12));
		assertEquals("Position cannot be created.", c1e2.getMessage());

		Exception c1e3 = assertThrows(IllegalArgumentException.class, () -> new Position("CSC 216 PTF", 0, 12));
		assertEquals("Position cannot be created.", c1e3.getMessage());

		Exception c1e4 = assertThrows(IllegalArgumentException.class, () -> new Position("CSC 216 PTF", 21, 12));
		assertEquals("Position cannot be created.", c1e4.getMessage());

		Exception c1e5 = assertThrows(IllegalArgumentException.class, () -> new Position("CSC 216 PTF", 12, 6));
		assertEquals("Position cannot be created.", c1e5.getMessage());

		Exception c1e6 = assertThrows(IllegalArgumentException.class, () -> new Position("CSC 216 PTF", 12, 36));
		assertEquals("Position cannot be created.", c1e6.getMessage());

	}

	/**
	 * tests addApplication
	 */
	@Test
	public void testAddApplication1() {
		Application.setCounter(0);
		Application t1 = new Application("hayden", "hunter", "hchunter");
		assertEquals(0, t1.getId());

		Position p1 = new Position("CSC 116 PTF", 15, 15);
		assertEquals(1, p1.addApplication("hayden", "hunter", "hchunter"));

		p1.deleteApplicationById(t1.getId());
		assertEquals(0, t1.getId());
	}

	/**
	 * tests adding an application
	 */
	@Test
	public void testAddApplication2() {
		Application.setCounter(1);
		Application a1 = new Application("hayden", "hunter", "hchunter");
		Position p1 = new Position("CSC 116 PTF", 15, 15);
		p1.addApplication(a1);
		assertEquals(1, a1.getId());

//		Application a2 = new Application(1, "Rejected", "hayden", "hunter", "hchunter", null, "Qualifications");
//		Exception p1e1 = assertThrows(IllegalArgumentException.class, () -> p1.addApplication(a2));
//		assertEquals("Application cannot be created.", p1e1.getMessage());

	}

	/**
	 * tests adding an application
	 */
	@Test
	public void testAddApplication3() {
//		Application.setCounter(0);
		Position p1 = new Position("CSC 116 PTF", 15, 15);

		ArrayList<Application> applications = new ArrayList<Application>();
		applications = p1.getApplications();

		assertEquals(0, applications.size());

		p1.addApplication("henry", "george", "hgeorge");
		assertEquals(1, applications.size());

		assertEquals("henry", p1.getApplicationById(1).getFirstName());
		assertEquals("george", p1.getApplicationById(1).getSurname());
		assertEquals("hgeorge", p1.getApplicationById(1).getUnityId());
	}
	
	/**
	 * check for failing staff test
	 */
	@Test
	public void testStaffTestExcecuteCommand() {
		Position p = new Position("Test Person", 13, 13);
		
		assertEquals(0, p.getApplications().size());
		p.addApplication(new Application(2, "Rejected", "Clinton", "Armstrong", "carmstr", null, "Qualifications"));
		assertEquals(2, p.getApplications().get(0).getId());
		assertEquals("Rejected", p.getApplicationById(2).getState());
		
		assertEquals(1, p.getApplications().size());
		Command res = new Command(CommandValue.RESUBMIT, null);
		Command ass = new Command(CommandValue.ASSIGN, "test");
		Command sch = new Command(CommandValue.SCHEDULE, null);
		Command pro = new Command(CommandValue.PROCESS, null);
		Command hir = new Command(CommandValue.HIRE, null);
		Command ter = new Command(CommandValue.TERMINATE, "Fired");
		Command rej = new Command(CommandValue.REJECT, "Qualifications");
		
		
		p.executeCommand(2, res);
		assertEquals("Submitted", p.getApplicationById(2).getState());
		
		p.executeCommand(2, ass);
		assertEquals("Reviewing", p.getApplicationById(2).getState());
		
		p.executeCommand(2, sch);
		assertEquals("Interviewing", p.getApplicationById(2).getState());
		
		p.executeCommand(2, pro);
		assertEquals("Processing", p.getApplicationById(2).getState());
		
		p.executeCommand(2, hir);
		assertEquals("Hired", p.getApplicationById(2).getState());
		
		p.executeCommand(2, ter);
		assertEquals("Inactive", p.getApplicationById(2).getState());
		
		Exception ce = assertThrows(Exception.class,
				() -> p.executeCommand(2, rej));
		assertEquals("Invalid command.", ce.getMessage());
		assertEquals("Inactive", p.getApplicationById(2).getState());
		
		
		
	}

}
