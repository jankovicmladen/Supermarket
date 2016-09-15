package supermarket.main.data.response;

import java.util.ArrayList;

import supermarket.main.data.DataCity;

/**
 * Created by cubesschool2 on 9/12/16.
 */
public class ResponseCity {

    public ResponceCityPom2 data;

    public class ResponseCityPom {
        public ArrayList<DataCity> townships;
    }

    public class ResponceCityPom2{
        public String status;
        public String error;
        public ResponseCityPom results;
    }
}
