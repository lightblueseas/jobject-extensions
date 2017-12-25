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
package de.alpharogroup.copy.object;

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;

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
	 * Test method for {@link CopyObjectExtensions#closeOutputStream(OutputStream)}.
	 */
	@Test(enabled = true)
	public void testCloseOutputStream() throws FileNotFoundException, URISyntaxException
	{
		boolean expected;
		boolean actual;
		final URL url = getClass().getClassLoader().getResource("log4j2-test.xml");
		final OutputStream os = new FileOutputStream(new File(url.toURI()));
		expected = true;
		actual = CopyObjectExtensions.closeOutputStream(os);
		assertEquals(expected, actual);
	}

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
	 * Test method for {@link CopyObjectExtensions#copyQuietly(Object, Object)}.
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

		CopyObjectExtensions.copyQuietly(expected, actual);

		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getPerson(), actual.getPerson());
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
	 * Test method for {@link CopyObjectExtensions#isCopyable(Object, Object)}.
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
		actual = CopyObjectExtensions.isCopyable(original, destination);
		assertEquals(expected, actual);
	}

}
