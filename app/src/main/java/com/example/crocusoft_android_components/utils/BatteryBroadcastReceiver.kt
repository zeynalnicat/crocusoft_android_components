package com.example.crocusoft_android_components.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryBroadcastReceiver(
    private val onLowBattery: () -> Unit,
    private val onBatteryOkay: () -> Unit
): BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when(p1?.action){
            Intent.ACTION_BATTERY_LOW -> {
                onLowBattery()
            }
            Intent.ACTION_BATTERY_OKAY -> {
                onBatteryOkay()
            }
        }

    }
}