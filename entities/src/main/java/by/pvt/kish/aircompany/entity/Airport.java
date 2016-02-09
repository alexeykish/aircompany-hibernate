package by.pvt.kish.aircompany.entity;

import java.io.Serializable;

/**
 * This class represents the Airport model.
 * The airport is used as a flights place of departure and place of arrival.
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
public class Airport implements Serializable {
    private Long aid;
    private String city;

    public Airport() {
    }

    /**
     * @param aid  - airport id
     * @param city - the place where the airport is located
     */
    public Airport(Long aid, String city) {
        this.aid = aid;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (aid != null ? !aid.equals(airport.aid) : airport.aid != null) return false;
        return city != null ? city.equals(airport.city) : airport.city == null;

    }

    @Override
    public int hashCode() {
        int result = aid != null ? aid.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "aid=" + aid +
                ", city='" + city + '\'' +
                '}';
    }
}
