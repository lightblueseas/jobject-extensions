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
package de.alpharogroup.evaluate.object.checkers;

import java.util.Optional;

import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.ToStringContractViolation;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ToStringCheck} provides algorithms for evaluate the {@link Object#toString()}
 * method.
 */
@UtilityClass
public final class ToStringCheck
{

	/**
	 * Checks if the given class implements the toString method.
	 *
	 * @param clazz
	 *            the class
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static Optional<ContractViolation> evaluate(Class<?> clazz)
	{
		if (clazz == null)
		{
			return Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		}
		try
		{
			clazz.getDeclaredMethod("toString");
		}
		catch (NoSuchMethodException ex)
		{
			return Optional.of(ToStringContractViolation.NOT_EXISTENT);
		}
		return Optional.empty();
	}

	/**
	 * Checks consistency of method {@link Object#toString()} for the given objects <br>
	 * This method calls the same name method with default iterations of 7<br>
	 * <br>
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> consistency(T object)
	{
		return consistency(object, 7);
	}

	/**
	 * Checks consistency of method {@link Object#toString()} for the given objects <br>
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param iterations
	 *            the iterations of call of equals method.
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> consistency(T object, int iterations)
	{
		if (object == null)
		{
			return Optional.of(ToStringContractViolation.CONSISTENCY_NULL_ARGUMENT);
		}
		final String initialToStringResult = object.toString();
		for (int i = 0; i < iterations; i++)
		{
			String currentToStringResult = object.toString();
			if (!initialToStringResult.equals(currentToStringResult))
			{
				return Optional.of(ToStringContractViolation.CONSISTENCY);
			}
		}
		return Optional.empty();
	}

	/**
	 * Checks the all the contract conditions for the methods {@link Object#toString()}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return true, if all contract conditions for the methods {@link Object#toString()} is given otherwise false
	 */
	public static <T> Optional<ContractViolation> evaluateAndConsistency(final T object)
	{
		if (object == null)
		{
			return Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		}
		Optional<ContractViolation> evaluated;
		evaluated = ToStringCheck.evaluate(object.getClass());
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = ToStringCheck.consistency(object);
		return evaluated;
	}

}
