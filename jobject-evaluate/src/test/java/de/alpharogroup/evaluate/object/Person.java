package de.alpharogroup.evaluate.object;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link Person} is a class intended for use in unit tests.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person
{

	/** The about. */
	String about;

	/** The married flag. */
	Boolean married;

	/** The name. */
	String name;

	/** The nickname. */
	String nickname;

}