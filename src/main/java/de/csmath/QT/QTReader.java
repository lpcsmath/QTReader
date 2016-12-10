package de.csmath.QT;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class represents a QuickTime File Reader.
 * @author lpfeiler
 */
public class QTReader {

    /**
     * This method reads a QuickTime file from an InputStream and collects
     * atoms according to the given list of QTCommands.
     * @param is the InputStream to read the file from
     * @param commands the list of QTCommands
     * @return a collection of collected atoms
     * @throws IOException
     */
    public Collection<QTAtom> readStream(InputStream is, List<QTCommand> commands) throws IOException {

        Collection<QTAtom> atoms = new LinkedList<>();

        int size = readInt(is);
        int type = readInt(is);
        if (type != QTAtom.FTYP) throw new IOException("not a QT-File");
        QTAtom atom = readFType(size, type, is);

        for (QTCommand cmd : commands) {
            size = readInt(is);
            type = readInt(is);

            while (type != cmd.getType()) {
                is.skip(size - 8);
                size = readInt(is);
                type = readInt(is);
            }

            switch (cmd.getCode()) {
                case READ:
                    switch (type) {
                        case QTAtom.MVHD:
                            atom = readMvhd(size, type, is);
                            break;
                        case QTAtom.STSD:
                            atom = readStsd(size, type, is);
                            break;
                        default:
                            atom = readAtom(size, type, is);
                    }
                    atoms.add(atom);
                    break;
                case STEPIN:
                    break;
                case SKIP:
                    is.skip(size - 8);
                    break;
            }

        }
        return atoms;
    }

    /**
     * Reads a QuickTime atom from the given InputStream.
     * @param size the size of the atom in the file
     * @param type the type of the atom
     * @param is the InputStream object
     * @return a QTAtom object
     * @throws IOException
     */
    private QTAtom readAtom(int size, int type, InputStream is) throws IOException {
        byte[] contents = readBytes(is, size - 8);
        return new QTAtom(size, type, contents);
    }

    /**
     * Reads a QuickTime atom of type 'ftyp' from the given InputStream.
     * @param size the size of the atom in the file
     * @param type the type of the atom (should be 'ftyp')
     * @param is the InputStream object
     * @return a FTypeAtom object
     * @throws IOException
     */
    private FTypeAtom readFType(int size, int type, InputStream is) throws IOException {
        FTypeAtomBuilder fb = new FTypeAtomBuilder(size,type)
                .withMajBrand(readInt(is))
                .withMinVersion(readInt(is));
        int numCompBrands = (size - 16) >>> 2;
        List<Integer> compBrands = new ArrayList<>(numCompBrands);
        for (int i=0; i < numCompBrands; i++) {
            compBrands.add(readInt(is));
        }
        fb.withCompBrands(compBrands);
        return fb.build();
    }

    /**
     * Reads a QuickTime atom of type 'mvhd' from the given InputStream.
     * @param size the size of the atom in the file
     * @param type the type of the atom (should be 'mvhd')
     * @param is the InputStream object
     * @return a MvhdAtom object
     * @throws IOException
     */
    private MvhdAtom readMvhd(int size, int type, InputStream is) throws IOException {
        MvhdAtomBuilder mb = new MvhdAtomBuilder(size,type)
                .withVersion((byte)is.read())
                .withFlags(readBytes(is, MvhdAtom.FLAGS_SIZE))
                .withCreationTime(readInt(is))
                .withModificationTime(readInt(is))
                .withTimeScale(readInt(is))
                .withDuration(readInt(is))
                .withRate(readInt(is))
                .withVolume(readShort(is))
                .withReserved(readBytes(is, MvhdAtom.RESERVED_SIZE))
                .withMatrix(readInts(is, MvhdAtom.MATRIX_SIZE))
                .withPrevTime(readInt(is))
                .withPrevDuration(readInt(is))
                .withPosterTime(readInt(is))
                .withSelectTime(readInt(is))
                .withSelectDuration(readInt(is))
                .withCurrTime(readInt(is))
                .withNextTrackId(readInt(is));
        return mb.build();
    }

    /**
     * Reads a QuickTime atom of type 'stsd' from the given InputStream.
     * @param size the size of the atom in the file
     * @param type the type of the atom (should be 'stsd')
     * @param is the InputStream object
     * @return a StsdAtom object
     * @throws IOException
     */
    private StsdAtom readStsd(int size, int type, InputStream is) throws IOException {
        StsdAtomBuilder sab = new StsdAtomBuilder(size, type)
                .withVersion((byte)is.read())
                .withFlags(readBytes(is, StsdAtom.FLAGS_SIZE))
                .withNumEntries(readInt(is));
        SampleDescription[] table = new SampleDescription[sab.getNumEntries()];
        for (int i=0; i < sab.getNumEntries(); i++) {
            table[i] = readSampleDesc(is);
        }
        return sab.withTable(table).build();
    }

