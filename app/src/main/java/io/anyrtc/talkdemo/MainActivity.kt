package io.anyrtc.talkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import io.anyrtc.artalk.*
import io.anyrtc.talkdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val logAdapter by lazy { LogAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userId = ((Math.random() * 9 + 1) * 100000).toInt().toString()


    private lateinit var arTalkClient: ARTalkClient
    private var arTalkChannel: ARTalkChannel? = null

    private val arTalkChannelListener = object : ARTalkChannelListener {
        override fun onPushToTalkResult(code: Int) {
            logAdapter.addData("onPushToTalkResult(code: ${code})")
            if (code == 0) {
                binding.tvChannelState.text = "我正在说话..."
            } else {
                binding.switchChannelSpeak.isChecked = false
                binding.tvChannelState.text = "上麦失败...${code}"
            }
        }

        override fun onPushToTalkEnded(code: Int) {
            logAdapter.addData("onPushToTalkEnded(code: ${code})")
            binding.tvChannelState.text = "结束说话"
        }

        override fun onUserIsTalkOn(userId: String?, userData: String?, userLevel: Int) {
            logAdapter.addData("onUserIsTalkOn(userId: ${userId})")
            binding.tvChannelState.text = "${userId}正在说话"
        }

        override fun onUserIsTalkOff(userId: String?, userData: String?) {
            logAdapter.addData("onUserIsTalkOff(userId: ${userId})")
            binding.tvChannelState.text = "${userId}停止说话"
        }

        override fun onUserStreamOn(userId: String?, userData: String?) {
            logAdapter.addData("onUserStreamOn(userId: ${userId})")
            binding.tvBroadcastState.text = "${userId}正在广播"
        }

        override fun onUserStreamOff(userId: String?, userData: String?) {
            logAdapter.addData("onUserStreamOff(userId: ${userId})")
            binding.tvBroadcastState.text = "${userId}停止广播"
        }

    }


    private var APP_ID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        APP_ID = getString(R.string.app_id)
        initArTalkClient()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            100
        )
        binding.run {
            tvUser.text = "当前用户ID ${userId}"
            rvLog.adapter = logAdapter
            switchLogin.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    login()
                } else {
                    logout()
                }
            }
            switchJoin.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    joinChannel()
                } else {
                    leaveChannel()
                }
            }
            switchChannelSpeak.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    arTalkClient.enableAudioCapture(true)
                    arTalkChannel?.pushToTalk(0)
                } else {
                    arTalkClient.enableAudioCapture(false)
                    arTalkChannel?.stopPushToTalk()
                }
            }

            switchChannelBroadcast.setOnCheckedChangeListener { compoundButton, b ->
                if (b) {
                    arTalkClient.enableAudioCapture(true)
                    arTalkChannel?.enableAudioStream(true)
                    binding.switchChannelBroadcast.text = "我正在广播...."
                } else {
                    arTalkClient.enableAudioCapture(false)
                    arTalkChannel?.enableAudioStream(false)
                    binding.switchChannelBroadcast.text = "我停止广播...."
                }
            }
        }
    }

    private fun initArTalkClient() {
        arTalkClient = ARTalkClient.createInstance(this, APP_ID, object : ARTalkClientListener {
            override fun onRenewTokenResult(p0: String?, p1: Int) {
            }

            override fun onTokenWillExpire() {
            }

            override fun onTokenExpired() {
            }

            override fun onConnectionStateChanged(p0: Int, p1: Int) {
            }

            override fun onNetworkStatus(p0: Int, p1: Int) {
            }

            override fun onTalkLocalMicAudioData(
                channelId: String?,
                data: ByteArray?,
                len: Int,
                sampleHz: Int,
                channel: Int
            ) {
            }

            override fun onTalkLocalSpeakerAudioData(
                p0: String?,
                p1: ByteArray?,
                p2: Int,
                p3: Int,
                p4: Int
            ) {
            }

            override fun onTalkRecordFile(p0: String?, p1: String?) {
            }

        })
    }

    private fun login() {
        arTalkClient.login("", userId, "", object : ResultCallback<Void> {
            override fun onSuccess(var1: Void?) {
                logAdapter.addData("登录成功")
            }

            override fun onFailure(code: Int) {
                logAdapter.addData("登录失败")
            }
        })
    }

    private fun logout() {
        arTalkChannel?.leave(null)
        arTalkChannel?.release()
        binding.switchJoin.isChecked = false
        binding.switchChannelSpeak.isChecked = false
        arTalkClient.logout(null)
        logAdapter.addData("退出登录")
    }

    private fun joinChannel() {
        arTalkChannel = arTalkClient.createChannel("9191", arTalkChannelListener)
        arTalkChannel?.join(object : ResultCallback<Void> {
            override fun onSuccess(var1: Void?) {
                logAdapter.addData("加入成功")
            }

            override fun onFailure(code: Int) {
                logAdapter.addData("加入失败")
            }

        })
    }

    private fun leaveChannel() {
        arTalkChannel?.leave(null)
        arTalkChannel?.release()
        logAdapter.addData("离开频道")
    }

    override fun onBackPressed() {
        arTalkClient.release()
        finish()
    }
}