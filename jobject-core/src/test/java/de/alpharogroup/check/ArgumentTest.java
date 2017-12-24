package de.alpharogroup.check;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


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