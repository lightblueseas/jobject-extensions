## Change log
----------------------

Version 1.10.0-SNAPSHOT
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
