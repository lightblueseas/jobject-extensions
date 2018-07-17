package de.alpharogroup.diff.beans;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.api.ContractViolation;
import de.alpharogroup.evaluate.object.checkers.EqualsHashCodeAndToStringCheck;

/**
 * The unit test class for the class {@link GenericChangedAttribute}
 */
public class GenericChangedAttributeTest
{

	/**
	 * Test method for {@link GenericChangedAttribute} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		GenericChangedAttribute<String, String> model = new GenericChangedAttribute<>();
		assertNotNull(model);
		model = new GenericChangedAttribute<>("foo", "name", "value");
		assertNotNull(model);
		model = GenericChangedAttribute.<String, String> builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link GenericChangedAttribute#equals(Object)} , {@link GenericChangedAttribute#hashCode()} and
	 * {@link GenericChangedAttribute#toString()}
	 *
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		Optional<ContractViolation> expected;
		Optional<ContractViolation> actual;
		actual = EqualsHashCodeAndToStringCheck
			.equalsHashcodeAndToString(GenericChangedAttribute.class);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link GenericChangedAttribute} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(GenericChangedAttribute.class);
	}

}
