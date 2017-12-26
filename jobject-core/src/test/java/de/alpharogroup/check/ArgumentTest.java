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
package de.alpharogroup.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


/**
 * The unit test class for the class {@link Argument}.
 */
public class ArgumentTest
{
	/**
	 * A rule for expecting exceptions
	 */
	@Rule
	public ExpectedException throwable = ExpectedException.none();

	/**
	 * Test method for {@link Argument#isInRange(Comparable, Comparable, Comparable, String)}
	 */
	@Test
	public void testIsInRange()
	{
		final Double min = 0.0d;
		final Double max = 5.0d;
		final Double value = 6.0d;
		final String name = "parameter";
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument '" + name + "' should have a value between " + min
			+ " - " + max + ", but given argument is currently:" + value + "");
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
		final Double min = 0.0d;
		final Double max = 5.0d;
		final Double value = 4.0d;
		final String name = "parameter";

		expected = value;
		actual = Argument.isInRange(min, max, value, name);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(java.util.Collection, String)}
	 */
	@Test
	public void testNotEmptyCollection()
	{
		final String name = "list";
		final List<String> list = new ArrayList<>();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given collection '" + name + "' may not be empty.");

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
		final String name = "list";
		expected = new ArrayList<>();
		expected.add("foo");

		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(Map, String)}
	 */
	@Test
	public void testNotEmptyMap()
	{
		final String name = "map";
		final Map<String, String> emptyMap = new HashMap<>();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given map '" + name + "' may not be empty.");
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
		final String name = "map";
		expected = new HashMap<>();
		expected.put("foo", "bar");

		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notEmpty(CharSequence, String)}
	 */
	@Test
	public void testNotEmptyString()
	{
		final String name = "parameter";
		final String argument = "";
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument '" + name + "' may not be empty.");
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
		final String name = "parameter";
		expected = "foo";
		actual = Argument.notEmpty(expected, name);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Argument#notNull(Object, String)}
	 */
	@Test
	public void testNotNull()
	{
		final String name = "parameter";
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument '" + name + "' may not be null.");

		Argument.notNull(null, name);
	}

}
