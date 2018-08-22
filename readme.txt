How to:
- Insert your website BaseURL, link prefix and parameter name
  in 'config.xml' in project/app/src/main/res/values directory,
  and copy that xml file in your existing project
- Add this intent filter in the declaration of your Launcher Activity
  in AndroidManifest.xml file:
  <intent-filter>
      <action android:name="android.intent.action.VIEW" />
      <category android:name="android.intent.category.DEFAULT" />
      <category android:name="android.intent.category.BROWSABLE" />
      <data
          android:host="@string/linkUrl"
          android:pathPrefix="@string/linkPrefix"
          android:scheme="http" />
      <data
          android:host="@string/linkUrl"
          android:pathPrefix="@string/linkPrefix"
          android:scheme="https" />
  </intent-filter>
- Copy 'parseIncomingData' and 'handleIncomingData' methods in your
  MainActivity and call your parameter handling native function from
  'handleIncomingData' method.
- call 'parseIncomingData' method from 'onCreate' of your Main Activity
- If your Launcher Activity and Native Function Handling (Main) Activity are
  different, then refer 'openAnotherActivity' method (commented) and
  pass the action and data of Launcher Activity to your Main Activity. And
  do the above setup in Main Activity.

P.S.
- Consider setting android:launchMode="singleTask" for the target Activity to
  avoid running multiple copies of your Activity on the same time.

For Reference, follow this link:
- https://proandroiddev.com/best-practices-for-deeplinking-in-android-1dc1ea060c0c

Current redirection link:
- https://play.google.com/store/apps/details?id=in.sarjak.smartludo
