package com.example.myproject2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myproject2.Models.LocationModel;
import com.example.myproject2.Models.Message;
import com.example.myproject2.Rertofit.ApiClient;
import com.example.myproject2.Rertofit.ApiInterface;
import com.example.myproject2.room.MessageRepository;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    
    int userid;

    SessionLoginManager sessionLoginManager;

    ApiInterface apiInterface;

    static MainActivity instance;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;

    Message item;
    MessageRepository repo;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionLoginManager = new SessionLoginManager(this);
        sessionLoginManager.checkLogin();

        userid = sessionLoginManager.getUserID();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        instance = this;

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if(sessionLoginManager.isLoggin()){
                            updateLocation();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "You must accept this locaiton.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        /*locationRequest = new LocationRequest();
        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                onLocationChanges(locationResult.getLastLocation());
            }
        };
        startLocationUpdates();
        updateGPS();*/

        /*googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) MainActivity.this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) MainActivity.this)
                .build();*/

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    private void updateLocation() {
        buildLocationRequest();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, MyLocationService.class);
        intent.setAction(MyLocationService.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(this, 0 ,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(10f);
    }

    public void updateLocationText(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_person:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_message:
                            selectedFragment = new MessageFragment();
                            break;
                        case R.id.nav_list:
                            selectedFragment = new TodoFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    /*@Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED 
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location != null) {
            locationuser();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }*/

    public void locationuser(double latitude, double longitude) {
        Call<LocationModel> callLocation = apiInterface.locationUser(userid,
                latitude,
                longitude);

        callLocation.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    LocationModel locationModel = response.body();
                    if(locationModel!=null) {
                        Toast.makeText(MainActivity.this,
                                locationModel.getMessages()+" id:"+userid+", "+locationModel.getStatus()+", "+locationModel.getHospitalname()+", "+locationModel.getIdRequest(),
                                Toast.LENGTH_SHORT).show();
                        if(locationModel.getStatus() > 0) {
                            item = new Message(locationModel.getHospitalname(), locationModel.getIdRequest());
                            repo = new MessageRepository(MainActivity.this);
                            repo.findRequest(locationModel.getHospitalname(), new MessageRepository.FindCallback() {
                                @Override
                                public void onGet(List<Message> itemList) {
                                    if(itemList.isEmpty()) {
                                        repo.insertMessage(item, new MessageRepository.InsertCallback() {
                                            @Override
                                            public void onInsertSuccess() {
                                                Toast.makeText(
                                                        MainActivity.this,
                                                        "Insert Hospital Name Success",
                                                        Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        });
                                    }
                                    else {
                                        repo.updateMessage(locationModel.getIdRequest(), locationModel.getHospitalname(), new MessageRepository.UpdateCallback() {
                                            @Override
                                            public void onUpdateSuccess() {
                                                Toast.makeText(
                                                        MainActivity.this,
                                                        "Update IdRequest Success",
                                                        Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Could not update location", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error because "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionLoginManager.logout();
                fusedLocationProviderClient.removeLocationUpdates(getPendingIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        updateGPS();
    }*/

   /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else {
                    Toast.makeText(this, "This app requies permission to be granted is order to work properly", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }*/

    /*private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    onLocationChanges(location);
                }
            });
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }*/

    /*private void onLocationChanges(Location location) {
        Log.i("MainActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
        UserModel userModel = new UserModel();
        Call<LocationModel> callLocation = apiInterface.locationUser(userModel.getUserId(),
                location.getLatitude(),
                location.getLongitude());

        callLocation.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    LocationModel locationModel = response.body();

                    if(locationModel.isSuccesses()) {
                        Toast.makeText(MainActivity.this, "update success ", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Could not update location", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error because "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/
}