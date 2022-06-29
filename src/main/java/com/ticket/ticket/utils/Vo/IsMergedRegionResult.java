package com.ticket.ticket.utils.Vo;

public class IsMergedRegionResult {
    public boolean merged;//是否是合并单元格
    public int startRow;//开始行数
    public int endRow;//结束行数
    public int startCol;//开始列数
    public int endCol;//结束列数
    public IsMergedRegionResult(boolean merged, int startRow, int endRow
            , int startCol, int endCol){
        this.merged = merged;
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }
}
