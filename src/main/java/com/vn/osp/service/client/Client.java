package com.vn.osp.service.client;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

/**
 * Created by TienManh on 5/16/2017.
 */
public class Client {

    private static Logger log = LoggerFactory.getLogger(Client.class.getName());
    private static String rootURI;

    public static String buildFullURI(String path){
        rootURI= SystemProperties.getProperty("url_config_server_api");
        return rootURI+path;
    }

    public static String getObject(String path) {
        String response = null;
        HttpGet httpGet = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            httpGet = new HttpGet(buildFullURI(path));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpGet, responseHandler);
        }  catch(Exception e) {
            log.error("Co loi xay ra tai client.getObject: " + e.getMessage()+":"+buildFullURI(path));
        } finally {
            httpGet.reset();
        }

        return response;
    }

    /*get object them param, co nhuoc diem la` phai truyen param vao. param nay lai phai co' gia tri giong het tren api(vi du la= stringFilter).
    * neu khong muon thi tam thoi chi nghi ra cach chuyen GET tren api sang POST de lay gia tri @RequestBody chu ko phai @RequestParam nhu hien tai voi ham get*/
    public static String getObjectByFilter(String path,String param,String param_value) {
        String response = null;
        HttpGet httpGet = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setPath(buildFullURI(path)).addParameter(param, param_value);
            URI uri1 = uriBuilder.build();
            httpGet = new HttpGet(uri1.toString().substring(1));//cat bo dau / o dau` uri
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpGet, responseHandler);
        }  catch(Exception e) {
            log.error("Co loi xay ra tai client.getObjectByFilter: " + e.getMessage()+":"+buildFullURI(path));
        } finally {
            httpGet.reset();
        }

        return response;
    }

    /**
     * get object with list params:  param[key,value]
     * @param path
     * @param params
     * @return
     */
    public static String getObjectByParams(String path,List<String[]> params) {
        String response = null;
        HttpGet httpGet = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setPath(buildFullURI(path));
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    uriBuilder.addParameter(params.get(i)[0],params.get(i)[1]);
                }
            }
            URI uri1 = uriBuilder.build();
            httpGet = new HttpGet(uri1.toString().substring(1));//cat bo dau / o dau` uri
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpGet, responseHandler);
        }  catch(Exception e) {
            log.error("Co loi xay ra tai client.getObjectByFilter: " + e.getMessage()+":"+buildFullURI(path));
        } finally {
            httpGet.reset();
        }
        return response;
    }

    public static String postObject(String path, String jsonObject) {
        String response = null;
        HttpPost httpPost = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            httpPost = new HttpPost(buildFullURI(path));
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");

            StringEntity stringEntity = new StringEntity(jsonObject, "UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpPost, responseHandler);
        } catch(Exception e) {
            log.error("Co loi xay ra tai client.postObject: " + buildFullURI(path) + ":" + e.getMessage() +":"+jsonObject);
        } finally {
            httpPost.reset();
        }
        return response;
    }

    public static String putObject(String path, String jsonObject) {
        String response = null;
        HttpPut httpPut = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            httpPut = new HttpPut(buildFullURI(path));
            httpPut.addHeader(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");

            StringEntity stringEntity = new StringEntity(jsonObject, "UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");
            httpPut.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpPut, responseHandler);
        } catch(Exception e) {
            log.error("Co loi xay ra tai client.putObject: " + buildFullURI(path) + ":" + e.getMessage() +":"+jsonObject);
        } finally {
            httpPut.reset();
        }

        return response;
    }

    public static String deleteObject(String path,String param,String param_value) {
        String response = null;
        HttpDelete httpDetele = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setPath(buildFullURI(path)).addParameter(param, param_value);
            URI uri1 = uriBuilder.build();
            httpDetele = new HttpDelete(uri1.toString().substring(1));//cat bo dau / o dau` uri
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpDetele, responseHandler);
        }  catch(Exception e) {
            log.error("Co loi xay ra tai client.deleteObject: " + e.getMessage()+":"+buildFullURI(path));
        } finally {
            httpDetele.reset();
        }

        return response;
    }

    public String getRootURI() {
        return rootURI;
    }

    public void setRootURI(String rootURI) {
        this.rootURI = rootURI;
    }


}
