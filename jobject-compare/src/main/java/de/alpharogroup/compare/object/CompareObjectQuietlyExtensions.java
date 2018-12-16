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
package de.alpharogroup.compare.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link CompareObjectQuietlyExtensions} provide methods for compare an object with
 * another given object.
 * 
 * @deprecated use instead the the verbose class<br>
 *             Note: will be removed in the next minor release
 */
@UtilityClass
@Slf4j
public final class CompareObjectQuietlyExtensions
{

	/**
	 * Compares the given object over the given property quietly.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param property
	 *            the property
	 * @return the int
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareToQuietly(final Object sourceOjbect, final Object objectToCompare,
		final String property)
	{
		Map<?, ?> beanDescription;
		try
		{
			beanDescription = BeanUtils.describe(sourceOjbect);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			log.error("BeanUtils.describe(sourceOjbect) throws an exception...", e);
			return 0;
		}
		Map<?, ?> clonedBeanDescription;
		try
		{
			clonedBeanDescription = BeanUtils.describe(objectToCompare);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			log.error("BeanUtils.describe(objectToCompare) throws an exception...", e);
			return 0;
		}
		final Object sourceAttribute = beanDescription.get(property);
		final Object changedAttribute = clonedBeanDescription.get(property);
		if (sourceAttribute == null && changedAttribute == null)
		{
			return 0;
		}
		if (sourceAttribute != null && changedAttribute == null)
		{
			return 1;
		}
		else if (sourceAttribute == null)
		{
			return -1;
		}
		return new BeanComparator(property).compare(sourceOjbect, objectToCompare);
	}

}
