package com.dahye.assignment;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import ca.uwaterloo.sensortoy.LineGraphView;

import static com.dahye.assignment.AccelerometerSensorListener.ax;
import static com.dahye.assignment.AccelerometerSensorListener.ay;
import static com.dahye.assignment.AccelerometerSensorListener.az;
import static com.dahye.assignment.AccelerometerSensorListener.x;
import static com.dahye.assignment.AccelerometerSensorListener.y;
import static com.dahye.assignment.AccelerometerSensorListener.z;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager SM;
    Sensor lightSensor;
    Sensor accelerometerSensor;
    Sensor magneticfieldSensor;
    //light sensor event listener
    SensorEventListener LSL;
    //accelerometer sensor event listener
    SensorEventListener ASL;
    //magnetic field sensor event listener
    SensorEventListener MSL;

    File file = null;
    public static PrintWriter printWriter = null;

    FileInputStream in = null;
    FileOutputStream out = null;

    public static LineGraphView graph;

    TextView lightReading;
    TextView highLightReading;

    TextView accelerometerReading;
    TextView highAccelerometerReading;

    TextView magneticfieldReading;
    TextView highmagneticfieldReading;



    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout
        LinearLayout ll = (LinearLayout) findViewById(R.id.l);
        ll.setOrientation(LinearLayout.VERTICAL);

        //graph
        //(application context,number of samples to keep on t-axis,list of labels for the graph)
        graph = new LineGraphView(getApplicationContext(),100, Arrays.asList("x","y","z"));
        ll.addView(graph,0);
        graph.setVisibility(View.VISIBLE);

        //buttons
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //Call the method up there.  We will write something into our cellphone!
                graph.purge();
            }
        });

        //Generate Record for acc.sen
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        //Call the method up there.  We will write something into our cellphone!
                        writeToFile();
                    }
                }
        );



        //create sensor manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

/////////////////////////// LIGHT SENSOR /////////////////////////////
        //Light value textview
        lightReading = (TextView) findViewById(R.id.lightValue);
        highLightReading = (TextView) findViewById(R.id.hlr);

        //light sensor
        lightSensor = SM.getDefaultSensor(Sensor.TYPE_LIGHT);
        LSL = new LightSensorListener(lightReading,highLightReading);
        SM.registerListener(LSL,lightSensor,SM.SENSOR_DELAY_NORMAL);

/////////////////////////// ACCELEROMETER /////////////////////////////
        //accelerometer textview
        accelerometerReading = (TextView) findViewById(R.id.ar);
        highAccelerometerReading = (TextView) findViewById(R.id.har);
        //generate accelerometer sensor
        accelerometerSensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ASL = new AccelerometerSensorListener(accelerometerReading, highAccelerometerReading);
        SM.registerListener(ASL,accelerometerSensor,SM.SENSOR_DELAY_NORMAL);



        /////////////////////////// MAGNETIC SENSOR /////////////////////////////
        //accelerometer textview
        magneticfieldReading = (TextView) findViewById(R.id.mr);
        highmagneticfieldReading = (TextView) findViewById(R.id.hmr);
        //generate accelerometer sensor
        magneticfieldSensor = SM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        MSL = new MagneticSensorListener(magneticfieldReading, highmagneticfieldReading);
        SM.registerListener(MSL,magneticfieldSensor,SM.SENSOR_DELAY_NORMAL);



        //generate file to store accelerometer variables
       /* try{
            in = new FileInputStream("input.txt");
            out = new FileOutputStream("output.txt");
            int c;

            while ((c = in.read()) != -1){
                out.write(c);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                //in.close();
            }
            if(out != null){
                out.close();
            }
        }
        */



    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void writeToFile(){

        try{

            //The default External File Directory is
            // Android/data/ANDROID_PACKAGE_NAME/Lecture 4 Demo
            // If the directory does not exist it will be created for you
            file = new File(getExternalFilesDir("lab1data"), "lab1data.csv");
            printWriter = new PrintWriter(file);

            printWriter.write("Least Recent Value\n");

            for(int i = 0 ; i < 100 ; i++) {
                printWriter.write(String.format("%d : (%.2f , %.2f , %.2f)\n",i+1, x.get(i), y.get(i), z.get(i)));
            }

            printWriter.write("Most Recent Values");
        }
        catch(IOException e){

            //Notice that we are using Log.d.
            Log.d("Lecture 4 Example", "File Write Fail: " + e.toString());
        }
        finally{

            //Added the null pointer check to prevent further NPE.
            //Credit to Farzan N. from ECE 155 W17 :)
            if(printWriter != null){
                printWriter.flush();
                printWriter.close();
            }

            //Notice that we are using Log.d.
            Log.d("Lecture 4 Example", "File Write Ended.");
        }

    }

}
