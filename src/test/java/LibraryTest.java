/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class LibraryTest {

    @Test public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }
    @Test public void tax() {
    	Library library = new Library();
    	double result = 108;
    	double addTax = library.tax(100);
    	assertThat(addTax, is(result));
    }

}
