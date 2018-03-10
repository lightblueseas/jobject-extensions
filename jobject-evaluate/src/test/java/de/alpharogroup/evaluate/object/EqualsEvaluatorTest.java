package de.alpharogroup.evaluate.object;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link EqualsEvaluator}.
 */
public class EqualsEvaluatorTest
{

	/**
	 * Test method for {@link EqualsEvaluator#evaluateReflexivity(Object)}.
	 */
	@Test
	public void testEvaluateReflexivity() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateReflexivity(Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivity(Integer.valueOf(10));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivity(Integer.valueOf(100));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivity(null);
		expected = false;
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateSymmetric(Object, Object)}.
	 */
	@Test
	public void testEvaluateSymmetric() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateSymmetric(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetric(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetric(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetric(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetric(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateTransitivity(Object, Object, Object)}.
	 */
	@Test
	public void testEvaluateTransitivity() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateTransitivity(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateTransitivity(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateTransitivity(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateTransitivity(null, Integer.valueOf(1), Integer.valueOf(1));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateTransitivity(Integer.valueOf(1), null, Integer.valueOf(1));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateTransitivity(Integer.valueOf(1), Integer.valueOf(1), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateNonNull(Object)}.
	 */
	@Test
	public void testEvaluateNonNull() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateNonNull(Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateNonNull(Integer.valueOf(10));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateNonNull(Integer.valueOf(100));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateNonNull(null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateConsistency(Object, Object)}.
	 */
	@Test
	public void testEvaluateConsistencyTT() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateConsistency(Object, Object, int)}.
	 */
	@Test
	public void testEvaluateConsistencyTTInt() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(0), Integer.valueOf(0), 10);
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(0), 20);
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(1), Integer.valueOf(1), 100);
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(null, Integer.valueOf(0), 10);
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateConsistency(Integer.valueOf(0), null, 10);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateReflexivityAndNonNull(Object)}.
	 */
	@Test
	public void testEvaluateReflexivityAndNonNull() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateReflexivityAndNonNull(Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityAndNonNull(Integer.valueOf(10));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityAndNonNull(Integer.valueOf(100));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityAndNonNull(null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsEvaluator#evaluateSymmetricAndConsistency(Object, Object)}.
	 */
	@Test
	public void testEvaluateSymmetricAndConsistency() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateSymmetricAndConsistency(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetricAndConsistency(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetricAndConsistency(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetricAndConsistency(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateSymmetricAndConsistency(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsEvaluator#evaluateReflexivityNonNullSymmetricAndConsistency(Object, Object)}.
	 */
	@Test
	public void testEvaluateReflexivityNonNullSymmetricAndConsistency() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsEvaluator#evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Object, Object, Object)}.
	 */
	@Test
	public void testEvaluateReflexivityNonNullSymmetricConsistencyAndTransitivity() throws Exception
	{
		boolean expected;
		boolean actual;
		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(null, Integer.valueOf(1), Integer.valueOf(1));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Integer.valueOf(1), null, Integer.valueOf(1));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(Integer.valueOf(1), Integer.valueOf(1), null);
		expected = false;
		assertEquals(expected, actual);
	}

}
