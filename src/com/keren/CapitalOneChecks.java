package com.keren;

/**
 * @author Keren He
 * @date 2018/10/14 11:28
 */

import java.io.*;
import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * CapitalOneChecks Class helps to do code statistics.
 * Read in valid program file and return an output of code statistics for comments.
 * @author Keren
 *
 */

public class CapitalOneChecks {


    private int countLines = 0; // Total # of lines
    private int countSingleComments = 0; // Total # of single line comments
    private int countBlockComments = 0; // Total # of block line comments
    private int countBlockCommentsLines = 0; // Total # of comment linew within block comments

    private int inlineBlockComments = 0; // Total # of inline block line comments (like '/* */')
    private int countTODO = 0; // Total # of "TODO"

    private File file = null; // Input File
    private String extension = null; // Extension of the input file

    private String[] extensions = {"java","js","c","cpp","h","py","ipynb","html","xml","css"}; // Valid extension list

    /**
     * To check whether the input file is valid
     * To set value to 'file' field and 'extension' field
     * @param filepath [in] The path of file
     * @return boolean
     * @exception FileNotFoundException
     */
    private boolean checkFile(String filepath) throws FileNotFoundException{

        // To get the extension of the file
        int index = filepath.lastIndexOf('.');

        if(index == -1){

            System.out.println(filepath + " is not a valid file!");
            return false;

        }

        else{
            extension = filepath.substring(index+1);

            // If not a extension defined in extension list
            if(!Arrays.asList(extensions).contains(extension)){

                System.out.println("Extention is not valid!");
                return false;

            }
        }

        file = new File(filepath);

        if(file == null || !file.exists()) {

            System.out.println("File Not Found!");
            return false;
            //throw new FileNotFoundException("File not found!");

        }

        return true;
    }


    /**
     * To get the code stats of files that comment like java (js,c,cpp)
     * Single line comment - //
     * Block line comment - \/**\/ \/***\/
     * @param file [in] The input file
     * @exception IOException
     */
    private void codeStatsJava(File file) throws IOException{


        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // The regular expression of single line comment
        String patternSingle = ".*/{2}.*";

        // The regular expression of block line comment starter \/*
        String patternBlockStart = ".*/\\x2a.*";

        // The regular expression of block line comment end \*/
        String patternBlockEnd = ".*\\x2a/.*";

        // The regular expression of 'TODO' line
        String patternTODO = ".*TODO:.*";


        String line = null;
        boolean blockFlag = false; // Used for

        // Go through every line of the file
        while( null != (line = br.readLine())){

            countLines++;

            if(Pattern.matches(patternTODO,line)){
                countTODO++;
            }

            // If the block line comment start but the end sign is not in the same line
            if(Pattern.matches(patternBlockStart,line) && !Pattern.matches(patternBlockEnd,line)) {

                // Loop until the block line comment ends
                do {

                    if(Pattern.matches(patternTODO,line)) countTODO++; // if TODO is not in the first line of block line comment

                    countLines++;
                    countBlockCommentsLines++;
                    line = br.readLine();

                } while (!Pattern.matches(patternBlockEnd, line));
                blockFlag = true;
                countBlockComments++;

            }

            // For single line block line comment
            else if(Pattern.matches(patternBlockStart,line) && Pattern.matches(patternBlockEnd,line)){

                countBlockComments++;
                inlineBlockComments++;
                countBlockCommentsLines++;

            }

            // The do-while loop would ignore the last line of every block line comment, need the add 1 to it
            if(blockFlag){
                countLines++;
                countBlockCommentsLines++;
                if(Pattern.matches(patternBlockEnd,line)){
                    blockFlag = false;

                }
            }

            // To check the single line comment
            if(Pattern.matches(patternSingle,line)){
                countSingleComments++;
            }

        }
        br.close(); // close the reader and release resources

    }

