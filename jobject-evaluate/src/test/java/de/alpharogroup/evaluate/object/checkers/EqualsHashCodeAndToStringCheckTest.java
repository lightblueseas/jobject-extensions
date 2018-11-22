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
package de.alpharogroup.evaluate.object.checkers;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.EqualsContractViolation;
import de.alpharogroup.evaluate.object.enums.EqualsHashcodeContractViolation;
import de.alpharogroup.evaluate.object.enums.HashcodeContractViolation;
import de.alpharogroup.evaluate.object.enums.ToStringContractViolation;
import de.alpharogroup.test.objects.Person;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.EqualsAndHashCode;

/**
 * The unit test class for the class {@link EqualsHashCodeAndToStringCheck}
 */
public class EqualsHashCodeAndToStringCheckTest
	extends
		AbstractTestCase<Optional<ContractViolation>, Optional<ContractViolation>>
{

	/**
	 * {@inheritDoc}
	 */
	@BeforeMethod
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	@AfterMethod
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}


	/**
	 * Test method for {@link EqualsHashCodeAndToStringCheck#hashcodeCheck(Object, Object, Object)}
	 */
	@Test(enabled = true)
	public void testHashcodeCheck()
	{
		actual = EqualsHashCodeAndToStringCheck.hashcodeCheck(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(HashcodeContractViolation.UNEQAUALITY);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.hashcodeCheck(new Person()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int hashCode()
			{
				return EnhancedRandom.random(Integer.class);
			}
		}, Person.builder().build(), new Person()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int hashCode()
			{
				return EnhancedRandom.random(Integer.class);
			}
		});
		expected = Optional.of(HashcodeContractViolation.EQAUALITY);
		assertEquals(expected, actual);

	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsAndHashcode(Object, Object, Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEqualsAndHashcode()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_AND_SECOND_EQUAL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0), null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(null, Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_ARG_NULL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), null, Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_AND_THIRD_UNEQUAL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsAndHashcodeEquality(Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEqualsAndHashcodeEquality()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(0),
			Integer.valueOf(1));
		expected = Optional.of(HashcodeContractViolation.EQAUALITY);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(1),
			Integer.valueOf(0));
		expected = Optional.of(HashcodeContractViolation.EQAUALITY);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(null, Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsAndHashcodeUnequality(Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEqualsAndHashcodeUnequality()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeUnequality(Integer.valueOf(0),
			Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeUnequality(Integer.valueOf(1),
			Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeUnequality(null,
			Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeUnequality(Integer.valueOf(0),
			null);
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsHashcodeAndToString(Object, Object, Object, Object)}.
	 */
	@Test(enabled = true)
	public void testEqualsHashcodeAndToString()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_AND_SECOND_EQUAL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0), null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(null, Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_ARG_NULL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), null, Integer.valueOf(0));
		expected = Optional.of(EqualsHashcodeContractViolation.FIRST_AND_THIRD_UNEQUAL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);	
	}

	/**
	 * Test method for {@link EqualsHashCodeAndToStringCheck#equalsHashcodeAndToString(Class)}.
	 * 
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test(enabled = true)
	public void testEqualsHashcodeAndToStringClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(null);
		expected = Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Person.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(String.class);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsHashCodeAndToStringCheck#equalsHashcodeAndToString(Object)}
	 */
	@Test(enabled = true)
	public void testEqualsHashcodeAndToStringObject()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(String.valueOf("foo"));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString((Object)null);
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(new Person()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int hashCode()
			{
				return EnhancedRandom.random(Integer.class);
			}
		});
		expected = Optional.of(HashcodeContractViolation.CONSISTENCY);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsHashcodeEqualityAndToString(Object, Object, Object)}
	 */
	@Test(enabled = true)
	public void testEqualsHashcodeEqualityAndToString()
	{
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeEqualityAndToString(
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeEqualityAndToString(null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(ToStringContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsHashCodeAndToStringCheck} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(EqualsHashCodeAndToStringCheck.class);
	}

}
