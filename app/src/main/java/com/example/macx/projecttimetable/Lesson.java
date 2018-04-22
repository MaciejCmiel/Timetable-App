package com.example.macx.projecttimetable;

/**
 * Created by MacX on 2017-12-12.
 * {@link Lesson} represents a single day in week.
 */

public class Lesson {

    private String subjectName;
    private String lecturer;
    private String location;
    private String startTime;
    private String endTime;
    private String typeOfClasses;
    private int dayOfWeek;
    private String cycle;

    /**
     * Create a new TimeInterval object.
     *
     * @param dayOfWeek     represents day in which this subject take place
     * @param startTime
     * @param endTime
     * @param subjectName
     * @param lecturer      is the name of teacher
     * @param typeOfClasses is short name for type ex W - wykład,
     * @param cycle         represents cycle of clases, ex I połowa sem.
     */
    public Lesson(int dayOfWeek, String startTime, String endTime, String subjectName, String lecturer,
                  String location, String typeOfClasses, String cycle) {

        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subjectName = subjectName;
        this.lecturer = lecturer;
        this.location = location;
        this.typeOfClasses = typeOfClasses;
        this.cycle = cycle;
    }


    /**
     * Get subject of classes.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Get name of lecturer.
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Get location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Get end time
     */
    public String getEndTime() {
        return endTime;
    }


    /**
     * Get type of classes
     */
    public String getTypeOfClasses() {
        return typeOfClasses;
    }

    /**
     * Get cycle
     */
    public String getCycle() {
        return cycle;
    }


}