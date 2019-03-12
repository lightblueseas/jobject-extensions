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
package de.alpharogroup.copy.object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import lombok.NonNull;
import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link CopyObjectExtensions} provide methods for copy an original object to a given
 * destination object.
 */
@UtilityClass
public final class CopyObjectExtensions
{
	
    /**
     * Copy the given original object
     *
     * @param original the original object
     * @return a copy of the given original object
     * @throws IllegalAccessException if the caller does not have access to the property accessor method
     * @throws InstantiationException Thrown if one of the following reasons: the class object
     *                                <ul>
     *                                <li>represents an abstract class</li>
     *                                <li>represents an interface</li>
     *                                <li>represents an array class</li>
     *                                <li>represents a primitive type</li>
     *                                <li>represents {@code void}</li>
     *                                <li>has no nullary constructor</li>
     *                                </ul>
     */
    public static Object copyObject(@NonNull Object original) throws IllegalAccessException, InstantiationException {
        Object destination = original.getClass().newInstance();
        return copyObject(original, destination);
    }
	
    /**
     * Copy the given original object to the given destination object. This also works on private
     * fields.
     *
     * @param <ORIGINAL>    the generic type of the original object.
     * @param <DESTINATION> the generic type of the destination object.
     * @param original      the original object.
     * @param destination   the destination object.
     * @return a copy of the given original object
     * @throws IllegalAccessException if the caller does not have access to the property accessor method
     * @throws InstantiationException Thrown if one of the following reasons: the class object
     *                                <ul>
     *                                <li>represents an abstract class</li>
     *                                <li>represents an interface</li>
     *                                <li>represents an array class</li>
     *                                <li>represents a primitive type</li>
     *                                <li>represents {@code void}</li>
     *                                <li>has no nullary constructor</li>
     *                                </ul>
     */
    public static <ORIGINAL, DESTINATION> DESTINATION copyObject(@NonNull ORIGINAL original, @NonNull DESTINATION destination) throws IllegalAccessException, InstantiationException {
        for (Field field : original.getClass().getDeclaredFields()) {
            if (copyField(field, original, destination)) continue;
        }
        return destination;
    }
	
    /**
     * Copy the given original object to the given destination object. This also works on private
     * fields.
     *
     * @param <ORIGINAL>    the generic type of the original object.
     * @param <DESTINATION> the generic type of the destination object.
     * @param field         the field
     * @param original      the original object.
     * @param destination   the destination object.
     * @return true if the field is null or final otherwise false
     * @throws IllegalAccessException if the caller does not have access to the property accessor method
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
     */
    public static <ORIGINAL, DESTINATION> boolean copyField(@NonNull Field field, @NonNull ORIGINAL original, @NonNull DESTINATION destination) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);
        if (field.get(original) == null || Modifier.isFinal(field.getModifiers())) {
            return true;
        }
        if (field.getType().isPrimitive() || field.getType().equals(String.class)
                || field.getType().getSuperclass().equals(Number.class)
                || field.getType().equals(Boolean.class)) {
            field.set(destination, field.get(original));
        } else {
            Object childObj = field.get(original);
            if (childObj == original) {
                field.set(destination, destination);
            } else {
                field.set(destination, copyObject(field.get(original)));
            }
        }
        return false;
    }
	
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
	 * @return the destination object
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 */
	public static <ORIGINAL, DESTINATION> DESTINATION copyPropertyWithReflection(
		final ORIGINAL original, final DESTINATION destination, final String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		ReflectionExtensions.copyFieldValue(original, destination, fieldName);
		return destination;
	}

	/**
	 * A delegate method to {@link CopyObjectExtensions#copyProperties(Object, Object)} for copy the
	 * given original object to the given destination object.
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
	 */
	public static <ORIGINAL, DESTINATION> DESTINATION copy(final ORIGINAL original,
		final DESTINATION destination)
		throws IllegalAccessException, InvocationTargetException, IllegalArgumentException
	{
		return copyProperties(original, destination);
	}

	/**
	 * Copy the given original object to the given destination object. Note: this method decorates
	 * the method of {@link BeanUtils#copyProperties(Object, Object)}
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
	 */
	public static <ORIGINAL, DESTINATION> DESTINATION copyProperties(final ORIGINAL original,
		final DESTINATION destination) throws IllegalAccessException, InvocationTargetException
	{
		BeanUtils.copyProperties(destination, original);
		return destination;
	}

	/**
	 * Copy the given object and return a copy of it. Note: this method decorates the method of
	 * {@link BeanUtils#copyProperties(Object, Object)} and create a new object for the returned
	 * object.
	 *
	 * @param <T>
	 *            the generic type of the given object.
	 * @param original
	 *            the original object.
	 * @return the new object that is a copy of the given object.
	 * 
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
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
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copyProperties(final T original)
		throws IllegalAccessException, InvocationTargetException, InstantiationException
	{
		Object destination = original.getClass().newInstance();
		BeanUtils.copyProperties(destination, original);
		return (T)destination;
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
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream))
		{
			objectOutputStream.writeObject(orig);
			objectOutputStream.flush();
			objectOutputStream.close();
			final ByteArrayInputStream bis = new ByteArrayInputStream(
				byteArrayOutputStream.toByteArray());
			final ObjectInputStream ois = new ObjectInputStream(bis);
			T object = (T)ois.readObject();
			byteArrayOutputStream.close();
			ois.close();
			return object;
		}
	}

}
