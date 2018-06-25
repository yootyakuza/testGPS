package com.example.qq.testadmin1462561;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sarayut on 25/6/2561.
 */
public class DirectionFinder {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyBxl1qElea63wCFh6rhHBayaJpIj2S0jeM";
    private static final String TAG = "GoogleDirection";
    private Context context;
    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> List;

    public DirectionFinder(Context context) {
        this.context = context;
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Finding direction...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            List = new ArrayList<>();
            HttpHandler sh = new HttpHandler();
            String url = DIRECTION_URL_API + GOOGLE_API_KEY;
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonData = new JSONObject(jsonStr);
                    JSONArray jsonRoutes = jsonData.getJSONArray("routes");
                    for (int i = 0; i < jsonRoutes.length(); i++) {
                        JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
                        //Route route = new Route();

                        JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
                        JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
                        JSONObject jsonLeg = jsonLegs.getJSONObject(0);
                        JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
                        JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
                        JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
                        JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

                        String Distancetext = jsonDistance.getString("text");
                        String Distancevalue = String.valueOf(jsonDistance.getInt("value"));
                        String Durationtext = jsonDuration.getString("text");
                        String Durationvalue = String.valueOf(jsonDuration.getInt("value"));
                        String endAddress = jsonLeg.getString("end_address");
                        String startAddress = jsonLeg.getString("start_address");
                        String startLocationLat = String.valueOf(jsonStartLocation.getDouble("lat"));
                        String startLocationLng = String.valueOf(jsonStartLocation.getDouble("lng"));
                        String endLocationLat = String.valueOf(jsonEndLocation.getDouble("lat"));
                        String endLocationLng = String.valueOf(jsonEndLocation.getDouble("lng"));
                        String points = overview_polylineJson.getString("points");


                        HashMap<String, String> data = new HashMap<>();
                        data.put("Distancetext", Distancetext);
                        data.put("Distancevalue", Distancevalue);
                        data.put("Durationtext", Durationtext);
                        data.put("Durationvalue", Durationvalue);
                        data.put("endAddress", endAddress);
                        data.put("startAddress", startAddress);
                        data.put("startLocationLat", startLocationLat);
                        data.put("startLocationLng", startLocationLng);
                        data.put("endLocationLat", endLocationLat);
                        data.put("endLocationLng", endLocationLng);
                        data.put("points", points);
                        List.add(data);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
