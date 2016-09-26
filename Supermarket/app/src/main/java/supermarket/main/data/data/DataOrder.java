package supermarket.main.data.data;

import supermarket.main.data.response.BaseResponse;

/**
 * Created by cubesschool2 on 9/26/16.
 */
public class DataOrder {

    public String status;
    public String message;
    public String error;
    public double sum;
    public Result results;


    public class Result{
        public String id;
    }


}


