package com.company;

//public class Main {

   // public static void main(String[] args) {
	// write your code here
  //  }
//}


import com.wialon.core.Errors;
import com.wialon.core.Session;
import com.wialon.extra.SearchSpec;
import com.wialon.item.Item;
import com.wialon.remote.handlers.ResponseHandler;
import com.wialon.remote.handlers.SearchResponseHandler;

public class Main implements Runnable {
    private Session session;

    // Login to server
    private void login(){
        // initialize Wialon session
        session.initSession("http://hst-api.wialon.com");
        // trying login
        session.loginToken("9c637b0df7f3b5d66967b3c53ff30ebaC3721467CC0BE2E5E134FF85C2F901197A333149", new ResponseHandler() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                // login succeed
                System.out.println(String.format("Logged successfully. User name is %s", session.getCurrUser().getName()));
                //call search units

                //searchUnits();
               // CreateRout();
                 SearchGeozon();
            }

            @Override
            public void onFailure(int errorCode, Throwable throwableError) {
                super.onFailure(errorCode, throwableError);
                // login failed, print error
                System.out.println(Errors.getErrorText(errorCode));
            }
        });

    }


    private void CreateRout(){
        session.CreateRoute("5b5123165acf4d486bb53e51f1d518100B8AB23328FF09EB913C63F98D61EE62FE79684A ", new ResponseHandler(){
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                // login succeed
                System.out.println(String.format(response));
                //call search units

                // searchUnits();
                logout();
            }

            @Override
            public void onFailure(int errorCode, Throwable throwableError) {
                super.onFailure(errorCode, throwableError);
                // login failed, print error
                System.out.println(Errors.getErrorText(errorCode));
            }
        });
    }

    private void SearchGeozon(){
        session.SearchGeozon(new ResponseHandler(){
            @Override
            public void onSuccess(String response) {


                super.onSuccess(response);
                // login succeed
                System.out.println(String.format("Попытка не пытка"));
                //call search units

                // searchUnits();
                logout();
            }


            @Override
            public void onFailure(int errorCode, Throwable throwableError) {

                super.onFailure(errorCode, throwableError);
                // login failed, print error
                System.out.println(Errors.getErrorText(errorCode));
            }
        });
    }

    private void searchUnits(){
        //Create new search specification
        SearchSpec searchSpec=new SearchSpec();
        //Set items type to search avl_units
        searchSpec.setItemsType(Item.ItemType.avl_unit);
        //Set property name to search
        searchSpec.setPropName("sys_name");
        //Set property value mask to search all units
        searchSpec.setPropValueMask("*");
        //Set sort type by units name
        searchSpec.setSortType("sys_name");


        //Send search by created search specification with items base data flag and from 0 to maximum number
        session.searchItems(searchSpec, 1, Item.dataFlag.base.getValue(), 0, Integer.MAX_VALUE, new SearchResponseHandler() {
            @Override
            public void onSuccessSearch(Item... items) {
                super.onSuccessSearch(items);
                // Search succeed
                System.out.println("Search items is successful");
                printUnitsNames(items);
                logout();
            }
            @Override
            public void onFailure(int errorCode, Throwable throwableError) {
                super.onFailure(errorCode, throwableError);
                // search item failed, print error
                System.out.println(Errors.getErrorText(errorCode));
                logout();
            }
        });
    }

    private void printUnitsNames(Item... items){
        if (items!=null && items.length>0) {
            System.out.println(String.format("%d units found\r\nPrinting their names...", items.length));
            //Print items names
            for (Item item : items)
                System.out.println(String.format("\t%s", item.getName()));
        }
    }
    // Logout
    private void logout(){
        session.logout(new ResponseHandler() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                // logout succeed
                System.out.println("Logout successfully");
                System.exit(0);
            }

            @Override
            public void onFailure(int errorCode, Throwable throwableError) {
                super.onFailure(errorCode, throwableError);
                // logout failed, print error
                System.out.println(Errors.getErrorText(errorCode));
                System.exit(0);
            }
        });
    }

    @Override
    public void run() {
        // get instance of current Session
        session=Session.getInstance();
        Test hh = new Test();
        hh.test(1);
        hh.test(2);
        hh.test(3);
        hh.test(4);

        login();
    }

    public static void main(String[] args){
        new Thread(new Main()).start();
    }
}