package de.alpharogroup.evaluate.object.enums;

import de.alpharogroup.evaluate.object.api.ContractViolation;

/**
 * The enum {@link HashcodeContractViolation} represents a contract violation as the name let
 * presume
 */
public enum HashcodeContractViolation implements ContractViolation
{
	/** This value represents the consistency contract violation. */
	CONSISTENCY,

	/** This value represents that the given argument is null for the consistency evaluation. */
	CONSISTENCY_NULL_ARGUMENT,

	/** This value represents the eqauality contract violation. */
	EQAUALITY,

	/** This value represents that the given argument is null for the eqauality evaluation. */
	EQAUALITY_NULL_ARGUMENT,

	/** This value represents the uneqauality contract violation. */
	UNEQAUALITY,

	/** This value represents that the given argument is null for the uneqauality evaluation. */
	UNEQAUALITY_NULL_ARGUMENT;
}
