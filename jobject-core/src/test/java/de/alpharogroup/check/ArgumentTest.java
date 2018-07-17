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
package de.alpharogroup.check;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;


/**
 * The unit test class for the class {@link Argument}.
 */
public class ArgumentTest
{

	String name;
	
	/**
	 * Test method for {@link Argument#isInRange(Comparable, Comparable, Comparable, String)}
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testIsInRange()
	{
		Double min;
		Double max;
		Double value;
		
		min = 0.0d;
		max = 5.0d;
		value = 6.0d;
		name = "parameter";
		Argument.isInRange(min, max, value, name);
	}

	/**
	 * Test method for {@link Argument#isInRange(Comparable, Comparable, Comparable, String)}
	 */
	@Test
	public void testIsInRangeNormalCase()
	{
		Double expected;
		Double actual;
		Double min;
		Double max;
		Double value;
		
		min = 0.0d;
		max = 5.0d;
		value = 4.0d;
		name = "parameter";

		expected = value;
		actual = Argument.isInRange(min, max, value, name);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(java.util.Collection, String)}
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testNotEmptyCollection()
	{
		List<String> list;
		
		name = "list";
		list = new ArrayList<>();

		Argument.notEmpty(list, name);
	}

	/**
	 * Test method for {@link Argument#notEmpty(java.util.Collection, String)}
	 */
	@Test
	public void testNotEmptyCollectionNormalCase()
	{
		List<String> expected;
		List<String> actual;
		
		name = "list";
		expected = new ArrayList<>();
		expected.add("foo");

		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(Map, String)}
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testNotEmptyMap()
	{
		Map<String, String> emptyMap;
		
		name = "map";
		emptyMap = new HashMap<>();
		Argument.notEmpty(emptyMap, name);
	}

	/**
	 * Test method for {@link Argument#notEmpty(Map, String)}
	 */
	@Test
	public void testNotEmptyMapNormalCase()
	{
		Map<String, String> expected;
		Map<String, String> actual;
		
		name = "map";
		expected = new HashMap<>();
		expected.put("foo", "bar");

		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(CharSequence, String)}
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testNotEmptyString()
	{
		String argument;
		
		name = "parameter";
		argument = "";
		Argument.notEmpty(argument, name);
	}

	/**
	 * Test method for {@link Argument#notEmpty(CharSequence, String)}
	 */
	@Test
	public void testNotEmptyStringNormalCase()
	{
		String expected;
		String actual;
		
		name = "parameter";
		expected = "foo";
		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notNull(Object, String)}
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testNotNull()
	{
		name = "parameter";

		Argument.notNull(null, name);
	}

	/**
	 * Test method for {@link Argument} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Argument.class);
	}

}
