package com.lucas.mycontacts

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.lucas.mycontacts.model.Resource
import java.util.*

/**
 * Created by lucas on 14/11/2017.
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
        internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it!!)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { setValue(Resource.success(it)) }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (!Objects.equals(result.value, newValue)) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
    }

    protected open fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> = result

    @WorkerThread
    protected abstract fun saveCallResult(item: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType): Boolean

    @MainThread
    protected abstract fun  loadFromDb(): LiveData<ResultType>
}