package com.example.yoshknight.androidavanzado_educacionit.utils;

import android.os.AsyncTask;

public class AsyncTaskExample extends AsyncTask<String, Void, Integer> {
    private AsyncTaskExampleInterface listener;

    public AsyncTaskExample(AsyncTaskExampleInterface listener) {
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.previoEjecucion();
    }

    @Override
    protected Integer doInBackground(String... strings) {

        String param = "";
        if (strings.length>0)
            param = strings[0];

        int k= 0;
        for(int i = 0; i < 100000 ; i++)
            for(int j = 0; j < 100000 ; j++)
                k = ( i + j ) *i;

        return (Integer)k;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        listener.posteriorEjecucion();
    }
}
