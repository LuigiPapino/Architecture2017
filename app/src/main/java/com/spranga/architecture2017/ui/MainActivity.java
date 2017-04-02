package com.spranga.architecture2017.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.spranga.architecture2017.R;
import com.spranga.architecture2017.data.model.Post;
import com.spranga.architecture2017.presenter.MainPresenter;
import dagger.android.AndroidInjection;
import java.util.List;
import javax.inject.Inject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity {

  @ViewById Toolbar toolbar;

  @Inject MainPresenter presenter;

  @AfterViews void setup() {
    AndroidInjection.inject(this);
    setSupportActionBar(toolbar);

    presenter.init(this);
  }

  @Click void fab() {
    presenter.click();
  }

  public void showError(String message) {
    new AlertDialog.Builder(this).setTitle("Error").setMessage(message).show();
  }

  public void showPosts(@NonNull List<Post> posts) {
    new AlertDialog.Builder(this).setTitle("Success")
        .setMessage(String.format("%d posts downloaded", posts.size()))
        .show();
  }
}
