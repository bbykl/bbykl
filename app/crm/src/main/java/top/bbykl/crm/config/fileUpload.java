package top.bbykl.crm.config;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 * @description: 文件上传类
 */

public class fileUpload extends CommonsMultipartResolver {
    @Override
    public void setDefaultEncoding(String defaultEncoding) {
        super.setDefaultEncoding("UTF-8");
    }

    @Override
    public void setMaxUploadSizePerFile(long maxUploadSizePerFile) {
        //5MB大小
        super.setMaxUploadSizePerFile(10485760/2);
    }
}
