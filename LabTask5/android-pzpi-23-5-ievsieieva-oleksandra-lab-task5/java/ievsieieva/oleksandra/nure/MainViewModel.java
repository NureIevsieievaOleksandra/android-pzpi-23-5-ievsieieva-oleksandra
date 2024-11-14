package ievsieieva.oleksandra.nure;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Boolean> isSearchVisible = new MutableLiveData<>(false);
    public MutableLiveData<FilterOrder> filterOrder = new MutableLiveData<>(FilterOrder.DESC);

    public enum FilterOrder {
        ASC, DESC
    }
}
