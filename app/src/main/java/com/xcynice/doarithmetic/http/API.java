package com.xcynice.doarithmetic.http;


import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.util.XUtil;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description : API
 * 接口的管理类
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class API {

    static final String BASE_URL = XUtil.getApplication().getString(R.string.baseUrl);

    @SuppressWarnings("AlibabaClassNamingShouldBeCamel")
    public interface IWAZApi {

    }

}
