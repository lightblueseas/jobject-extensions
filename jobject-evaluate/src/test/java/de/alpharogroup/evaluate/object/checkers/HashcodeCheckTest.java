package de.alpharogroup.evaluate.object.checkers;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Optional;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.HashcodeContractViolation;

/**
 * The unit test class for the class {@link HashcodeCheck}
 */
public class HashcodeCheckTest extends AbstractTestCase<Optional<ContractViolation>, Optional<ContractViolation>>
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

}
