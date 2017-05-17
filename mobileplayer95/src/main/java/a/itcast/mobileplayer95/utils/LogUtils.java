package a.itcast.mobileplayer95.utils;

import android.util.Log;

public class LogUtils {

    private static final boolean ENABLE = true;

    public static void e(String tag,String msg){
        if (ENABLE)
            Log.e("itcast_"+tag,msg);
    }

    public static void e(Class cls,String msg){
        if (ENABLE)
            Log.e("itcast_"+cls.getSimpleName(),msg);
    }
}
