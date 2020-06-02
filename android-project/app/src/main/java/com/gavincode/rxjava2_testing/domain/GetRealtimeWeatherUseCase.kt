package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetRealtimeWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(): Single<UseCaseResult<Weather>> {
        return weatherRepository.getRealtimeWeather()
            .subscribeOn(Schedulers.io())
            .map {
                UseCaseResult.Success(it) as UseCaseResult<Weather>
            }
            .onErrorResumeNext {
                Single.just(UseCaseResult.Failure<Weather>(it) as UseCaseResult<Weather>)
            }
    }
}