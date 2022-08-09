package com.example.itunessearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.itunessearch.di.DaggerViewModelComponent
import com.example.itunessearch.model.SearchResultResponse
import com.example.itunessearch.model.SearchItem
import com.example.itunessearch.model.SearchItemApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(application: Application):AndroidViewModel(application) {

    val searchItems by lazy{ MutableLiveData<List<SearchItem>>() }
    val loadError by lazy{ MutableLiveData<Boolean>() }
    val loading by lazy{ MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiService: SearchItemApiService //dagger lateinit!
    private val disposable = CompositeDisposable()

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun refresh(term: String, entity: String) {
        loading.value = true
        getSearchItems(term, entity)
    }

    private fun getSearchItems(term: String, entity: String) {
        disposable.add(
            apiService.getSearchItems(term, entity)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<SearchResultResponse>() {
                    override fun onSuccess(jsonObject: SearchResultResponse) {
                        loadError.value = false
                        searchItems.value = jsonObject.results
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}