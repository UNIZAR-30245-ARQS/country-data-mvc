# Model-View in Java Swing example
Course: Arquitecturas Software (30245) (Software Architecture), Universidad Zaragoza.
[From the degree in Computer Science, specialization in Software Engineering, EINA, UNIZAR](http://titulaciones.unizar.es/asignaturas/30245/index14.html).

## Description
This is a Java Swing program designed to be a bare-bones example of the Model-View and [Model-View-Presenter with Passive View](http://martinfowler.com/eaaDev/PassiveScreen.html) design patterns. It does not have any external dependency besides Java 1.7, and does not use any Model-View framework. 

It is self-contained, and uses a simple text file as data repository in order to focus on the MV patterns.  When launched, it searches for a specific text file in your temp directory. If it does not find it, it creates one, populates it with some initial data (countries and populations) and uses that as the data repository for the application (if it finds it, it uses it). This allows us to have a self-contained application where users can create, modify and delete data and make this changes permanent between runs (until the temp directory is cleaned).

# Use
Clone this repository in your computer:
`$ git clone https://github.com/UNIZAR-30245-ARQS/country-data-mvc.git`

The repository is configured to use [Gradle](http://www.gradle.org/docs/current/userguide/userguide.html) to manage builds and dependencies. 

## Console
Go to the directory you want to try and check that there is a file named `build.gradle`. Run `$ ./gradlew tasks` (gradlew.bat if you are in Windows). If everything is fine, this will download a specific version of Gradle to your computer (only the first time), and will show the available Gradle tasks in the build. 

As there are two versions of the application (one that uses Model-View, and the other that uses Model-View-Presenter with Passive View) I have configured gradle.build to allow to launch both. The generic command is `$ ./gradlew -PmainClass=MAINCLASS run`

The available main classes are:

- application.modelview.MVApplicationLauncher
- application.mvp.MVPApplicationLauncher

## Eclipse
Install the plugin named "Gradle Integration for Eclipse" that corresponds to your Eclipse version from its marketplace. Then go to File > Import..., choose Gradle Project, put as root folder the directory you want (one with a build.gradle file) from the project, click on Build model, choose the one that is shown and click on Finish. This imports the project to Eclipse so you can run it from there.  

If dependencies are not automatically met, right click on the name of the projecto in the Package Explorer and choose Gradle > Refresh All.
