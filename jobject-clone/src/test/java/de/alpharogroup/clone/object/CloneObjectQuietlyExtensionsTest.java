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

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.test.objects.A;
import de.alpharogroup.test.objects.NotSerializable;
import lombok.experimental.ExtensionMethod;

/**
 * The unit test class for the class {@link CloneObjectQuietlyExtensions}.
 */
@ExtensionMethod(CloneObjectQuietlyExtensions.class)
public class CloneObjectQuietlyExtensionsTest
{

	/**
	 * Test method for {@link CloneObjectQuietlyExtensions#cloneQuietly(Object)} with not
	 * serializable object.
	 */
	@Test(enabled = true)
	public void testCloneNotSerializableObject()
	{

		Object expected;
		Object actual;

		expected = NotSerializable.builder().build();
		actual = CloneObjectQuietlyExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);
	}

	/**
	 * Test method for {@link CloneObjectQuietlyExtensions#cloneObjectQuietly(Object)}.
	 */
	@Test(enabled = true)
	public void testCloneObjectQuietly()
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectQuietlyExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectQuietlyExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectQuietlyExtensions.cloneObjectQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectQuietlyExtensions#cloneQuietly(Object)}.
	 */
	@Test(enabled = true)
	public void testCloneQuietly()
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 04);
		actual = CloneObjectQuietlyExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectQuietlyExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectQuietlyExtensions.cloneQuietly(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectQuietlyExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CloneObjectQuietlyExtensions.class);
	}

}

