package de.alpharogroup.evaluate.object.enums;

import de.alpharogroup.evaluate.object.api.ContractViolation;

public enum EqualsHashCodeViolation implements ContractViolation
{
	FIRST_ARG_NULL, FIRST_AND_SECOND_EQUAL, FIRST_AND_THIRD_UNEQUAL;
}
