package de.csmath.QT;

/**
 * This class builds a StsdAtom from given parameters.
 * @author lpfeiler
 */
public class StsdAtomBuilder extends QTAtomBuilder {

    /**
     * @see StsdAtom#version
     */
    private byte version;

    /**
     * @see StsdAtom#flags
     */
    private byte[] flags = new byte[3];

    /**
     * @see StsdAtom#numEntries
     */
    private int numEntries;

    /**
     * @see StsdAtom#table
     */
    private SampleDescription[] table;

    /**
     * Constructs a StsdAtomBuilder
     * @param size the size of the atom in the file
     * @param type the type of the atom
     */
    public StsdAtomBuilder(int size, int type) {
        super(size,type);
    }

    /**
     * Builds a new StsdAtom.
     * @return a new StsdAtom
     */
    public StsdAtom build() {
        return new StsdAtom(size, type, version, flags, numEntries, table);
    }

    /**
     * Sets the version of the atom.
     * @param version the version of the atom
     * @return a reference of this objec
     */
    public StsdAtomBuilder withVersion(byte version) {
        this.version = version;
        return this;
    }

    /**
     * Sets the flags.
     * @param flags the flags
     * @return a reference to this object
     */
    public StsdAtomBuilder withFlags(byte[] flags) {
        this.flags = flags;
        return this;
    }

    /**
     * Sets the number of sample descriptions.
     * @param numEntries the number of sample descriptions
     * @return a reference to this object
     */
    public StsdAtomBuilder withNumEntries(int numEntries) {
        this.numEntries = numEntries;
        return this;
    }

    /**
     * Returns the number of sample descriptions.
     * @return the number of sample descriptions
     */
    public int getNumEntries() {
        return numEntries;
    }

    /**
     * Sets the array of sample descriptions.
     * @param table the array of sample descriptions
     * @return a reference to this object
     */
    public StsdAtomBuilder withTable(SampleDescription[] table) {
        this.table = table;
        return this;
    }
}
