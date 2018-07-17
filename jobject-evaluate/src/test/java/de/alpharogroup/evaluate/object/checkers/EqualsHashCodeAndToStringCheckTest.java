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
import de.alpharogroup.evaluate.object.enums.EqualsHashCodeViolation;
import de.alpharogroup.evaluate.object.enums.HashcodeContractViolation;

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
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsAndHashcode(Object, Object, Object, Object)}.
	 */
	@Test
	public void testEqualsAndHashcode()
	{
		// TODO ...
		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(HashcodeContractViolation.UNEQAUALITY);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashCodeViolation.FIRST_AND_SECOND_EQUAL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0), null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(null, Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.of(EqualsHashCodeViolation.FIRST_ARG_NULL);
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcode(Integer.valueOf(0),
			Integer.valueOf(1), null, Integer.valueOf(0));
		expected = Optional.of(EqualsHashCodeViolation.FIRST_AND_THIRD_UNEQUAL);
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
	@Test(enabled = false)
	public void testEqualsAndHashcodeEquality()
	{
		// TODO ...
		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(0),
			Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(1),
			Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(null, Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeEquality(Integer.valueOf(0), null);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsAndHashcodeUnequality(Object, Object)}.
	 */
	@Test(enabled = false)
	public void testEqualsAndHashcodeUnequality()
	{
		// TODO ...
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
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsAndHashcodeUnequality(Integer.valueOf(0),
			null);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsHashCodeAndToStringCheck#equalsHashcodeAndToString(Object)}.
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	@Test(enabled = false)
	public void testEqualsHashcodeAndToStringT() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		// TODO ...
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(String.valueOf("foo"));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(null);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsHashcodeAndToString(Object, Object, Object, Object)}.
	 */
	@Test(enabled = false)
	public void testEqualsHashcodeAndToStringTTTT()
	{
		// TODO ...
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0), null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(null, Integer.valueOf(0),
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), null, Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(Integer.valueOf(0),
			Integer.valueOf(1), Integer.valueOf(0), null);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link EqualsHashCodeAndToStringCheck#equalsHashcodeEqualityAndToString(Object, Object, Object)}.
	 */
	@Test(enabled = false)
	public void testEqualsHashcodeEqualityAndToString()
	{
		// TODO ...
		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeEqualityAndToString(
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsHashCodeAndToStringCheck.equalsHashcodeEqualityAndToString(null,
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
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
