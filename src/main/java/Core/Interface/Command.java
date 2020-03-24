package Core.Interface;

import java.util.ArrayList;

public abstract class Command {
    private static final String COMMAND_NOT_FOUND_MESSAGE="Unknown command, type \"list\" for commands";
    private static ArrayList<Command> commandLinks=new ArrayList();
    private static String[] parsedCommand;
    private final String name;
    private final String[] argVector;
    private final int[] subArgCount;
    private final int[][] incompatibleArgs; // Pairs of indexes of incompatible args EXAMPLE: {{0,1},{1,2}}
    private final String definition;
    private final String argDescription;
    private final String helpMessage;
    private final String errorMessage;
    public Command(String name,String[] argVector,int[] subArgCount, int[][] incompatibleArgs,String definition,String argDescription){
        this.name=name;
        this.argVector=argVector;
        this.subArgCount=subArgCount;
        this.incompatibleArgs=incompatibleArgs;
        this.definition=definition;
        this.argDescription=argDescription;
        helpMessage=definition+argDescription;
        errorMessage="Wrong set of arguments\n" + helpMessage;
        commandLinks.add(this);
    }
    private String call(){
        String ret="";
        ArgMatrix argMatrix;
        try {
            argMatrix = getArgMatrix(parsedCommand, argVector, subArgCount);
            if(argMatrix==null || !checkIncompatibleArgs(argMatrix.argument_matches))return errorMessage;
            String logicAnswer=logic(argMatrix);
            if(logicAnswer==null)return errorMessage;
            ret+=logicAnswer;
            if (argMatrix.argument_matches[0]) {
                ret+=helpMessage;
            }
        }
        catch(Exception e){return errorMessage;}
        return ret;
    }
    protected abstract String logic(ArgMatrix argMatrix);
    private static String[] separateArguments(String command){
        return command.trim().replaceAll("  "," ").split(" ");
    }

    private String getName(){
        return name;
    }
    public static String ExecuteLine(String line){
        parsedCommand=separateArguments(line);
        if(parsedCommand.length==1 && parsedCommand[0].equals("list"))return getList();
        for(Command c: commandLinks){
            if(parsedCommand[0].equals(c.getName())){
                return c.call();
            }
        }
        return COMMAND_NOT_FOUND_MESSAGE;
    }
    private ArgMatrix getArgMatrix(String[] commandVector,String[] argVector,int[] sub_arg_count)throws IndexOutOfBoundsException{
        boolean[] argMatch=new boolean[argVector.length];
        boolean argMatched;
        String[][] subArgMatrix=new String[argVector.length][];
        for(int i=1;i<commandVector.length;i++){
            argMatched=false;
            for(int k=0;k<argVector.length;k++){
                if(commandVector[i].equals(argVector[k])){
                    if(argMatch[k])return null;
                    argMatched=true;
                    argMatch[k]=true;
                    if(sub_arg_count[k]>0){
                        subArgMatrix[k]=new String[sub_arg_count[k]];
                        for(int m=0;m<sub_arg_count[k];m++){
                            i++;
                            if(commandVector[i].charAt(0)=='/')return null;
                            subArgMatrix[k][m]=commandVector[i];
                        }
                    }
                }
            }
            if(!argMatched)return null;
        }
        return new ArgMatrix(subArgMatrix,argMatch);
    }
    private boolean checkIncompatibleArgs(boolean[] argMatches){
        for(int i=0;i<argMatches.length;i++){
            if(argMatches[i]){
                for(int k=0;k<argMatches.length;k++){
                    if(argMatches[k] && i!=k){
                        for(int[] pair:incompatibleArgs){
                            if(pair[0]==i && pair[1]==k || pair[0]==k && pair[1]==i)return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private static String getList(){
        String ret="";
        for(Command command:commandLinks){
            ret=ret.concat(command.definition.replaceAll("\n","")).concat("\n");
        }
        return ret;
    }
    protected class ArgMatrix{
        public String[][] subarguments;
        public boolean[] argument_matches;
        public ArgMatrix(String[][] subarguments, boolean[] argument_matches){
            this.subarguments=subarguments;
            this.argument_matches=argument_matches;
        }
        public boolean noArgMatches(){
            boolean ret=false;
            for(boolean b:argument_matches){
                ret|=b;
            }
            return !ret;
        }
    }
}
