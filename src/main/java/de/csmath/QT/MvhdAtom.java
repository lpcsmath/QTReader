package de.csmath.QT;

import java.util.*;

/**
 * The MvhdAtom class represents the QuickTime File Type Atom ('mvhd').
 * It specifies the characteristics of an entire QuickTime movie.
 * @author lpfeiler
 */
public final class MvhdAtom extends QTAtom {

    /**
     * The size of the flag-array.
     */
    public static int FLAGS_SIZE = 3;

    /**
     * The size of the reserved-array.
     */
    public static int RESERVED_SIZE = 10;

    /**
     * The size of the matrix-array.
     */
    public static int MATRIX_SIZE = 9;

    /**
     * The version of this movie header atom.
     */
    private final byte version;

    /**
     * The space for future movie header flags.
     */
    private final byte[] flags = new byte[FLAGS_SIZE];

    /**
     * The date and time when the movie was created.
     */
    private final Date creationTime;

    /**
     * The date and time when the movie was changed.
     */
    private final Date modificationTime;

    /**
     * The number of time units that pass per second
     * in its time coordinate system.
     */
    private final int timeScale;

    /**
     * The duration of the longest trak in time scale units (@see #timeScale).
     */
    private final int duration;

    /**
     * The preferred rate at which to play this movie.
     */
    private final int rate;

    /**
     * The preferred volume of the movie's sound.
     */
    private final short volume;

    /**
     * This space is reserved by Apple.
     */
    private final byte[] reserved = new byte[RESERVED_SIZE];

    /**
     * The mapping of points from one coordinate space into another.
     */
    private final int[] matrix = new int[MATRIX_SIZE];

    /**
     * The time at which the preview begins.
     */
    private final int prevTime;

    /**
     * The duration of the preview in time scale units.
     */
    private final int prevDuration;

    /**
     * The time of the movie poster.
     */
    private final int posterTime;

    /**
     * The start time of the current selection.
     */
    private final int selectTime;

    /**
     * The duration of the current selection in time scale units.
     */
    private final int selectDuration;

    /**
     * The current time position within the movie.
     */
    private final int currTime;

    /**
     * The next track ID indicates the value to use for the next track
     * added to the movie.
     */
    private final int nextTrackId;

    /**
     * Constructs a MvhdAtom.
     * @param size the size of the atom in the file
     * @param type the type of the atom, should be set to 'mvhd'
     * @param version the version of this movie header atom
     * @param flags the future movie header flags
     * @param creationTime the creation time
     * @param modificationTime the modification time
     * @param timeScale the time scale
     * @param duration the duration of the movie
     * @param rate the preferred rate to play the movie
     * @param volume the preferred the volume
     * @param reserved the reserved data
     * @param matrix the point mapping matrix
     * @param prevTime the preview start time
     * @param prevDuration the preview duration
     * @param posterTime the time of the movie poster
     * @param selectTime the time of the selection
     * @param selectDuration the duration of the selection
     * @param currTime the current time position in the movie
     * @param nextTrackId the next track ID to use
     */
    public MvhdAtom(int size, int type, byte version, byte[] flags, int creationTime,
                    int modificationTime, int timeScale, int duration, int rate,
                    short volume, byte[] reserved, int[] matrix, int prevTime,
                    int prevDuration, int posterTime, int selectTime, int selectDuration,
                    int currTime, int nextTrackId) {
        super(size, type);
        if (type != QTAtom.MVHD)
            throw new IllegalArgumentException("no mvhd type");
        this.version = version;
        for (int i=0; i < flags.length && i < this.flags.length; i++) {
            this.flags[i] = flags[i];
        }
        this.creationTime = convertToDate(creationTime);
        this.modificationTime = convertToDate(modificationTime);
        this.timeScale = timeScale;
        this.duration = duration;
        this.rate = rate;
        this.volume = volume;
        for (int i=0; i < reserved.length && i < this.reserved.length; i++) {
            this.reserved[i] = reserved[i];
        }
        for (int i=0; i < matrix.length && i < this.matrix.length; i++) {
            this.matrix[i] = matrix[i];
        }
        this.prevTime = prevTime;
        this.prevDuration = prevDuration;
        this.posterTime = posterTime;
        this.selectTime = selectTime;
        this.selectDuration = selectDuration;
        this.currTime = currTime;
        this.nextTrackId = nextTrackId;
    }

    public MvhdAtom(int size, int type, int creationTime, int timeScale, int duration) {
        this(size,type,(byte)0,new byte[0],creationTime,0,timeScale,duration,
                0,(short)0,new byte[0], new int[0],
                0,0,0,0,0,0,0);
    }

    /**
     * Returns the version of the movie header atom.
     * @return the version of the movie header atom
     */
    public byte getVersion() {
        return version;
    }

    /**
     * Returns the creation date and time of the movie.
     * @return the creation date and time of the movie
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Converts the date/time value of the file (in seconds since 1904-01-01 0:00)
     * into a java.util.Date.
     * @param time date/time value (in seconds since 1904-01-01 0:00)
     * @return the date/time value as java.util.Date
     */
    private Date convertToDate(int time) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        cal.set(1904,0,1,0,0,0);
        cal.set(Calendar.MILLISECOND,0);
        long ct = ((1L << 32) + time) * 1000;
        ct += cal.getTimeInMillis();
        cal.setTimeInMillis(ct);
        //TODO 2 hours off
        return cal.getTime();
    }

    /**
     * Returns the modification time.
     * @return the modification time
     */
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Returns the time scale.
     * @return the time scale
     */
    public int getTimeScale() {
        return timeScale;
    }

    /**
     * Returns the frames per seconds, derived from the time scale.
     * @return the frames per seconds
     */
    public double getFps() {
        return timeScale / 1000.0;
    }

    /**
     * Returns the duration of the movie in time scale units.
     * @return the duration of the movie in time scale units
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the duration of the movie in seconds.
     * @return the duration of the movie in seconds
     */
    public int getDurationSec() {
        return duration / timeScale;
    }

    /**
     * Returns the preferred rate.
     * @return the preferred rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Returns the preferred volume.
     * @return the preferred volume
     */
    public short getVolume() {
        return volume;
    }

    /**
     * Returns an iterator over the matrix.
     * @return an iterator over the matrix
     */
    public Iterator<Integer> getMatrix() {
        return new MatrixIterator();
    }

    /**
     * Returns the start time of the preview.
     * @return the start time of the preview
     */
    public int getPrevTime() {
        return prevTime;
    }

    /**
     * Returns the duration of the preview in time scale units.
     * @return the duration of the preview in time scale units
     */
    public int getPrevDuration() {
        return prevDuration;
    }

    /**
     * Returns the time position of the movie poster.
     * @return the time position of the movie poster
     */
    public int getPosterTime() {
        return posterTime;
    }

    /**
     * Returns the start time of the current selection.
     * @return the start time of the current selection
     */
    public int getSelectTime() {
        return selectTime;
    }

    /**
     * Returns the duration of the current selection.
     * @return the duration of the current selection
     */
    public int getSelectDuration() {
        return selectDuration;
    }

    /**
     * Returns the current time position.
     * @return the current time position
     */
    public int getCurrTime() {
        return currTime;
    }

    /**
     * Returns the next track ID for added track.
     * @return the next track ID for added track
     */
    public int getNextTrackId() {
        return nextTrackId;
    }

    /**
     * Iterator class over the matrix integers.
     */
    private class MatrixIterator implements Iterator<Integer> {

        /**
         * The current index of the iterator.
         */
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < matrix.length;
        }

        @Override
        public Integer next() {
            return matrix[index++];
        }
    }
}
