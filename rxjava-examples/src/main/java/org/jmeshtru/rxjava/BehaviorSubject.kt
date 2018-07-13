package org.jmeshtru.rxjava

import rx.Observable
import rx.subjects.BehaviorSubject

object BehaviorSubject {
    @JvmStatic
    fun main(args: Array<String>) {
        val subject = BehaviorSubject.create<Int>()
        subject.onNext(1)
        subject.onCompleted()
        subject.subscribe({it -> println(it)}, {_ -> println("error")}, {println("completed")})

        val intArray = arrayOf(1, 2, 4)
        Observable.from(intArray).subscribe  { it -> println(it)}
    }
}