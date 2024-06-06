package ru.senior.passgen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import ru.senior.passgen.R;
import ru.senior.passgen.core.PasswordGenerator;

public class MainActivity extends AppCompatActivity {

    private CheckBox russianLettersCheckbox;
    private CheckBox engLettersCheckbox;
    private CheckBox symbolsCheckbox;
    private CheckBox numsCheckbox;
    private TextView passwordLenthTextView;
    private TextView passwordCountTextView;
    private Button generateButton;
    private Button copyButton;
    private TextView passwordTextView;
    private final PasswordGenerator passwordGenerator;

    public MainActivity() {
        passwordGenerator = new PasswordGenerator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        russianLettersCheckbox = findViewById(R.id.russianLetterCheckbox);
        engLettersCheckbox = findViewById(R.id.engLetterCheckbox);
        symbolsCheckbox = findViewById(R.id.symbolsLetterCheckbox);
        numsCheckbox = findViewById(R.id.numsLetterCheckbox);
        passwordLenthTextView = findViewById(R.id.passwordLenthTextView);
        passwordCountTextView = findViewById(R.id.password_count);

        generateButton = findViewById(R.id.generate_button);
        copyButton = findViewById(R.id.copy_button);
        passwordTextView = findViewById(R.id.generatedPasswordTextView);


        generateButton.setOnClickListener(this::onGenerateButtonClick);
        copyButton.setOnClickListener(this::onCopyButtonClick);

    }

    private void onGenerateButtonClick(View view) {
        final boolean russianSymbolsEnabled = russianLettersCheckbox.isChecked();
        final boolean engSymbolsEnabled = engLettersCheckbox.isChecked();
        final boolean numsSymbolsEnabled = numsCheckbox.isChecked();
        final boolean specialSymbolsEnabled = symbolsCheckbox.isChecked();

        final int length = Integer.parseInt(passwordLenthTextView.getText().toString());

        final int passwordCount = Integer.parseInt(passwordCountTextView.getText().toString());

        final StringBuilder generatedPasswordsStringBuilder = new StringBuilder();

        for (int i = 0; i < passwordCount; i++) {
            final String generatedPassword = passwordGenerator.generatePassword(
                    length,
                    numsSymbolsEnabled,
                    engSymbolsEnabled,
                    specialSymbolsEnabled,
                    russianSymbolsEnabled
            );

            generatedPasswordsStringBuilder
                    .append(generatedPassword)
                    .append('\n');
        }


        passwordTextView.setText(generatedPasswordsStringBuilder.toString());

    }

    private void onCopyButtonClick(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", passwordTextView.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Скопировано", Toast.LENGTH_SHORT).show();
    }
}