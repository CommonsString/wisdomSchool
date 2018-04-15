package com.cj.witbasics.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StudentScoreService {


    /**
     * 批量导入
     */
    boolean bathImportInfo(MultipartFile file, InputStream in, Long operatorId);
}
