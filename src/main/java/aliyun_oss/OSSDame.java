package aliyun_oss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.google.gson.JsonNull;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author shipengfei
 * @data 19-11-12
 */
public class OSSDame {

    public static void main(String[] args) throws IOException {

        String p="artistic/artistic_Jony/teacher_Tom/20191112/video/";

        Date date=new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        System.out.println(date);

        String urls=OSSDame.getURL("easyarch-w",p);
        System.out.println(urls);

    }

    //下载文件到本地
    public static void download(String objectName) throws IOException {

        String endpoint = "http://oss-cn-beijing.aliyuncs.com";

        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM账号进行API访问或日常运维。
        // 请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FnMcpJZp5GHxexUq5EV";
        String accessKeySecret = "zwM6UWgidMRPu5EOx4AFdX4vYB9UhG";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String currentUser = System.getProperty("user.name");
        File file=new File("/home/"+currentUser+"/Desktop/"+objectName);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.createNewFile();
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest("easyarch-w",objectName ), file);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    public static String getURL(String bucketName, String keyPrefix){

//        List<String> url=new ArrayList<>();
        Map<String,String> urls=new HashMap<>();
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM账号进行API访问或日常运维。
        // 请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FnMcpJZp5GHxexUq5EV";
        String accessKeySecret = "zwM6UWgidMRPu5EOx4AFdX4vYB9UhG";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        Date date=new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);

        // 构造ListObjectsRequest请求。
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // 设置prefix参数来获取fun目录下的所有文件。
        listObjectsRequest.setPrefix(keyPrefix);
        // 递归列出fun目录下的所有文件。
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        for (OSSObjectSummary s : listing.getObjectSummaries()) {

            String key=s.getKey();
            System.out.println("\t" + key);
            if (key.equals(keyPrefix)){
                continue;
            }
            String url=ossClient.generatePresignedUrl(bucketName,key,date).toString();
            System.out.println(url);
            String[] keys=key.split("/");
            key=keys[keys.length-1];
            urls.put(key,url);
        }
        // 关闭OSSClient。
        ossClient.shutdown();

        // map中含有对象Map -> JSON
        String json=JSON.toJSONString(urls,true);

        return json;
    }

}
