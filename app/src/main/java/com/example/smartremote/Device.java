package com.example.smartremote;

public class Device {

    private String m_name, m_locationName;
    int m_bearing, m_altitude;

    public Device(String m_name, String m_locationName, int m_bearing, int m_altitude) {
        this.m_name = m_name;
        this.m_locationName = m_locationName;
        this.m_bearing = m_bearing;
        this.m_altitude = m_altitude;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_locationName() {
        return m_locationName;
    }

    public void setM_locationName(String m_locationName) {
        this.m_locationName = m_locationName;
    }

    public void setM_bearing(int m_bearing) {
        this.m_bearing = m_bearing;
    }

    public void setM_altitude(int m_altitude) {
        this.m_altitude = m_altitude;
    }

}
