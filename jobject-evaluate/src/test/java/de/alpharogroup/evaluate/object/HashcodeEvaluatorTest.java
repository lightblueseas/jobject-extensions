package de.alpharogroup.evaluate.object;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link HashcodeEvaluator}.
 */
public class HashcodeEvaluatorTest
{

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateConsistency(Object)}.
	 */
	@Test
	public void testEvaluateConsistency() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = HashcodeEvaluator.evaluateConsistency(Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(Integer.valueOf(10));
		expected = true;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateEquality(Object, Object)}.
	 */
	@Test
	public void testEvaluateEquality() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = HashcodeEvaluator.evaluateEquality(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);
		
		actual = HashcodeEvaluator.evaluateEquality(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);
		
		actual = HashcodeEvaluator.evaluateEquality(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateEquality(Object, Object)}.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testEvaluateEqualityExpectedException01() throws Exception
	{
		HashcodeEvaluator.evaluateEquality(Integer.valueOf(1), Integer.valueOf(0));
	}

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateEquality(Object, Object)}.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testEvaluateEqualityExpectedException02() throws Exception
	{
		HashcodeEvaluator.evaluateEquality(Integer.valueOf(1), null);
	}

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateUnequality(Object, Object)}.
	 */
	@Test
	public void testEvaluateUnequality() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = HashcodeEvaluator.evaluateUnequality(Integer.valueOf(0), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateUnequality(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateUnequality(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateUnequality(Integer.valueOf(1), null);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeEvaluator#evaluateUnequality(Object, Object)}.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testEvaluateUnequalityExpectedException01() throws Exception
	{
		HashcodeEvaluator.evaluateUnequality(Integer.valueOf(0), Integer.valueOf(0));		
	}

}
