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
import de.alpharogroup.evaluate.object.enums.EqualsContractViolation;
import lombok.experimental.UtilityClass;

/**
 * The class {@link EqualsCheck} provides algorithms for check the <a href=
 * "https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-">equals
 * contract</a> of an given object.
 */
@UtilityClass
public final class EqualsCheck
{

	/**
	 * Checks the contract condition for reflexivity of the given object, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x}, {@code x.equals(x)}
	 * should return {@code true}.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> reflexivity(T object)
	{
		if (object == null)
		{
			return Optional.of(EqualsContractViolation.REFLEXIVITY_NULL_ARGUMENT);
		}
		if(object.equals(object)) {
			return Optional.empty();
		}
		return Optional.of(EqualsContractViolation.REFLEXIVITY);
	}

	/**
	 * Checks the contract condition for symmetric of the given objects, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and {@code y},
	 * {@code x.equals(y)} should return {@code true} if and only if {@code y.equals(x)} returns
	 * {@code true}.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param anotherObject
	 *            the another object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> symmetric(T object, T anotherObject)
	{
		if (object == null || anotherObject == null)
		{
			return Optional.of(EqualsContractViolation.SYMMETRICITY_NULL_ARGUMENT);
		}
		boolean even = object.equals(anotherObject);
		boolean odd = anotherObject.equals(object);
		return even == odd ? Optional.empty() : Optional.of(EqualsContractViolation.SYMMETRICITY);
	}

	/**
	 * Checks the contract condition for transitivity of the given objects, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>transitive</i>: for any non-null reference values {@code x}, {@code y}, and
	 * {@code z}, if {@code x.equals(y)} returns {@code true} and {@code y.equals(z)} returns
	 * {@code true}, then {@code x.equals(z)} should return {@code true}.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param a
	 *            the object
	 * @param b
	 *            other object
	 * @param c
	 *            another object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> transitivity(T a, T b, T c)
	{
		if (a == null || b == null)
		{
			return Optional.of(EqualsContractViolation.TRANSITIVITY_NULL_ARGUMENT);
		}
		boolean aEqualsB = a.equals(b);
		boolean bEqualsC = b.equals(c);
		boolean aEqualsC = a.equals(c);
		return aEqualsB && bEqualsC && aEqualsC
			? Optional.empty()
			: Optional.of(EqualsContractViolation.TRANSITIVITY);
	}

	/**
	 * Checks the contract condition for non-null condition is given of the given object, that means
	 * according to {@link Object#equals(Object)} that this method should evaluate the following
	 * contract condition:
	 * <ul>
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should return
	 * {@code false}.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)} <br>
	 *
	 * @param <T>
	 *            the generic type
	 *
	 * @param object
	 *            the object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> nonNull(T object)
	{
		if (object == null)
		{
			return Optional.of(EqualsContractViolation.NON_NULL_NULL_ARGUMENT);
		}
		// negate because the valid result is false and if it is valid we want to return true...
		boolean result = !object.equals(null);
		return result ? Optional.empty() : Optional.of(EqualsContractViolation.NON_NULL);
	}

	/**
	 * Checks the contract condition for consistency of the given objects, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and {@code y},
	 * multiple invocations of {@code x.equals(y)} consistently return {@code true} or consistently
	 * return {@code false}, provided no information used in {@code equals} comparisons on the
	 * objects is modified.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)}<br>
	 * <br>
	 * This method calls the same name method with default iterations of 7<br>
	 * <br>
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param anotherObject
	 *            the another object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> consistency(T object, T anotherObject)
	{
		return consistency(object, anotherObject, 7);
	}

	/**
	 * Checks the contract condition for consistency of the given objects, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and {@code y},
	 * multiple invocations of {@code x.equals(y)} consistently return {@code true} or consistently
	 * return {@code false}, provided no information used in {@code equals} comparisons on the
	 * objects is modified.
	 * </ul>
	 *
	 * Note: The upper list entry is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param anotherObject
	 *            the another object
	 * @param iterations
	 *            the iterations of call of equals method.
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> consistency(T object, T anotherObject,
		int iterations)
	{
		if (object == null || anotherObject == null)
		{
			return Optional.of(EqualsContractViolation.CONSISTENCY_NULL_ARGUMENT);
		}
		final boolean initialEqualsResult = object.equals(anotherObject);
		for (int i = 0; i < iterations; i++)
		{
			boolean currentEqualsResult = object.equals(anotherObject);
			if (initialEqualsResult != currentEqualsResult)
			{
				return Optional.of(EqualsContractViolation.CONSISTENCY);
			}
		}
		return Optional.empty();
	}

	/**
	 * Checks the contract conditions for reflexivity and non null, that means according to
	 * {@link Object#equals(Object)} that this method should evaluate the following contract
	 * condition:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x}, {@code x.equals(x)}
	 * should return {@code true}.
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should return
	 * {@code false}.
	 * </ul>
	 *
	 * Note: The upper list entries is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> reflexivityAndNonNull(T object)
	{
		Optional<ContractViolation> evaluated;
		evaluated = reflexivity(object);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = nonNull(object);
		return evaluated;
	}

	/**
	 * Checks the contract conditions for symmetric and consistency of the given objects, that means
	 * according to {@link Object#equals(Object)} that this method should evaluate the following
	 * contract condition:
	 * <ul>
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and {@code y},
	 * {@code x.equals(y)} should return {@code true} if and only if {@code y.equals(x)} returns
	 * {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and {@code y},
	 * multiple invocations of {@code x.equals(y)} consistently return {@code true} or consistently
	 * return {@code false}, provided no information used in {@code equals} comparisons on the
	 * objects is modified.
	 * </ul>
	 *
	 * Note: The upper list entries is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param anotherObject
	 *            the another object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> symmetricAndConsistency(T object, T anotherObject)
	{
		Optional<ContractViolation> evaluated;
		evaluated = symmetric(object, anotherObject);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = consistency(object, anotherObject);
		return evaluated;
	}

	/**
	 * Checks the contract conditions for reflexivity, non null, symmetric and consistency of the
	 * given objects, that means according to {@link Object#equals(Object)} that this method should
	 * evaluate the following contract condition:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x}, {@code x.equals(x)}
	 * should return {@code true}.
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should return
	 * {@code false}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and {@code y},
	 * {@code x.equals(y)} should return {@code true} if and only if {@code y.equals(x)} returns
	 * {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and {@code y},
	 * multiple invocations of {@code x.equals(y)} consistently return {@code true} or consistently
	 * return {@code false}, provided no information used in {@code equals} comparisons on the
	 * objects is modified.
	 * </ul>
	 *
	 * Note: The upper list entries is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param otherObject
	 *            the other object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> reflexivityNonNullSymmetricAndConsistency(
		T object, T otherObject)
	{
		Optional<ContractViolation> evaluated;
		evaluated = reflexivityAndNonNull(object);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = symmetricAndConsistency(object, otherObject);
		return evaluated;
	}

	/**
	 * Checks the contract conditions for reflexivity, non null, symmetric, consistency and
	 * transitivity of the given objects, that means according to {@link Object#equals(Object)} that
	 * this method should evaluate the following contract condition:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x}, {@code x.equals(x)}
	 * should return {@code true}.
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should return
	 * {@code false}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and {@code y},
	 * {@code x.equals(y)} should return {@code true} if and only if {@code y.equals(x)} returns
	 * {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and {@code y},
	 * multiple invocations of {@code x.equals(y)} consistently return {@code true} or consistently
	 * return {@code false}, provided no information used in {@code equals} comparisons on the
	 * objects is modified.
	 * <li>It is <i>transitive</i>: for any non-null reference values {@code x}, {@code y}, and
	 * {@code z}, if {@code x.equals(y)} returns {@code true} and {@code y.equals(z)} returns
	 * {@code true}, then {@code x.equals(z)} should return {@code true}.
	 * </ul>
	 *
	 * Note: The upper list entries is taken from the javadoc from {@link Object#equals(Object)}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param otherObject
	 *            the other object
	 * @param anotherObject
	 *            the another object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> reflexivityNonNullSymmetricConsistencyAndTransitivity(
		T object, T otherObject, T anotherObject)
	{
		Optional<ContractViolation> evaluated;
		evaluated = reflexivityNonNullSymmetricAndConsistency(otherObject, otherObject);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = transitivity(object, otherObject, anotherObject);
		return evaluated;
	}

}
