package de.alpharogroup.merge.object;


import java.lang.reflect.InvocationTargetException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.date.DateDecorator;
import de.alpharogroup.date.SqlTimestampDecorator;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import lombok.experimental.ExtensionMethod;


/**
 * The class {@link MergeObjectExtensionsTest}.
 */
@ExtensionMethod(MergeObjectExtensions.class)
public class MergeObjectExtensionsTest
{

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testMerge() throws InvocationTargetException, IllegalAccessException
	{

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("About what...").nickname("beast").build();

		final Employee with = Employee.builder().person(person).id("23").build();

		Employee mergeInObject = Employee.builder().build();
		mergeInObject.merge(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

		mergeInObject = Employee.builder().id("22").person(Person.builder().build()).build();
		mergeInObject.merge(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

	}

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(enabled = true)
	public void testMergeOrCopyQuietly() throws InvocationTargetException, IllegalAccessException
	{
		final DateDecorator dateDecorator = DateDecorator.builder().date(CreateDateExtensions.now())
			.build();

		final SqlTimestampDecorator timestampDecorator = SqlTimestampDecorator.builder().build();

		timestampDecorator.mergeOrCopyQuietly(dateDecorator);

		AssertJUnit.assertTrue("Time should be equal.",
			timestampDecorator.getDate().getTime() == dateDecorator.getDate().getTime());
	}


	/**
	 * Test method for {@link MergeObjectExtensions#mergeQuietly(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testMergeQuietly()
	{

		final Person person = Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
			.about("About what...").nickname("beast").build();

		final Employee with = Employee.builder().person(person).id("23").build();

		Employee mergeInObject = Employee.builder().build();
		mergeInObject.mergeQuietly(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

		mergeInObject = Employee.builder().id("22").person(Person.builder().build()).build();
		mergeInObject.mergeQuietly(with);

		AssertJUnit.assertTrue("", mergeInObject.getId().equals("23"));
		AssertJUnit.assertTrue("", mergeInObject.getPerson().equals(person));

	}

	/**
	 * Test method for {@link MergeObjectExtensions#merge(Object, Object)}.
	 *
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testMergeThrowIllegalArgumentException()
		throws InvocationTargetException, IllegalAccessException
	{
		final DateDecorator dateDecorator = DateDecorator.builder().date(CreateDateExtensions.now())
			.build();

		final SqlTimestampDecorator timestampDecorator = SqlTimestampDecorator.builder().build();

		timestampDecorator.merge(dateDecorator);
	}

}

