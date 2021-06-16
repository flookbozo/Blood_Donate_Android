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

    public void findRequest(int idUser, String hosname, FindCallback callback) {
        FindTask findTask = new FindTask(mContext, callback);
        findTask.execute(idUser, hosname);
    }

    public void updateMessage(int idRq, int idUser, String hosName, UpdateCallback callback) {
        UpdateTask updateTask = new UpdateTask(mContext, callback);
        updateTask.execute(idRq, idUser, hosName);
    }

    public void updateActive(int idUser, String hosName, UpdateActiveCallback callback) {
        UpdateActiveTask updateActiveTask = new UpdateActiveTask(mContext, callback);
        updateActiveTask.execute(idUser, hosName);
    }

    public void getMessageId(int idUser, GetMessageIdCallback callback) {
        GetMessageIdTask getMessageIdTask = new GetMessageIdTask(mContext, callback);
        getMessageIdTask.execute(idUser);
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

    public static class FindTask extends AsyncTask<Object, Void, List<Message>> {
        private Context mContext;
        private FindCallback mCallback;

        public FindTask(Context mContext, FindCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected List<Message> doInBackground(Object... objects) {
            int iduser = (Integer) objects[0];
            String hosname = (String) objects[1];
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<Message> itemList = db.messageDAO().findRequest(iduser, hosname);
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
            int idU = (Integer) objects[1];
            String hosName = (String) objects[2];
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.messageDAO().updateMessage(idRq, idU, hosName);
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

    public static class UpdateActiveTask extends AsyncTask<Object, Void, Void> {
        private Context mContext;
        private UpdateActiveCallback mCallback;

        public UpdateActiveTask(Context mContext, UpdateActiveCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            int idU = (Integer) objects[0];
            String hosname = (String) objects[1];
            AppDatabase db = AppDatabase.getInstance(mContext);
            db.messageDAO().updateActive(idU, hosname);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onUpdateSuccess();
        }
    }

    public interface UpdateActiveCallback {
        void onUpdateSuccess();
    }

    public static class GetMessageIdTask extends AsyncTask<Integer, Void, List<Message>> {
        private Context mContext;
        private GetMessageIdCallback mCallback;

        public GetMessageIdTask(Context mContext, GetMessageIdCallback mCallback) {
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        protected List<Message> doInBackground(Integer... integers) {
            AppDatabase db = AppDatabase.getInstance(mContext);
            List<Message> itemList = db.messageDAO().getMessageId(integers[0]);
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            super.onPostExecute(messages);
            mCallback.onGetSuccess(messages);
        }
    }

    public interface GetMessageIdCallback {
        void onGetSuccess(List<Message> itemList);
    }
}
