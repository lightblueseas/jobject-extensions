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


import java.lang.reflect.InvocationTargetException;

import de.alpharogroup.copy.object.CopyObjectQuietlyExtensions;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link MergeObjectQuietlyExtensions} provide extension methods for merge a source
 * object with another object.
 */
@UtilityClass
@Slf4j
public final class MergeObjectQuietlyExtensions
{

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
			return MergeObjectExtensions.merge(mergeInObject, withObject);
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
			merged = CopyObjectQuietlyExtensions.copyQuietly(withObject, mergeInObject);
		}
		return merged;
	}

}
