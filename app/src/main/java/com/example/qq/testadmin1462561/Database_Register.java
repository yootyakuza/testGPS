package com.example.qq.testadmin1462561;

/**
 * Created by Sarayut on 14/6/2561.
 */
public class Database_Register {
    public String _email;
    public String _username;
    public String _telephone;
    public String _password;


    public Database_Register(){

    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_telephone() {
        return _telephone;
    }

    public void set_telephone(String _telephone) {
        this._telephone = _telephone;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    //    public Database_Register(String _username, String _Email, String _password , String _Telephone){
//        this._username = _username;
//        this._Email = _Email;
//        this._password = _password;
//        this._Telephone = _Telephone;
//    }

}
