package blacklinden.com.roomapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class AdatViewModel extends AndroidViewModel {

    private AdatRepo repo;
    private LiveData<List<Adat>> mAllWords;

    public AdatViewModel(@NonNull Application application) {
        super(application);
        repo = new AdatRepo(application);
        mAllWords = repo.getAllWords();
    }

    LiveData<List<Adat>> getAllWords(){
        return mAllWords;
    }

    void insert(Adat adat){repo.insert(adat);}

}
