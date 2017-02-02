package de.csmath.QT;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static de.csmath.QT.QTProgCompiler.*;

/**
 * Created by lpfeiler on 04.12.2016.
 */
public class TestQTReader extends TestCase {

    public TestQTReader(String name) {
        super(name);
    }

    public void testReadAtom() {
        QTReader reader = new QTReader();
        String filename =
                "src/test/resources/XT210339.MOV";


        try(FileInputStream fis = new FileInputStream(filename)) {
            String prog = "step into moov\n"
                        + "read mvhd";
            List<QTCommand> cmds = QTProgCompiler.compile(prog);
            Collection<QTAtom> atoms = reader.readStream(fis, cmds);

            //One MvhdAtom
            assertEquals(1, atoms.size());
            QTAtom a = atoms.iterator().next();
            assertEquals(QTAtom.MVHD, a.getType());
            assertEquals(true,a instanceof MvhdAtom);

            MvhdAtom mvhd = (MvhdAtom) a;
            assertEquals(24.0, mvhd.getFps());
            System.out.println(mvhd.getCreationTime());
            System.out.println(mvhd.getModificationTime());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testReadMetaData() {
        QTReader reader = new QTReader();
        String filename =
                "src/test/resources/XT210339.MOV";

        try(FileInputStream fis = new FileInputStream(filename)) {
            String prog = "step into moov\n"
                        + "read mvhd\n"
                        + "step into trak\n"
                        + "step into mdia\n"
                        + "step into minf\n"
                        + "step into stbl\n"
                        + "read stsd\n"
                        + "step into trak\n"
                        + "step into mdia\n"
                        + "step into minf\n"
                        + "step into stbl\n"
                        + "read stsd";
            List<QTCommand> cmds = QTProgCompiler.compile(prog);
            Collection<QTAtom> atoms = reader.readStream(fis, cmds);

            //QTReader could read all 3 atoms
            assertEquals(3, atoms.size());
            Iterator<QTAtom> it = atoms.iterator();
            QTAtom mvhd = it.next();

            //First atom is an mvhd atom
            assertEquals(QTAtom.MVHD, mvhd.getType());
            StsdAtom stsd = (StsdAtom) it.next();

            //Second atom is an stsd atom
            assertEquals(QTAtom.STSD, stsd.getType());
            Iterator<SampleDescription> sdit = stsd.getSDIterator();

            //stsd atom has at least 1 sample description
            assertEquals(true,sdit.hasNext());
            SampleDescription sd = sdit.next();

            //stsd atom has exactly one sample description
            assertEquals(false, sdit.hasNext());

            //Sample description is an h.264 video sample description
            assertEquals(SampleDescription.AVC1, sd.getDataFormat());
            assertEquals(true, sd instanceof VideoSampleDescription);

            VideoSampleDescription vsd = (VideoSampleDescription) sd;
            Iterator<QTAtom> ex = vsd.getExtIterator();

            //The video sample description has at least 1 extension
            assertEquals(true, ex.hasNext());

            QTAtom ea = ex.next();

            //The first extension is a colr atom
            assertEquals(QTAtom.COLR, ea.getType());
            assertEquals(true, ea instanceof ColrAtom);

            //The sample description has another extension
            assertEquals(true, ex.hasNext());

            ea = ex.next();

            //The second extension is an avcC atom
            assertEquals(QTAtom.AVCC, ea.getType());

            stsd = (StsdAtom) it.next();

            //There is a second stsd atom
            assertEquals(QTAtom.STSD, stsd.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}