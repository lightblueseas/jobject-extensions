package de.alpharogroup.diff.beans;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link GenericChangedAttribute}
 */
public class GenericChangedAttributeTest
{

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
