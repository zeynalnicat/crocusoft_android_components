package com.example.crocusoft_android_components.presentation.music

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.drawable.Icon
import android.os.BatteryManager
import android.os.IBinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.registerReceiver
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.crocusoft_android_components.R
import com.example.crocusoft_android_components.service.MusicService
import com.example.crocusoft_android_components.utils.BatteryBroadcastReceiver
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MusicContent(
    innerPaddingValues: PaddingValues,
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.wave_animation))

    val progressAnimation by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    var showAnimation by remember { mutableStateOf(false) }

    val context = LocalContext.current

    var musicService by remember { mutableStateOf<MusicService?>(null) }
    var isBound by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val connection = remember {
        object : ServiceConnection {

            override fun onServiceConnected(
                name: ComponentName?,
                service: IBinder?
            ) {
                val binder = service as MusicService.MusicBinder
                musicService = binder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
                musicService = null
            }
        }
    }




    LaunchedEffect(Unit) {

        val batteryIntent =
            context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)?:-1
        val scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE,-1)?:-1

        val batterPct = level*100 / scale.toFloat()

        showAnimation = batterPct>20

        val intent = Intent(context, MusicService::class.java)

        ContextCompat.startForegroundService(context, intent)

        context.bindService(
            intent,
            connection,
            Context.BIND_AUTO_CREATE
        )
    }

    DisposableEffect(Unit) {

        val receiver = BatteryBroadcastReceiver(
            onBatteryOkay = { showAnimation = true },
            onLowBattery = { showAnimation = false }
        )

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            if (isBound) {
                context.unbindService(connection)
            }
        }
    }

    LaunchedEffect(musicService) {

        while (true) {

            musicService?.let {

                val current = it.getCurrentPosition()
                val duration = it.getDuration()

                if (duration > 0) {
                    progress = current.toFloat() / duration
                }
            }

            delay(500)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .padding(16.dp)
            .verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            composition = composition,
            progress = {
                if (progress > 0f && isPlaying && showAnimation) progressAnimation
                else 0f
            }
        )


        Spacer(modifier = Modifier.height(16.dp))


        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            IconButton(
                onClick = {
                    if (!isPlaying) {
                        musicService?.startMusic()
                    } else {
                        musicService?.stopMusic()
                    }
                    isPlaying = !isPlaying
                }
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.PauseCircle else Icons.Default.PlayCircle,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                )
            }

        }

    }
}