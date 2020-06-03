package com.gavincode.rxjava2_testing.domain

import java.lang.Exception

sealed class UseCaseResult<out R> {
    class Success<out T> (val value: T): UseCaseResult<T>()
    class Failure(val throwable: Throwable): UseCaseResult<Nothing>()
}