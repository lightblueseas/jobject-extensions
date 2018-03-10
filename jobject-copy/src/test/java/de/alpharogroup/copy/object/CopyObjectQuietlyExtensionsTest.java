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

import static org.testng.AssertJUnit.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link CopyObjectExtensions}.
 */
public class CopyObjectQuietlyExtensionsTest
{

	/**
	 * Test method for {@link CopyObjectQuietlyExtensions#copyQuietly(Object, Object)}.
	 */
	@Test(enabled = true)
	public void testCopyQuietly()
	{
		Employee expected;
		Employee actual;

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		expected = Employee.builder().person(person).id("23").build();

		actual = Employee.builder().build();

		CopyObjectQuietlyExtensions.copyQuietly(expected, actual);

		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getPerson(), actual.getPerson());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CopyObjectQuietlyExtensions#isCopyable(Object, Object)}.
	 */
	@Test(enabled = true)
	public void testIsCopyable()
	{
		boolean expected;
		boolean actual;

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		Employee original = Employee.builder().person(person).id("23").build();

		Employee destination = Employee.builder().build();

		expected = true;
		actual = CopyObjectQuietlyExtensions.isCopyable(original, destination);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CopyObjectQuietlyExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CopyObjectQuietlyExtensions.class);
	}

}
