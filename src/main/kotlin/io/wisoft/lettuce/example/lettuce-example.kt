package io.wisoft.lettuce.example

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.RedisClient
import io.lettuce.core.TransactionResult
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.async.multi
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalLettuceCoroutinesApi::class)
fun main() = runBlocking {
    val client: RedisClient = RedisClient.create("redis://localhost:6379/")
    val connection:  StatefulRedisConnection<String, String> = client.connect()
    val api: RedisCoroutinesCommands<String, String> = connection.coroutines()

    api.set("foo", "bar")
    val foo: Flow<String> = api.keys("fo*")
    println(foo.first())

//    val result: TransactionResult = connection.async().multi {
//        set("foo", "bar")
//        get("foo")
//    }
//    println(result)
}
