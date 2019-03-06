# Overview

<div align="center">

[![Build Status](https://travis-ci.org/lightblueseas/jobject-extensions.svg?branch=master)](https://travis-ci.org/lightblueseas/jobject-extensions) 
[![Coverage Status](https://coveralls.io/repos/github/lightblueseas/jobject-extensions/badge.svg?branch=develop)](https://coveralls.io/github/lightblueseas/jobject-extensions?branch=develop) 
[![Open Issues](https://img.shields.io/github/issues/lightblueseas/jobject-extensions.svg?style=flat)](https://github.com/lightblueseas/jobject-extensions/issues) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jobject-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/jobject-extensions)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

The jobject-extensions project provides main operation that you can do with java objects.

When programming with java you have offen to clone or copy to compare or to diff and some times you have even merge objects. For some reasons you have to evaluate bean classes.
Thats exactly the functionality what this library provides. 
There are several extension methods for the java core class Object. The feature extension methods can be provided over the lombok library. 

## Key features:

1. very small size
2. extension methods for java core class Object for clone, compare, copy, diff, check, evaluate and merge
3. modularizable, import only the functionality you need
4. simple to use

## License

The source code comes under the liberal MIT License, making jobject-extensions great for all types of applications.

## javadoc

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-clone.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-clone) jobject-clone

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-compare.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-compare) jobject-compare

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-copy.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-copy) jobject-copy

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-core.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-core) jobject-core

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-diff.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-diff) jobject-diff

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-evaluate.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-evaluate) jobject-evaluate

[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/jobject-merge.svg)](http://www.javadoc.io/doc/de.alpharogroup/jobject-merge) jobject-merge

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~jobject-extensions~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jobject-extensions:

Than you can add the dependency to your dependencies:		

	<properties>
			...
		<!-- JOBJECT-EXTENSIONS versions -->
		<jobject-extensions.version>2.5.1</jobject-extensions.version>		
		<jobject-clone.version>${jobject-extensions.version}</jobject-clone.version>
		<jobject-compare.version>${jobject-extensions.version}</jobject-compare.version>
		<jobject-copy.version>${jobject-extensions.version}</jobject-copy.version>
		<jobject-core.version>${jobject-extensions.version}</jobject-core.version>
		<jobject-diff.version>${jobject-extensions.version}</jobject-diff.version>
		<jobject-evaluate.version>${jobject-extensions.version}</jobject-evaluate.version>
		<jobject-merge.version>${jobject-extensions.version}</jobject-merge.version>
		<jobject-merge-api.version>${jobject-extensions.version}</jobject-merge-api.version>
			...
	</properties>
		
You can add the following dependencies to your project for use the functionality of jobject-extensions.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jobject-clone:

		<dependencies>
			...
			<!-- JOBJECT-CLONE DEPENDENCY -->
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
			<!-- JOBJECT-COMPARE DEPENDENCY -->
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
			<!-- JOBJECT-COPY DEPENDENCY -->
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
			<!-- JOBJECT-CORE DEPENDENCY -->
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
			<!-- JOBJECT-DIFF DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-diff</artifactId>
				<version>${jobject-diff.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-evaluate:

		<dependencies>
			...
			<!-- JOBJECT-EVALUATE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-evaluate</artifactId>
				<version>${jobject-evaluate.version}</version>
			</dependency>
			...
		</dependencies>


Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-merge:

		<dependencies>
			...
			<!-- JOBJECT-MERGE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge</artifactId>
				<version>${jobject-merge.version}</version>
			</dependency>
			...
		</dependencies>
		
Add the following maven dependency to your project `pom.xml` if you want to import only the jobject-merge-api:

		<dependencies>
			...
			<!-- JOBJECT-MERGE-API DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge-api</artifactId>
				<version>${jobject-merge-api.version}</version>
			</dependency>
			...
		</dependencies>


You can of course import all dependencies of jobject-extensions:

		<dependencies>
			...
			<!-- JOBJECT-EXTENSIONS DEPENDENCIES -->
			<!-- JOBJECT-CLONE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-clone</artifactId>
				<version>${jobject-clone.version}</version>
			</dependency>
			<!-- JOBJECT-COMPARE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-compare</artifactId>
				<version>${jobject-compare.version}</version>
			</dependency>
			<!-- JOBJECT-COPY DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-copy</artifactId>
				<version>${jobject-copy.version}</version>
			</dependency>
			<!-- JOBJECT-CORE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-core</artifactId>
				<version>${jobject-core.version}</version>
			</dependency>
			<!-- JOBJECT-DIFF DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-diff</artifactId>
				<version>${jobject-diff.version}</version>
			</dependency>
			<!-- JOBJECT-EVALUATE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-evaluate</artifactId>
				<version>${jobject-evaluate.version}</version>
			</dependency>
			<!-- JOBJECT-MERGE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge</artifactId>
				<version>${jobject-merge.version}</version>
			</dependency>
			<!-- JOBJECT-MERGE-API DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>jobject-merge-api</artifactId>
				<version>${jobject-merge-api.version}</version>
			</dependency>
			...
		</dependencies>

## Semantic Versioning

The versions of jobject-extensions are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

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

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. 
If you like this project, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over flattr: <a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fjobject-extensions" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

# Similar projects

- [cloning](https://github.com/kostaskougios/cloning) deep clone java objects
- [JaVers](https://github.com/javers/javers) JaVers - Object auditing and diff framework for Java

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|
