package com.laioffer.tinnews.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class SearchViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> searchInput = new MutableLiveData<>();

    public SearchViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }

    public LiveData<NewsResponse> searchNews() {
        /***
         * transformation.switchMap用途是
         * 当searchInput里面的内容发生变化时
         * 执行repository 的 searchNews 方法
         * 相当于一直监听input，一旦input发生变化，则执行对应操作
         */
        return Transformations.switchMap(searchInput, repository::searchNews);
    }
}
