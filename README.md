## Kaffa Mobile - Pre-qualification test (v1.10)

###### This repository contains my solution to the exercises in the technical test for the position of backend developer at Kaffa Mobile.

All the exercises have been developed in the Kotlin language and can be executed via the command line. The solutions I propose are simple and compact, following clean code practices. You only need a Kotlin compiler to create the runnable `.jar` file and the Java Runtime Environment (JRE) to execute it. No third-party libraries have been used.

### How to build and run

#### Pre-requisites

- [Kotlin compiler](https://github.com/JetBrains/kotlin/releases);
- [Java Runtime Environment (JRE)](https://www.java.com/en/download/).

The exercises were tested using Kotlin `2.0.20` and JRE `17.0.12`.

#### Build instructions

1. Compile: `kotlinc ex1/ex1.kt -include-runtime -d ex1/ex1.jar`;
2. Then run: `java -jar ex1/ex1.jar`.

##### Exercise 3

Only [exercise 3](ex3) requires the previous exercise [`ex2`](ex2) package to be imported, and to do so:

1. Compile: `kotlinc ex3/ex3.kt -include-runtime -classpath ex2/ex2.jar -d ex3/ex3.jar`;
2. Then run: `java -cp ex3/ex3.jar:ex2/ex2.jar ex3.Ex3Kt`.

Alternatively, you can also use modern IDEs such as [IntelliJ IDEA](https://www.jetbrains.com/idea/).
For more information, check the [official documentation](https://kotlinlang.org/docs/command-line.html).

### Exercices

1. [Validate CNPJ format and check digits](ex1)
2. [Test if two rectangles intersect](ex2)
3. [Compute the area of intersection between two rectangles](ex3)
4. [Simple Todo List](ex4)
5. [Rest Client - World Clock](ex5)
6. [Rest Server - World Clock](ex6)
7. [Entity Relationship Diagram - Simple Order Manager](ex7)