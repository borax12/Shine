# Shine
Application to understand android platform specifics

#Roadmap of discovery

1. HttpUrlConnection 

2. Offloading data intensive computations to an AsyncTask 

3. AsyncTask doInBackground and postExecute and preExecute methods 

4. URI Builder to add params and values to a base url 

5. JulianDays 

6. Making use of Custom Fragment Layouts

7. Activating ability to add options to menu of a Fragment

8. Logging - verbose for debugging and error logging

9. Intents and putExtra to send messages to recipent class , recieving intents and extracting messages 

10. Making a settings page by extending PrefernceActivity and how to implement onPreferenceChangeListener

11. Making preference items via preferences xml file and using root element PreferenceScreen - and further child elements like EditTextPreference , ListPreference , all of them have a key and a default value

12. Making array elements as resources - string-array which can be accessed by @array attribute

13. How to add the preference via the addPrefernceFromResource(xml file id) , finding preference by findPreference(by key string)

14. getting the preference values via PreferenceManager.getDefaultPrefernces(context) and then using prefs.getString(key,value)

15. Debugging app and breakpointing 

16. Doing explicit intents like maps intent - ie by building a data uri (geo:0,0 in maps case) amd then setData(uri) to Intent of type intent.ACTION_VIEW and then running the intent via startActivity(intent) only when the intent resolvesActivity(getPackageManager) is not null , ie some installed  application can acutally handle that action_view intent

17. Making a share buttong , the strings are added for the share action label and then the menu resource file is made with the namespace as app if the older devices are to be supported and the item tag should have an attribute called actionProviderClass which directs to the android support library ShareActionProvider class , then the custom shareAction Intent is made which instantiates an ACTION_SEND intent and then adds the flag by intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET) to notify that the new share intent activity launched is a part of the root activity from where the share intent is started from . the intent is also defined its setType for the data type being sent and also any custom data to be sent is bundle by the putEXTRA method . Then in the onCreateOptionsMEnu , the inflater inflates the menu resource and then the menu item for the share action is found and stored in MenuItem , and finally the shareActionProvider helps us to set the share intent to be the new intent method we created 

18. To notify the android system that the app can reieve an intent it has to register a receiver by either the manifest or dynamicaly ,the custom receiver has to extend the BroadCastReceiver class and override the onReceive method and handle the received method and for registering edit the manifest to include a receiver tag and the intent filter it can handle , with specification for the intent action it can handle like custom action or common intents , or it can also register the receiver dynamically -though this only listens till the app is alive , eg of an action specified by system - ACTION_POWER_CONNECTED

19. Activity life cycle - OnCreate - onStart -OnREsume- OnPause-OnSTop- ONdestroy
20.                     - OnCreate - onStart -OneResume- OnPause-OnStop-OnDestroy-OnCreate-OnStart-On-Resume (if activity configuration changes that is )

21. Android provides a callback to rebuild the app statue -- its onSaveInstanceState and onRestoreinstanceState
22. So generally speaking the classes you neeed in your app to handle databases ,- is a Contract class with subclasses pointing to the various tables you have in the database ,it should have the couloumn names in it as a static string , its just an agreement between views and data model , then you need a DBHelper class which extends SqliteOpenHelper which helps in creation of DB and initializing of database and also the versioning of the database

23. the dbhelper class whll take the context in the constructor - WeatherDBHelper(context context) { super(contenxt, database_name,null,database_Version),and it will also need an onCreate method which has the SqliteDatabase as an argument , which will create the table - the craete table string will be store in a final static string and the sqlitedatabase.execsql will be called with the statement , then there is also the onUpgrade method which takes the databse and also the verion int , new and old , so that its called when a new version code is established for the database , basically you should drop the old tables and create new ones 

24. For making test cases , extend android testcase and you can additionally overrride the setup method and tearDown method , for running all the testcases you can make a full test suite calss extend the TestSuite class and just in the constructor call TestSuiteBuilder(customfulltestsuiteclass.class).includeAllPackagesUnderHere().build)_ 

25. In an example testDB class that will test the databse , in the setup() method , delete the databse and do this by the presenet mContext variable - mcontext.deletedatabse(databsename) 

26. for testing the creation fo the database and the tables , you would add lets say a testCreateDb method in the testdb class and in that you would make a hashset of strings with the table names added to it , then you would get a new writeable databse from the weatherdbhelper - weatherDBHelper(this.mcontext).getWriteableDatabse(), the check that this databse isOpen in an assertEquals(true,db.isOpen()) and now to check that the tables are crteated in this particular databse or not-  for this check 27

27. Curosr c= db.rawQuery(Select name from sqlite_master where type='table' ,null) 
