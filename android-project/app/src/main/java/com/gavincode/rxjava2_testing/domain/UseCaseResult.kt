package com.gavincode.rxjava2_testing.domain

import java.lang.Exception

sealed class UseCaseResult<T> {
    class Success<T> (value: T): UseCaseResult<T>()
    class Failure<T>(throwable: Throwable): UseCaseResult<T>()
}