package com.mahomud.islamicapp.data.pojo;

public class PrayerTiming {

    private String paryerName, payerTime;

    public PrayerTiming(String paryerName, String payerTime) {
        this.paryerName = paryerName;
        this.payerTime = payerTime;
    }

    public String getParyerName() {
        return paryerName;
    }

    public String getPayerTime() {
        return payerTime;
    }

}
