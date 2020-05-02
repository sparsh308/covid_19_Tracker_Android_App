package com.sparsh.corona_track.ui.India;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparsh.corona_track.R;

public class IndiaFragment extends Fragment {

    private WebView webView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_india, container, false);
        webView = root.findViewById(R.id.webviewid);

        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        Toast.makeText(getActivity(), "Loading India Data", Toast.LENGTH_SHORT).show();
       webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //Fun Part will be here :)
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('fadeInUp logo')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('last-update')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('navbar-toggle')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('titles')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('Navbar')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('button')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('link')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('button github')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('button excel')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('button twitter')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('button telegram')[0].style.display='none'; " +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('Banner fadeInUp')[0].style.display='none'; " +
                        "})()");

            }
                                 }
        );

        webView.loadUrl("https://bit.ly/3a8mZkC");

        return root;
    }
}
