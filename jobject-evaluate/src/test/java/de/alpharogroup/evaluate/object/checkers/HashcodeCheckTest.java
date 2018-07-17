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

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.HashcodeContractViolation;

/**
 * The unit test class for the class {@link HashcodeCheck}
 */
public class HashcodeCheckTest
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
	 * Test method for {@link HashcodeCheck#consistency(Object)}
	 */
	@Test
	public void testConsistency()
	{
		actual = HashcodeCheck.consistency(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.consistency(Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.consistency(Integer.valueOf(10));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.consistency(null);
		expected = Optional.of(HashcodeContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeCheck#equality(Object, Object)}
	 */
	@Test
	public void testEquality()
	{
		actual = HashcodeCheck.equality(Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.equality(null, Integer.valueOf(0));
		expected = Optional.of(HashcodeContractViolation.EQAUALITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = HashcodeCheck.equality(Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeCheck#unequality(Object, Object)}
	 */
	@Test
	public void testUnequality()
	{
		actual = HashcodeCheck.unequality(Integer.valueOf(0), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.unequality(Integer.valueOf(1), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = HashcodeCheck.unequality(null, Integer.valueOf(0));
		expected = Optional.of(HashcodeContractViolation.UNEQAUALITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = HashcodeCheck.unequality(Integer.valueOf(1), null);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link HashcodeCheck} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(HashcodeCheck.class);
	}

}
