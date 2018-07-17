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
/**
 * 
 */
package de.alpharogroup.evaluate.object.checkers;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.ToStringContractViolation;

/**
 * The unit test class for the class {@link ToStringCheck}
 */
public class ToStringCheckTest
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
	 * Test method for {@link ToStringCheck#consistency(Object)}
	 */
	@Test
	public void testConsistency()
	{
		actual = ToStringCheck.consistency(Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ToStringCheck#consistency(Object, int)}
	 */
	@Test
	public void testConsistencyWithIterations()
	{
		actual = ToStringCheck.consistency(Integer.valueOf(1), 10);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ToStringCheck#evaluate(Class)}
	 */
	@Test
	public void testEvaluate()
	{
		actual = ToStringCheck.evaluate(Integer.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = ToStringCheck.evaluate(String.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = ToStringCheck.evaluate(null);
		expected = Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = ToStringCheck.evaluate(Serializable.class);
		expected = Optional.of(ToStringContractViolation.NOT_EXISTENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ToStringCheck} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ToStringCheck.class);
	}

}
