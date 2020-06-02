package com.gavincode.rxjava2_testing.domain

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRealtimeWeatherUseCaseTest {

    lateinit var sut: GetRealtimeWeatherUseCase

    @Mock
    lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        sut = GetRealtimeWeatherUseCase(weatherRepository)
    }

    @Test
    fun `test Get Realtime Weather With Success Result`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.just(Weather(10.0)))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Success }
    }

    @Test
    fun `test Get Realtime Weather With Failure Result`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.error(Throwable()))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Failure }
    }
}