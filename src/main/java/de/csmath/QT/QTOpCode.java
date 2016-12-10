package de.csmath.QT;

/**
 * The action values of a QTCommand.
 * @see QTCommand
 */
public enum QTOpCode {

    /**
     * Read an atom.
     */
    READ,

    /**
     * Step into an atom container.
     */
    STEPIN,

    /**
     * Skip an atom.
     */
    SKIP

}
