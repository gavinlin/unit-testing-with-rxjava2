package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single

class GetRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(): Single<UseCaseResult<Weather>> {
        return weatherRepository.getRealtimeWeather()
            .map {
                UseCaseResult.Success(it)
            }
    }
}