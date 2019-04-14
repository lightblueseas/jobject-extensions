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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Function;

import de.alpharogroup.clone.object.CloneObjectExtensions;
import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.enums.EqualsHashcodeContractViolation;
import de.alpharogroup.evaluate.object.enums.ToStringContractViolation;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import lombok.experimental.UtilityClass;

/**
 * The class {@link EqualsHashCodeAndToStringCheck} is a combination of all checks.
 */
@UtilityClass
public final class EqualsHashCodeAndToStringCheck
{

	/**
	 * Checks all the contract conditions for the methods {@link Object#equals(Object)} and
	 * {@link Object#hashCode()}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param first
	 *            the first object
	 * @param second
	 *            the second object that have to be uneqal to the first object
	 * @param third
	 *            the third object have to be equal to first object and fourth object
	 * @param fourth
	 *            the fourth object have to be equal to first object and third object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> equalsAndHashcode(final T first, final T second,
		final T third, final T fourth)
	{
		Optional<ContractViolation> evaluated;
		if (first == null)
		{
			return Optional.of(EqualsHashcodeContractViolation.FIRST_ARG_NULL);
		}
		if (first.equals(second))
		{
			return Optional.of(EqualsHashcodeContractViolation.FIRST_AND_SECOND_EQUAL);
		}
		if (!first.equals(third))
		{
			return Optional.of(EqualsHashcodeContractViolation.FIRST_AND_THIRD_UNEQUAL);
		}
		evaluated = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(first, second);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = EqualsCheck.reflexivityNonNullSymmetricConsistencyAndTransitivity(first, third,
			fourth);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		return hashcodeCheck(first, second, fourth);
	}

	/**
	 * Checks all the contract conditions for the method {@link Object#hashCode()}
	 *
	 * @param <T>
	 *            the generic type
	 * @param first
	 *            the first object
	 * @param second
	 *            the second object that have to be uneqal to the first object
	 * @param fourth
	 *            the fourth object have to be equal to first object and third object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> hashcodeCheck(final T first, final T second,
		final T fourth)
	{
		Optional<ContractViolation> evaluated;
		evaluated = HashcodeCheck.equality(first, fourth);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = HashcodeCheck.unequality(first, second);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = HashcodeCheck.consistency(first);
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
	 * Evaluate equality of hash code from the given objects that should be equal(if not an
	 * {@link IllegalArgumentException} will be thrown), that means according to
	 * {@link Object#hashCode()} that this method should evaluate the following contract condition:
	 * <ul>
	 * <li>If two objects are equal according to the {@code equals(Object)} method, then calling the
	 * {@code hashCode} method on each of the two objects must produce the same integer result.
	 * </ul>
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
	public static <T> Optional<ContractViolation> equalsAndHashcodeEquality(final T object,
		final T otherObject)
	{
		Optional<ContractViolation> evaluated;
		evaluated = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(object, otherObject);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = HashcodeCheck.equality(object, otherObject);
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
	 * Evaluate unequality of hash code from the given objects that should be unequal(if not an
	 * {@link IllegalArgumentException} will be thrown), that means according to
	 * {@link Object#hashCode()} that this method should evaluate the following contract condition:
	 * <ul>
	 * <li>It is <em>not</em> required that if two objects are unequal according to the
	 * {@link java.lang.Object#equals(java.lang.Object)} method, then calling the {@code hashCode}
	 * method on each of the two objects must produce distinct integer results. However, the
	 * programmer should be aware that producing distinct integer results for unequal objects may
	 * improve the performance of hash tables.
	 * </ul>
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
	public static <T> Optional<ContractViolation> equalsAndHashcodeUnequality(final T object,
		final T otherObject)
	{
		Optional<ContractViolation> evaluated;
		evaluated = EqualsCheck.reflexivityNonNullSymmetricAndConsistency(object, otherObject);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = HashcodeCheck.unequality(object, otherObject);
		return evaluated;
	}

	/**
	 * Checks all the contract conditions for the methods {@link Object#equals(Object)},
	 * {@link Object#hashCode()} and {@link Object#toString()} from the given {@link Class}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the class
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 */
	public static <T> Optional<ContractViolation> equalsHashcodeAndToString(Class<T> cls)
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
		InstantiationException, IOException, ClassNotFoundException
	{
		Function<Class<T>, T> function = new EnhancedRandomBuilder().build()::nextObject;
		return equalsHashcodeAndToString(cls, function);
	}

	/**
	 * Checks all the contract conditions for the methods {@link Object#equals(Object)},
	 * {@link Object#hashCode()} and {@link Object#toString()} from the given {@link Class}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the class
	 * @param function
	 *            the function that can create random objects
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 *
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws ClassNotFoundException
	 *             occurs if a given class cannot be located by the specified class loader
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<ContractViolation> equalsHashcodeAndToString(Class<T> cls,
		Function<Class<T>, T> function) throws NoSuchMethodException, IllegalAccessException,
		InvocationTargetException, InstantiationException, IOException, ClassNotFoundException
	{
		if (cls == null)
		{
			return Optional.of(ToStringContractViolation.CLASS_NULL_ARGUMENT);
		}
		final T first = function.apply(cls);
		final T second = function.apply(cls);
		final T third = (T)CloneObjectExtensions.cloneObject(first);
		final T fourth = (T)CloneObjectExtensions.cloneObject(third);

		return EqualsHashCodeAndToStringCheck.equalsHashcodeAndToString(first, second, third,
			fourth);
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
	 * Evaluate consistency of the given object, that means according to {@link Object#hashCode()}
	 * that this method should evaluate the following contract condition:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during an execution of a Java
	 * application, the {@code hashCode} method must consistently return the same integer, provided
	 * no information used in {@code equals} comparisons on the object is modified. This integer
	 * need not remain consistent from one execution of an application to another execution of the
	 * same application.
	 * </ul>
	 *
	 * Note: The upper list is taken from the javadoc from {@link Object#hashCode()}
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return an empty {@link Optional} if no violation occurred or an {@link Optional} with the
	 *         specific violation type
	 */
	public static <T> Optional<ContractViolation> equalsHashcodeAndToString(final T object)
	{
		Optional<ContractViolation> evaluated;
		evaluated = EqualsCheck.reflexivityAndNonNull(object);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = HashcodeCheck.consistency(object);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		evaluated = ToStringCheck.consistency(object);
		return evaluated;
	}

	/**
	 * Checks the all the contract conditions for the methods {@link Object#equals(Object)},
	 * {@link Object#hashCode()} and {@link Object#toString()}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param first
	 *            the first object
	 * @param second
	 *            the second object that have to be uneqal to the first object
	 * @param third
	 *            the third object have to be equal to first object and fourth object
	 * @param fourth
	 *            the fourth object have to be equal to first object and third object
	 * @return true, if all contract conditions for the methods {@link Object#equals(Object)},
	 *         {@link Object#hashCode()} and {@link Object#toString()} is given otherwise false
	 */
	public static <T> Optional<ContractViolation> equalsHashcodeAndToString(final T first,
		final T second, final T third, final T fourth)
	{
		Optional<ContractViolation> evaluated;
		evaluated = equalsAndHashcode(first, second, third, fourth);
		if (evaluated.isPresent())
		{
			return evaluated;
		}
		return ToStringCheck.evaluateAndConsistency(first);
	}

}
