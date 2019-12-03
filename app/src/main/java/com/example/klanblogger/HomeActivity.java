package com.example.klanblogger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.klanblogger.ReadingList.ReadingListFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth auth;
    private FirebaseFirestore databaseReference;
    String name = "Profile name";
    String email = "Profile email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = databaseReference.collection("users");
        Query userDetailQuery = collectionReference.whereEqualTo("id", auth.getCurrentUser().getUid());
        userDetailQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        User user = document.toObject(User.class);
                        name = user.getName();
                        email = user.getEmail();
                    }
                }
            }
        });

        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        TextView profileName = (TextView) findViewById(R.id.drawer_profile_name);

        if (auth.getCurrentUser() == null) {

            Log.e("AUHTH KHAAALO", "onCreate: " );
        }
        if (profileName == null )
        {
            Log.e("KHAAALO", "onCreate: " );
            Log.e("onCreate:", name+" "+email+auth.getCurrentUser().getUid() );

        } else {
            profileName.setText(name);
        }

        TextView profileEmail = (TextView) findViewById(R.id.drawer_profile_email);
//        profileName.setText(email);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_reading_list, R.id.nav_new_story, R.id.nav_stats, R.id.nav_stories, R.id.nav_settings, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
                break;
            case R.id.nav_reading_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ReadingListFragment()).commit();
                break;
            case R.id.nav_new_story:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new NewStoryFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new BlogFragment()).commit();
                break;
            case R.id.nav_stories:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new StoriesFragment()).commit();
                break;
            case R.id.nav_logout: {
                Log.e("HERE", "onNavigationItemSelected: ");
                Toast.makeText(getApplicationContext(), "LOG OUT!", Toast.LENGTH_SHORT).show();
                auth.signOut();
                finish();
                startActivity(new Intent(this, SignInActivity.class));
            }
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
                break;

        }

        mAppBarConfiguration.getDrawerLayout().closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        assert mAppBarConfiguration.getDrawerLayout() != null;
        if (mAppBarConfiguration.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            mAppBarConfiguration.getDrawerLayout().closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
