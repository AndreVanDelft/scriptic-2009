# How to compile and run Scriptic applications from Eclipse #

You can compile and run Scriptic applications from Eclipse when you have checked out all sources using Subversion. To do so, it is best to have the [Subclipse](http://subclipse.tigris.org/) plugin for Eclipse.

I assume here you have created a project named Scriptic, with all the sources. How to proceed:

  * In Eclipse, start opening the Run Commands for editing
  * Add a command named "Compile Examples", in project Scriptic.
  * Main class is scriptic.tools.Sawa
  * Specify as arguments: "-o ..\..\bin\ `*`.sawa"
  * For the class path, add the user entries Scriptic (the project), rt.jar (external jar from your local Java installation), and add folder \Scriptic\bin\Examples (using the Advanced... button)

Now you should be able to compile all .sawa sources in Examples. To run your application, do for instance the following:

  * make a command "Run Life" in project Scriptic
  * Main class is Examples.LifeFrame
  * For the class path, add the use entry Scriptic (the project)

That should work.

There should be a better way to compile your sources in Eclipse, though. We only have to find out...