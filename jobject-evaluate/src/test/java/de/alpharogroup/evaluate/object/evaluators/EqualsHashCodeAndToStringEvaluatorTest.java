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
package de.alpharogroup.evaluate.object.evaluators;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link EqualsHashCodeAndToStringEvaluator}.
 */
public class EqualsHashCodeAndToStringEvaluatorTest
{

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsAndHashcode(Object, Object, Object, Object)}.
	 */
	@Test
	public void testEvaluateEqualsAndHashcode()
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(Integer.valueOf(0),
			null, Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(null,
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsAndHashcodeEquality(Object, Object)}.
	 */
	@Test
	public void testEvaluateEqualsAndHashcodeEquality()
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeEquality(Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeEquality(Integer.valueOf(1), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcodeEquality(null,
			Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeEquality(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsAndHashcodeUnequality(Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEvaluateEqualsAndHashcodeUnequality()
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeUnequality(Integer.valueOf(0), Integer.valueOf(1));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeUnequality(Integer.valueOf(1), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsAndHashcodeUnequality(null,
			Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsAndHashcodeUnequality(Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsHashcodeAndToString(Object, Object, Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEvaluateEqualsHashcodeAndToString()
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(
			Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(
			Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(null,
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(
			Integer.valueOf(0), Integer.valueOf(1), null, Integer.valueOf(0));
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(
			Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0), null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsHashcodeAndToString(Class)}
	 * 
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testEvaluateEqualsHashcodeAndToStringClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		boolean expected;
		boolean actual;

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString((Class<?>)null);
		expected = false;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(Person.class);
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(Integer.class);
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(String.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringEvaluator#evaluateEqualsHashcodeAndToString(Object)}
	 */
	@Test(enabled = true)
	public void testEvaluateEqualsHashcodeAndToStringSingleObject()
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(Integer.valueOf(0));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(String.valueOf("foo"));
		expected = true;
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString((Object)null);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsHashCodeAndToStringEvaluator} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(EqualsHashCodeAndToStringEvaluator.class);
	}

}
