# QTReader
A library which provides a Reader to read a collection of atoms from a
QuickTime file.

One can right a description to read the atoms of interest.

#### Example:
To read important meta data like the duration and resolution of a video, one
can tell the reader to execute the following steps:

```
step into moov  
read mvhd  
step into trak  
step into mdia  
step into minf  
step into stbl  
read stsd
```

To execute these steps, one compiles these lines into a
list of QTCommands and applies the readStream method on
these commands and an InputStream of the Quicktime file.

```java
FileInputStream fis = new FileInputStream(fileName);
String prog = "step into moov\n"
            + "read mvhd\n"
            + "step into trak\n"
            + "step into mdia\n"
            + "step into minf\n"
            + "step into stbl\n"
            + "read stsd\n";
List<QTCommand> cmds = QTProgCompiler.compile(prog);
QTReader reader = new QTReader();
Collection<QTAtom> atoms = reader.readStream(fis,cmds);
```

The reader then returns a collection of the appropriate atoms.
In this case, there are two atoms (MvhdAtom, StsdAtom)
inside of the returned collection, which provide the needed
information.