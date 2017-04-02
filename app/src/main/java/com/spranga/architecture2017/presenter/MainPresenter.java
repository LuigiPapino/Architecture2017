package com.spranga.architecture2017.presenter;

import com.spranga.architecture2017.data.model.Post;
import com.spranga.architecture2017.data.network.ApiService;
import com.spranga.architecture2017.ui.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by nietzsche on 02/04/17.
 */

public class MainPresenter {

  ApiService apiService;

  MainActivity view;

  @Inject public MainPresenter(ApiService apiService) {
    this.apiService = apiService;
  }

  public void init(MainActivity view) {
    this.view = view;
  }

  public void click() {

    apiService.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::doSomething, this::onError);
  }

  private void onError(Throwable throwable) {
    view.showError(throwable.getMessage());
  }

  private void doSomething(List<Post> posts) {
    view.showPosts(posts);
  }
}
