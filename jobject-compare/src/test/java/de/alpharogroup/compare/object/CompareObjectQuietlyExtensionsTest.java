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

import static org.testng.AssertJUnit.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link CompareObjectQuietlyExtensions}.
 */
public class CompareObjectQuietlyExtensionsTest
{

	/**
	 * Test method for
	 * {@link CompareObjectQuietlyExtensions#compareToQuietly(Object, Object, String)}.
	 */
	@Test
	public void testCompareToQuietly()
	{
		int expected;
		int actual;
		Person obelix;
		Person asterix;

		obelix = Person.builder().gender(Gender.MALE).name("obelix").build();

		asterix = Person.builder().gender(Gender.MALE).name("asterix").build();

		actual = CompareObjectQuietlyExtensions.compareToQuietly(asterix, obelix, "name");

		final Comparator<String> comp = new Comparator<String>()
		{

			@Override
			public int compare(final String o1, final String o2)
			{
				return o1.compareTo(o2);
			}
		};
		expected = comp.compare(asterix.getName(), obelix.getName());

		assertEquals("Result of compared properties should be equal.", expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectQuietlyExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CompareObjectQuietlyExtensions.class);
	}

}

