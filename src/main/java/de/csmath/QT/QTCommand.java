package de.csmath.QT;

/**
 * This class represents a command that describes how a
 * QuickTime file is read.
 * With the help of commands a QTReader can traverse a QuickTime file
 * and can read the needed atoms.
 * @author lpfeiler
 */
public final class QTCommand {

    /**
     * The action to execute.
     */
    private final QTOpCode code;

    /**
     * The type of the atom, on which to perform the action.
     */
    private final int type;

    /**
     * Constructs a QTCommand.
     * @param code the action to execute
     * @param type the type of the atom
     */
    public QTCommand(QTOpCode code,int type) {
        this.code = code;
        this.type = type;
    }

    /**
     * Returns the action to execute.
     * @return the action to execute
     */
    public QTOpCode getCode() {
        return code;
    }

    /**
     * Returns the type of the atom, on which to perform the action.
     * @return the type of the atom
     */
    public int getType() {
        return type;
    }
}
