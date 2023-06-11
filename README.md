# jdeserialize2
![build status](https://github.com/ShayArtzi/jdeserialize2/actions/workflows/build.yml/badge.svg)

This is an attempt to modernize and maintain the [jdeserialize](https://github.com/unsynchronized/jdeserialize) project.

## Original Readme
jdeserialize is a library that interprets Java serialized objects -- the data generated by an ObjectOutputStream.  It also comes with a command-line tool that can generate compilable class declarations, extract block data, and print textual representations of instance values.

It is a full implementation of the Object Serialization Stream Protocol, as described in the Java Object Serialization Specification, chapter 6.  It does not instantiate any classes described in the stream; instead, it builds up an intermediate representation of the types, instances, and values.  Because of this, it can analyze streams without access to the class code that generated them.

It is aimed at reverse engineers working with serialized streams of unknown provenance, as well as developers working with code that uses Java serialization to store data.

All public classes have workable javadoc documentation.  ~~It's online [here](http://wiki.jdeserialize.googlecode.com/hg/javadoc/index.html)~~ (link is dead); the `jdeserialize` class is a good place to start.

To get started using the command-line utility, use:
```
% java -jar jdeserialize.jar -help
```

This code is in the public domain.  It has no dependencies other than the standard Java library.

