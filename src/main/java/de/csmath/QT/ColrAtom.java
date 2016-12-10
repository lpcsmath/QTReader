package de.csmath.QT;

/**
 * The ColrAtom class represents the QuickTime File Type Atom ('colr').
 * It is an extension of the Video Sample Description of the 'stsd' atom.
 * @author lpfeiler
 */
public final class ColrAtom extends QTAtom {

    /**
     * The color parameter type
     */
    private final int colParamType;

    /**
     * The primaries index
     */
    private final short primIndex;

    /**
     * The transfer function index
     */
    private final short transFuncIndex;

    /**
     * The matrix index
     */
    private final short matrixIndex;

    /**
     * Constructs the ColrAtom.
     * @param size The size of the atom in the file
     * @param type The type of the atom, should be set to 'colr'
     * @param colParamType The color parameter type
     * @param primIndex The primaries index
     * @param transFuncIndex The transfer function index
     * @param matrixIndex The matrix index
     */
    public ColrAtom(int size, int type, int colParamType,
                    short primIndex, short transFuncIndex, short matrixIndex) {
        super(size,type);
        this.colParamType = colParamType;
        this.primIndex = primIndex;
        this.transFuncIndex = transFuncIndex;
        this.matrixIndex = matrixIndex;
    }

    /**
     * Returns the color parameter type.
     * @return The color parameter type
     */
    public int getColParamType() {
        return colParamType;
    }

    /**
     * Returns the primaries index.
     * @return The primaries index
     */
    public short getPrimIndex() {
        return primIndex;
    }

    /**
     * Returns the transfer function index.
     * @return The transfer function index
     */
    public short getTransFuncIndex() {
        return transFuncIndex;
    }

    /**
     * Returns the matrix index.
     * @return The matrix index.
     */
    public short getMatrixIndex() {
        return matrixIndex;
    }
}
