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
package de.alpharogroup.reflection;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.test.objects.A;
import de.alpharogroup.test.objects.Member;
import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link ReflectionExtensions}
 */
public class ReflectionExtensionsTest
{

	/**
	 * Test method for {@link ReflectionExtensions#copyFieldValue(Object, Object, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testCopyFieldValue()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person alex = Person.builder().name("Alex").build();
		final Person nik = Person.builder().name("Nik").build();
		expected = "Alex";
		ReflectionExtensions.copyFieldValue(alex, nik, "name");
		actual = nik.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#firstCharacterToUpperCase(String)}.
	 */
	@Test
	public void testFirstCharacterToUpperCase()
	{
		String expected;
		String actual;
		actual = ReflectionExtensions.firstCharacterToUpperCase("name");

		expected = "Name";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class)}
	 */
	@Test
	public void testGetAllDeclaredFieldNames()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class);
		expected = 6;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class);
		expected = 9;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetAllDeclaredFieldNamesWithIgnoreFields()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID", "name"));
		expected = 4;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class,
			ListFactory.newArrayList("dateofbirth", "name"));
		expected = 7;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class, String...)}
	 */
	@Test
	public void testGetAllDeclaredFieldNamesWithVarargs()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class,
			"serialVersionUID", "name");
		expected = 4;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class,
			"dateofbirth", "name");
		expected = 7;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFields(Class)}
	 */
	@Test
	public void testGetAllDeclaredFields()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(Person.class);
		expected = 6;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(Member.class);
		expected = 9;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredField(Class, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetDeclaredFieldClassOfQString() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		Field declaredField = ReflectionExtensions.getDeclaredField(Person.class, "name");
		assertNotNull(declaredField);
		expected = "name";
		actual = declaredField.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class)}
	 */
	@Test
	public void testGetDeclaredFieldNames()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class);
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("serialVersionUID"));
		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldNamesWithIgnoreFields()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID", "name"));
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldNamesWithVarargs()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class,
			"serialVersionUID", "name");
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFields(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldsWithIgnoreFields()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class,
			ListFactory.newArrayList("serialVersionUID"));
		expected = 5;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class,
			ListFactory.newArrayList("serialVersionUID", "married"));
		expected = 4;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class,
			ListFactory.newArrayList("serialVersionUID"));
		expected = 2;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class,
			ListFactory.newArrayList("serialVersionUID", "dateofMarriage"));
		expected = 1;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFields(Class, String...)}
	 */
	@Test
	public void testGetDeclaredFieldsWithVarargs()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class,
			"serialVersionUID");
		expected = 5;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class, "serialVersionUID",
			"married");
		expected = 4;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class,
			"serialVersionUID");
		expected = 2;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class, "serialVersionUID",
			"dateofMarriage");
		expected = 1;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredField(Object, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetDeclaredFieldTString() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		final Person alex = Person.builder().name("Alex").build();
		Field declaredField = ReflectionExtensions.getDeclaredField(alex, "name");
		assertNotNull(declaredField);
		expected = "name";
		actual = declaredField.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class)}
	 */
	@Test
	public void testGetFieldNames()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("serialVersionUID"));
		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}


	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class, List)}
	 */
	@Test
	public void testGetFieldNamesIgnore()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID"));
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class, String...)}
	 */
	@Test
	public void testGetFieldNamesIgnoreVarargs()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class,
			"serialVersionUID");
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldValue(Object, String)}
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testGetFieldValueObject()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Alex";
		actual = (String)ReflectionExtensions.getFieldValue(person, "name");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getMethodNames(Class)}.
	 */
	@Test
	public void testGetMethodNames()
	{
		List<String> methodNames = Arrays.asList(ReflectionExtensions.getMethodNames(Person.class));
		assertNotNull(methodNames);

		assertTrue(methodNames.contains("builder"));
		assertTrue(methodNames.contains("getNickname"));
		assertTrue(methodNames.contains("getName"));
	}


	/**
	 * Test method for
	 * {@link ReflectionExtensions#getMethodNamesWithPrefixFromFieldNames(java.util.List, String)}.
	 */
	@Test
	public void testGetMethodNamesWithPrefixFromFieldNames()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class);
		assertNotNull(fieldNames);
		Map<String, String> methodNames = ReflectionExtensions
			.getMethodNamesWithPrefixFromFieldNames(fieldNames, "get");
		assertNotNull(methodNames);

		assertNotNull(methodNames.get("serialVersionUID"));
		assertNotNull(methodNames.get("gender"));
		assertNotNull(methodNames.get("name"));
		assertNotNull(methodNames.get("nickname"));
		assertNotNull(methodNames.get("about"));
		assertNotNull(methodNames.get("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getModifiers(java.lang.reflect.Field)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetModifiers() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		Field declaredField = ReflectionExtensions.getDeclaredField(Person.class, "name");
		List<String> modifiers = ReflectionExtensions.getModifiers(declaredField);

		assertNotNull(modifiers);
		assertFalse(modifiers.isEmpty());
		expected = "private";
		actual = modifiers.get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#newInstance(Class)}.
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its nullary constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no nullary
	 *             constructor; or if the instantiation fails for some other reason.
	 */
	@Test
	public void testNewInstanceClassOfT() throws InstantiationException, IllegalAccessException
	{
		Person expected;
		Person actual;
		final Class<Person> clazz = Person.class;
		actual = ReflectionExtensions.newInstance(clazz);
		assertNotNull(actual);
		expected = new Person();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#newInstance(Object)}.
	 *
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 * @throws IllegalAccessException
	 *             is thrown if the class or its nullary constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no nullary
	 *             constructor; or if the instantiation fails for some other reason.
	 */
	@Test
	public void testNewInstanceT()
		throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Object expected;
		Object actual;
		final A a = new A();
		actual = ReflectionExtensions.newInstance(a);
		assertNotNull(actual);
		expected = new A();
		assertEquals(expected, actual);
		expected = A.builder().build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#newInstanceWithObjenesis(Class)}
	 */
	@Test
	public void testNewInstanceWithObjenesis()
	{
		Person expected;
		Person actual;
		final Class<Person> clazz = Person.class;
		actual = ReflectionExtensions.newInstanceWithObjenesis(clazz);
		assertNotNull(actual);
		expected = Person.builder().build();
		expected.setAbout(null);
		expected.setGender(null);
		expected.setMarried(null);
		expected.setName(null);
		expected.setNickname(null);
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link ReflectionExtensions#setFieldValue(Object, String, Object)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testSetFieldValueObject()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Leo";
		ReflectionExtensions.setFieldValue(person, "name", "Leo");
		actual = person.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#setFieldValue(Class, String, Object)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testSetFieldValueWithClass()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;

		ReflectionExtensions.setFieldValue(StaticBox.class, "value", "Leo");
		actual = StaticBox.getValue();
		expected = "Leo";
		assertEquals(expected, actual);

		ReflectionExtensions.setFieldValue(StaticBox.class, "value", null);
		actual = StaticBox.getValue();
		expected = null;
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link ReflectionExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(ReflectionExtensions.class);
	}

}

class StaticBox
{
	private static String value;

	public static String getValue()
	{
		return value;
	}
}
