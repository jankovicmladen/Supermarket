package supermarket.main.data.response;

public class ResponseUser {

    public ResponseUserPom data;

    public class ResponseUserPom {

        public String status;
        public String error;
        public ResponseDataUser results;
        public String token;
        public String login_token;
        public String incorrect_logins;
    }
}
