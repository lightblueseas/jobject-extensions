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

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link CopyObjectQuietlyExtensions} provide methods for copy an original object to a
 * given destination object in a quietly manner as the name let presume.
 */
@UtilityClass
@Slf4j
public final class CopyObjectQuietlyExtensions
{

	/**
	 * Copy quietly the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return the destination object or null if the copy process failed.
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copyQuietly(final ORIGINAL original,
		final DESTINATION destination)
	{
		try
		{
			return CopyObjectExtensions.copy(original, destination);
		}
		catch (final IllegalAccessException e)
		{
			log.error(e.getLocalizedMessage(), e);
			return null;
		}
		catch (final InvocationTargetException e)
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
	 * Checks if is copyable and copies if its possible otherwise it returns false.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return true, if is copyable otherwise false.
	 */
	public static final <DESTINATION, ORIGINAL> boolean isCopyable(final ORIGINAL original,
		final DESTINATION destination)
	{
		return copyQuietly(original, destination) != null;
	}

	/**
	 * Copy quietly the given original object to the given destination object.
	 *
	 * @param <DESTINATION>
	 *            the generic type of the destination object.
	 * @param <ORIGINAL>
	 *            the generic type of the original object.
	 * @param original
	 *            the original object.
	 * @param destination
	 *            the destination object.
	 * @return the destination object or null if an exception occur.
	 */
	public static final <ORIGINAL, DESTINATION> DESTINATION copyPropertiesQuietly(
		final ORIGINAL original, final DESTINATION destination)
	{
		try
		{
			CopyObjectExtensions.copyProperties(original, destination);
			return destination;
		}
		catch (IllegalAccessException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		catch (InvocationTargetException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		return null;
	}

	/**
	 * Closes the given OutputStream.
	 *
	 * @param out
	 *            The OutputStream to close.
	 * @deprecated use instead the try-with-resources Statement. Note: will be removed on next minor
	 *             version
	 * @return Returns true if the OutputStream is closed otherwise false.
	 */
	public static boolean closeOutputStream(OutputStream out)
	{
		boolean closed = true;
		try
		{
			if (out != null)
			{
				out.flush();
				out.close();
				out = null;
			}
		}
		catch (final IOException e)
		{
			closed = false;
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.flush();
					out.close();
				}
			}
			catch (final IOException e)
			{
				closed = false;
			}
		}
		return closed;
	}

}
