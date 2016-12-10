package de.csmath.QT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides static methods to compile a QuickTime program.
 * It compiles a string into a list of QTCommands.
 * The program language:
 * Prog   ::= Cmd | Cmd '\n' Prog | CommentLine | CommentLine '\n' Prog
 * CommentLine ::= '#' [A..z 0..9]*
 * Cmd    ::= Action Spaces Atom | Action Spaces Atom Spaces CommentLine
 * Spaces ::= ' ' | ' ' Spaces
 * Action ::= 'read' | 'step' Spaces 'into' | 'skip'
 * Atom is the name of a QuickTime atom (made of 4 symbols)
 *
 * Example:
 *   step into moov
 *   read mvhd
 *   skip trak
 */
public class QTProgCompiler {

    /**
     * Compiles a program string into a list of QTCommand objects.
     * @param prog a program string
     * @return a list of QTCommand objects
     */
    public static List<QTCommand> compile(String prog) {
        String[] lines = prog.split("\n");
        return compile(lines);
    }

    /**
     * compiles an array of string commands into a list of QTCommand objects.
     * @param prog an array of string commands
     * @return a list of QTCommand objects
     */
    public static List<QTCommand> compile(String[] prog) {
        List<QTCommand> code = Arrays.stream(prog)
                .map( line -> line.trim() )
                .map( line -> line.toLowerCase() )
                .map( line -> line.split("\\s+") )
                .filter( tokens -> tokens.length > 0 )
                .filter( tokens -> tokens[0].length() > 0 )
                .filter( tokens -> !tokens[0].startsWith("#") )
                .map( tokens -> tokens2Commands(tokens) )
                .collect(Collectors.toList());
        return code;
    }

    /**
     * Converts an array of strings into a QTCommand object.
     * The first strings of the array must be actions. The
     * last string must be the name of an atom.
     * @param tokens a tokenized command
     * @return a QTCommand object
     */
    private static QTCommand tokens2Commands(String[] tokens) {
        QTOpCode opcode = QTOpCode.SKIP;
        int atom = 0;
        switch(tokens[0]) {
            case "read":
                if (!isValidShortCmd(tokens))
                    throw new IllegalArgumentException("syntax error");
                opcode = QTOpCode.READ;
                atom = atomNameToInt(tokens[1]);
                break;
            case "skip":
                if (!isValidShortCmd(tokens))
                    throw new IllegalArgumentException("syntax error");
                opcode = QTOpCode.SKIP;
                atom = atomNameToInt(tokens[1]);
                break;
            case "step":
                if (!isValidLongCmd(tokens))
                    throw new IllegalArgumentException("syntax error");
                opcode = QTOpCode.STEPIN;
                atom = atomNameToInt(tokens[2]);
                break;
            default:
                throw new IllegalArgumentException("syntax error");
        }
        return new QTCommand(opcode, atom);
    }

    /**
     * Converts an atom name into an integer.
     * @param name name of an atom
     * @return the integer of the ASCII codes of the letters
     */
    public static int atomNameToInt(String name) {
        if (name.length() != 4) throw new IllegalArgumentException("no atom name");
        int value = 0;
        for (int i=0; i < 4; i++) {
            value |= (byte) name.charAt(i) << (24 - 8 * i);
        }
        return value;
    }

    /**
     * This predicate is true iff the given command is a valid two word command.
     * @param tokens a tokenized command
     * @return true iff the given command is a valid two word command
     */
    private static boolean isValidShortCmd(String[] tokens) {
        return (tokens.length == 2 || hasTrailingComment(tokens)) && tokens[1].length() == 4;
    }

    /**
     * This predicate is true iff the given command is a valid three word command.
     * @param tokens a tokenized command
     * @return true iff the given command is a valid three word command
     */
    private static boolean isValidLongCmd(String[] tokens) {
        return tokens.length == 3 || hasTrailingComment(tokens) && tokens[2].length() == 4;
    }

    /**
     * This predicate is true iff the command has a trailing comment.
     * @param tokens a tokenized command
     * @return true iff the command has a trailing comment
     */
    private static boolean hasTrailingComment(String[] tokens) {
        return tokens.length > 2 && tokens[2].startsWith("#") ||
                tokens.length > 3 && tokens[3].startsWith("#");
    }
}
