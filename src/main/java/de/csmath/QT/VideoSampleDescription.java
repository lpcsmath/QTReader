package de.csmath.QT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a video sample description.
 * @author lpfeiler
 */
public class VideoSampleDescription extends SampleDescription {

    /**
     * The size of the video sample description, without extensions.
     */
    public static final int SIZE = 86;

    /**
     * The fixed size of the compressor name string in the file.
     */
    public static final int CN_STRING_SIZE = 32;

    /**
     * The version of the video sample description.
     */
    private final short version;

    /**
     * The revision level.
     */
    private final short revLevel;   // must 0

    /**
     * The vendor of the compressor.
     */
    private final int vendor;

    /**
     * The temporal quality.
     */
    private final int tempQual;

    /**
     * The spatial quality.
     */
    private final int spatQual;

    /**
     * The width of the source image in pixel.
     */
    private final short width;

    /**
     * The height of the source image in pixel.
     */
    private final short height;

    /**
     * The horizontal resolution in pixel per inch.
     */
    private final int horzRes;

    /**
     * The vertical resolution in pixel per inch.
     */
    private final int vertRes;

    /**
     * The data size.
     */
    private final int dataSize;

    /**
     * The number of frames in each sample.
     */
    private final short frameCount;

    /**
     * The name of the compressor.
     */
    private final String compName;

    /**
     * The pixel depth of the compressed image.
     */
    private final short depth;

    /**
     * This ID indicates, which color table to use.
     */
    private final short colTableId;

    /**
     * The extensions that attached to this sample description.
     */
    private final Collection<QTAtom> extensions;

    /**
     * Constructs a VideoSampleDescription
     * @param size the size of the atom in the file
     * @param dataFormat the data format of the file
     * @param width the width of the image
     * @param height the height of the image
     * @param compName the name of the compressor
     * @param extensions the attached extensions
     */
    public VideoSampleDescription(int size, int dataFormat, short width, short height,
                                  String compName, Collection<QTAtom> extensions) {
        super(size, dataFormat,new byte[0], (short)0);
        this.version = 0;
        this.revLevel = 0;
        this.vendor = 0;
        this.tempQual = 0;
        this.spatQual = 0;
        this.width = width;
        this.height = height;
        this.horzRes = 0;
        this.vertRes = 0;
        this.dataSize = 0;
        this.frameCount = 0;
        this.compName = compName;
        this.depth = 0;
        this.colTableId = 0;
        this.extensions = new LinkedList<QTAtom>();
        for (QTAtom a : extensions) {
            this.extensions.add(a);
        }
    }

    /**
     * Returns the version of the sample description.
     * @return the version of the sample description
     */
    public short getVersion() {
        return version;
    }

    /**
     * Returns the revision level.
     * @return the revision level
     */
    public short getRevLevel() {
        return revLevel;
    }

    /**
     * Returns the vendor of the compressor.
     * @return the vendor of the compressor
     */
    public int getVendor() {
        return vendor;
    }

    /**
     * Returns the temporal quality.
     * @return the temporal quality
     */
    public int getTempQual() {
        return tempQual;
    }

    /**
     * Returns the spatial quality.
     * @return the spatial quality
     */
    public int getSpatQual() {
        return spatQual;
    }

    /**
     * Returns the width of the source image in pixel.
     * @return the width of the source image in pixel
     */
    public short getWidth() {
        return width;
    }

    /**
     * Returns the height of the source image in pixel.
     * @return the height of the source image in pixel
     */
    public short getHeight() {
        return height;
    }

    /**
     * Returns the name of the compressor.
     * @return the name of the compressor
     */
    public String getCompName() { return compName; }

    /**
     * Returns an iterator over the extensions.
     * @return an iterator over the extensions
     */
    public Iterator<QTAtom> getExtIterator() {
        return extensions.iterator();
    }
}
