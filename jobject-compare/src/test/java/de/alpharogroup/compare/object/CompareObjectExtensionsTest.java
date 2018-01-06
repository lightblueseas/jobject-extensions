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
package de.alpharogroup.compare.object;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;
import lombok.extern.slf4j.Slf4j;

/**
 * The unit test class for the class {@link CompareObjectExtensions}.
 */
@Slf4j
public class CompareObjectExtensionsTest
{

	/**
	 * Test method for {@link CompareObjectExtensions#compare(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true)
	public void testCompare()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		boolean expected;
		boolean actual;
		// create a person...
		final Person sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		// make a clone of it...
		final Person objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();
		// 1. scenario...
		// expected: the compare method should return true...
		// explanation of expected: compared object are equal
		expected = true;
		actual = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		assertEquals("Cloned object should be equal with the source object.", expected, actual);
		// 2. scenario...
		// expected: the compare method should return false...
		// explanation of expected: compared object are not equal

		// change the gender and compare again...
		objectToCompare.setGender(Gender.FEMALE);

		expected = false;
		actual = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		assertEquals(
			"Object to compare should be not equal with the source object because it has changed.",
			expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareTo()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{

		int expected;
		int actual;
		// create a person...
		final Person sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		// make a clone of it...
		final Person objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals("Given objects should not be equal.", expected, actual);

		sourceOjbect.setAbout("Foo");
		expected = 3;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals("Given objects should not be equal.", expected, actual);


	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object, String)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(enabled = true)
	public void testCompareToProperty()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		final List<Person> persons = new ArrayList<>();
		final Person obelix = Person.builder().gender(Gender.MALE).name("obelix").build();

		final Person asterix = Person.builder().gender(Gender.MALE).name("asterix").build();

		final Person miraculix = Person.builder().gender(Gender.MALE).name("miraculix").build();

		// 1. scenario...
		actual = CompareObjectExtensions.compareTo(asterix, obelix, "name");

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

		persons.add(obelix);
		persons.add(asterix);
		persons.add(miraculix);

		assertEquals(persons.get(0), obelix);
		assertEquals(persons.get(1), asterix);
		assertEquals(persons.get(2), miraculix);

		log.debug("Unsorted Persons:");
		log.debug(persons.toString());
		final Comparator defaultComparator = new BeanComparator("name");
		Collections.sort(persons, defaultComparator);

		log.debug("Sorted Persons by name:");
		log.debug(persons.toString());

		assertEquals(persons.get(0), asterix);
		assertEquals(persons.get(1), miraculix);
		assertEquals(persons.get(2), obelix);

		Collections.reverse(persons);

		log.debug("Sorted Persons by name reversed:");
		log.debug(persons.toString());
		assertEquals(persons.get(0), obelix);
		assertEquals(persons.get(1), miraculix);
		assertEquals(persons.get(2), asterix);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareToQuietly(Object, Object, String)}.
	 */
	@Test
	public void testCompareToQuietly()
	{
		int expected;
		int actual;

		final Person obelix = Person.builder().gender(Gender.MALE).name("obelix").build();

		final Person asterix = Person.builder().gender(Gender.MALE).name("asterix").build();

		actual = CompareObjectExtensions.compareToQuietly(asterix, obelix, "name");

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
	 * Test method for {@link CompareObjectExtensions#getCompareToResult(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test
	public void testGetCompareToResult()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;

		final Person obelix = Person.builder().gender(Gender.MALE).name("obelix").build();

		final Person asterix = Person.builder().gender(Gender.MALE).name("asterix").build();
		Map<String, Integer> compareToResult = CompareObjectExtensions.getCompareToResult(asterix,
			obelix);

		assertNotNull(compareToResult);
		expected = 5;
		actual = compareToResult.size();

		assertEquals("size of map should be 5 but is " + actual + ".", expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions={BeanTestException.class, InvocationTargetException.class, UnsupportedOperationException.class})
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CompareObjectExtensions.class);
	}

}

