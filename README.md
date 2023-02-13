# ArTalk-Demo
anyrtc Talk Android SDK 可实现公网实时语音对讲、实时广播功能, 详情请[文档中心](https://docs.anyrtc.io/cn/Talk/product_rtk)。

ArTalk AndroidSDK 提供的以下 API 接口和回调:

## 登录登出流程

| API                                 | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [createInstance](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#createinstance)  | 创建并返回一个 [ARTalkClient](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice) 实例。|
| [login](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#login)  | 用户登录 anyrtc Talk 系统。|
| [logout](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#logout)  | 退出登录，退出后自动断开连接和销毁回调监听。|

| 事件回调                               | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [ConnectionStateChanged](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkserviceeventhandler#onconnectionstatechanged)  | SDK 与 anyrtc Talk 系统的连接状态发生了改变时触发该回调。|

## 对讲频道相关
| API                                 | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [createChannel](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#createchannel)  | 创建并返回一个 [ARTalkChannel](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class) 实例。|
| [join](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#join)  | 加入频道。加入频道成功后可发起对讲等操作、收到该频道对讲语音等通知。|
| [leave](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#leave)  | 离开频道。不再接收频道对讲语音等。|
| [setLevel](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#setlevel)  | 设置用户对讲级别。注：只当前频道有效。|
| [getLevel](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#getlevel)  | 获取用户对讲级别。注：只当前频道有效。|
| [pushToTalk](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#pushtotalk)  | 申请对讲（简称上麦)。|
| [stopPushToTalk](https://docs.anyrtc.io/cn/Talk/rtk_android/class/android_ichannel_class#stoppushtotalk)  | 取消对讲（简称下麦）。|
| [breakTalk](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#breaktalk)  | 打断对讲。|
| [muteAllRemoteAudio](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#muteallremoteaudio)  | 关闭/打开所有远程用户的音频。|
| [setPullAudioQuality](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#setpullaudioquality)  | 设置音频拉流质量。|
| [setPushAudioQuality](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichannel_class#setpushaudioquality)  | 设置音频推流质量。|

| 事件回调                               | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [onUserIsTalkOn](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onuseristalkon)  | 远端用户用户上麦回调。|
| [onUserIsTalkOff](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onuseristalkoff)  | 远端用户用户下麦回调。|
| [onUserStreamOn](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onuserstreamon)  | 频道广播开始回调。|
| [onUserStreamOff](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onuserstreamoff)  | 频道广播结束回调。|
| [onPushToTalkResult](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onpushtotalkresult)  | 上麦结果回调。|
| [onPushToTalkEnded](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/class/android_ichanneleventhandler_class#onpushtotalkended)  | 下麦结果回调。|

## 更新 Token
| API                                 | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [renewToken](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#renewtoken)  | 更新当前 Token。|  

| 事件回调                                 | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [onTokenWillExpired](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkserviceeventhandler#ontokenwillexpired)  | Token 即将过期触发该回调。|   
| [onTokenExpired](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkserviceeventhandler#ontokenexpired)  | Token 过期时触发该回调。|    
     


## 日志设置与版本查询
| 属性                               | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [setLogFilter](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#setlogfilter)  | 设置 SDK 的日志输出等级。 |  
| [getSdkVersion](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#getsdkversion)  | anyrtc Talk SDK 的当前版本信息。| 


## 定制方法
| API                                 | 描述                                |
| :---------------------------------- | :---------------------------------- |
| [setParameters](https://docs.anyrtc.io/cn/Talk/api-ref/rtk_android/android_rtk_irtkservice#setparameters)  | 配置 SDK 提供技术预览或特别定制功能。| 
