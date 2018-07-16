/**
 * 
 */
package de.alpharogroup.evaluate.object.checkers;

import static org.testng.Assert.assertEquals;

import java.util.Optional;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.ToStringContractViolation;

/**
 * The unit test class for the class {@link ToStringCheck}
 */
public class ToStringCheckTest extends AbstractTestCase<Optional<ContractViolation>, Optional<ContractViolation>>
{

	@BeforeMethod
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	@AfterMethod
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	
	/**
	 * Test method for {@link ToStringCheck#evaluate(Class)}.
	 */
	@Test
	public void testEvaluate() throws Exception
	{
		// TODO ...
		actual = ToStringCheck.evaluate(Integer.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = ToStringCheck.evaluate(String.class);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = ToStringCheck.evaluate(null);
		expected = Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ToStringCheck#consistency(Object)}.
	 */
	@Test
	public void testConsistency() throws Exception
	{
		// TODO ...
		actual = ToStringCheck.consistency(Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ToStringCheck#consistency(Object, int)}.
	 */
	@Test
	public void testConsistencyWithIterations() throws Exception
	{
		// TODO ...
		actual = ToStringCheck.consistency(Integer.valueOf(1), 10);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

}
