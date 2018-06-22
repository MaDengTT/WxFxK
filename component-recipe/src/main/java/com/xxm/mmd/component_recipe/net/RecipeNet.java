package com.xxm.mmd.component_recipe.net;



import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by MaDeng on 2018/6/22.
 */
public interface RecipeNet {
    @GET("www.baidu.com")
    Observable<String> getBaiDuString();
}
