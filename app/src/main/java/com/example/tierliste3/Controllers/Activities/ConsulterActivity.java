package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsulterActivity extends AppCompatActivity implements FilmAdapter.OnItemClickListener{
    private static final String TAG = "ConsulterActivity";

    @BindView(R.id.recycled_movie_grid)
    RecyclerView movie_grid_recyclerView;

    ArrayList<Film> mPopularList;
    ArrayList<Film> mTopTopRatedList;

    private FilmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        mPopularList = (ArrayList<Film>) intent.getSerializableExtra("mPopularList");
        mTopTopRatedList = (ArrayList<Film>) intent.getSerializableExtra("mTopTopRatedList");

        movie_grid_recyclerView.setLayoutManager(new GridLayoutManager(this, getResources()
                .getInteger(R.integer.number_of_grid_columns)));
        mAdapter = new FilmAdapter(ConsulterActivity.this,new ArrayList<Film>(), ConsulterActivity.this);
        mAdapter.add(mPopularList);
        movie_grid_recyclerView.setAdapter(mAdapter);
    }


    public void send_details(Film movie, int position) {
            Intent intent = new Intent(this, FilmDetailActivity.class);
            intent.putExtra("detail", movie);
            startActivity(intent);
        }
}

