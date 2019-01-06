package youtube.main;

import cn.wowspeeder.SSLocal;
import cn.wowspeeder.SSLocalHander;
import cn.wowspeeder.config.Config;
import cn.wowspeeder.config.ConfigLoader;
import com.google.gson.Gson;
import youtube.downloader.VideoMeta;
import youtube.downloader.YouTubeExtractor;
import youtube.downloader.YtFile;

import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zh_zhou
 * created at 2019/01/06 14:15
 * Copyright [2019] [zh_zhou]
 */
public class DowloadMain {

    public static void main(String[] args) throws Exception {

        InputStream is = DowloadMain.class.getResourceAsStream("/config.json");
        Config config = ConfigLoader.load(is);
        SSLocal ssLocal = SSLocal.getInstance();
        ssLocal.start(config, new SSLocalHander() {
            @Override
            public void success() {
            }
        });

        File folder = new File("temp/youtube/cache");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1081));
        YouTubeExtractor.LOGGING=true;
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
        extractor.setDefaultHttpProtocol(true);
        extractor.extract("https://www.youtube.com/watch?v=zfIPCp0wzgc&pbjreload=10",
                true,
                true);
    }
}
