package com.gavincode.rxjava2_testing.domain

import com.gavincode.rxjava2_testing.util.RxJavaAsyncTestRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.locks.LockSupport

@RunWith(MockitoJUnitRunner::class)
class GetRealtimeWeatherUseCaseTest {

    lateinit var sut: GetRealtimeWeatherUseCase

    @Rule
    @JvmField
    val rxJavaAsyncTestRule = RxJavaAsyncTestRule()

    @Mock
    lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        sut = GetRealtimeWeatherUseCase(weatherRepository)
    }

    @Test
    fun `Get Realtime Weather then Success Result Returned`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.just(Weather(10.0)))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Success }
    }

    @Test
    fun `Get Realtime Weather then Failure Result Returned`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.error(Throwable("Custom Error")))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Failure && it.throwable.message == "Custom Error"}
    }
}