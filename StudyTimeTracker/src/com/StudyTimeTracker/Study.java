package com.StudyTimeTracker;

import java.lang.SecurityException;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class Study {

	private Formatter output; // output to Study.bin text file
	private Time time = new Time();
	
	public void openFile() {
		try {
			output = new Formatter("Study.bin");
		} catch (SecurityException se) {
			System.err.println("No write permissions");
		} catch (FileNotFoundException fnfe) {
			System.err.println("File not found");
		}
	}
	
	
	public void addStartTime() {
		try {
			output.format("%s ",DateTimeFormatter.ofPattern("HH:mm:ss").format(time.getTimeStartedStudying()));
		} catch (FormatterClosedException fce) {
			System.err.println("Error printing to file");
		}
	}
	
	private void addTotalTimes() {
		try {
			time.setTimeStoppedStudying(Instant.now());
			output.format("%s ",DateTimeFormatter.ofPattern("HH:mm:ss").format(time.getTimeStoppedStudying()));
			
			output.format("%d %d %d", time.getTotalStudyTimeToday().toDays(),
					time.getTotalStudyTimeToday().toHours(),
					time.getTotalStudyTime().toMinutes());
			
			output.format("%d %d %d", time.getTotalStudyTime().toDays(),
					time.getTotalStudyTime().toHours(),
					time.getTotalStudyTime().toMinutes());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void closeFile() {
		if (output != null) {
			output.close();
		}
	}
	
}
