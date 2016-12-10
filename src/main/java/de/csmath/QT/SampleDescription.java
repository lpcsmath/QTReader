package de.csmath.QT;

/**
 * This class represents a sample description as part of a 'stsd' atom.
 * @author lpfeiler
 */
public class SampleDescription {

    /**
     * The data format 'avc1'.
     */
    public static final int AVC1 = 0x61766331; //'avc1'

    /**
     * The data format 'sowt'
     */
    public static final int SOWT = 0x736F7774; //'sowt'

    /**
     * The number of bytes of the reserved part.
     */
    public static final int RESERVED1_SIZE = 6;

    /**
     * The size of the sample description in the file.
     */
    private int size;

    /**
     * The data format.
     */
    private int dataFormat;

    /**
     * The reserved bytes.
     */
    private byte[] reserved1 = new byte[RESERVED1_SIZE];

    /**
     * The data reference index.
     */
    private short refIndex;

    /**
     * Constructs a sample description.
     * @param size the size of the sample description in the file
     * @param dataFormat the data format of the sample description
     * @param reserved1 the reserved part
     * @param refIndex the data reference index
     */
    public SampleDescription(int size, int dataFormat, byte[] reserved1, short refIndex) {
        this.size = size;
        this.dataFormat = dataFormat;
        for (int i=0; i < reserved1.length && i < this.reserved1.length; i++) {
            this.reserved1[i] = reserved1[i];
        }
        this.refIndex = refIndex;
    }

    /**
     * Returns the size of the sample description in the file.
     * @return the size of the sample description
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the data format of the sample description.
     * @return the data format of the sample description
     */
    public int getDataFormat() {
        return dataFormat;
    }

    /**
     * Returns the data reference index.
     * @return the data reference index
     */
    public short getRefIndex() {
        return refIndex;
    }
}
