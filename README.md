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
10.Making a settings page by extending PrefernceActivity and how to implement onPreferenceChangeListener
11.Making preference items via preferences xml file and using root element PreferenceScreen - and further child elements like EditTextPreference , ListPreference , all of them have a key and a default value
12.Making array elements as resources - string-array which can be accessed by @array attribute
13.How to add the preference via the addPrefernceFromResource(xml file id) , finding preference by findPreference(by key string)
14.getting the preference values via PreferenceManager.getDefaultPrefernces(context) and then using prefs.getString(key,value)
15.Debugging app and breakpointing 
16.Doing explicit intents like maps intent - ie by building a data uri (geo:0,0 in maps case) amd then setData(uri) to Intent of type intent.ACTION_VIEW and then running the intent via startActivity(intent) only when the intent resolvesActivity(getPackageManager) is not null , ie some installed  application can acutally handle that action_view intent
