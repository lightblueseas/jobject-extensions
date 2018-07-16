package de.alpharogroup.evaluate.object.enums;

import de.alpharogroup.evaluate.object.api.ContractViolation;

/**
 * The enum {@link EqualsContractViolation} represents a contract violation as the name let presume
 */
public enum EqualsContractViolation implements ContractViolation
{
	/** This value represents the consistency contract violation. */
	CONSISTENCY,
	
	/** This value represents that the given argument is null for the consistency evaluation. */
	CONSISTENCY_NULL_ARGUMENT,

	/** This value represents the non null contract violation. */
	NON_NULL,

	/** This value represents that the given argument is null for the non null evaluation. */
	NON_NULL_NULL_ARGUMENT,

	/** This value represents the reflexivity contract violation. */
	REFLEXIVITY,

	/** This value represents that the given argument is null for the reflexivity evaluation. */
	REFLEXIVITY_NULL_ARGUMENT,

	/** This value represents the symmetric contract violation. */
	SYMMETRICITY,

	/** This value represents that the given argument is null for the symmetric evaluation. */
	SYMMETRICITY_NULL_ARGUMENT,

	/** This value represents the transitivity contract violation. */
	TRANSITIVITY,

	/** This value represents that the given argument is null for the transitivity evaluation. */
	TRANSITIVITY_NULL_ARGUMENT;
}
