package co.id.katalogfilmkioser.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Sainal Sultan on 6/18/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
object Coroutines {

    fun<T:Any> ioTheMain(work: suspend(() ->T?), callback: ((T?)->Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()
            callback(data)
        }

}