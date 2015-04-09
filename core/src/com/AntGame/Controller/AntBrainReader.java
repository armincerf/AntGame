package com.AntGame.Controller;


import com.AntGame.Model.Helper.Left_or_Right;
import com.AntGame.Model.Helper.Marker;
import com.AntGame.Model.Helper.SenseDirection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntBrainReader {

    private static boolean correct = false;
    private static int buffer = 0;

    public static List<Instruction> readBrainFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        List<Instruction> instrTypeList = new ArrayList<>();

        String line;
        do {
            line = br.readLine();

            if(line == null)
                break;
            checkSyntax(line);

            instrTypeList.add(buildInstruction(line));

        } while (line != null && correct);

        return instrTypeList;
    }

    public boolean getCorrect() {
        return correct;
    }

    public static void checkSyntax(String line) {
        line = line.toLowerCase();
        correct = true;
        int index = 0;

        index = 0;
        line = line + "               $";
        if (line.substring(index, index + 5).equals("sense")) {
            boolean senseDirection = false;
            int nextWhiteSpace = 0;
            index += 6;

            if (line.substring(index, index + 4).equals("here")) {
                senseDirection = true;
                index += 5;
            } else if (line.substring(index, index + 5).equals("ahead")) {
                senseDirection = true;
                index += 6;
            } else if (line.substring(index, index + 9).equals("leftahead")) {
                senseDirection = true;
                index += 10;
            } else if (line.substring(index, index + 10).equals("rightahead")) {
                senseDirection = true;
                index += 11;
            }
            nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }

            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isState2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState2 = true;
            }
            boolean cond = false;
            index = nextWhiteSpace + 1;
            if (line.substring(index, index + 6).equals("friend")) {
                cond = true;
                index += 7;
            } else if (line.substring(index, index + 3).equals("foe")) {
                cond = true;
                index += 4;
            } else if (line.substring(index, index + 14).equals("friendwithfood")) {
                cond = true;
                index += 15;
            } else if (line.substring(index, index + 11).equals("foewithfood")) {
                cond = true;
                index += 12;
            } else if (line.substring(index, index + 4).equals("food")) {
                cond = true;
                index += 5;
            } else if (line.substring(index, index + 4).equals("rock")) {
                cond = true;
                index += 5;
            } else if (line.substring(index, index + 8).equals("marker 0")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 8).equals("marker 1")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 8).equals("marker 2")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 8).equals("marker 3")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 8).equals("marker 4")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 8).equals("marker 5")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 9).equals("foemarker")) {
                cond = true;
                index += 10;
            } else if (line.substring(index, index + 4).equals("home")) {
                cond = true;
                index += 5;
            } else if (line.substring(index, index + 7).equals("foehome")) {
                cond = true;
                index += 8;
            }

            if (!(senseDirection && cond && isState && isState2)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("mark")) {
            boolean isDigit = false;
            index += 5;

            if (line.substring(index, index + 1).equals("0")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("1")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("2")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("3")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("4")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("5")) {
                isDigit = true;
                index += 2;
            }

            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }

            index = nextWhiteSpace++;
            if (!(isDigit && isState)) {
                correct = false;
            }


        } else if (line.substring(index, index + 6).equals("unmark")) {
            boolean isDigit = false;
            index += 7;

            if (line.substring(index, index + 1).equals("0")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("1")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("2")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("3")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("4")) {
                isDigit = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("5")) {
                isDigit = true;
                index += 2;
            }

            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }

            index = nextWhiteSpace + 1;
            if (!(isDigit && isState)) {
                correct = false;
            }

        } else if (line.substring(index, index + 6).equals("pickup")) {
            index += 7;
            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isState2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState2 = true;
            }
            index = nextWhiteSpace + 1;

            if (!(isState && isState2)) {
                correct = false;
            }
        } else if (line.substring(index, index + 4).equals("drop")) {
            index += 5;
            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }

            if (!(isState)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("turn")) {

            index += 5;
            boolean isDirection = false;
            if (line.substring(index, index + 4).equals("left")) {
                isDirection = true;
                index += 5;
            } else if (line.substring(index, index + 5).equals("right")) {
                isDirection = true;
                index += 6;
            }
            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }

            index = nextWhiteSpace++;
            if (!(isState && isDirection)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("move")) {

            index += 5;

            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isState2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState2 = true;
            }

            if (!(isState && isState2)) {
                correct = false;
            }
        } else if (line.substring(index, index + 4).equals("flip")) {
            index += 5;

            int nextWhiteSpace = index;
            boolean isState = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isState2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState2 = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isState3 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isState3 = true;
            }

            if (!(isState && isState2 && isState3)) {
                correct = false;
            }
        } else {
            correct = false;
            System.out.println("There is a problem with the brain");
        }



    }

    //Most hacky parser ever,  10/10 - IGN
    public static Instruction buildInstruction(String line)
    {
        String[] wordList = line.toLowerCase().split("\\s");
        System.out.println(line);
        Instruction newInstr = new Instruction();

        switch(wordList[0]){
            case "sense":
                newInstr.instrType = InstructionEnum.Sense;
                newInstr.senseDirection = SenseDirection.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]) + buffer;
                newInstr.state2 = Integer.parseInt(wordList[3]) + buffer;
                newInstr.condition = Condition.valueOf(wordList[4]);
                if(newInstr.condition == Condition.marker)
                    newInstr.condition.markerNum = Integer.parseInt(wordList[5]);
                break;
            case "mark":
                newInstr.instrType = InstructionEnum.Mark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]) + buffer;
                break;
            case "unmark":
                newInstr.instrType = InstructionEnum.Unmark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]) + buffer;
                break;
            case "pickup":
                newInstr.instrType = InstructionEnum.Pickup;
                newInstr.state1 = Integer.parseInt(wordList[1]) + buffer;
                newInstr.state2 = Integer.parseInt(wordList[2]) + buffer;
                break;
            case "drop":
                newInstr.instrType = InstructionEnum.Drop;
                newInstr.state1 = Integer.parseInt(wordList[1]) + buffer;
                break;
            case "turn":
                newInstr.instrType = InstructionEnum.Turn;
                newInstr.lr = Left_or_Right.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]) + buffer;
                break;
            case "move":
                newInstr.instrType = InstructionEnum.Move;
                newInstr.state1 = Integer.parseInt(wordList[1]) + buffer;
                newInstr.state2 = Integer.parseInt(wordList[2]) + buffer;
                break;
            case "flip":
                newInstr.instrType = InstructionEnum.Flip;
                newInstr.n = Integer.parseInt(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]) + buffer;
                newInstr.state2 = Integer.parseInt(wordList[3]) + buffer;
                break;

            default:
                newInstr = null;
                System.out.println("U FUCKED UP");
                break;
        }
        return newInstr;
    }







}