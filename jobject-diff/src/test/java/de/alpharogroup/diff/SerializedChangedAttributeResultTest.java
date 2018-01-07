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
package de.alpharogroup.diff;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import de.alpharogroup.diff.beans.SerializedChangedAttributeResult;
import de.alpharogroup.test.objects.evaluations.EqualsEvaluator;
import de.alpharogroup.test.objects.evaluations.HashcodeEvaluator;
import de.alpharogroup.test.objects.evaluations.ToStringEvaluator;

/**
 * The unit test class for the class {@link SerializedChangedAttributeResult}.
 */
public class SerializedChangedAttributeResultTest
{

	/**
	 * Test method for {@link SerializedChangedAttributeResult#equals(Object)}
	 */
	@Test
	public void testEqualsObject()
	{
		final SerializedChangedAttributeResult expected = SerializedChangedAttributeResult
			.builder().attributeName("foo").sourceAttribute("").changedAttribute("").build();
		final SerializedChangedAttributeResult actual = new SerializedChangedAttributeResult();

		assertNotSame(expected, actual);
		final SerializedChangedAttributeResult attributeResult = new SerializedChangedAttributeResult();
		attributeResult.setAttributeName("foo");
		attributeResult.setSourceAttribute("");
		attributeResult.setChangedAttribute("");
		assertEquals(expected, attributeResult);
		assertTrue(EqualsEvaluator.evaluateReflexivityNonNullSymmetricAndConsistency(expected, actual));
		assertTrue(EqualsEvaluator.evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(expected, attributeResult, SerializedChangedAttributeResult
				.builder().attributeName("foo").sourceAttribute("").changedAttribute("").build()));
	}

	/**
	 * Test method for {@link SerializedChangedAttributeResult#hashCode()}
	 */
	@Test
	public void testHashcode()
	{
		boolean expected;
		boolean actual;
		final SerializedChangedAttributeResult attributeResult1 = SerializedChangedAttributeResult
			.builder().attributeName("foo").sourceAttribute("").changedAttribute("").build();
		final SerializedChangedAttributeResult attributeResult2 =SerializedChangedAttributeResult
			.builder().attributeName("foo").sourceAttribute("").changedAttribute("").build();
		actual = HashcodeEvaluator.evaluateEquality(attributeResult1, attributeResult2);
		expected = true;
		assertEquals(expected, actual);

		expected = true;
		actual = HashcodeEvaluator.evaluateUnequality(attributeResult1, SerializedChangedAttributeResult
			.builder().attributeName("bar").sourceAttribute("").changedAttribute("").build());
		assertEquals(expected, actual);

		actual = HashcodeEvaluator.evaluateConsistency(attributeResult1);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link SerializedChangedAttributeResult#toString()}
	 */
	@Test
	public void testToString()
	{
		boolean expected;
		boolean actual;
		actual = ToStringEvaluator.evaluate(SerializedChangedAttributeResult.class);
		expected = true;
		assertEquals(expected, actual);

		final SerializedChangedAttributeResult integerBox = SerializedChangedAttributeResult
			.builder().attributeName("foo").sourceAttribute("").changedAttribute("").build();

		actual = ToStringEvaluator.evaluateConsistency(integerBox);
		expected = true;
		assertEquals(expected, actual);
	}

}
