package de.csmath.QT;

import java.util.Iterator;

/**
 * The StsdAtom class represents the QuickTime File Type Atom ('stsd').
 * It holds information about the decoding of samples in the media.
 * @author lpfeiler
 */
public class StsdAtom extends QTAtom {

    /**
     * The number of bytes for future fags
     */
    public  static final int FLAGS_SIZE = 3;

    /**
     * The version of the atom.
     */
    private final byte version;

    /**
     * Flags (for future purpose)
     */
    private final byte[] flags = new byte[3];

    /**
     * The number of sample descriptions
     */
    private final int numEntries;

    /**
     * The table of sample descriptions
     */
    private final SampleDescription[] table;

    /**
     * Constructs a StsdAtom.
     * @param size the size of the atom in the file
     * @param type the type of the atom (should be 'stsd')
     * @param version the version of the atom
     * @param flags the flags
     * @param numEntries the number of sample descriptions
     * @param sds the array of sample descriptions
     */
    public StsdAtom(int size, int type, byte version, byte[] flags,
                    int numEntries, SampleDescription[] sds) {
        super(size, type);
        this.version = version;
        if (numEntries != sds.length)
            throw new IllegalArgumentException("wrong number of entries");
        for (int i=0; i < flags.length && i < this.flags.length; i++) {
            this.flags[i] = flags[i];
        }
        this.numEntries = numEntries;
        table = new SampleDescription[numEntries];
        for (int i=0; i < sds.length && i < table.length; i++) {
            this.table[i] = sds[i];
        }
    }

    /**
     * Returns an iterator over the included sample descriptions.
     * @return an iterator over the included sample descriptions
     */
    public Iterator<SampleDescription> getSDIterator() {
        return new SDIterator();
    }

    /**
     * An iterator class for the sample descriptions.
     */
    private class SDIterator implements Iterator<SampleDescription> {

        /**
         * The current index of the sample description table
         */
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < table.length;
        }

        @Override
        public SampleDescription next() {
            return table[index++];
        }
    }

}
