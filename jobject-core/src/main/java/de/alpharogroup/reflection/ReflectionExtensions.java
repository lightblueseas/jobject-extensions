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
package de.alpharogroup.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.experimental.UtilityClass;


/**
 * The class {@link ReflectionExtensions}.
 */
@UtilityClass
public final class ReflectionExtensions
{

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
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void copyFieldValue(final @NonNull T source, final @NonNull T target,
		final @NonNull String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		final Object sourceValue = sourceField.get(source);
		setFieldValue(target, fieldName, sourceValue);
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
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void setFieldValue(final @NonNull T source, final @NonNull String fieldName,
		final Object newValue)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		sourceField.set(source, newValue);
	}

	/**
	 * Gets the field value of the given source object over the field name.
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source
	 * @param fieldName
	 *            the field name
	 * @return the field value
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> Object getFieldValue(final @NonNull T source, final @NonNull String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		return sourceField.get(source);
	}

	/**
	 * Sets the field value of the given class object over the field name.
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            The class
	 * @param fieldName
	 *            the field name
	 * @param newValue
	 *            the new value
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void setFieldValue(final @NonNull Class<?> cls,
		final @NonNull String fieldName, final Object newValue)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(cls, fieldName);
		sourceField.setAccessible(true);
		sourceField.set(null, newValue);
	}

	/**
	 * Gets all field names from the given class as an String list.
	 *
	 * @param cls
	 *            The class object to get the field names.
	 *
	 * @return Gets all field names from the given class as an String list.
	 */
	public static List<String> getFieldNames(final @NonNull Class<?> cls)
	{
		return Arrays.stream(cls.getDeclaredFields()).map(field -> {
			return field.getName();
		}).collect(Collectors.toList());
	}

	/**
	 * Gets all the declared field names from the given class object.
	 *
	 * Note: without the field names from any superclasses
	 *
	 * @param cls
	 *            the class object
	 * @return all the declared field names from the given class as an String array
	 */
	public static String[] getDeclaredFieldNames(final @NonNull Class<?> cls)
	{
		return Arrays.stream(cls.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
	}

	/**
	 * Gets all method names from the given class as an String array.
	 *
	 * @param cls
	 *            The class object to get the method names.
	 *
	 * @return Gets all method names from the given class as an String array.
	 */
	public static String[] getMethodNames(final @NonNull Class<?> cls)
	{
		final Method[] methods = cls.getDeclaredMethods();
		final String[] methodNames = new String[methods.length];
		for (int i = 0; i < methods.length; i++)
		{
			methodNames[i] = methods[i].getName();
		}
		return methodNames;
	}

	/**
	 * Generates a Map with the fieldName as key and the method as value. Concatenates the given
	 * prefix and the field name and puts the result into the map.
	 *
	 * @param fieldNames
	 *            A list with the field names.
	 * @param prefix
	 *            The prefix for the method name.
	 *
	 * @return the method names with prefix from field names
	 */
	public static Map<String, String> getMethodNamesWithPrefixFromFieldNames(
		final @NonNull List<String> fieldNames, final String prefix)
	{
		final Map<String, String> fieldNameMethodMapper = new HashMap<>();
		for (final String fieldName : fieldNames)
		{
			final String firstCharacterToUpperCasefieldName = firstCharacterToUpperCase(fieldName);
			final String methodName = prefix + firstCharacterToUpperCasefieldName;
			fieldNameMethodMapper.put(fieldName, methodName);
		}
		return fieldNameMethodMapper;
	}

	/**
	 * Sets the first character from the given string to upper case and returns it. Example:<br>
	 * Given fieldName: userName <br>
	 * Result: UserName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static String firstCharacterToUpperCase(final @NonNull String fieldName)
	{
		String firstCharacter = fieldName.substring(0, 1);
		firstCharacter = firstCharacter.toUpperCase();
		final char[] fc = firstCharacter.toCharArray();
		final char[] fn = fieldName.toCharArray();
		fn[0] = fc[0];
		return new String(fn);
	}

	/**
	 * Gets the modifiers from the given Field as a list of String objects.
	 *
	 * @param field
	 *            The field to get the modifiers.
	 * @return A list with the modifiers as String objects from the given Field.
	 */
	public static List<String> getModifiers(final @NonNull Field field)
	{
		final String modifiers = Modifier.toString(field.getModifiers());
		final String[] modifiersArray = modifiers.split(" ");
		return Arrays.asList(modifiersArray);
	}

	/**
	 * Creates a new instance from the same type as the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the new instance
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(final @NonNull T object)
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return newInstance((Class<T>)Class.forName(object.getClass().getCanonicalName()));
	}

	/**
	 * Creates a new instance from the same type as the given Class.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the Class object
	 * @return the new instance
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 */

	public static <T> T newInstance(final @NonNull Class<T> clazz)
		throws InstantiationException, IllegalAccessException
	{
		return clazz.newInstance();
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
	public static <T> Field getDeclaredField(@NonNull final T object,
		final @NonNull String fieldName) throws NoSuchFieldException, SecurityException
	{
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * Gets the {@link Field} that match to the given field name that exists in the given class.
	 *
	 * @param cls
	 *            the class object
	 * @param fieldName
	 *            the field name
	 * @return the declared field
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static Field getDeclaredField(final @NonNull Class<?> cls,
		final @NonNull String fieldName) throws NoSuchFieldException, SecurityException
	{
		return cls.getDeclaredField(fieldName);
	}

	/**
	 * Gets all the declared fields including all fields from all superclasses from the given class
	 * object
	 *
	 * @param cls
	 *            the class object
	 * @return all the declared fields
	 */
	public static Field[] getAllDeclaredFields(final @NonNull Class<?> cls)
	{
		List<Field> fields = new ArrayList<>(Arrays.asList(cls.getDeclaredFields()));
		Class<?> superClass = cls.getSuperclass();
		if (superClass != null && superClass.equals(Object.class))
		{
			return fields.toArray(new Field[] { });
		}
		while ((superClass != null && superClass.getSuperclass() != null
			&& superClass.getSuperclass().equals(Object.class)))
		{
			fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
			superClass = superClass.getSuperclass();
		}
		return fields.toArray(new Field[] { });
	}


	/**
	 * Gets all the declared field names including all fields from all superclasses from the given
	 * class object
	 *
	 * @param cls
	 *            the class object
	 * @return all the declared field names
	 */
	public static String[] getAllDeclaredFieldNames(final @NonNull Class<?> cls)
	{
		List<String> fieldNames = Arrays.stream(getAllDeclaredFields(cls)).map(field -> {
			return field.getName();
		}).collect(Collectors.toList());
		return fieldNames.toArray(new String[fieldNames.size()]);
	}

}
