package com.coronatracker.About;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coronatracker.Home.HomeActivity;
import com.coronatracker.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {
    private static final String TAG = "AboutFragment";
    RelativeLayout exit, appInfo, socials, contactDeveloper, privacyPolicy, changeLanguage, mainRelLayout;
    String developer = "coronastats@mail.com";
    String subject = "I Love this App";
    String body = "Thanks for this App, it is So useful!";
    TextView translateMe, title_holder5, title_holder4;

    boolean lang_selected = true;
    Context mContext = getActivity();
    Context context;
    Resources resources;

    private CardView progress_CardView;


    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        Log.d(TAG, "onCreateView: ");



        //change

        //Hooks
        exit = view.findViewById(R.id.signOutLayout);
        appInfo = (RelativeLayout) view.findViewById(R.id.appInfo);
        socials = (RelativeLayout) view.findViewById(R.id.socials);
        contactDeveloper = (RelativeLayout) view.findViewById(R.id.contact_developer);
        privacyPolicy = (RelativeLayout) view.findViewById(R.id.privacy_Policy);
        mainRelLayout = (RelativeLayout) view.findViewById(R.id.mainRelLayout);
        translateMe = (TextView) view.findViewById(R.id.visit_me);
        title_holder4 = (TextView) view.findViewById(R.id.title_holder4);
        progress_CardView = (CardView) view.findViewById(R.id.progress_CardView);
        progress_CardView.setVisibility(View.GONE);


        //sign out dialog
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exit();
            }
        });

        //privacy Policy
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPrivacyPolicyDialog();
            }
        });


        //feedback intent
        contactDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Sending message to developer using intent ");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{developer});
                intent.putExtra(Intent.EXTRA_SUBJECT, R.string.iloveThisApp);
                intent.putExtra(Intent.EXTRA_TEXT, R.string.thanks);
                intent.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(intent, getResources().getString(R.string.sendEmailWith)));

                } catch (android.content.ActivityNotFoundException exception) {
                    Toast.makeText(AboutFragment.this.getActivity(), R.string.noEmailClient, Toast.LENGTH_LONG).show();
                }

            }
        });

        //socials
        socials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Socials.class);
                startActivity(intent);
            }
        });


        //app info screen
        appInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appInfo();
            }
        });

        //link to translate
        translateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate_link();
            }
        });


        return view;
    }


    private void translate_link() {
        goToThisURL(getResources().getString(R.string.translationLink));

    }

    private void goToThisURL(String s) {
        Uri linkURI = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, linkURI));
    }

    //Logout Dialog
    private void Exit() {
        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(getActivity(), R.style.AlertDialogDefault);


        final AlertDialog dialogLogout = new AlertDialog.Builder(wrappedContext)
                .setMessage(R.string.logoutConfirm)
                .setPositiveButton(getResources().getString(R.string.logout), null)
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .show();
        Button positiveButton = dialogLogout.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().moveTaskToBack(true);
                getActivity().finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        });


    }





    private void noEmailClient() {
        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(getActivity(), R.style.AlertDialogDefault);
        final AlertDialog dialogLogout = new AlertDialog.Builder(wrappedContext)
                .setMessage(R.string.logoutConfirm)
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .show();
    }


    //privacy policy dialog popup

    private void setPrivacyPolicyDialog() {
        Log.d(TAG, "setPrivacyPolicyDialog: Dialog onclick");
        String message = getResources().getString(R.string.privacy_description);

        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(getActivity(), R.style.AlertDialog);
        final AlertDialog dialogPrivacy = new AlertDialog.Builder(wrappedContext)
                .setTitle(getResources().getString(R.string.legal_info))
                .setMessage(getResources().getString(R.string.privacy_description))
                .setCancelable(true)
                .setPositiveButton(getResources().getString(R.string.cancel), null)
                .show();


        dialogPrivacy.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.dark_grey));


    }

    private void mChangLanguageDialog() {
        //Array of language
        ContextThemeWrapper wrappedContext = new ContextThemeWrapper(getActivity(), R.style.AlertDialogDefault);
        final String[] languagesList = {"English", "Afrikaans"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(wrappedContext);
        mBuilder.setTitle("Select Language");
        mBuilder.setSingleChoiceItems(languagesList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    //English
                    setLocal("en");
                    getActivity().recreate();
                } else if (i == 1) {
                    //Afrikaans
                    setLocal("af");
                    getActivity().recreate();
                }
                //Dismiss Dialog when language selected
                dialogInterface.dismiss();


            }
        });
        AlertDialog mDialog = mBuilder.create();

        //show dialog
        mDialog.show();


    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());

        //Save changes in shared preferences
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_language", lang);
        editor.apply();

    }
/*
This has to be implemented in the future
 */
    //Load language saved in shared Preference

    private void loadLocals() {
        SharedPreferences pref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        String language = pref.getString("My_language", "");
        setLocal(language);
    }

    private void appInfo() {
        Intent appInfo = new Intent(getContext(), AppInfo.class);
        startActivity(appInfo);


    }


}
