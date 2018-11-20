package com.giant.boot.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellCommand {
    private static final Logger LOG = LoggerFactory.getLogger(ExecuteShellCommand.class);

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();

        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[]{"sh", "-c", command}, null, null);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            LOG.trace(e.getMessage(), e);
        }

        return output.toString();

    }

}
