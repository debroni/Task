package ua.sumdu.j2se.matusevich.tasks.model;


import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.lang.String;
import java.util.regex.*;

public class TaskIO implements Serializable,  Cloneable {
    public static final String dateFormate = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String regexpRepetative = "^\"(.*)\" from \\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})] to \\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})] every \\[(.*)](.*)[.;]$";
    public static final String regexpNotRepetative = "^\"(.*)\" at \\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})](.*)[.;]$";
    public static final Pattern patternRepetative = Pattern.compile(regexpRepetative);
    public static final Pattern patternNotRepetative = Pattern.compile(regexpNotRepetative);
    public static int parseInterval(String string){
        if (string == null){
            throw new IllegalArgumentException("The string must not be null");
        }
        int seconds = 0;
        Pattern pattern = Pattern.compile("(\\d*) day");
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()){
            seconds += Integer.parseInt(matcher.group(1)) * 86400;
        }
        pattern = Pattern.compile("(\\d*) hour");
        matcher = pattern.matcher(string);
        if(matcher.find()){
            seconds += Integer.parseInt(matcher.group(1)) * 3600;
        }
        pattern = Pattern.compile("(\\d*) minute");
        matcher = pattern.matcher(string);
        if(matcher.find()){
            seconds += Integer.parseInt(matcher.group(1)) * 60;
        }
        pattern = Pattern.compile("(\\d*) second");
        matcher = pattern.matcher(string);
        if(matcher.find()){
            seconds += Integer.parseInt(matcher.group(1));
        }
        return seconds;
    }
    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = null;
        try{
            dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeInt(tasks.size());
            for(Task task : tasks){
                dataOutputStream.writeInt(task.getTitle().length());
                for(int i = 0; i < task.getTitle().length(); i++){
                    dataOutputStream.writeChar(task.getTitle().charAt(i));
                }
                dataOutputStream.writeBoolean(task.isActive());
                dataOutputStream.writeInt(task.getRepeatInterval());
                if(task.isRepeated()){
                    dataOutputStream.writeLong(task.getStartTime().getTime());
                    dataOutputStream.writeLong(task.getEndTime().getTime());
                }else{
                    dataOutputStream.writeLong(task.getTime().getTime());
                }
            }
        }finally {
            if(dataOutputStream != null){
                dataOutputStream.close();
            }
        }
    }

    public static void read(TaskList tasks, InputStream in) throws IOException {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(in);
            int amOfTasks = dataInputStream.readInt();
            for (int i = 0; i < amOfTasks; i++) {
                int detailsLength = dataInputStream.readInt();
                StringBuilder taskDetails = new StringBuilder();
                for (int j = 0; j < detailsLength; j++) {
                    taskDetails.append(dataInputStream.readChar());
                }
                boolean isActive = dataInputStream.readBoolean();
                int interval = dataInputStream.readInt();
                if (interval != 0) {
                    long startTime = dataInputStream.readLong();
                    long endTime = dataInputStream.readLong();
                    Task task = new Task(taskDetails.toString(), new Date(startTime), new Date(endTime), interval);
                    task.setActive(isActive);
                    tasks.add(task);
                } else {
                    long time = dataInputStream.readLong();
                    Task task = new Task(taskDetails.toString(), new Date(time));
                    task.setActive(isActive);
                    tasks.add(task);
                }
            }
        } finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            in.close();
        }
    }

    public static void writeBinary(TaskList tasks, File file) throws IOException{
        BufferedOutputStream bufferedOutputStream = null;
        try{
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            write(tasks,bufferedOutputStream);
        }finally {
            if(bufferedOutputStream != null){
                bufferedOutputStream.close();
            }
        }
    }

    public static void readBinary(TaskList tasks, File file) throws IOException{
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
            read(tasks,fileInputStream);
        }finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }
    }

    public static void write(TaskList tasks, Writer out) throws IOException{
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(out);
            printWriter.write(tasks.toString());
        }finally {
            if(printWriter != null){
                printWriter.close();
            }
        }
    }

    public static void read(TaskList tasks, Reader in) throws IOException, ParseException{
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(in);

            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
            Task task;
            String stringToParse = bufferedReader.readLine();
            while (stringToParse != null){
                Matcher matcher = patternNotRepetative.matcher(stringToParse);
                if (matcher.find()){
                    String details = matcher.group(1).replace("\"\"", "\"");
                    Date time = dateFormat.parse(matcher.group(2));
                    boolean isActive = matcher.group(3).length() == 0;
                    task = new Task(details,time);
                    task.setActive(isActive);
                    tasks.add(task);
                }else{
                    matcher = patternRepetative.matcher(stringToParse);
                    if(matcher.find()) {
                        String details = matcher.group(1).replace("\"\"", "\"");
                        Date start = dateFormat.parse(matcher.group(2));
                        Date end = dateFormat.parse(matcher.group(3));
                        int interval = parseInterval(matcher.group(4));
                        boolean isActive = matcher.group(5).length() == 0;
                        task = new Task(details, start, end, interval);
                        task.setActive(isActive);
                        tasks.add(task);
                    } else if(stringToParse.length() == 0){
                        return;
                    } else {
                        throw new IOException(new StringBuilder("Wrong stringToParse value : ").append(stringToParse).toString());
                    }
                }
                stringToParse = bufferedReader.readLine();
            }
        }finally {
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }
    }

    public static void writeText(TaskList tasks, File file) throws IOException{
        System.out.println("wrinitg starts");
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            write(tasks, bufferedWriter);
        } finally {
            bufferedWriter.close();
        }
        System.out.println("Writing ends");
    }

    public static void readText(TaskList tasks, File file) throws IOException{
        BufferedReader bufferedReader =  null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            read(tasks, bufferedReader);
        } catch (ParseException e) {
            throw new IOException(e);
        }finally {
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }
    }

}