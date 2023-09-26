package com.common.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Event Bus is used for publishing and listening any events in the Fragments or Activity.
 *
 * Usage of Event Bus:
 * =========================================
 * Listen Event:-
 *
 * Inside Activity:
 *
 *      lifecycleScope.launch {
 *          EventBus.listen<Intent> { intent ->
 *              if(intent.action == this::class.java.simpleName)  ==> To check whether the action is for specific
 *              activity/fragment
 *              //Do your stuff with the received intent and update your activity/fragment
 *          }
 *      }
 *
 * Inside ViewModel:
 *
 *      viewModelScope.launch {
 *          EventBus.listen<String> { yourString ->
 *              //Do your stuff with the string
 *          }
 *      }
 *
 *      Other Ex:-
 *      EventBus.listen<Int> { yourInt -> }
 *      EventBus.listen<String> { yourString -> }
 *      EventBus.listen<YourModel> { yourModel -> }
 *
 * =========================================
 * Publish Event:-
 *
 *      lifecycleScope.launch {
 *          val intent = Intent(MainActivity::class.java.simpleName)
 *          intent.putExtra(EXTRAS_REFRESH_MY_GROUPS, EXTRAS_REFRESH_MY_GROUPS)
 *          EventBus.publish(intent)
 *      }
 *
 *      Other Ex:
 *      EventBus.publish(yourInt)
 *      EventBus.publish(yourString)
 *      EventBus.publish(yourModel)
 * =========================================
 * */

object EventBus {

    val flow = MutableSharedFlow<Any>()

    suspend fun <TYPE> publish(event: TYPE) {
        flow.emit(event as Any)
    }

    suspend inline fun <reified TYPE> listen(collector: FlowCollector<TYPE>) {
        flow.filterIsInstance<TYPE>().collect(collector)
    }

    fun <TYPE> ViewModel.publishEvent(event: TYPE) {
        viewModelScope.launch { publish(event) }
    }

    fun <TYPE> AppCompatActivity.publishEvent(
        event: TYPE,
        context: CoroutineContext = Dispatchers.IO
    ) {
        lifecycleScope.launch(context) { publish(event) }
    }

    inline fun <reified TYPE> ViewModel.listenEvent(collector: FlowCollector<TYPE>) {
        viewModelScope.launch { listen(collector) }

    }

    inline fun <reified TYPE> AppCompatActivity.listenEvent(
        context: CoroutineContext = Dispatchers.IO,
        collector: FlowCollector<TYPE>,
    ) {
        lifecycleScope.launch(context) { listen(collector) }
    }
}