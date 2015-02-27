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
