package de.csmath.QT;

/**
 * This class provides a skeletal implementation of a builder
 * of QuickTime atoms.
 * @author lpfeiler
 */
public abstract class QTAtomBuilder {

    /**
     * @see QTAtom#size
     */
    protected int size;

    /**
     * @see QTAtom#type
     */
    protected int type;

    /**
     * Constructs a QTAtomBuilder
     * @param size the size of the atom in the file
     * @param type the type of the atom
     */
    public QTAtomBuilder(int size, int type) {
        this.size = size;
        this.type = type;
    }

    /**
     * Builds a new QTAtom.
     * @return a new QTAtom
     */
    public abstract QTAtom build();

}
