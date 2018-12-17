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
package de.alpharogroup.merge.object;


import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import de.alpharogroup.check.Check;
import de.alpharogroup.lang.ObjectExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link MergeObjectExtensions} provide extension methods for merge a source object with
 * another object.
 */
@UtilityClass
public class MergeObjectExtensions
{

	/**
	 * Merge the given property to the given 'to' object with the given 'with' object over
	 * reflection.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in, in other words the target
	 * @param withObject
	 *            the object to merge with, in other words the source
	 * @param fieldName
	 *            the field name
	 * @return true, if successful
	 * @throws IllegalArgumentException
	 *             if the <code>mergeInObject</code> or <code>withObject</code> argument is null or
	 *             if the <code>mergeInObject</code> property type is different from the source type
	 *             and the relevant converter has not been registered.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static final <MERGE_IN, WITH> boolean mergePropertyWithReflection(
		final MERGE_IN mergeInObject, final WITH withObject, final String fieldName)
	{
		try
		{
			ReflectionExtensions.copyFieldValue(withObject, mergeInObject, fieldName);
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException
			| IllegalAccessException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Merge the given to object with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object.
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws IllegalArgumentException
	 *             if the <code>mergeInObject</code> or <code>withObject</code> argument is null or
	 *             if the <code>mergeInObject</code> property type is different from the source type
	 *             and the relevant converter has not been registered.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN merge(final MERGE_IN mergeInObject,
		final WITH withObject) throws InvocationTargetException, IllegalAccessException
	{
		Check.get().notNull(mergeInObject, "mergeInObject").notNull(withObject, "withObject");

		final Class<?> toClass = mergeInObject.getClass();

		final PropertyDescriptor[] propertyDescriptors = PropertyUtils
			.getPropertyDescriptors(toClass);

		for (final PropertyDescriptor descriptor : propertyDescriptors)
		{
			mergeProperty(mergeInObject, withObject, descriptor);
		}
		return mergeInObject;
	}

	/**
	 * Merge the given property to the given 'to' object with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @param propertyDescriptor
	 *            the property descriptor
	 * @return true, if merge was successful otherwise false
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws IllegalArgumentException
	 *             if the <code>mergeInObject</code> or <code>withObject</code> argument is null or
	 *             if the <code>mergeInObject</code> property type is different from the source type
	 *             and the relevant converter has not been registered.
	 */
	public static final <MERGE_IN, WITH> boolean mergeProperty(final MERGE_IN mergeInObject,
		final WITH withObject, final PropertyDescriptor propertyDescriptor)
		throws IllegalAccessException, InvocationTargetException
	{
		if (PropertyUtils.isReadable(mergeInObject, propertyDescriptor.getName())
			&& PropertyUtils.isWriteable(mergeInObject, propertyDescriptor.getName()))
		{
			final Method getter = propertyDescriptor.getReadMethod();
			final Object value = getter.invoke(withObject);
			if (!ObjectExtensions.isDefaultValue(propertyDescriptor.getPropertyType(), value))
			{
				final Method setter = propertyDescriptor.getWriteMethod();
				setter.invoke(mergeInObject, value);
				return true;
			}
		}
		return false;
	}

}
