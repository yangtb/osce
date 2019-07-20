package com.osce.server.utils.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: OssUploadUtil
 * @Description: 阿里云OSS文件上传工具类
 * @Author yangtongbin
 * @Date 2018/10/8
 */
@Component
@SuppressWarnings("unused")
public class OssUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(OssUploadUtil.class);


    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    private OSSClient ossClient;

    public OssUploadUtil(@Value("${oss.endpoint}") String endpoint,
                         @Value("${oss.accessKeyId}") String accessKeyId,
                         @Value("${oss.accessKeySecret}") String accessKeySecret) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * OSS单文件上传
     *
     * @param file
     * @param fileType 文件后缀
     * @param request
     * @param sum
     * @return 文件地址
     */
    public String uploadFile(MultipartFile file, String fileType,
                             String ossConfigKey, HttpServletRequest request, int sum) {
        // 文件名，根据UUID来
        String fileName = UUID.randomUUID().toString().toUpperCase().replace("-", "") + "." + fileType;
        return putObject(file, fileType, fileName, request, sum, false);
    }

    /**
     * OSS单文件上传
     *
     * @param file
     * @param fileType 文件后缀
     * @param request
     * @param sum
     * @return 文件地址
     */
    public String uploadFileProgress(MultipartFile file, String fileType,
                                     String ossConfigKey, HttpServletRequest request, int sum) {
        // 文件名，根据UUID来
        String fileName = UUID.randomUUID().toString().toUpperCase().replace("-", "") + "." + fileType;
        return putObject(file, fileType, fileName, request, sum, true);
    }

    /**
     * 更新文件:只更新内容，不更新文件名和文件地址。
     * (因为地址没变，可能存在浏览器原数据缓存，不能及时加载新数据，例如图片更新，请注意)
     *
     * @param file
     * @param fileType
     * @param oldUrl
     * @param request
     * @param sum
     * @return
     */
    public String updateFile(CommonsMultipartFile file, String fileType,
                             String oldUrl, HttpServletRequest request, int sum) {
        String fileName = getFileName(oldUrl);
        if (fileName == null) {
            return null;
        }
        return putObject(file, fileType, fileName, request, sum, false);
    }

    /**
     * 替换文件:删除原文件并上传新文件，文件名和地址同时替换
     * * 解决原数据缓存问题，只要更新了地址，就能重新加载数据)
     *
     * @param file
     * @param fileType     文件后缀
     * @param oldUrl       需要删除的文件地址
     * @param ossConfigKey
     * @param request
     * @param sum
     * @return
     */
    public String replaceFile(CommonsMultipartFile file,
                              String fileType, String oldUrl, String ossConfigKey,
                              HttpServletRequest request, int sum) {
        // 先删除原文件
        boolean flag = deleteFile(oldUrl);
        if (!flag) {
            // 更改文件的过期时间，让他到期自动删除。
        }
        return uploadFile(file, fileType, ossConfigKey, request, sum);
    }


    /**
     * 单文件删除
     *
     * @param fileUrl 需要删除的文件url
     * @return
     */
    public boolean deleteFile(String fileUrl) {
        // 根据url获取bucketName
        String bucketName = OssUploadUtil.getBucketName(fileUrl);
        // 根据url获取fileName
        String fileName = OssUploadUtil.getFileName(fileUrl);
        if (bucketName == null || fileName == null) {
            return false;
        }
        try {
            GenericRequest request = new DeleteObjectsRequest(bucketName)
                    .withKey(fileName);
            ossClient.deleteObject(request);
        } catch (Exception oe) {
            oe.printStackTrace();
            return false;
        } finally {
            //ossClient.shutdown();
        }
        return true;
    }


    /**
     * 批量文件删除(较快)：适用于相同endPoint和BucketName
     *
     * @param fileUrls 需要删除的文件url集合
     * @return
     */
    public int deleteFile(List<String> fileUrls) {
        // 成功删除的个数
        int deleteCount = 0;
        // 根据url获取bucketName
        String bucketName = OssUploadUtil.getBucketName(fileUrls.get(0));
        // 根据url获取fileName
        List<String> fileNames = getFileName(fileUrls);
        if (bucketName == null || fileNames.size() <= 0) {
            return 0;
        }
        try {
            DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName)
                    .withKeys(fileNames);
            DeleteObjectsResult result = ossClient.deleteObjects(request);
            deleteCount = result.getDeletedObjects().size();
        } catch (OSSException oe) {
            oe.printStackTrace();
            throw new RuntimeException("OSS服务异常:", oe);
        } catch (ClientException ce) {
            ce.printStackTrace();
            throw new RuntimeException("OSS客户端异常:", ce);
        } finally {
            //ossClient.shutdown();
        }
        return deleteCount;

    }

    /**
     * 批量文件删除(较慢)：适用于不同endPoint和BucketName
     *
     * @param fileUrls 需要删除的文件url集合
     * @return
     */
    public int deleteFiles(List<String> fileUrls) {
        int count = 0;
        for (String url : fileUrls) {
            if (deleteFile(url)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param fileType
     * @param fileName
     * @param request1
     * @param sum
     * @param progress
     * @return
     */
    private String putObject(MultipartFile file, String fileType,
                             String fileName, HttpServletRequest request1, int sum, boolean progress) {
        String url = null;
        try {

            InputStream input = file.getInputStream();
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 设置上传内容类型
            meta.setContentType(contentType(fileType));
            // 被下载时网页的缓存行为
            meta.setCacheControl("no-cache");

            /* MultipartFile转File */
            File f = null;
            try {
                f = File.createTempFile("tmp", null);
                file.transferTo(f);
                f.deleteOnExit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (progress) {
                /**
                 * 这里用带进度条的OSS上传
                 * 将session传入PutObjectProgressListener的构造中!官网例子是没有这个操作的 注意new
                 *  PutObjectRequest()的第三个参数是File而不是官网介绍的FileInputStream,否则获取不到进度.
                 */
                PutObjectResult putObjectResult = ossClient
                        .putObject(new PutObjectRequest(bucketName, fileName, f)
                                .withProgressListener(new PutObjectProgressListener(request1.getSession(), sum)));
            } else {
                //创建上传请求
                PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, meta);
                ossClient.putObject(request);
            }

            // 上传成功再返回的文件路径
            url = endpoint.replaceFirst("https://", "https://" + bucketName + ".") + "/" + fileName;
        } catch (OSSException oe) {
            oe.printStackTrace();
            return null;
        } catch (ClientException ce) {
            ce.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //ossClient.shutdown();
        }
        return url;
    }

    /**
     * 获取文件类型
     *
     * @param fileType
     * @return
     */
    private String contentType(String fileType) {
        fileType = fileType.toLowerCase();
        String contentType = "";
        if (fileType.equals("bmp")) {
            contentType = "image/bmp";

        } else if (fileType.equals("gif")) {
            contentType = "image/gif";

        } else if (fileType.equals("png") || fileType.equals("jpeg")
                || fileType.equals("jpg")) {
            contentType = "image/jpeg";

        } else if (fileType.equals("html")) {
            contentType = "text/html";

        } else if (fileType.equals("txt")) {
            contentType = "text/plain";

        } else if (fileType.equals("vsd")) {
            contentType = "application/vnd.visio";

        } else if (fileType.equals("ppt") || fileType.equals("pptx")) {
            contentType = "application/vnd.ms-powerpoint";

        } else if (fileType.equals("doc") || fileType.equals("docx")) {
            contentType = "application/msword";

        } else if (fileType.equals("xml")) {
            contentType = "text/xml";

        } else if (fileType.equals("mp4")) {
            contentType = "video/mp4";

        } else {
            contentType = "application/octet-stream";

        }
        return contentType;
    }

    /**
     * 根据url获取bucketName
     *
     * @param fileUrl 文件url
     * @return
     */
    private static String getBucketName(String fileUrl) {
        String http = "http://";
        String https = "https://";
        int httpIndex = fileUrl.indexOf(http);
        int httpsIndex = fileUrl.indexOf(https);
        int startIndex = 0;
        if (httpIndex == -1) {
            if (httpsIndex == -1) {
                return null;
            } else {
                startIndex = httpsIndex + https.length();
            }
        } else {
            startIndex = httpIndex + http.length();
        }
        int endIndex = fileUrl.indexOf(".oss-");
        return fileUrl.substring(startIndex, endIndex);
    }

    /**
     * 根据url获取fileName
     *
     * @param fileUrl 文件url
     * @return
     */
    private static String getFileName(String fileUrl) {
        String str = "aliyuncs.com/";
        int beginIndex = fileUrl.indexOf(str);
        if (beginIndex == -1) {
            return null;
        }
        return fileUrl.substring(beginIndex + str.length());
    }


    /**
     * 根据url获取fileNames集合
     *
     * @param fileUrls 文件url
     * @return
     */
    private List<String> getFileName(List<String> fileUrls) {
        List<String> names = new ArrayList<>();
        for (String url : fileUrls) {
            names.add(getFileName(url));
        }
        return names;
    }

    public class PutObjectProgressListener implements ProgressListener {
        private HttpSession session;
        private long bytesWritten = 0;
        private long totalBytes = -1;
        private boolean succeed = false;
        private int percent = 0;
        int sum = 0;


        public PutObjectProgressListener() {
        }

        /**
         * 构造方法中加入session
         */
        public PutObjectProgressListener(HttpSession mSession, int sum) {
            this.session = mSession;
            this.sum = sum;
            session.setAttribute("upload_percent", 0);
            session.setAttribute("upload_sum", sum);
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    logger.info("Start to upload......");
                    break;
                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    logger.info(this.totalBytes + " bytes in total will be uploaded to OSS");
                    break;
                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.bytesWritten += bytes;
                    if (this.totalBytes != -1) {
                        percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
                        // 将进度percent放入session中
                        session.setAttribute("upload_percent", percent);
                    } else {
                    }
                    break;
                case TRANSFER_COMPLETED_EVENT:
                    this.succeed = true;

                    break;
                case TRANSFER_FAILED_EVENT:

                    break;
                default:
                    break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }
    }


}
