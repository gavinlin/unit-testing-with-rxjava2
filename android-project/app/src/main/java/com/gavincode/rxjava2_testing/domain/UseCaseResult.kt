package com.gavincode.rxjava2_testing.domain

import java.lang.Exception

sealed class UseCaseResult<T> {
    class Success<T> (val value: T): UseCaseResult<T>()
    class Failure<T>(val throwable: Throwable): UseCaseResult<T>()
}