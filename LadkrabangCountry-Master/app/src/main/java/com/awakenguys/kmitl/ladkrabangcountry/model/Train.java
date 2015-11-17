package com.awakenguys.kmitl.ladkrabangcountry.model;

public class Train {
    private String name;
    private String trainArrivalTimes;

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
    public String getNextTrainArrivalTime(String currentTime)
    {
        String[] currentTimeSplit =  currentTime.split(":");
        int currentMinuteOfTheDay = Integer.parseInt(currentTimeSplit[0])*60+Integer.parseInt(currentTimeSplit[1]);

        String[] trainArrivalTimes = this.trainArrivalTimes.split(",");
        String[] trainArrivalTimeSplit;
        int trainArrivalMinuteOfTheDay;

        for(int i=0;i<trainArrivalTimes.length;i++)
        {
            trainArrivalTimeSplit = trainArrivalTimes[i].split(":");
            trainArrivalMinuteOfTheDay = Integer.parseInt(trainArrivalTimeSplit[0])*60+Integer.parseInt(trainArrivalTimeSplit[1]);

            if(currentMinuteOfTheDay<trainArrivalMinuteOfTheDay)
            {
                return trainArrivalTimes[i];
            }
        }
        return trainArrivalTimes[0];
    }

    public String getPassedTrainArrivalTime(String currentTime)
    {
        String[] currentTimeSplit =  currentTime.split(":");
        int currentMinuteOfTheDay = Integer.parseInt(currentTimeSplit[0])*60+Integer.parseInt(currentTimeSplit[1]);

        String[] trainArrivalTimes = this.trainArrivalTimes.split(",");
        String[] trainArrivalTimeSplit;
        int trainArrivalMinuteOfTheDay;

        for(int i=0;i<trainArrivalTimes.length;i++)
        {
            trainArrivalTimeSplit = trainArrivalTimes[i].split(":");
            trainArrivalMinuteOfTheDay = Integer.parseInt(trainArrivalTimeSplit[0])*60+Integer.parseInt(trainArrivalTimeSplit[1]);

            if(currentMinuteOfTheDay<trainArrivalMinuteOfTheDay)
            {
                if(i==0) return trainArrivalTimes[trainArrivalTimes.length-1];
                return trainArrivalTimes[i-1];
            }
        }
        return trainArrivalTimes[trainArrivalTimes.length-1];
    }
}
