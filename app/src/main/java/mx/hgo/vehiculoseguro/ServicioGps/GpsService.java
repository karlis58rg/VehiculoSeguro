package mx.hgo.vehiculoseguro.ServicioGps;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class GpsService extends Service {
    public GpsService() {
    }

    MyTask tarea;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"SERVICIO CREADO",Toast.LENGTH_LONG).show();
        tarea = new MyTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tarea.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"SERVICIO DESTRUIDO",Toast.LENGTH_LONG).show();
        tarea.cancel(true);
    }

    public  class MyTask extends AsyncTask<String, String, String> {
        private boolean aux;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            aux = true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(),"SERVICIO EN EJECUCION",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            aux = false;
        }

        @Override
        protected String doInBackground(String... strings) {
            while (aux){
                try{
                    publishProgress();
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


}
