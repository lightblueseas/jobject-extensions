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

import static org.testng.AssertJUnit.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link DefaultValue}.
 */
public class DefaultValueTest
{

	/**
	 * Test method for {@link DefaultValue#get(Class)}
	 */
	@Test
	public void testGetDefaultValue()
	{
		Object expected;
		Object actual;
		expected = Boolean.FALSE;
		actual = DefaultValue.get(boolean.class);
		assertEquals(expected, actual);

		expected = Character.valueOf('\0');
		actual = DefaultValue.get(char.class);
		assertEquals(expected, actual);

		expected = Byte.valueOf("0");
		actual = DefaultValue.get(byte.class);
		assertEquals(expected, actual);

		expected = Short.valueOf("0");
		actual = DefaultValue.get(short.class);
		assertEquals(expected, actual);

		expected = Integer.valueOf("0");
		actual = DefaultValue.get(int.class);
		assertEquals(expected, actual);

		expected = Long.valueOf("0");
		actual = DefaultValue.get(long.class);
		assertEquals(expected, actual);

		expected = Float.valueOf("0.0");
		actual = DefaultValue.get(float.class);
		assertEquals(expected, actual);

		expected = Double.valueOf("0.0");
		actual = DefaultValue.get(double.class);
		assertEquals(expected, actual);

		expected = null;
		actual = DefaultValue.get(Object.class);
		assertEquals(expected, actual);

		expected = null;
		actual = DefaultValue.get(void.class);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link DefaultValue} with {@link BeanTester}
	 */
	@Test(expectedExceptions={BeanTestException.class, InvocationTargetException.class, UnsupportedOperationException.class})
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(DefaultValue.class);
	}

}
