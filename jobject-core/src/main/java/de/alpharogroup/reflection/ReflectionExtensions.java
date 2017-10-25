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
package de.alpharogroup.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public static <T> void setFieldValue(final T source, final String fieldName,
		final Object newValue) throws NoSuchFieldException, SecurityException,
		IllegalArgumentException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		sourceField.set(source, newValue);
	}

	/**
	 * Gets all fieldnames from the given class as an String array.
	 *
	 * @param cls
	 *            The class object to get the fieldnames.
	 *
	 * @return Gets all fieldnames from the given class as an String array.
	 */
	public static List<String> getFieldNames(final Class<?> cls)
	{
		final Field[] fields = cls.getDeclaredFields();
		final List<String> fieldNames = new ArrayList<>();
		for (final Field field : fields)
		{
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	/**
	 * Gets all methodnames from the given class as an String array.
	 *
	 * @param cls
	 *            The class object to get the methodnames.
	 *
	 * @return Gets all methodnames from the given class as an String array.
	 */
	public static String[] getMethodNames(final Class<?> cls)
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
	 * prefix and the fieldname and puts the result into the map.
	 *
	 * @param fieldNames
	 *            A list with the fieldNames.
	 * @param prefix
	 *            The prefix for the methodname.
	 *
	 * @return the method names with prefix from field names
	 */
	public static final Map<String, String> getMethodNamesWithPrefixFromFieldNames(
		final List<String> fieldNames, final String prefix)
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
	public static final String firstCharacterToUpperCase(final String fieldName)
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
	public static List<String> getModifiers(final Field field)
	{
		final String modifiers = Modifier.toString(field.getModifiers());
		final String[] modifiersArray = modifiers.split(" ");
		return Arrays.asList(modifiersArray);
	}

	/**
	 * Creates a new instance from the same type as the given object.
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param obj
	 *            the obj
	 * @return the new instance
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @deprecated use instead {@link ReflectionExtensions#newInstance(Object)}
	 */
	@Deprecated
	public static <T> T getNewInstance(final T obj)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return newInstance(obj);
	}

	/**
	 * Creates a new instance from the same type as the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param obj
	 *            the obj
	 * @return the new instance
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(final T obj)
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return newInstance((Class<T>)Class.forName(obj.getClass().getCanonicalName()));
	}

	/**
	 * Creates a new instance from the same type as the given Class.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the Class object
	 * @return the new instance
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */

	public static <T> T newInstance(final Class<T> clazz)
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
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
	public static <T> Field getDeclaredField(final T object, final String fieldName)
		throws NoSuchFieldException, SecurityException
	{
		final Field field = object.getClass().getDeclaredField(fieldName);
		return field;
	}

	/**
	 * Gets the {@link Field} that match to the given field name that exists in the given class.
	 *
	 * @param cls
	 *            the cls
	 * @param fieldName
	 *            the field name
	 * @return the declared field
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static Field getDeclaredField(final Class<?> cls, final String fieldName)
		throws NoSuchFieldException, SecurityException
	{
		final Field field = cls.getDeclaredField(fieldName);
		return field;
	}

}
