package de.alpharogroup.evaluate.object.enums;

import de.alpharogroup.evaluate.object.api.ContractViolation;

/**
 * The enum {@link ToStringContractViolation} represents a contract violation as the name let
 * presume
 */
public enum ToStringContractViolation implements ContractViolation
{
	/** This value represents that the given class argument is null. */
	CLASS_NULL_ARGUMENT,
	
	/** This value represents the consistency contract violation. */
	CONSISTENCY,
	
	/** This value represents that the given argument is null for the consistency evaluation. */
	CONSISTENCY_NULL_ARGUMENT,

	/** This value represents that the method does not exists. */
	NOT_EXISTENT, 
	
	/** This value represents that the method does not return a {@link String} object. */
	RETURNTYPE_NOT_STRING;
}
