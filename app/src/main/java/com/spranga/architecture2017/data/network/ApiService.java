package com.spranga.architecture2017.data.network;

import com.spranga.architecture2017.data.model.Post;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;

/**
 * Created by nietzsche on 02/04/17.
 */

public interface ApiService {

  @GET("posts") Single<List<Post>> getPosts();
}
