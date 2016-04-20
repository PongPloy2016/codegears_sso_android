package com.tow.sso.codegears_sso_android.logger;

import android.util.Log;

public  class Logger {
    private static final  boolean IS_ENABLE = true;

    public  static  void Log (String name, String text){
        if( IS_ENABLE ){
           // name.replaceAll(str, str1);
            name.replace("", "*$\n");
            text.replace("", "$\n");

            Log.e(name,text);

        }
    }


}
