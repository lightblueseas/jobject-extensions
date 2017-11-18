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
package de.alpharogroup.clone.object;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.test.objects.A;
import lombok.experimental.ExtensionMethod;

/**
 * The unit test class for the class {@link CloneObjectExtensions}.
 */
@ExtensionMethod(CloneObjectExtensions.class)
public class CloneObjectExtensionsTest
{

	/**
	 * Test method for {@link CloneObjectExtensions#clone(Object)}.
	 *
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws IllegalArgumentException
	 *             Thrown if an illegal argument is given
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
	@Test(enabled = true)
	public void testClone() throws NoSuchMethodException, SecurityException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
		InstantiationException, IOException
	{
		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectExtensions#cloneObject(Object)}.
	 * 
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 * @throws SecurityException
	 *             Thrown if the security manager indicates a security violation.
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws IllegalArgumentException
	 *             Thrown if an illegal argument is given
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
	@Test(enabled = true)
	public void testCloneObject()
		throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectExtensions.cloneObject(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectExtensions.cloneObject(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectExtensions.cloneObject(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectExtensions#cloneObjectQuietly(Object)}.
	 */
	@Test(enabled = true)
	public void testCloneObjectQuietly()
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectExtensions#cloneQuietly(Object)}.
	 */
	@Test(enabled = true)
	public void testCloneQuietly()
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

}

