package cn.wowspeeder.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * 加载Config配置
 */
public class ConfigLoader {

    private static Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public static Config load(InputStream in) throws Exception {
        try {
            JsonReader reader;
            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            Config config = new Gson().fromJson(reader, Config.class);
            reader.close();
            return config;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
    public static Config load(String file) throws Exception {
        InputStream in = new FileInputStream(file);
        return load(in);

    }
}
