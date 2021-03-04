package com.example.android_arch.kotlin

class Ext {
    private fun <T, R> T.doWithTry(block: T.() -> R): R {
        return block.invoke(this)
    }

    fun test(){
        print("hello".doWithTry {
            this.length
        })
    }
}