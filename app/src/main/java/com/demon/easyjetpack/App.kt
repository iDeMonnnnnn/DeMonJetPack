package com.demon.easyjetpack

import com.alibaba.android.arouter.launcher.ARouter
import com.demon.basemvvm.MvvmApp
import com.demon.easyjetpack.dagger.DaggerAppComponent
import com.jeremyliao.liveeventbus.LiveEventBus

/**AppComponent
 * @author DeMon
 * Created on 2020/4/10.
 * E-mail 757454343@qq.com
 * Desc:
 */
class App : MvvmApp() {


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

        initARouter()

        LiveEventBus.config().supportBroadcast(getAppContext())
    }


    /**
     * ARouter 相关的配置
     */
    private fun initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)       // 尽可能早，推荐在Application中初始化
    }
}