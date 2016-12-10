package de.csmath.QT;

/**
 * This class builds a FTypeAtom from given parameters.
 * @author lpfeiler
 */
public class MvhdAtomBuilder extends QTAtomBuilder {

    /**
     * @see MvhdAtom#version
     */
    private byte version;

    /**
     * @see MvhdAtom#flags
     */
    private byte[] flags = new byte[MvhdAtom.FLAGS_SIZE];

    /**
     * @see MvhdAtom#creationTime
     */
    private int creationTime;

    /**
     * @see MvhdAtom#modificationTime
     */
    private int modificationTime;

    /**
     * @see MvhdAtom#timeScale
     */
    private int timeScale;

    /**
     * @see MvhdAtom#duration
     */
    private int duration;

    /**
     * @see MvhdAtom#rate
     */
    private int rate;

    /**
     * @see MvhdAtom#volume
     */
    private short volume;

    /**
     * @see MvhdAtom#reserved
     */
    private byte[] reserved = new byte[MvhdAtom.RESERVED_SIZE];

    /**
     * @see MvhdAtom#matrix
     */
    private int[] matrix = new int[MvhdAtom.MATRIX_SIZE];

    /**
     * @see MvhdAtom#prevTime
     */
    private int prevTime;

    /**
     * @see MvhdAtom#prevDuration
     */
    private int prevDuration;

    /**
     * @see MvhdAtom#posterTime
     */
    private int posterTime;

    /**
     * @see MvhdAtom#selectTime
     */
    private int selectTime;

    /**
     * @see MvhdAtom#selectDuration
     */
    private int selectDuration;

    /**
     * @see MvhdAtom#currTime
     */
    private int currTime;

    /**
     * @see MvhdAtom#nextTrackId
     */
    private int nextTrackId;

    /**
     * Constructs a MvhdAtomBuilder
     * @param size the size of the atom in the file
     * @param type the type of the atom, should be set to 'mvhd'
     */
    public MvhdAtomBuilder(int size, int type) {
        super(size, type);
    }

    /**
     * Sets the version of the atom.
     * @param version the version of the atom
     * @return a reference to this object
     */
    public MvhdAtomBuilder withVersion(byte version) {
        this.version = version;
        return this;
    }

    /**
     * Sets the flags.
     * @param flags the flags
     * @return a reference to this object
     */
    public MvhdAtomBuilder withFlags(byte[] flags) {
        this.flags = flags;
        return this;
    }

    /**
     * Sets the creation time of the movie.
     * @param creationTime the creation time of the movie
     * @return a reference to this object
     */
    public MvhdAtomBuilder withCreationTime(int creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    /**
     * Sets the modification time of the movie.
     * @param modificationTime the last modification time of the movie
     * @return a reference to this object.
     */
    public MvhdAtomBuilder withModificationTime(int modificationTime) {
        this.modificationTime = modificationTime;
        return this;
    }

    /**
     * Sets the time scale.
     * @param timeScale the time scale
     * @return a reference to this object
     */
    public MvhdAtomBuilder withTimeScale(int timeScale) {
        this.timeScale = timeScale;
        return this;
    }

    /**
     * Sets the duration of the movie in time scale units.
     * @param duration the duration of the movie in time scale units
     * @return a reference to the object
     */
    public MvhdAtomBuilder withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Sets the preferred rate.
     * @param rate the preferred rate
     * @return a reference to this object
     */
    public MvhdAtomBuilder withRate(int rate) {
        this.rate = rate;
        return this;
    }

    /**
     * Sets the preferred volume.
     * @param volume the preferred volume
     * @return a reference to this object
     */
    public MvhdAtomBuilder withVolume(short volume) {
        this.volume = volume;
        return this;
    }

    /**
     * Sets the reserved bytes.
     * @param reserved the reserved bytes
     * @return a reference to this object
     */
    public MvhdAtomBuilder withReserved(byte[] reserved) {
        this.reserved = reserved;
        return this;
    }

    /**
     * Sets the matrix.
     * @param matrix the matrix
     * @return a reference to this object
     */
    public MvhdAtomBuilder withMatrix(int[] matrix) {
        this.matrix = matrix;
        return this;
    }

    /**
     * Sets the start time of the preview.
     * @param prevTime the start time of the preview
     * @return a reference to this object
     */
    public MvhdAtomBuilder withPrevTime(int prevTime) {
        this.prevTime = prevTime;
        return this;
    }

    /**
     * Sets the duration of the preview.
     * @param prevDuration the duration of the preview
     * @return a reference to this object
     */
    public MvhdAtomBuilder withPrevDuration(int prevDuration) {
        this.prevDuration = prevDuration;
        return this;
    }

    /**
     * Sets the time value of the poster.
     * @param posterTime the time value of the poster
     * @return a reference to this object
     */
    public MvhdAtomBuilder withPosterTime(int posterTime) {
        this.posterTime = posterTime;
        return this;
    }

    /**
     * Sets the start time of the current selection.
     * @param selectTime the start time of the current selection
     * @return a reference to this object
     */
    public MvhdAtomBuilder withSelectTime(int selectTime) {
        this.selectTime = selectTime;
        return this;
    }

    /**
     * Sets the duration of the current selection.
     * @param selectDuration the duration of the current selection
     * @return a reference to this object
     */
    public MvhdAtomBuilder withSelectDuration(int selectDuration) {
        this.selectDuration = selectDuration;
        return this;
    }

    /**
     * Sets the current time position.
     * @param currTime the current time position
     * @return a reference to this object
     */
    public MvhdAtomBuilder withCurrTime(int currTime) {
        this.currTime = currTime;
        return this;
    }

    /**
     * Sets the next track ID value.
     * @param nextTrackId the next track ID
     * @return a reference to this object
     */
    public MvhdAtomBuilder withNextTrackId(int nextTrackId) {
        this.nextTrackId = nextTrackId;
        return this;
    }

    /**
     * Creates a new MvhdAtom.
     * @return a new MvhdAtom
     */
    public MvhdAtom build() {
        return new MvhdAtom(size, type, creationTime, timeScale, duration);
    }
}