    /**
     * To get the code stats of files that comment like python
     * Single line comment - #
     * Block line comment - continues comment lines start with '#'
     * @param file [in] The input file
     * @exception IOException
     */
    private void codeStatsPython(File file) throws IOException{

        FileReader fr = new FileReader(file);

        BufferedReader br = new BufferedReader(fr);


        // The regular expression of single line comment
        String patternSingle = ".*#.*";

        // The regular expression of line that starts with '#'
        String patternBlock = "\\s*#.*";

        // The regular expresion of 'TODO' line
        String patternTODO = ".*TODO:.*";

        String line = null;

        while(null != (line = br.readLine())){

            countLines++;

            // If the line starts with #, it could be single line or block line comment
            if(Pattern.matches(patternBlock,line)){
                int lines = 0; // Used to check if it's multi-line comments

                // Loop until the block line comment ends
                do {
                    if(Pattern.matches(patternTODO,line)) {

                        countTODO++; //
                    }
                    line = br.readLine();
                    lines++;
                    countBlockCommentsLines++;
                    countLines++;

                }while(!(line == null) && Pattern.matches(patternBlock,line));

                // If there are more than 1 continues comment lines, means it's block line comment
                if(lines >= 2){
                    countBlockComments++;
                }

                if(lines == 1){
                    countSingleComments++;
                    countBlockCommentsLines--;
                }


                if(line == null){
                    countLines--;
                    break;
                }
            }

            // if the comment starts from the middle of a line
            else if(Pattern.matches(patternSingle,line)){
                if(Pattern.matches(patternTODO,line)) {
                    countTODO++; //
                }

                countSingleComments++;
            }
        }
    }

    /**
     * To get the code stats of files that comment like html
     * Single line comment - <!-- -->
     * Block line comment - continues comment lines start with '<!--' and end with '-->'
     * @param file [in] The input file
     * @exception IOException
     */

    private void codeStatsHTML(File file) throws IOException{

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // The regular expression of <!--
        String patternStart = ".*\\x3c\\x21\\x2d{2}.*";

        // The regular expression of -->
        String patternEnd = ".*\\x2d{2}\\x3e.*";

        // The regular expression of TODO:
        String patternTODO = ".*TODO:.*";

        String line = null;

        boolean blockFlag = false;


        while(null != (line = br.readLine())){

            countLines++;

            if(Pattern.matches(patternTODO,line)){
                countTODO++;
            }

            // For single line block line commen,treat it as single line comment
            if(Pattern.matches(patternStart,line) && Pattern.matches(patternEnd,line)) {

                countSingleComments++;

            }

            // If the block line comment start but the end sign is not in the same line
            else if(Pattern.matches(patternStart,line) && !Pattern.matches(patternEnd,line)){

                // Loop until the block line comment ends
                do {

                    if(Pattern.matches(patternTODO,line)){
                        countTODO++;
                    }

                    countLines++;
                    countBlockCommentsLines++;
                    line = br.readLine();

                }while(!Pattern.matches(patternEnd,line));

                countBlockComments++;
                blockFlag = true;
            }

            // The do-while loop would ignore the last line of every block line comment, need the add 1 to it
            if(blockFlag){
                countLines++;
                countBlockCommentsLines++;

                if(Pattern.matches(patternEnd,line)){
                    blockFlag = false;
                }

            }

        }

    }

    /**
     * A function used to dispatch different functions for different languages
     * @param filepath [in] The input file
     * @exception IOException
     */
    public  void check(String filepath) throws IOException{

        // check to see if the file is valid
        boolean isValid = checkFile(filepath);

        if(isValid){
            if(extension.equals("java")     ||
                    extension.equals("js")  ||
                    extension.equals("c")   ||
                    extension.equals("h")   ||
                    extension.equals("cpp") ||
                    extension.equals("css"))
            {
                codeStatsJava(file);

                //css file do not have '//' comment, single line comment is like /**/ in one line
                if(extension.equals("css")){
                    countSingleComments = inlineBlockComments;
                    countBlockComments -= countSingleComments;
                    countBlockCommentsLines -= countSingleComments;
                    countLines -= countSingleComments;
                }

                countLines = (countLines-(countBlockComments-inlineBlockComments));
            }

            else if(extension.equals("py") || extension.equals(("ipynb"))){
                codeStatsPython(file);

            }

            else if(extension.equals("html") || extension.equals("xml")){
                codeStatsHTML(file);
                countLines = countLines-countBlockComments;
            }

            System.out.println("Total # of lines: "+countLines);
            System.out.println("Total # of comment lines: "+(countSingleComments+countBlockCommentsLines));
            System.out.println("Total # of single line comments: "+countSingleComments);
            System.out.println("Total # of comment lines within block comments: "+countBlockCommentsLines);
            System.out.println("Total # of block line comments: "+countBlockComments);
            System.out.println("Total # of TODO's: "+countTODO);
        }

    }

}