    /**
     * Reads a SampleDescription from the given InputStream.
     * @param is the InputStream object
     * @return a SampleDescription object
     * @throws IOException
     */
    private SampleDescription readSampleDesc(InputStream is) throws IOException {
        int size = readInt(is);
        int dataFormat = readInt(is);
        SampleDescription sd = null;
        switch (dataFormat) {
            case SampleDescription.AVC1:
                sd = readVideoSampleDesc(is, size, dataFormat);
                break;
            case SampleDescription.SOWT:
            default:
                is.skip(size - 8);
        }
        return sd;
    }

    /**
     * Reads a video sample description from the given InputStream.
     * @param is the InputStream object
     * @param size the size of the video sample description
     * @param dataFormat the data format of the video sample description
     * @return a VideoSampleDescription object
     * @throws IOException
     */
    private VideoSampleDescription readVideoSampleDesc(InputStream is,
                                                       int size, int dataFormat) throws IOException {
        VideoSampleDescBuilder vsdb = new VideoSampleDescBuilder();
        vsdb.withSize(size)
                .withDataFormat(dataFormat)
                .withReserved1(readBytes(is, SampleDescription.RESERVED1_SIZE))
                .withRefIndex(readShort(is))
                .withVersion(readShort(is))
                .withRevLevel(readShort(is))
                .withVendor(readInt(is))
                .withTempQual(readInt(is))
                .withSpatQual(readInt(is))
                .withWidth(readShort(is))
                .withHeight(readShort(is))
                .withHorzRes(readInt(is))
                .withVertRes(readInt(is))
                .withDataSize(readInt(is))
                .withFrameCount(readShort(is))
                .withCompName(readBytes(is, VideoSampleDescription.CN_STRING_SIZE))
                .withDepth(readShort(is))
                .withColTableId(readShort(is));
        size -= VideoSampleDescription.SIZE;
        vsdb.withExtensions(readVsdExtensions(is, size));
        return vsdb.build();
    }

    /**
     * Reads a collection of extensions of a video sample description from the
     * given InputStream.
     * @param is the InputStream object
     * @param extSize the total size of all extensions of the video sample description
     * @return a collection of extensions as QTAtom objects
     * @throws IOException
     */
    private Collection<QTAtom> readVsdExtensions(InputStream is,
                                                 int extSize) throws IOException {
        Collection<QTAtom> extensions = new ArrayList<>();
        while (extSize > 0) {
            int size = readInt(is);
            int type = readInt(is);
            QTAtom a = null;
            if (type == QTAtom.COLR) {
                a = new ColrAtomBuilder(size,type)
                        .withColParamType(readInt(is))
                        .withPrimIndex(readShort(is))
                        .withTransFuncIndex(readShort(is))
                        .withMatrixIndex(readShort(is))
                        .build();

            } else if (type == QTAtom.AVCC) {
                a = new AvcCAtom(size,type,readBytes(is,size - 8));
            } else {
                a = new QTAtom(size,type,readBytes(is,size - 8));
            }
            extensions.add(a);
            extSize -= size;
        }
        return extensions;
    }

    /**
     * Reads a byte array from the given InputStream.
     * @param is the InputStream object
     * @param size the number of bytes to read
     * @return a byte array
     * @throws IOException
     */
    private byte[] readBytes(InputStream is, int size) throws IOException {
        byte[] buf = new byte[size];
        is.read(buf);
        return buf;
    }

    /**
     * Reads an array of 32-bit integers from the given InputStream.
     * @param is the InputStream object
     * @param size the number of integers to read
     * @return an integer array
     * @throws IOException
     */
    private int[] readInts(InputStream is, int size) throws IOException {
        int[] buf = new int[size];
        for (int i=0; i < size; i++) {
            buf[i] = readInt(is);
        }
        return buf;
    }

    /**
     * Reads a 32-bit integer from the given InputStream.
     * @param is the InputStream object
     * @return a 32-bit integer
     * @throws IOException
     */
    private int readInt(InputStream is) throws IOException {
        return (int) readInteger(is, 4);
    }

    /**
     * Reads a 16-bit integer from the given InputStream.
     * @param is the InputStream object
     * @return a 16-bit integer
     * @throws IOException
     */
    private short readShort(InputStream is) throws IOException {
        return (short)readInteger(is,2);
    }

    /**
     * Reads an integer of 1,2,4 or 8 bytes from the given InputStream.
     * @param is the InputStream object
     * @param bytes the number of bytes, which represent the integer
     * @return a 64-bit integer
     * @throws IOException
     */
    private long readInteger(InputStream is, int bytes) throws IOException {
        long value = 0;
        int c;
        for (int count = 0;(c = is.read()) != -1 && count < (bytes - 1); count ++) {
            value <<= 8;
            value |= c;
        }
        if (c == -1) throw new IOException("Premature end of file");
        value <<= 8;
        return value | c;
    }
}
