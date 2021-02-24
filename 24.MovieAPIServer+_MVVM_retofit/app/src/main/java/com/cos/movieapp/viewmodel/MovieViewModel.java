package com.cos.movieapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.movieapp.MainActivity;
import com.cos.movieapp.model.Movie;
import com.cos.movieapp.model.ResponseDto;
import com.cos.movieapp.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private static final String TAG = "MovieViewModel";

    private MutableLiveData<List<Movie>> mtMovie = new MutableLiveData<>();

    public MovieViewModel() {
        List<Movie> movies = new ArrayList<>();
        mtMovie.setValue(movies);
    }

    public MutableLiveData<List<Movie>> subscribe(){
        return mtMovie;
    }

    //전체 가져오기, 삭제하기
    public void findAll(){

        Call<List<Movie>> call = MovieService.retrofit.create(MovieService.class).findAll();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                //ViewModel 건드릴 것
                List<Movie> movies = response.body();
                mtMovie.setValue(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public void deleteById(final long id){

        Call<ResponseDto> call = MovieService.retrofit.create(MovieService.class).deleteById(id);
        call.enqueue(new Callback<ResponseDto<?>>() {
            @Override
            public void onResponse(Call<ResponseDto<?>> call, Response<ResponseDto<?>> response) {
                Log.d(TAG, "onResponse: response"+response);
                ResponseDto result = response.body();
                Log.d(TAG, "onResponse: result"+result);

                if(result.getStatusCode()==1){
                    List<Movie> movie
                }
            }

            @Override
            public void onFailure(Call<ResponseDto<?>> call, Throwable t) {

            }
        });
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String msg = response.body();
                if(msg.equals("ok")){
                    List<Movie> movies = mtMovie.getValue();
                    for (int i =0;i<movies.size();i++){
                        if(movies.get(i).getId() == id){
                            movies.remove(i);
                            break;
                        }
                    }
                    mtMovie.setValue(movies);
                }else{
                    Log.d(TAG, "onResponse: 삭제실패");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
