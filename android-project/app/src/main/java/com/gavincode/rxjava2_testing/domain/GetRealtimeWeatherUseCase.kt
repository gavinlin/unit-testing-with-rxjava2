package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(): Single<Weather> {
        return weatherRepository.getRealtimeWeather()
            .subscribeOn(Schedulers.io())
    }
}