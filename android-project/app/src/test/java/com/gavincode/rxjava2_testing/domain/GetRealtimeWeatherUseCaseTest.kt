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

@RunWith(MockitoJUnitRunner::class)
class GetRealtimeWeatherUseCaseTest {

    companion object{
        private val SAMPLE_WEATHER  = Weather(10.0)
    }

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
        val testObserver = TestObserver<Weather>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.just(SAMPLE_WEATHER))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it == SAMPLE_WEATHER }
    }

    @Test
    fun `Get Realtime Weather then Failure Result Returned`() {
        val testObserver = TestObserver<Weather>()
        `when`(weatherRepository.getRealtimeWeather()).thenReturn(Single.error(Throwable("Custom Error")))

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertError { it.message == "Custom Error" }
    }
}