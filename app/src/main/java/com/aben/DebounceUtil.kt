package com.aben

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 类描述：
 * 创建人：chenbin
 * 创建时间：2024/7/22 10:44 AM
 * @version
 *
 */

/**
 * 第一种方式 使用handler 延时处理
 */
fun View.click(onClickListener: OnClickListener) {
    var isClickAble = true
    setOnClickListener {
        if (!isClickAble) return@setOnClickListener
        onClickListener.onClick(this)
        isClickAble = false
        Log.i("DebounceUtils", "我被暂时禁用了")
        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("DebounceUtils", "我又恢复可点击了")
            isClickAble = true
        }, 500)
    }
}


//第二种方式 Aspect
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class DebounceClick(val delay: Long = 1000)

//@Aspect
//class DebounceClickAspect{
//    private var lastTime:Long = 0
//
//    @Before("execution(@com.aben.DebounceClick * *(..))")
//    fun beforClick(joinPoint:JoinPoint){
//        val currentTime = System.currentTimeMillis()
//        val methodSignature = joinPoint.signature as MethodSignature
//        val debounceClick = methodSignature.method.getAnnotation(DebounceClick::class.java)
//        val delay = debounceClick?.delay ?: 1000
//
//        if(currentTime - lastTime < delay){
//            throw DebounceException("debounce clicked")
//        }
//
//        lastTime = currentTime
//    }
//}
//
class DebounceException(message: String) : Exception(message)


//第三种方式 动态代理
class DebounceClickProxy(private val target: Any) : InvocationHandler {

    private var lastClickTime: Long = 0
    override fun invoke(proxy: Any?, methond: Method?, args: Array<out Any>?): Any? {
        if (isDebouncedClick() && methond?.name == "onClick") {
            return null
        }
        return methond?.invoke(target, *(args ?: arrayOfNulls<Any>(0)))
    }

    private fun isDebouncedClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        val interval = currentTime - lastClickTime
        lastClickTime = currentTime

        return interval < 1000 // 设置延迟时间，单位为毫秒
    }
}

fun View.onDebounceClick(listener: OnClickListener) {
    val proxy = Proxy.newProxyInstance(
        listener.javaClass.classLoader,
        arrayOf(OnClickListener::class.java),
        DebounceClickProxy(listener)
    ) as OnClickListener

    setOnClickListener(proxy)
}
