package com.dahye.assignment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.dahye.assignment.MainActivity.graph;
import static com.dahye.assignment.MainActivity.printWriter;

/**
 * Created by Dahye on 2017-01-17.
 */

public class AccelerometerSensorListener  implements SensorEventListener {

    TextView output;
    float number;

    Button bt1;

    TextView highOutput;
    float highNumber_x = 0;
    float highNumber_y = 0;
    float highNumber_z = 0;

    public static float ax = 0;
    public static float ay = 0;
    public static float az = 0;

    public static LinkedList<Float> x;
    public static LinkedList<Float> y;
    public static LinkedList<Float> z;

    public AccelerometerSensorListener(TextView outputView, TextView highOutputView){
        this.output = outputView;
        this.highOutput = highOutputView;
        highNumber_x = 0;
        highNumber_y = 0;
        highNumber_z = 0;



        x = new LinkedList<>();
        y = new LinkedList<>();
        z = new LinkedList<>();


        //initialize x,y,z values for csv file
        for(int i = 0 ; i < 100 ; i++) {
            x.add(0.0f);
            y.add(0.0f);
            z.add(0.0f);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        graph.addPoint(event.values);

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            output.setText(String.format("(%.2f,%.2f,%.2f)",event.values[0],event.values[1],event.values[2]));

            // add elements. If the size is >= 100 remove the first-in value and add new values
            if(x.size() >= 100) {
                x.removeFirst();
                x.add(event.values[0]);
            }


            if(y.size() >= 100) {
                y.removeFirst();
                y.add(event.values[1]);
            }


            if(z.size() >= 100) {
                z.removeFirst();
                z.add(event.values[2]);
            }





        }

        if(event.values[0] > highNumber_x ){
            highNumber_x = event.values[0];
            highOutput.setText(String.format("(%.2f,%.2f,%.2f)",highNumber_x,highNumber_y,highNumber_z));

        }
        if(event.values[1] > highNumber_y ){
            highNumber_y = event.values[1];
            highOutput.setText(String.format("(%.2f,%.2f,%.2f)",highNumber_x,highNumber_y,highNumber_z));

        }
        if(event.values[2] > highNumber_z ){
            highNumber_z = event.values[2];
            highOutput.setText(String.format("(%.2f,%.2f,%.2f)",highNumber_x,highNumber_y,highNumber_z));

        }

        System.out.println(x.get(0));


    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}

