package com.dahye.assignment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;
import android.hardware.SensorEventListener;

/**
 * Created by Dahye on 2017-01-17.
 */

public class MagneticSensorListener implements SensorEventListener {

    TextView output;
    float number;

    TextView highOutput;
    float highNumber_x = 0;
    float highNumber_y = 0;
    float highNumber_z = 0;

    public MagneticSensorListener(TextView outputView, TextView highOutputView){
        this.output = outputView;
        this.highOutput = highOutputView;
        highNumber_x = -Float.MAX_VALUE;
        highNumber_y = -Float.MAX_VALUE;
        highNumber_z = -Float.MAX_VALUE;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){

            output.setText(String.format("(%.2f,%.2f,%.2f)",event.values[0],event.values[1],event.values[2]));

          //record high values

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

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

