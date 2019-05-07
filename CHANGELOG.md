## Change log
----------------------

Version 3.1-SNAPSHOT
-------------

CHANGED:

- moved jobject-core to its own project
- update of test-objects dependency version to 5.1
- new method for copy properties over reflection
- removed unused dependency commons-beanutils in project jobject-clone

Version 3
-------------

ADDED:

- new methods for get the declared fields of a class with an optional list of fields that shell be ignored
- new method for copy an array over reflection

CHANGED:

- moved jobject-compare to its own project
- moved jobect-evaluate to its own project
- moved jobject-diff to its own project

Version 2.6
-------------

ADDED:

- new methods for copy object with reflection
- new dependency objenesis for instantiate object from class objects
- new method to create new instances of a given class object

CHANGED:

- moved jobject-merge-api to its own project
- tagged class SilentEqualsHashCodeAndToStringEvaluator as deprecated

Version 2.5.1
-------------

ADDED:

- new generic class GenericSummarizer created for merge items 

CHANGED:

- update of parent version to 4.7
- update of silly-collections version to 5.1
- update of test-objects dependency version to 5.0.1
- replaced last test dependendy from Slf4j with java util logging

Version 2.5
-------------

CHANGED:

- removed deprecated classes
- removed cloning dependency from project jobject-clone
- removed log4j-to-slf4j dependency from project jobject-compare and jobject-copy

Version 2.4
-------------

CHANGED:

- update of vintage-time version to 5.1
- update of silly-collections version to 4.35.1

Version 2.3.2
-------------

CHANGED:

- update of parent version to 4.4
- added explicit dependency to projects
- removed dependency test-objects from project jobject-evaluate

Version 2.3
-------------

CHANGED:

- evaluator classes delegate now to the corresponding checker class
- moved merge interfaces to own project jobject-merge-api

Version 2.2
-------------

CHANGED:

- unit tests extended for improve of code coverage to 100%
- removed deprecated methods

Version 2.1
-------------

CHANGED:

- update the configuration files to log4j2 version
- unit tests extended for improve of code coverage to 99%
- tagged unused methods as deprecated

Version 2
-------------

ADDED:

- new method in classes EqualsHashCodeAndToStringEvaluator and SilentEqualsHashCodeAndToStringEvaluator with new function parameter for create random objects
- new method in class ReflectionExtensions created for get the value of an object over reflection 

CHANGED:

- update of parent version to 4.2
- update of silly-collections version to 4.35

Version 1.12
-------------

ADDED:

- new deploy scripts for module projects jobject-diff and jobject-evaluate
- new checker classes for objects created with a declarative enum return type if a violation occurs

CHANGED:

- update of parent version to 4
- update of test-objects dependency version from 4.26 to 4.28 
- update of vintage-time dependency version from 4.11.0 to 4.12 
- remove of experimental lombok annotation ExtensionMethod
- unit tests extended for improve code coverage

Version 1.11
-------------

ADDED:

- new dependency jobject-evaluate in dependency-management section
- new section 'Semantic Versioning' in README.md file

CHANGED:

- update of parent version to 3.11
- removed unneeded .0 at the end of version
- update of test-objects dependency version from 4.24.0 to 4.26 
- update of cloning dependency version from 1.9.9 to 1.9.10 
- changed order of cloning object, first BeanUtils and the Cloner

Version 1.10.0
-------------

ADDED:

- new dependency of uk.com.robust-it:cloning in version 1.9.9
- new clone method for cloneable objects
- new methods for clone silently 
- new copyProperties method with single object
- new methods for copy silently

CHANGED:

- removed deprecated method closeOutputStream

Version 1.9.1
-------------

ADDED:

- new project created for evaluate java objects
- new method setFieldValue in ReflectionExtensions for set value over class
- new methods for copy and clone created

CHANGED:

- update of parent version to 3.9.0

Version 1.8.0
-------------

ADDED:
 
- ObjectExtensions now supports method isNotDefaultValue
- added new meanbean dependency for better unit testing of beans
- moved conveniences quietly methods from CloneObjectExtensions and CopyObjectExtensions to its own class

CHANGED:

- SortOrderComparator is not abstract and provides factory methods
- GenericChangedAttribute and SerializedChangedAttributeResult provides now a NoArgs Constructor

Version 1.7.0
-------------

ADDED:
 
- provide package.html for the javadoc of packages
- provide LICENSE file to root directory

CHANGED:

- unit tests extended for improve code coverage to 86%

Version 1.6.0
-------------

ADDED:
 
- Donation buttons extended for paypal and bitcoin
- new validation class Argument created

CHANGED:

- removed deprecated classes
- removed deprecated methods
- unit tests extended for improve code coverage
- default value methods removed typed arguments
- excluded commons-collections from commons-beanutils

Version 1.5.0
-------------

ADDED:

- code coverage added
- continuous integration added through travis ci

CHANGED:

- update of parent version
- update of documentation of README.md 
- update of test-objects dependency versions
- update of launch script
- removed silly-collections dependency
- unit tests improved
 

Version 1.4.0
-------------

CHANGED:

- moved relevant classes for check objects to jobject-core project
- javadoc extended and improved

Version 1.3.0
-------------

ADDED:

- moved relevant classes for compare objects from jcommons-lang to this project

CHANGED:

- unit tests improved for better readability
- replaced AssertJUnit with static import

Version 1.2.0
-------------

ADDED:

- cleaned up and moved missing unit tests from jcommons-lang

CHANGED:

- moved interfaces to appropriate package

Version 1.1.0
-------------

ADDED:

- moved interfaces for merge from jcommons-lang

CHANGED:

- update of documentation of README.md 
- javadoc extended and improved

Version 1.0.0
-------------

ADDED:

- initial version
- moved all relevant classes from project jcommons-lang
- initial version of README.md
- new launch scripts created


Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
