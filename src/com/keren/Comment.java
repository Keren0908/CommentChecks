package com.keren;

/**
 * @author Keren He
 * @date 2018/10/24 19:59
 */
public class Comment {

    private int countLines = 0; // Total # of lines
    private int countCommentLines = 0;
    private int countSingleComments = 0; // Total # of single line comments
    private int countBlockComments = 0; // Total # of block line comments
    private int countBlockCommentsLines = 0; // Total # of comment linew within block comments
    private int countTODO = 0; // Total # of "TODO"
    private String error = "";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCountCommentLines() {
        return countCommentLines;
    }

    public void setCountCommentLines(int countCommentLines) {
        this.countCommentLines = countCommentLines;
    }

    public int getCountLines() {
        return countLines;
    }

    public void setCountLines(int countLines) {
        this.countLines = countLines;
    }

    public int getCountSingleComments() {
        return countSingleComments;
    }

    public void setCountSingleComments(int countSingleComments) {
        this.countSingleComments = countSingleComments;
    }

    public int getCountBlockComments() {
        return countBlockComments;
    }

    public void setCountBlockComments(int countBlockComments) {
        this.countBlockComments = countBlockComments;
    }

    public int getCountBlockCommentsLines() {
        return countBlockCommentsLines;
    }

    public void setCountBlockCommentsLines(int countBlockCommentsLines) {
        this.countBlockCommentsLines = countBlockCommentsLines;
    }



    public int getCountTODO() {
        return countTODO;
    }

    public void setCountTODO(int countTODO) {
        this.countTODO = countTODO;
    }
}
