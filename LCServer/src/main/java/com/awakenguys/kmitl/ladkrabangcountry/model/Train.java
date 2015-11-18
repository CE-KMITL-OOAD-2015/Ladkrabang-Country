package com.awakenguys.kmitl.ladkrabangcountry.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Train {
    @Id
    private String id;
    private String name;
    private String trainArrivalTimes;// = new ArrayList<String>();

    public Train() {
    }

    public Train(String name, String trainArrivalTimes) {
        this.name = name;
        this.trainArrivalTimes = trainArrivalTimes;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTrainArrivalTimes()
    {
        return trainArrivalTimes;
    }

    public void setTrainArrivalTimes(String trainArrivalTimes) {
        this.trainArrivalTimes = trainArrivalTimes;
    }


    /*Time is in format hh:mm in 24hr form/*at*/
    /*public String getNextTrainArrivalTime(String currentTime)
    {
        String[] currentTimeSplit =  currentTime.split(":");
        int currentMinuteOfTheDay = Integer.parseInt(currentTimeSplit[0])*60+Integer.parseInt(currentTimeSplit[1]);

        String[] trainArrivalTimeSplit;
        int trainArrivalMinuteOfTheDay;

        for(int i=0;i<trainArrivlength()s.size();i++)
        {
            trainArrivalTimeSplit = trainArrim[\s.get(i).split(":");
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
      }*/
}
