package com.pheah.pheah_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	public static final String PREF = "com.pheah.pheah_android.PREF";

	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_container);

		fragment = new SignUpFragment();

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragmentContainer, fragment);
		transaction.commit();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fragment.onActivityResult(requestCode, resultCode, data);
	}

	public void changeFragment(Fragment fragment) {
		this.fragment = fragment;

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentContainer, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	/*public void openMap(MapFragment f) {
		this.fragment = f;

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentContainer, fragment);
		transaction.commit();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}*/
}
/*
public class MainActivity extends AppCompatActivity implements
		View.OnClickListener,
		ActivityCompat.OnRequestPermissionsResultCallback,
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {

	private static final String TAG = "pheah.MainActivity";

	*/
/* RequestCode for resolutions involving sign-in *//*

	private static final int RC_SIGN_IN = 1;

	*/
/* RequestCode for resolutions to get GET_ACCOUNTS permission on M *//*

	private static final int RC_PERM_GET_ACCOUNTS = 2;

	*/
/* Keys for persisting instance variables in savedInstanceState *//*

	private static final String KEY_IS_RESOLVING = "is_resolving";
	private static final String KEY_SHOULD_RESOLVE = "should_resolve";

	*/
/* Client for accessing Google APIs *//*

	private GoogleApiClient mGoogleApiClient;

	*/
/* View to display current status (signed-in, signed-out, disconnected, etc) *//*

	private TextView mStatus;

	// [START resolution_variables]
	*/
/* Is there a ConnectionResult resolution in progress? *//*

	private boolean mIsResolving = false;

	*/
/* Should we automatically resolve ConnectionResults when possible? *//*

	private boolean mShouldResolve = false;
	// [END resolution_variables]

	public static boolean startMap = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_signup);

		// Restore from saved instance state
		// [START restore_saved_instance_state]
		if (savedInstanceState != null) {
			mIsResolving = savedInstanceState.getBoolean(KEY_IS_RESOLVING);
			mShouldResolve = savedInstanceState.getBoolean(KEY_SHOULD_RESOLVE);
		}
		// [END restore_saved_instance_state]

		// Set up button click listeners
		findViewById(R.id.sign_in).setOnClickListener(this);
		findViewById(R.id.sign_out).setOnClickListener(this);
		findViewById(R.id.disconnect_button).setOnClickListener(this);
		findViewById(R.id.map_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, MapActivity.class)); //TODO: Make Fragment.
			}
		});

		// Large sign-in
		((SignInButton) findViewById(R.id.sign_in)).setSize(SignInButton.SIZE_WIDE);

		// Start with sign-in button disabled until sign-in either succeeds or fails
		findViewById(R.id.sign_in).setEnabled(false);

		// Set up view instances
		mStatus = (TextView) findViewById(R.id.status);

		// [START create_google_api_client]
		// Build GoogleApiClient with access to basic profile
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API)
				.addScope(new Scope(Scopes.PROFILE))
				.addScope(new Scope(Scopes.EMAIL))
				.build();
		// [END create_google_api_client]
	}

	private void updateUI(boolean isSignedIn) {
		if (isSignedIn) {
			Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
			if (currentPerson != null) {
				// Show signed-in user's name
				String name = currentPerson.getDisplayName();
				mStatus.setText(name);//getString(R.string.signed_in_fmt, name));

				// Show users' email address (which requires GET_ACCOUNTS permission)
				if (checkAccountsPermission()) {
					String currentAccount = Plus.AccountApi.getAccountName(mGoogleApiClient);
					mStatus.setText(mStatus.getText() + currentAccount);
				}
			} else {
				// If getCurrentPerson returns null there is generally some error with the
				// configuration of the application (invalid Client ID, Plus API not enabled, etc).
				Log.w(TAG, "Null person");
				mStatus.setText("Null person");
			}

			// Set button visibility
			findViewById(R.id.sign_in).setVisibility(View.GONE);
			//findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
			findViewById(R.id.sign_out).setVisibility(View.VISIBLE);
			findViewById(R.id.disconnect_button).setVisibility(View.VISIBLE);
		} else {
			// Show signed-out message and clear email field
			mStatus.setText("Please Sign in");
			//((TextView) findViewById(R.id.email)).setText("");

			// Set button visibility
			findViewById(R.id.sign_in).setEnabled(true);
			findViewById(R.id.sign_in).setVisibility(View.VISIBLE);
			//findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
			findViewById(R.id.sign_out).setVisibility(View.GONE);
			findViewById(R.id.disconnect_button).setVisibility(View.GONE);
		}
	}

	*/
