package com.sparsh.corona_track.ui.world;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sparsh.corona_track.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CovidworldAdapter extends RecyclerView.Adapter<CovidworldAdapter.ViewHolder> {

    ArrayList<Covidworld> covidCountries;
    private Context context;

    public CovidworldAdapter(ArrayList<Covidworld> covidCountries, Context context){
        this.covidCountries = covidCountries;
        this.context = context;
    }

    @NonNull
    @Override
    public CovidworldAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_world, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidworldAdapter.ViewHolder holder, int position) {
        Covidworld covidworld = covidCountries.get(position);
        holder.tvTotalCases.setText(covidworld.getmCases());
        holder.tvCountryName.setText(covidworld.getmCovidCountry());

        // Glide
        Glide.with(context)
                .load(covidworld.getmFlags())
                .apply(new RequestOptions().override(240, 160))
                .into(holder.imgCountryFlag);
    }

    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases, tvCountryName;
        ImageView imgCountryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            imgCountryFlag = itemView.findViewById(R.id.imgCountryFlag);
        }
    }
}
