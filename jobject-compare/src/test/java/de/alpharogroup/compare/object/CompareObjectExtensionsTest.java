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

import static org.testng.AssertJUnit.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.clone.object.CloneObjectExtensions;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * The unit test class for the class {@link CompareObjectExtensions}.
 */
@ExtensionMethod(CompareObjectExtensions.class)
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
	@Test(enabled = false)
	public void testCompare()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = new Person();
		sourceOjbect.setGender(Gender.MALE);
		sourceOjbect.setName("obelix");

		final Person objectToCompare = (Person)CloneObjectExtensions
			.cloneObjectQuietly(sourceOjbect);

		boolean result = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		assertTrue("Cloned object should be equal with the source object.", result);

		objectToCompare.setGender(Gender.FEMALE);
		result = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		AssertJUnit.assertFalse(
			"Object to compare should be not equal with the source object because it has changed.",
			result);
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
	public void testCompareTo()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final List<Person> persons = new ArrayList<>();
		final Person obelix = Person.builder()
			.gender(Gender.MALE)
			.name("obelix")
			.build();

		final Person asterix = Person.builder()
			.gender(Gender.MALE)
			.name("asterix")
			.build();

		final Person miraculix = Person.builder()
			.gender(Gender.MALE)
			.name("miraculix")
			.build();

		final int i = CompareObjectExtensions.compareTo(asterix, obelix, "name");

		assertTrue(i < 0);

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

}

