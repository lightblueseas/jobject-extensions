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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.date.DateDecorator;
import de.alpharogroup.date.SqlTimestampDecorator;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link CopyObjectExtensions}.
 */
public class CopyObjectExtensionsTest
{

	/**
	 * Test method for {@link CopyObjectExtensions#copy(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	@Test(enabled = true)
	public void testCopyNotEqualType() throws IllegalAccessException, InvocationTargetException
	{
		Object expected;
		Object actual;

		final DateDecorator dateDecorator = DateDecorator.builder().date(CreateDateExtensions.now())
			.build();

		final SqlTimestampDecorator timestampDecorator = SqlTimestampDecorator.builder().build();

		CopyObjectExtensions.copy(dateDecorator, timestampDecorator);
		expected = dateDecorator.getDate().getTime();
		actual = timestampDecorator.getDate().getTime();
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link CopyObjectExtensions#copyObject(Object)}
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
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
	 */
	@Test
	public void testCopyObject()
		throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		Person actual;
		Person expected;

		expected = Person.builder().gender(Gender.MALE).name("asterix").build();
		actual = CopyObjectExtensions.copyObject(expected);
		assertEquals(expected, actual);

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		Employee original = Employee.builder().person(person).id("23").build();
		Employee employee = CopyObjectExtensions.copyObject(original);
		assertEquals(original, employee);
	}

	/**
	 * Test method for {@link CopyObjectExtensions#copyObject(Object)}
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
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
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 */
	@Test
	public void testCopyObjectIgnoreFields()
		throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		Person actual;
		Person expected;

		expected = Person.builder().gender(Gender.MALE).name("asterix").build();
		actual = CopyObjectExtensions.copyObject(expected);
		assertEquals(expected, actual);

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		Employee original = Employee.builder().person(person).id("23").build();
		Employee destination = Employee.builder().build();
		Employee employee = CopyObjectExtensions.copyObject(original, destination);
		assertEquals(original, employee);
		// new scenario with ignore the id...
		destination = Employee.builder().build();
		employee = CopyObjectExtensions.copyObject(original, destination, "id");
		original.setId(null);
		assertEquals(original, employee);
	}

	/**
	 * Test method for {@link CopyObjectExtensions#copyProperties(Object)}
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
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
	 */
	@Test
	public void testCopyProperties()
		throws IllegalAccessException, InvocationTargetException, InstantiationException
	{
		Person actual;
		Person expected;

		expected = Person.builder().gender(Gender.MALE).name("asterix").build();
		actual = CopyObjectExtensions.copyProperties(expected);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link CopyObjectExtensions#copyPropertyWithReflection(Object, Object, String)}.
	 */
	@Test(enabled = true)
	public void testCopyPropertyWithReflection() throws NoSuchFieldException, SecurityException,
		IllegalArgumentException, IllegalAccessException
	{

		Person expected;
		Person actual;

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		Employee original = Employee.builder().person(person).id("23").build();

		Employee destination = Employee.builder().build();

		CopyObjectExtensions.copyPropertyWithReflection(original, destination, "person");
		expected = original.getPerson();
		actual = destination.getPerson();
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link CopyObjectExtensions#copySerializedObject(java.io.Serializable)}.
	 */
	@Test(enabled = true)
	public void testCopySerializedObject() throws ClassNotFoundException, IOException
	{
		Employee expected;
		Employee actual;

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("Ha ha ha...").nickname("beast").build();

		expected = Employee.builder().person(person).id("23").build();

		actual = CopyObjectExtensions.copySerializedObject(expected);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CopyObjectExtensions#copyPropertiesWithReflection(Object)}
	 *
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	@Test(enabled = true)
	public void testCopyPropertiesWithReflection()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException
	{
		Employee expected;
		Employee actual;

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
				.about("Bla foo bar ...").nickname("beast").build();

		expected = Employee.builder().person(person).id("23").build();

		actual = CopyObjectExtensions.copyPropertiesWithReflection(expected, "serialVersionUID");

		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link CopyObjectExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CopyObjectExtensions.class);
	}

}
