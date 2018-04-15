package com.cj.witcommon.utils.excle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *
 * 学生统计信息
 * @author daochuwenziyao
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatistics{
    private Integer id;
    @IsNeeded
    private BigDecimal totalGrade;
    @IsNeeded
    private BigDecimal avgGrade;



}