package io.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {
	 
	MathUtils mUtils;
	/**
	 * Please run this before anything else @BeforeEach
	 */
	@BeforeEach
	void init() {
		mUtils = new MathUtils();
	}
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up.....");
	}
	
	@DisplayName("Addition")
	@Test
	void test() {
		//System.out.println("This tets ran");
		//MathUtils mUtils = new MathUtils();
		int expected = 2;
		int actual = mUtils.add(1, 1);
		assertEquals(expected, actual,"adding Numbers");
		
	}

	@Test
	void testComputeCircleRadius() {
		//MathUtils mUtils = new MathUtils();
		int expected = 2;
		assertEquals(314.1592653589793, mUtils.computeCircleRadius(10),"calculating radius");
	}
	
	@Test
	void testDivide() {
		//MathUtils mUtils = new MathUtils();
		assertThrows(ArithmeticException.class, () -> mUtils.divide(1, 0), "divide by Zero exception");
		//mUtils.divide(1, 0);
	}
	
	@Nested
	class AddTest {
		
		@Test
		@DisplayName("Multiple Addition ")
		void testAdd() {
			//assertEquals(4, mUtils.add(2, 2),"Addition");
			//Using lambdas multiple assert statements
			assertAll(
					() -> assertEquals(4, mUtils.add(2, 2)),
					() -> assertEquals(2, mUtils.add(2, 0)),
					() -> assertEquals(1, mUtils.add(2, -1))
					);
		}
		
		@Test
		@DisplayName("Multiple Addition ")
		void testAddPositive() {
			assertAll(
					() -> assertEquals(4, mUtils.add(2, 2))
					);
		}
		
	}
	
}
