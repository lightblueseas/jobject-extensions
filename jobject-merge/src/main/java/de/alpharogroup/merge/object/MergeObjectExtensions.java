package de.alpharogroup.merge.object;


import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import de.alpharogroup.check.Check;
import de.alpharogroup.copy.object.CopyObjectExtensions;
import de.alpharogroup.lang.ObjectExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link MergeObjectExtensions} provide extension methods for merge a source object with
 * another object.
 */
@UtilityClass
@ExtensionMethod(ObjectExtensions.class)
@Slf4j
public final class MergeObjectExtensions
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
		final WITH withObject)
		throws InvocationTargetException, IllegalAccessException, IllegalArgumentException
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
	 * Merge quietly the given merge in object(destination) with the given 'with' object.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object or null if the merge process failed.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN mergeQuietly(final MERGE_IN mergeInObject,
		final WITH withObject)
	{
		try
		{
			return merge(mergeInObject, withObject);
		}
		catch (final InvocationTargetException e)
		{
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
		catch (final IllegalAccessException e)
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
	 * Try first to merge quietly the given merge in object(destination) with the given 'with'
	 * object, if this fails try to copy.
	 *
	 * @param <MERGE_IN>
	 *            the generic type of the object to merge in
	 * @param <WITH>
	 *            the generic type of the object to merge with
	 * @param mergeInObject
	 *            the object to merge in
	 * @param withObject
	 *            the object to merge with
	 * @return the merged object or null if the merge process failed.
	 */
	public static final <MERGE_IN, WITH> MERGE_IN mergeOrCopyQuietly(final MERGE_IN mergeInObject,
		final WITH withObject)
	{
		MERGE_IN merged = mergeQuietly(mergeInObject, withObject);
		if (merged == null)
		{
			merged = CopyObjectExtensions.copyQuietly(withObject, mergeInObject);
		}
		return merged;
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
		throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		if (PropertyUtils.isReadable(mergeInObject, propertyDescriptor.getName())
			&& PropertyUtils.isWriteable(mergeInObject, propertyDescriptor.getName()))
		{
			final Method getter = propertyDescriptor.getReadMethod();
			final Object value = getter.invoke(withObject);
			if (!value.isDefaultValue())
			{
				final Method setter = propertyDescriptor.getWriteMethod();
				setter.invoke(mergeInObject, value);
				return true;
			}
		}
		return false;
	}

}
