package com.gavincode.rxjava2_testing.domain

import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GetRealtimeWeatherUseCaseTest {

    lateinit var sut: GetRealtimeWeatherUseCase

    @Before
    fun setUp() {
        sut = GetRealtimeWeatherUseCase()
    }

    @Test
    fun `test Get Realtime Weather With Success Result`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Success }
    }

    @Test
    fun `test Get Realtime Weather With Failure Result`() {
        val testObserver = TestObserver<UseCaseResult<Weather>>()

        sut.execute()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { it is UseCaseResult.Failure }
    }
}