package de.csmath.QT;

import java.util.Collection;

/**
 * This class represents a builder of a VideoSampleDescription.
 * @author lpfeiler
 */
public class VideoSampleDescBuilder {

    /**
     * @see SampleDescription#size
     */
    private int size;

    /**
     * @see SampleDescription#dataFormat
     */
    private int dataFormat;

    /**
     * @see SampleDescription#reserved1
     */
    private byte[] reserved1;

    /**
     * @see SampleDescription#refIndex
     */
    private short refIndex;

    /**
     * @see VideoSampleDescription#version
     */
    private short version;

    /**
     * @see VideoSampleDescription#revLevel
     */
    private short revLevel;   // must 0

    /**
     * @see VideoSampleDescription#vendor
     */
    private int vendor;

    /**
     * @see VideoSampleDescription#tempQual
     */
    private int tempQual;

    /**
     * @see VideoSampleDescription#spatQual
     */
    private int spatQual;

    /**
     * @see VideoSampleDescription#width
     */
    private short width;

    /**
     * @see VideoSampleDescription#height
     */
    private short height;

    /**
     * @see VideoSampleDescription#horzRes
     */
    private int horzRes;

    /**
     * @see VideoSampleDescription#vertRes
     */
    private int vertRes;

    /**
     * @see VideoSampleDescription#dataSize
     */
    private int dataSize;

    /**
     * @see VideoSampleDescription#frameCount
     */
    private short frameCount;

    /**
     * @see VideoSampleDescription#compName
     */
    private String compName;

    /**
     * @see VideoSampleDescription#depth
     */
    private short depth;

    /**
     * @see VideoSampleDescription#colTableId
     */
    private short colTableId;

    /**
     * @see VideoSampleDescription#extensions
     */
    private Collection<QTAtom> extensions;

    /**
     * Builds a new VideoSampleDescription object.
     * @return a new VideoSampleDescription object
     */
    public VideoSampleDescription build() {
        VideoSampleDescription sd =
                new VideoSampleDescription(size, dataFormat, width,
                        height, compName, extensions);
        return sd;
    }

    /**
     * Sets the size of the atom in the file
     * @param size the size of the atom in the file
     * @return a reference to this object.
     */
    public VideoSampleDescBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the data format of the sample description.
     * @param dataFormat the data format
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withDataFormat(int dataFormat) {
        this.dataFormat = dataFormat;
        return this;
    }

    /**
     * Sets the bytes of the resereved space.
     * @param reserved1 the bytes of the reserved space
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withReserved1(byte[] reserved1) {
        this.reserved1 = reserved1;
        return this;
    }

    /**
     * Sets the reference index.
     * @param refIndex the reference index
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withRefIndex(short refIndex) {
        this.refIndex = refIndex;
        return this;
    }

    /**
     * Sets the version of the sample description.
     * @param version the version
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withVersion(short version) {
        this.version = version;
        return this;
    }

    /**
     * Sets the revision level.
     * @param revLevel the revision level
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withRevLevel(short revLevel) {
        this.revLevel = revLevel;
        return this;
    }

    /**
     * Sets the vendor of the compressor.
     * @param vendor the vendor of the compressor
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withVendor(int vendor) {
        this.vendor = vendor;
        return this;
    }

    /**
     * Sets the temporal quality.
     * @param tempQual the temporal quality
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withTempQual(int tempQual) {
        this.tempQual = tempQual;
        return this;
    }

    /**
     * Sets the spatial quality.
     * @param spatQual the spatial quality
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withSpatQual(int spatQual) {
        this.spatQual = spatQual;
        return this;
    }

    /**
     * Sets the width of the image in pixel.
     * @param width the width of the image in pixel
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withWidth(short width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the image in pixel.
     * @param height the height of the image in pixel
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withHeight(short height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the horizontal resolution in pixel per inch.
     * @param horzRes the horizontal resolution in pixel per inch
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withHorzRes(int horzRes) {
        this.horzRes = horzRes;
        return this;
    }

    /**
     * Sets the vertical resolution in pixel per inch.
     * @param vertRes the vertical resolution in pixel per inch
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withVertRes(int vertRes) {
        this.vertRes = vertRes;
        return this;
    }

    /**
     * Sets the data size.
     * @param dataSize the data size
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withDataSize(int dataSize) {
        this.dataSize = dataSize;
        return this;
    }

    /**
     * Sets the number of frames in each sample.
     * @param frameCount the number of frames
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withFrameCount(short frameCount) {
        this.frameCount = frameCount;
        return this;
    }

    /**
     * Sets the name of the compressor.
     * @param cnBytes the name of the compressor
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withCompName(byte[] cnBytes) {
        char[] chars = new char[cnBytes[0]];
        for (int i=1; i <= cnBytes[0]; i++) {
            chars[i-1] = (char)cnBytes[i];
        }
        this.compName = new String(chars);
        return this;
    }

    /**
     * Sets the depth.
     * @param depth the depth
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withDepth(short depth) {
        this.depth = depth;
        return this;
    }

    /**
     * Sets the color table ID.
     * @param colTableId the color table ID.
     * @return a reference to this object
     */
    public VideoSampleDescBuilder withColTableId(short colTableId) {
        this.colTableId = colTableId;
        return this;
    }

    /**
     * Sets the attached extensions.
     * @param extensions a collection of extensions
     * @return a reference to this object.
     */
    public VideoSampleDescBuilder withExtensions(Collection<QTAtom> extensions) {
        this.extensions = extensions;
        return this;
    }
}
