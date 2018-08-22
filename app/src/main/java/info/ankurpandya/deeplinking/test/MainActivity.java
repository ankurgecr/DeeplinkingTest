package info.ankurpandya.deeplinking.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseIncomingData(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIncomingData(intent);
    }

    /* If the Activity uas Uri in getIntent().getData()
     * that means that Application is launched from a Link
     * The following method checks the same thing */
    private void parseIncomingData(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            handleIncomingData(null);
        } else {

            /*
            // Some other useful methods
            String uriString1 = data.getScheme(); // http
            String uriString2 = data.getSchemeSpecificPart(); // /api.rideotg-dev.xyz/deeplinktest?roomId=123
            String uriString3 = data.getEncodedPath(); // /deeplinktest
            String uriString4 = data.getQuery(); // roomId=123
            String uriString5 = data.getQueryParameter(paramToParse); // 123
            List<String> uriString6 = data.getQueryParameters(paramToParse); // 1 element
            */

            String param1ToParse = getString(R.string.param1ToParse);
            String param2ToParse = getString(R.string.param2ToParse);
            String param1Value = null;
            String param2Value = null;
            try {
                param1Value = data.getQueryParameter(param1ToParse);
                param2Value = data.getQueryParameter(param2ToParse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            handleIncomingData(param1Value + " & " + param2Value);
        }
    }

    //ToDo - call your native method to handle 'roomId' here
    private void handleIncomingData(String paramValue) {
        if (isValid(paramValue)) {
            printLaunchMessage("Application launched from DeepLink.\nParameter Value: [" + paramValue + "]");
        } else {
            printLaunchMessage("Application launched manually");
        }
    }

    private void printLaunchMessage(String msgString) {
        TextView msg = findViewById(R.id.msg);
        msg.setText(msgString);
    }

    /*
    private void openAnotherActivity() {
        Intent intent = new Intent(this, AnotherActivity.class);
        if (getIntent().getAction() != null)
            intent.setAction(getIntent().getAction());
        if (getIntent().getData() != null)
            intent.setData(getIntent().getData());
        startActivity(intent);
    }
    */

    public static boolean isValid(String string) {
        return string != null && string.trim().length() > 0;
    }
}
