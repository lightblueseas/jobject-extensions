/**
 * 
 */
package de.alpharogroup.evaluate.object.checkers;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Optional;

import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.EqualsContractViolation;

/**
 * The unit test class for the class {@link EqualsCheck}
 */
public class EqualsCheckTest 
{

	Optional<ContractViolation> actual;
	Optional<ContractViolation> expected;
	
	/**
	 * Test method for {@link EqualsCheck#consistency(Object, Object)}.
	 */
	@Test
	public void testConsistency()
	{
		actual = EqualsCheck.consistency(Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(1), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(null, Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#consistency(Object, Object, int)}.
	 */
	@Test
	public void testConsistencyWithIterations()
	{
		actual = EqualsCheck.consistency(Integer.valueOf(0), Integer.valueOf(0), 10);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(1), Integer.valueOf(0), 20);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(1), Integer.valueOf(1), 100);
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(null, Integer.valueOf(0), 10);
		expected = Optional.of(EqualsContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.consistency(Integer.valueOf(0), null, 10);
		expected = Optional.of(EqualsContractViolation.CONSISTENCY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#nonNull(Object)}.
	 */
	@Test
	public void testNonNull()
	{
		actual = EqualsCheck.nonNull(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.nonNull(Integer.valueOf(10));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.nonNull(Integer.valueOf(100));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.nonNull(null);
		expected = Optional.of(EqualsContractViolation.NON_NULL_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#reflexivity(Object)}.
	 */
	@Test
	public void testReflexivity()
	{
		actual = EqualsCheck.reflexivity(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivity(Integer.valueOf(10));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivity(Integer.valueOf(100));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivity(null);
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#reflexivityAndNonNull(Object)}.
	 */
	@Test
	public void testReflexivityAndNonNull()
	{
		actual = EqualsCheck.reflexivityAndNonNull(Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityAndNonNull(Integer.valueOf(10));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityAndNonNull(Integer.valueOf(100));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityAndNonNull(null);
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	
	}

	/**
	 * Test method for {@link EqualsCheck#reflexivityNonNullSymmetricAndConsistency(Object, Object)}.
	 */
	@Test
	public void testReflexivityNonNullSymmetricAndConsistency()
	{
		actual = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(
			Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(
			Integer.valueOf(1), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(
			Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(null,
			Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#reflexivityNonNullSymmetricConsistencyAndTransitivity(Object, Object, Object)}.
	 */
	@Test
	public void testReflexivityNonNullSymmetricConsistencyAndTransitivity()
	{
		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(
			Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(
			Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(
			Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(null,
			Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(
			Integer.valueOf(1), null, Integer.valueOf(1));
		expected = Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(
			Integer.valueOf(1), Integer.valueOf(1), null);
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#symmetric(Object, Object)}.
	 */
	@Test
	public void testSymmetric()
	{
		actual = EqualsCheck.symmetric(Integer.valueOf(0), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetric(Integer.valueOf(1), Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetric(Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetric(null, Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetric(Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#symmetricAndConsistency(Object, Object)}.
	 */
	@Test
	public void testSymmetricAndConsistency()
	{
		// TODO throw new RuntimeException("not yet implemented");
		actual = EqualsCheck.symmetricAndConsistency(Integer.valueOf(0),
			Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetricAndConsistency(Integer.valueOf(1),
			Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetricAndConsistency(Integer.valueOf(1),
			Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetricAndConsistency(null, Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.symmetricAndConsistency(Integer.valueOf(0), null);
		expected = Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EqualsCheck#transitivity(Object, Object, Object)}.
	 */
	@Test
	public void testTransitivity()
	{
		// TODO throw new RuntimeException("not yet implemented");
		actual = EqualsCheck.transitivity(Integer.valueOf(0), Integer.valueOf(0),
			Integer.valueOf(0));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.transitivity(Integer.valueOf(0), Integer.valueOf(1),
			Integer.valueOf(0));
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);

		actual = EqualsCheck.transitivity(Integer.valueOf(1), Integer.valueOf(1),
			Integer.valueOf(1));
		expected = Optional.empty();
		assertEquals(expected, actual);

		actual = EqualsCheck.transitivity(null, Integer.valueOf(1), Integer.valueOf(1));
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.transitivity(Integer.valueOf(1), null, Integer.valueOf(1));
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY_NULL_ARGUMENT);
		assertEquals(expected, actual);

		actual = EqualsCheck.transitivity(Integer.valueOf(1), Integer.valueOf(1), null);
		expected = Optional.of(EqualsContractViolation.TRANSITIVITY);
		assertEquals(expected, actual);
	}

}
