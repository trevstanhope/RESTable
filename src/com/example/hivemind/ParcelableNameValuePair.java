package com.example.hivemind;

import org.apache.http.NameValuePair;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableNameValuePair implements NameValuePair, Parcelable {

	String name, value;
	
	public ParcelableNameValuePair(String name, String value) {
		this.name = name;
		this.value = value;	
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(value);		
	}

    /* 
     * This is used to regenerate your object.
     * A Parcelable must have a CREATOR that implements these two methods.
     */
    public static final Parcelable.Creator<ParcelableNameValuePair> CREATOR = new Parcelable.Creator<ParcelableNameValuePair>() {
        public ParcelableNameValuePair createFromParcel(Parcel in) {
            return new ParcelableNameValuePair(in);
        }
        public ParcelableNameValuePair[] newArray(int size) {
            return new ParcelableNameValuePair[size];
        }
    };

    /* 
     * Example constructor that takes a Parcel,
     * then gives you an object populated with it's values
     */
    private ParcelableNameValuePair(Parcel in) {
		name = in.readString();
		value = in.readString();
    }
    
}
