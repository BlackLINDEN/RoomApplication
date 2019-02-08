package blacklinden.com.roomapplication;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Adat.class}, version = 1)
public abstract class AdatRoomDatabase extends RoomDatabase {
    public abstract AdatDao adatDao();

    private static volatile AdatRoomDatabase INSTANCE;

    static AdatRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AdatRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AdatRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AdatDao mDao;

        PopulateDbAsync(AdatRoomDatabase db) {
            mDao = db.adatDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            Adat word = new Adat("Hello");
            mDao.insert(word);
            word = new Adat("World");
            mDao.insert(word);
            return null;
        }
    }
}


