package com.cj.witcommon.utils.entity.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <b> 分页通用类 </b>
 *
 * @author kangxu
 * @param <T>
 *
 */
@Data
@ApiModel(value = "分页对象")
public class Pager<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4542617637761955078L;

    /**
     * currentPage 页码
     */
    @ApiModelProperty(name = "currentPage",value = "页码",dataType = "Integer")
    private int currentPage = 1;
    /**
     * pageSize 每页大小
     */
    @ApiModelProperty(name = "pageSize",value = "每页大小",dataType = "Integer")
    private int pageSize = 10;


    private int minRow = 0;  //LIMIT中的第一个参数，开始下标
    private int maxRow = pageSize;  //LIMIT中的第二个参数，从下标开始查询的条数




    /**
     * pageTotal 总页数
     */
    @ApiModelProperty(name = "pageTotal",value = "总页数",dataType = "Integer")
    private int pageTotal;
    /**
     * recordTotal 总条数
     */
    @ApiModelProperty(name = "recordTotal",value = "总条数",dataType = "Integer")
    private int recordTotal = 0;
    /**
     * previousPage 前一页
     */
    @ApiModelProperty(name = "previousPage",value = "前一页",dataType = "Integer")
    private int previousPage;
    /**
     * nextPage 下一页
     */
    @ApiModelProperty(name = "nextPage",value = "下一页",dataType = "Integer")
    private int nextPage;
    /**
     * firstPage 第一页
     */
    @ApiModelProperty(name = "firstPage",value = "第一页",dataType = "Integer")
    private int firstPage = 1;
    /**
     * lastPage 最后一页
     */
    @ApiModelProperty(name = "lastPage",value = "最后一页",dataType = "Integer")
    private int lastPage;
    /**
     * 传入的多个参数
     */
    @ApiModelProperty(name = "parameters",value = "多条件map集合",dataType = "HashMap")
    private Map<String, Object> parameters = new HashMap<>();
    /**
     * 传入的单个参数，模糊查询用
     */
    @ApiModelProperty(name = "parameter",value = "单条件",dataType = "String")
    private String parameter;

    /**
     * content 返回的每页的内容
     */
    @ApiModelProperty(name = "content",value = "返回的每页的内容")
    private List<T> content;

    // 以下set方式是需要赋值的
    /**
     * 设置当前页 <br>
     *
     * @author kangxu
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        if(currentPage > 0) {
            this.currentPage = currentPage;
        }


    }

    /**
     * 设置每页大小,也可以不用赋值,默认大小为10条 <br>
     *
     * @author kangxu
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if(pageSize > 0) {

            this.pageSize = pageSize;
            this.minRow = (this.currentPage - 1) * this.pageSize;
            this.maxRow = (this.currentPage - 1) * this.pageSize + this.pageSize;
        }

    }

    /**
     * 设置总条数,默认为0 <br>
     *
     * @author kangxu
     *
     * @param recordTotal
     */
    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
        otherAttr();
    }

    /**
     * 设置分页内容 <br>
     *
     * @author kangxu
     *
     * @param content
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * 设置其他参数
     *
     * @author kangxu
     *
     */
    public void otherAttr() {
        // 总页数
        this.pageTotal = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1 : this.recordTotal / this.pageSize;
        // 第一页
        this.firstPage = 1;
        // 最后一页
        this.lastPage = this.pageTotal;
        // 前一页
        if (this.currentPage > 1) {
            this.previousPage = this.currentPage - 1;
        } else {
            this.previousPage = this.firstPage;
        }
        // 下一页
        if (this.currentPage < this.lastPage) {
            this.nextPage = this.currentPage + 1;
        } else {
            this.nextPage = this.lastPage;
        }
    }

    // 放开私有属性
    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public List<T> getContent() {
        return content;
    }




}