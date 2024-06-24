package com.example.cefr

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.cefr.databinding.ActivityLiveBinding
import com.example.cefr.utils.PathUtils
import com.pedro.encoder.input.video.CameraHelper
import com.pedro.encoder.input.video.CameraOpenException
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import java.io.File

class LiveActivity : AppCompatActivity(), ConnectCheckerRtmp,
    SurfaceHolder.Callback, View.OnTouchListener {

//    private var streamKey: String? = "ztyc-p893-jatc-6y0t-fs1b" Damir youtube
//    private var rtmpip: String? = "rtmp://a.rtmp.youtube.com/live2/"
    private var rtmpip: String? = "rtmp://arn04.contribute.live-video.net/app/"
    private var streamKey: String? = "live_509012821_J6GzFWy6vraGlObtJC0XHI7QbLh43v" //Amir twitch
//    private var streamKey: String? = "live_1066953762_f77bVwCLe4D4wIRukmsjlZJZwtjvfS"//Damir
    private lateinit var rtmpCamera1: RtmpCamera1

    private var user: String = ""
    private var password: String = ""
    private var resolution: Int = 0
    private var fps: Int = 30
    private var audioBitrate: Int = 128
    private var videoBitrate: Int = 2500
    private var sampleRate: Int = 44100
    private var channel: Boolean = true
    private var echoCanceler: Boolean = true
    private var noiseSuppressor: Boolean = true

    var menuOpenTimer: Int = 0

    private var folder: File? = null
    private var currentDateAndTime = ""

    var timerHandler: Handler? = null
    var timerThread: Thread? = null
    var initTimer = 0
    var streamTime: Int = 0
    var streamComplete: Int = 0
    private var visibleTime: String = ""

    private val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private val permissionsA13 = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.POST_NOTIFICATIONS
    )

    private lateinit var binding: ActivityLiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions()

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        folder = PathUtils.getRecordPath()

        binding.surfaceViewN.holder.addCallback(this)
        binding.surfaceViewN.setOnTouchListener(this)
        rtmpCamera1 = RtmpCamera1(binding.surfaceViewN, this)

        makeHandler()

        rtmpip += streamKey

        setupListeners()
    }


    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasPermissions(this)) {
                ActivityCompat.requestPermissions(this, permissionsA13, 1)
            }
        } else {
            if (!hasPermissions(this)) {
                ActivityCompat.requestPermissions(this, permissions, 1)
            }
        }
    }

    private fun hasPermissions(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasPermissions(context, *permissionsA13)
        } else {
            hasPermissions(context, *permissions)
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context, permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    private fun setupListeners() {

        binding.broadcastLiveArea.setOnClickListener {
            if (!rtmpCamera1.isStreaming) {

                binding.bStartStop.setImageResource(R.drawable.stream_stop)
                binding.broadcastLiveArea.setBackgroundResource(R.drawable.stream_stop_back)

                binding.broadcastCloseArea.visibility = View.INVISIBLE


                val user = user
                val password = password
                if (user.isNotEmpty() && password.isNotEmpty()) {
                    rtmpCamera1.setAuthorization(user, password)
                }


                if (rtmpCamera1.isRecording || prepareEncoders()) {

                    rtmpCamera1.startStream(rtmpip)
                    streamComplete = 1
                    streamTimerThread()

                } else {
                    Toast.makeText(
                        this,
                        "Error preparing stream, This device cant do it",
                        Toast.LENGTH_SHORT
                    ).show()

                    binding.bStartStop.setImageResource(R.drawable.stream_start)
                    binding.broadcastLiveArea.setBackgroundResource(R.drawable.stream_start_back)

                    binding.broadcastCloseArea.visibility = View.VISIBLE

                    streamComplete = 0
                }
            } else {
                streamComplete = 0

                binding.bStartStop.setImageResource(R.drawable.stream_start)
                binding.broadcastLiveArea.setBackgroundResource(R.drawable.stream_start_back)

                binding.broadcastCloseArea.visibility = View.VISIBLE

                rtmpCamera1.stopStream()
            }
        }

        binding.mikeBtn.setOnClickListener {
            if (!rtmpCamera1.isAudioMuted) {
                binding.mikeBtn.setImageResource(R.drawable.mike_off)
                rtmpCamera1.disableAudio()
            } else {
                binding.mikeBtn.setImageResource(R.drawable.mike_on)
                rtmpCamera1.enableAudio()
            }
        }

        binding.switchCam.setOnClickListener {
            try {
                rtmpCamera1.switchCamera()
            } catch (e: CameraOpenException) {
                Toast.makeText(this@LiveActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.broadcastCloseArea.setOnClickListener {
            this.finish()
        }
    }

    override fun onAuthErrorRtmp() {
        runOnUiThread { Toast.makeText(this@LiveActivity, "Auth error", Toast.LENGTH_SHORT).show() }
    }

    override fun onAuthSuccessRtmp() {
        runOnUiThread {
            Toast.makeText(this@LiveActivity, "Auth success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConnectionFailedRtmp(reason: String) {
        runOnUiThread {


            Toast.makeText(this@LiveActivity, "Connection failed. $reason", Toast.LENGTH_SHORT)
                .show()
            rtmpCamera1.stopStream()

            binding.bStartStop.setImageResource(R.drawable.stream_start)
            binding.broadcastLiveArea.setBackgroundResource(R.drawable.stream_start_back)

            binding.broadcastCloseArea.visibility = View.VISIBLE

            rtmpCamera1.stopStream()

            streamComplete = 0

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && rtmpCamera1.isRecording) {
                rtmpCamera1.stopRecord()
                PathUtils.updateGallery(
                    applicationContext, folder!!.absolutePath + "/" + currentDateAndTime + ".mp4"
                )

                Toast.makeText(
                    this@LiveActivity,
                    "file " + currentDateAndTime + ".mp4 saved in " + folder!!.absolutePath,
                    Toast.LENGTH_SHORT
                ).show()
                currentDateAndTime = ""
            }
        }
    }

    override fun onConnectionStartedRtmp(rtmpUrl: String) {
    }

    override fun onConnectionSuccessRtmp() {
        runOnUiThread {

        }
    }

    override fun onDisconnectRtmp() {
        runOnUiThread {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && rtmpCamera1.isRecording) {
                rtmpCamera1.stopRecord()
                PathUtils.updateGallery(
                    applicationContext, folder!!.absolutePath + "/" + currentDateAndTime + ".mp4"
                )
                Toast.makeText(
                    this@LiveActivity,
                    "file " + currentDateAndTime + ".mp4 saved in " + folder!!.absolutePath,
                    Toast.LENGTH_SHORT
                ).show()
                currentDateAndTime = ""
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onNewBitrateRtmp(bitrate: Long) {
        runOnUiThread { binding.tvBitrate.text = "$bitrate bps" }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {}

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        rtmpCamera1.startPreview()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && rtmpCamera1.isRecording) {
            rtmpCamera1.stopRecord()
            PathUtils.updateGallery(this, folder!!.absolutePath + "/" + currentDateAndTime + ".mp4")

            Toast.makeText(
                this,
                "file " + currentDateAndTime + ".mp4 saved in " + folder!!.absolutePath,
                Toast.LENGTH_SHORT
            ).show()
            currentDateAndTime = ""
        }
        if (rtmpCamera1.isStreaming) {
            rtmpCamera1.stopStream()
            binding.bStartStop.setImageResource(R.drawable.stream_start)
            binding.broadcastLiveArea.setBackgroundResource(R.drawable.stream_start_back)

        }
        rtmpCamera1.stopPreview()
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        if (motionEvent != null) {
            val action: Int = motionEvent.action
            if (motionEvent.pointerCount > 1) {
                if (action == MotionEvent.ACTION_MOVE) {
                    rtmpCamera1.setZoom(motionEvent)
                }
            } else if (action == MotionEvent.ACTION_DOWN) {
                rtmpCamera1.tapToFocus(view, motionEvent)
            }
        }
        return true
    }

    private fun prepareEncoders(): Boolean {
        val resolution = rtmpCamera1.resolutionsBack[resolution]
        val width = resolution.width
        val height = resolution.height

        return rtmpCamera1.prepareVideo(
            width, height, fps, videoBitrate * 1024, CameraHelper.getCameraOrientation(this)
        ) && rtmpCamera1.prepareAudio(
            audioBitrate * 1024, sampleRate, channel, echoCanceler, noiseSuppressor
        )
    }

    private fun streamTimerThread() {
        timerThread = object : Thread() {
            override fun run() {
                while (true) {

                    try {
                        val msg: Message = timerHandler!!.obtainMessage()
                        if (streamComplete == 0) {
                            timerThread?.interrupt()

                            streamTime = initTimer

                            msg.what = 0
                            msg.arg1 = streamTime
                            timerHandler!!.sendMessage(msg)

                            if (isInterrupted) {
                                break
                            }
                        } else {
                            streamTime++

                            msg.what = 1
                            msg.arg1 = streamTime
                            msg.arg2 = streamComplete
                            timerHandler?.sendMessage(msg)
                            sleep(1000)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } catch (e: InterruptedException) {
                        Log.d("TAG_R", "InterruptedException Interrupt occurs")
                        Log.d("TAG_R", "InterruptedException Resource organizing")
                    }
                }
            }
        }
        (timerThread as Thread).start()
    }

    private fun makeHandler() {

        timerHandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == 1) {
                    if (msg.arg2 == 1) {
                        val timerVal: String = timerTrans(msg.arg1)
                        if (menuOpenTimer == 1) {
                        }
                    }
                } else {
                    val timerVal: String = timerTrans(msg.arg1)
                }
            }
        }
    }

    fun timerTrans(timerNum: Int): String {
        val hour: Int = timerNum / (60 * 60)
        val minute: Int = timerNum / 60 - (hour * 60)
        val second: Int = timerNum % 60


        var hourVal: String? = null
        var minuteVal: String? = null
        var secondVal: String? = null
        hourVal = if (hour.toString().length == 1) {
            "0${hour}"
        } else {
            hour.toString()
        }

        minuteVal = if (minute.toString().length == 1) {
            "0${minute}"
        } else {
            minute.toString()
        }

        secondVal = if (second.toString().length == 1) {
            "0${second}"
        } else {
            second.toString()
        }

        visibleTime = "$hourVal:$minuteVal:$secondVal"

        return "$hourVal:$minuteVal:$secondVal"
    }
}