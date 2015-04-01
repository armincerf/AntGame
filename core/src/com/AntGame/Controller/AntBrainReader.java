package com.AntGame.Controller;


        import com.AntGame.Model.Helper.Left_or_Right;
        import com.AntGame.Model.Helper.Marker;
        import com.AntGame.Model.Helper.SenseDirection;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Bradley on 31/03/2015.
 */
public class AntBrainReader {

    public static List<Instruction> readBrainFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        List<Instruction> instrList = new ArrayList<>();
        String line;
        do {
            line = br.readLine();
            if(line == null)
                break;

            instrList.add(buildInstruction(line));

        }while(line != null);

        return instrList;
    }

    //Most hacky parser ever,  10/10 - IGN
    public static Instruction buildInstruction(String line)
    {
        String[] wordList = line.split(" ");

        Instruction newInstr;

        switch(wordList[0]){
            case "Sense":
                newInstr = Instruction.Sense;
                newInstr.senseDirection = SenseDirection.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                newInstr.condition = Condition.valueOf(wordList[4]);
                if(newInstr.condition == Condition.Marker)
                    newInstr.condition.markerNum = Integer.parseInt(wordList[5]);
                break;
            case "Mark":
                newInstr = Instruction.Mark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "Unmark":
                newInstr = Instruction.Unmark;
                newInstr.marker = new Marker(Integer.parseInt(wordList[1]));
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "PickUp":
                newInstr = Instruction.Pickup;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "Drop":
                newInstr = Instruction.Drop;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                break;
            case "Turn":
                newInstr = Instruction.Turn;
                newInstr.lr = Left_or_Right.valueOf(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                break;
            case "Move":
                newInstr = Instruction.Move;
                newInstr.state1 = Integer.parseInt(wordList[1]);
                newInstr.state2 = Integer.parseInt(wordList[2]);
                break;
            case "Flip":
                newInstr = Instruction.Flip;
                newInstr.n = Integer.parseInt(wordList[1]);
                newInstr.state1 = Integer.parseInt(wordList[2]);
                newInstr.state2 = Integer.parseInt(wordList[3]);
                break;
            default:
                newInstr = null;
        }
        return newInstr;
    }







}
