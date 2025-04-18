package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;



/**
 * Tests Schedule class
 * @author saket
 *
 */
public class ScheduleTest {

	/** Course name */
    private static final String NAME = "CSC316";
    /** Course title */
    private static final String TITLE = "Software Advanced";
    /** Course section */
    private static final String SECTION = "004";
    /** Course credits */
    private static final int CREDITS = 5;
    /** Course instructor id */
    private static final String INSTRUCTOR_ID = "sesmith5";
    /** Course meeting days */
    private static final String MEETING_DAYS = "MWF";
    /** Course start time */
    private static final int START_TIME = 1330;
    /** Course end time */
    private static final int END_TIME = 1445;
 
    
    
    /**
     * Tests Schedule constructor
     */
    @Test
    public void testSchedule() {
       Schedule schedule = assertDoesNotThrow(() -> new Schedule());
        assertEquals("My Schedule", schedule.getTitle());
    }
    /**
     * Tests addcoursetoSchedule
     */
    @Test
    public void testAddCourseToSchedule() {
       Schedule schedule = new Schedule();
        Course c = assertDoesNotThrow(
                () -> new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME),
                "Should not throw exception");
        schedule.addCourseToSchedule(c);
        
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> schedule.addCourseToSchedule(c));
        assertEquals("You are already enrolled in CSC316", e2.getMessage());

    }
    
    /**
     * Tests RemovecourseFromSchedule
     */
    @Test
    public void testRemoveCourseFromSchedule() {
       Schedule schedule = new Schedule();
       schedule.resetSchedule();
       Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
       
       schedule.addCourseToSchedule(c);

       assertTrue(schedule.removeCourseFromSchedule(c));
    }
    
    /**
     * Tests set title
     */
    @Test
    public void testSetTitle() {
       Schedule schedule = new Schedule();
        schedule.setTitle("New Schedule");
        assertEquals("New Schedule", schedule.getTitle());

        Exception e1 = assertThrows(IllegalArgumentException.class, () -> schedule.setTitle(null));
        assertEquals("Title cannot be null.", e1.getMessage());
    }
    /**
     * Tests Reset Schedule
     */
    @Test
   public void testResetSchedule() {
        Schedule schedule = new Schedule();
        schedule.setTitle("New Schedule");
        Course c = assertDoesNotThrow(
                () -> new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10,  MEETING_DAYS, START_TIME, END_TIME),
                "Should not throw exception");
        schedule.addCourseToSchedule(c);
        assertEquals(1, schedule.getScheduledCourses().length);
        schedule.removeCourseFromSchedule(c);
        assertEquals(0, schedule.getScheduledCourses().length);

        schedule.addCourseToSchedule(c);
        schedule.resetSchedule();
        assertEquals(0, schedule.getScheduledCourses().length);
    }
    
    
	

}
