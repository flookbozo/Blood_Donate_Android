package com.example.myproject2.room;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myproject2.Models.Message;

import java.util.List;

public class MessageRepository {
    private Context mContext;
    public MessageRepository(Context context) {this.mContext = context;}
    public void getMessage(Callback callback) {
        GetTask getTask = new GetTask(mContext, callback);
        getTask.execute();
    }

    public void insertMessage(Message item, InsertCallback callback) {
        InsertTask insertTask = new InsertTask(mContext, callback);
        insertTask.execute(item);
    }

    public void findRequest(String hosname, FindCallback callback) {
        FindTask findTask = new FindTask(mContext, callback);
        findTask.execute(hosname);
    }

    public void updateMessage(int idRq, String hosName, UpdateCallback callback) {
        UpdateTask updateTask = new UpdateTask(mContext, callback);
        updateTask.execute(idRq, hosName);
    }

    private static class GetTask extends AsyncTask<Void, Void, List<Message>> {
        private Context mContext;
        private Callback mCallback;

        public GetTask(Context context, Callback callback) {
            this.mContext = context;
            this.mCallback = callback;
        }

        @Override
        protected List<Message> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<Message> itemList = db.messageDAO().getAllMessage();
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);
            mCallback.onGet(messages);
        }
    }

    public interface Callback {
        void onGet(List<Message> itemList);
    }

    private static class InsertTask extends AsyncTask<Message, Void, Void> {
        private Context mContext;
        private InsertCallback mCallback;

        public InsertTask(Context mContext, InsertCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected Void doInBackground(Message... messages) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.messageDAO().insertMessage(messages[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onInsertSuccess();
        }
    }

    public interface InsertCallback {
        void onInsertSuccess();
    }

    public static class FindTask extends AsyncTask<String, Void, List<Message>> {
        private Context mContext;
        private FindCallback mCallback;

        public FindTask(Context mContext, FindCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected List<Message> doInBackground(String... strings) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<Message> itemList = db.messageDAO().findRequest(strings[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);
            mCallback.onGet(messages);
        }
    }

    public interface FindCallback {
        void onGet(List<Message> itemList);
    }

    public static class UpdateTask extends AsyncTask<Object, Void, Void> {
        private Context mContext;
        private UpdateCallback mCallback;

        public UpdateTask(Context mContext, UpdateCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            int idRq = (Integer) objects[0];
            String hosName = (String) objects[1];
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.messageDAO().updateMessage(idRq, hosName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onUpdateSuccess();
        }
    }

    public interface UpdateCallback {
        void onUpdateSuccess();
    }

}
