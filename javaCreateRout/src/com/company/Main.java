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
        session.initSession("http://wialon.biz");
        // trying login
        session.loginToken("2c80e39f3f7285ed958c02aa20368c5435787DFD720037D82423C3152BF751A2407AC05A", new ResponseHandler() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                // login succeed
                System.out.println(String.format("Logged successfully. User name is %s", session.getCurrUser().getName()));
                //call search units

                //searchUnits();
                searchObject();
                ImportMassage();
               // CreateRout();
                // SearchGeozon();
            }

            @Override
            public void onFailure(int errorCode, Throwable throwableError) {
                super.onFailure(errorCode, throwableError);
                // login failed, print error
                System.out.println(Errors.getErrorText(errorCode));
            }
        });

    }

    private void searchObject(){
/*
        https://hst-api.wialon.com/wialon/ajax.html?svc=core/search_items&
        params={
                "spec":{
            "itemsType":"user",
                    "propName":"sys_name",
                    "propValueMask":"*",
                    "sortType":"sys_name"
        },
        "force":1,
                "flags":1,
                "from":0,
                "to":0
	}&sid=<your_sid>


        */

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
        session.searchItems(searchSpec, 1, Item.dataFlag.groupGeo.getValue(), 0, Integer.MAX_VALUE, new SearchResponseHandler() {
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
            @Override
            public void onSuccess(String response) {

                session.CreateGroupGeozon(new ResponseHandler()
                {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        // login succeed
                        System.out.println(String.format("Попытка не пытка 222"));

                    }

                }, response);





                super.onSuccess(response);
                // logout succeed
                System.out.println("Logout successfully");
                System.exit(0);
            }
        });
    }


    private void ImportMassage(){
        session.ImportMassage("1be3b9a29e2159e9a58bc5376d06f43e49E553A8C3F560838DF9EC994B2CFDD26D198641", new ResponseHandler(){
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




    private void CreateRout(){
        session.CreateRoute("1be3b9a29e2159e9a58bc5376d06f43e49E553A8C3F560838DF9EC994B2CFDD26D198641", new ResponseHandler(){
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

                session.CreateGroupGeozon(new ResponseHandler()
                {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        // login succeed
                        System.out.println(String.format("Попытка не пытка 222"));

                    }

                }, response);


                super.onSuccess(response);
                // login succeed
                System.out.println(String.format("Попытка не пытка"));

                //CreateGroupGeozon
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
/*
        https://hst-api.wialon.com/wialon/ajax.html?svc=core/search_items&
        params={
                "spec":{
            "itemsType":"user",
                    "propName":"sys_name",
                    "propValueMask":"*",
                    "sortType":"sys_name"
        },
        "force":1,
                "flags":1,
                "from":0,
                "to":0
	}&sid=<your_sid>


        */

        //Create new search specification
        SearchSpec searchSpec=new SearchSpec();
        //Set items type to search avl_units
        searchSpec.setItemsType(Item.ItemType.avl_resource);
        //Set property name to search
        searchSpec.setPropName("sys_name");
        //Set property value mask to search all units
        searchSpec.setPropValueMask("*");
        //Set sort type by units name
        searchSpec.setSortType("sys_name");


        //Send search by created search specification with items base data flag and from 0 to maximum number
        session.searchItems(searchSpec, 1, Item.dataFlag.groupGeo.getValue(), 0, Integer.MAX_VALUE, new SearchResponseHandler() {
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
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                // logout succeed
                System.out.println("Logout successfully");
                System.exit(0);
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

//Распихать геозоны по группам.
    private void UpdateGeozon(){

        //1 Получить список "групп" геозон(существующих) из виалона
        //2 Получить список всех геозон из ресурса

        //3.1 Берем первую геозону
        //3.2 Выясняем название ее группы
        //3.3 Проверяем есть ли такая группа в списке
            //Если есть, то проверяем входит ли id геозоны в эту группу
                //Если да то завершить
                //Если нет, то добавляем
            //Если нет, то добавляем номер группы в список групп
                //Добавляем геозону в группу



    }


    public static void main(String[] args){
        new Thread(new Main()).start();
    }
}