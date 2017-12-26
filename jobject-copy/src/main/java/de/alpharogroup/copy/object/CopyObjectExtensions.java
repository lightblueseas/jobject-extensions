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
package de.alpharogroup.copy.object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link CopyObjectExtensions} provide methods for copy an original object to a given
 * destination object.
 */
@UtilityClass
@Slf4j
public final class CopyObjectExtensions
{

	/**
	 * Copy the given original object to the given destination object. This also works on private
	 * fields.
	 *
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @param fieldName
	 *            the field name
	 * @return the destination object or null if the copy process failed.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalArgumentException
	 *             if the <code>destination</code> or <code>original</code> argument is null or if
	 *             the <code>destination</code> property type is different from the source type and
	 *             the relevant converter has not been registered.
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copyPropertyWithReflection(
		final ORIGINAL original, final DESTINATION destination, final String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalArgumentException,
		IllegalAccessException
	{
		ReflectionExtensions.copyFieldValue(original, destination, fieldName);
		return destination;
	}

	/**
	 * Copy the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return the destination object.
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalArgumentException
	 *             if the <code>destination</code> or <code>original</code> argument is null or if
	 *             the <code>destination</code> property type is different from the source type and
	 *             the relevant converter has not been registered.
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copy(final ORIGINAL original,
		final DESTINATION destination)
		throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		BeanUtils.copyProperties(destination, original);
		return destination;
	}

	/**
	 * Copy quietly the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return the destination object or null if the copy process failed.
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copyQuietly(final ORIGINAL original,
		final DESTINATION destination)
	{
		try
		{
			return copy(original, destination);
		}
		catch (final IllegalAccessException e)
		{
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
		catch (final InvocationTargetException e)
		{
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
		catch (final IllegalArgumentException e)
		{
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	/**
	 * Checks if is copyable and copies if its possible otherwise it returns false.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return true, if is copyable otherwise false.
	 */
	public static final <DESTINATION, ORIGINAL> boolean isCopyable(final ORIGINAL original,
		final DESTINATION destination)
	{
		return copyQuietly(original, destination) != null;
	}

	/**
	 * Copys the given Object and returns the copy from the object or null if the object can't be
	 * serialized.
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param orig
	 *            The object to copy.
	 * @return Returns a copy from the original object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             is thrown when a class is not found in the classloader or no definition for the
	 *             class with the specified name could be found.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T copySerializedObject(final T orig)
		throws IOException, ClassNotFoundException
	{
		T object = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try
		{
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(orig);
			objectOutputStream.flush();
			objectOutputStream.close();
			final ByteArrayInputStream bis = new ByteArrayInputStream(
				byteArrayOutputStream.toByteArray());
			final ObjectInputStream ois = new ObjectInputStream(bis);
			object = (T)ois.readObject();
		}
		finally
		{
			closeOutputStream(byteArrayOutputStream);
			closeOutputStream(objectOutputStream);
		}
		return object;
	}

	/**
	 * Closes the given OutputStream.
	 *
	 * @param out
	 *            The OutputStream to close.
	 * @return Returns true if the OutputStream is closed otherwise false.
	 */
	public static boolean closeOutputStream(OutputStream out)
	{
		boolean closed = true;
		try
		{
			if (out != null)
			{
				out.flush();
				out.close();
				out = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.flush();
					out.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

}
