/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.diff.beans;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.evaluators.EqualsEvaluator;
import de.alpharogroup.evaluate.object.evaluators.HashcodeEvaluator;
import de.alpharogroup.evaluate.object.evaluators.ToStringEvaluator;

/**
 * The unit test class for the class {@link ChangedAttributeResult}
 */
public class ChangedAttributeResultTest
{

	/**
	 * Test method for {@link ChangedAttributeResult#equals(Object)}
	 */
	@Test
	public void testEqualsObject()
	{
		final ChangedAttributeResult expected = ChangedAttributeResult.builder()
			.attributeName("foo").build();
		final ChangedAttributeResult actual = new ChangedAttributeResult();

		assertNotSame(expected, actual);
		final ChangedAttributeResult attributeResult = new ChangedAttributeResult();
		attributeResult.setAttributeName("foo");
		assertEquals(expected, attributeResult);
		assertTrue(
			EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(expected, actual));
		assertTrue(
			EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(expected,
				attributeResult, ChangedAttributeResult.builder().attributeName("foo").build()));
	}

	/**
	 * Test method for {@link ChangedAttributeResult#hashCode()}
	 */
	@Test
	public void testHashcode()
	{
		boolean expected;
		boolean actual;
		final ChangedAttributeResult attributeResult1 = ChangedAttributeResult.builder().build();
		final ChangedAttributeResult attributeResult2 = ChangedAttributeResult.builder().build();
		actual = HashcodeEvaluator.evaluateEquality(attributeResult1, attributeResult2);
		expected = true;
		assertEquals(expected, actual);

		expected = true;
		actual = HashcodeEvaluator.evaluateUnequality(attributeResult1,
			ChangedAttributeResult.builder().attributeName("foo").build());
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(attributeResult1);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ChangedAttributeResult#toString()}
	 */
	@Test
	public void testToString()
	{
		boolean expected;
		boolean actual;
		actual = ToStringEvaluator.evaluate(ChangedAttributeResult.class);
		expected = true;
		assertEquals(expected, actual);

		final ChangedAttributeResult integerBox = ChangedAttributeResult.builder().build();

		actual = ToStringEvaluator.evaluateConsistency(integerBox);
		expected = true;
		assertEquals(expected, actual);
	}

}
