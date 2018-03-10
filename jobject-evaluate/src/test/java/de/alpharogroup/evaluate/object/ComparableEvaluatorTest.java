package de.alpharogroup.evaluate.object;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ComparableEvaluator}.
 */
public class ComparableEvaluatorTest
{

	/**
	 * Test method for {@link ComparableEvaluator#evaluateConsistency(Comparable, Comparable)}.
	 */
	@Test(enabled = true)
	public void testEvaluateConsistency() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = ComparableEvaluator.evaluateConsistency(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);
		
		actual = ComparableEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);
		
		actual = ComparableEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);
		
	}

	/**
	 * Test method for
	 * {@link ComparableEvaluator#evaluateReversalComparison(Comparable, Comparable)}.
	 */
	@Test(enabled = true)
	public void testEvaluateReversalComparison() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = ComparableEvaluator.evaluateReversalComparison(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);
		
		actual = ComparableEvaluator.evaluateReversalComparison(Integer.valueOf(100), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);
		
		actual = ComparableEvaluator.evaluateReversalComparison(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ComparableEvaluator#evaluateTransitivity(Comparable, Comparable, Comparable)}.
	 */
	@Test(enabled = true)
	public void testEvaluateTransitivity() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = ComparableEvaluator.evaluateTransitivity(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = ComparableEvaluator.evaluateTransitivity(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = ComparableEvaluator.evaluateTransitivity(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);
	}

}
