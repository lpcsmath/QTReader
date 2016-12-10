package de.csmath.QT;

import java.util.Collection;
import java.util.Iterator;

/**
 * The FTypeAtom class represents the QuickTime File Type Atom ('ftyp').
 * It holds information about the compatibility of the related file.
 * @author lpfeiler
 */
public final class FTypeAtom extends QTAtom {

    /**
     * Major_Brand identifies the preferred brand and should be
     * set to 'qt  ' for QuickTime movie files.
     */
    private int majBrand = 0;

    /**
     * Minor_Version specifies the file format specification version.
     */
    private int minVersion = 0;

    /**
     * Compatible_Brands holds 32-Bit integers of compatible file formats.
     */
    private int[] compBrands;

    /**
     * This flag indicates whether this file is compatible to the
     * QuickTime file format.
     */
    private boolean isQT = false;

    /**
     * Constructs a File Type Atom.
     * @param size       size of the atom in the file
     * @param type       type of the atom, should be set to 'ftyp'
     * @param majBrand   identifies the preferred brand
     * @param minVersion indicates the file format spec version
     * @param compBrands list of compatible brands
     */
    FTypeAtom(int size, int type, int majBrand, int minVersion, Collection<Integer> compBrands) {
        super(size,type);
        if (type != QTAtom.FTYP)
            throw new IllegalArgumentException("no ftyp type");
        this.majBrand = majBrand;
        this.minVersion = minVersion;
        if (majBrand == QTAtom.QT) isQT = true;
        this.compBrands = new int [(size - 16)>>>2];
        int i=0;
        for (Integer brand : compBrands) {
            if (brand == QTAtom.QT) isQT = true;
            this.compBrands[i++] = brand;
        }
    }

    /**
     * Returns the major brand.
     * @return the major brand
     */
    public int getMajBrand() {
        return majBrand;
    }

    /**
     * Returns the minor version.
     * @return the minor version
     */
    public int getMinVersion() {
        return minVersion;
    }

    /**
     * Returns an iterator over the list of compatible brands.
     * @return an iterator over the list of compatible brands
     */
    public Iterator<Integer> getCompBrands() {
        return new CompBrandsIterator();
    }

    /**
     * Returns true iff this atom indicates a QuickTime file.
     * @return
     */
    public boolean isQT() {
        return isQT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName() + ": ");
        if (isQT) sb.append("QT");
        else sb.append("NoQT");
        sb.append("(")
            .append(getSize())
            .append(", ")
            .append(typeAsString())
            .append(", ")
            .append(String.format("0x%08X", majBrand))
            .append(", ")
            .append(String.format("0x%08X", minVersion))
            .append(", ")
            .append("[");
        for (int i=0; i < compBrands.length; i++) {
            sb.append(String.format("0x%08X", compBrands[i]));
            if ((i + 1) < compBrands.length) sb.append(", ");
        }
        sb.append("])");
        return sb.toString();
    }

    /**
     * This class is an iterator over the list of compatible brands of a
     * FTypeAtom.
     * @author lpfeiler
     */
    private class CompBrandsIterator implements Iterator<Integer> {

        /**
         * The current index of the list.
         */
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < compBrands.length;
        }

        @Override
        public Integer next() {
            return compBrands[index++];
        }
    }

}