/**
	 * Check if we have the GET_ACCOUNTS permission and request it if we do not.
	 * @return true if we have the permission, false if we do not.
	 *//*

	private boolean checkAccountsPermission() {
		final String perm = Manifest.permission.GET_ACCOUNTS;
		int permissionCheck = ContextCompat.checkSelfPermission(this, perm);
		if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
			// We have the permission
			return true;
		} */
/*else if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
			// Need to show permission rationale, display a snackbar and then request
			// the permission again when the snackbar is dismissed.
			Snackbar.make(findViewById(R.id.main_layout),
					R.string.contacts_permission_rationale,
					Snackbar.LENGTH_INDEFINITE)
					.setAction(android.R.string.ok, new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// Request the permission again.
							ActivityCompat.requestPermissions(MainActivity.this,
									new String[]{perm},
									RC_PERM_GET_ACCOUNTS);
						}
					}).show();
			return false;
		} else {
			// No explanation needed, we can request the permission.
			ActivityCompat.requestPermissions(this,
					new String[]{perm},
					RC_PERM_GET_ACCOUNTS);
			return false;
		}*//*

		else {
			Toast.makeText(this, "Please grant all the required permissions", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	public static final String PREF = "com.pheah.pheah_android.PREF";

	private void showSignedInUI() {
		//updateUI(true);

		Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
		Log.d(TAG, "p = " + currentPerson + ", m = " + startMap);
		if (currentPerson != null) {
			SharedPreferences p = getSharedPreferences(PREF, MODE_PRIVATE);
			SharedPreferences.Editor e = p.edit();
			e.putString("name", currentPerson.getDisplayName());
			e.putString("email", Plus.AccountApi.getAccountName(mGoogleApiClient));
			e.putString("photoUrl", currentPerson.getImage().getUrl());
			e.apply();
			if (startMap) {
				startActivity(new Intent(this, MapActivity.class)); //TODO: Make Fragment.
				this.finish();
			}
		}
	}

	private void showSignedOutUI() {
		updateUI(false);
	}

	// [START on_start_on_stop]
	@Override
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mGoogleApiClient.disconnect();
	}
	// [END on_start_on_stop]

	// [START on_save_instance_state]
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_IS_RESOLVING, mIsResolving);
		outState.putBoolean(KEY_SHOULD_RESOLVE, mShouldResolve);
	}
	// [END on_save_instance_state]

	// [START on_activity_result]
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

		if (requestCode == RC_SIGN_IN) {
			// If the error resolution was not successful we should not resolve further.
			if (resultCode != RESULT_OK) {
				mShouldResolve = false;
			}

			mIsResolving = false;
			mGoogleApiClient.connect();
		}
	}
	// [END on_activity_result]

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
										   @NonNull int[] grantResults) {
		Log.d(TAG, "onRequestPermissionsResult:" + requestCode);
		if (requestCode == RC_PERM_GET_ACCOUNTS) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				showSignedInUI();
			} else {
				Log.d(TAG, "GET_ACCOUNTS Permission Denied.");
			}
		}
	}

	// [START on_connected]
	@Override
	public void onConnected(Bundle bundle) {
		// onConnected indicates that an account was selected on the device, that the selected
		// account has granted any requested permissions to our app and that we were able to
		// establish a service connection to Google Play services.
		Log.d(TAG, "onConnected:" + bundle);
		mShouldResolve = false;

		// Show the signed-in UI
		showSignedInUI();
	}
	// [END on_connected]

	@Override
	public void onConnectionSuspended(int i) {
		// The connection to Google Play services was lost. The GoogleApiClient will automatically
		// attempt to re-connect. Any UI elements that depend on connection to Google APIs should
		// be hidden or disabled until onConnected is called again.
		Log.w(TAG, "onConnectionSuspended:" + i);
	}

	private ConnectionResult mConnectionResult;

	// [START on_connection_failed]
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		// Could not connect to Google Play Services.  The user needs to select an account,
		// grant permissions or resolve an error in order to sign in. Refer to the javadoc for
		// ConnectionResult to see possible error codes.
		Log.d(TAG, "onConnectionFailed:" + connectionResult);

		if (!mIsResolving && mShouldResolve) {
			Log.d(TAG, "inside first if");
			if (connectionResult.hasResolution()) {
				try {
					connectionResult.startResolutionForResult(this, RC_SIGN_IN);
					mIsResolving = true;
					Log.d(TAG, "inside try and second if");
				} catch (IntentSender.SendIntentException e) {
					Log.e(TAG, "Could not resolve ConnectionResult.", e);
					mIsResolving = false;
					mGoogleApiClient.connect();
				}
			} else {
				// Could not resolve the connection result, show the user an
				// error dialog.
				Log.d(TAG, "Error dialog.");
				showErrorDialog(connectionResult);
			}
		} else {
			Log.d(TAG, "Sign out ui");
			// Show the signed-out UI
			showSignedOutUI();
		}

		// Save the result and resolve the connection failure upon a user click.
		mConnectionResult = connectionResult;
		Log.d(TAG, "Saving result.");
	}
	// [END on_connection_failed]

	private void showErrorDialog(ConnectionResult connectionResult) {
		GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
		int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

		if (resultCode != ConnectionResult.SUCCESS) {
			if (apiAvailability.isUserResolvableError(resultCode)) {
				apiAvailability.getErrorDialog(this, resultCode, RC_SIGN_IN,
						new DialogInterface.OnCancelListener() {
							@Override
							public void onCancel(DialogInterface dialog) {
								mShouldResolve = false;
								showSignedOutUI();
							}
						}).show();
			} else {
				Log.w(TAG, "Google Play Services Error:" + connectionResult);
				String errorString = apiAvailability.getErrorString(resultCode);
				Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();

				mShouldResolve = false;
				showSignedOutUI();
			}
		}
	}

	// [START on_click]
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sign_in:
				onSignInClicked();
				break;
			case R.id.sign_out:
				onSignOutClicked();
				break;
			case R.id.disconnect_button:
				onDisconnectClicked();
				break;
		}
	}
	// [END on_click]

	// [START on_sign_in_clicked]
	private void onSignInClicked() {
		// User clicked the sign-in button, so begin the sign-in process and automatically
		// attempt to resolve any errors that occur.
		mShouldResolve = true;
		mGoogleApiClient.connect();

		// Show a message to the user that we are signing in.
		mStatus.setText("Signing in");
	}
	// [END on_sign_in_clicked]

	// [START on_sign_out_clicked]
	private void onSignOutClicked() {
		// Clear the default account so that GoogleApiClient will not automatically
		// connect in the future.
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();
		}

		showSignedOutUI();
	}
	// [END on_sign_out_clicked]

	// [START on_disconnect_clicked]
	private void onDisconnectClicked() {
		// Revoke all granted permissions and clear the default account.  The user will have
		// to pass the consent screen to sign in again.
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
			mGoogleApiClient.disconnect();
		}

		showSignedOutUI();
	}
	// [END on_disconnect_clicked]
}
*/
