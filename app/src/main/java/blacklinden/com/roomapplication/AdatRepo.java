package blacklinden.com.roomapplication;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class AdatRepo {

    private AdatDao adatDao;
    private LiveData<List<Adat>> mAllWords;

    AdatRepo(Application application){
        AdatRoomDatabase db = AdatRoomDatabase.getDatabase(application);
        adatDao = db.adatDao();
        mAllWords = adatDao.getAllWords();
    }

    LiveData<List<Adat>> getAllWords(){
        return mAllWords;
    }

    void insert (Adat adat){
        new insertAsyncTask(adatDao).execute(adat);
    }

    private static class insertAsyncTask extends AsyncTask<Adat, Void, Void> {

        private AdatDao mAsyncTaskDao;

        insertAsyncTask(AdatDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Adat... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
