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

    public static List<Instruction> readBrainFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        List<Instruction> instrTypeList = new ArrayList<>();
        String line;
        do {
            line = br.readLine();

            if(line == null)
                break;
            System.out.println(line);
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
            boolean SenseDir = false;
            int nextWhiteSpace = 0;
            index += 6;

            if (line.substring(index, index + 4).equals("here")) {
                SenseDir = true;
                index += 5;
            } else if (line.substring(index, index + 5).equals("ahead")) {
                SenseDir = true;
                index += 6;
            } else if (line.substring(index, index + 9).equals("leftahead")) {
                SenseDir = true;
                index += 10;
            } else if (line.substring(index, index + 10).equals("rightahead")) {
                SenseDir = true;
                index += 11;
            }
            nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }

            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isAstate2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate2 = true;
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

            if (!(SenseDir && cond && isAstate && isAstate2)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("mark")) {
            boolean isAnum = false;
            index += 5;

            if (line.substring(index, index + 1).equals("0")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("1")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("2")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("3")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("4")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("5")) {
                isAnum = true;
                index += 2;
            }

            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }

            index = nextWhiteSpace++;
            if (!(isAnum && isAstate)) {
                correct = false;
            }


        } else if (line.substring(index, index + 6).equals("unmark")) {
            boolean isAnum = false;
            index += 7;

            if (line.substring(index, index + 1).equals("0")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("1")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("2")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("3")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("4")) {
                isAnum = true;
                index += 2;
            } else if (line.substring(index, index + 1).equals("5")) {
                isAnum = true;
                index += 2;
            }

            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }

            index = nextWhiteSpace + 1;
            if (!(isAnum && isAstate)) {
                correct = false;
            }

        } else if (line.substring(index, index + 6).equals("pickup")) {
            index += 7;
            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isAstate2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate2 = true;
            }
            index = nextWhiteSpace + 1;

            if (!(isAstate && isAstate2)) {
                correct = false;
            }
        } else if (line.substring(index, index + 4).equals("drop")) {
            index += 5;
            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }

            index = nextWhiteSpace + 1;
            if (!(isAstate)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("turn")) {

            index += 5;
            boolean isAdir = false;
            if (line.substring(index, index + 4).equals("left")) {
                isAdir = true;
                index += 5;
            } else if (line.substring(index, index + 5).equals("right")) {
                isAdir = true;
                index += 6;
            }
            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }

            index = nextWhiteSpace++;
            if (!(isAstate && isAdir)) {
                correct = false;
            }

        } else if (line.substring(index, index + 4).equals("move")) {

            index += 5;

            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isAstate2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate2 = true;
            }
            index = nextWhiteSpace + 1;

            if (!(isAstate && isAstate2)) {
                correct = false;
            }
        } else if (line.substring(index, index + 4).equals("flip")) {
            index += 5;

            int nextWhiteSpace = index;
            boolean isAstate = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;
            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isAstate2 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate2 = true;
            }
            nextWhiteSpace++;
            index = nextWhiteSpace;
            boolean isAstate3 = false;
            while (nextWhiteSpace < line.length() && !(line.substring(nextWhiteSpace, nextWhiteSpace + 1).equals(" "))) {
                nextWhiteSpace++;

            }
            if (line.substring(index, nextWhiteSpace).matches("\\d+")) {
                isAstate3 = true;
            }
            index = nextWhiteSpace + 1;

            if (!(isAstate && isAstate2 && isAstate3)) {
                correct = false;
            }
        } else {
            correct = false;
            System.out.println("There is a problem with the brain");
        }

        if (correct) {

            System.out.println("successfully made a brain! :D");

        } else {
            System.out.println("Failed to make a brain :( ");
        }

    }

    //Most hacky parser ever,  10/10 - IGN
    public static Instruction buildInstruction(String line)
    {
        String[] wordList = line.split("\\s");

        Instruction newInstr = new Instruction();

        switch(wordList[0]){
            case "Sense":
                newInstr.instrType = InstructionEnum.Sense;
                newInstr.senseDirection = SenseDirection.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                newInstr.condition = Condition.valueOf(wordList[4]);
                if(newInstr.condition == Condition.Marker)
                    newInstr.condition.markerNum = Integer.parseInt(wordList[5]);
                break;
            case "Mark":
                newInstr.instrType = InstructionEnum.Mark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "Unmark":
                newInstr.instrType = InstructionEnum.Unmark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "PickUp":
                newInstr.instrType = InstructionEnum.Pickup;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "Drop":
                newInstr.instrType = InstructionEnum.Drop;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                break;
            case "Turn":
                newInstr.instrType = InstructionEnum.Turn;
                newInstr.lr = Left_or_Right.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "Move":
                newInstr.instrType = InstructionEnum.Move;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "Flip":
                newInstr.instrType = InstructionEnum.Flip;
                newInstr.n = Integer.parseInt(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                break;

            default:
                newInstr = null;
                break;
        }
        return newInstr;
    }







}