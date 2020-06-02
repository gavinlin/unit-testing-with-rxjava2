package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single

class GetRealtimeWeatherUseCase {

    fun execute(): Single<UseCaseResult<Weather>> {
        return Single.just(UseCaseResult.Success(Weather(1.0)))
    }
}