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
package de.alpharogroup.diff.object;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.diff.beans.ChangedAttributeResult;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link DiffObjectExtensions}.
 */
public class DiffObjectExtensionsTest
{

	/**
	 * A rule for expecting exceptions
	 */
	@Rule
	public ExpectedException throwable = ExpectedException.none();

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedData(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test
	public void testGetChangedData()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();

		final Person objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		List<ChangedAttributeResult> result = DiffObjectExtensions.getChangedData(sourceOjbect,
			objectToCompare);
		assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		objectToCompare.setGender(Gender.FEMALE);
		// and get the changed data...
		result = DiffObjectExtensions.getChangedData(sourceOjbect, objectToCompare);
		assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		assertTrue("Size should be 1 but is " + result.size(), result.size() == 1);

		final ChangedAttributeResult changed = result.get(0);
		final Object sourceAttribute = changed.getSourceAttribute();
		final Object changedAttribute = changed.getChangedAttribute();
		assertTrue("", sourceAttribute.equals(Gender.MALE.name()));
		assertTrue("", changedAttribute.equals(Gender.FEMALE.name()));
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedData(Object, Object)} with source as
	 * null value.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataCompareNullValue()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().build();
		final Person objectToCompare = null;
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument 'objectToCompare' may not be null.");
		DiffObjectExtensions.getChangedData(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedData(Object, Object)} with different
	 * class of source and compare object.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataDifferentClass()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().build();
		final Employee objectToCompare = Employee.builder().build();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Object should not be null and be the same type.");
		DiffObjectExtensions.getChangedData(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedDataMap(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true)
	public void testGetChangedDataMap()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();

		final Person objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		Map<Object, ChangedAttributeResult> result = DiffObjectExtensions
			.getChangedDataMap(sourceOjbect, objectToCompare);
		assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		objectToCompare.setGender(Gender.FEMALE);
		// and get the changed data...
		result = DiffObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
		assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		assertTrue("", result.containsKey("gender"));
		final ChangedAttributeResult changed = result.get("gender");
		final Object sourceAttribute = changed.getSourceAttribute();
		final Object changedAttribute = changed.getChangedAttribute();
		assertTrue("", sourceAttribute.equals(Gender.MALE.name()));
		assertTrue("", changedAttribute.equals(Gender.FEMALE.name()));
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedDataMap(Object, Object)} with source as
	 * null value.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataMapCompareNullValue()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().build();
		final Person objectToCompare = null;
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument 'objectToCompare' may not be null.");
		DiffObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
	}


	/**
	 * Test method for {@link DiffObjectExtensions#getChangedDataMap(Object, Object)} with different
	 * class of source and compare object.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataMapDifferentClass()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = Person.builder().build();
		final Employee objectToCompare = Employee.builder().build();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Object should not be null and be the same type.");
		DiffObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedDataMap(Object, Object)} with source as
	 * null value.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataMapSourceNullValue()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = null;
		final Person objectToCompare = Person.builder().build();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument 'sourceOjbect' may not be null.");
		DiffObjectExtensions.getChangedDataMap(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedData(Object, Object)} with source as
	 * null value.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetChangedDataSourceNullValue()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect = null;
		final Person objectToCompare = Person.builder().build();
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Given argument 'sourceOjbect' may not be null.");
		DiffObjectExtensions.getChangedData(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link DiffObjectExtensions#getChangedDataWithReflection(Object, Object,
	 * List, ChangedAttributeResult).
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this method or object is enforcing java language access control and the
	 *             underlying method or object is inaccessible.
	 */
	@Test
	public void testGetChangedDataWithReflection() throws IllegalAccessException
	{
		final Person sourceOjbect = Person.builder().build();
		final Person objectToCompare = Person.builder().build();
		final List<ChangedAttributeResult> result = DiffObjectExtensions
			.getChangedDataWithReflection(sourceOjbect, objectToCompare, null, null);
		assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// TODO extend unit test scenarios...
	}

	/**
	 * Test method for
	 * {@link DiffObjectExtensions#getChangedDataWithReflection(Object, Object, List, ChangedAttributeResult)}
	 * with different class of source and compare object.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(enabled = true)
	public void testGetChangedDataWithReflectionDifferentClass() throws IllegalAccessException
	{
		Object objectToCompare;
		final Person sourceOjbect = Person.builder().build();
		objectToCompare = Employee.builder().build();
		List<ChangedAttributeResult> result = DiffObjectExtensions
			.getChangedDataWithReflection(sourceOjbect, objectToCompare, null, null);
		assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);

		objectToCompare = Person.builder().build();
		result = DiffObjectExtensions.getChangedDataWithReflection(sourceOjbect, objectToCompare,
			result, null);
		assertTrue("Size should be 0 but is " + result.size(), result.size() == 0);
		// Change the gender from the objectToCompare...
		((Person)objectToCompare).setGender(Gender.FEMALE);

		// and get the changed data...
		result = DiffObjectExtensions.getChangedDataWithReflection(sourceOjbect, objectToCompare,
			result, null);
		assertFalse("Size should be 1 but is " + result.size(), result.size() == 0);
		assertTrue("Size should be 1 but is " + result.size(), result.size() == 1);
	}

	/**
	 * Test method for {@link DiffObjectExtensions} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(DiffObjectExtensions.class);
	}

}

