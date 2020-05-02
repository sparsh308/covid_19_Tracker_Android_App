package com.sparsh.corona_track.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sparsh.corona_track.R;

import org.json.JSONException;
import org.json.JSONObject;


    public class HomeFragment extends Fragment {

   private TextView totalconfirmed,totaldeath,totalrecovered;
   private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
   //call view
        getActivity().findViewById(R.id.titlelogo).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.logoimage).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.abouticon).setVisibility(View.VISIBLE);
        totalconfirmed=root.findViewById(R.id.totalcasesvalueid);
        totaldeath=root.findViewById(R.id.totaldeathid);
        totalrecovered=root.findViewById(R.id.totalrecoverdid);
        progressBar=root.findViewById(R.id.progressBar);
        //call volley
        getData();
        return root;
    }

    private void getData() {

        RequestQueue queue= Volley.newRequestQueue(getActivity());
        String url="https://disease.sh/v2/all";
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    totalconfirmed.setText(jsonObject.getString("cases"));
                    totaldeath.setText(jsonObject.getString("deaths"));
                    totalrecovered.setText(jsonObject.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              progressBar.setVisibility(View.GONE);
                Log.d("error",error.toString());
            }

        });

        queue.add(stringRequest);

    }
}
