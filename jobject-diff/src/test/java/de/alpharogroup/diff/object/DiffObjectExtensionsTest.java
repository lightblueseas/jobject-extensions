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
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;

import de.alpharogroup.diff.ChangedAttributeResult;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;

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
	 * Test method for {@link MergeObjectExtensions#getChangedDataMap(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test(enabled = true)
	public void testGetChangedDataMap()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
//			new Person();
//		sourceOjbect.setGender(Gender.MALE);
//		sourceOjbect.setName("obelix");

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

		sourceOjbect = null;
		result = DiffObjectExtensions
				.getChangedDataMap(sourceOjbect, objectToCompare);
	}


	/**
	 * Test method for {@link MergeObjectExtensions#getChangedDataMap(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test(enabled = false)
	public void testGetChangedDataMapNullCass()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Person sourceOjbect =  null;
		final Person objectToCompare = null;
		throwable.expect(IllegalArgumentException.class);
		throwable.expectMessage("Object should not be null and be the same type.");
		DiffObjectExtensions
				.getChangedDataMap(sourceOjbect, objectToCompare);
	}


	@Test
	public void testGetChangedData()
	{
		// TODO
	}

	@Test
	public void testGetChangedDataWithReflection()
	{
		// TODO
	}

}

