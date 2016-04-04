Hey! So listen, the source files are in the yodleintern folder. Main method runs from the jugglersncircuits.java, but the implementation
is spread out among the other .java files

Now to compile, from the same directory this readme is in, run this command from command line

>javac -d bin yodleintern/*.java

for me the full path looks like
C:\Users\Vamsi\Documents\Yodle intern prob>javac -d bin yodleintern/*.java

this will create a folder in /bin called "yodleintern" with all the class files

now, to run the program go into the bin folder

you should see two things:

the "/yodleintern" folder with all the class files
and a file called test2.txt, this is the input file

now from this directory, where you can see test2.txt
run the command 
>java yodleintern.jugglersncircuits

For me the full path looks like
C:\Users\Vamsi\Documents\Yodle intern prob\bin>java yodleintern.jugglersncircuits
This will create a test3.txt next to test2.txt
test3.txt is your output.

I included test2 and test3 in this directory as a backup just incase.