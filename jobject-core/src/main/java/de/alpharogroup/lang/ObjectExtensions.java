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
package de.alpharogroup.lang;

import java.util.Collection;
import java.util.Map;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ObjectExtensions} provides extension methods to check if the object is the
 * default value. It also provides methods to find changed data between Objects.
 */
@UtilityClass
public final class ObjectExtensions
{

	/**
	 * Checks if the given object has the default value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param fieldClass
	 *            the field class
	 * @param object
	 *            the object
	 * @return true, if is default value
	 */
	public static final <T> boolean isDefaultValue(final Class<T> fieldClass, final T object)
	{
		if (object == null)
		{
			return true;
		}
		final T defaultValue = DefaultValue.get(fieldClass);
		if (defaultValue != null)
		{
			return DefaultValue.get(fieldClass).equals(object);
		}
		return false;
	}

	/**
	 * Gets the {@link ClassType} from the given class.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            The class.
	 * @return the {@link ClassType} from the given class.
	 */
	public static <T> ClassType getClassType(final Class<T> clazz)
	{
		if (clazz.isArray())
		{
			return ClassType.ARRAY;
		}
		if (Collection.class.isAssignableFrom(clazz))
		{
			return ClassType.COLLECTION;
		}
		if (Map.class.isAssignableFrom(clazz))
		{
			return ClassType.MAP;
		}
		if (clazz.isLocalClass())
		{
			return ClassType.LOCAL;
		}
		if (clazz.isMemberClass())
		{
			return ClassType.MEMBER;
		}
		if (clazz.isPrimitive())
		{
			return ClassType.PRIMITIVE;
		}
		if (clazz.isAnnotation())
		{
			return ClassType.ANNOTATION;
		}
		if (clazz.isEnum())
		{
			return ClassType.ENUM;
		}
		if (clazz.isInterface())
		{
			return ClassType.INTERFACE;
		}
		if (clazz.isSynthetic())
		{
			return ClassType.SYNTHETIC;
		}
		if (clazz.isAnonymousClass())
		{
			return ClassType.ANONYMOUS;
		}
		return ClassType.DEFAULT;
	}

}
