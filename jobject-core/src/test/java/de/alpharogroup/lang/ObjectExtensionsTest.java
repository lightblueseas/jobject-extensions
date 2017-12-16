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
package de.alpharogroup.lang;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Company;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.annotations.AnnotatedInterface;
import de.alpharogroup.test.objects.annotations.Mandatory;
import lombok.experimental.ExtensionMethod;

/**
 * The unit test class for the class {@link ObjectExtensions}.
 */
@ExtensionMethod(ObjectExtensions.class)
public class ObjectExtensionsTest
{

	/**
	 * Test method for {@link ObjectExtensions#isDefaultValue(Class, Object)}
	 */
	@Test
	public void testIsDefaultValue()
	{
		boolean expected;
		boolean actual;

		expected = true;
		actual = ObjectExtensions.isDefaultValue(int.class, 0);
		assertEquals(expected, actual);

		final Company company = Company.builder().build();
		expected = true;
		actual = ObjectExtensions.isDefaultValue(String.class, company.getName());
		assertEquals(expected, actual);

		expected = false;
		actual = ObjectExtensions.isDefaultValue(int.class, 2);
		assertEquals(expected, actual);

		expected = false;
		actual = ObjectExtensions.isDefaultValue(Company.class, company);
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link ObjectExtensions#getClassType(Class)}
	 */
	@Test
	public void testGetClassType()
	{
		ClassType expected;
		ClassType actual;

		expected = ClassType.PRIMITIVE;
		actual = ObjectExtensions.getClassType(int.class);
		assertEquals(expected, actual);

		expected = ClassType.DEFAULT;
		actual = ObjectExtensions.getClassType(String.class);
		assertEquals(expected, actual);

		expected = ClassType.ANNOTATION;
		actual = ObjectExtensions.getClassType(Mandatory.class);
		assertEquals(expected, actual);

		expected = null;
		actual = ObjectExtensions.getClassType(null);
		assertEquals(expected, actual);

		expected = ClassType.ENUM;
		actual = ObjectExtensions.getClassType(Gender.class);
		assertEquals(expected, actual);

		expected = ClassType.ARRAY;
		final String [] stringArray = {"foo", "bar"};
		actual = ObjectExtensions.getClassType(stringArray.getClass());
		assertEquals(expected, actual);

		expected = ClassType.COLLECTION;
		final List<String> strings = new ArrayList<>();
		actual = ObjectExtensions.getClassType(strings.getClass());
		assertEquals(expected, actual);

		expected = ClassType.MAP;
		final Map<String, String> map = new HashMap<>();
		actual = ObjectExtensions.getClassType(map.getClass());
		assertEquals(expected, actual);

		expected = ClassType.INTERFACE;
		actual = ObjectExtensions.getClassType(AnnotatedInterface.class);
		assertEquals(expected, actual);

	}

}
