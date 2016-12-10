package de.csmath.QT;


import java.util.Arrays;
import java.util.Iterator;

/**
 * This class represents an atom of a QuickTime file.
 * @author lpfeiler
 */
public class QTAtom {

    /**
     * The brand 'qt  ' indicates a QuickTime file.
     */
    public final static int QT   = 0x71742020; //'qt  '

    /**
     * The atom type 'ftyp'
     */
    public final static int FTYP = 0x66747970; //'ftyp'

    /**
     * The atom container type 'moov'
     */
    public final static int MOOV = 0x6D6F6F76; //'moov'

    /**
     * The atom type 'mvhd'
     */
    public final static int MVHD = 0x6D766864; //'mvhd'

    /**
     * The atom container type 'trak'
     */
    public final static int TRAK = 0x7472616B; //'trak'

    /**
     * The atom container type 'mdia'
     */
    public final static int MDIA = 0x6D646961; //'mdia'

    /**
     * The atom container type 'minf'
     */
    public final static int MINF = 0x6D696E66; //'minf'

    /**
     * The atom container type 'stbl'
     */
    public final static int STBL = 0x7374626C; //'stbl'

    /**
     * The atom type 'stsd'
     */
    public final static int STSD = 0x73747364; //'stsd'

    /**
     * The video sample desc. extension type 'colr'
     */
    public final static int COLR = 0x636F6C72; //'colr'

    /**
     * The video sample desc. extension type 'avcC'
     */
    public final static int AVCC = 0x61766343; //'avcC'

    /**
     * The size of the atom in the file.
     */
    private final int size;

    /**
     * The type of the atom.
     */
    private final int type;

    /**
     * The contents of the atom (if not further specified).
     */
    private final byte[] contents;

    /**
     * Constructs a QTAtom
     * @param size the size of the atom in the file
     * @param type the type of the atom
     */
    public QTAtom(int size, int type) {
        this.size = size;
        this.type = type;
        this.contents = null;
    }

    /**
     * Constructs a QTAtom
     * @param size the size of the atom in the file
     * @param type the type of the atom
     * @param contents the contents of the atom
     */
    public QTAtom(int size, int type, byte[] contents) {
        this.size = size;
        this.type = type;
        if (contents.length != (size - 8))
            throw new IllegalArgumentException("contents size mismatch");
        this.contents = Arrays.copyOf(contents,contents.length);
    }

    /**
     * Returns the size of the atom in the file.
     * @return the size of the atom in the file
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the type of the atom.
     * @return the type of the atom
     */
    public int getType() {
        return type;
    }

    /**
     * Returns an iterator over the contents of the atom.
     * @return an iterator over the contents of the atom
     */
    public Iterator<Byte> getContentsIterator() {
        return new ContentsIterator();
    }

    /**
     * Returns the type as a string
     * @return the type as a string
     */
    protected String typeAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append((char)(type >> 24));
        sb.append((char)((type >> 16) & 0xFF));
        sb.append((char)((type >> 8) & 0xFF));
        sb.append((char)(type & 0xFF));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(this.getClass().getName() + ": ")
            .append("(")
            .append(getSize())
            .append(", ")
            .append(typeAsString())
            .append(")");

        return sb.toString();
    }

    /**
     * This class is an iterator over the contents of the atom.
     */
    private class ContentsIterator implements Iterator<Byte> {

        /**
         * The current index of the array.
         */
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < contents.length;
        }

        @Override
        public java.lang.Byte next() {
            return contents[index++];
        }
    }
}
