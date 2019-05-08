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
package de.alpharogroup.clone.object;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.array.ArrayFactory;
import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.test.objects.A;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Member;
import de.alpharogroup.test.objects.NotSerializable;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;
import lombok.SneakyThrows;

/**
 * The unit test class for the class {@link CloneObjectExtensions}.
 */
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
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 4);
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);


		expected = "Hy there...";
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = A.builder().a("a").build();
		actual = CloneObjectExtensions.clone(expected);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);

		expected = null;
		actual = CloneObjectExtensions.clone(null);
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link CloneObjectExtensions#clone(Object)} with an array.
	 */
	@Test(enabled = true)
	public void testCloneArray() throws NoSuchMethodException, SecurityException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		ClassNotFoundException, InstantiationException, IOException
	{
		String[] expected;
		String[] actual;

		expected = ArrayFactory.newArray("foo", "bar");
		actual = CloneObjectExtensions.clone(expected);
		for (int i = 0; i < actual.length; i++)
		{
			assertEquals("Cloned object should be equal with the source object.", expected[i],
				actual[i]);
		}
	}

	/**
	 * Test method for {@link CloneObjectExtensions#cloneBean(Object)} with an array.
	 */
	@Test(enabled = true)
	@SneakyThrows
	public void testCloneBeanWithComposition()
	{
		Employee actual;
		Employee expected;

		actual = Employee.builder().person(Person.builder().name("Nikky").nickname("Six")
			.gender(Gender.MALE).about("").married(false).build()).build();
		expected = CloneObjectExtensions.withCloner(actual);
		assertEquals("Cloned bean should be equal with the source object.", expected, actual);
	}

	/**
	 * Test method for {@link CloneObjectExtensions#cloneBean(Object)} with an array.
	 */
	@Test(enabled = true)
	@SneakyThrows
	public void testCloneWithCloner()
	{
		Person actual;
		Person expected;
		actual = Person.builder().name("Nikky").nickname("Six").gender(Gender.MALE).about("")
			.married(false).build();
		expected = CloneObjectExtensions.withCloner(actual);
		assertEquals("Cloned bean should be equal with the source object.", expected, actual);
		actual = Member.buildMember().name("Nikky").nickname("Six").gender(Gender.MALE).about("")
			.married(false).dateofbirth(new Date()).dateofMarriage(new Date()).build();

		expected = CloneObjectExtensions.withCloner(actual);
		assertEquals("Cloned bean should be equal with the source object.", expected, actual);
	}

	/**
	 * Test method for {@link CloneObjectExtensions#clone(Object)} with an array with primitive
	 * values.
	 */
	@Test(enabled = true)
	@SneakyThrows
	public void testCloneNotSerializable()
	{
		NotSerializable actual;
		NotSerializable expected;

		expected = NotSerializable.builder().name("foo").build();
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
	public void testCloneObject() throws NoSuchMethodException, IllegalAccessException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{

		Object expected;
		Object actual;

		expected = CreateDateExtensions.newDate(2009, 3, 26, 10, 37, 4);
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
	 * Test method for {@link CloneObjectExtensions#clone(Object)} with an array with primitive
	 * values.
	 */
	@Test(enabled = true)
	public void testClonePrimitiveArray()
		throws NoSuchMethodException, SecurityException, IllegalAccessException,
		InvocationTargetException, ClassNotFoundException, InstantiationException, IOException
	{
		int[] actual;
		int[] expected;

		expected = new int[2];
		expected[0] = 1;
		expected[1] = 2;
		actual = CloneObjectExtensions.clone(expected);
		for (int i = 0; i < actual.length; i++)
		{
			assertEquals("Cloned object should be equal with the source object.", expected[i],
				actual[i]);
		}
	}

	/**
	 * Test method for {@link CloneObjectExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(CloneObjectExtensions.class);
	}

}

