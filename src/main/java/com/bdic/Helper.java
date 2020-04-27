package com.bdic;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Helper {
    public static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private InputStream errorStream;
        private Consumer<String> inputConsumer;
        private Consumer<String> errorConsumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.inputConsumer = consumer;
        }

        public StreamGobbler(InputStream inputStream, InputStream errorStream, Consumer<String> inputConsumer, Consumer<String> errorConsumer) {
            this.inputStream = inputStream;
            this.errorStream = errorStream;
            this.inputConsumer = inputConsumer;
            this.errorConsumer = errorConsumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(inputConsumer);
            new BufferedReader(new InputStreamReader(errorStream)).lines()
                    .forEach(errorConsumer);
        }
    }

    public static class SystemCommand {
        public static String runCommand(String command, File direction, int timeout) throws IOException, InterruptedException {
            if (direction == null) {
                direction = new File(System.getProperty("user.home"));
            }
            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            ProcessBuilder builder = new ProcessBuilder();
            //javac a.java && java HelloWorld
            if (isWindows) {
                builder.command("cmd.exe", "/c", command);
            } else {
                builder.command("sh", "-c", command);
            }
            builder.directory(direction.getParentFile());
            Process process = builder.start();
//            StringBuilder sb1 = new StringBuilder();
//            StringBuilder sb2 = new StringBuilder();
            MyStringBuilder sb3 = new MyStringBuilder();
            MyStringBuilder sb4 = new MyStringBuilder();
//            Helper.StreamGobbler streamGobbler =
//                    new Helper.StreamGobbler(process.getInputStream(), process.getErrorStream(), sb1::append, sb2::append);
//            Executors.newSingleThreadExecutor().submit(streamGobbler);
            Helper.StreamGobbler streamGobbler2 =
                    new Helper.StreamGobbler(process.getInputStream(), process.getErrorStream(), sb3::append, sb4::append);
            Executors.newSingleThreadExecutor().submit(streamGobbler2);
            boolean exitCode = process.waitFor(timeout, TimeUnit.SECONDS);
            assert exitCode;
            if (sb3.length() != 0) return sb3.toString();
            else return sb4.toString();
        }
    }

    static class MyStringBuilder {
        @Override
        public String toString() {
            return sb.toString();
        }

        StringBuilder sb;

        MyStringBuilder() {
            sb = new StringBuilder();
        }

        public void append(CharSequence s) {
            sb.append(s);
        }

        public void append(String s) {
            sb.append(s);
            sb.append('\n');
        }
        public int length(){
            return sb.length();
        }
    }
}
