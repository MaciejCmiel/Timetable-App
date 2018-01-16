package com.example.macx.projecttimetable;

/**
 * Created by MacX on 2017-12-12.
 * {@link Lesson} represents a single day in week.
 */

public class Lesson {

    private String mSubjectName;
    private String mLecturer;
    private String mLocation;
    private String mStartTime;
    private String mEndTime;
    private String mTypeOfClasses;
    private int mDayOfWeek;
    private String mCycle;

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
    public Lesson(int dayOfWeek, String startTime, String endTime, String subjectName, String lecturer, String location, String typeOfClasses, String cycle) {

        mDayOfWeek = dayOfWeek;
        mStartTime = startTime;
        mEndTime = endTime;
        mSubjectName = subjectName;
        mLecturer = lecturer;
        mLocation = location;
        mTypeOfClasses = typeOfClasses;
        mCycle = cycle;
    }


    /**
     * Get subject of classes.
     */
    public String getSubjectName() {
        return mSubjectName;
    }

    /**
     * Get name of lecturer.
     */
    public String getLecturer() {
        return mLecturer;
    }

    /**
     * Get location
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get start time
     */
    public String getStartTime() {
        return mStartTime;
    }

    /**
     * Get end time
     */
    public String getEndTime() {
        return mEndTime;
    }

    /**
     * Get day of week
     */
    public int getDayOfWeek() {
        return mDayOfWeek;
    }

    /**
     * Get type of classes
     */
    public String getTypeOfClasses() {
        return mTypeOfClasses;
    }

    /**
     * Get cycle
     */
    public String getCycle() {
        return mCycle;
    }


}