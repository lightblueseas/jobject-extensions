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

import java.util.Collection;
import java.util.Map;

import lombok.experimental.UtilityClass;


/**
 * The class {@link Argument} that can assert conditions on arguments.
 */
@UtilityClass
public class Argument
{

	/**
	 * Checks if the given {@code value} argument is in the given a range.
	 *
	 * @param <T>
	 *            the generic type of the arguments
	 * @param min
	 *            The minimum from the range to check.
	 * @param max
	 *            The maximum from the range to check.
	 * @param value
	 *            The value to check if it is in the given range.
	 * @param name
	 *            the name of the given argument
	 * @return if the check is successful the {@code value} object. This case is if the given
	 *         {@code value} is in the given range.
	 * @throws IllegalArgumentException
	 *             when the given {@code value} is not in the given range.
	 */
	public static <T extends Comparable<? super T>> T isInRange(final T min, final T max,
		final T value, final String name)
	{
		notNull(min, "min");
		notNull(max, "max");
		if ((value.compareTo(min) < 0) || (value.compareTo(max) > 0))
		{
			throw new IllegalArgumentException(String.format(
				"Given argument '%s' should have a value between %s - %s, but given argument is currently:%s",
				name, min, max, value));
		}
		return value;
	}

	/**
	 * Checks if the given {@code collection} is not null or empty.
	 *
	 * @param <T>
	 *            the generic type of the elements in the given collection
	 * @param <C>
	 *            the generic type of the given collection
	 * @param collection
	 *            the collection to check
	 * @param name
	 *            the name of the given collection
	 * @return if the check is successful the {@code collection} object. This case is if the given
	 *         collection is not null or empty.
	 * @throws IllegalArgumentException
	 *             when the given {@code collection} is null or empty.
	 */
	public static <T, C extends Collection<T>> C notEmpty(final C collection, final String name)
	{
		notNull(collection, name);
		if (collection.isEmpty())
		{
			throw new IllegalArgumentException("Given collection '" + name + "' may not be empty.");
		}
		return collection;
	}

	/**
	 * Checks if the given map is not null or empty.
	 *
	 * @param <K>
	 *            the generic type of the key from the given Map
	 * @param <V>
	 *            the generic type of the value from the given Map
	 * @param <M>
	 *            the generic type of the given Map
	 * @param map
	 *            the map to check
	 * @param name
	 *            the name of the given collection
	 * @return if the check is successful the {@code map} object. This case is if the given
	 *         {@code map} is not null or empty.
	 * @throws IllegalArgumentException
	 *             when the given {@code map} is null or empty.
	 */
	public static <K, V, M extends Map<K, V>> M notEmpty(final M map, final String name)
	{
		notNull(map, name);
		if (map.isEmpty())
		{
			throw new IllegalArgumentException("Given map '" + name + "' may not be empty.");
		}
		return map;
	}

	/**
	 * Checks if the given {@code argument} is not empty.
	 *
	 * @param <T>
	 *            the generic type of the given argument
	 * @param argument
	 *            the argument
	 * @param name
	 *            the name of the given argument
	 * @return if the check is successful the {@code argument} object. This case is if the given
	 *         {@code argument} is not null or empty.
	 * @throws IllegalArgumentException
	 *             when the given {@code argument} is null or empty.
	 */
	public static <T extends CharSequence> T notEmpty(final T argument, final String name)
	{
		notNull(argument, name);
		if ((argument.length() == 0) || (argument.toString().trim().length() == 0))
		{
			throw new IllegalArgumentException("Given argument '" + name + "' may not be empty.");
		}
		return argument;
	}

	/**
	 * Checks if the given {@code argument} is not null.
	 *
	 * @param <T>
	 *            the generic type of the given argument
	 * @param argument
	 *            the argument
	 * @param name
	 *            the name of the given argument
	 * @return if the check is successful the {@code argument} object. This case is if the given
	 *         {@code argument} is not null.
	 * @throws IllegalArgumentException
	 *             when the given {@code argument} is null.
	 */
	public static <T> T notNull(final T argument, final String name)
	{
		if (argument == null)
		{
			throw new IllegalArgumentException("Given argument '" + name + "' may not be null.");
		}
		return argument;
	}

}
