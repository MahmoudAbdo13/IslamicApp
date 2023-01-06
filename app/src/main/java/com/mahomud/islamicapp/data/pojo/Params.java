
package com.mahomud.islamicapp.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("Fajr")
    @Expose
    private Double fajr;
    @SerializedName("Isha")
    @Expose
    private String isha;

    public Double getFajr() {
        return fajr;
    }

    public void setFajr(Double fajr) {
        this.fajr = fajr;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Params.class.getName()).append('@').append(Double.toHexString(System.identityHashCode(this))).append('[');
        sb.append("fajr");
        sb.append('=');
        sb.append(((this.fajr == null)?"<null>":this.fajr));
        sb.append(',');
        sb.append("isha");
        sb.append('=');
        sb.append(((this.isha == null)?"<null>":this.isha));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
