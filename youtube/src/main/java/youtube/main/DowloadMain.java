package youtube.main;

import cn.wowspeeder.SSLocal;
import cn.wowspeeder.SSLocalHander;
import cn.wowspeeder.SocketMain;
import cn.wowspeeder.config.Config;
import cn.wowspeeder.config.ConfigLoader;

import org.apache.logging.slf4j.Log4jLogger;
import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.LoggerFactory;
import potato.main.SocketMainJava;
import youtube.downloader.VideoMeta;
import youtube.downloader.YouTubeExtractor;
import youtube.downloader.YtFile;

import javax.net.ssl.*;
import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author zh_zhou
 * created at 2019/01/06 14:15
 * Copyright [2019] [zh_zhou]
 */
public class DowloadMain {

    public static void main(String[] args) throws Exception {
        SocketMainJava.main(null);


        boolean skip = true;
        if (skip) {
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }


        File folder = new File("temp/youtube/cache");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1081));
        YouTubeExtractor.LOGGING = true;
        YouTubeExtractor extractor = new YouTubeExtractor(folder.toPath()) {
            @Override
            protected void onPreExecute() {
                System.out.println("onPreExecute");
            }

            @Override
            protected void onProgressUpdate(Void... progress) {
                System.out.println("progress");
            }

            @Override
            protected void onExtractionComplete(Map<Integer, YtFile> ytFiles, VideoMeta videoMeta) {
                System.out.println("complete");
            }
        };
        extractor.setProxy(proxy);
//        extractor.setDefaultHttpProtocol(true);
        try {
            extractor.extract("https://www.youtube.com/watch?v=J4qG6l-aqec",
                    true,
                    true);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }};

    public static class NullHostNameVerifier implements HostnameVerifier {
        /*
         * (non-Javadoc)
         *
         * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
         * javax.net.ssl.SSLSession)
         */
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
