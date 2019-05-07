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
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;

import de.alpharogroup.lang.ClassType;
import de.alpharogroup.lang.ObjectExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link CopyObjectExtensions} provide methods for copy an original object to a given
 * destination object.
 */
@UtilityClass
public final class CopyObjectExtensions
{

	/**
	 * Copy the given original object.
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param original
	 *            the original object
	 * @return a copy of the given original object
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
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
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 */
	public static <T> T copyObject(@NonNull T original)
		throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		T destination = ReflectionExtensions.newInstance(original);
		return copyObject(original, destination);
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
	 * @param ignoreFields
	 *            optional field names to ignore
	 * @return a copy of the given original object
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
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
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 */
	public static <ORIGINAL, DESTINATION> DESTINATION copyObject(final @NonNull ORIGINAL original,
		final @NonNull DESTINATION destination, final String... ignoreFields)
		throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		Field[] allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(original.getClass(), ignoreFields);
		for (Field field : allDeclaredFields)
		{
			if (Arrays.asList(ignoreFields).contains(field.getName())
				|| copyField(field, original, destination))
			{
				continue;
			}
		}
		return destination;
	}

	/**
	 * Copy the given original object to the given destination object. This also works on private
	 * fields.
	 *
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param field
	 *            the field
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return true if the field is null or final otherwise false
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
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
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 */
	@SuppressWarnings("unchecked")
	public static <ORIGINAL, DESTINATION> boolean copyField(final @NonNull Field field,
		final @NonNull ORIGINAL original, final @NonNull DESTINATION destination)
		throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		field.setAccessible(true);
		Object value = field.get(original);
		if (value == null || Modifier.isFinal(field.getModifiers()))
		{
			return true;
		}
		Class<?> fieldType = field.getType();
		ClassType classType = ObjectExtensions.getClassType(fieldType);
		switch (classType)
		{
			case ARRAY :
				field.set(destination, ReflectionExtensions.copyArray(value));
				break;
			case ENUM :
				Enum<?> enumValue = (Enum<?>)value;
				String name = enumValue.name();
				field.set(destination, Enum.valueOf(fieldType.asSubclass(Enum.class), name));
				break;
			case ANNOTATION :
			case ANONYMOUS :
			case COLLECTION :
			case LOCAL :
			case DEFAULT :
			case MEMBER :
			case SYNTHETIC :
			case INTERFACE :
			case PRIMITIVE :
				field.set(destination, value);
				break;
			case MAP :
				field.set(destination, value);
				break;
		}
		return false;
	}


	/**
	 * Copy the given object over reflection and return a copy of it
	 * object.
	 *
	 * @param <T>
	 *            the generic type of the given object.
	 * @param original
	 *            the original object.
	 * @return the new object that is a copy of the given object
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copyPropertiesWithReflection(
			final @NonNull T original, final String... ignoreFieldNames) throws InstantiationException, IllegalAccessException, NoSuchFieldException
	{
		Class<T> clazz = (Class<T>)original.getClass();
		T destination = ReflectionExtensions.newInstanceWithObjenesis(clazz);
		String[] allDeclaredFieldNames = ReflectionExtensions.getAllDeclaredFieldNames(clazz, ignoreFieldNames);
		for (String fieldName : allDeclaredFieldNames) {
			ReflectionExtensions.copyFieldValue(original, destination, fieldName);
		}
		return destination;
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
		final @NonNull ORIGINAL original, final @NonNull DESTINATION destination, final @NonNull String fieldName)
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
	public static <ORIGINAL, DESTINATION> DESTINATION copy(final @NonNull ORIGINAL original,
		final @NonNull DESTINATION destination)
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
	public static <ORIGINAL, DESTINATION> DESTINATION copyProperties(final @NonNull ORIGINAL original,
		final @NonNull DESTINATION destination) throws IllegalAccessException, InvocationTargetException
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
	public static <T> T copyProperties(final @NonNull T original)
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
	public static <T extends Serializable> T copySerializedObject(final @NonNull T orig)
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
