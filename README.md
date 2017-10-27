# Overview

The jobject-extensions project provides several extension methods for the java core class Object. The feature extension methods can be provided over the lombok library. 

When programming with java you have offen to clone or copy to compare or to diff and some times you have even merge objects. 
Thats exactly the functionality what this library provides. 

## Key features:

1. very small size
2. extension methods for java core class Object for clone, compare, copy and merge
3. modularizable, import only the functionality you need
4. simple to use

## License

The source code comes under the liberal MIT License, making jobject-extensions great for all types of applications.

## Build status

[![Build Status](https://travis-ci.org/lightblueseas/jobject-extensions.svg?branch=master)](https://travis-ci.org/lightblueseas/jobject-extensions)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jobject-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jobject-extensions)

## javadoc

jobject-clone [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-clone.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-clone)
jobject-compare [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-compare.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-compare)
jobject-copy [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-copy.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-copy)
jobject-core [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-core.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-core)
jobject-diff [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-diff.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-diff)
jobject-merge [![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-merge.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-merge)

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~jobject-extensions~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jobject-extensions:

Than you can add the dependency to your dependencies:		

	<properties>
			...
		<!-- JOBJECT-EXTENSIONS version -->
		<jobject-extensions.version>1.1.0</jobject-extensions.version>		
		<jobject-clone.version>${jobject-extensions.version}</jobject-clone.version>
		<jobject-compare.version>${jobject-extensions.version}</jobject-compare.version>
		<jobject-copy.version>${jobject-extensions.version}</jobject-copy.version>
		<jobject-core.version>${jobject-extensions.version}</jobject-core.version>
		<jobject-diff.version>${jobject-extensions.version}</jobject-diff.version>
		<jobject-merge.version>${jobject-extensions.version}</jobject-merge.version>
			...
	</properties>
		
You can add the following dependencies to your project for use the functionality of jobject-extensions.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jobject-clone:

		<dependencies>
			...
			<!-- JOBJECT-CLONE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-clone</artifactId>
				<version>${jobject-clone.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of jobject-compare:

		<dependencies>
			...
			<!-- JOBJECT-COMPARE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-compare</artifactId>
				<version>${jobject-compare.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import the functionality of jobject-copy:

		<dependencies>
			...
			<!-- JOBJECT-COPY DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-copy</artifactId>
				<version>${jobject-copy.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-core:

		<dependencies>
			...
			<!-- JOBJECT-CORE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-core</artifactId>
				<version>${jobject-core.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-diff:

		<dependencies>
			...
			<!-- JOBJECT-DIFF DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-diff</artifactId>
				<version>${jobject-diff.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-merge:

		<dependencies>
			...
			<!-- JOBJECT-MERGE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge</artifactId>
				<version>${jobject-merge.version}</version>
			</dependency>
			...
		</dependencies>


You can of course import all dependencies of jobject-extensions:

		<dependencies>
			...
			<!-- JOBJECT-EXTENSIONS DEPENDENCIES -->
			<!-- JOBJECT-CLONE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-clone</artifactId>
				<version>${jobject-clone.version}</version>
			</dependency>
			<!-- JOBJECT-COMPARE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-compare</artifactId>
				<version>${jobject-compare.version}</version>
			</dependency>
			<!-- JOBJECT-COPY DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-copy</artifactId>
				<version>${jobject-copy.version}</version>
			</dependency>
			<!-- JOBJECT-CORE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-core</artifactId>
				<version>${jobject-core.version}</version>
			</dependency>
			<!-- JOBJECT-DIFF DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-diff</artifactId>
				<version>${jobject-diff.version}</version>
			</dependency>
			<!-- JOBJECT-MERGE DEPENDENCIES -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge</artifactId>
				<version>${jobject-merge.version}</version>
			</dependency>
			...
		</dependencies>


## Want to Help and improve it? ###

The source code for jobject-extensions are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/jobject-extensions/fork](https://github.com/lightblueseas/jobject-extensions/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/jobject-extensions/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the jobject-extensions developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/jobject-extensions/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fjobject-extensions" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>
