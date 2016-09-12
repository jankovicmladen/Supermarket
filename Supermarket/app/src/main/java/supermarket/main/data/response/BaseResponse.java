package supermarket.main.data.response;

/**
 * Created by cubesschool2 on 9/12/16.
 */
public class BaseResponse<T> {

    public String status;
    public String error;
    public T results;

}
