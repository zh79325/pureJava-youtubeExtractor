package potato.main;

import com.stfl.misc.Config;
import com.stfl.network.NioLocalServer;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;

/**
 * @author zh_zhou
 * created at 2019/01/06 16:46
 * Copyright [2019] [zh_zhou]
 */
public class SocketMainJava {
    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException {
        Config config = new Config("103.114.161.160",
                2233,
                "127.0.0.1",
                1081,
                "aes-256-cfb",
                "ntdtv.com");
        NioLocalServer server = new NioLocalServer(config);
        new Thread(server).start();
    }
}
