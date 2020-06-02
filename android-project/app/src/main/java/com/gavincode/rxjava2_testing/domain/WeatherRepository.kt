package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single
import java.util.concurrent.locks.ReentrantLock

interface WeatherRepository {
    fun getRealtimeWeather(): Single<Weather>
}