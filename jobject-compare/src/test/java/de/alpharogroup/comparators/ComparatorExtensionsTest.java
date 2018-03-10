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
package de.alpharogroup.comparators;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;
import lombok.experimental.ExtensionMethod;

/**
 * The unit test class for the class {@link ComparatorExtensions}.
 */
@ExtensionMethod({ ComparatorExtensions.class })
public class ComparatorExtensionsTest
{

	/**
	 * Test for method {@link ComparatorExtensions#compare(Comparable, Comparable)}.
	 */
	@Test
	public void testCompare()
	{
		final Person person = Person.builder().name("al").build();
		final Person otherPerson = Person.builder().name("bert").build();
		final int actual = person.compare(otherPerson);
		assertTrue(actual == -1);
	}

	/**
	 * Test for method {@link ComparatorExtensions#compare(Comparable, Comparable, SortOrder)}.
	 */
	@Test
	public void testCompareSortOrder()
	{
		final Person person = Person.builder().name("al").build();
		final Person otherPerson = Person.builder().name("bert").build();
		int actual = person.compare(otherPerson, SortOrder.DESCENDING);
		assertTrue(actual == 1);
		actual = person.compare(otherPerson, SortOrder.ASCENDING);
		assertTrue(actual == -1);
	}

	/**
	 * Test for method {@link ComparatorExtensions#equalNullCheck(Object, Object)}.
	 */
	@Test
	public void testEqualNullCheck()
	{
		Boolean expected;
		Boolean actual;
		final Person person = Person.builder().name("al").build();
		final Person otherPerson = Person.builder().name("bert").build();

		expected = null;
		actual = ComparatorExtensions.equalNullCheck(person, otherPerson);

		assertNull(actual);
		assertEquals(actual, expected);

		expected = true;
		actual = ComparatorExtensions.equalNullCheck(person, person);
		assertEquals(actual, expected);

		expected = false;
		actual = ComparatorExtensions.equalNullCheck(person, null);
		assertEquals(actual, expected);

		expected = false;
		actual = ComparatorExtensions.equalNullCheck(null, person);
		assertEquals(actual, expected);

	}

	/**
	 * Test for method {@link ComparatorExtensions#nullCheck(Object, Object)}.
	 */
	@Test
	public void testNullCheck()
	{
		Integer actual = ComparatorExtensions.nullCheck(Person.builder().build(), null);
		assertTrue(actual == 1);
		Person person = Person.builder().build();
		actual = person.nullCheck(null);
		assertTrue(actual == 1);
		final Person otherPerson = Person.builder().name("s").build();
		actual = person.nullCheck(otherPerson);
		assertTrue(actual == null);
		person = null;
		actual = person.nullCheck(null);
		assertTrue(actual == 0);
		actual = person.nullCheck(otherPerson);
		assertTrue(actual == -1);
	}

	/**
	 * Test for method {@link ComparatorExtensions#nullCheck(Object, Object, boolean)}.
	 */
	@Test
	public void testNullCheckWithNullFlag()
	{
		// false case...
		Integer actual = ComparatorExtensions.nullCheck(Person.builder().build(), null, false);
		assertTrue(actual == 1);
		Person person = Person.builder().build();
		actual = person.nullCheck(null, false);
		assertTrue(actual == 1);
		final Person otherPerson = Person.builder().name("s").build();
		actual = person.nullCheck(otherPerson, false);
		assertTrue(actual == null);
		person = null;
		actual = person.nullCheck(null, false);
		assertTrue(actual == 0);
		actual = person.nullCheck(otherPerson, false);
		assertTrue(actual == -1);
		// true case...
		actual = ComparatorExtensions.nullCheck(Person.builder().build(), null, true);
		assertTrue(actual == -1);
		person = Person.builder().build();
		actual = person.nullCheck(null, true);
		assertTrue(actual == -1);
		actual = person.nullCheck(otherPerson, true);
		assertTrue(actual == null);
		person = null;
		actual = person.nullCheck(null, true);
		assertTrue(actual == 0);
		actual = person.nullCheck(otherPerson, true);
		assertTrue(actual == 1);
	}

}
