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
package de.alpharogroup.clone.object;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import com.rits.cloning.Cloner;

import de.alpharogroup.copy.object.CopyObjectQuietlyExtensions;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link CloneObjectQuietlyExtensions} provide methods for clone object in a quietly
 * manner as the name let presume.
 * 
 * @deprecated use instead the the verbose class<br>
 *             Note: will be removed in the next minor release
 */
@UtilityClass
@Slf4j
public final class CloneObjectQuietlyExtensions
{

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
		if (object == null)
		{
			return object;
		}
		Object clone = null;

		// Try to clone the object if it is Cloneble.
		if (object instanceof Cloneable)
		{
			clone = cloneCloneableQuietly(object);
		}

		// Try to clone the object if it implements Serializable.
		if (clone == null && object instanceof Serializable)
		{
			Serializable serializableObject = (Serializable)object;
			clone = CopyObjectQuietlyExtensions.copySerializedObjectQuietly(serializableObject);
		}

		// Try to clone the object by copying all his properties with
		// the BeanUtils.cloneBean(Object) method.
		if (clone == null)
		{
			clone = cloneBeanQuietly(object);
		}

		// Try to clone the object with deep cloning with the Cloner class...
		if (clone == null)
		{
			Cloner cloner = new Cloner();
			clone = cloner.deepClone(object);
		}

		// Try to clone the object by copying all his properties with
		// the BeanUtils.copyProperties(Object, Object) method.
		if (clone == null)
		{
			clone = CopyObjectQuietlyExtensions.copyPropertiesQuietly(object);
		}

		return clone;
	}

	/**
	 * Clone the given object quietly.
	 *
	 * @param <T>
	 *            the generic type of the given bean
	 * @param object
	 *            the object to clone
	 * @return the cloned object or null if the clone process failed.
	 */
	public static <T> T cloneBeanQuietly(T object)
	{
		T clone = null;
		try
		{
			clone = CloneObjectExtensions.cloneBean(object);
		}
		catch (IllegalAccessException | InstantiationException | InvocationTargetException
			| NoSuchMethodException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		return clone;
	}

	/**
	 * Try to clone the given object that implements {@link Cloneable}.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 */
	public static Object cloneCloneableQuietly(final Object object)
	{
		Object clone = null;
		try
		{
			clone = CloneObjectExtensions.cloneCloneable(object);
		}
		catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		return clone;
	}

}
