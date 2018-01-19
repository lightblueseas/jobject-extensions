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
package de.alpharogroup.clone.object;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import lombok.experimental.UtilityClass;

/**
 * The class {@link CloneObjectQuietlyExtensions} provide methods for clone object in a quietly manner as the name let presume.
 */
@UtilityClass
public final class CloneObjectQuietlyExtensions
{

	/** The logger constant. */
	private static final Logger LOG = Logger.getLogger(CloneObjectQuietlyExtensions.class.getName());

	/**
	 * Try to clone the given generic object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object to clone
	 * @return The cloned object or null if the clone process failed.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cloneQuietly(final T object)
	{
		return (T)cloneObjectQuietly(object);
	}

	/**
	 * Try to clone the given object quietly.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 */
	public static Object cloneObjectQuietly(final Object object)
	{
		Object clone = null;
		try
		{
			clone = CloneObjectExtensions.cloneObject(object);
		}
		catch (final NoSuchMethodException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: NoSuchMethodException", e);
		}
		catch (final SecurityException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: SecurityException", e);
		}
		catch (final IllegalAccessException e)
		{
			LOG.error(
				"Try to clone the object with " + "org.apache.commons.beanutils.BeanUtils failed "
					+ "cause of IllegalAccessException. Could not found from ReflectionExtensions.",
				e);
		}
		catch (final IllegalArgumentException e)
		{
			LOG.error("Try to clone the object with " + "reflection and call the clone method. "
				+ "Thrown exception: IllegalArgumentException", e);
		}
		catch (final InvocationTargetException e)
		{
			LOG.error(
				"Try to clone the object with " + "org.apache.commons.beanutils.BeanUtils failed "
					+ "cause of InvocationTargetException. Could not found from ReflectionExtensions.",
				e);
		}
		catch (final ClassNotFoundException e)
		{
			LOG.error(
				"Try to clone the object with " + "org.apache.commons.beanutils.BeanUtils failed "
					+ "cause of ClassNotFoundException. Could not found from ReflectionExtensions.",
				e);
		}
		catch (final InstantiationException e)
		{
			LOG.error(
				"Try to clone the object with " + "org.apache.commons.beanutils.BeanUtils failed "
					+ "cause of InstantiationException. Could not found from ReflectionExtensions.",
				e);
		}
		catch (final IOException e)
		{
			LOG.error("Try to clone the object with "
				+ "CopyObjectExtensions.copySerializedObject((Serializable)object) "
				+ "caused an IOException.", e);
		}
		return clone;
	}

}
