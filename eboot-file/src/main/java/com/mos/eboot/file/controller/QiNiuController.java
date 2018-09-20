package com.mos.eboot.file.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mos.eboot.file.config.ConstantQiniu;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.util.KeyUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
/**
 * 
 * @author Administrator
 *七牛云上传类
 */
@Controller
@RequestMapping("/qiniu/")
public class QiNiuController {
	@Autowired
    private ConstantQiniu constantQiniu;
	/**
	 * 上传到七牛云
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("uploadImgQiniu")
	@ResponseBody
	public ResultModel<String> uploadImgQiniu(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		ResultModel<String> result=new ResultModel<String>();
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = uploadQNImg(inputStream, KeyUtil.genUniqueKey()); // KeyUtil.genUniqueKey()生成图片的随机名
        result.setData(path);
        return result;
    }

    /**
     * 将图片上传到七牛云
     */
    private String uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(constantQiniu.getAccessKey(), constantQiniu.getSecretKey());
            String upToken = auth.uploadToken(constantQiniu.getBucket());
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = constantQiniu.getPath() + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
