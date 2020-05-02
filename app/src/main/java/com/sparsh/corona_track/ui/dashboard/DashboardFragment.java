package com.sparsh.corona_track.ui.dashboard;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sparsh.corona_track.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {
    String country_name = null;
   ImageView imageViewflag;
    private static final int REQUEST_PERMISSION = 1;

    private TextView cases, todaycases, deaths, todaydeaths, recovered, active, critical, casespermillion, deathspermillion, tests, testpermillion, locationplace;
    private ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //call view
        getActivity().findViewById(R.id.titlelogo).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.logoimage).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.abouticon).setVisibility(View.VISIBLE);

         imageViewflag=root.findViewById(R.id.flaglocation);


        cases = root.findViewById(R.id.casesindiaid2value);
        todaycases = root.findViewById(R.id.todaycasesindiaid2value);
        deaths = root.findViewById(R.id.deathsindiaid2value);
        todaydeaths = root.findViewById(R.id.todaydeathid2value);
        recovered = root.findViewById(R.id.recoveredindiaidvalue);
        active = root.findViewById(R.id.activeindiaid2value);
        critical = root.findViewById(R.id.criticalindiaid2value);
        casespermillion = root.findViewById(R.id.casespermillionindiaid2value);
        deathspermillion = root.findViewById(R.id.deathpermillionindiaid2value);
        tests = root.findViewById(R.id.testsindaiidvalue);
        locationplace=root.findViewById(R.id.locationplace);


        progressBar = root.findViewById(R.id.progressBar3);
        //call volley
        if (ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION
            );
        } else {

         getlocation();
        }

        return root;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 getlocation();
            } else {
                Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Main", "Im Resume baby");
        if (ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED) {
            getlocation();
           getData();
        }
    }



 public  void  getlocation(){

     LocationManager lm = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
     Geocoder geocoder = new Geocoder(getActivity());
     for(String provider: lm.getAllProviders()) {
         @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
         if(location!=null) {
             try {
                 List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                 if(addresses != null && addresses.size() > 0) {
                     country_name = addresses.get(0).getCountryName();
                     Log.d("country name:",country_name);
                     break;
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }

 }

    public void getData() {
        Toast.makeText(getActivity(), country_name+" Report", Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://disease.sh/v2/countries/"+country_name;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    cases.setText(jsonObject.getString("cases"));
                    todaycases.setText(jsonObject.getString("todayCases"));
                    deaths.setText(jsonObject.getString("deaths"));
                    todaydeaths.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    active.setText(jsonObject.getString("active"));
                    critical.setText(jsonObject.getString("critical"));
                    casespermillion.setText(jsonObject.getString("casesPerOneMillion"));
                    deathspermillion.setText(jsonObject.getString("deathsPerOneMillion"));
                    tests.setText(jsonObject.getString("tests"));
                    locationplace.setText(country_name);


                      JSONObject jsonObject1 = jsonObject.getJSONObject("countryInfo");
                      Log.d("image url", jsonObject1.getString("flag"));

                    Picasso.get().load(jsonObject1.getString("flag")).into(imageViewflag);
                } catch (JSONException  e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("error", error.toString());
            }

        });

        queue.add(stringRequest);

    }

}
