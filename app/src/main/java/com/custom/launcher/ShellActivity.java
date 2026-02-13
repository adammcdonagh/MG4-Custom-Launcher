package com.custom.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Shell Command Activity
 * Provides an interface for executing shell commands and viewing their output
 */
public class ShellActivity extends AppCompatActivity {
    private static final String TAG = "ShellActivity";

    private EditText commandInput;
    private TextView outputText;
    private Button executeButton;
    private Button clearButton;
    private Button closeButton;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);

        mainHandler = new Handler(Looper.getMainLooper());

        // Initialize views
        commandInput = findViewById(R.id.commandInput);
        outputText = findViewById(R.id.outputText);
        executeButton = findViewById(R.id.executeButton);
        clearButton = findViewById(R.id.clearButton);
        closeButton = findViewById(R.id.closeButton);

        // Make output scrollable
        outputText.setMovementMethod(new ScrollingMovementMethod());

        // Setup button listeners
        executeButton.setOnClickListener(v -> executeCommand());
        clearButton.setOnClickListener(v -> clearOutput());
        closeButton.setOnClickListener(v -> finish());

        // Allow pressing enter to execute command
        commandInput.setOnEditorActionListener((v, actionId, event) -> {
            executeCommand();
            return true;
        });

        appendOutput("Shell Command Interface\n");
        appendOutput("═══════════════════════════════════\n");
        appendOutput("Running as system UID on the device\n");
        appendOutput("═══════════════════════════════════\n");
        appendOutput("Common commands:\n");
        appendOutput("  • ls -la - List files in detail\n");
        appendOutput("  • pwd - Print working directory\n");
        appendOutput("  • df -h - Show disk space\n");
        appendOutput("  • ps - List running processes\n");
        appendOutput("  • getprop - Show system properties\n");
        appendOutput("  • dumpsys - Dump system services\n");
        appendOutput("  • cat /proc/cpuinfo - CPU info\n");
        appendOutput("  • mount - Show mounted filesystems\n");
        appendOutput("═══════════════════════════════════\n\n");
    }

    private void executeCommand() {
        String command = commandInput.getText().toString().trim();

        if (command.isEmpty()) {
            appendOutput("⚠ No command entered\n\n");
            return;
        }

        appendOutput("$ " + command + "\n");
        executeButton.setEnabled(false);
        executeButton.setText("Running...");

        // Execute command in background thread
        new Thread(() -> {
            try {
                // Run command through shell for proper environment and permissions
                String[] shellCmd = { "/system/bin/sh", "-c", command };
                Process process = Runtime.getRuntime().exec(shellCmd);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));

                StringBuilder output = new StringBuilder();
                String line;

                // Read stdout
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                // Read stderr
                while ((line = errorReader.readLine()) != null) {
                    output.append("ERROR: ").append(line).append("\n");
                }

                int exitCode = process.waitFor();

                String result = output.toString();
                if (result.isEmpty()) {
                    result = "(No output)\n";
                }
                result += "Exit code: " + exitCode + "\n\n";

                final String finalOutput = result;
                mainHandler.post(() -> {
                    appendOutput(finalOutput);
                    executeButton.setEnabled(true);
                    executeButton.setText("Execute");
                    commandInput.setText("");
                });

            } catch (Exception e) {
                Log.e(TAG, "Command execution failed", e);
                mainHandler.post(() -> {
                    appendOutput("✗ Error: " + e.getMessage() + "\n\n");
                    executeButton.setEnabled(true);
                    executeButton.setText("Execute");
                });
            }
        }).start();
    }

    private void appendOutput(String text) {
        outputText.append(text);

        // Auto-scroll to bottom (check if layout is ready to avoid crashes)
        if (outputText.getLayout() != null) {
            final int scrollAmount = outputText.getLayout().getLineTop(outputText.getLineCount())
                    - outputText.getHeight();
            if (scrollAmount > 0) {
                outputText.scrollTo(0, scrollAmount);
            } else {
                outputText.scrollTo(0, 0);
            }
        }
    }

    private void clearOutput() {
        outputText.setText("");
        appendOutput("Output cleared.\n\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Shell activity destroyed");
    }
}
