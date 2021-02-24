package com.cos.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.movieapp.MainActivity;
import com.cos.movieapp.R;
import com.cos.movieapp.databinding.CardItemBinding;
import com.cos.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = "MovieAdapter";
    private List<Movie> movies = new ArrayList<>();

    private final MainActivity mContext;

    public MovieAdapter(MainActivity mContext) {
        this.mContext = mContext;
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public long getMovieId(int position){
        return movies.get(position).getId();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardItemBinding cardItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.card_item,
                parent,
                false);
        return new MovieViewHolder(cardItemBinding);
    }

    //card_item디자인이 연결
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //setItem을 호출하는 거이 아니라
        holder.cardItemBinding.setMovie(movies.get(position));  //xml에 코드 값 집어 넣음
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    //Card_Item 디자인이 연결
    class MovieViewHolder extends RecyclerView.ViewHolder {

        private CardItemBinding cardItemBinding;

        public MovieViewHolder(@NonNull CardItemBinding cardItemBinding) {
            super(cardItemBinding.getRoot());
            this.cardItemBinding = cardItemBinding;
        }
    }
}
