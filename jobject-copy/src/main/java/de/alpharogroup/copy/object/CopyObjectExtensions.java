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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

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
		copyFieldValue(original, destination, fieldName);
		return destination;
	}

	/**
	 * Copies the field value of the given source object to the given target object.
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @param fieldName
	 *            the field name
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalArgumentException
	 *             is thrown if an illegal or inappropriate argument has been passed to a method.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void copyFieldValue(final T source, final T target, final String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalArgumentException,
		IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		final Object sourceValue = sourceField.get(source);
		setFieldValue(target, fieldName, sourceValue);
	}
	/**
	 * Gets the {@link Field} that match to the given field name that exists in the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param fieldName
	 *            the field name
	 * @return the declared field
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static <T> Field getDeclaredField(final T object, final String fieldName)
		throws NoSuchFieldException, SecurityException
	{
		final Field field = object.getClass().getDeclaredField(fieldName);
		return field;
	}
	/**
	 * Sets the field value of the given source object over the field name.
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source
	 * @param fieldName
	 *            the field name
	 * @param newValue
	 *            the new value
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalArgumentException
	 *             is thrown if an illegal or inappropriate argument has been passed to a method.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void setFieldValue(final T source, final String fieldName, final Object newValue)
		throws NoSuchFieldException, SecurityException, IllegalArgumentException,
		IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		sourceField.set(source, newValue);
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

}
