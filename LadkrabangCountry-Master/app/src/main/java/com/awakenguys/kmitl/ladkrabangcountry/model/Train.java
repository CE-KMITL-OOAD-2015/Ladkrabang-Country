package com.awakenguys.kmitl.ladkrabangcountry.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 17/11/2558.
 */
public class Train {
    private String name;
    private List<String> trainArrivalTimes = new ArrayList<String>();

    public Train(String name, List<String> trainArrivalTimes) {
        this.name = name;
        this.trainArrivalTimes = trainArrivalTimes;
    }

    public String getTrainName()
    {
        return name;
    }

    public List<String> getTrainArrivalTimes()
    {
        return trainArrivalTimes;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /*Time is in format hh:mm in 24hr format*/
    public String getNextTrainArrivalTime(String currentTime)
    {
        String[] currentTimeSplit =  currentTime.split(":");
        int currentMinuteOfTheDay = Integer.parseInt(currentTimeSplit[0])*60+Integer.parseInt(currentTimeSplit[1]);

        String[] trainArrivalTimeSplit;
        int trainArrivalMinuteOfTheDay;

        for(int i=0;i<trainArrivalTimes.size();i++)
        {
            trainArrivalTimeSplit = trainArrivalTimes.get(i).split(":");
            trainArrivalMinuteOfTheDay = Integer.parseInt(trainArrivalTimeSplit[0])*60+Integer.parseInt(trainArrivalTimeSplit[1]);

            if(currentMinuteOfTheDay<trainArrivalMinuteOfTheDay)
            {
                return trainArrivalTimes.get(i);
            }
        }
        return trainArrivalTimes.get(0);
    }

    public String getPassedTrainArrivalTime(String currentTime)
    {
        String[] currentTimeSplit =  currentTime.split(":");
        int currentMinuteOfTheDay = Integer.parseInt(currentTimeSplit[0])*60+Integer.parseInt(currentTimeSplit[1]);

        String[] trainArrivalTimeSplit;
        int trainArrivalMinuteOfTheDay;

        for(int i=0;i<trainArrivalTimes.size();i++)
        {
            trainArrivalTimeSplit = trainArrivalTimes.get(i).split(":");
            trainArrivalMinuteOfTheDay = Integer.parseInt(trainArrivalTimeSplit[0])*60+Integer.parseInt(trainArrivalTimeSplit[1]);

            if(currentMinuteOfTheDay<trainArrivalMinuteOfTheDay)
            {
                if(i==0) return trainArrivalTimes.get(trainArrivalTimes.size()-1);
                return trainArrivalTimes.get(i-1);
            }
        }
        return trainArrivalTimes.get(trainArrivalTimes.size()-1);
    }
}
