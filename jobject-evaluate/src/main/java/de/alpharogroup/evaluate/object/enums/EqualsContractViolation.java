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
