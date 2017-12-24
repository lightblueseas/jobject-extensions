package de.alpharogroup.clone.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link ClonableObject} is a class intended for use in unit tests that represents a
 * clonable object and implements {@link Cloneable}.
 * @deprecated use instead same name class from next release of test-objects.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Deprecated
public class ClonableObject implements Cloneable
{

	/** The name. */
	private String name;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		ClonableObject clone = new ClonableObject();
		clone.setName(this.name);
		return clone;
	}

}
