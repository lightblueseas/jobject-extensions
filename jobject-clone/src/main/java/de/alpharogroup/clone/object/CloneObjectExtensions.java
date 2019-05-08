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

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.rits.cloning.Cloner;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link CloneObjectExtensions} provide methods for clone an object.
 */
@UtilityClass
public final class CloneObjectExtensions
{
	private final static Cloner cloner = new Cloner();
	/**
	 * Try to clone the given generic object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object to clone
	 * @return The cloned object or null if the clone process failed.
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws InstantiationException
	 *             Thrown if one of the following reasons: the class object
	 *             <ul>
	 *             <li>represents an abstract class</li>
	 *             <li>represents an interface</li>
	 *             <li>represents an array class</li>
	 *             <li>represents a primitive type</li>
	 *             <li>represents {@code void}</li>
	 *             <li>has no nullary constructor</li>
	 *             </ul>
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(final T object) throws NoSuchMethodException, IllegalAccessException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		return (T)cloneObject(object);
	}

	/**
	 * Try to clone the given object.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 * @throws InstantiationException
	 *             Thrown if one of the following reasons: the class object
	 *             <ul>
	 *             <li>represents an abstract class</li>
	 *             <li>represents an interface</li>
	 *             <li>represents an array class</li>
	 *             <li>represents a primitive type</li>
	 *             <li>represents {@code void}</li>
	 *             <li>has no nullary constructor</li>
	 *             </ul>
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Object cloneObject(final Object object)
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
		ClassNotFoundException, InstantiationException, IOException
	{
		if (object == null)
		{
			return object;
		}
		Object clone = null;

		// Try to clone the object if it is 'Cloneable'
		if (object instanceof Cloneable)
		{
			clone = cloneCloneable(object);
		}

		// Try to clone the object with external cloner
		if(clone == null) {
			clone = withCloner(object);
		}

		return clone;
	}

	/**
	 * Try to clone the given object with the external cloner
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @return the t
	 */
	public static  <T> T withCloner(final @NonNull T object) {
		return cloner.deepClone(object);
	}

	/**
	 * Try to clone the given object that implements {@link Cloneable}.
	 *
	 * @param object
	 *            The object to clone.
	 * @return The cloned object or null if the clone process failed.
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 */
	public static Object cloneCloneable(final Object object)
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Object clone;
		if (object.getClass().isArray())
		{
			final Class<?> componentType = object.getClass().getComponentType();
			if (componentType.isPrimitive())
			{
				int length = Array.getLength(object);
				clone = Array.newInstance(componentType, length);
				while (length-- > 0)
				{
					Array.set(clone, length, Array.get(object, length));
				}
			}
			else
			{
				clone = ((Object[])object).clone();
			}
			if (clone != null)
			{
				return clone;
			}
		}

		final Class<?> clazz = object.getClass();
		final Method cloneMethod = clazz.getDeclaredMethod("clone");
		cloneMethod.setAccessible(true);
		return cloneMethod.invoke(object, (Object[])null);
	}

}
