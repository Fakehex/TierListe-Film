package com.example.tierliste3.Controllers.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private Context mContext;
    ArrayList<Film> list;

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void send_details(Film movie, int position);
    }
    public static final float POSTER_ASPECT_RATIO = 1.5f;
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";

    public FilmAdapter(Context context, ArrayList<Film> movieList, OnItemClickListener mItemClickListener) {
        this.mContext = context;
        this.list = movieList;
        this.mOnItemClickListener = mItemClickListener;

    }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        int layoutForMovieItem = R.layout.movie_item;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.movie_item, parent, shouldAttachToParentImmediately);
        final Context context = view.getContext();

        int gridColsNumber = context.getResources()
                .getInteger(R.integer.number_of_grid_columns);

        view.getLayoutParams().height = (int) (parent.getWidth() / gridColsNumber *
                POSTER_ASPECT_RATIO);


        FilmViewHolder viewHolder = new FilmViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final FilmViewHolder holder, int position) {
        final Film movie = list.get(position);
        final Context context = holder.mView.getContext();

        holder.mMovie = movie;
        holder.mMovietitle.setText(movie.getOriginalTitle());

        String posterUrl = movie.getPosterPath();

        // Warning: onError() will not be called, if url is null.
        // Empty url leads to app crash.
        if (posterUrl == null) {
            holder.mMovietitle.setVisibility(View.VISIBLE);
        }

        Picasso.get()
                .load(movie.getPosterPath())
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.mMovieThumbnail,
                        new Callback() {
                            @Override
                            public void onSuccess() {
                                if (holder.mMovie.getId() != movie.getId()) {
                                    holder.cleanUp();
                                } else {
                                    holder.mMovieThumbnail.setVisibility(View.VISIBLE);
                                }
                            }
                            @Override
                            public void onError(Exception e) {
                                Log.i("MOVIEADAPTER","--------- "+e.getMessage()+" ----------\n");
                                holder.mMovietitle.setVisibility(View.VISIBLE);
                            }
                        }
                );

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.send_details(movie,holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewRecycled(FilmViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
    }
    //Inner Class
    public class FilmViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public Film mMovie;

        @BindView(R.id.movie_thumbnail)
        ImageView mMovieThumbnail;

        @BindView(R.id.movie_title)
        TextView mMovietitle;

        public FilmViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }
        //Other methods
        public void cleanUp() {
            final Context context = mView.getContext();
            Picasso.get().cancelRequest(mMovieThumbnail);
            mMovieThumbnail.setImageBitmap(null);
            mMovieThumbnail.setVisibility(View.INVISIBLE);
            mMovietitle.setVisibility(View.GONE);
        }

    }



    @Override
    public long getItemId(int position) {
        return position;
    }


    public void add(ArrayList<Film> movies) {
        list.clear();
        list.addAll(movies);
        notifyDataSetChanged();
    }
    public ArrayList<Film> getMovies() {
        return list;
    }
}
