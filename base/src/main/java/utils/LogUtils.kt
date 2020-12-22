package utils

import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.DEBUG

object LogUtils {
    fun d(tag:String,msg:String){
        Logger.log(DEBUG,tag,msg,null)
    }

    fun d(msg:String){
        Logger.log(DEBUG,this.javaClass.simpleName,msg,null)
    }
}