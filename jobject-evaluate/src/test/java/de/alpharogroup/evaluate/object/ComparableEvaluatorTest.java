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
