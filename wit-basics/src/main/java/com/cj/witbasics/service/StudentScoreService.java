package com.cj.witbasics.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

public interface StudentScoreService {


    /**
     * 批量导入
     */
    boolean bathImportInfo(MultipartFile file, InputStream in, Map params, Long operatorId, Integer modelNumber);
}
