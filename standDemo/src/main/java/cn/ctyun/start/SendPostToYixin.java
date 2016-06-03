package cn.ctyun.start;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 向易信服务器发送POST请求示例
 * @author newflydd
 * 易信授权的相关数据如下：
 * url:https://open.yixin.im/api/sendp2pmsg
 * access_token：b2421624-db5c-44d1-93f0-1e71f9cXXXXX
 * toAccountId：6c0f7514f4bdXXXX（唐蔚）；fd60e46db0dc119cfea740c3375XXXXX（王群）
 * content：需要推送的数据
 * {"text_content": "这段文字发送自易信SDK示例程序，并且由服务器自动调用发送","desc": "不需要描述","type":"text"}
 * {
"image": "http://img.hoop8.com/attachments/1510/911826782594.jpg",
"title": "新增发展积分",
"url": "http://img.hoop8.com/attachments/1510/911826782594.jpg",
"type":"image"
}
 *
 */

public class SendPostToYixin {

    public static void main(String[] args) throws Exception{
        HttpClient client=new DefaultHttpClient();
        HttpPost post=new HttpPost("https://open.yixin.im/api/sendp2pmsg");

        List<NameValuePair> parameters=new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("access_token", "48908ace-1299-4e3b-853b-d0462ef7cf19"));
        parameters.add(new BasicNameValuePair("toAccountId", "d6b1b0138b61b617c3061cd18d77b7a0"));//d6b1b0138b61b617c3061cd18d77b7a0 qingpan
        //文字类型parameters.add(new BasicNameValuePair("content", "{\"text_content\":\"这段文字发送自易信SDK示例程序，并且由服务器自动调用发送\",\"desc\":\"不需要描述\",\"type\":\"text\"}"));
       // 图片类型parameters.add(new BasicNameValuePair("content","{\"ps\":\"ddgg \",\"image\":\"http://nos.netease.com/yixinpublic/pr_ezqerif_wrtieb39mrdj3w==_1385019306_3704\",\"title\":\"月出MOONRISE\",\"url\":\"http://music.163.com/song/27955410/\",\"type\":\"image\"}"));
        //音乐类型
        //parameters.add(new BasicNameValuePair("content","{\"ps\":\"ddgg \",\"desc\":\"朱哲琴\",\"image\":\"http://nos.netease.com/yixinpublic/pr_ezqerif_wrtieb39mrdj3w==_1385019306_3704\",\"music_url\":\"http://m1.music.126.net/IjQ3JRJ1V6ybQIa5K86gXg==/5825212603979308.mp3\",\"title\":\"月出MOONRISE\",\"url\":\"http://music.163.com/song/27955410/\",\"type\":\"music\"}"));
        //图文类型
        parameters.add(new BasicNameValuePair("content","{\"ps\":\"ddgg \",\"desc\":\"朱哲琴\",\"image\":\"http://nos.netease.com/yixinpublic/pr_ezqerif_wrtieb39mrdj3w==_1385019306_3704\",\"title\":\"月出MOONRISE\",\"url\":\"http://music.163.com/song/27955410/\",\"type\":\"webpage\"}"));
        post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));

        HttpResponse response=client.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}