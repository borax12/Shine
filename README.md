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

27. Curosr c= db.rawQuery(Select name from sqlite_master where type='table' ,null) the cursor is basially a resultset from a query and then to check if this returns something valid or not - doa  assertTrue("This means the tables are not crated",c.moveToFirst()); and then verify the tables do(tablename hashset.remove(c.getString(0))while(c.moveToNext) - this 0th column gives the name of the table from the result retured from the rawQuery

28. now for checking if the coloums are made correctly - do a curosr c = db.rawQuery(PRAGMA table_info(tablename),null) and again check validity of even working properly using c.moveToFirst in an assertTrue, and then make a hashset of the coloumn names and then using the column value of a coulumn called name in the table returend from that pragma command keep removing from the hashset the same columns 

29. closing the db and curosr is immportant - c.close, db.close

30. Now if you want to test whether let say weather table values - you do the following , you get the contentvalues and then make a dbhelper object and get a writeable databse and then you insert values and then check if the returned row id is not equal to -1 , and then make a cursor point to the result set returned from a query which selects the projection to be returned from the table (After insertionof values ) and then assertTrue that it is a valid result returned by cursor.moveToFirst and then validate current record by your own validate record function which would basically take the curosr and the expected value content values object and then it will take a Set of map entries of a string - column name and the value of that coloumn and it will store the valuesset returned from the expectedvalues - expectedvalues.valueset and the for every map entry from the value set it will take the key value - that is coloumn name, find out the index of that particular coloumn using curosr.getcoloumnindex(coloumnname) and then assertEquals that expectedvalue.getvalue and the curosr.getString(coloumnIndex) 

31. Content Providers can be used to effectively update data to the ui , while being connected to a data source , the advantage of using the content provider is that we can leverage the functionality of other android classes like SyncAdapter and cursorAdapters or loaders whihc all use a content provider , also it opens up the data of our app to other apps

32. To first of all read a content provider , standard content providers , you have to add the permission that it uses the data eg ANDROID.permission.READ_USER_DICTIONARY

33. Then a content Resolver is used as a middlemena to get a cursoor and that and that curosor is an iterarotr of a a query function on the database , , we do a resolver.query(content ui,selection, selection args,group by , having , sort order) this to get a cursor 

34.  The content uro is like content://user-dictionary/words where content://is just a protocl and the user-dictionary is a content authority , generally the best practice is to use package name as an autority name. 

35.   A curosr adapter hooks with a curosr and can be used to upadte views like a listview , for this you need two arrays , one string array of the columns to bind with , where the values will come from the curosr and the layout fields to fill the data with , so a simple cursor adapter can be initialized with simpleCursorAdapter(this,layout of textview per list item, curosr , string[] from , int[] to), and then listview.setadapter(---)

36. Going on to building a content provider  , we will need  to determine the uris the app will support , update the contract to include the uris and fill out the uri matcher to support the new uris and also implement the various functions a content provider has like query , insert , update , delete , bulkinsert 

37. So  we update the contract to include the content authroity , and then have the base uri as the content:// and content authority passed to Uri.parse("content://"+content_authority

38. To build the uri for the content uri you can use the base content uri and then builUpon().appendPath(path).build 

39. android returns dir item type for curosrs which return more than one rows and itemtype for curosr which return one record

40. So we need to make a string called contenttype  which we can get from the ContentResolver.CURSOR_DIR_TYPE+"/"+Content_authrity+pathlocation, 

41. You can also have standard static methods in the contract entity class which builds the uris for you , takes a paramter , then either use ContentUris.withAppendedId(Content_uri,id), or uses Contenturi.buildUpon().appendPath(path).build or contenturi.buildUpon().appendQueryParamter(query,value).build(); or use  return Uri.getPathSegments().get(1)

42. In our contentProvider we also need a uri matcher , so a buildUriMatcher static method can be implemented which can the UriMatcher instantiated and then we add the matcher uri patters by matcher.addURI(authority,path+"/*", returncode when match)

43. To make joins in tables you can use SQLiteQueryBuilder to instatitae a new SQliteQueryBuilder object adnd then do a setTables(Tablename +"INNER JOIN"+Tablename+"ON"+Condition)

44. To build a content provider , you need to implement , on create , getType, query , insert, delete, update , shutdown

45. In the oncreate method of the contentprovider , you can instantiate , the dbHelper Object 

46. In the GetType(Uri uri) method , get a int match code for a urimatcher.match(uri), then pass the matchcode in a switch case, do the cooresponding task for each return code return the appropiate return code 

47. In the query method whcih returns curosr  - curosr query(uri, string[] orojection,string selection, string selection args,sortorder), then call match function of the uri matcher in a swtich function and the according to the return codes, just call the query method of the dbhelper object .getReadableDatabase or the SqliteQueryBuilder Object query method with the given arguments ,

48. In the insert method, call the insert command of the dbhelper and then get the reutrned row id of the insert row and then also getContext.getContentResolver.notfiyChange(Uri,null) to notify the sending uri that the data has been changed 

49. For the delete method , just overrride the bulkInsert metehod that takes a uri , and content values array , and then just keep inserting the rows based on the match return coe and for this , it has to be done between the db.beginTransaction and the db.setTransactionSuccesful() method 

50. To test if the manifest file contains the said provider info , get the packagemanager from the mcontext and then the compnonentName - compnonentName = New ComponentName(mcontext.getPackacgName,weatherProvider.class.getName), thena in a try block do a providerInfo = pm.getProviderInfo(componentname,0) amd then assertEquals(providerInfo.authority,WeatherContract.AUTHORITY)

51. To test if a notifcation uri ahs been set or not , we do an assertEquals(locationCursor.getNotificationUri,ContentURI), available after api level 19 , so put this in an if (BuildVersion.SDK_INT>19)

