package com.AntGame.Controller;


import com.AntGame.Model.Helper.Left_or_Right;
import com.AntGame.Model.Helper.Marker;
import com.AntGame.Model.Helper.SenseDirection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AntBrainReader {

    private static boolean correct = false;
    final static String pattern = "(Turn (Right|Left) \\d{1,4})|(Flip [1-9][0-9]* \\d{1,4} \\d{1,4})|" +
            "(Sense (Here|Ahead|LeftAhead|RightAhead) \\d{1,4} \\d{1,4} (Friend(WithFood)?|Foe(WithFood|Marker|Home)?|Food|Rock|Marker [0-5]|Home))|" +
            "(Mark [0-5] \\d{1,4})|" +
            "(Unmark [0-5] \\d{1,4})|" +
            "(PickUp \\d{1,4} \\d{1,4})|" +
            "(Drop \\d{1,4})|" +
            "(Move \\d{1,4} \\d{1,4})";


    /**
     * Reads in a file and returns a list of instructions
     *
     * @param filename file to be read
     * @return instruction list
     * @throws IOException
     */
    public static List<Instruction> readBrainFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        List<Instruction> instrTypeList = new ArrayList<>();

        String line;
        do {
            line = br.readLine();

            if(line == null)
                break;
            line = line.replaceAll("( *;.*)", ""); //removes any comments in the brain file
            syntaxCheck(line);
            instrTypeList.add(buildInstruction(line));

        } while (line != null && correct);

        return instrTypeList;
    }

    /**
     * checks the ant brain is correct or not
     * call the method to get results
     * @return correct as a boolean value
     */
    public boolean getCorrect() {
        return correct;
    }


    public static void syntaxCheck(String line) {
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        Matcher m = r.matcher(line);
        boolean matches = m.matches();
        if (!matches) {
            System.out.println("parse failed");
                correct = false;
            System.out.println(line);
            }
        correct = true;
        }



    //Most hacky parser ever,  10/10 - IGN

    /**
     * takes the checkSyntax output and constructs a instruction list for the brain
     * @param line as checked output from checkSyntax and being added to the word list
     * @return newInstr Instruction list with parsed instruction commands, else return the error message
     */
    public static Instruction buildInstruction(String line)
    {
        String[] wordList = line.toLowerCase().split("\\s");
        Instruction newInstr = new Instruction();

        switch(wordList[0]){
            case "sense":
                newInstr.instrType = InstructionEnum.Sense;
                newInstr.senseDirection = SenseDirection.valueOf(wordList[1]);

                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                newInstr.condition = Condition.valueOf(wordList[4]);
                if(newInstr.condition == Condition.marker)
                    newInstr.marker = new Marker(Integer.parseInt(wordList[5]));
                break;
            case "mark":
                newInstr.instrType = InstructionEnum.Mark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "unmark":
                newInstr.instrType = InstructionEnum.Unmark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "pickup":
                newInstr.instrType = InstructionEnum.Pickup;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "drop":
                newInstr.instrType = InstructionEnum.Drop;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                break;
            case "turn":
                newInstr.instrType = InstructionEnum.Turn;
                newInstr.lr = Left_or_Right.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "move":
                newInstr.instrType = InstructionEnum.Move;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "flip":
                newInstr.instrType = InstructionEnum.Flip;
                newInstr.n = Integer.parseInt(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                break;

            default:
                newInstr = null;
                System.out.println("command not correct " + wordList[0]);

                break;
        }
        return newInstr;
    }







}