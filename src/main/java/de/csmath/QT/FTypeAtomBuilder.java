package de.csmath.QT;

import java.util.Collection;
import java.util.List;

/**
 * This class builds a FTypeAtom from given parameters.
 * @author lpfeiler
 */
public class FTypeAtomBuilder extends QTAtomBuilder {

    /**
     * @see FTypeAtom#majBrand
     */
    private int majBrand = 0;

    /**
     * @see FTypeAtom#minVersion
     */
    private int minVersion = 0;

    /**
     * @see FTypeAtom#compBrands
     */
    private Collection<Integer> compBrands;

    /**
     * Constructs a FTypeAtomBuilder.
     * @param size the size of the FTypeAtom in the file
     * @param type the type of the atom, should be set to 'ftyp'
     */
    public FTypeAtomBuilder(int size, int type) {
        super(size, type);
    }

    /**
     * Returns a new FTypeAtom.
     * @return a new FTypeAtom
     */
    public FTypeAtom build() {
        return new FTypeAtom(size,type,majBrand,minVersion,compBrands);
    }

    /**
     * Sets the major brand.
     * @see FTypeAtom#majBrand
     * @param majBrand the major brand
     * @return a reference to this object
     */
    public FTypeAtomBuilder withMajBrand(int majBrand) {
        this.majBrand = majBrand;
        return this;
    }

    /**
     * Sets the minor version.
     * @see FTypeAtom#minVersion
     * @param minVersion the minor version
     * @return a reference to this object
     */
    public FTypeAtomBuilder withMinVersion(int minVersion) {
        this.minVersion = minVersion;
        return this;
    }

    /**
     * Sets the compatible brands.
     * @param compBrands a collection of compatible brands
     * @return a reference to this object
     */
    public FTypeAtomBuilder withCompBrands(Collection<Integer> compBrands) {
        this.compBrands = compBrands;
        return this;
    }

}
