package org.kitfox.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;

public class Http {
    /*
    oauthUrl=https://api.app.ulaval.ca/ul/auth/oauth/v2/token
        oauthMagicPassword=ena2009;
        adminUser=adsy1
        ense2User=ense2
        etud2User=etud2
        oauthClientId=l7xxb55b37825df04746b5da0f547eab4d02
        oauthSecret=e3b4aa96f64b47b09f5eebb0d494ea36
    */

    public static void main(String[] args) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.test.ulaval.ca/ul/auth/oauth/v2/token");
        String base = new String(Base64.encode("l7xxb55b37825df04746b5da0f547eab4d02:e3b4aa96f64b47b09f5eebb0d494ea36".getBytes()));

        post.addHeader("Authorization", "Basic " + base);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("grant_type", "password"));
        nameValuePairs.add(new BasicNameValuePair("password", "VS&@7pqz"));
        nameValuePairs.add(new BasicNameValuePair("username", "etud2"));
        nameValuePairs.add(new BasicNameValuePair("scope", ""));

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
            post.setEntity(entity);
            HttpResponse response = client.execute(post);

            response.getStatusLine().getStatusCode();

            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        //                nameValuePairs.add(new BasicNameValuePair("registrationid",
        //                                "123456789"));
        //                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        //                
        //            HttpClient client = new HttpClient();
        //        client.getParams().setParameter("Authorization", "Basic bDd4eGI1NWIzNzgyNWRmMDQ3NDZiNWRhMGY1NDdlYWI0ZDAyOmUzYjRhYTk2ZjY0YjQ3YjA5ZjVlZWJiMGQ0OTRlYTM2");
        //
        //        BufferedReader br = null;
        //
        //        HttpPost method = new HttpPost("https://api.app.ulaval.ca/ul/auth/oauth/v2/token");
        //        method.setRequestBody(new NameValuePair[] {
        //                                                   new NameValuePair("grant_type", "password"),
        //                                                   new NameValuePair("username", "ense2"),
        //                                                   new NameValuePair("password", "VS&@7pqz"),
        //                                                   new NameValuePair("scope", ""),
        //        });
        //        method.setParameter("requestContentType", "application/x-www-form-urlencoded");

    }

}
