package de.csmath.QT;

import junit.framework.TestCase;

import java.util.List;

import static de.csmath.QT.QTProgCompiler.*;

/**
 * Created by lpfeiler on 04.12.2016.
 */
public class TestQTProgCompiler extends TestCase {

    public TestQTProgCompiler(String name) {
        super(name);
    }

    public void testAtomToName() {

        int val = atomNameToInt("moov");
        assertEquals(QTAtom.MOOV, val);
    }

    public void testCompileOneReadLine() {
        String line = "read mvhd";
        List<QTCommand> cmds = compile(line);
        assertEquals(1,cmds.size());
        QTCommand cmd = cmds.get(0);
        assertEquals(QTOpCode.READ, cmd.getCode());
        assertEquals(QTAtom.MVHD, cmd.getType());


        line = "   read    mvhd   ";
        cmds = compile(line);
        assertEquals(1,cmds.size());
        cmd = cmds.get(0);
        assertEquals(QTOpCode.READ, cmd.getCode());
        assertEquals(QTAtom.MVHD, cmd.getType());
    }

    public void testCompileOneStepLine() {
        String line = "step into trak";
        List<QTCommand> cmds = compile(line);
        assertEquals(1,cmds.size());
        QTCommand cmd = cmds.get(0);
        assertEquals(QTOpCode.STEPIN, cmd.getCode());
        assertEquals(QTAtom.TRAK, cmd.getType());


        line = "   step   into    trak   ";
        cmds = compile(line);
        assertEquals(1,cmds.size());
        cmd = cmds.get(0);
        assertEquals(QTOpCode.STEPIN, cmd.getCode());
        assertEquals(QTAtom.TRAK, cmd.getType());
    }

    public void testCompileOneSkipLine() {
        String line = "skip mdia";
        List<QTCommand> cmds = compile(line);
        assertEquals(1,cmds.size());
        QTCommand cmd = cmds.get(0);
        assertEquals(QTOpCode.SKIP, cmd.getCode());
        assertEquals(QTAtom.MDIA, cmd.getType());


        line = "   skip    mdia   ";
        cmds = compile(line);
        assertEquals(1,cmds.size());
        cmd = cmds.get(0);
        assertEquals(QTOpCode.SKIP, cmd.getCode());
        assertEquals(QTAtom.MDIA, cmd.getType());
    }

    public void testCompileTwoLines() {
        String line = "skip mdia\nread stsd";
        List<QTCommand> cmds = compile(line);
        assertEquals(2,cmds.size());
        QTCommand cmd1 = cmds.get(0);
        QTCommand cmd2 = cmds.get(1);
        assertEquals(QTOpCode.SKIP, cmd1.getCode());
        assertEquals(QTAtom.MDIA, cmd1.getType());
        assertEquals(QTOpCode.READ, cmd2.getCode());
        assertEquals(QTAtom.STSD, cmd2.getType());

        line = "\n\nskip mdia\r\n\r\nread stsd\n\n";
        cmds = compile(line);
        assertEquals(2,cmds.size());
        cmd1 = cmds.get(0);
        cmd2 = cmds.get(1);
        assertEquals(QTOpCode.SKIP, cmd1.getCode());
        assertEquals(QTAtom.MDIA, cmd1.getType());
        assertEquals(QTOpCode.READ, cmd2.getCode());
        assertEquals(QTAtom.STSD, cmd2.getType());
    }

    public void testCompileWithComments() {
        String line = "#skip the media atom\nskip mdia\n#read this\nread stsd #this one\n#end";
        List<QTCommand> cmds = compile(line);
        assertEquals(2,cmds.size());
        QTCommand cmd1 = cmds.get(0);
        QTCommand cmd2 = cmds.get(1);
        assertEquals(QTOpCode.SKIP, cmd1.getCode());
        assertEquals(QTAtom.MDIA, cmd1.getType());
        assertEquals(QTOpCode.READ, cmd2.getCode());
        assertEquals(QTAtom.STSD, cmd2.getType());
    }
}
