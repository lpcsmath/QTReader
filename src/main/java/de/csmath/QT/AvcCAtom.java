package de.csmath.QT;

import java.util.Iterator;

/**
 * The AvcCAtom class represents the QuickTime File Type Atom ('avcC').
 * It is an extension of the Video Sample Description of the 'stsd' atom.
 * @author lpfeiler
 */
public final class AvcCAtom extends QTAtom {

    /**
     * Constructs an AvcCAtom.
     * @param size size of the atom in the file
     * @param type type of the atom, should be set to 'avcC'
     * @param decConfRecord the byte-Array with the AVC decoder configuration record.
     */
    public AvcCAtom(int size, int type, byte[] decConfRecord) {
        super(size, type, decConfRecord);
    }

    /**
     * Returns a byte iterator of the AVC decoder configuration record.
     * @return a byte iterator of the AVC decoder configuration record
     */
    public Iterator<Byte> getDecConfRecIterator() {
        return getContentsIterator();
    }
}
