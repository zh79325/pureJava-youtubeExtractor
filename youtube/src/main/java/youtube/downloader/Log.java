package youtube.downloader;

/**
 * @author zh_zhou
 * created at 2019/01/06 14:19
 * Copyright [2019] [zh_zhou]
 */
public class Log {
    public static void e(String tag, String message) {
        log("ERROR",tag,message);
    }

    static  void log(String type,String tag,String message){
        System.out.println(String.format("%s [%s]=>%s",type,tag,message));
    }

    public static void d(String tag, String message) {
        log("DEBUG",tag,message);
    }
}
