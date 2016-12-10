package de.csmath.QT;

/**
 * This class builds a ColrAtom from given parameters.
 * @author lpfeiler
 */
public class ColrAtomBuilder extends QTAtomBuilder {

    /**
     * @see ColrAtom#colParamType
     */
    private int colParamType;

    /**
     * @see ColrAtom#primIndex
     */
    private short primIndex;

    /**
     * @see ColrAtom#transFuncIndex
     */
    private short transFuncIndex;

    /**
     * @see ColrAtom#matrixIndex
     */
    private short matrixIndex;

    /**
     * Constructs a ColrAtomBuilder
     * @param size the size of the atom in the file.
     * @param type the type of the ColrAtom, should be set to 'colr'
     */
    public ColrAtomBuilder(int size, int type) {
        super(size,type);
    }

    /**
     * Returns a new ColrAtom.
     * @return a new ColrAtom
     */
    public ColrAtom build() {
        return new ColrAtom(size, type, colParamType,
                primIndex, transFuncIndex, matrixIndex);
    }

    /**
     * Sets the color parameter type.
     * @see ColrAtom#colParamType
     * @param colParamType the color parameter type
     * @return a reference to this object
     */
    public ColrAtomBuilder withColParamType(int colParamType) {
        this.colParamType = colParamType;
        return this;
    }

    /**
     * Sets the primaries index.
     * @see ColrAtom#primIndex
     * @param primIndex the primaries index
     * @return a reference to this object
     */
    public ColrAtomBuilder withPrimIndex(short primIndex) {
        this.primIndex = primIndex;
        return this;
    }

    /**
     * Sets the transfer function index.
     * @see ColrAtom#transFuncIndex
     * @param transFuncIndex the transfer function index
     * @return a reference to this object
     */
    public ColrAtomBuilder withTransFuncIndex(short transFuncIndex) {
        this.transFuncIndex = transFuncIndex;
        return this;
    }

    /**
     * Sets the matrix index.
     * @see ColrAtom#matrixIndex
     * @param matrixIndex the matrix index
     * @return a reference to this object
     */
    public ColrAtomBuilder withMatrixIndex(short matrixIndex) {
        this.matrixIndex = matrixIndex;
        return this;
    }
}
