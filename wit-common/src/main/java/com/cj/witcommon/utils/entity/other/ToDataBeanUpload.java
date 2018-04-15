package com.cj.witcommon.utils.entity.other;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDataBeanUpload {
    private String createId;
    private String uuid;
    private String originalName;
    private String extName;
    private String fileSize;
    private String fileUrl;  //图片访问地址
    private String fileKey;
    private String fileName;
    private String duration;
}
