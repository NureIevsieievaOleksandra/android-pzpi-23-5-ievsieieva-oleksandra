package ievsieieva.oleksandra.nure;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import ievsieieva.oleksandra.nure.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private NavController navController;
    private ActivityMainBinding binding;
    Toolbar toolbar;
    public MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Fragment navHost = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        navController = NavHostFragment.findNavController(navHost);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.fragmentList);
        setSupportActionBar(toolbar);
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            switch (navDestination.getId()) {
                case R.id.noteListFragment:
                    toolbar.setTitle(R.string.fragmentList);
                    break;
                case R.id.noteEditFragment:
                    toolbar.setTitle(R.string.fragmentEdit);
                    break;
                case R.id.galleryFragment:
                    toolbar.setTitle(R.string.fragemntGallery);
                    break;
                case R.id.noteDetailsFragment:
                    toolbar.setTitle(R.string.fragmentDetails);
            }
        });
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                navController.navigate(NavGraphDirections.actionToNoteEditFragment(null));
                break;
            case R.id.search:
                viewModel.isSearchVisible.postValue(!viewModel.isSearchVisible.getValue());
                break;
            case R.id.filter:
                if(viewModel.filterOrder.getValue()== MainViewModel.FilterOrder.DESC) {
                    viewModel.filterOrder.postValue(MainViewModel.FilterOrder.ASC);
                } else {
                    viewModel.filterOrder.postValue(MainViewModel.FilterOrder.DESC);
                }
                break;
        }
        return false;
    }
}

