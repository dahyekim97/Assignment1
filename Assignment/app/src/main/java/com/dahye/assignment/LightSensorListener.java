package com.dahye.assignment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import static com.dahye.assignment.MainActivity.graph;

/**
 * Created by Dahye on 2017-01-04.
 */
public class LightSensorListener implements SensorEventListener {

    TextView output;
    float number;

    TextView highOutput;
    float highNumber = 0;


    public LightSensorListener(TextView outputView,TextView highOutputView){
        this.output = outputView;
        this.highOutput = highOutputView;
        highNumber = -Float.MAX_VALUE;;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        if(event.sensor.getType() == Sensor.TYPE_LIGHT){

            //set the value to textview
                output.setText(String.format("%.2f",event.values[0]));

            //record high values

            if(event.values[0] > highNumber ){
                highNumber = event.values[0];
                highOutput.setText(String.format("%.2f",highNumber));

            }


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
