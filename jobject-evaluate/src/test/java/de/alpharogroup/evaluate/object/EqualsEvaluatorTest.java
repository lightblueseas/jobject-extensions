/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
	public void testEvaluateConsistency() throws Exception
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
	public void testEvaluateConsistencyWithIterations() throws Exception
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
