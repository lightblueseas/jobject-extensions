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
package de.alpharogroup.evaluate.object.evaluators;

import de.alpharogroup.evaluate.object.checkers.EqualsCheck;
import lombok.experimental.UtilityClass;

/**
 * The class {@link EqualsEvaluator} provides algorithms for evaluate the <a href=
 * "https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-">equals
 * contract</a> of an given object.
 */
@UtilityClass
public final class EqualsEvaluator
{

	/**
	 * Evaluates the contract condition for reflexivity of the given object, that means according to
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
	 * @return true, if reflexivity contract condition is given otherwise false
	 */
	public static <T> boolean evaluateReflexivity(T object)
	{
		return !EqualsCheck.reflexivity(object).isPresent();
	}

	/**
	 * Evaluates the contract condition for symmetric of the given objects, that means according to
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
	 * @return true, if symmetric contract condition is given otherwise false
	 */
	public static <T> boolean evaluateSymmetric(T object, T anotherObject)
	{
		return !EqualsCheck.symmetric(object, anotherObject).isPresent();
	}

	/**
	 * Evaluates the contract condition for transitivity of the given objects, that means according
	 * to {@link Object#equals(Object)} that this method should evaluate the following contract
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
	 * @return true, if transitivity contract condition is given otherwise false
	 */
	public static <T> boolean evaluateTransitivity(T a, T b, T c)
	{
		return !EqualsCheck.transitivity(a, b, c).isPresent();
	}

	/**
	 * Evaluates the contract condition for non-null condition is given of the given object, that
	 * means according to {@link Object#equals(Object)} that this method should evaluate the
	 * following contract condition:
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
	 * @return true, if non-null contract condition is given otherwise false
	 */
	public static <T> boolean evaluateNonNull(T object)
	{
		return !EqualsCheck.nonNull(object).isPresent();
	}

	/**
	 * Evaluates the contract condition for consistency of the given objects, that means according
	 * to {@link Object#equals(Object)} that this method should evaluate the following contract
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
	 * @return true, if consistency contract condition is given otherwise false
	 */
	public static <T> boolean evaluateConsistency(T object, T anotherObject)
	{
		return !EqualsCheck.consistency(object, anotherObject).isPresent();
	}

	/**
	 * Evaluates the contract condition for consistency of the given objects, that means according
	 * to {@link Object#equals(Object)} that this method should evaluate the following contract
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
	 * @return true, if consistency contract condition is given otherwise false
	 */
	public static <T> boolean evaluateConsistency(T object, T anotherObject, int iterations)
	{
		return !EqualsCheck.consistency(object, anotherObject, iterations).isPresent();
	}

	/**
	 * Evaluates the contract conditions for reflexivity and non null, that means according to
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
	 * @return true, if reflexivity and non-null contract conditions is given otherwise false
	 */
	public static <T> boolean evaluateReflexivityAndNonNull(T object)
	{
		return !EqualsCheck.reflexivityAndNonNull(object).isPresent();
	}

	/**
	 * Evaluates the contract conditions for symmetric and consistency of the given objects, that
	 * means according to {@link Object#equals(Object)} that this method should evaluate the
	 * following contract condition:
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
	 * @return true, if symmetric and consistency contract conditions is given otherwise false
	 */
	public static <T> boolean evaluateSymmetricAndConsistency(T object, T anotherObject)
	{
		return !EqualsCheck.symmetricAndConsistency(object, anotherObject).isPresent();
	}

	/**
	 * Evaluates the contract conditions for reflexivity, non null, symmetric and consistency of the
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
	 * @return true, if reflexivity, non null, symmetric and consistency contract conditions is
	 *         given otherwise false
	 */
	public static <T> boolean evaluateReflexivityNonNullSymmetricAndConsistency(T object,
		T otherObject)
	{
		return !EqualsCheck.reflexivityNonNullSymmetricAndConsistency(object, otherObject)
			.isPresent();
	}

	/**
	 * Evaluates the contract conditions for reflexivity, non null, symmetric, consistency and
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
	 * @return true, if reflexivity, non null, symmetric, consistency and transitivity contract
	 *         conditions is given otherwise false
	 */
	public static <T> boolean evaluateReflexivityNonNullSymmetricConsistencyAndTransitivity(
		T object, T otherObject, T anotherObject)
	{
		return !EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(object,
			otherObject, anotherObject).isPresent();
	}

}
