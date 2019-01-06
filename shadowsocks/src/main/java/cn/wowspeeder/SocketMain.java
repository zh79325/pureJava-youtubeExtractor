package cn.wowspeeder;

import cn.wowspeeder.config.Config;
import cn.wowspeeder.config.ConfigLoader;

import java.io.InputStream;

/**
 * @author zh_zhou
 * created at 2019/01/06 16:29
 * Copyright [2019] [zh_zhou]
 */
public class SocketMain {

    public static void main(String[] args) throws Exception {
        InputStream is = SocketMain.class.getResourceAsStream("/config.json");
        Config config = ConfigLoader.load(is);

        SSLocal ssLocal = SSLocal.getInstance();
        ssLocal.start(config, new SSLocalHander() {
            @Override
            public void success() {
            }
        });
    }
}
